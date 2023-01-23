package com.nexient.orders.web.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexient.orders.data.entity.Order;
import com.nexient.orders.service.OrderService;

@AllArgsConstructor
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public Order save(@RequestBody Order order) {
        return orderService.saveOrders(order);
    }

    @PutMapping("/orders")
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrders(order);
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable("id") String orderId){
        return orderService.deleteOrders(orderId);
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable("id") String orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrder() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/count")
    public long getAllOrderCount() {
        return orderService.getAllOrderCount();
    }
}
