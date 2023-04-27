package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.woniuxy.mall.entiy.Perm;

import com.woniuxy.mall.mapper.PermMapper;
import com.woniuxy.mall.service.PermService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm>implements PermService {
    @Override
    public List<Perm> getPermByUserId(int id) {
        return getBaseMapper().getPermByUserId(id);
    }
}
