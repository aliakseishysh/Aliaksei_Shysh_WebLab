package com.epam.esm.service.util;

import com.epam.esm.database.entity.Order;
import com.epam.esm.database.entity.Tag;
import com.epam.esm.service.dto.order.CreateOrderDto;
import com.epam.esm.service.dto.order.OrderDto;
import com.epam.esm.service.dto.tag.TagDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toObject(CreateOrderDto createOrderDto) {
        return new Order(
                null,
                createOrderDto.getUserId(),
                createOrderDto.getCertificateId(),
                createOrderDto.getPrice(),
                createOrderDto.getPurchaseDate()
        );
    }

    public static OrderDto toDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getUserId(),
                order.getCertificateId(),
                order.getPrice(),
                order.getPurchaseDate()
        );
    }

    public static List<OrderDto> toDto(List<Order> tags) {
        return tags.stream().map((OrderMapper::toDto)).collect(Collectors.toList());
    }

}
