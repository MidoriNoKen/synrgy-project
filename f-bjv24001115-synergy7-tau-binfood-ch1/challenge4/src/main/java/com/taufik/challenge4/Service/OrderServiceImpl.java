package com.taufik.challenge4.Service;

import com.taufik.challenge4.Model.DTO.OrderDTO;
import com.taufik.challenge4.Model.Entity.Order;
import com.taufik.challenge4.Model.Entity.User;
import com.taufik.challenge4.Repository.OrderRepository;
import com.taufik.challenge4.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderTime(orderDTO.getOrderTime());
        order.setDestinationAddress(orderDTO.getDestinationAddress());
        order.setCompleted(orderDTO.isCompleted());

        User user = userRepository.readUser(orderDTO.getUserId());
        order.setUser(user);

        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setOrderTime(orderDTO.getOrderTime());
        order.setDestinationAddress(orderDTO.getDestinationAddress());
        order.setCompleted(orderDTO.isCompleted());

        User user = userRepository.readUser(orderDTO.getUserId());
        order.setUser(user);

        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderDTO readOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return mapToDTO(order);
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderTime(order.getOrderTime());
        orderDTO.setDestinationAddress(order.getDestinationAddress());
        orderDTO.setCompleted(order.isCompleted());
        orderDTO.setUserId(order.getUser().getUserId());
        return orderDTO;
    }
}
