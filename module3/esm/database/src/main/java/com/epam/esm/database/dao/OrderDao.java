package com.epam.esm.database.dao;

import com.epam.esm.database.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderDao {

    Long create(Long userId, Long certificateId, BigDecimal price, LocalDateTime purchaseDate);

    List<Order> read(String username);

    List<Order> read(Long orderId);

}
