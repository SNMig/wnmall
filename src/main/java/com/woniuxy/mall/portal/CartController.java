package com.woniuxy.mall.portal;

import com.woniuxy.mall.entiy.Cart;
import com.woniuxy.mall.entiy.User;
import com.woniuxy.mall.service.CartService;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseResult<Void>add(@RequestBody Cart cart, HttpSession session){
        User user= (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        cart.setAddTime(LocalDateTime.now());
        cartService.add(cart);
        return ResponseResult.ok();
    }

    @GetMapping("view")
    public String view(){
        return "cart";
    }
}
