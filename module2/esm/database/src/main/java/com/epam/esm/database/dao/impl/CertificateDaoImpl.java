package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.CertificateDao;
import com.epam.esm.database.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Component
public class CertificateDaoImpl implements CertificateDao {

    private static final String CREATE_CERTIFICATE = "INSERT INTO gift_certificates(name, description, price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_CERTIFICATE = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificates";
    private static final String READ_CERTIFICATE_BY_ID = "SELECT id, name, description, price, duration, create_date, last_update_date FROM gift_certificates WHERE id = ?";
    private static final String READ_CERTIFICATE_BY_TAG_NAME = "SELECT gift_certificates.id, gift_certificates.name, description, price, duration, create_date, last_update_date "
            + "FROM tags_gift_certificates INNER JOIN gift_certificates ON gift_certificates.id = tags_gift_certificates.certificate_id INNER JOIN tags ON "
            + "tags.id = tags_gift_certificates.tag_id WHERE tags.name = ?";
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
    public List<GiftCertificate> read(String tagName) {
        return jdbcTemplate.query(READ_CERTIFICATE_BY_TAG_NAME, new BeanPropertyRowMapper<>(GiftCertificate.class), tagName);
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
