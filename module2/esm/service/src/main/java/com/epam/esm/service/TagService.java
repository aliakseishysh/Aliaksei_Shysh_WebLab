package com.epam.esm.service;

import com.epam.esm.database.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findTags();
    List<Tag> read(Tag tag);
    long createTag(Tag tag);
    boolean deleteTag(long id);
}
