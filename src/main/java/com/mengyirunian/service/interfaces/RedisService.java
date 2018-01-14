package com.mengyirunian.service.interfaces;

import java.util.List;

/**
 * Created by Jiaxiayuan on 2018/1/14
 */
public interface RedisService {

    boolean set(String key, String value);

    String get(String key);

    boolean expire(String key, long expire);

    <T> boolean setList(String key, List<T> list);

    <T> List<T> getList(String key, Class<T> clz);

    long lpush(String key, Object obj);

    long rpush(String key, Object obj);

    String lpop(String key);

}
