package com.taufik.challenge5.Service;

import com.taufik.challenge5.Model.DTO.OrderDTO;
import com.taufik.challenge5.Model.DTO.OrderInvoiceDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> list();

    List<OrderInvoiceDTO> listOrdersByUserId(Long userId);

    OrderDTO show(Long id);

    void create(OrderDTO orderDTO);

    void update(Long id, OrderDTO orderDTO);

    void delete(Long id);
}
