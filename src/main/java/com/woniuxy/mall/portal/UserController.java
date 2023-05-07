package com.woniuxy.mall.portal;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.woniuxy.mall.commons.MailMsg;
import com.woniuxy.mall.entiy.User;
import com.woniuxy.mall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping ("/login")
    public String login(String account, String password, Model model, HttpSession session) {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",account);
        User user=userService.getOne(queryWrapper);
        if (user==null){
            model.addAttribute("msg","账号不存在");

        } else if (user.getStatus().equals("n")) {
            model.addAttribute("msg","未激活");

        }else if (!password.equals(user.getPassword())){
            model.addAttribute("msg","密码错误");

        }else {
            session.setAttribute("user",user);

        }
        return "redirect:/";

    }


    @PostMapping("/register")
    public String register(User user) {
        //yepzkejtozjlbfee
        log.debug("注册用户信息{}", user);
        user.setAvatar("1");
        user.setIsDel("n");
        user.setRegtime(LocalDateTime.now());
        user.setScore(0);
        user.setStatus("n");
        userService.save(user);
        MailMsg mailMsg = new MailMsg();
        mailMsg.setId(1);
        mailMsg.setEmail(user.getEmail());
        amqpTemplate.convertAndSend("mail_exchange", "mail", mailMsg);
        return "login";
    }

    @GetMapping("/active")
    public String active(int id,String token){
        User user=new User();
        user.setId(id);
        user.setStatus("y");
        userService.updateById(user);
        return "login";
    }
}
