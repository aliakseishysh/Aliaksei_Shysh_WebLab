package com.epam.esm.database.dao;

import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.database.entity.SearchData;

import java.util.List;

public interface CertificateDao {
    long create(GiftCertificate certificate);
    List<GiftCertificate> read();
    List<CertificateTag> read(SearchData searchData);
    boolean update(long id, GiftCertificate giftCertificate);
    boolean delete(long id);

}
