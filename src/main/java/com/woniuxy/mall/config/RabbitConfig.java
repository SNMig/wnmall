package com.woniuxy.mall.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    @Bean
    public FanoutExchange mailExchange(){
        return new FanoutExchange("mail_exchange");
    }
    @Bean
    public Queue mailQueue(){
        return new Queue("mail_queue");
    }
    @Bean
    public Binding mailBinding(){
        return BindingBuilder.bind(mailQueue()).to(mailExchange());
    }




    @Bean
    public FanoutExchange orderExchange(){
        return new FanoutExchange("order_exchange");
    }
    @Bean
    public Queue orderQueue(){
        Map<String,Object>map=new HashMap<>();
        map.put("x-message-ttl",1*60*1000);
        map.put("x-dead-letter-exchange","order_exchange2");
        map.put("x-dead-letter-routing-key","");
        return new Queue("order_queue_1",true,false,false,map);
    }
    @Bean
    public Binding orderBinding(){
        return BindingBuilder.bind(orderQueue()).to(orderExchange());
    }

    @Bean
    public FanoutExchange orderExchange2(){
        return new FanoutExchange("order_exchange2");
    }
    @Bean
    public Queue orderQueue2(){
        Map<String,Object>map=new HashMap<>();
        map.put("x-message-ttl",1*60*1000);
        return new Queue("order_queue_2",true,false,false,map);
    }
    @Bean
    public Binding orderBinding2(){
        return BindingBuilder.bind(orderQueue()).to(orderExchange());
    }
}
