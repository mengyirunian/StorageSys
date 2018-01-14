package com.mengyirunian.service.impl;

import com.google.common.collect.Lists;
import com.mengyirunian.service.interfaces.RedisService;
import com.mengyirunian.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jiaxiayuan on 2018/1/14
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    @Override
    public boolean set(final String key, final String value) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
            return true;
        });
    }

    public String get(final String key) {
        return redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] value = connection.get(serializer.serialize(key));
            return serializer.deserialize(value);
        });
    }

    @Override
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public <T> boolean setList(String key, List<T> list) {
        try {
            String value = JSONUtils.list2json(list);
            return set(key, value);
        } catch (Exception e) {
            log.error("list 转 json 失败>>", e);
        }
        return false;
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clz) {
        try {
            String json = get(key);
            if (json != null) {
                return JSONUtils.json2list(json, clz);
            }
        } catch (Exception e) {
            log.error("json 转 list 失败>>", e);
        }
        return Lists.newArrayList();
    }

    @Override
    public long lpush(final String key, Object obj) {
        try {
            final String value = JSONUtils.obj2json(obj);
            return redisTemplate.execute((RedisCallback<Long>) connection -> {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return connection.lPush(serializer.serialize(key), serializer.serialize(value));
            });
        } catch (Exception e) {
            log.error("redis lpush 失败");
            throw new RuntimeException(e);
        }
    }

    @Override
    public long rpush(final String key, Object obj) {
        try {
            final String value = JSONUtils.obj2json(obj);
            return redisTemplate.execute((RedisCallback<Long>) connection -> {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return connection.rPush(serializer.serialize(key), serializer.serialize(value));
            });
        } catch (Exception e) {
            log.error("redis lpush 失败");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String lpop(final String key) {
        return redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] res = connection.lPop(serializer.serialize(key));
            return serializer.deserialize(res);
        });
    }

}
