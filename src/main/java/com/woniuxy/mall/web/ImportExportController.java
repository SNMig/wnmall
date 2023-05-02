package com.woniuxy.mall.web;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import com.alibaba.excel.util.ListUtils;
import com.woniuxy.mall.comons.GoodsMapper;
import com.woniuxy.mall.entiy.Goods;
import com.woniuxy.mall.pojo.GoodsData;

import com.woniuxy.mall.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/impexp")
public class ImportExportController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/import")
    public void simpleRead() {

        String fileName ="E:\\goods.xlsx";

        EasyExcel.read(fileName, GoodsData.class, new AnalysisEventListener<GoodsData>() {
            public static final int BATCH_COUNT = 100;
            /**
             *临时存储
             */
            private List<GoodsData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
            @Override
            public void invoke(GoodsData data, AnalysisContext context) {
                System.out.println(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println("完成");
            }
        }).sheet().doRead();

    }
    @Autowired
    private GoodsMapper goodsMapper;
    @RequestMapping("/export")
    public void exportGoods(HttpServletResponse response) throws IOException {

        List<Goods>goods=goodsService.list();
        List<GoodsData>goodsDatas=goodsMapper.map(goods);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("商品", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), GoodsData.class).sheet("模板").doWrite(goodsDatas);
    }
}
