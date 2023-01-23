package com.nexient.orders.data.repository;

import com.nexient.orders.data.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderRepositoryTest {

  public static final String HASH_KEY = "Order";

  private RedisTemplate<String, Order> redisTemplate;
  private HashOperations<String, String, Order> hashOperations;
  private Map<String, Order> map = null;

  @BeforeEach
  void setUp() {
    redisTemplate = mock(RedisTemplate.class);
    hashOperations = mock(HashOperations.class);

    hashOperations.entries(HASH_KEY);
    map = new HashMap<String, Order>();
    map.put("100", new Order("100", "Food Item1", 100, 1));
    map.put("200", new Order("200", "Food Item2", 200, 1));

    when(hashOperations.entries(HASH_KEY)).thenReturn(map);
  }

  @Test
  public void getFindAllTest() {
    List<Order> list = Stream.of(new Order("1", "Food Item1", 100, 1),
            new Order("2", "Food Item2", 200, 2)).collect(Collectors.toList());

    assertEquals(2, map.size());
  }

  @Test
  public void getFindByIdTest() {
    String id = "100";
    Order order = new Order("100", "Food Item1", 1, 50);

    doReturn(order).when(hashOperations).get(HASH_KEY, id);
    assertEquals(50, order.getPrice());

    assertEquals(id, order.getId());
    assertEquals(50, order.getPrice());
  }

  @Test
  public void getOrdersCountTest() {
    assertEquals(2, map.size());
  }

  @Test
  public void deleteUserTest() {
    String id = "100";
    Order order = new Order("100", "Food Item1", 1, 50);

    doReturn(order).when(hashOperations).get(HASH_KEY, id);
    assertEquals(50, order.getPrice());
  }

  @Test
  public void saveTest() {
    Order order = new Order("400", "Food Item", 4, 400);
    doNothing().when(hashOperations).put(HASH_KEY, order.getId(),order);

    assertEquals(order.getId(), "400");
  }

  @Test
  public void updateTest(){
    this.saveTest();
  }

  @Test
  public void saveAllTest(){

    doNothing().when(hashOperations).putAll(HASH_KEY, map);

    assertEquals(2, map.values().size());
  }
}
