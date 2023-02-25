package com.example.auth.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

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

    public void storeToken(String token,String userName){
        redisTemplate.opsForValue().set(userName,token);
    }

}
