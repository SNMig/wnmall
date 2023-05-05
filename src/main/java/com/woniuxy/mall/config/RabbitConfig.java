package com.woniuxy.mall.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
