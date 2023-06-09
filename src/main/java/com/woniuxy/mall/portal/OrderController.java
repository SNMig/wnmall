package com.woniuxy.mall.portal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mall.entiy.*;
import com.woniuxy.mall.service.AddressService;
import com.woniuxy.mall.service.GoodsService;
import com.woniuxy.mall.service.OrderService;
import com.woniuxy.mall.utils.MallUtil;
import com.woniuxy.mall.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.lang.UUID;


@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private AddressService  addressService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/confirm")
    public String confirm(@RequestParam("id")Integer goodsId, Integer num, Model model, HttpSession session){
        User user=(User)session.getAttribute("user");
        QueryWrapper<Address>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        List<Address> addressList=addressService.list(queryWrapper);
        model.addAttribute("addressList",addressList);
        Goods goods=goodsService.getById(goodsId);
        GoodsVO goodsVO=new GoodsVO();
        goodsVO.setGoods(goods);
        goodsVO.setNum(num);
        model.addAttribute("goodsVoList", Arrays.asList(goodsVO));
        String token=UUID.randomUUID().toString().replaceAll("-","");
        redisTemplate.opsForValue().set(user.getId(),token,7, TimeUnit.MINUTES);
        model.addAttribute("token",token);
        return "order_confirm";
    }
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public String add(Order order,String token, HttpServletRequest request,int[] goodsid,int[]nums,HttpSession session){
        User user = (User) session.getAttribute("user");
        String uuid=(String) redisTemplate.opsForValue().get(user.getId());
        if (token!=null){
            if (!token.equals(uuid)){
                return "info";
            }
            redisTemplate.delete(user.getId());
        }
        String[] goodsids=request.getParameterValues("goodsid");

        order.setNo(MallUtil.createOrderNo());
        order.setOrderTime(LocalDateTime.now());
        order.setUserId(user.getId());
        order.setStatus("y");

        List<Orderdetails>orderdetails=new ArrayList<>();
        for (int i = 0; i < goodsid.length; i++) {
            Orderdetails orderdetail=new Orderdetails();
            orderdetail.setGoodsId(goodsid[i]);
            orderdetail.setNums(nums[i]);
            orderdetails.add(orderdetail);
        }
        orderService.add(order,orderdetails);

        return "order_add";

    }
}
