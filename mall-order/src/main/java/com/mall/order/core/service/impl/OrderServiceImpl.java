package com.mall.order.core.service.impl;

import com.mall.common.base.GenericResponse;
import com.mall.common.util.IdUtils;
import com.mall.order.common.OrderStatusEnum;
import com.mall.order.core.service.OrderService;
import com.mall.order.core.service.ProductService;
import com.mall.order.dao.entity.OrderDetailEntity;
import com.mall.order.dao.entity.OrderEntity;
import com.mall.order.dao.mapper.OrderDetailMapper;
import com.mall.order.dao.mapper.OrderMapper;
import com.mall.order.pojo.OrderConverter;
import com.mall.order.pojo.dto.OrderDTO;
import com.mall.order.pojo.form.OrderDetailForm;
import com.mall.order.pojo.form.OrderForm;
import com.mall.order.pojo.form.OrderSearchForm;
import com.mall.order.pojo.vo.OrderDetailVO;
import com.mall.order.pojo.vo.OrderVO;
import com.mall.order.pojo.vo.ProductVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Resource
    private OrderConverter orderConverter;


    @Override
    public GenericResponse<OrderVO> preview(OrderForm form) {
        OrderDTO orderDTO = new OrderDTO();
        generateOrder(form, orderDTO);

        return new GenericResponse<>(getOrderVO(orderDTO));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public GenericResponse<OrderVO> create(OrderForm form) {
        OrderDTO orderDTO = new OrderDTO();
        generateOrder(form, orderDTO);

        orderMapper.save(orderDTO.getOrderEntity());
        orderDetailMapper.saveAll(orderDTO.getOrderDetailEntities());

        return new GenericResponse<>(getOrderVO(orderDTO));
    }

    @Override
    public OrderVO getByOrderNo(String no) {
        OrderDTO orderDTO = new OrderDTO();
        orderMapper.findByOrderNo(no).ifPresent(orderDTO::setOrderEntity);
        orderDetailMapper.findByOrderNo(no).ifPresent(orderDTO::setOrderDetailEntities);

        return getOrderVO(orderDTO);
    }

    @Override
    public List<OrderVO> list(OrderSearchForm form) {
        return Optional.of(orderMapper.findAll())
                // list<entity> ---> list<vo>
                .map(entities -> {
                    List<OrderVO> orderVoList = orderConverter.reductionToList(entities);
                    // 设置订单详情
                    orderVoList.forEach(orderVO ->
                            orderVO.setDetailList(orderDetailMapper.findByOrderNo(orderVO.getOrderNo())
                                    .map(details -> orderConverter.<OrderDetailVO, OrderDetailEntity>reductionToList(details))
                                    .orElse(Collections.emptyList()))
                    );
                    return orderVoList;
                })
                .orElse(Collections.emptyList());
    }

    @Override
    public GenericResponse<String> delete(String no) {
        orderMapper.findByOrderNo(no)
                .ifPresent(orderEntity -> orderMapper.deleteLogic(orderEntity));

        return GenericResponse.SUCCESS;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public GenericResponse<String> realDelete(String no) {
        orderMapper.deleteByOrderNo(no);
        orderDetailMapper.deleteByOrderNo(no);

        return GenericResponse.SUCCESS;
    }

    /**
     * 封装订单 VO
     *
     * @param orderDTO OrderDTO
     * @return OrderVO
     */
    private OrderVO getOrderVO(OrderDTO orderDTO) {
        OrderVO orderVO = orderConverter.reduction(orderDTO.getOrderEntity());
        List<OrderDetailVO> detailList = orderConverter.reductionToList(orderDTO.getOrderDetailEntities());
        orderVO.setDetailList(detailList);

        return orderVO;
    }

    /**
     * 根据订单表单生成订单信息
     *
     * @param form     订单表单
     * @param orderDTO 订单返回参数封装
     */
    private void generateOrder(OrderForm form, OrderDTO orderDTO) {
        assert orderDTO != null;

        List<OrderDetailForm> detailList = form.getDetailList();
        if (detailList == null || detailList.size() == 0) {
            return;
        }

        // 订单
        OrderEntity orderEntity = new OrderEntity();
        // 订单详情
        List<OrderDetailEntity> detailEntities = new ArrayList<>();
        // 总价
        BigDecimal consumeAmount = BigDecimal.ZERO;

        // 生成订单号
        String orderNo = IdUtils.orderNo();

        for (OrderDetailForm detailForm : detailList) {
            ProductVO productVO = productService.getByProductNo(detailForm.getProductNo());
            OrderDetailEntity detailEntity = orderConverter.convert2(productVO);
            detailEntity.setOrderNo(orderNo);
            detailEntity.setBuyCount(detailForm.getBuyCount());
            detailEntities.add(detailEntity);
            consumeAmount = consumeAmount.add(
                    detailEntity.getUnitPrice().multiply(BigDecimal.valueOf(detailEntity.getBuyCount())));
        }

        orderEntity.setOrderNo(orderNo);
        orderEntity.setOrderStatus(OrderStatusEnum.AWAIT_PAY);
        orderEntity.setUserId(form.getUserId());
        orderEntity.setConsumeAmount(consumeAmount);
        orderEntity.setDescription(form.getDescription());
        orderEntity.setConsumeDate(LocalDate.now());
        orderEntity.setConsumeTime(LocalDateTime.now());

        // 封装返回对象
        orderDTO.setOrderEntity(orderEntity).setOrderDetailEntities(detailEntities);
    }

}
