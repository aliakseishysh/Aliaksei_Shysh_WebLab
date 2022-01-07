package com.epam.esm.service;

import com.epam.esm.service.dto.tag.*;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;

import java.util.List;

public interface TagService {

    List<TagDto> findAll();

    List<TagDto> findByName(ReadTagByNameDto readTagByNameDto);

    List<TagCostDto> read(ReadMostWidelyUsedTagDto readMostWidelyUsedTagDto);

    long save(CreateTagDto createTagDto) throws EntityAlreadyExistsServiceException;

    void deleteById(DeleteTagByIdDto deleteTagByIdDto);

    void deleteByName(DeleteTagByNameDto deleteTagByNameDto);

}
