package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class TagDaoImpl implements TagDao {

    private JdbcTemplate jdbcTemplate;
    private static final String CREATE_TAG = "INSERT INTO tags(name) VALUES (?)";
    private static final String READ_TAGS = "SELECT id, name FROM tags";

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(String name) {
        return jdbcTemplate.update(CREATE_TAG, name) == 1;
    }

    @Override
    public long create(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE_TAG, new String[] {"id"});
            statement.setString(1, tag.getName());
            return statement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Tag> read() {
        return jdbcTemplate.query(READ_TAGS, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Optional<Tag> read(long id) {
        return Optional.empty();
    }

    @Override
    public List<Tag> readByCertificateId(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
