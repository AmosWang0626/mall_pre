package com.mall.warehouse.core.service.impl;

import com.mall.warehouse.core.service.InventoryService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION: 库存业务实现
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/4/2
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    private final RedissonClient redissonClient;
    private final StringRedisTemplate redisTemplate;

    private static final String STORE = "STORE_POOL";

    private static final String LOCK_KEY = "LOCK_KEY";
    private static final long LOCK_EXPIRE_SECONDS = 10;

    public InventoryServiceImpl(StringRedisTemplate redisTemplate, RedissonClient redissonClient) {
        this.redisTemplate = redisTemplate;
        this.redissonClient = redissonClient;
    }

    @Override
    public String consume(String orderId) {
        RLock redissonLock = redissonClient.getLock(LOCK_KEY);
        try {
            /// 有原子性问题
            // Boolean absent = redisTemplate.opsForValue().setIfAbsent(LOCK_KEY, orderId);
            // redisTemplate.expire(LOCK_KEY, 10L, TimeUnit.SECONDS);

            /// 有锁过期，任务仍没有处理完成，下一个现场来了，则有问题
            /*Boolean absent = redisTemplate.opsForValue()
                    .setIfAbsent(LOCK_KEY, orderId, LOCK_EXPIRE_SECONDS, TimeUnit.SECONDS);
            if (!absent) {
                return "请稍后重试!";
            }*/

            // 假设设置锁时间为30秒，则会每隔 10秒(30 * 1/3) 去检查一遍是否还持有锁，如果持有，则重新设置为30秒
            redissonLock.lock(LOCK_EXPIRE_SECONDS, TimeUnit.SECONDS);

            Optional<String> optional = Optional.ofNullable(redisTemplate.opsForValue().get(STORE));
            if (!optional.isPresent()) {
                return "系统异常";
            }

            int over = Integer.parseInt(optional.get());
            if (over == 0) {
                return "库存不足";
            }

            redisTemplate.opsForValue().set(STORE, String.valueOf(--over));

            return "出售产品编号: " + (over + 1);
        } finally {
            redissonLock.unlock();
            /*if (orderId.equals(redisTemplate.opsForValue().get(LOCK_KEY))) {
                redisTemplate.delete(LOCK_KEY);
            }*/
        }
    }

}
