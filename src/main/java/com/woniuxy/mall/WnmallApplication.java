package com.woniuxy.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.woniuxy.mall.mapper")
@SpringBootApplication
public class WnmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(WnmallApplication.class, args);
    }

}
