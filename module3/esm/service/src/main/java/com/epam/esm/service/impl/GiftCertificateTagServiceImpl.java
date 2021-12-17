package com.epam.esm.service.impl;

import com.epam.esm.database.dao.TagCertificateDao;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.GiftCertificateTagService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.CreateGiftCertificateDto;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.dto.certificate.CreateUpdateCertificateTagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GiftCertificateTagServiceImpl implements GiftCertificateTagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiftCertificateTagServiceImpl.class);
    private final GiftCertificateService giftCertificateService;
    private final TagService tagService;
    private final TagCertificateDao tagCertificateDao;
    @Autowired
    public GiftCertificateTagServiceImpl(GiftCertificateService giftCertificateService, TagService tagService, TagCertificateDao tagCertificateDao) {
        this.giftCertificateService = giftCertificateService;
        this.tagService = tagService;
        this.tagCertificateDao = tagCertificateDao;
    }

    @Override
    public long create(CreateUpdateCertificateTagDto certificateTagDto) throws EntityIsNotValidServiceException {
        CreateGiftCertificateDto certificate = certificateTagDto.getCreateGiftCertificateDto();
        long createdCertificateId = giftCertificateService.create(certificate);
        // createTagsForCertificate(createdCertificateId, certificateTagDto.getCreateTagDtoList());
        return createdCertificateId;
    }

    @Override
    public boolean update(long id, CertificateTagDto certificateTag) throws EntityIsNotValidServiceException {
        GiftCertificateDto certificate = certificateTag.getCertificate();
        boolean certificateUpdateResult = giftCertificateService.update(id, certificate);
        // createTagsForCertificate(id, certificateTag.getTags());
        return certificateUpdateResult;
    }

    private void createTagsForCertificate(long certificateId, List<CreateTagDto> tags) throws EntityIsNotValidServiceException {
//        if (tags != null) {
//            for (TagDto tag : tags) {
//                List<TagDto> tagList = tagService.read(tag);
//                if (tagList.size() == 0) {
//                    long createdTagId = 0;
//                    try {
//                        createdTagId = tagService.createTag(tag);
//                    } catch (EntityAlreadyExistsServiceException e) {
//                        LOGGER.info("Entity " + tag + " already exists.");
//                    }
//                    tagCertificateDao.create(createdTagId, certificateId);
//                } else {
//                    tagCertificateDao.create(tagList.get(0).getId(), certificateId);
//                }
//            }
//
//        }
    }
}
