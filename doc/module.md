# Module

创建module的步骤

```tex
1. 创建module
2. 改pom
3. 写yml
4. 配之主启动类
5. 写业务类
```

## cloud-payment-service 支付服务

> 描述

```tex
订单的增删改查等功能的服务
```

> 数据库SQL

```mysql
CREATE TABLE `payment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `serials` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单流水号的json数组',
  `orderNo` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `submitterId` bigint(20) NULL DEFAULT NULL COMMENT '订单提交人ID',
  `orderType` int(2) NULL DEFAULT NULL COMMENT '订单类型',
  `orderStatus` int(2) NULL DEFAULT NULL COMMENT '订单状态',
  `paymentType` int(4) NULL DEFAULT NULL COMMENT '支付类型',
  `paymentAttachment` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付附属信息',
  `receiverId` bigint(20) NULL DEFAULT NULL COMMENT '收货人信息ID',
  `reviewerId` int(11) NULL DEFAULT NULL COMMENT '订单审核人ID',
  `orderAmount` bigint(40) NULL DEFAULT NULL COMMENT '订单金额（实际支付）',
  `createTime` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` bigint(20) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `orderNo`(`orderNo`) USING BTREE,
  INDEX `createTime`(`createTime`) USING BTREE,
  INDEX `submitterId_orderType_orderSuatus`(`submitterId`, `orderType`, `orderStatus`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
```

> 接口

公共参数：

公共返回：

| 参数名称 | 参数描述 |      |
| -------- | -------- | ---- |
| code     |          |      |
| message  |          |      |
| data     |          |      |



| 接口 | 描述 | 请求方式 | 参数 |
| ---- | ---- | -------- | ---- |
|      |      |          |      |

