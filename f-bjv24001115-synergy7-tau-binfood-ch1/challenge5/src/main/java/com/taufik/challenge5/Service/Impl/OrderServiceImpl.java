package com.taufik.challenge5.Service.Impl;

import com.taufik.challenge5.Model.DTO.OrderDTO;
import com.taufik.challenge5.Model.DTO.OrderInvoiceDTO;
import com.taufik.challenge5.Model.Entity.Order;
import com.taufik.challenge5.Repository.OrderRepository;
import com.taufik.challenge5.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<OrderInvoiceDTO> listOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this::convertToInvoiceDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO show(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDTO(order);
    }

    @Override
    @Transactional
    public void create(OrderDTO orderDTO) {
        Order order = new Order();
        order.setDate(order.getDate());
        order.setAddress(order.getAddress());
        order.setUser(order.getUser());
        order.setMerchant(order.getMerchant());
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void update(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setDate(order.getDate());
        order.setAddress(order.getAddress());
        order.setUser(order.getUser());
        order.setMerchant(order.getMerchant());
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setDate(order.getDate());
        dto.setAddress(order.getAddress());
        dto.setUser(order.getUser());
        dto.setMerchant(order.getMerchant());
        return dto;
    }

    private OrderInvoiceDTO convertToInvoiceDTO(Order order) {
        OrderInvoiceDTO dto = new OrderInvoiceDTO();
        dto.setCompleted(order.isCompleted());
        dto.setPrice(order.getPrice());
        dto.setQuantity(order.getQuantity());
        dto.setDate(order.getDate());
        dto.setAddress(order.getAddress());
        dto.setUsername(order.getUser().getUsername());
        dto.setEmail(order.getUser().getEmail());
        return dto;
    }
}
