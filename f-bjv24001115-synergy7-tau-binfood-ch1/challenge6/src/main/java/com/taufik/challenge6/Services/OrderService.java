package com.taufik.challenge6.Services;

import java.util.List;
import java.util.UUID;

import com.taufik.challenge6.Models.Dtos.Order.OrderCompleteRequestDto;
import com.taufik.challenge6.Models.Dtos.Order.OrderCreateRequestDto;
import com.taufik.challenge6.Models.Dtos.Order.OrderDto;
import com.taufik.challenge6.Models.Entities.Order;
import com.taufik.challenge6.Models.Entities.Merchant;
import com.taufik.challenge6.Models.Entities.Auth.User;

public interface OrderService {
    // CREATE
    OrderDto create(OrderCreateRequestDto orderCreateRequestDto);

    // READ
    Order getById(UUID id);

    OrderDto getDtoById(UUID id);

    List<OrderDto> getList();

    List<OrderDto> getCompletedListByUser(User user);

    List<OrderDto> getCompletedListByMerchant(Merchant merchant);

    int getTotalPrice(UUID id);

    int getTotalQty(UUID id);

    // UPDATE
    OrderDto completeOrder(UUID id, OrderCompleteRequestDto orderCompleteRequestDto);

    // DELETE
    void safeDeleteById(UUID id);
}
