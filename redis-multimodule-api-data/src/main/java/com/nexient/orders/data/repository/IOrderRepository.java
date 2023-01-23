package com.nexient.orders.data.repository;

import com.nexient.orders.data.entity.Order;

import java.util.List;

public interface IOrderRepository {
    Order findById(String id);

    List<Order> findAll();

    String delete(String id);

    Order save(Order order);

    Order update(Order order);

    String saveAll(java.util.Map<String, Order> map);

    long getCount();
}
