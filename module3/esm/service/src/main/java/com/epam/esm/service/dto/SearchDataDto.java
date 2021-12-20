package com.epam.esm.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDataDto {
    @Size(min = 1, max = 254, message = "Field tagName should be between 1 and 254 characters")
    private String tagName;
    @Size(min = 1, max = 254, message = "Field partialCertificateName should be between 1 and 254 characters")
    private String partialCertificateName;
    @Size(min = 1, max = 254, message = "Field partialCertificateDescription should be between 1 and 254 characters")
    private String partialCertificateDescription;
    @Valid
    private List<SortDto> sortData;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SortDto {
        @Pattern(regexp = "^(cte_ac.c_name|cte_ac.description)$", message = "Field sortBy should be equals cte_ac.c_name or cte_ac.description")
        private String sortBy;
        @Pattern(regexp = "^(ASC|DESC)$", message = "Field orderBy should be equals ASC or DESC")
        private String orderBy;
    }

}
