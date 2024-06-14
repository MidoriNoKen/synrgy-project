package com.taufik.challenge6.Services.Impl;

import com.taufik.challenge6.Exceptions.OrderDetailNotFoundException;
import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailCreateRequestDto;
import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailDto;
import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailUpdateRequestDto;
import com.taufik.challenge6.Models.Entities.*;
import com.taufik.challenge6.Repositories.OrderDetailRepository;
import com.taufik.challenge6.Services.ProductService;
import com.taufik.challenge6.Services.OrderDetailService;
import com.taufik.challenge6.Services.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public OrderDetailDto create(OrderDetailCreateRequestDto orderDetailCreateRequestDto) {
        OrderDetail orderDetail = modelMapper.map(orderDetailCreateRequestDto, OrderDetail.class);

        orderDetail.setOrder(orderService.getById(orderDetailCreateRequestDto.getOrderId()));
        orderDetail.setProduct(productService.getById(orderDetailCreateRequestDto.getProductId()));

        Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findByOrderAndProductAndSize(
                orderDetail.getOrder(), orderDetail.getProduct(), orderDetail.getSize());

        if (existingOrderDetail.isPresent()) {
            OrderDetail updatedOrderDetail = existingOrderDetail.get();
            updatedOrderDetail.addQty(orderDetail.getQty());
            updatedOrderDetail.setPrice(
                    updatedOrderDetail.getQty() * orderDetail.getProduct().getSizePrice(orderDetail.getSize()));

            updatedOrderDetail = orderDetailRepository.save(updatedOrderDetail);
            return modelMapper.map(updatedOrderDetail, OrderDetailDto.class);
        }

        orderDetail.setPrice(orderDetail.getQty() * orderDetail.getProduct().getSizePrice(orderDetail.getSize()));

        orderDetail = orderDetailRepository.save(orderDetail);
        return modelMapper.map(orderDetail, OrderDetailDto.class);
    }

    @Override
    public OrderDetail getById(UUID id) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id);
        if (optionalOrderDetail.isPresent()) {
            return optionalOrderDetail.get();
        }

        log.error("Order detail not found: {}", (id));
        throw new OrderDetailNotFoundException("Order detail not found: " + id);
    }

    @Override
    public OrderDetailDto getDtoById(UUID id) {
        return modelMapper.map(getById(id), OrderDetailDto.class);
    }

    @Override
    public List<OrderDetailDto> getListByOrder(Order order) {
        return orderDetailRepository.findByOrder(order).stream()
                .map(orderDetail -> modelMapper.map(orderDetail, OrderDetailDto.class))
                .toList();
    }

    @Override
    public List<OrderDetailDto> getListByProduct(Product product) {
        return orderDetailRepository.findByProduct(product).stream()
                .map(orderDetail -> modelMapper.map(orderDetail, OrderDetailDto.class))
                .toList();
    }

    @Override
    public OrderDetailDto update(UUID id, OrderDetailUpdateRequestDto orderDetailUpdateRequestDto) {
        OrderDetail existingOrderDetail = getById(id);

        String size = orderDetailUpdateRequestDto.getSize();
        int qty = orderDetailUpdateRequestDto.getQty();

        if (existingOrderDetail.getSize().equals(size)) {
            existingOrderDetail.setQty(qty);
            existingOrderDetail.setPrice(qty * existingOrderDetail.getProduct().getSizePrice(size));

            OrderDetail updatedOrderDetail = orderDetailRepository.save(existingOrderDetail);
            return modelMapper.map(updatedOrderDetail, OrderDetailDto.class);
        }

        OrderDetailCreateRequestDto newOrderDetail = new OrderDetailCreateRequestDto();
        newOrderDetail.setSize(size);
        newOrderDetail.setQty(qty);
        newOrderDetail.setOrderId(existingOrderDetail.getOrder().getId());
        newOrderDetail.setProductId(existingOrderDetail.getProduct().getId());

        orderDetailRepository.delete(existingOrderDetail);
        return create(newOrderDetail);
    }

    @Override
    public void safeDeleteById(UUID id) {
        OrderDetail orderDetail = getById(id);
        orderDetail.setDeleted(true);
        orderDetailRepository.save(orderDetail);
        log.info("Order detail {} has been deleted", id);
    }
}
