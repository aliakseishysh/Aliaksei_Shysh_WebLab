package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.TagCertificateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagCertificateDaoImpl implements TagCertificateDao {
    private static final String CREATE_TAG_CERTIFICATE = "INSERT INTO tags_gift_certificates(tag_id, certificate_id) VALUES (?, ?)";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TagCertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(long tagId, long certificateId) {
        return jdbcTemplate.update(CREATE_TAG_CERTIFICATE, tagId, certificateId) == 1;
    }
}
