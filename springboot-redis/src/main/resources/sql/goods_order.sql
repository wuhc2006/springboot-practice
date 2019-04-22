/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : springboot-practice

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-04-22 23:06:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods_order
-- ----------------------------
DROP TABLE IF EXISTS `goods_order`;
CREATE TABLE `goods_order` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `count` int(3) NOT NULL DEFAULT '1' COMMENT '购买数量',
  `total_price` decimal(10,2) DEFAULT NULL,
  `create_time` date NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_order
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL,
  `goods_name` varchar(20) NOT NULL COMMENT '商品名称',
  `amount` int(8) NOT NULL,
  `inventory` int(8) NOT NULL COMMENT '库存',
  `original_price` decimal(10,2) NOT NULL COMMENT '原价',
  `sell_price` decimal(10,2) NOT NULL COMMENT '售价',
  `create_time` date NOT NULL COMMENT '创建时间',
  `modify_time` date NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '小米手机', '1000', '1000', '1999.00', '999.00', '2019-04-22', '2019-04-22');
