package com.epam.esm.service.util;

import com.epam.esm.database.entity.GiftCertificate;
import com.epam.esm.service.dto.CreateGiftCertificateDto;
import com.epam.esm.service.dto.GiftCertificateDto;

import java.util.List;
import java.util.stream.Collectors;

public class GiftCertificateMapper {

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

    public static GiftCertificateDto toDto(CreateGiftCertificateDto createGiftCertificateDto) {
        return new GiftCertificateDto(
                null,
                createGiftCertificateDto.getName(),
                createGiftCertificateDto.getDescription(),
                createGiftCertificateDto.getPrice(),
                createGiftCertificateDto.getDuration(),
                createGiftCertificateDto.getCreateDate(),
                createGiftCertificateDto.getLastUpdateDate()
        );
    }

    public static List<GiftCertificateDto> toDto(List<GiftCertificate> certificates) {
        return certificates.stream().map((GiftCertificateMapper::toDto)).collect(Collectors.toList());
    }

    public static List<GiftCertificate> toObject(List<GiftCertificateDto> certificatesDto) {
        return certificatesDto.stream().map((GiftCertificateMapper::toObject)).collect(Collectors.toList());
    }


}
