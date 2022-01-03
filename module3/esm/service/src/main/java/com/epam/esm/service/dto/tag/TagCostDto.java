package com.epam.esm.service.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagCostDto {
    private TagDto tagDto;
    private BigDecimal costDto;
}
