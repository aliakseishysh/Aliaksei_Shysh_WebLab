package com.epam.esm.service.impl;

import com.epam.esm.database.dao.TagCertificateDao;
import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CertificateTagServiceImpl implements CertificateTagService {

    private GiftCertificateService giftCertificateService;
    private TagService tagService;
    private TagCertificateDao tagCertificateDao;
    @Autowired
    public CertificateTagServiceImpl(GiftCertificateService giftCertificateService, TagService tagService, TagCertificateDao tagCertificateDao) {
        this.giftCertificateService = giftCertificateService;
        this.tagService = tagService;
        this.tagCertificateDao = tagCertificateDao;
    }

    @Override
    public long create(CertificateTag certificateTag) {
        GiftCertificate certificate = certificateTag.getCertificate();
        long createdCertificateId = giftCertificateService.create(certificate);
        createTagsForCertificate(createdCertificateId, certificateTag.getTags());
        return createdCertificateId;
    }

    @Override
    public boolean update(long id, CertificateTag certificateTag) {
        GiftCertificate certificate = certificateTag.getCertificate();
        boolean certificateUpdateResult = giftCertificateService.update(id, certificate);
        createTagsForCertificate(id, certificateTag.getTags());
        return certificateUpdateResult;
    }

    // TODO change return type to boolean?
    private void createTagsForCertificate(long certificateId, List<Tag> tags) {
        tags.stream().forEach((tag) -> {
            List<Tag> tagList = tagService.read(tag);
            if (tagList.size() == 0) {
                long createdTagId = tagService.createTag(tag);
                tagCertificateDao.create(createdTagId, certificateId);
            } else {
                tagCertificateDao.create(tagList.get(0).getId(), certificateId);
            }
        });
    }
}
