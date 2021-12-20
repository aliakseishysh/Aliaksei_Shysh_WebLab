package com.epam.esm.service.dto.certificate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCertificateByIdDto {
    @NotNull(message = "Field id should not be empty")
    @Min(value = 1, message = "Field id should be equals or greater than 1")
    private Long id;
}
