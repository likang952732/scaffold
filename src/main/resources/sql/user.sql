-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '登录名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态(0->禁用；1->启用)',
  `user_type` TINYINT(1) DEFAULT '0' COMMENT '用户类型(0->普通用户；1->厨师; 2->管理员)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(64) DEFAULT NULL COMMENT '最近一次登录IP地址',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';



-- ----------------------------
-- Table structure for t_chef
-- ----------------------------
DROP TABLE IF EXISTS `t_chef`;
CREATE TABLE `t_chef` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL COMMENT '用户id',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` TINYINT(1) DEFAULT '0' COMMENT '性别(0->男；1->女)',
  `icon` varchar(500) DEFAULT NULL COMMENT '厨师头像',
  `birth_date` varchar(32) DEFAULT NULL COMMENT '出生年月',
  `qq` varchar(32) DEFAULT NULL COMMENT 'qq',
  `weixin` varchar(32) DEFAULT NULL COMMENT 'weixin',
  `title` varchar(32) DEFAULT NULL COMMENT '厨师称号',
  `job_date` varchar(64) DEFAULT NULL COMMENT '从业时间',
  `credential_url` varchar(256) DEFAULT NULL COMMENT '资质图片',
  `service_zone_list` varchar(256) DEFAULT NULL COMMENT '服务区域',
  `serve_time_list` varchar(256) DEFAULT NULL COMMENT '服务时间',
  `start_time` varchar(64) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(64) DEFAULT NULL COMMENT '结束时间',
  `is_food` TINYINT(1) DEFAULT '0' COMMENT '是否自备食材(0->否;1->是)',
  `is_ware` TINYINT(1) DEFAULT '0' COMMENT '是否自备厨具(0->否;1->是)',
  `food_list` varchar(64) DEFAULT NULL COMMENT '拿手菜',
  `cuisine_ist` varchar(256) DEFAULT NULL COMMENT '擅长菜系',
  `star` TINYINT(1) DEFAULT '0' COMMENT '星级大厨(0->；1->)',
  `status` TINYINT(1) DEFAULT '0' COMMENT '认证状态(0->认证中；1->已认证)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='厨师表';



-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(32) NOT NULL COMMENT '订单id',
  `admin_id` bigint(20) NOT NULL COMMENT '用户id',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `amount` bigint(20) NOT NULL COMMENT '订单金额(分为单位)',
  `status` TINYINT(1) DEFAULT '0' COMMENT '(0->等待接单状态;1->已接单;2->已完成)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP, 
  `receive_time` datetime DEFAULT NULL COMMENT '订单接收时间', 
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

