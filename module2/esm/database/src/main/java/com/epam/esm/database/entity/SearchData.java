package com.epam.esm.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchData {
    private String tagName;
    private String partialCertificateName;
    private String partialCertificateDescription;
    private List<Sort> sortData;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sort {
        private String sortBy;
        private OrderType orderBy;
    }

    public enum OrderType {
        ASC, DESC
    }


}
