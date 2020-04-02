package com.mall.warehouse.business;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * DESCRIPTION: 库存业务实现
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/4/2
 */
@Service
public class InventoryBusinessImpl implements InventoryBusiness {

    private final StringRedisTemplate redisTemplate;

    private static final String STORE = "STORE_POOL";

    private static final String LOCK_KEY = "LOCK_KEY";

    public InventoryBusinessImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String consume(String name) {
        long val = System.currentTimeMillis();
        Boolean absent = redisTemplate.opsForValue().setIfAbsent(LOCK_KEY, String.valueOf(val));
        if (!absent) {
            return "请稍后重试!";
        }

        Optional<String> optional = Optional.ofNullable(redisTemplate.opsForValue().get(STORE));
        if (!optional.isPresent()) {
            return "系统异常";
        }

        int over = Integer.parseInt(optional.get());
        if (over == 0) {
            return "库存不足";
        }

        redisTemplate.opsForValue().set(STORE, String.valueOf(--over));
        redisTemplate.delete(LOCK_KEY);

        return "剩余库存: " + (over - 1);
    }

}
