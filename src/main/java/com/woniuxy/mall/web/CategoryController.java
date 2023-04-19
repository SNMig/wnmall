package com.woniuxy.mall.web;

import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.woniuxy.mall.entiy.Category;
import com.woniuxy.mall.service.CategoryService;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @PostMapping("/add")
    public ResponseResult<Void>add(@RequestBody Category category){
        return null;
    }
}
