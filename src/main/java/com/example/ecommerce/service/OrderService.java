package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAllOrders();
    Order getOrderById(Long id);
    Order saveOrder(OrderDTO orderDTO);
    void deleteOrder(Long id);

    // Add this if you need to handle order items specifically
    OrderItem getOrderItemById(Long id);
}
