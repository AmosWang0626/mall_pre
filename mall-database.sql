
-- 创建数据库
CREATE DATABASE mall CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_general_ci';

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `USER_ID` bigint(11) NOT NULL COMMENT '用户ID',
  `ORDER_NO` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号',
  `CONSUME_DATE` date DEFAULT NULL COMMENT '消费日期',
  `CONSUME_TIME` datetime DEFAULT NULL COMMENT '消费时间',
  `CONSUME_AMOUNT` decimal(8,2) DEFAULT NULL COMMENT '消费金额',
  `SERIAL_NO` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '交易流水号',
  `ORDER_STATUS` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '订单状态',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='订单表';

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `ORDER_NO` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号',
  `PRODUCT_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '消费商品名称',
  `PRODUCT_UNIT_PRICE` decimal(8,2) DEFAULT NULL COMMENT '消费商品单价',
  `PRODUCT_CONSUME_COUNT` int(11) DEFAULT NULL COMMENT '消费商品数量',
  `CONSUME_DATE` date DEFAULT NULL COMMENT '消费日期',
  `CONSUME_TIME` datetime DEFAULT NULL COMMENT '消费时间',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='订单详情表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `NICK_NAME` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `NAME` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名字',
  `PHONE_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `EMAIL` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `SALT` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码盐',
  `PASSWORD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `GENDER` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';




