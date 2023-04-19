package com.woniuxy.mall;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;

public class HutoolsTest {
    @Test
    public void test(){
        String str = "123456";
        String md5Hex1 = DigestUtil.md5Hex(str);
        System.out.println(md5Hex1);
    }
}
