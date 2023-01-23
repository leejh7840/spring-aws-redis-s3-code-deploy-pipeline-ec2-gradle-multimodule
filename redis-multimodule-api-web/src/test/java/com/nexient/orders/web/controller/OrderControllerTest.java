package com.nexient.orders.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nexient.orders.data.entity.Order;
import com.nexient.orders.service.OrderService;
import com.nexient.orders.web.controller.OrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {

  private OrderService orderService;

  private OrderController orderController;

  @Autowired
  private MockMvc mockMvc;

  private static ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    orderService = mock(OrderService.class);
    orderController = new OrderController(orderService);
    mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

    objectMapper = new ObjectMapper();
    objectMapper.registerModules(new JavaTimeModule());
  }

  @Test
  public void getAllOrderTest() throws Exception {
    List<Order> list = Stream.of(new Order("100","Food Item1", 100, 1),
            new Order("200","Food Item2", 200, 2)).collect(Collectors.toList());

    when(orderService.getAllOrders()).thenReturn(list);
    mockMvc.perform(get("/orders"))
            .andExpect(status().isOk());
  }

  @Test
  public void getOrderByIdTest() throws Exception {
    Order order = Order.builder().id("100").item("Food").qty(1).price(100).build();

    when(orderService.getOrderById(anyString())).thenReturn(order);
    mockMvc.perform(get("/orders/100"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    verify(orderService, times(1)).getOrderById("100");
  }

  @Test
  public void saveOrderTest() throws Exception {
    Order order = Order.builder().id("300").item("Food").qty(1).price(200).build();

    when(orderService.saveOrders(any(Order.class))).thenReturn(order);

    mockMvc.perform(post("/orders")
                    .content(objectMapper.writeValueAsBytes(order))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200));

    verify(orderService, times(1)).saveOrders(any(Order.class));
  }
  
}
