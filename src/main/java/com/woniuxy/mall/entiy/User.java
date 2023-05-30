package com.woniuxy.mall.entiy;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {
    private Integer id;
    private String account;
    private String password;
    private String email;
    private String mobile;
    private String avatar;
    private LocalDateTime regtime;
    private Integer score;
    private String status;
    private String isDel;
}
