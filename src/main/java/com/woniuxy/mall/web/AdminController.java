package com.woniuxy.mall.web;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mall.entiy.Admin;
import com.woniuxy.mall.mallenum.ResponseCode;
import com.woniuxy.mall.service.AdminService;
import com.woniuxy.mall.vo.AdminVO;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @PostMapping("/login")
    public ResponseResult<Void> login(@RequestBody AdminVO adminVO){
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<Admin>();
        queryWrapper.eq("account",adminVO.getAccount());
        Admin admin=adminService.getOne(queryWrapper);
        if (admin==null){
            return new ResponseResult(402,"账号不存在");
        }
        if (!admin.getPassword().equals(DigestUtil.md5Hex(adminVO.getPassword()))){
            return new ResponseResult<>(401,"密码错误");
        }
        return ResponseResult.ok();
    }

}
