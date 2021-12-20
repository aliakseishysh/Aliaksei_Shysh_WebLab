package com.epam.esm.service;

import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.DeleteCertificateByIdDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

import java.util.List;

public interface GiftCertificateService {

    long create(CreateCertificateDto createGiftCertificateDto) throws EntityIsNotValidServiceException;

    List<GiftCertificateDto> read();

    List<CertificateTagDto> read(SearchDataDto searchData) throws EntityIsNotValidServiceException;

    boolean update(long id, UpdateCertificateDto certificate) throws EntityIsNotValidServiceException;

    boolean delete(DeleteCertificateByIdDto deleteCertificateByIdDto) throws EntityIsNotValidServiceException;

}
