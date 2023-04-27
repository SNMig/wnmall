package com.woniuxy.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.mall.entiy.Perm;

import java.util.List;

public interface PermService extends IService<Perm> {
    List<Perm> getPermByUserId(int id);
}
