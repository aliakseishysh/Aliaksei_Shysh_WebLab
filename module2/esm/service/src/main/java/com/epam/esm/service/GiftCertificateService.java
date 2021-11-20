package com.epam.esm.service;

import com.epam.esm.database.entity.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {
    List<GiftCertificate> findCertificates();
}
