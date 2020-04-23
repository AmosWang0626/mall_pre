
-- 用户
INSERT INTO `mall`.`org_user` (`id`, `account`, `username`, `salt`, `password`, `status`, `create_time`, `create_user`, `modify_time`, `modify_user`, `delete_flag`) VALUES ('8a80cb816f2bd9f6016f2bda3ee00000', 'amos', 'amos.wang', 'xRS4SwqW', '706056F58CC0182B', '1', '2019-12-21 22:25:21', 'amos.wang', '2019-12-21 22:25:21', 'amos.wang', b'0');

-- 商品
INSERT INTO `mall`.`po_product` (`id`, `product_no`, `name`, `unit_price`, `description`, `create_time`, `create_user`, `modify_time`, `modify_user`, `delete_flag`) VALUES ('8a80cb8171a291360171a291a1fd0000', 'PROD20200422225715507993d376bc38', '劳斯莱斯幻影', '10000.00', 'Rolls-Royce 劳斯莱斯幻影，世界上最安静的车', '2020-04-22 10:46:25', 'anonymous', '2020-04-22 10:51:27', 'anonymous', b'0');
INSERT INTO `mall`.`po_product` (`id`, `product_no`, `name`, `unit_price`, `description`, `create_time`, `create_user`, `modify_time`, `modify_user`, `delete_flag`) VALUES ('8a80cb8171a291360171a291a4960001', 'PROD20200422225716fd77330c101bc4', '莱肯超级跑车', '2000.00', 'W Motors 莱肯超级跑车，第一个位于中东的高性能豪华跑车的开发商', '2020-04-22 10:46:26', 'anonymous', '2020-04-22 10:46:26', 'anonymous', b'0');

-- 订单
INSERT INTO `mall`.`po_order` (`id`, `user_id`, `order_no`, `serial_no`, `consume_amount`, `order_status`, `consume_date`, `consume_time`, `reduce_no`, `reduce_amount`, `reduce_reason`, `description`, `create_time`, `create_user`, `modify_time`, `modify_user`, `delete_flag`) VALUES ('2c9097a371a633530171a633a1880000', '8a80cb816f2bd9f6016f2bda3ee00000', 'ODER20200423164213f530f8c42fc55e', NULL, '28000.00', 'AWAIT_PAY', '2020-04-21', '2020-04-23 03:42:14', NULL, NULL, NULL, '兜风专用', '2020-04-23 03:42:14', 'anonymous', '2020-04-23 04:07:47', 'anonymous', b'0');

INSERT INTO `mall`.`po_order` (`id`, `user_id`, `order_no`, `serial_no`, `consume_amount`, `order_status`, `consume_date`, `consume_time`, `reduce_no`, `reduce_amount`, `reduce_reason`, `description`, `create_time`, `create_user`, `modify_time`, `modify_user`, `delete_flag`) VALUES ('2c9097a371a639210171a63968d60000', '8a80cb816f2bd9f6016f2bda3ee00000', 'ODER2020042316483264f59eef132985', NULL, '16000.00', 'AWAIT_PAY', '2020-04-22', '2020-04-23 03:48:32', NULL, NULL, NULL, '兜风专用', '2020-04-23 03:48:32', 'anonymous', '2020-04-23 03:48:32', 'anonymous', b'0');

-- 订单详情
INSERT INTO `mall`.`po_order_detail` (`id`, `order_no`, `product_no`, `product_name`, `unit_price`, `buy_count`, `user_address`, `create_time`, `create_user`, `modify_time`, `modify_user`, `delete_flag`) VALUES ('2c9097a371a633530171a633a1ad0001', 'ODER20200423164213f530f8c42fc55e', 'PROD20200422225715507993d376bc38', '劳斯莱斯幻影', '10000.00', '2', NULL, '2020-04-23 03:42:14', 'anonymous', '2020-04-23 03:42:14', 'anonymous', b'0');
INSERT INTO `mall`.`po_order_detail` (`id`, `order_no`, `product_no`, `product_name`, `unit_price`, `buy_count`, `user_address`, `create_time`, `create_user`, `modify_time`, `modify_user`, `delete_flag`) VALUES ('2c9097a371a633530171a633a1af0002', 'ODER20200423164213f530f8c42fc55e', 'PROD20200422225716fd77330c101bc4', '莱肯超级跑车', '2000.00', '4', NULL, '2020-04-23 03:42:14', 'anonymous', '2020-04-23 03:42:14', 'anonymous', b'0');

INSERT INTO `mall`.`po_order_detail` (`id`, `order_no`, `product_no`, `product_name`, `unit_price`, `buy_count`, `user_address`, `create_time`, `create_user`, `modify_time`, `modify_user`, `delete_flag`) VALUES ('2c9097a371a639210171a63968ef0001', 'ODER2020042316483264f59eef132985', 'PROD20200422225715507993d376bc38', '劳斯莱斯幻影', '10000.00', '1', NULL, '2020-04-23 03:48:32', 'anonymous', '2020-04-23 03:48:32', 'anonymous', b'0');
INSERT INTO `mall`.`po_order_detail` (`id`, `order_no`, `product_no`, `product_name`, `unit_price`, `buy_count`, `user_address`, `create_time`, `create_user`, `modify_time`, `modify_user`, `delete_flag`) VALUES ('2c9097a371a639210171a63968f10002', 'ODER2020042316483264f59eef132985', 'PROD20200422225716fd77330c101bc4', '莱肯超级跑车', '2000.00', '3', NULL, '2020-04-23 03:48:32', 'anonymous', '2020-04-23 03:48:32', 'anonymous', b'0');

