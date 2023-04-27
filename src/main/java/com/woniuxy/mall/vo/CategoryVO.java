package com.woniuxy.mall.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CategoryVO {
    private Integer id;
    private String name;
    private List<CategoryVO> subCategorys =new ArrayList<>();
}
