package com.epam.esm.service;

import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.CreateCertificateTagDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateTagDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

public interface CertificateTagService {

    long create(CreateCertificateDto createCertificateTagDto);

    boolean update(UpdateCertificateDto updateCertificateTagDto);

}
