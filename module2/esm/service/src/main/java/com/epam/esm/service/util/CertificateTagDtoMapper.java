package com.epam.esm.service.util;

import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.service.dto.CertificateTagDto;

import java.util.List;
import java.util.stream.Collectors;

public class CertificateTagDtoMapper {

    public static CertificateTag toObject(CertificateTagDto certificateTagDto) {
        return new CertificateTag(
                GiftCertificateDtoMapper.toObject(certificateTagDto.getCertificate()),
                TagDtoMapper.toObject(certificateTagDto.getTags())
        );
    }

    public static List<CertificateTag> toObject(List<CertificateTagDto> certificateTagDtoList) {
        return certificateTagDtoList.stream()
                .map((certificateTagDto -> toObject(certificateTagDto)))
                .collect(Collectors.toList());
    }

    public static CertificateTagDto toDto(CertificateTag certificateTag) {
        return new CertificateTagDto(
                GiftCertificateDtoMapper.toDto(certificateTag.getCertificate()),
                TagDtoMapper.toDto(certificateTag.getTags())
        );
    }

    public static List<CertificateTagDto> toDto(List<CertificateTag> certificateTagList) {
        return certificateTagList.stream()
                .map((certificateTag -> toDto(certificateTag)))
                .collect(Collectors.toList());
    }


}
