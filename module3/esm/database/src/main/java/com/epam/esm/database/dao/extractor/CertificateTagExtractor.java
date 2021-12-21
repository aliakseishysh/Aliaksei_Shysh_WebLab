package com.epam.esm.database.dao.extractor;

import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.Certificate;
import com.epam.esm.database.entity.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CertificateTagExtractor implements ResultSetExtractor<List<CertificateTag>> {
    private final Map<Long, CertificateTag> certificatesWithTags = new HashMap<>();

    @Override
    public List<CertificateTag> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<CertificateTag> results = new ArrayList<>();
        while (rs.next()) {
            CertificateTag certificateTag = certificatesWithTags.get(rs.getLong(1));
            if (certificateTag == null) {
                certificateTag = new CertificateTag();
                Certificate certificate = extractGiftCertificate(rs);
                certificateTag.setCertificate(certificate);
                certificateTag.setTags(new ArrayList<>());
                certificatesWithTags.put(rs.getLong(1), certificateTag);
                results.add(certificateTag);
            }
            Tag tag = extractTag(rs);
            if (tag.getId() != 0 && tag.getName() != null) {
                certificateTag.getTags().add(tag);
            }
        }
        return results;
    }

    private Tag extractTag(ResultSet rs) throws SQLException {
        Tag tag = new Tag();
        tag.setId(rs.getLong(8));
        tag.setName(rs.getString(9));
        return tag;
    }

    private Certificate extractGiftCertificate(ResultSet rs) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setId(rs.getLong(1));
        certificate.setName(rs.getString(2));
        certificate.setDescription(rs.getString(3));
        certificate.setPrice(rs.getBigDecimal(4));
        certificate.setDuration(rs.getInt(5));
        certificate.setCreateDate(rs.getObject(6, LocalDateTime.class));
        certificate.setLastUpdateDate(rs.getObject(7, LocalDateTime.class));
        return certificate;
    }
}
