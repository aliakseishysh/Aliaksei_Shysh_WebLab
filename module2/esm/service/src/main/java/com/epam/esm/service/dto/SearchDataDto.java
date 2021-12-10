package com.epam.esm.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDataDto {
    private String tagName;
    private String partialCertificateName;
    private String partialCertificateDescription;
    private List<SortDto> sortData;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SortDto {
        private String sortBy;
        private String orderBy;
    }

}
