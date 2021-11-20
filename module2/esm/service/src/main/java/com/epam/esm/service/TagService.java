package com.epam.esm.service;

import com.epam.esm.database.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findTags();
    boolean createTag(String name);
    long createTag(Tag tag);
}
