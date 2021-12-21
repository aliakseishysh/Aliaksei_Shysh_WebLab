package com.epam.esm.service.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchOrderByIdDto {
    @NotNull(message = "Field orderId should not be empty")
    @Min(value = 1, message = "Field orderId should be greater than 1")
    private Long orderId;
}
