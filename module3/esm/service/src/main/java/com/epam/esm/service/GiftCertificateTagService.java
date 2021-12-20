package com.epam.esm.service;

import com.epam.esm.service.dto.certificate.CreateCertificateTagDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateTagDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

public interface GiftCertificateTagService {

    long create(CreateCertificateTagDto createCertificateTagDto) throws EntityIsNotValidServiceException;

    boolean update(UpdateCertificateTagDto updateCertificateTagDto) throws EntityIsNotValidServiceException;

}
