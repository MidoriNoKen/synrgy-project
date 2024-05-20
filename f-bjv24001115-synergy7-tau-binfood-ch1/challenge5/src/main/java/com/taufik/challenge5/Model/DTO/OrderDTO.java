package com.taufik.challenge5.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.taufik.challenge5.Model.Entity.OrderDetail;
import com.taufik.challenge5.Model.Entity.User;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime date;
    private String address;
    private User user;
    private Boolean completed;
    private List<OrderDetail> orderDetails;
}
