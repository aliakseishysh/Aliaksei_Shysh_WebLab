package com.epam.esm.service;

import com.epam.esm.service.dto.order.CreateOrderDto;
import com.epam.esm.service.dto.order.OrderDto;
import com.epam.esm.service.dto.order.SearchOrderByIdDto;
import com.epam.esm.service.dto.order.SearchOrderByUsernameDto;
import com.epam.esm.service.dto.order.SearchOrdersDto;
import com.epam.esm.service.dto.user.CreateUserDto;
import com.epam.esm.service.dto.user.PublicUserDto;
import com.epam.esm.service.dto.user.ReadPublicUserByNameDto;
import com.epam.esm.service.exception.EntityAlreadyExistsServiceException;

import javax.validation.Valid;
import java.util.List;

public interface OrderService {

    Long create(CreateOrderDto createOrderDto);

    List<OrderDto> readById(SearchOrderByIdDto searchOrderByIdDto);

    List<OrderDto> readByName(SearchOrderByUsernameDto searchOrderByUsernameDto);
}
