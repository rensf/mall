package com.sys.common.core.script;

/**
 * Redis 脚本
 *
 * @author rensf
 * @date 2024/1/29
 */
public interface RedisScript {

    /**
     * 释放分布式锁
     */
    String RELEASE_LOCK = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

}
