package com.woniuxy.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.mall.entiy.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<Menu> getMenusByUserId(int id);
}
