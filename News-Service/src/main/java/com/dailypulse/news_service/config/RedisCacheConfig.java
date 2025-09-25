package com.dailypulse.news_service.config;

import org.springframework.boot.autoconfigure.data.redis.RedisConnectionDetails;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/*
Configuration class to setup redis based cache configuration.
 */
@Configuration
public class RedisCacheConfig {

    /*
    Configures and create Redis cache manager bean.
    This manager will handle all caching operations using redis as caching backend.

     */
    @Bean
    public CacheManager getCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)) //Default TTL of 10 mins
                .disableCachingNullValues() //Do not cache null values
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); //Use json serializer

        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(redisCacheConfig).build();
    }
}
