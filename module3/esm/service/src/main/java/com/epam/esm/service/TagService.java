package com.epam.esm.service;

import com.epam.esm.service.dto.tag.TagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.dto.tag.DeleteTagByIdDto;
import com.epam.esm.service.dto.tag.DeleteTagByNameDto;
import com.epam.esm.service.dto.tag.ReadTagByNameDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

import java.util.List;

public interface TagService {

    List<TagDto> read();

    List<TagDto> read(ReadTagByNameDto readTagByNameDto);

    long createTag(CreateTagDto createTagDto) throws EntityAlreadyExistsServiceException;

    boolean deleteTag(DeleteTagByIdDto deleteTagByIdDto);

    boolean deleteTag(DeleteTagByNameDto deleteTagByNameDto);

}
