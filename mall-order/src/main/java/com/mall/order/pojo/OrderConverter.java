package com.mall.order.pojo;

import com.mall.common.api.BaseConverter;
import com.mall.order.dao.entity.OrderDetailEntity;
import com.mall.order.dao.entity.OrderEntity;
import com.mall.order.dao.entity.ProductEntity;
import com.mall.order.pojo.form.ProductForm;
import com.mall.order.pojo.vo.OrderDetailVO;
import com.mall.order.pojo.vo.OrderVO;
import com.mall.order.pojo.vo.ProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 模块名称: mall
 * 模块描述: OrderConverter
 *
 * @author amos.wang
 * @date 2020/4/8 17:22
 */
@Mapper(componentModel = "spring")
public interface OrderConverter extends BaseConverter {

    @Mappings({})
    ProductEntity convert(ProductForm form);

    @Mappings({})
    ProductVO reduction(ProductEntity entity);

    @Mappings({})
    OrderVO reduction(OrderEntity entity);

    @Mappings({})
    OrderDetailVO reduction(OrderDetailEntity entity);

    @Mappings({
            @Mapping(source = "name", target = "productName")
    })
    OrderDetailEntity convert2(ProductVO vo);

}