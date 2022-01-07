package com.epam.esm.service.dto.certificate;

import com.epam.esm.service.dto.tag.CreateTagDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CreateCertificateDto {
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
    @Min(value = 1, message = "Field duration should be equals or greater than 1")
    private Integer duration;
    @NotNull(message = "Field createDate should not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createDate;
    @NotNull(message = "Field lastUpdateDate should not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime lastUpdateDate;
    @Valid
    private List<CreateTagDto> createTagDtoList;
}
