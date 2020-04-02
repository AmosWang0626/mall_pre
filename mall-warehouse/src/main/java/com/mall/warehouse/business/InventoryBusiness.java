package com.mall.warehouse.business;

/**
 * DESCRIPTION: 库存业务接口
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/4/2
 */
public interface InventoryBusiness {

    /**
     * 扣减库存
     *
     * @param orderId 订单号
     * @return 结果
     */
    String consume(String orderId);

}
