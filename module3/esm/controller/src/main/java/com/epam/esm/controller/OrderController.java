package com.epam.esm.controller;

import com.epam.esm.controller.exception.WrongParametersControllerException;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.dto.order.CreateOrderDto;
import com.epam.esm.service.dto.order.OrderDto;
import com.epam.esm.service.dto.order.SearchOrderByIdDto;
import com.epam.esm.service.dto.order.SearchOrderByUsernameDto;
import com.epam.esm.service.dto.order.SearchOrdersDto;
import com.epam.esm.service.util.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Creates new tag with specified parameters
     *
     * @param createOrderDto dto object for order entity
     * @return {@long} id of created object
     */
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createOrder(@RequestBody @Valid CreateOrderDto createOrderDto) {
        Long result = orderService.create(createOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // TODO restrict to private (user/admin)

    /**
     * Reads all orders from database for username
     *
     * @param searchOrdersDto dto object for username
     * @return {@code List<OrderDto>} with tags from database
     */
    @GetMapping(path = "/search")
    public ResponseEntity<Object> readOrders(@RequestBody @Valid SearchOrdersDto searchOrdersDto,
                                                     @RequestParam(name = "byOrderId", required = false, defaultValue = "false") Boolean byOrderId,
                                                     @RequestParam(name = "byUsername", required = false, defaultValue = "false") Boolean byUsername) throws WrongParametersControllerException {
        if (byOrderId && !byUsername) {
            SearchOrderByIdDto searchOrderByIdDto = new SearchOrderByIdDto(searchOrdersDto.getOrderId());
            DtoValidator.validate(searchOrderByIdDto);
            List<OrderDto> orders = orderService.readById(searchOrderByIdDto);
            return !orders.isEmpty() ? ResponseEntity.ok(orders.get(0)) : ResponseEntity.noContent().build();
        } else if (byUsername && !byOrderId) {
            SearchOrderByUsernameDto searchOrderByUsernameDto = new SearchOrderByUsernameDto(searchOrdersDto.getUsername());
            DtoValidator.validate(searchOrderByUsernameDto);
            List<OrderDto> orders = orderService.readByName(searchOrderByUsernameDto);
            return !orders.isEmpty() ? ResponseEntity.ok(orders) : ResponseEntity.noContent().build();
        }
        throw new WrongParametersControllerException();
    }

}
