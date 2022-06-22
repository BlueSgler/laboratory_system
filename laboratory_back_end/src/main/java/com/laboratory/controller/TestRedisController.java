package com.laboratory.controller;

import com.laboratory.util.redis.RedisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author echokilig
 * @date 2022/6/5 14:57
 */

@RestController
@RequestMapping("/testRedis")
public class TestRedisController {
    @Resource
    private RedisService redisService;

    @GetMapping("/get/{key}")
    public Object get(@PathVariable String key) {
        return redisService.get(key);
    }

    @PostMapping(value = "/set/{key}", produces = "application/json;charset=UTF-8")
    public Object set(@PathVariable("key") String key,
                      @RequestBody Object value) {
        redisService.set(key, value, 20);
        return "set success";
    }
}
