package com.woniuxy.mall.web;


import com.woniuxy.mall.entiy.Goods;
import com.woniuxy.mall.service.GoodsService;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @PostMapping("/add")
    public ResponseResult<Void>add(@RequestBody Goods goods){
        goods.setIsDel("y");
        goodsService.save(goods);
        return ResponseResult.ok();
    }

}
