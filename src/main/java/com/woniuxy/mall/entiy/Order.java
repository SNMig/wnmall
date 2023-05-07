package com.woniuxy.mall.entiy;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    private Integer id;
    private String no;
    private LocalDateTime orderTime;
    private Integer userId;
    private BigDecimal amount;
    private String address;
    private String name;
    private String mobile;
    private String status;
}
