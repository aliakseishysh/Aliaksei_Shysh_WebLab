package com.epam.esm.service.util;

import com.epam.esm.database.entity.Certificate;
import com.epam.esm.service.dto.certificate.CertificateDto;
import com.epam.esm.service.dto.certificate.CreateCertificateDto;
import com.epam.esm.service.dto.certificate.UpdateCertificateDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CertificateMapper {

    public static Certificate toObject(CertificateDto certificateDto) {
        return new Certificate(
                certificateDto.getId(),
                certificateDto.getName(),
                certificateDto.getDescription(),
                certificateDto.getPrice(),
                certificateDto.getDuration(),
                certificateDto.getCreateDate(),
                certificateDto.getLastUpdateDate(),
                TagMapper.toObject(certificateDto.getTags())
        );
    }

    public static Certificate toObject(CreateCertificateDto createGiftCertificateDto) {
        return new Certificate(
                null,
                createGiftCertificateDto.getName(),
                createGiftCertificateDto.getDescription(),
                createGiftCertificateDto.getPrice(),
                createGiftCertificateDto.getDuration(),
                createGiftCertificateDto.getCreateDate(),
                createGiftCertificateDto.getLastUpdateDate(),
                TagMapper.toObjectCreateTagList(createGiftCertificateDto.getCreateTagDtoList())
        );
    }

    public static Certificate toObject(UpdateCertificateDto updateCertificateDto) {
        return new Certificate(
                null,
                updateCertificateDto.getName(),
                updateCertificateDto.getDescription(),
                updateCertificateDto.getPrice(),
                updateCertificateDto.getDuration(),
                updateCertificateDto.getCreateDate(),
                updateCertificateDto.getLastUpdateDate(),
                TagMapper.toObjectUpdateTagList(updateCertificateDto.getUpdateTagDtoList())
        );
    }

    public static CertificateDto toDto(Certificate certificate) {
        return new CertificateDto(
                certificate.getId(),
                certificate.getName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getDuration(),
                certificate.getCreateDate(),
                certificate.getLastUpdateDate(),
                TagMapper.toDto(certificate.getTags())
        );
    }

    public static CertificateDto createCertificateDtoToCertificateDto(CreateCertificateDto createGiftCertificateDto) {
        return new CertificateDto(
                null,
                createGiftCertificateDto.getName(),
                createGiftCertificateDto.getDescription(),
                createGiftCertificateDto.getPrice(),
                createGiftCertificateDto.getDuration(),
                createGiftCertificateDto.getCreateDate(),
                createGiftCertificateDto.getLastUpdateDate(),
                TagMapper.createTagDtoToTagDto(createGiftCertificateDto.getCreateTagDtoList())
        );
    }

    public static CertificateDto updateCertificateDtoToCertificateDto(UpdateCertificateDto updateGiftCertificateDto) {
        return new CertificateDto(
                null,
                updateGiftCertificateDto.getName(),
                updateGiftCertificateDto.getDescription(),
                updateGiftCertificateDto.getPrice(),
                updateGiftCertificateDto.getDuration(),
                updateGiftCertificateDto.getCreateDate(),
                updateGiftCertificateDto.getLastUpdateDate(),
                TagMapper.updateTagDtoListToTagDtoList(updateGiftCertificateDto.getUpdateTagDtoList())
        );
    }

    public static List<CertificateDto> toDto(List<Certificate> certificates) {
        return certificates.stream().map((CertificateMapper::toDto)).collect(Collectors.toList());
    }

    public static List<Certificate> toObject(List<CertificateDto> certificatesDto) {
        return certificatesDto.stream().map((CertificateMapper::toObject)).collect(Collectors.toList());
    }


}
