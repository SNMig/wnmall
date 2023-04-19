package com.woniuxy.mall.entiy;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Menu {
    private Integer id;
    private String name;
    private String link;
    private Integer pid;
    private List<Menu> subMenus=new ArrayList<>();
}
