package com.epam.esm.service.impl;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.dto.tag.CreateTagDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.exception.EntityIsNotValidServiceException;
import com.epam.esm.service.util.TagMapper;
import com.epam.esm.service.validation.TagDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<TagDto> read() {
        return TagMapper.toDto(tagDao.read());
    }

    @Override
    public List<TagDto> read(TagDto tagDto) throws EntityIsNotValidServiceException {
        TagDtoValidator.validate(tagDto.getName());
        return TagMapper.toDto(tagDao.read(TagMapper.toObject(tagDto)));
    }

    @Override
    public long createTag(CreateTagDto tagDto) throws EntityAlreadyExistsServiceException {
        try {
            //TagDtoValidator.validate(tagDto.getName());
            return tagDao.create(TagMapper.toObject(tagDto));
        } catch (EntityAlreadyExistsDaoException e) {
            throw new EntityAlreadyExistsServiceException(e);
        }
    }

    @Override
    public boolean deleteTag(TagDto tagDto) throws EntityIsNotValidServiceException {
        TagDtoValidator.validate(tagDto.getId());
        return tagDao.delete(TagMapper.toObject(tagDto).getId());
    }
}
