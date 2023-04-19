package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.mall.entiy.Menu;
import com.woniuxy.mall.mapper.MenuMapper;
import com.woniuxy.mall.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>implements MenuService  {
}
