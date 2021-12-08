package com.epam.esm.database.dao;

import com.epam.esm.database.entity.GiftCertificate;

import java.util.List;

public interface CertificateDao {
    long create(GiftCertificate certificate);
    List<GiftCertificate> read();
    List<GiftCertificate> read(long id);
    List<GiftCertificate> read(String tagName);
    boolean update(long id, GiftCertificate giftCertificate);
    boolean delete(long id);
}
