package com.woniuxy.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.woniuxy.mall.entiy.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    @Select("SELECT m.* from mall_menu m,mall_role_menu rm,mall_admin_role ra,mall_admin a\n" +
            "        where m.id=rm.menu_id and rm.role_id=ra.role_id and ra.admin_id=a.role_id and a.id=#{UserId}")
    List<Menu>getMenuByUserId(int UserId);

}
