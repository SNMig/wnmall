package com.woniuxy.mall.entiy;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@TableName("mall_category")
public class Category implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String name;
    private String show;
    private  Integer sort;
    private LocalDateTime lastUpdateTime;
    private String isDel;
    private Integer pid;
}
