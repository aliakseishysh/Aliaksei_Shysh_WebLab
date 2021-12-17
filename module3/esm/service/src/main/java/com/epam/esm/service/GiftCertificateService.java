package com.epam.esm.service;

import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.CreateGiftCertificateDto;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

import java.util.List;

public interface GiftCertificateService {

    long create(CreateGiftCertificateDto createGiftCertificateDto) throws EntityIsNotValidServiceException;

    List<GiftCertificateDto> read();

    List<CertificateTagDto> read(SearchDataDto searchData) throws EntityIsNotValidServiceException;

    boolean update(long id, GiftCertificateDto certificate) throws EntityIsNotValidServiceException;

    boolean delete(GiftCertificateDto giftCertificateDto) throws EntityIsNotValidServiceException;

}
