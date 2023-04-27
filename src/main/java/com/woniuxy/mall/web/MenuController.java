package com.woniuxy.mall.web;

import cn.hutool.jwt.JWT;
import com.woniuxy.mall.comons.MenuMapper;
import com.woniuxy.mall.entiy.Menu;
import com.woniuxy.mall.mallenum.ResponseCode;
import com.woniuxy.mall.service.MenuService;
import com.woniuxy.mall.vo.MenuVO;
import com.woniuxy.mall.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuMapper menuMapper;
    @GetMapping("/list")
    public ResponseResult<List<MenuVO>>list(@RequestHeader String authorization){
        log.debug("authorization:{}",authorization);
        JWT jwt=JWT.of(authorization);
        Object id=jwt.getPayload("id");
        log.debug("{}",id);
        List<Menu>menus=menuService.getMenusByUserId(Integer.parseInt((String) id));
        List<MenuVO> menuVO =menuMapper.map(menus);
        return new ResponseResult<>(ResponseCode.SUCCESS,menuVO);
    }
}
