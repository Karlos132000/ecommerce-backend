package com.example.ecommerce.dto;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;

import java.util.Date;
import java.util.List;

public class OrderDTO extends Order {
    private Long id;
    private Date orderDate;
    private Double totalAmount;
    private List<OrderItem> orderItems; // List of order items

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }


    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;


    }
}
