package com.nexient.orders.service;

import com.nexient.orders.data.entity.Order;
import com.nexient.orders.data.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceTest  {

	@InjectMocks
	private OrderService service;
	private OrderRepository repository;
	private RedisTemplate<String, Order> redisTemplate;

	@BeforeEach
	void setUp() {
		repository = mock(OrderRepository.class);
		service = new OrderService(repository);
	}

	@Test
	public void getAllOrdersTest(){
		List<Order> list = Stream.of(new Order("1","Food Item1", 100, 1),
						new Order("2","Food Item2", 200, 2)).collect(Collectors.toList());

		when(repository.findAll()).thenReturn(list);

		when(service.getAllOrders()).thenReturn(list);

		assertEquals(2, list.size());
	}

	@Test
	public void getAllOrdersCountTest() {
		List<Order> list = Stream.of(new Order("100","Food Item1", 100, 1),
						new Order("200","Food Item2", 200, 2)).collect(Collectors.toList());

		when(repository.getCount()).thenReturn((long) list.size());

		when(service.getAllOrderCount()).thenReturn((long)list.size());
	}

	@Test
	public void saveOrderTest() {
		Order order = Order.builder().id("300").item("Food").qty(1).price(200).build();

		when(repository.save(order)).thenReturn(order);

		assertEquals(order, service.saveOrders(order));
	}

	@Test
	public void deleteUserTest() {
		when(repository.delete("200")).thenReturn("Deleted Successfully!!");

		service.deleteOrders("300");

		verify(repository, times(1)).delete("300");
	}

	@Test
	public void getOrderByIdTest(){
		String id = "100";
		Order order = Order.builder().id("100").item("Food").qty(1).price(100).build();

		when(repository.findById("100")).thenReturn(order);

		when(service.getOrderById(id)).thenReturn(order);

		assertEquals("100", order.getId());
		assertEquals(1, order.getQty());
	}
}
