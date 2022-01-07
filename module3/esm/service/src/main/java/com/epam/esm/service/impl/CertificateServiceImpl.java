package com.epam.esm.service.impl;

import com.epam.esm.database.dao.CertificateDao;
import com.epam.esm.database.entity.Certificate;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.dto.certificate.CertificateDto;
import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.DeleteCertificateByIdDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;
import com.epam.esm.service.util.CertificateMapper;
import com.epam.esm.service.util.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateDao certificateDao;
    @Autowired
    public CertificateServiceImpl(CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    public long save(CreateCertificateDto certificate) {
        return certificateDao.save(CertificateMapper.toObject(certificate)).getId();
    }
    @Override
    public List<CertificateDto> findAll() {
        return CertificateMapper.toDto(certificateDao.findAll());
    }

    @Override
    public List<CertificateDto> read(SearchDataDto searchDataDto) {
        // certificateDao.
        throw new UnsupportedOperationException();
        // return CertificateTagMapper.toDto(certificateDao.read(SearchDataMapper.toObject(searchDataDto)));
    }

    //TODO test with postman
    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public CertificateDto update(UpdateCertificateDto certificate) {
        Optional<Certificate> dbCertificateOptional = certificateDao.findById(certificate.getCertificateIdToUpdate());
        if (dbCertificateOptional.isPresent()) {
            Certificate dbCertificate = dbCertificateOptional.get();
            if (certificate.getName() != null) {
                dbCertificate.setName(certificate.getName());
            }
            if (certificate.getDescription() != null) {
                dbCertificate.setDescription(certificate.getDescription());
            }
            if (certificate.getPrice() != null) {
                dbCertificate.setPrice(certificate.getPrice());
            }
            if (certificate.getDuration() != null) {
                dbCertificate.setDuration(certificate.getDuration());
            }
            if (certificate.getCreateDate() != null) {
                dbCertificate.setCreateDate(certificate.getCreateDate());
            }
            if (certificate.getLastUpdateDate() != null) {
                dbCertificate.setLastUpdateDate(certificate.getLastUpdateDate());
            }
            if (certificate.getUpdateTagDtoList() != null) {
                certificate.getUpdateTagDtoList().forEach(
                        (entryToUpdate) -> {
                            boolean isExists = dbCertificate.getTags()
                                    .stream()
                                    .noneMatch((dbEntry) -> entryToUpdate.getName().equals(dbEntry.getName()));
                            if (isExists) {
                                dbCertificate.getTags().add(TagMapper.toObject(entryToUpdate));
                            }
                        }
                );
            }

            dbCertificateOptional = Optional.of(certificateDao.update(dbCertificate));
        }
        return dbCertificateOptional.map(CertificateMapper::toDto)
                .orElse(CertificateMapper
                .updateCertificateDtoToCertificateDto(certificate));
    }

    //TODO return value
    @Override
    public void delete(DeleteCertificateByIdDto deleteCertificateByIdDto) {
        certificateDao.deleteById(deleteCertificateByIdDto.getId());
    }

}
