package com.epam.esm.database.dao;

import com.epam.esm.database.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao {
    boolean create(String name);
    long create(Tag tag);
    List<Tag> read();
    Optional<Tag> read(long id);
    List<Tag> readByCertificateId(long id);
    boolean delete(long id);
}
