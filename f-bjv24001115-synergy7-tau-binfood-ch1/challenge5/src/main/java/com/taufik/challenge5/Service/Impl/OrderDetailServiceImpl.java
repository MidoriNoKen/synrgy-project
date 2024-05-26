package com.taufik.challenge5.Service.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taufik.challenge5.Model.DTO.OrderDetailDTO;
import com.taufik.challenge5.Model.Entity.Order;
import com.taufik.challenge5.Model.Entity.OrderDetail;
import com.taufik.challenge5.Repository.OrderDetailRepository;
import com.taufik.challenge5.Repository.OrderRepository;
import com.taufik.challenge5.Service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetailDTO> list() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO show(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Detail not found"));
        return convertToDTO(orderDetail);
    }

    @Override
    @Transactional
    public void create(OrderDetailDTO orderDetailDTO) {
        Order order = orderRepository.findById(orderDetailDTO.getOrder().getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        BigDecimal quantity = new BigDecimal(orderDetailDTO.getQuantity());
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(orderDetailDTO.getOrder());
        orderDetail.setProduct(orderDetailDTO.getProduct());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setTotalPrice(orderDetailDTO.getProduct().getPrice().multiply(quantity));
        orderDetailRepository.save(orderDetail);

        order.setQuantity(order.getQuantity() + orderDetailDTO.getQuantity());
        order.setPrice(order.getPrice().add(orderDetailDTO.getTotalPrice()));
    }

    @Override
    @Transactional
    public void update(Long id, OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Detail not found"));
        Order order = orderRepository.findById(orderDetail.getOrder().getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setQuantity(order.getQuantity() - orderDetail.getQuantity());
        order.setPrice(order.getPrice().subtract(orderDetail.getTotalPrice()));

        BigDecimal quantity = new BigDecimal(orderDetailDTO.getQuantity());
        orderDetail.setOrder(orderDetailDTO.getOrder());
        orderDetail.setProduct(orderDetailDTO.getProduct());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setTotalPrice(orderDetailDTO.getProduct().getPrice().multiply(quantity));
        orderDetailRepository.save(orderDetail);

        order.setQuantity(order.getQuantity() + orderDetailDTO.getQuantity());
        order.setPrice(order.getPrice().add(orderDetailDTO.getTotalPrice()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }

    private OrderDetailDTO convertToDTO(OrderDetail orderDetail) {
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setId(orderDetail.getId());
        dto.setOrder(orderDetail.getOrder());
        dto.setProduct(orderDetail.getProduct());
        dto.setQuantity(orderDetail.getQuantity());
        dto.setTotalPrice(orderDetail.getTotalPrice());
        return dto;
    }
}
