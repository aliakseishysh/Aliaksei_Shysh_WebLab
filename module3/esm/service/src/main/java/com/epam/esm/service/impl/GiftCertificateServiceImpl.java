package com.epam.esm.service.impl;

import com.epam.esm.database.dao.CertificateDao;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.DeleteCertificateByIdDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import com.epam.esm.service.util.CertificateTagDtoMapper;
import com.epam.esm.service.util.GiftCertificateMapper;
import com.epam.esm.service.util.SearchDataDtoMapper;
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
    public long create(CreateCertificateDto certificate) throws EntityIsNotValidServiceException {
        return certificateDao.create(GiftCertificateMapper.toObject(certificate));
    }
    @Override
    public List<GiftCertificateDto> read() {
        return GiftCertificateMapper.toDto(certificateDao.read());
    }

    @Override
    public List<CertificateTagDto> read(SearchDataDto searchDataDto) throws EntityIsNotValidServiceException {
        return CertificateTagDtoMapper.toDto(certificateDao.read(SearchDataDtoMapper.toObject(searchDataDto)));
    }

    @Override
    public boolean update(long id, UpdateCertificateDto certificate) {
        return certificateDao.update(id, GiftCertificateMapper.toObject(certificate));
    }

    @Override
    public boolean delete(DeleteCertificateByIdDto deleteCertificateByIdDto) {
        return certificateDao.delete(deleteCertificateByIdDto.getId());
    }

}
