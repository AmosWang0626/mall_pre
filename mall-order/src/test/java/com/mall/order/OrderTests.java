package com.mall.order;

import com.mall.order.common.OrderStatusEnum;
import com.mall.order.core.service.OrderService;
import com.mall.order.dao.entity.OrderDetailEntity;
import com.mall.order.dao.entity.OrderEntity;
import com.mall.order.dao.mapper.OrderDetailMapper;
import com.mall.order.dao.mapper.OrderMapper;
import com.mall.order.pojo.form.OrderDetailForm;
import com.mall.order.pojo.form.OrderForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 模块名称: mall
 * 模块描述: 订单
 *
 * @author amos.wang
 * @date 2020/4/22 16:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTests {

    @Resource
    private OrderService orderService;

    @Test
    public void preview() {
        OrderForm orderForm = new OrderForm();
        orderForm.setUserId("8a80cb816f2bd9f6016f2bda3ee00000");
        orderForm.setDescription("兜风专用");
        List<OrderDetailForm> detailForms = new ArrayList<>();
        detailForms.add(new OrderDetailForm().setProductNo("PROD20200422225715507993d376bc38").setBuyCount(2));
        detailForms.add(new OrderDetailForm().setProductNo("PROD20200422225716fd77330c101bc4").setBuyCount(4));
        orderForm.setDetailList(detailForms);

        System.out.println(orderService.preview(orderForm));
    }


    @Test
    public void create() {
        OrderForm orderForm = new OrderForm();
        orderForm.setUserId("8a80cb816f2bd9f6016f2bda3ee00000");
        orderForm.setDescription("兜风专用");
        List<OrderDetailForm> detailForms = new ArrayList<>();
        detailForms.add(new OrderDetailForm().setProductNo("PROD20200422225715507993d376bc38").setBuyCount(1));
        detailForms.add(new OrderDetailForm().setProductNo("PROD20200422225716fd77330c101bc4").setBuyCount(3));
        orderForm.setDetailList(detailForms);

        System.out.println(orderService.create(orderForm));
    }

    @Test
    public void get() {
        System.out.println(orderService.getByOrderNo("ODER20200423164213f530f8c42fc55e"));
    }

    @Test
    public void list() {
        System.out.println(orderService.list(null));
    }

    @Test
    public void logicDelete() {
        System.out.println(orderService.delete("ODER20200423164213f530f8c42fc55e"));
    }

    @Test
    public void delete() {
        
    }

}
