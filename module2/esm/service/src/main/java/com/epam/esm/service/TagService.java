package com.epam.esm.service;

import com.epam.esm.database.entity.Tag;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;

import java.util.List;

public interface TagService {
    List<TagDto> read();
    List<TagDto> read(TagDto tag) throws EntityIsNotValidServiceException;
    long createTag(TagDto tag) throws EntityAlreadyExistsServiceException, EntityIsNotValidServiceException;
    boolean deleteTag(TagDto tagDto) throws EntityIsNotValidServiceException;
}
