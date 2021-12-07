package com.epam.esm.service;
import com.epam.esm.database.entity.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {
    long create(GiftCertificate certificate);
    List<GiftCertificate> findCertificates();
    boolean update(long id, GiftCertificate certificate);
    boolean delete(long id);
}
