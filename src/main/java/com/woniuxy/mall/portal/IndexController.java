package com.woniuxy.mall.portal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mall.entiy.Category;
import com.woniuxy.mall.entiy.Goods;
import com.woniuxy.mall.service.CategoryService;
import com.woniuxy.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping
    public String index(Model model){
        List<Category>categories=(List<Category>) redisTemplate.opsForValue().get("categories");
        if (categories==null){
            categories=categoryService.list();
            redisTemplate.opsForValue().set("categories",categories);
        }
        model.addAttribute("categories",categoryService.list());
        QueryWrapper<Goods>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("newest","y");
        model.addAttribute("newestGoods",goodsService.list(queryWrapper));
        return "index";
    }
}
