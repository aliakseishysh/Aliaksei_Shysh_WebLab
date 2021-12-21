package com.epam.esm.database.dao;

import com.epam.esm.database.entity.User;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;

import java.util.List;

public interface UserDao {
    Long create(String username, String password) throws EntityAlreadyExistsDaoException;

    List<User> read(String username);
}
