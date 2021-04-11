package com.jiangnan.qiuzhao.config;

import com.jiangnan.qiuzhao.utils.ApplicationContextutils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

//不是工厂管理的
public class RedisCache implements Cache {
    //放入缓冲的namespace
    private final String id;
    public RedisCache(String id){
        this.id = id;
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplat = (RedisTemplate) ApplicationContextutils.getBean("redisTemplate");
        redisTemplat.setKeySerializer(new StringRedisSerializer());
        redisTemplat.setHashKeySerializer(new StringRedisSerializer());
        redisTemplat.opsForHash().put(id.toString(),getMd5Key(key.toString()),value);

    }

    @Override
    public Object getObject(Object o) {
        RedisTemplate redisTemplat = (RedisTemplate) ApplicationContextutils.getBean("redisTemplate");
        redisTemplat.setKeySerializer(new StringRedisSerializer());
        redisTemplat.setHashKeySerializer(new StringRedisSerializer());
        Object o1 = redisTemplat.opsForHash().get(id.toString(), getMd5Key(o.toString()));
        return o1;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {
        //默认调用清空缓存
        RedisTemplate redisTemplat = (RedisTemplate) ApplicationContextutils.getBean("redisTemplate");
        redisTemplat.setKeySerializer(new StringRedisSerializer());
        redisTemplat.setHashKeySerializer(new StringRedisSerializer());
        redisTemplat.delete(id.toString());
    }

    @Override
    public int getSize() {
        RedisTemplate redisTemplat = (RedisTemplate) ApplicationContextutils.getBean("redisTemplate");
        redisTemplat.setKeySerializer(new StringRedisSerializer());
        redisTemplat.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplat.opsForHash().size(id.toString()).intValue();
    }
    private String getMd5Key(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
