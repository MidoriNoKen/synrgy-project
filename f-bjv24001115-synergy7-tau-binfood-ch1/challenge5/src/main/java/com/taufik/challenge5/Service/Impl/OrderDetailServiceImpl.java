package com.taufik.challenge5.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taufik.challenge5.Model.DTO.OrderDetailDTO;
import com.taufik.challenge5.Model.Entity.OrderDetail;
import com.taufik.challenge5.Repository.OrderDetailRepository;
import com.taufik.challenge5.Service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

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
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(orderDetailDTO.getOrder());
        orderDetail.setProduct(orderDetailDTO.getProduct());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setTotalPrice(orderDetailDTO.getTotalPrice());
        orderDetailRepository.save(orderDetail);
    }

    @Override
    @Transactional
    public void update(Long id, OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Detail not found"));
        orderDetail.setOrder(orderDetailDTO.getOrder());
        orderDetail.setProduct(orderDetailDTO.getProduct());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setTotalPrice(orderDetailDTO.getTotalPrice());
        orderDetailRepository.save(orderDetail);
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
