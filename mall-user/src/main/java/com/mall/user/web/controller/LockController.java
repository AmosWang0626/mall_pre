package com.mall.user.web.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mall.common.api.Token;
import com.mall.user.dao.entity.DbLockEntity;
import com.mall.user.dao.mapper.DbLockMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.concurrent.*;

/**
 * 模块名称: mall-user
 * 模块描述: 数据库锁测试
 *
 * @author amos.wang
 * @date 2020/3/19 9:45
 */
@RestController
@RequestMapping("lock")
public class LockController {

    /**
     * 线程池
     */
    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("lock-pool-%d").build();
    private ExecutorService singleThreadPool = new ThreadPoolExecutor(
            2, 3, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    @Resource
    private DbLockMapper dbLockMapper;

    @Token(check = false)
    @GetMapping("multiple")
    public void multiple() {
        save();
    }

    private void save() {
        long account = 10L;
        String currentDay = LocalDate.now().toString();
        doSave(account, currentDay);
        doSave(account + 10, currentDay);
        doSave(account + 10, currentDay);
    }

    private void doSave(Long account, String currentDay) {
        singleThreadPool.execute(() -> {
            DbLockEntity dbLockEntity = dbLockMapper.findPrimary(account, currentDay);
            if (dbLockEntity != null) {
                dbLockEntity.setDescription("多线程更新" + Thread.currentThread().getName());
            } else {
                dbLockEntity = new DbLockEntity();
                dbLockEntity.setAccount(account);
                dbLockEntity.setCurrentDay(currentDay);
                dbLockEntity.setDescription("多线程插入测试");
            }
            dbLockMapper.save(dbLockEntity);
        });
        singleThreadPool.shutdown();
    }


}
