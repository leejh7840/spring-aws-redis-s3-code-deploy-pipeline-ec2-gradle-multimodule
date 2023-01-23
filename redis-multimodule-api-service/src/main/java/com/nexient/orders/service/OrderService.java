package com.nexient.orders.service;

import com.nexient.orders.data.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import com.nexient.orders.data.repository.IOrderRepository;

@AllArgsConstructor
@EnableCaching
@Service
public class OrderService {

    public static final String HASH_KEY = "Order";

    @Autowired
    private IOrderRepository repository;

    @Cacheable(key = "#id" , value = HASH_KEY, unless = "#result.price > 2000") //price is less than 2000
    public Order getOrderById(String id) {
        //System.out.println("Called getOrderById from DB");
        return repository.findById(id);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public long getAllOrderCount() {
        return repository.getCount();
    }

    @CacheEvict(key = "#id" , value = HASH_KEY)
    public String deleteOrders(String id) {
        return repository.delete(id);
    }

    public Order saveOrders(Order order) {
        return repository.save(order);
    }

    public Order updateOrders(Order order) {
        return repository.update(order);
    }

    public String saveAllOrders(Map<String, Order> map) {
        return repository.saveAll(map);
    }
}
