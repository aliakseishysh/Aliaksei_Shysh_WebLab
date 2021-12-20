package com.epam.esm.service.dto.certificate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCertificateDto {
    @Size(min = 1, max = 254, message = "Field name should be between 1 and 254 characters")
    private String name;
    @Size(min = 1, max = 254, message = "Field description should be between 1 and 254 characters")
    private String description;
    @DecimalMin(value = "0.0", message = "Field price should be greater than 0.0")
    @Digits(integer = 10,fraction = 2, message = "Max integer part should be 10 and max fraction part should be 2")
    private BigDecimal price;
    @Min(value = 1, message = "Field duration should be equals or greater than 1")
    private Integer duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime lastUpdateDate;
}
