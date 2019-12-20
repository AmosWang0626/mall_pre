package com.mall.common.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map; 
import java.util.Set;

/**
 * PROJECT: base
 * DESCRIPTION: 由静态方法构成的Redis工具类
 *
 * @author amos.wang
 * @date 2018/8/30
 */
public class RedisUtils {

    private static JedisPool jedisPool;

    public static void setPool(JedisPool jedisPool) {
        RedisUtils.jedisPool = jedisPool;
    }

    private static JedisPool getPool() {
        return jedisPool;
    }

    /*
     * string
     */

    public static void set(String key, String value) {
        set(key, value, null, null);
    }

    public static void set(String key, String value, Integer second) {
        set(key, value, null, second);
    }

    public static void setByIndex(String key, String value, Integer index) {
        set(key, value, index, null);
    }

    public static void set(String key, String value, Integer index, Integer second) {
        Jedis jedis = getPool().getResource();
        jedis.set(key, value);

        if (index != null) {
            jedis.select(index);
        }

        // 过期时间
        if (second != null && second != 0) {
            jedis.expire(key, second);
        }

        jedis.close();
    }

    public static String get(String key) {
        return get(key, null);
    }

    public static String get(String key, Integer index) {
        Jedis jedis = getPool().getResource();

        if (index != null) {
            jedis.select(index);
        }

        String value = jedis.get(key);

        jedis.close();

        return value;
    }

    public static Long incr(String key) {
        return incr(key, null, null);
    }

    public static Long incr(String key, Integer second) {
        return incr(key, null, second);
    }

    public static Long incrByIndex(String key, Integer index) {
        return incr(key, index, null);
    }

    public static Long incr(String key, Integer index, Integer second) {
        Jedis jedis = getPool().getResource();

        if (index != null) {
            jedis.select(index);
        }

        Long count = jedis.incr(key);

        // 过期时间
        if (second != null && second != 0) {
            jedis.expire(key, second);
        }

        jedis.close();

        return count;
    }

    /*
     * key
     */

    public static void del(String key) {
        del(key, null);
    }

    public static void del(String key, Integer index) {
        Jedis jedis = getPool().getResource();
        if (index != null) {
            jedis.select(index);
        }

        jedis.del(key);

        jedis.close();
    }

    /*
     * list
     */

    /**
     * 最后插入的在列表第一个位置
     */
    public static Long lpush(String key, String... strings) {
        return lpush(key, null, strings);
    }

    /**
     * 最后插入的在列表第一个位置
     */
    public static Long lpush(String key, Integer index, String... strings) {
        Jedis jedis = getPool().getResource();
        if (index != null) {
            jedis.select(index);
        }

        Long count = jedis.lpush(key, strings);

        jedis.close();

        return count;
    }

    /**
     * 覆盖列表第一个元素
     */
    public static Long lpushx(String key, String... strings) {

        return lpushx(key, null, strings);
    }

    /**
     * 覆盖列表第一个元素
     */
    public static Long lpushx(String key, Integer index, String... strings) {
        Jedis jedis = getPool().getResource();
        if (index != null) {
            jedis.select(index);
        }

        Long count = jedis.lpushx(key, strings);

        jedis.close();

        return count;
    }

    public static List<String> lrange(String key, long start, long end) {
        return lrange(key, null, start, end);
    }

    public static List<String> lrange(String key, Integer index, long start, long end) {
        Jedis jedis = getPool().getResource();
        if (index != null) {
            jedis.select(index);
        }

        List<String> list = jedis.lrange(key, start, end);

        jedis.close();

        return list;
    }

    /*
     * hash
     */


    public static String hmset(String key, Map<String, String> map, Integer index) {
        Jedis jedis = getPool().getResource();
        jedis.select(index);

        String status = jedis.hmset(key, map);

        jedis.close();

        return status;
    }


    public static String hget(String key, String field, Integer index) {
        Jedis jedis = getPool().getResource();
        jedis.select(index);

        String value = jedis.hget(key, field);

        jedis.close();

        return value;
    }

    /*
     * set
     */

    public static Long sadd(String key, Integer index, String... strings) {
        Jedis jedis = getPool().getResource();
        jedis.select(index);

        Long count = jedis.sadd(key, strings);

        jedis.close();

        return count;
    }

    public static Set<String> smembers(String key, Integer index) {
        Jedis jedis = getPool().getResource();
        jedis.select(index);

        Set<String> members = jedis.smembers(key);

        jedis.close();

        return members;
    }

    /*
     * sort
     */

    public static Long zadd(String key, double score, String member, Integer index) {
        Jedis jedis = getPool().getResource();
        jedis.select(index);

        Long count = jedis.zadd(key, score, member);

        jedis.close();

        return count;
    }

    public static Long zadd(String key, Map<String, Double> scoreMembers, Integer index) {
        Jedis jedis = getPool().getResource();
        jedis.select(index);

        Long count = jedis.zadd(key, scoreMembers);

        jedis.close();

        return count;
    }

    public static Set<String> zrange(String key, Integer index, long start, long end) {
        Jedis jedis = getPool().getResource();
        jedis.select(index);

        Set<String> list = jedis.zrange(key, start, end);

        jedis.close();

        return list;
    }


}
