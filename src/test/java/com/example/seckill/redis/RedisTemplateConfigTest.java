package com.example.seckill.redis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTemplateConfigTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisForString() {
        redisTemplate.boundValueOps("2 redisTest").set("2Test");
    }

    @Test
    public void testRedisTestForList() {
        List list = new ArrayList<>();
        list.add(0, "测试");
        redisTemplate.boundHashOps("1 resdisTest").put("list", list);
    }
}