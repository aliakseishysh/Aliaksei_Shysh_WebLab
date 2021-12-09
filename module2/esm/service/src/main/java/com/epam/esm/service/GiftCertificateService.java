package com.epam.esm.service;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.database.entity.SearchData;

import java.util.List;

public interface GiftCertificateService {
    long create(GiftCertificate certificate);
    List<GiftCertificate> read();
    List<CertificateTag> read(String tagName);
    List<CertificateTag> read(SearchData searchData);
    boolean update(long id, GiftCertificate certificate);
    boolean delete(long id);

}
