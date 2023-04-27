package com.woniuxy.mall.comons;

import com.woniuxy.mall.entiy.Menu;
import com.woniuxy.mall.vo.MenuVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    List<MenuVO> map(List<Menu> menus);
}
