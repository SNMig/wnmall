package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.woniuxy.mall.entiy.Goods;
import com.woniuxy.mall.entiy.Order;

import com.woniuxy.mall.entiy.Orderdetails;
import com.woniuxy.mall.mapper.GoodsMapper;
import com.woniuxy.mall.mapper.OrderMapper;

import com.woniuxy.mall.mapper.OrderdetailsMapper;
import com.woniuxy.mall.service.OrderService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderdetailsMapper orderdetailsMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Transactional
    @Override
    public void add(Order order, List<Orderdetails> orderdetails) {
        BigDecimal totalMoney = new BigDecimal("9.9");
        for (Orderdetails orderdetail : orderdetails) {
            BigDecimal price = goodsMapper.selectById(orderdetail.getGoodsId()).getSalesPrice();
            totalMoney = totalMoney.add(price.multiply(new BigDecimal(orderdetail.getNums())));
            Goods goods = goodsMapper.selectById(orderdetail.getGoodsId());
            order.setName(goods.getName());
        }
        order.setAmount(totalMoney);

        orderMapper.insert(order);
        for (Orderdetails orderdetail : orderdetails) {
            Goods goods = goodsMapper.selectById(orderdetail.getGoodsId());
            orderdetail.setOrderNo(order.getId());
            orderdetail.setNums(orderdetail.getNums());
            orderdetail.setName(goods.getName());
            orderdetail.setPrice(goods.getSalesPrice());
            orderdetailsMapper.insert(orderdetail);
        }

        amqpTemplate.convertAndSend("order_exchange", "order", order.getId());
    }
}
