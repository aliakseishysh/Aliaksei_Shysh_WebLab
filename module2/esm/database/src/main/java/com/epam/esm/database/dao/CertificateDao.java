package com.epam.esm.database.dao;

import com.epam.esm.database.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface CertificateDao {
    boolean create(GiftCertificate tag);
    List<GiftCertificate> read();
    Optional<GiftCertificate> read(long id);
    List<GiftCertificate> readByCertificateId(long id);
    boolean update(GiftCertificate giftCertificate);
    boolean delete(long id);
}
