package com.epam.esm.service;

import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.certificate.CertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.DeleteCertificateByIdDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;

import java.util.List;

public interface CertificateService {

    long save(CreateCertificateDto createGiftCertificateDto);

    List<CertificateDto> findAll();

    List<CertificateDto> read(SearchDataDto searchData);

    CertificateDto update(UpdateCertificateDto certificate);

    void delete(DeleteCertificateByIdDto deleteCertificateByIdDto);

}
