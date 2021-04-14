package top.crwenassert.favorites.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * ClassName: RedisUtil
 * Description:
 * date: 2020/8/15 23:03
 *
 * @author crwen
 * @create 2020-08-15-23:03
 * @since JDK 1.8
 */

public class RedisUtil {



    //https://www.jianshu.com/p/0fa4c100e9a9
    public static void setString(RedisTemplate redisTemplate, String key, String data) {
        redisTemplate.opsForValue().set(key, data);
    }

    /**
     *  将 map 数据放入缓存
     * @param redisTemplate
     * @param key
     * @param map
     */
    public static void setHash(RedisTemplate redisTemplate, String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     *  获取 key 下的所有 hashkey 和 value
     * @param redisTemplate
     * @param key
     * @return
     */
    public static Map<Object, Object> getHash(RedisTemplate redisTemplate, String key) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    /**
     *  验证指定的 key 下有没有指定的 hashKey
     * @param redisTemplate
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hashKey(RedisTemplate redisTemplate, String key, String hashKey) {
        Boolean res = redisTemplate.opsForHash().hasKey(key, hashKey);
        return res;
    }

    /**
     *  获取指定 key下 的指定 hashKey 的值
     * @param redisTemplate
     * @param key
     * @param hashKey
     * @return
     */
    public Object getMapString(RedisTemplate redisTemplate, String key, String hashKey) {
        Object result = redisTemplate.opsForHash().get(key, hashKey);
        return result;
    }
}
