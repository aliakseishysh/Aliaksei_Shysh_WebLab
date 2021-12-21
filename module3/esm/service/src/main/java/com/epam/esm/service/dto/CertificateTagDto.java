package com.epam.esm.service.dto;

import com.epam.esm.service.dto.certificate.CertificateDto;
import com.epam.esm.service.dto.tag.TagDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateTagDto {
    private CertificateDto certificate;
    private List<TagDto> tags;
}
