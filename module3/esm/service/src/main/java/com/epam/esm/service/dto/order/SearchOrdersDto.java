package com.epam.esm.service.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchOrdersDto {
    @Min(value = 1, message = "Field orderId should be greater than 1")
    private Long orderId;
    @Size(min = 1, max = 254, message = "Filed username should be between 1 and 254 characters")
    private String username;
}
