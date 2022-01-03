package com.epam.esm.service.impl;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.tag.*;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.util.TagMapper;
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
    public List<TagDto> read(ReadTagByNameDto readTagByNameDto) {
        return TagMapper.toDto(tagDao.read(readTagByNameDto.getName()));
    }

    @Override
    public List<TagCostDto> read(ReadMostWidelyUsedTagDto readMostWidelyUsedTagDto) {
        return TagMapper.toDtoTagCost(tagDao.readMostWidelyUsed(readMostWidelyUsedTagDto.getUsername()));
    }

    @Override
    public long createTag(CreateTagDto tagDto) throws EntityAlreadyExistsServiceException {
        try {
            return tagDao.create(TagMapper.toObject(tagDto));
        } catch (EntityAlreadyExistsDaoException e) {
            throw new EntityAlreadyExistsServiceException(e);
        }
    }

    @Override
    public boolean deleteTag(DeleteTagByIdDto deleteTagByIdDto) {
        return tagDao.delete(deleteTagByIdDto.getId());
    }
    @Override
    public boolean deleteTag(DeleteTagByNameDto deleteTagByNameDto) {
        return tagDao.delete(deleteTagByNameDto.getName());
    }
}
