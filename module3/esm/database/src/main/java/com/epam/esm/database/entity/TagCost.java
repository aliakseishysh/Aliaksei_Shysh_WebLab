package com.epam.esm.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagCost {
    private Tag tag;
    private BigDecimal cost;
}
