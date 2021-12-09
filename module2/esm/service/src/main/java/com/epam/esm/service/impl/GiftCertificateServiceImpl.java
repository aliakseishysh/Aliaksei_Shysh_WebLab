package com.epam.esm.service.impl;

import com.epam.esm.database.dao.CertificateDao;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.database.entity.SortOrder;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private CertificateDao certificateDao;
    @Autowired
    public GiftCertificateServiceImpl(CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    public long create(GiftCertificate certificate) {
        return certificateDao.create(certificate);
    }
    @Override
    public List<GiftCertificate> read() {
        return certificateDao.read();
    }

    @Override
    public List<CertificateTag> read(String tagName) {
        return certificateDao.read(tagName);
    }

    @Override
    public List<CertificateTag> readSortedOrdered(List<SortOrder> sortOrder) {
        return certificateDao.readSortedOrdered(sortOrder);
    }

    @Override
    public List<CertificateTag> readByPartialName(String partialName) {
        return certificateDao.readByPartialName(partialName);
    }

    @Override
    public List<CertificateTag> readByPartialDescription(String partialDescription) {
        return certificateDao.readByPartialDescription(partialDescription);
    }

    @Override
    public boolean update(long id, GiftCertificate certificate) {
        return certificateDao.update(id, certificate);
    }

    @Override
    public boolean delete(long id) {
        return certificateDao.delete(id);
    }

}
