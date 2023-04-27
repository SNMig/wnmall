package com.woniuxy.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.mall.entiy.Menu;
import com.woniuxy.mall.entiy.Perm;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermMapper extends BaseMapper<Perm> {
    @Select("SELECT * from v_perm where admin_id=#{UserId}")
    List<Perm>getPermByUserId(int UserId);

}
