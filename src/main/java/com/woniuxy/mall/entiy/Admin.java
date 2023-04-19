package com.woniuxy.mall.entiy;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Admin {
    private Integer id;
    private String account;
    private String password;
    private Integer roleId;
    private String status;
    private String isDel;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
}
