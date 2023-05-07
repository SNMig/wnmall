package com.woniuxy.mall.entiy;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Orderdetails {
    private Integer id;
    private Integer orderNo;
    private Integer goodsId;
    private String name;
    private BigDecimal price;
    private Integer nums;
}
