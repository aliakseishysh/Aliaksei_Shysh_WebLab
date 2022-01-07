package com.epam.esm.database.dao;

import com.epam.esm.database.entity.Tag;
import com.epam.esm.database.entity.TagCost;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagDao extends JpaRepository<Tag, Long> {
    List<Tag> findByName(String name);

    List<Tag> deleteByName(String name);
}
