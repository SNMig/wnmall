package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.mall.entiy.Category;
import com.woniuxy.mall.mapper.CategoryMapper;
import com.woniuxy.mall.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {
}
