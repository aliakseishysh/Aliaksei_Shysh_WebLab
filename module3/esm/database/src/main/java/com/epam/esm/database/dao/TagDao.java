package com.epam.esm.database.dao;

import com.epam.esm.database.entity.Tag;
import com.epam.esm.database.entity.TagCost;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;

import java.util.List;

public interface TagDao {

    long create(Tag tag) throws EntityAlreadyExistsDaoException;

    List<Tag> read();

    List<Tag> read(String name);

    boolean delete(long id);

    boolean delete(String name);

    List<TagCost> readMostWidelyUsed(String username);
}
