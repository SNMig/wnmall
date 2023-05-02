package com.woniuxy.mall.comons;

import com.woniuxy.mall.entiy.Goods;
import com.woniuxy.mall.entiy.Menu;
import com.woniuxy.mall.pojo.GoodsData;
import com.woniuxy.mall.vo.MenuVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GoodsMapper {
    List<GoodsData> map(List<Goods> goods);
}
