package com.woniuxy.mall.entiy;

import lombok.Data;

@Data
public class Address {
    private Integer id;
    private Integer userId;
    private String accept;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String address;
    private String defaultAddress;
}
