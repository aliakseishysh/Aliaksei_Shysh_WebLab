package com.epam.esm.service.impl;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> findTags() {
        return tagDao.read();
    }

    @Override
    public List<Tag> read(Tag tag) {
        return tagDao.read(tag);
    }

    @Override
    public long createTag(Tag tag) {
        return tagDao.create(tag);
    }

    @Override
    public boolean deleteTag(long id) {
        return tagDao.delete(id);
    }
}
