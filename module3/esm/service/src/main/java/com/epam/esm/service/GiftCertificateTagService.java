package com.epam.esm.service;

import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.CreateGiftCertificateDto;
import com.epam.esm.service.dto.certificate.CreateUpdateCertificateTagDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

public interface GiftCertificateTagService {

    long create(CreateUpdateCertificateTagDto createUpdateCertificateTagDto) throws EntityIsNotValidServiceException;

    boolean update(long id, CertificateTagDto certificateTagDto) throws EntityIsNotValidServiceException;

}
