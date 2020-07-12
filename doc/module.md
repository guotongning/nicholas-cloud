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
  `serial` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单流水号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
```

> 接口

公共参数：

公共返回：

| 参数名称      | 参数描述 |      |
| ------------- | -------- | ---- |
| code(Integer) |          |      |
| message       |          |      |
| data          |          |      |



| 接口 | 描述 | 请求方式 | 参数 |
| ---- | ---- | -------- | ---- |
|      |      |          |      |

