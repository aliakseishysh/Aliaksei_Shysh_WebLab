package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.CertificateDao;
import com.epam.esm.database.dao.extractor.CertificateTagExtractor;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.database.entity.SearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CertificateDaoImpl implements CertificateDao {

    private static final String SPACE_SEPARATOR = " ";
    private static final String CREATE_CERTIFICATE = "INSERT INTO gift_certificates(name, description, price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_CERTIFICATE = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificates";

    private static final String READ_CERTIFICATES_WITH_TAGS_CTE = "WITH cte_ac AS (SELECT gift_certificates.id AS c_id, gift_certificates.name AS c_name, "
            + "description, price, duration, create_date, last_update_date, tags.id AS t_id, tags.name AS t_name "
            + "FROM gift_certificates LEFT JOIN tags_gift_certificates ON gift_certificates.id = tags_gift_certificates.certificate_id LEFT JOIN tags ON tags.id = tags_gift_certificates.tag_id "
            + "), cte_ot AS (SELECT gift_certificates.id AS o_id FROM gift_certificates LEFT JOIN tags_gift_certificates ON gift_certificates.id = tags_gift_certificates.certificate_id "
            + "LEFT JOIN tags ON tags.id = tags_gift_certificates.tag_id %s) SELECT cte_ac.c_id AS id, cte_ac.c_name AS name, "
            + "cte_ac.description, cte_ac.price, cte_ac.duration, cte_ac.create_date, cte_ac.last_update_date, "
            + "cte_ac.t_id AS id, cte_ac.t_name AS name FROM cte_ac WHERE EXISTS(SELECT cte_ot.o_id FROM cte_ot WHERE cte_ac.c_id = cte_ot.o_id)";

    private static final String AND_CERTIFICATE_NAME_LIKE = "AND cte_ac.c_name LIKE ?";
    private static final String AND_CERTIFICATE_DESCRIPTION_LIKE = "AND cte_ac.description LIKE ?";
    private static final String AND_CERTIFICATE_NAME_AND_DESCRIPTION_LIKE = "AND cte_ac.c_name LIKE ? AND cte_ac.description LIKE ?";

    private static final String WHERE_TAGS_NAME = "WHERE tags.name = ?";


    private static final String DELETE_CERTIFICATE = "DELETE FROM gift_certificates WHERE id = ?";
    private static final String UPDATE_CERTIFICATE = "UPDATE gift_certificates SET id = COALESCE(?, id), name = COALESCE(?, name), description = COALESCE(?, description), "
            + "price = COALESCE(?, price), duration = COALESCE(?, duration), create_date = COALESCE(?, create_date), "
            + "last_update_date = COALESCE(?, last_update_date) WHERE id = ?";

    private static final String PERCENT = "%";
    private static final String EMPTY_LINE = "";
    private static final String ORDER_BY = "ORDER BY";
    private static final String COMMA = ",";
    private static final String QUESTION = "?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(GiftCertificate certificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE_CERTIFICATE, new String[]{"id"});
            statement.setString(1, certificate.getName());
            statement.setString(2, certificate.getDescription());
            statement.setBigDecimal(3, certificate.getPrice());
            statement.setInt(4, certificate.getDuration());
            statement.setTimestamp(5, Timestamp.valueOf(certificate.getCreateDate()));
            statement.setTimestamp(6, Timestamp.valueOf(certificate.getLastUpdateDate()));
            return statement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<GiftCertificate> read() {
        return jdbcTemplate.query(READ_CERTIFICATE, new BeanPropertyRowMapper<>(GiftCertificate.class));
    }

    @Override
    public List<CertificateTag> read(SearchData searchData) {
        List<String> queryParameters = new ArrayList<>();
        String resultQuery = READ_CERTIFICATES_WITH_TAGS_CTE;
        resultQuery = tryAddSearchByTag(searchData, queryParameters, resultQuery);
        resultQuery = tryAddSearchByPartialNameDescription(searchData, queryParameters, resultQuery);
        resultQuery = tryAddOrderBy(searchData, queryParameters, resultQuery);
        return jdbcTemplate.query(resultQuery, new CertificateTagExtractor(), queryParameters.toArray());
    }

    private String tryAddSearchByTag(SearchData searchData, List<String> queryParameters, String resultQuery) {
        if (searchData.getTagName() != null) {
            queryParameters.add(searchData.getTagName());
            resultQuery = String.format(resultQuery, WHERE_TAGS_NAME);
        } else {
            resultQuery = String.format(resultQuery, EMPTY_LINE);
        }
        return resultQuery;
    }

    private String tryAddSearchByPartialNameDescription(SearchData searchData, List<String> queryParameters, String resultQuery) {
        if (searchData.getPartialCertificateName() != null && searchData.getPartialCertificateDescription() == null) {
            queryParameters.add(PERCENT + searchData.getPartialCertificateName() + PERCENT);
            resultQuery = String.join(SPACE_SEPARATOR, resultQuery, AND_CERTIFICATE_NAME_LIKE);
        } else if (searchData.getPartialCertificateName() == null && searchData.getPartialCertificateDescription() != null) {
            queryParameters.add(PERCENT + searchData.getPartialCertificateDescription() + PERCENT);
            resultQuery = String.join(SPACE_SEPARATOR, resultQuery, AND_CERTIFICATE_DESCRIPTION_LIKE);
        } else if (searchData.getPartialCertificateName() != null && searchData.getPartialCertificateDescription() != null) {
            queryParameters.add(PERCENT + searchData.getPartialCertificateName() + PERCENT);
            queryParameters.add(PERCENT + searchData.getPartialCertificateDescription() + PERCENT);
            resultQuery = String.join(SPACE_SEPARATOR, resultQuery, AND_CERTIFICATE_NAME_AND_DESCRIPTION_LIKE);
        }
        return resultQuery;
    }

    private String tryAddOrderBy(SearchData searchData, List<String> queryParameters, String resultQuery) {
        if (searchData.getSortData() != null && searchData.getSortData().size() >= 1) {
            resultQuery = String.join(SPACE_SEPARATOR, resultQuery, ORDER_BY);
            for (int i = 0; i < searchData.getSortData().size(); i++) {
                resultQuery = String.join(SPACE_SEPARATOR, resultQuery, QUESTION, searchData.getSortData().get(i).getOrderBy());
                queryParameters.add(searchData.getSortData().get(i).getSortBy());
                if (searchData.getSortData().size() - i > 1) {
                    resultQuery += COMMA;
                }
            }
        }
        return resultQuery;
    }


    @Override
    public boolean update(long id, GiftCertificate giftCertificate) {
        return jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CERTIFICATE);
            statement.setObject(1, giftCertificate.getId());
            statement.setObject(2, giftCertificate.getName());
            statement.setObject(3, giftCertificate.getDescription());
            statement.setObject(4, giftCertificate.getPrice());
            statement.setObject(5, giftCertificate.getDuration());
            Timestamp createDate = giftCertificate.getCreateDate() != null ? Timestamp.valueOf(giftCertificate.getCreateDate()) : null;
            statement.setObject(6, createDate);
            Timestamp lastUpdateDate = giftCertificate.getLastUpdateDate() != null ? Timestamp.valueOf(giftCertificate.getLastUpdateDate()) : null;
            statement.setObject(7, lastUpdateDate);
            statement.setLong(8, id);
            return statement;
        }) == 1;
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE_CERTIFICATE, id) == 1;
    }
}
