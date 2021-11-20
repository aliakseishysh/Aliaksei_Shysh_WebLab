package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.CertificateDao;
import com.epam.esm.database.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class CertificateDaoImpl implements CertificateDao {

    private JdbcTemplate jdbcTemplate;
    private static final String CREATE_CERTIFICATE = "INSERT INTO tags(name) VALUES (?)";

    @Autowired
    public CertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(GiftCertificate certificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE_CERTIFICATE, new String[] {"id"});
            statement.setString(1, certificate.getName());
            statement.setString(2, certificate.getDescription());
            statement.setBigDecimal(3, certificate.getPrice());
            statement.setInt(4, certificate.getDuration());
            // statement.setTimestamp(5, certificate.getCreateDate());
            return  statement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<GiftCertificate> read() {
        return null;
    }

    @Override
    public Optional<GiftCertificate> read(long id) {
        return Optional.empty();
    }

    @Override
    public List<GiftCertificate> readByCertificateId(long id) {
        return null;
    }

    @Override
    public boolean update(GiftCertificate giftCertificate) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
