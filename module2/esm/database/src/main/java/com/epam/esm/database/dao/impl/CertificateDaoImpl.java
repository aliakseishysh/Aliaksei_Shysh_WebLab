package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.CertificateDao;
import com.epam.esm.database.dao.extractor.CertificateTagExtractor;
import com.epam.esm.database.dao.mapper.CertificateTagMapper;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CertificateDaoImpl implements CertificateDao {

    private static final String CREATE_CERTIFICATE = "INSERT INTO gift_certificates(name, description, price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_CERTIFICATE = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificates";
    private static final String READ_CERTIFICATE_BY_ID = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificates WHERE id = ?";
    private static final String READ_CERTIFICATES_WITH_TAGS = "SELECT gift_certificates.id, gift_certificates.name, description, price, duration, create_date, last_update_date, "
            + "tags.id, tags.name "
            + "FROM tags_gift_certificates INNER JOIN gift_certificates ON gift_certificates.id = tags_gift_certificates.certificate_id INNER JOIN tags ON "
            + "tags.id = tags_gift_certificates.tag_id";
    // TODO load sql script from separate file!!!!!!!!!!!!!!!!!
    private static final String READ_CERTIFICATES_WITH_TAGS_BY_TAG = "WITH cte_all_certificates AS (SELECT gift_certificates.id AS all_id, gift_certificates.name AS all_name, "
            + "description AS all_desc, price AS all_price, duration AS all_dur, create_date AS all_cd, last_update_date AS all_ud, tags.id AS all_t_ud, tags.name AS all_t_name "
            + "FROM tags_gift_certificates INNER JOIN gift_certificates ON gift_certificates.id = tags_gift_certificates.certificate_id INNER JOIN tags ON tags.id = tags_gift_certificates.tag_id "
            + "), cte_certificates_only_with_tag AS (SELECT gift_certificates.id AS only_id FROM tags_gift_certificates INNER JOIN gift_certificates ON gift_certificates.id = tags_gift_certificates.certificate_id "
            + "INNER JOIN tags ON tags.id = tags_gift_certificates.tag_id WHERE tags.name = ?) SELECT cte_all_certificates.all_id AS id, cte_all_certificates.all_name AS name, "
            + "cte_all_certificates.all_desc AS description, cte_all_certificates.all_price AS price, cte_all_certificates.all_dur AS duration, cte_all_certificates.all_cd AS create_date, cte_all_certificates.all_ud AS last_update_date, "
            + "cte_all_certificates.all_t_ud AS id, cte_all_certificates.all_t_name AS name FROM cte_all_certificates INNER JOIN cte_certificates_only_with_tag ON cte_all_certificates.all_id = cte_certificates_only_with_tag.only_id";
    private static final String DELETE_CERTIFICATE = "DELETE FROM gift_certificates WHERE id = ?";
    private static final String UPDATE_CERTIFICATE = "UPDATE gift_certificates SET id = COALESCE(?, id), name = COALESCE(?, name), description = COALESCE(?, description), "
            + "price = COALESCE(?, price), duration = COALESCE(?, duration), create_date = COALESCE(?, create_date), "
            + "last_update_date = COALESCE(?, last_update_date) WHERE id = ?";
    private JdbcTemplate jdbcTemplate;

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
    public List<GiftCertificate> read(long id) {
        return jdbcTemplate.query(READ_CERTIFICATE_BY_ID, new BeanPropertyRowMapper<>(GiftCertificate.class), id);
    }

    @Override
    public List<CertificateTag> read(String tagName) {
        return jdbcTemplate.query(READ_CERTIFICATES_WITH_TAGS_BY_TAG, new CertificateTagExtractor(), tagName);
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
