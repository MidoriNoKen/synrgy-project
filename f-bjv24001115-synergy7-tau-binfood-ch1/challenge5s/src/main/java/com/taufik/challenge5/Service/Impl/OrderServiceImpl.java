package com.taufik.challenge5.Service.Impl;

import com.taufik.challenge5.Model.DTO.OrderDTO;
import com.taufik.challenge5.Model.Entity.Order;
import com.taufik.challenge5.Repository.OrderRepository;
import com.taufik.challenge5.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDTO> list() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO show(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDTO(order);
    }

    @Override
    public void create(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUser(orderDTO.getUser());
        order.setDate(orderDTO.getDate());
        order.setOrderDetail(orderDTO.getOrderDetail());
        orderRepository.save(order);
    }

    @Override
    public void update(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setUser(orderDTO.getUser());
        order.setDate(orderDTO.getDate());
        order.setOrderDetail(orderDTO.getOrderDetail());
        orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUser(order.getUser());
        dto.setDate(order.getDate());
        dto.setOrderDetail(order.getOrderDetail());
        return dto;
    }
}
