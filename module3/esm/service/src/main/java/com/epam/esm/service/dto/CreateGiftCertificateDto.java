package com.epam.esm.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGiftCertificateDto {
    @NotEmpty(message = "Field name should not be empty")
    @Size(min = 1, max = 254, message = "Field name should be between 1 and 254 characters")
    private String name;
    @NotEmpty(message = "Field description should not be empty")
    @Size(min = 1, max = 254, message = "Field description should be between 1 and 254 characters")
    private String description;
    @NotNull(message = "Field price should not be empty")
    @DecimalMin(value = "0.0", message = "Field price should be greater than 0.0")
    @Digits(integer = 10,fraction = 2, message = "Max integer part should be 10 and max fraction part should be 2")
    private BigDecimal price;
    @NotNull(message = "Field duration should not be empty")
    @Size(min = 1, message = "Field duration should be equals or greater than 1")
    private Integer duration;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss.SSS")
    private LocalDateTime createDate;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss.SSS")
    private LocalDateTime lastUpdateDate;
}
