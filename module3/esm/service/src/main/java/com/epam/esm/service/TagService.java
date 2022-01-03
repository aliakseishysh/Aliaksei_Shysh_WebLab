package com.epam.esm.service;

import com.epam.esm.service.dto.tag.*;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

import java.util.List;

public interface TagService {

    List<TagDto> read();

    List<TagDto> read(ReadTagByNameDto readTagByNameDto);

    List<TagCostDto> read(ReadMostWidelyUsedTagDto readMostWidelyUsedTagDto);

    long createTag(CreateTagDto createTagDto) throws EntityAlreadyExistsServiceException;

    boolean deleteTag(DeleteTagByIdDto deleteTagByIdDto);

    boolean deleteTag(DeleteTagByNameDto deleteTagByNameDto);

}
