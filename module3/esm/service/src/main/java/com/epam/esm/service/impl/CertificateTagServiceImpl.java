package com.epam.esm.service.impl;

import com.epam.esm.database.dao.TagCertificateDao;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.tag.TagDto;
import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.CreateCertificateTagDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateTagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.util.TagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CertificateTagServiceImpl {

//    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateTagServiceImpl.class);
//    private final CertificateService certificateService;
//    private final TagService tagService;
//    private final TagCertificateDao tagCertificateDao;

//    @Autowired
//    public CertificateTagServiceImpl(CertificateService certificateService, TagService tagService, TagCertificateDao tagCertificateDao) {
//        this.certificateService = certificateService;
//        this.tagService = tagService;
//        this.tagCertificateDao = tagCertificateDao;
//    }
//
//    @Override
//    public long create(CreateCertificateTagDto certificateTagDto) {
//        CreateCertificateDto certificate = certificateTagDto.getCreateGiftCertificateDto();
//        long createdCertificateId = certificateService.save(certificate);
//        createTagsForCertificate(createdCertificateId, certificateTagDto.getCreateTagDtoList());
//        return createdCertificateId;
//    }
//
//    @Override
//    public boolean update(UpdateCertificateDto updateCertificateDto) {
//        UpdateCertificateDto certificate = updateCertificateDto.getUpdateCertificateDto();
//        boolean certificateUpdateResult = certificateService.update(updateCertificateTagDto.getCertificateIdToUpdate(), certificate);
//        createTagsForCertificate(updateCertificateTagDto.getCertificateIdToUpdate(), updateCertificateTagDto.getCreateTagDtoList());
//        return certificateUpdateResult;
//    }
//
//    private void createTagsForCertificate(long certificateId, List<CreateTagDto> tags) {
//        if (tags != null) {
//            for (CreateTagDto tag : tags) {
//                List<TagDto> tagList = tagService.findByName(TagMapper.toDtoFromDto(tag));
//                if (tagList.size() == 0) {
//                    long createdTagId = 0;
//                    try {
//                        createdTagId = tagService.save(tag);
//                        tagCertificateDao.create(createdTagId, certificateId);
//                    } catch (EntityAlreadyExistsServiceException e) {
//                        LOGGER.info("Entity " + tag + " already exists.");
//                    }
//                } else {
//                    boolean isInUse = tagCertificateDao.read(tagList.get(0).getId(), certificateId);
//                    if (!isInUse) {
//                        tagCertificateDao.create(tagList.get(0).getId(), certificateId);
//                    }
//                }
//            }
//
//        }
//    }

}
