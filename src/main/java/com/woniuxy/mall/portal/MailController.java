package com.woniuxy.mall.portal;

import com.woniuxy.mall.entiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MailController {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @GetMapping("/sendmail")
    public void sendMail(User user){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("1048321255@qq.com");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("用户注册激活邮件");
        simpleMailMessage.setText("<a href='http://localhost:8080/active?id=1'></a>");
        mailSender.send(simpleMailMessage);
    }
}
