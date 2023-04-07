package br.ucsal.discordadapterapi.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService<K, V> {
	
	@Autowired
    private RedisTemplate<K, V> redisTemplate;

    public void setValue(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }
    
    public void setValueIfAbset(K key, V value, long expiration) {
        redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofMillis(expiration));
    }

    public V getValue(K key) {
        return redisTemplate.opsForValue().get(key);
    }

}
