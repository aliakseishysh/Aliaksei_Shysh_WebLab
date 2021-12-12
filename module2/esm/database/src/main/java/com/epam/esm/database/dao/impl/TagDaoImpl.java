package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {

    private static final String CREATE_TAG = "INSERT INTO tags(name) VALUES (?)";
    private static final String READ_TAGS = "SELECT id, name FROM tags";
    private static final String READ_TAG = "SELECT id, name FROM tags WHERE id = ? OR name = ?";
    private static final String DELETE_TAG = "DELETE FROM tags WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(Tag tag) throws EntityAlreadyExistsDaoException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(CREATE_TAG, new String[]{"id"});
                statement.setString(1, tag.getName());
                return statement;
            }, keyHolder);
            return keyHolder.getKey().longValue();
        } catch (DuplicateKeyException e) {
            throw new EntityAlreadyExistsDaoException("Entity Tag with name=" + tag.getName() + " already exists.");
        }
    }

    @Override
    public List<Tag> read() {
        return jdbcTemplate.query(READ_TAGS, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public List<Tag> read(Tag tag) {
        return jdbcTemplate.query(READ_TAG, new BeanPropertyRowMapper<>(Tag.class), tag.getId(), tag.getName());
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE_TAG, id) == 1;
    }
}
