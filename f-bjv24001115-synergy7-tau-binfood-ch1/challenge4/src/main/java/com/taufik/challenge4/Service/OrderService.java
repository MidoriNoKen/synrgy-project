package com.taufik.challenge4.Service;

import com.taufik.challenge4.Model.DTO.OrderDTO;

public interface OrderService {

    OrderDTO readOrder(Long orderId);

    void createOrder(OrderDTO orderDTO);

    void updateOrder(Long orderId, OrderDTO orderDTO);

    void deleteOrder(Long orderId);
}
