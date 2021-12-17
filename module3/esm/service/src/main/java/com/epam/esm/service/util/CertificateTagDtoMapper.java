package com.epam.esm.service.util;

import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.service.dto.CertificateTagDto;

import java.util.List;
import java.util.stream.Collectors;

public class CertificateTagDtoMapper {

    public static CertificateTag toObject(CertificateTagDto certificateTagDto) {
        return new CertificateTag(
                GiftCertificateMapper.toObject(certificateTagDto.getCertificate()),
                TagMapper.toObject(certificateTagDto.getTags())
        );
    }

    public static List<CertificateTag> toObject(List<CertificateTagDto> certificateTagDtoList) {
        return certificateTagDtoList.stream()
                .map((CertificateTagDtoMapper::toObject))
                .collect(Collectors.toList());
    }

    public static CertificateTagDto toDto(CertificateTag certificateTag) {
        return new CertificateTagDto(
                GiftCertificateMapper.toDto(certificateTag.getCertificate()),
                TagMapper.toDto(certificateTag.getTags())
        );
    }

    public static List<CertificateTagDto> toDto(List<CertificateTag> certificateTagList) {
        return certificateTagList.stream()
                .map((CertificateTagDtoMapper::toDto))
                .collect(Collectors.toList());
    }


}
