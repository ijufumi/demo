package jp.ijufumi.sample.spring.cache.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    // if you save cache on memory.
    @Bean
    public CacheManager cacheManager()
    {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(Arrays.asList("default"));

        return cacheManager;
    }

    // if you save cache on redis server
    @Bean(name = "cacheManager")
    public CacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        Map<String, Long> expiresMap = new HashMap<>();
        expiresMap.put("default", 60L * 60);

        redisCacheManager.setExpires(expiresMap);
        return redisCacheManager;
    }

    @Bean
    @Profile({"product"})
    public RedisTemplate redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName("hostname"); // set your redis hostname
        connectionFactory.setPort(0); // set your redis port number
        connectionFactory.setUsePool(true);

        return connectionFactory;
    }
}
