package com.epam.esm.service;

import java.security.cert.Certificate;
import java.util.List;

public interface GiftCertificateService {
    List<Certificate> findCertificates();
}
