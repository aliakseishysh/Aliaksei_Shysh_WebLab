package com.epam.esm.service;

import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.dto.tag.DeleteTagByIdDto;
import com.epam.esm.service.dto.tag.DeleteTagByNameDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

import java.util.List;

public interface TagService {

    List<TagDto> read();

    List<TagDto> read(TagDto tag) throws EntityIsNotValidServiceException;

    long createTag(CreateTagDto createTagDto) throws EntityAlreadyExistsServiceException, EntityIsNotValidServiceException;

    boolean deleteTag(DeleteTagByIdDto deleteTagByIdDto) throws EntityIsNotValidServiceException;

    boolean deleteTag(DeleteTagByNameDto deleteTagByNameDto) throws EntityIsNotValidServiceException;

}
