package com.woniuxy.mall.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsVO {
    private Integer id;
    private String name;
    private String image;
    private Integer categoryId;
    private BigDecimal marketPrice;
    private BigDecimal salesPrice;
    private String description;
    private Integer stock;
    private String hotest;
    private String newest;
    private String status;
    private String isDel;
}
