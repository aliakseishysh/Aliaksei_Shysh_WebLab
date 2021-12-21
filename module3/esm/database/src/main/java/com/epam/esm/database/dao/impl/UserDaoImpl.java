package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.dao.UserDao;
import com.epam.esm.database.dao.extractor.UserPublicExtractor;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.database.entity.User;
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
public class UserDaoImpl implements UserDao {

    private static final String CREATE_USER = "INSERT INTO users(username, password) VALUES (?, ?)";
    private static final String READ_USER_PUBLIC = "SELECT id, username FROM users WHERE username = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long create(String username, String password) throws EntityAlreadyExistsDaoException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(CREATE_USER, new String[]{"id"});
                statement.setString(1, username);
                statement.setString(2, password);
                return statement;
            }, keyHolder);
            return keyHolder.getKey().longValue();
        } catch (DuplicateKeyException e) {
            throw new EntityAlreadyExistsDaoException("Entity User with name=" + username + " already exists.");
        }
    }

    @Override
    public List<User> read(String name) {
        return jdbcTemplate.query(READ_USER_PUBLIC, new BeanPropertyRowMapper(User.class), name);
    }

}
