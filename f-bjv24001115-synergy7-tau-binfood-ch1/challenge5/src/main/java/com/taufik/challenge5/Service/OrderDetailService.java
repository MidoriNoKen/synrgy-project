package com.taufik.challenge5.Service;

import java.util.List;
import com.taufik.challenge5.Model.DTO.OrderDetailDTO;

public interface OrderDetailService {

    List<OrderDetailDTO> list();

    OrderDetailDTO show(Long id);

    void create(OrderDetailDTO orderDetailDTO);

    void update(Long id, OrderDetailDTO orderDetailDTO);

    void delete(Long id);
}
