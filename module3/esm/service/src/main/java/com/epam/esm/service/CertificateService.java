package com.epam.esm.service;

import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.certificate.CertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.DeleteCertificateByIdDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

import java.util.List;

public interface CertificateService {

    long create(CreateCertificateDto createGiftCertificateDto);

    List<CertificateDto> read();

    List<CertificateTagDto> read(SearchDataDto searchData);

    boolean update(long id, UpdateCertificateDto certificate);

    boolean delete(DeleteCertificateByIdDto deleteCertificateByIdDto);

}
