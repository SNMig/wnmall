package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.mall.entiy.Admin;
import com.woniuxy.mall.mapper.AdminMapper;
import com.woniuxy.mall.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
