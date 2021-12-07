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
        // TODO create certificate, then create tags
        GiftCertificate certificate = certificateTag.getCertificate();
        long createdCertificateId = giftCertificateService.create(certificate);
        List<Tag> tags = certificateTag.getTags();
        tags.stream().forEach((tag) -> {
            List<Tag> tagList = tagService.read(tag);
            if (tagList.size() == 0) {
                long createdTagId = tagService.createTag(tag);
                tagCertificateDao.create(createdTagId, createdCertificateId);
            }
        });
        // TODO what should I return?
        return 0;
    }

    @Override
    public boolean update(long id, CertificateTag certificateTag) {
        // TODO update certificate, then create tags
        GiftCertificate certificate = certificateTag.getCertificate();
        boolean certificateUpdateResult = giftCertificateService.update(id, certificate);
        List<Tag> tags = certificateTag.getTags();
        tags.stream().forEach((tag) -> {
            List<Tag> tagList = tagService.read(tag);
            if (tagList.size() == 0) {
                long createdTagId = tagService.createTag(tag);
                tagCertificateDao.create(createdTagId, id);
            }
        });
        // TODO what should I return?
        return false;
    }
}
