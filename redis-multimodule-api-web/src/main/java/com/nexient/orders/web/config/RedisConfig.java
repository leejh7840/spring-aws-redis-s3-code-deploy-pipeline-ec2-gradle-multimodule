package com.nexient.orders.web.config;
import com.nexient.orders.data.entity.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@Configuration
@EnableRedisRepositories
public class RedisConfig {

     //Creating Connection with Redis
     @Bean
     public RedisConnectionFactory redisConnectionFactory() {
         return new LettuceConnectionFactory();
     }

     //Creating RedisTemplate for Entity 'Orders'
     @Bean
     public RedisTemplate<String, Order> redisTemplate(){
         RedisTemplate<String, Order> empTemplate = new RedisTemplate<>();
         empTemplate.setConnectionFactory(redisConnectionFactory());
         return empTemplate;
     }
}
