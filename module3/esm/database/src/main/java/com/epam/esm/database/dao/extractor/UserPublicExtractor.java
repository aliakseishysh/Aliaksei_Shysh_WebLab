package com.epam.esm.database.dao.extractor;

import com.epam.esm.database.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPublicExtractor implements ResultSetExtractor<User> {
    // TODO mb throw exception if
    @Override
    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
        User user = new User();
        if (rs.next()) {
            user.setId(rs.getLong(1));
            user.setUsername(rs.getString(2));
        }
        return user;
    }
}
