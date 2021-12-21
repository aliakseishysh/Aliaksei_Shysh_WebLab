package com.epam.esm.service.impl;

import com.epam.esm.database.dao.OrderDao;
import com.epam.esm.database.dao.UserDao;
import com.epam.esm.database.exception.EntityAlreadyExistsDaoException;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import com.epam.esm.service.dto.order.CreateOrderDto;
import com.epam.esm.service.dto.order.OrderDto;
import com.epam.esm.service.dto.order.SearchOrderByIdDto;
import com.epam.esm.service.dto.order.SearchOrderByUsernameDto;
import com.epam.esm.service.dto.user.CreateUserDto;
import com.epam.esm.service.dto.user.PublicUserDto;
import com.epam.esm.service.dto.user.ReadPublicUserByNameDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;
import com.epam.esm.service.util.OrderMapper;
import com.epam.esm.service.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public Long create(CreateOrderDto createOrderDto) {
        return orderDao.create(
                createOrderDto.getUserId(),
                createOrderDto.getCertificateId(),
                createOrderDto.getPrice(),
                createOrderDto.getPurchaseDate()
        );
    }

    @Override
    public List<OrderDto> readById(SearchOrderByIdDto searchOrderByIdDto) {
        return OrderMapper.toDto(orderDao.read(searchOrderByIdDto.getOrderId()));
    }

    @Override
    public List<OrderDto> readByName(SearchOrderByUsernameDto searchOrderByUsernameDto) {
        return OrderMapper.toDto(orderDao.read(searchOrderByUsernameDto.getUsername()));
    }
}
