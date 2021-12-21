package com.epam.esm.service.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {
    @NotNull
    @Min(value = 1, message = "Field userId should be greater than 1")
    private Long userId;
    @NotNull
    @Min(value = 1, message = "Field certificateId should be greater than 1")
    private Long certificateId;
    @NotNull(message = "Field price should not be empty")
    @DecimalMin(value = "0.0", message = "Field price should be greater than 0.0")
    @Digits(integer = 10,fraction = 2, message = "Max integer part should be 10 and max fraction part should be 2")
    private BigDecimal price;
    @NotNull(message = "Field purchaseDate should not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime purchaseDate;
}
