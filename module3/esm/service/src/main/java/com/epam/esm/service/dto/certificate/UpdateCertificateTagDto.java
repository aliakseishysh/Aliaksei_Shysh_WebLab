package com.epam.esm.service.dto.certificate;

import com.epam.esm.service.dto.tag.CreateTagDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCertificateTagDto {
    @NotNull(message = "Field certificateIdToUpdate should not be empty")
    @Min(value = 1, message = "Field certificateIdToUpdate should be greater than 1")
    private Long certificateIdToUpdate;
    @Valid
    private UpdateCertificateDto updateCertificateDto;
    @Valid
    private List<CreateTagDto> createTagDtoList;
}
