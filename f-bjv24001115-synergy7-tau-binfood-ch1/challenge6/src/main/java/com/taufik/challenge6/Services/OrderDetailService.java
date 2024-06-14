package com.taufik.challenge6.Services;

import java.util.List;
import java.util.UUID;

import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailCreateRequestDto;
import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailDto;
import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailUpdateRequestDto;
import com.taufik.challenge6.Models.Entities.Product;
import com.taufik.challenge6.Models.Entities.Order;
import com.taufik.challenge6.Models.Entities.OrderDetail;

public interface OrderDetailService {
    // CREATE
    OrderDetailDto create(OrderDetailCreateRequestDto orderDetailCreateRequestDto);

    // READ
    OrderDetail getById(UUID id);

    OrderDetailDto getDtoById(UUID id);

    List<OrderDetailDto> getListByOrder(Order order);

    List<OrderDetailDto> getListByProduct(Product product);

    // UPDATE
    OrderDetailDto update(UUID id, OrderDetailUpdateRequestDto orderDetailUpdateRequestDto);

    // DELETE
    void safeDeleteById(UUID id);
}
