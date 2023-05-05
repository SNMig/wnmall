package com.woniuxy.mall.portal;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mall.entiy.Goods;
import com.woniuxy.mall.service.GoodsService;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller("portalgoodscontroller")
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @GetMapping("/view")
    public String show(Model model,int id){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Goods goods=goodsService.getOne(queryWrapper);
        model.addAttribute("goods",goods);
        return "goods_view";
    }


}
