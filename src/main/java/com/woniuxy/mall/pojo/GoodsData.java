package com.woniuxy.mall.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsData {
    @ExcelProperty("序号")
    private Integer id;
    @ExcelProperty("s商品名")
    private String name;
    @ExcelProperty("类别")
    private String categoryName;
    @ExcelProperty("市场价")
    private BigDecimal marketPrice;
    @ExcelProperty("蜗牛价")
    private BigDecimal salesPrice;
    @ExcelProperty("描述")
    private String description;
    @ExcelProperty("库存")
    private Integer stock;


}
