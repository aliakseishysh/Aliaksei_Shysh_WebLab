package com.epam.esm.service;

import com.epam.esm.service.dto.certificate.CreateCertificateTagDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateTagDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

public interface CertificateTagService {

    long create(CreateCertificateTagDto createCertificateTagDto);

    boolean update(UpdateCertificateTagDto updateCertificateTagDto);

}
