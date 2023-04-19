package com.woniuxy.mall;

import com.woniuxy.mall.entiy.Category;
import com.woniuxy.mall.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CategoryMapperTest {
    @Autowired
    private CategoryMapper categoryMapper;
    @Test
    public void testAdd(){
        Category category=new Category();
        category.setName("大家电");
        category.setShow("y");
        category.setSort(1);
        category.setIsDel("n");
        category.setLastUpdateTime(LocalDateTime.now());
        categoryMapper.insert(category);
    }

}
