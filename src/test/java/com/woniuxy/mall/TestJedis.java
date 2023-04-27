package com.woniuxy.mall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

public class TestJedis {
    @Test
    public void testJedis(){
        Jedis jedis=new Jedis("192.168.11.191",6379);
        jedis.set("test1","测试1");
        jedis.close();
    }
}
