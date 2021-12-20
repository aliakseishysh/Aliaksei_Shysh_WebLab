package com.epam.esm.service.dto.certificate;

import com.epam.esm.service.dto.tag.CreateTagDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCertificateTagDto {
    @Valid
    private CreateCertificateDto createGiftCertificateDto;
    @Valid
    private List<CreateTagDto> createTagDtoList;
}
