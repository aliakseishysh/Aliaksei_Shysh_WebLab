package com.epam.esm.service;
import com.epam.esm.database.entity.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {
    long create(GiftCertificate certificate);
    List<GiftCertificate> read();
    List<GiftCertificate> read(String tagName);
    boolean update(long id, GiftCertificate certificate);
    boolean delete(long id);
}
