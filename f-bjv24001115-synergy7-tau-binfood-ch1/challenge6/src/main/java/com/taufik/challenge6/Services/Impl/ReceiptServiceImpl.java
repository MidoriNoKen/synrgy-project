package com.taufik.challenge6.Services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taufik.challenge6.Models.Dtos.Order.OrderReceiptDto;
import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailDto;
import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailReportDto;
import com.taufik.challenge6.Models.Entities.Order;
import com.taufik.challenge6.Services.ProductService;
import com.taufik.challenge6.Services.OrderDetailService;
import com.taufik.challenge6.Services.OrderService;
import com.taufik.challenge6.Services.ReceiptService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    ProductService productService;

    @Override
    public OrderReceiptDto getOrderReceiptDto(UUID orderId) {
        Order order = orderService.getById(orderId);
        OrderReceiptDto orderReceiptDto = new OrderReceiptDto();

        orderReceiptDto.setOrderId(orderId);
        orderReceiptDto.setOrderTime(order.getOrderTime());
        orderReceiptDto.setMerchantName(order.getMerchant().getName());
        orderReceiptDto.setDestinationAddress(order.getDestinationAddress());

        List<OrderDetailReportDto> orderDetailReportDtoList = new ArrayList<>();

        for (OrderDetailDto orderDetailDto : orderDetailService.getListByOrder(order)) {
            OrderDetailReportDto orderDetailReportDto = modelMapper.map(orderDetailDto, OrderDetailReportDto.class);

            orderDetailReportDto.setProductName(productService.getById(orderDetailDto.getProductId()).getName());

            orderDetailReportDtoList.add(orderDetailReportDto);
        }

        orderReceiptDto.setOrderDetailReportDtoList(orderDetailReportDtoList);
        orderReceiptDto.setTotalPrice(orderService.getTotalPrice(orderId));
        orderReceiptDto.setTotalQty(orderService.getTotalQty(orderId));

        return orderReceiptDto;
    }
}
