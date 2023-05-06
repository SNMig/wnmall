package com.woniuxy.mall.portal;

import com.woniuxy.mall.entiy.Address;
import com.woniuxy.mall.entiy.User;
import com.woniuxy.mall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/ucenter")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping("/address")
    public String add(){
        return "address_list";
    }
    @PostMapping("/address")
    @ResponseBody
    public String add(@RequestBody Address address, HttpSession session){
        User user= (User) session.getAttribute("user");
        address.setUserId(user.getId());
        address.setDefaultAddress("y");
        addressService.save(address);
        return "address_list";

    }
}
