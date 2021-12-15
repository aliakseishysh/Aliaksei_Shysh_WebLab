package com.epam.esm.validation;

import com.epam.esm.service.dto.SearchDataDto;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

import java.util.List;

import static com.epam.esm.database.dao.SqlKeyWord.ASC;
import static com.epam.esm.database.dao.SqlKeyWord.DESC;

public class SearchDataValidator {

    private static boolean validateTagNameInternal(String tagName) {
        return tagName == null || tagName.length() < 254;
    }

    private static boolean validateNamePartialInternal(String partialName) {
        return partialName == null || partialName.length() < 254;
    }

    private static boolean validateDescriptionPartialInternal(String partialDescription) {
        return partialDescription == null || partialDescription.length() < 254;
    }

    private static boolean validateSortDataInternal(List<SearchDataDto.SortDto> sortDtoList) {
        boolean result = false;
        if (sortDtoList == null) {
            result = true;
        } else {
            for (SearchDataDto.SortDto sortDto : sortDtoList) {
                if (sortDto.getSortBy() != null
                        && sortDto.getOrderBy()  != null
                        && (sortDto.getOrderBy().equals(ASC)
                            || sortDto.getOrderBy().equals(DESC))) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public static void validate(SearchDataDto searchDataDto) throws EntityIsNotValidServiceException {
        if (validateTagNameInternal(searchDataDto.getTagName())
                && validateNamePartialInternal(searchDataDto.getPartialCertificateName())
                && validateDescriptionPartialInternal(searchDataDto.getPartialCertificateDescription())
                && validateSortDataInternal(searchDataDto.getSortData())
        ) {
            // is valid
        }
        else {
            throw new EntityIsNotValidServiceException("Entity " + searchDataDto + " is not valid.");
        }
    }
}
