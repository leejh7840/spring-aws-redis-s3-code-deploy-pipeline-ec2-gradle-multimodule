package com.nexient.orders.data.repository;

import com.nexient.orders.data.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import com.nexient.orders.data.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository implements IOrderRepository {

    public static final String HASH_KEY = "Order";

    @Autowired
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Order> redisTemplate;

    private HashOperations<String, String, Order> hashOperations;

    public OrderRepository(RedisTemplate<String, Order> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Order save(Order order) {
        hashOperations.put(HASH_KEY, order.getId(), order);
        return order;
    }

    @Override
    public Order findById(String id) {
        return (Order) hashOperations.get(HASH_KEY, id);
    }

    @Override
    public Order update(Order order) {
        return save(order);
    }

    @Override
    public List<Order> findAll() {
        Map<String, Order> map = hashOperations.entries(HASH_KEY);
        List<Order> result = map.values().stream().collect(Collectors.toList());
        return result;
    }

    @Override
    public String delete(String id) {
        hashOperations.delete(HASH_KEY, id);
        return "Order Deleted";
    }

    @Override
    public String saveAll(Map<String, Order> map) {
        hashOperations.putAll(HASH_KEY, map);
        return "saved all Order successfully";
    }

    @Override
    public long getCount() {
        Map<String, Order> list = hashOperations.entries(HASH_KEY);
        return list.size();
    }
}
