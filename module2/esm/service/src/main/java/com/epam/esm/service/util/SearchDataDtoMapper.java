package com.epam.esm.service.util;

import com.epam.esm.database.entity.SearchData;
import com.epam.esm.service.dto.SearchDataDto;

import java.util.List;
import java.util.stream.Collectors;

public class SearchDataDtoMapper {

    private static SearchDataDto.SortDto toDto(SearchData.Sort sort) {
        return new SearchDataDto.SortDto(
                sort.getSortBy(),
                sort.getOrderBy()
        );
    }
    private static SearchData.Sort toObject(SearchDataDto.SortDto sortDto) {
        return new SearchData.Sort(
                sortDto.getSortBy(),
                sortDto.getOrderBy()
        );
    }

    private static List<SearchDataDto.SortDto> toDto(List<SearchData.Sort> sort) {
        return sort.stream().map((sortEl -> toDto(sortEl))).collect(Collectors.toList());
    }
    private static List<SearchData.Sort> toObject(List<SearchDataDto.SortDto> sortDto) {
        return sortDto.stream().map((dto -> toObject(dto))).collect(Collectors.toList());
    }

    public static SearchData toObject(SearchDataDto searchDataDto) {
        return new SearchData(
                searchDataDto.getTagName(),
                searchDataDto.getPartialCertificateName(),
                searchDataDto.getPartialCertificateDescription(),
                searchDataDto.getSortData() == null ? null : toObject(searchDataDto.getSortData())
        );
    }

    public static SearchDataDto toDto(SearchData searchData) {
        return new SearchDataDto(
                searchData.getTagName(),
                searchData.getPartialCertificateName(),
                searchData.getPartialCertificateDescription(),
                toDto(searchData.getSortData())
        );
    }

}
