package com.epam.esm.service.impl;

import com.epam.esm.database.dao.CertificateDao;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import com.epam.esm.service.util.CertificateTagDtoMapper;
import com.epam.esm.service.util.GiftCertificateDtoMapper;
import com.epam.esm.service.util.SearchDataDtoMapper;
import com.epam.esm.validation.GiftCertificateDtoValidator;
import com.epam.esm.validation.SearchDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final CertificateDao certificateDao;
    @Autowired
    public GiftCertificateServiceImpl(CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    public long create(GiftCertificateDto certificate) throws EntityIsNotValidServiceException {
        GiftCertificateDtoValidator.validate(certificate);
        return certificateDao.create(GiftCertificateDtoMapper.toObject(certificate));
    }
    @Override
    public List<GiftCertificateDto> read() {
        return GiftCertificateDtoMapper.toDto(certificateDao.read());
    }

    @Override
    public List<CertificateTagDto> read(SearchDataDto searchDataDto) throws EntityIsNotValidServiceException {
        SearchDataValidator.validate(searchDataDto);
        return CertificateTagDtoMapper.toDto(certificateDao.read(SearchDataDtoMapper.toObject(searchDataDto)));
    }

    @Override
    public boolean update(long id, GiftCertificateDto certificate) throws EntityIsNotValidServiceException {
        GiftCertificateDtoValidator.validatePartial(certificate);
        return certificateDao.update(id, GiftCertificateDtoMapper.toObject(certificate));
    }

    @Override
    public boolean delete(GiftCertificateDto giftCertificateDto) throws EntityIsNotValidServiceException {
        GiftCertificateDtoValidator.validateId(giftCertificateDto.getId());
        return certificateDao.delete(GiftCertificateDtoMapper.toObject(giftCertificateDto).getId());
    }

}
