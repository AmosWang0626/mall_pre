# order

## 订单系统


## 订单状态

- 待支付 AWAIT_PAY
  - 付款 PAY_SUCCESS
  - 取消订单 PAY_CANCEL
  - 确认信息(多件商品) INFO_CONFIRM
- 待发货 AWAIT_SHIP
  - 待发货退款 AWAIT_SHIP_CANCEL
- 待收货 SHIPPED
  - 确认收货 RECEIVED
  - 待收货退款 SHIPPED_CANCEL
- 交易完成 FINISH
  - 评价 EVALUATION
  - 申请售后 AFTER_SALE
- 交易终态 DONE

