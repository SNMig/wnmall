package com.woniuxy.mall.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mall.entiy.Admin;
import com.woniuxy.mall.mallenum.ResponseCode;
import com.woniuxy.mall.service.AdminService;
import com.woniuxy.mall.vo.AdminVO;
import com.woniuxy.mall.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {
    @Resource
    private AdminService adminService;
    @Value("${JWT.secretKey}")
    private String secretKey;
    @PostMapping("/login")
    public ResponseResult<Void> login(@RequestBody AdminVO adminVO, HttpServletResponse response){
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<Admin>();
        queryWrapper.eq("account",adminVO.getAccount());
        Admin admin=adminService.getOne(queryWrapper);
        if (admin==null){
            return new ResponseResult(402,"账号不存在");
        }
        if (!admin.getPassword().equals(DigestUtil.md5Hex(adminVO.getPassword()))){
            return new ResponseResult<>(401,"密码错误");
        }
        //产生JWT，保存到响应头
        String token = JWT.create()
                .setPayload("id", String.valueOf(admin.getId()))
                .setPayload("account", admin.getAccount())
                .setKey(secretKey.getBytes())
                .sign();
        response.setHeader("Authorization", token);
        return ResponseResult.ok();
    }

    @GetMapping("/test")
    public ResponseResult<Void> test(@RequestHeader String authorization){

        log.debug("authorization:{}",authorization);
        JWT jwt=JWT.of(authorization);
        Object id=jwt.getPayload("id");
        log.debug("{}",id);
        // 密钥
        byte[] key = secretKey.getBytes();
// 默认验证HS265的算法
        JWT.of(authorization).setKey(key).verify();

        return ResponseResult.ok();

    }







}
