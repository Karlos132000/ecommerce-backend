package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderItemDTO;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.service.OrderServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderServiceimpl orderServiceimpl;

    @GetMapping
    public List<OrderItemDTO> getAllOrderItems() {
        return orderServiceimpl.findAllOrderItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        OrderItem orderItem = orderServiceimpl.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem createdOrderItem = orderServiceimpl.saveOrderItem((OrderItemDTO) orderItem);
        return ResponseEntity.ok(createdOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderServiceimpl.deleteOrderItem(id);
        return ResponseEntity.ok().build();
    }
}
