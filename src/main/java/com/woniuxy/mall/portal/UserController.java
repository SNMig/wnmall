package com.woniuxy.mall.portal;

import com.woniuxy.mall.entiy.User;
import com.woniuxy.mall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
@Slf4j
public class UserController {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/register")
    public String register(User user){
        //yepzkejtozjlbfee
        //log.debug("注册用户信息{}",user);
        //userService.save(user);
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("1048321255@qq.com");
        message.setTo(user.getEmail());
        message.setSubject("激活");
        message.setText("激活连接");
        javaMailSender.send(message);
        return "login";
    }
}
