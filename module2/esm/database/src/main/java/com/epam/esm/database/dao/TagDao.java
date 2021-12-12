package com.epam.esm.database.dao;

import com.epam.esm.database.entity.Tag;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;

import java.util.List;

public interface TagDao {

    long create(Tag tag) throws EntityAlreadyExistsDaoException;

    List<Tag> read();

    List<Tag> read(Tag tag);

    boolean delete(long id);

}
