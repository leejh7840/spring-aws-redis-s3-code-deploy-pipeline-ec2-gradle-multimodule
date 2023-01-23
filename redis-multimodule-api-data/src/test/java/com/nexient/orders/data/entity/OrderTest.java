package com.nexient.orders.data.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexient.orders.data.utility.UnitTestResourceLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ObjectMapper mapper = new ObjectMapper();
	UnitTestResourceLoader load = null;

	public OrderTest(){
		load = new UnitTestResourceLoader();
	}

	private Order order;

	@BeforeEach
	void setUp(){
		order = new Order();
		order.setId("N100");
		order.setItem("Cookies");
		order.setQty(1);
	}

	@Test
	public void verifyGetOrderResponse() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = load.getString("classpath:/order.json");
		System.out.println("Json:" + json);
		Order response = mapper.readValue(json, Order.class);

		assertEquals(response.getId(), response.getId());
		assertEquals(response.getPrice(), response.getPrice());
		assertEquals(response.getQty(), response.getQty());
	}

	@Test
	void getId() {
		Order newOrder = Order.builder().id(order.getId())
						.item(order.getItem())
						.price(order.getPrice())
						.qty(order.getQty()).build();

		assertEquals(order.getItem(), newOrder.getItem());
		assertEquals(order.getId(), newOrder.getId());
		assertEquals(order.getPrice(), newOrder.getPrice());
		assertEquals(order.getQty(), newOrder.getQty());
	}
}
