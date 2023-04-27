package com.woniuxy.mall.web;


import com.woniuxy.mall.entiy.Category;
import com.woniuxy.mall.service.CategoryService;
import com.woniuxy.mall.vo.CategoryVO;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/add")
    public ResponseResult<Void>add(){
        return ResponseResult.ok();
    }
    @GetMapping("/list")
    public ResponseResult<List<CategoryVO>>getAll(){
        List<Category>categories=categoryService.list();
        List<CategoryVO>categoryVOS=new ArrayList<>();
        for (Category category : categories) {
            if (category.getPid()==null){
                CategoryVO categoryVO=new CategoryVO();
                BeanUtils.copyProperties(category,categoryVO);
                for (Category subCategory : categories) {
                    if (category.getId().equals(subCategory.getPid())){
                        CategoryVO subCategoryVO=new CategoryVO();
                        BeanUtils.copyProperties(subCategory,subCategoryVO);
                        subCategoryVO.setSubCategorys(null);
                        categoryVO.getSubCategorys().add(subCategoryVO);
                    }
                }
                if (categoryVO.getSubCategorys().size()==0){
                    categoryVO.setSubCategorys(null);
                }
                categoryVOS.add(categoryVO);
            }
        }
        return ResponseResult.ok(categoryVOS);
    }
}
