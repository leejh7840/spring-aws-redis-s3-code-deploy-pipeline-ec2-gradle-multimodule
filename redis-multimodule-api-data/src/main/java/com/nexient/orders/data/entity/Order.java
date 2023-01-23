package com.nexient.orders.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Builder
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Order")
public class Order implements Serializable {

    private String id;
    private String item;
    private Integer qty;
    private Integer price;


    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id;}

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
