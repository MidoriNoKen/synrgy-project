package com.taufik.challenge6.Services.Impl;

import com.taufik.challenge6.Exceptions.OrderNotFoundException;
import com.taufik.challenge6.Exceptions.UsernameExistedException;
import com.taufik.challenge6.Models.Dtos.Order.OrderCompleteRequestDto;
import com.taufik.challenge6.Models.Dtos.Order.OrderCreateRequestDto;
import com.taufik.challenge6.Models.Dtos.Order.OrderDto;
import com.taufik.challenge6.Models.Entities.*;
import com.taufik.challenge6.Models.Entities.Auth.User;
import com.taufik.challenge6.Repositories.OrderRepository;
import com.taufik.challenge6.Services.OrderService;
import com.taufik.challenge6.Services.MerchantService;
import com.taufik.challenge6.Services.UserService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserService userService;
    @Autowired
    MerchantService merchantService;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public OrderDto create(OrderCreateRequestDto orderCreateRequestDto) {
        Order order = new Order();
        order.setUser(userService.getById(orderCreateRequestDto.getUserId()));
        order.setMerchant(merchantService.getById(orderCreateRequestDto.getMerchantId()));

        order.setUser(userService.getById(orderCreateRequestDto.getUserId()));
        order.setMerchant(merchantService.getById(orderCreateRequestDto.getMerchantId()));

        order = orderRepository.save(order);
        log.info("Order {} successfully added", order.getId());

        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public Order getById(UUID id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        }

        log.error("Order not found: {}", (id));
        throw new OrderNotFoundException("Order not found: " + id);
    }

    @Override
    public OrderDto getDtoById(UUID id) {
        return modelMapper.map(getById(id), OrderDto.class);
    }

    @Override
    public List<OrderDto> getList() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    @Override
    public List<OrderDto> getCompletedListByUser(User user) {
        return orderRepository.findByUserAndCompleted(user, true).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    @Override
    public List<OrderDto> getCompletedListByMerchant(Merchant merchant) {
        return orderRepository.findByMerchantAndCompleted(merchant, true).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    @Override
    public int getTotalPrice(UUID id) {
        return orderRepository.getTotalPrice(id);
    }

    public int getTotalQty(UUID id) {
        return orderRepository.getTotalQty(id);
    }

    @Override
    public OrderDto completeOrder(UUID id, OrderCompleteRequestDto orderCompleteRequestDto) {
        Order existingOrder = getById(id);

        if (existingOrder.isCompleted()) {
            log.error("Order has been completed: {}", existingOrder.getId());
            throw new UsernameExistedException("Order has been completed: " + existingOrder.getId());
        }

        existingOrder.setDestinationAddress(orderCompleteRequestDto.getDestinationAddress());
        existingOrder.setNotes(orderCompleteRequestDto.getNotes());
        existingOrder.setOrderTime(LocalDateTime.now());
        existingOrder.setCompleted(true);

        Order updatedOrder = orderRepository.save(existingOrder);
        log.info("Order {} has been completed", updatedOrder.getId());
        return modelMapper.map(updatedOrder, OrderDto.class);
    }

    @Override
    public void safeDeleteById(UUID id) {
        Order order = getById(id);
        order.setDeleted(true);
        orderRepository.save(order);
        log.info("Order {} has been deleted", id);
    }
}
