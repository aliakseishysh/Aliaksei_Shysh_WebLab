package com.epam.esm.database.dao;

import com.epam.esm.database.entity.Tag;

import java.util.List;

public interface TagDao {
    long create(Tag tag);
    List<Tag> read();
    boolean delete(long id);
}
