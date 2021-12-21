package com.epam.esm.service.impl;

import com.epam.esm.database.dao.CertificateDao;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.dto.CertificateTagDto;
import com.epam.esm.service.dto.certificate.CertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.DeleteCertificateByIdDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import com.epam.esm.service.util.CertificateTagMapper;
import com.epam.esm.service.util.CertificateMapper;
import com.epam.esm.service.util.SearchDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateDao certificateDao;
    @Autowired
    public CertificateServiceImpl(CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    public long create(CreateCertificateDto certificate) {
        return certificateDao.create(CertificateMapper.toObject(certificate));
    }
    @Override
    public List<CertificateDto> read() {
        return CertificateMapper.toDto(certificateDao.read());
    }

    @Override
    public List<CertificateTagDto> read(SearchDataDto searchDataDto) {
        return CertificateTagMapper.toDto(certificateDao.read(SearchDataMapper.toObject(searchDataDto)));
    }

    @Override
    public boolean update(long id, UpdateCertificateDto certificate) {
        return certificateDao.update(id, CertificateMapper.toObject(certificate));
    }

    @Override
    public boolean delete(DeleteCertificateByIdDto deleteCertificateByIdDto) {
        return certificateDao.delete(deleteCertificateByIdDto.getId());
    }

}
