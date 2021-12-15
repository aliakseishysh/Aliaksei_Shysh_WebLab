package com.epam.esm.service.util;

import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.service.dto.GiftCertificateDto;

import java.util.List;
import java.util.stream.Collectors;

public class GiftCertificateDtoMapper {

    public static GiftCertificate toObject(GiftCertificateDto certificateDto) {
        return new GiftCertificate(
                certificateDto.getId(),
                certificateDto.getName(),
                certificateDto.getDescription(),
                certificateDto.getPrice(),
                certificateDto.getDuration(),
                certificateDto.getCreateDate(),
                certificateDto.getLastUpdateDate()
        );
    }

    public static List<GiftCertificate> toObject(List<GiftCertificateDto> certificatesDto) {
        return certificatesDto.stream().map((GiftCertificateDtoMapper::toObject)).collect(Collectors.toList());
    }

    public static GiftCertificateDto toDto(GiftCertificate certificate) {
        return new GiftCertificateDto(
                certificate.getId(),
                certificate.getName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getDuration(),
                certificate.getCreateDate(),
                certificate.getLastUpdateDate()
        );
    }

    public static List<GiftCertificateDto> toDto(List<GiftCertificate> certificates) {
        return certificates.stream().map((GiftCertificateDtoMapper::toDto)).collect(Collectors.toList());
    }


}
