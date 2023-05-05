package com.woniuxy.mall.entiy;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class Cart {
    private Integer id;
    private Integer userId;
    @TableField(value = "goods_id",property = "goods.id")
    private Goods goods;
    private Integer nums;
    private LocalDateTime addTime;
    private BigDecimal addPrice;
    private String selected;
}
