package com.mall.order.core.service.impl;

import com.mall.common.base.GenericResponse;
import com.mall.order.common.OrderStatusEnum;
import com.mall.order.core.service.OrderService;
import com.mall.order.core.service.ProductService;
import com.mall.order.dao.entity.OrderDetailEntity;
import com.mall.order.dao.entity.OrderEntity;
import com.mall.order.dao.mapper.OrderDetailMapper;
import com.mall.order.dao.mapper.OrderMapper;
import com.mall.order.pojo.dto.OrderInitDTO;
import com.mall.order.pojo.form.OrderDetailForm;
import com.mall.order.pojo.form.OrderForm;
import com.mall.order.pojo.form.OrderSearchForm;
import com.mall.order.pojo.vo.OrderVO;
import com.mall.order.pojo.vo.ProductVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 模块名称: mall
 * 模块描述: 订单相关
 *
 * @author amos.wang
 * @date 2020/4/22 17:36
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private ProductService productService;

    @Override
    public GenericResponse<OrderVO> preview(OrderForm form) {
        OrderInitDTO orderInitDto = new OrderInitDTO();
        generateOrder(form, orderInitDto);
        return null;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public GenericResponse<OrderVO> create(OrderForm form) {
        OrderInitDTO orderInitDTO = new OrderInitDTO();
        generateOrder(form, orderInitDTO);
        return null;
    }

    @Override
    public GenericResponse<OrderVO> getByOrderNo(String no) {
        return null;
    }

    @Override
    public GenericResponse<List<OrderVO>> list(OrderSearchForm form) {
        return null;
    }

    private void generateOrder(OrderForm form, OrderInitDTO orderInitDto) {
        assert orderInitDto != null;

        List<OrderDetailForm> detailList = form.getDetailList();
        if (detailList == null || detailList.size() == 0) {
            return;
        }

        // 订单
        OrderEntity orderEntity = new OrderEntity();
        // 订单详情
        List<OrderDetailEntity> detailEntities = new ArrayList<>();
        // 总价
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderDetailForm detailForm : detailList) {
            ProductVO productVO = productService.getByProductNo(detailForm.getProductNo());
            detailEntities.add(new OrderDetailEntity()
                    .setProductNo(detailForm.getProductNo())
                    .setProductName(productVO.getName())
                    .setUnitPrice(productVO.getUnitPrice())
                    .setBuyCount(detailForm.getProductCount())
            );
            totalAmount = totalAmount.add(
                    productVO.getUnitPrice().multiply(BigDecimal.valueOf(detailForm.getProductCount())));
        }

        orderEntity.setOrderStatus(OrderStatusEnum.AWAIT_PAY);
        orderEntity.setUserId(form.getUserId());
        orderEntity.setConsumeAmount(totalAmount);
        orderEntity.setDescription(form.getDescription());
        orderEntity.setConsumeDate(LocalDate.now());
        orderEntity.setConsumeTime(LocalDateTime.now());

        // 
        orderInitDto.setOrderEntity(orderEntity);
        orderInitDto.setOrderDetailEntities(detailEntities);
    }
}
