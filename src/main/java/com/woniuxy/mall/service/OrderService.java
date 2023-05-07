package com.woniuxy.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.mall.entiy.Order;
import com.woniuxy.mall.entiy.Orderdetails;

import java.util.List;


public interface OrderService extends IService<Order> {
    void add(Order order, List<Orderdetails> orderdetails);
}
