package com.epam.esm.database.dao;

import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.database.entity.SortOrder;

import java.util.List;

public interface CertificateDao {
    long create(GiftCertificate certificate);
    List<GiftCertificate> read();
    List<GiftCertificate> read(long id);
    List<CertificateTag> read(String tagName);
    List<CertificateTag> readSortedOrdered(List<SortOrder> sortOrder);
    List<CertificateTag> readByPartialName(String partialName);
    List<CertificateTag> readByPartialDescription(String partialDescription);
    boolean update(long id, GiftCertificate giftCertificate);
    boolean delete(long id);

}
