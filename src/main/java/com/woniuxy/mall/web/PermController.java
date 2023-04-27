package com.woniuxy.mall.web;

import cn.hutool.jwt.JWT;
import com.woniuxy.mall.comons.MenuMapper;
import com.woniuxy.mall.entiy.Menu;
import com.woniuxy.mall.entiy.Perm;
import com.woniuxy.mall.mallenum.ResponseCode;
import com.woniuxy.mall.service.MenuService;
import com.woniuxy.mall.service.PermService;
import com.woniuxy.mall.vo.MenuVO;
import com.woniuxy.mall.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/perm")
public class PermController {
    @Autowired
    private PermService permService;

    @GetMapping("/list")
    public ResponseResult<List<Perm>>list(@RequestHeader String authorization){
        log.debug("authorization:{}",authorization);
        JWT jwt=JWT.of(authorization);
        Object id=jwt.getPayload("id");
        log.debug("{}",id);
        List<Perm>perms=permService.getPermByUserId(Integer.parseInt((String) id));
        return new ResponseResult<>(ResponseCode.SUCCESS,perms);
    }
}
