package com.epam.esm.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortOrder {
    private String sortBy;
    private OrderType orderBy;

    public enum OrderType {
        ASC, DESC
    }

}
