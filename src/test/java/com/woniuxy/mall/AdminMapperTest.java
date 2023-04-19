package com.woniuxy.mall;

import cn.hutool.crypto.digest.DigestUtil;
import com.woniuxy.mall.entiy.Admin;
import com.woniuxy.mall.mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AdminMapperTest {
    @Autowired
    private AdminMapper adminMapper;
    @Test
    public void testAdd(){
        Admin admin=new Admin();
        admin.setAccount("admin");
        admin.setPassword("123456");
        admin.setRoleId(1);
        admin.setIsDel("y");
        admin.setStatus("y");
        admin.setLastLoginTime(LocalDateTime.now());
        admin.setLastLoginIp("192.168.1.1");
        adminMapper.insert(admin);
    }

}
