package com.epam.esm.database.dao.extractor;

import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
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
                GiftCertificate giftCertificate = new GiftCertificate();
                giftCertificate.setId(rs.getLong(1));
                giftCertificate.setName(rs.getString(2));
                giftCertificate.setDescription(rs.getString(3));
                giftCertificate.setPrice(rs.getBigDecimal(4));
                giftCertificate.setDuration(rs.getInt(5));
                giftCertificate.setCreateDate(rs.getObject(6, LocalDateTime.class));
                giftCertificate.setLastUpdateDate(rs.getObject(7, LocalDateTime.class));
                certificateTag.setCertificate(giftCertificate);
                certificateTag.setTags(new ArrayList<>());
                certificatesWithTags.put(rs.getLong(1), certificateTag);
                results.add(certificateTag);
            }
            Tag tag = new Tag();
            tag.setId(rs.getLong(8));
            tag.setName(rs.getString(9));
            certificateTag.getTags().add(tag);
        }
        return results;
    }
}
