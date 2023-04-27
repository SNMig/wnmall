package com.woniuxy.mall.web;

import cn.hutool.json.JSON;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.util.ListUtils;
import com.woniuxy.mall.pojo.GoodsData;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
@Slf4j
@RestController
@RequestMapping("/api")
public class ImpexpController {

    public void simpleRead() {
        String fileName ="E:\\goods.xlsx";
        EasyExcel.read(fileName, GoodsData.class, new PageReadListener<GoodsData>(goodslist -> {
            for (GoodsData goodsData : goodslist) {
                log.info("读取到一条数据{}", JSON.toJSONString(goodsData));
            }
        })).sheet().doRead();

    }
}
