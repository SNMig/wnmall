package com.woniuxy.mall.service.impl;

import cn.hutool.json.JSON;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.woniuxy.mall.vo.GoodsVO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class FileDataListener implements ReadListener<GoodsVO> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<GoodsVO> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);




    @Override
    public void invoke(GoodsVO goodsVO, AnalysisContext analysisContext) {


        cachedDataList.add(goodsVO);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
// 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }
}
