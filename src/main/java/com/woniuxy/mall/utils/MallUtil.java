package com.woniuxy.mall.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MallUtil {
    public static final String createOrderNo(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return "WN"+formatter.format(LocalDateTime.now())+"3223";
    }
}
