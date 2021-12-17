package com.epam.esm.service.dto.certificate;

import com.epam.esm.service.dto.CreateGiftCertificateDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateCertificateTagDto {
    @NotEmpty(message = "List of gift certificates should not be empty")
    private CreateGiftCertificateDto createGiftCertificateDto;
    private List<CreateTagDto> createTagDtoList;
}
