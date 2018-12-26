package com.mall.order;

import com.mall.order.common.OrderStatusEnum;
import com.mall.order.dao.entity.ApplyOrderEntity;
import com.mall.order.dao.entity.OrderDetailEntity;
import com.mall.order.dao.mapper.ApplyOrderMapper;
import com.mall.order.dao.mapper.OrderDetailMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTests {

    @Resource
    private ApplyOrderMapper applyOrderMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Test
    public void insert() {
        String orderNo = "ORDER_" + System.currentTimeMillis();

        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();

        OrderDetailEntity detailEntity1 = new OrderDetailEntity();
        detailEntity1.setOrderNo(orderNo);
        detailEntity1.setAddress("上海");
        detailEntity1.setConsumeTime(new Date());
        detailEntity1.setCreateTime(new Date());
        detailEntity1.setProductCount(3);
        detailEntity1.setProductName("水晶");
        detailEntity1.setProductUnitPrice(new BigDecimal(100));
        orderDetailEntities.add(detailEntity1);

        OrderDetailEntity detailEntity2 = new OrderDetailEntity();
        detailEntity2.setOrderNo(orderNo);
        detailEntity2.setAddress("北京");
        detailEntity2.setConsumeTime(new Date());
        detailEntity2.setCreateTime(new Date());
        detailEntity2.setProductCount(22);
        detailEntity2.setProductName("斗篷");
        detailEntity2.setProductUnitPrice(new BigDecimal(800));
        orderDetailEntities.add(detailEntity2);

        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderDetailEntity orderDetailEntity : orderDetailEntities) {
            totalAmount = totalAmount.add(orderDetailEntity.getProductUnitPrice().multiply(new BigDecimal(orderDetailEntity.getProductCount())));
        }

        ApplyOrderEntity applyOrderEntity = new ApplyOrderEntity();
        applyOrderEntity.setOrderNo(orderNo);
        applyOrderEntity.setOrderStatus(OrderStatusEnum.AWAIT_PAY);
        applyOrderEntity.setConsumeAmount(totalAmount);
        applyOrderEntity.setConsumeDate(new Date());
        applyOrderEntity.setConsumeTime(new Date());
        applyOrderEntity.setCreateTime(new Date());
        applyOrderEntity.setReduceAmount(BigDecimal.ZERO);
        applyOrderEntity.setReduceReason(null);
        // applyOrderEntity.setSerialNo("ALI_PAY_" + System.currentTimeMillis());
        applyOrderEntity.setUserId(10000L);

        applyOrderMapper.save(applyOrderEntity);
        orderDetailMapper.saveAll(orderDetailEntities);
    }

}
