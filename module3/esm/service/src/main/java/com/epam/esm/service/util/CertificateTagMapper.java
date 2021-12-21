package com.epam.esm.service.util;

import com.epam.esm.database.entity.CertificateTag;
import com.epam.esm.service.dto.CertificateTagDto;

import java.util.List;
import java.util.stream.Collectors;

public class CertificateTagMapper {

    public static CertificateTag toObject(CertificateTagDto certificateTagDto) {
        return new CertificateTag(
                CertificateMapper.toObject(certificateTagDto.getCertificate()),
                TagMapper.toObject(certificateTagDto.getTags())
        );
    }

    public static List<CertificateTag> toObject(List<CertificateTagDto> certificateTagDtoList) {
        return certificateTagDtoList.stream()
                .map((CertificateTagMapper::toObject))
                .collect(Collectors.toList());
    }

    public static CertificateTagDto toDto(CertificateTag certificateTag) {
        return new CertificateTagDto(
                CertificateMapper.toDto(certificateTag.getCertificate()),
                TagMapper.toDto(certificateTag.getTags())
        );
    }

    public static List<CertificateTagDto> toDto(List<CertificateTag> certificateTagList) {
        return certificateTagList.stream()
                .map((CertificateTagMapper::toDto))
                .collect(Collectors.toList());
    }


}
