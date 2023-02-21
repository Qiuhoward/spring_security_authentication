package com.example.auth.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <token_utils>
 * 1.set token expire time
 */
@Component
public class TokenUtils {
    private final StringRedisTemplate redisTemplate;

    public TokenUtils(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setTokenExpireTime(String token,String userName){
        redisTemplate.opsForValue().set(userName,token,60*2,TimeUnit.SECONDS);
    }

}
