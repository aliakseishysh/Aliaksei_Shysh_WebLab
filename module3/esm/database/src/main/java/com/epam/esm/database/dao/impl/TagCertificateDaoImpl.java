package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.TagCertificateDao;
import com.epam.esm.database.entity.CertificateIdTagId;
import com.epam.esm.database.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagCertificateDaoImpl implements TagCertificateDao {
    private static final String CREATE_TAG_CERTIFICATE = "INSERT INTO tags_gift_certificates(tag_id, certificate_id) VALUES (?, ?)";
    private static final String READ_TAG_CERTIFICATE = "SELECT tag_id, certificate_id FROM tags_gift_certificates WHERE tag_id = ? AND certificate_id = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagCertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(long tagId, long certificateId) {
        return jdbcTemplate.update(CREATE_TAG_CERTIFICATE, tagId, certificateId) == 1;
    }

    @Override
    public boolean read(long tagId, long certificateId) {
        return jdbcTemplate.query(READ_TAG_CERTIFICATE, new BeanPropertyRowMapper<>(CertificateIdTagId.class), tagId, certificateId).size() == 1;
    }
}
