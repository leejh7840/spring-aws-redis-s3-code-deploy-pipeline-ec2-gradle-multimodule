package com.nexient.orders.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nexient.orders.data.entity.Order;
import com.nexient.orders.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest2 {

  @MockBean
  private OrderService orderService;

  @Autowired
  private MockMvc mockMvc;

  private static ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
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
    mockMvc.perform(get("/orders/100")
                            .content(objectMapper.writeValueAsBytes(order))
                            .contentType(MediaType.APPLICATION_JSON) )
                    .andExpect(status().isOk());

    verify(orderService, times(1)).getOrderById("100");
  }

  @Test
  public void saveOrderTest() throws Exception {
    Order order = Order.builder().id("200").item("Food").qty(1).price(200).build();
    when(orderService.saveOrders(any(Order.class))).thenReturn(order);

    mockMvc.perform(post("/orders")
                    .content(objectMapper.writeValueAsBytes(order))
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200));

    verify(orderService, times(1)).saveOrders(order);
  }

  @Test
  public void deleteOrderTest() throws Exception {
    Order order = Order.builder().id("200").item("Food").qty(1).price(200).build();
    when(orderService.deleteOrders(anyString())).thenReturn("Order Deleted Successfully!!" +order.getId());

    mockMvc.perform(delete("/orders/100")
              .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk());

    verify(orderService, times(1)).deleteOrders("100");
  }

  @Test
  public void getAllOrderCountTest() throws Exception {
    List<Order> list = Stream.of(new Order("100","Food Item1", 100, 1),
            new Order("200","Food Item2", 200, 2)).collect(Collectors.toList());

    when(orderService.getAllOrderCount()).thenReturn((long) list.size());

    mockMvc.perform(get("/orders/count")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    verify(orderService, times(1)).getAllOrderCount();
  }

}
