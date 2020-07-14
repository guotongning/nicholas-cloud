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
=============================================
CREATE TABLE `transaction`  (
  `id` bigint(20) NOT NULL,
  `serial` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流水序列号',
  `transactionType` int(2) NULL DEFAULT NULL COMMENT '交易类型',
  `transactionStatus` int(2) NULL DEFAULT NULL COMMENT '交易状态',
  `paymentUserId` bigint(20) NULL DEFAULT NULL COMMENT '付款方用户id',
  `paymentAccount` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方账号',
  `paymentUsername` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方昵称',
  `paymentPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方手机号',
  `paymentIdentityID` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方身份证号',
  `paymentAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方地址',
  `paymentTime` bigint(20) NULL DEFAULT NULL COMMENT '出账时间',
  `collectUserId` bigint(20) NULL DEFAULT NULL COMMENT '收款方用户id',
  `collectAccount` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款方账号',
  `collectUsername` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款方昵称',
  `collectPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款方手机号',
  `collectIdentityID` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款方身份证号',
  `collectAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款方地址',
  `collectTime` bigint(20) NULL DEFAULT NULL COMMENT '入账时间（到账时间）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `paymentId_type_status`(`paymentUserId`, `transactionType`, `transactionStatus`) USING BTREE,
  INDEX `collectUserId`(`collectUserId`) USING BTREE,
  INDEX `paymentTime`(`paymentTime`) USING BTREE,
  INDEX `collectTime`(`collectTime`) USING BTREE,
  UNIQUE INDEX `serial`(`serial`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
=============================================
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

## api-base-utils 通用工具

> 描述

```tex
所有项目都会用到的通用好用的工具类的聚合。
```

