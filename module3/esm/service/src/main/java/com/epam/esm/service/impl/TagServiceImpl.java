package com.epam.esm.service.impl;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.tag.*;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.util.TagMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<TagDto> findAll() {
        return TagMapper.toDto(tagDao.findAll());
    }

    @Override
    public List<TagDto> findByName(ReadTagByNameDto readTagByNameDto) {
        return TagMapper.toDto(tagDao.findByName(readTagByNameDto.getName()));
    }

    // TODO rewrite with hibernate
    @Override
    public List<TagCostDto> read(ReadMostWidelyUsedTagDto readMostWidelyUsedTagDto) {
        throw new UnsupportedOperationException("Not supported!!!");
        //return TagMapper.toDtoTagCost(tagDao.readMostWidelyUsed(readMostWidelyUsedTagDto.getUsername()));
    }


    @Override
    public long save(CreateTagDto tagDto) {
        return tagDao.save(TagMapper.toObject(tagDto)).getId();
    }

    @Override
    public void deleteById(DeleteTagByIdDto deleteTagByIdDto) {
       tagDao.deleteById(deleteTagByIdDto.getId());
    }

    @Transactional
    @Override
    public void deleteByName(DeleteTagByNameDto deleteTagByNameDto) {
        tagDao.deleteByName(deleteTagByNameDto.getName());
    }
}
