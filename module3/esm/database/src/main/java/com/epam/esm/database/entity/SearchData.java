package com.epam.esm.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchData {
    private List<String> tagNames;
    private String partialCertificateName;
    private String partialCertificateDescription;
    private List<Sort> sortData;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sort {
        private String sortBy;
        private String orderBy;
    }

}
