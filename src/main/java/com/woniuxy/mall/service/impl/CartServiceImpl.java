package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.mall.entiy.Cart;
import com.woniuxy.mall.entiy.Goods;
import com.woniuxy.mall.mapper.CartMapper;
import com.woniuxy.mall.mapper.GoodsMapper;
import com.woniuxy.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public void add(Cart cart) {
        int id=cart.getGoods().getId();
        Goods goods=goodsMapper.selectById(id);
        cart.setAddPrice(goods.getSalesPrice());
        //cart.setAddPrice(goodsMapper.selectById(cart.getGoods().getId()).getSalesPrice());
        cart.setSelected("y");
        cartMapper.insert(cart);
    }
}
