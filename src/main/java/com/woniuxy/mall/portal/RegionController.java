package com.woniuxy.mall.portal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mall.entiy.Region;
import com.woniuxy.mall.service.RegionService;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ucenter/region")
public class RegionController {
    @Autowired
    private RegionService regionService;
    @GetMapping("getbypid")
    public ResponseResult<List<Region>>getByPid(Integer pid){
        QueryWrapper<Region>queryWrapper=new QueryWrapper<>();
        if (pid!=null){
            queryWrapper.eq("pid",pid);
        }else {
            queryWrapper.isNull("pid");
        }

        List<Region>regions=regionService.list(queryWrapper);
        return new ResponseResult<>(regions);
    }
}
