package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.OrderDao;
import com.epam.esm.database.entity.Order;
import com.epam.esm.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final String CREATE_ORDER = "INSERT INTO orders(user_id, certificate_id, price, purchase_date) VALUES (?, ?, ?, ?)";
    private static final String READ_ORDER_BY_ID = "SELECT order_id, user_id, certificate_id, price, purchase_date FROM orders WHERE order_id = ?";
    private static final String READ_ORDERS_BY_USERNAME = "SELECT orders.order_id, orders.user_id, orders.certificate_id, orders.price, orders.purchase_date FROM orders INNER JOIN users ON "
            + "users.id = orders.user_id WHERE users.username = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long create(Long userId, Long certificateId, BigDecimal price, LocalDateTime purchaseDate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE_ORDER, new String[]{"order_id"});
            statement.setLong(1, userId);
            statement.setLong(2, certificateId);
            statement.setBigDecimal(3, price);
            statement.setTimestamp(4, Timestamp.valueOf(purchaseDate));
            return statement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Order> read(String name) {
        return jdbcTemplate.query(READ_ORDERS_BY_USERNAME, new BeanPropertyRowMapper(Order.class), name);
    }

    @Override
    public List<Order> read(Long orderId) {
        return jdbcTemplate.query(READ_ORDER_BY_ID, new BeanPropertyRowMapper(Order.class), orderId);
    }

}
