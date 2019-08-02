/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : springboot-practice

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-07-29 21:17:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('13');

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` bigint(20) NOT NULL,
  `goods_name` varchar(20) NOT NULL COMMENT '商品名称',
  `amount` int(8) NOT NULL,
  `inventory` int(8) NOT NULL COMMENT '库存',
  `original_price` decimal(10,2) NOT NULL COMMENT '原价',
  `sell_price` decimal(10,2) NOT NULL COMMENT '售价',
  `create_time` date NOT NULL COMMENT '创建时间',
  `modify_time` date NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `fullindex_goodsname` (`goods_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('1', '小米手机', '1000', '996', '1999.00', '999.00', '2019-04-22', '2019-04-22');
INSERT INTO `t_goods` VALUES ('2', 'apple手机', '3000', '100', '5000.00', '4599.00', '2019-05-11', '2019-05-11');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` bigint(50) DEFAULT NULL COMMENT '菜单父节点ID',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_name` varchar(50) DEFAULT NULL COMMENT '父级菜单名称',
  `path` varchar(100) DEFAULT NULL COMMENT '路径',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_people` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `update_people` varchar(50) DEFAULT NULL COMMENT '修改人',
  `sort_no` bigint(10) NOT NULL DEFAULT '1' COMMENT '排序字段',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8 COMMENT='菜单信息表';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('116', '0', '用户管理', null, 'pages/user/user-main.html', '2018-06-14 16:58:14', null, null, null, '1');
INSERT INTO `t_menu` VALUES ('117', '0', '角色管理', null, 'pages/role/role-main.html', '2018-06-14 16:58:31', null, null, null, '1');
INSERT INTO `t_menu` VALUES ('118', '0', '菜单管理', '', 'pages/menu/menu-main.html', '2018-06-14 16:59:23', null, '2018-07-14 16:54:22', null, '1');
INSERT INTO `t_menu` VALUES ('119', '0', '供应商管理', null, null, '2018-06-14 16:59:39', null, null, null, '1');
INSERT INTO `t_menu` VALUES ('120', '0', '订单管理', null, null, '2018-06-14 17:02:19', null, null, null, '1');
INSERT INTO `t_menu` VALUES ('121', '0', '售后管理', null, null, '2018-06-14 17:02:34', null, null, null, '1');
INSERT INTO `t_menu` VALUES ('122', '0', '出售商品管理', null, null, '2018-06-14 17:03:55', null, null, null, '1');
INSERT INTO `t_menu` VALUES ('124', '0', '对账管理', null, null, '2018-06-14 17:04:24', null, null, null, '1');
INSERT INTO `t_menu` VALUES ('127', '116', '供应商用户管理', '用户管理', 'pages/user/supplier-main.html', '2018-06-14 17:14:18', null, null, null, '1');
INSERT INTO `t_menu` VALUES ('128', '116', '平台用户管理', '用户管理', 'pages/user/user-main.html', '2018-06-14 17:14:37', null, '2018-07-10 19:55:28', null, '2');
INSERT INTO `t_menu` VALUES ('129', '117', '角色权限管理', '角色管理', 'pages/role/role-main.html', '2018-06-14 17:15:23', null, null, null, '1');
INSERT INTO `t_menu` VALUES ('130', '118', '模块菜单管理', '菜单管理', 'pages/menu/menu-main.html', '2018-06-14 17:15:44', null, null, null, '1');
INSERT INTO `t_menu` VALUES ('131', '120', '接单', '订单管理', 'pages/order/receive.html', '2018-06-14 17:16:34', null, '2018-07-17 17:19:18', null, '1');
INSERT INTO `t_menu` VALUES ('132', '120', '退单', '订单管理', 'pages/order/return-back.html', '2018-06-14 17:16:41', null, '2018-07-17 17:19:38', null, '2');
INSERT INTO `t_menu` VALUES ('133', '121', '用户评价', '售后管理', 'pages/after-sale/comment.html', '2018-06-14 17:17:02', null, '2018-07-17 17:20:49', null, '1');
INSERT INTO `t_menu` VALUES ('134', '121', '投诉建议', '售后管理', 'pages/after-sale/suggestion.html', '2018-06-14 17:17:46', null, '2018-07-17 17:21:23', null, '2');
INSERT INTO `t_menu` VALUES ('135', '122', '出售商品', '出售商品管理', 'pages/product/onsale.html', '2018-06-14 17:18:01', null, '2018-07-17 17:22:16', null, '1');
INSERT INTO `t_menu` VALUES ('136', '122', '待审核商品', '出售商品管理', 'pages/product/checking.html', '2018-06-14 17:18:26', null, '2018-07-17 17:22:36', null, '2');
INSERT INTO `t_menu` VALUES ('137', '124', '日清结', '对账管理', 'pages/check-account/day-check.html', '2018-06-14 17:19:06', null, '2018-07-17 17:24:16', null, '1');
INSERT INTO `t_menu` VALUES ('138', '0', '月清结', '', 'pages/check-account/month-check/diffierences.html', '2018-06-14 17:19:19', null, '2018-07-17 18:17:32', null, '2');
INSERT INTO `t_menu` VALUES ('140', '120', '商家历史订单', '订单管理', 'pages/order/shop-history.html', '2018-07-16 18:42:45', null, '2018-07-18 16:34:57', null, '1');
INSERT INTO `t_menu` VALUES ('141', '119', '供应商管理列表', '供应商管理', 'pages/shop/shop-main.html', '2018-07-17 17:27:04', null, '2018-07-17 17:31:54', null, '1');
INSERT INTO `t_menu` VALUES ('142', '120', '用户历史订单', '订单管理', 'pages/order/user-history.html', '2018-07-18 16:35:20', null, '2018-07-18 16:35:20', null, '1');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `count` int(3) NOT NULL DEFAULT '1' COMMENT '购买数量',
  `total_price` decimal(10,2) DEFAULT NULL,
  `create_time` date NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('9', '1', '1', '1', '999.00', '2019-06-12');
INSERT INTO `t_order` VALUES ('10', '2', '1', '1', '999.00', '2019-06-12');
INSERT INTO `t_order` VALUES ('11', '3', '1', '1', '999.00', '2019-06-12');
INSERT INTO `t_order` VALUES ('12', '4', '1', '1', '999.00', '2019-06-12');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'add', '新增', '2019-01-02 21:17:54', null);
INSERT INTO `t_permission` VALUES ('2', 'view', '查看', '2019-01-02 21:18:07', null);
INSERT INTO `t_permission` VALUES ('3', 'update', '修改', '2019-01-02 21:18:22', null);
INSERT INTO `t_permission` VALUES ('4', 'delete', '删除', '2019-01-02 21:18:36', null);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色标号',
  `title` varchar(255) DEFAULT NULL COMMENT '角色标题',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `type` int(2) DEFAULT '0' COMMENT '角色类型',
  `status` int(2) DEFAULT '1' COMMENT '启用或禁用，0为禁用，1为启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', '管理员', '2019-01-02 21:16:49', '2019-01-21 21:13:39', '1', '1');
INSERT INTO `t_role` VALUES ('2', 'guest', '游客', '2019-01-02 21:16:57', null, '0', '1');
INSERT INTO `t_role` VALUES ('4', 'whc', '测试1', '2019-01-11 22:48:10', '2019-07-26 23:40:37', '1', '1');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `role_menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_menu_id`),
  KEY `t_role_id` (`role_id`) USING HASH COMMENT '增加索引'
) ENGINE=InnoDB AUTO_INCREMENT=2193 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('1892', '4', '129');
INSERT INTO `t_role_menu` VALUES ('1893', '4', '130');
INSERT INTO `t_role_menu` VALUES ('1894', '4', '131');
INSERT INTO `t_role_menu` VALUES ('1895', '4', '132');
INSERT INTO `t_role_menu` VALUES ('1896', '4', '133');
INSERT INTO `t_role_menu` VALUES ('2029', '13', '116');
INSERT INTO `t_role_menu` VALUES ('2030', '13', '117');
INSERT INTO `t_role_menu` VALUES ('2031', '13', '118');
INSERT INTO `t_role_menu` VALUES ('2032', '13', '119');
INSERT INTO `t_role_menu` VALUES ('2033', '13', '120');
INSERT INTO `t_role_menu` VALUES ('2034', '13', '121');
INSERT INTO `t_role_menu` VALUES ('2035', '13', '122');
INSERT INTO `t_role_menu` VALUES ('2036', '13', '124');
INSERT INTO `t_role_menu` VALUES ('2037', '13', '127');
INSERT INTO `t_role_menu` VALUES ('2038', '13', '128');
INSERT INTO `t_role_menu` VALUES ('2039', '13', '129');
INSERT INTO `t_role_menu` VALUES ('2040', '13', '130');
INSERT INTO `t_role_menu` VALUES ('2041', '13', '131');
INSERT INTO `t_role_menu` VALUES ('2042', '13', '132');
INSERT INTO `t_role_menu` VALUES ('2043', '13', '133');
INSERT INTO `t_role_menu` VALUES ('2044', '13', '134');
INSERT INTO `t_role_menu` VALUES ('2045', '13', '135');
INSERT INTO `t_role_menu` VALUES ('2046', '13', '136');
INSERT INTO `t_role_menu` VALUES ('2047', '13', '137');
INSERT INTO `t_role_menu` VALUES ('2048', '13', '138');
INSERT INTO `t_role_menu` VALUES ('2049', '13', '139');
INSERT INTO `t_role_menu` VALUES ('2152', '1', '116');
INSERT INTO `t_role_menu` VALUES ('2153', '1', '117');
INSERT INTO `t_role_menu` VALUES ('2154', '1', '118');
INSERT INTO `t_role_menu` VALUES ('2155', '1', '119');
INSERT INTO `t_role_menu` VALUES ('2156', '1', '120');
INSERT INTO `t_role_menu` VALUES ('2157', '1', '121');
INSERT INTO `t_role_menu` VALUES ('2158', '1', '122');
INSERT INTO `t_role_menu` VALUES ('2159', '1', '124');
INSERT INTO `t_role_menu` VALUES ('2160', '1', '127');
INSERT INTO `t_role_menu` VALUES ('2161', '1', '128');
INSERT INTO `t_role_menu` VALUES ('2162', '1', '129');
INSERT INTO `t_role_menu` VALUES ('2163', '1', '130');
INSERT INTO `t_role_menu` VALUES ('2164', '1', '131');
INSERT INTO `t_role_menu` VALUES ('2165', '1', '132');
INSERT INTO `t_role_menu` VALUES ('2166', '1', '133');
INSERT INTO `t_role_menu` VALUES ('2167', '1', '134');
INSERT INTO `t_role_menu` VALUES ('2168', '1', '135');
INSERT INTO `t_role_menu` VALUES ('2169', '1', '136');
INSERT INTO `t_role_menu` VALUES ('2170', '1', '137');
INSERT INTO `t_role_menu` VALUES ('2171', '1', '138');
INSERT INTO `t_role_menu` VALUES ('2172', '1', '140');
INSERT INTO `t_role_menu` VALUES ('2173', '1', '141');
INSERT INTO `t_role_menu` VALUES ('2180', '3', '142');
INSERT INTO `t_role_menu` VALUES ('2181', '2', '120');
INSERT INTO `t_role_menu` VALUES ('2182', '2', '121');
INSERT INTO `t_role_menu` VALUES ('2183', '2', '122');
INSERT INTO `t_role_menu` VALUES ('2184', '2', '124');
INSERT INTO `t_role_menu` VALUES ('2185', '2', '131');
INSERT INTO `t_role_menu` VALUES ('2186', '2', '132');
INSERT INTO `t_role_menu` VALUES ('2187', '2', '133');
INSERT INTO `t_role_menu` VALUES ('2188', '2', '135');
INSERT INTO `t_role_menu` VALUES ('2189', '2', '136');
INSERT INTO `t_role_menu` VALUES ('2190', '2', '137');
INSERT INTO `t_role_menu` VALUES ('2191', '2', '138');
INSERT INTO `t_role_menu` VALUES ('2192', '2', '140');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限id',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1', '1', '2019-01-02 21:19:25', null);
INSERT INTO `t_role_permission` VALUES ('2', '1', '2', '2019-01-02 21:19:27', null);
INSERT INTO `t_role_permission` VALUES ('3', '1', '3', '2019-01-02 21:19:30', null);
INSERT INTO `t_role_permission` VALUES ('4', '1', '4', '2019-01-02 21:19:32', null);
INSERT INTO `t_role_permission` VALUES ('5', '2', '2', '2019-01-02 21:19:36', null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `real_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '管理员', 'admin', '2018-12-23 11:06:57', '2018-12-23 12:52:32');
INSERT INTO `t_user` VALUES ('2', 'guest', '游客', 'guest', '2019-01-02 21:16:30', '2019-01-02 21:16:32');
INSERT INTO `t_user` VALUES ('3', 'whc', '我', 'whc', '2019-07-21 11:46:45', '2019-07-21 11:46:48');
INSERT INTO `t_user` VALUES ('4', 'test', '测试', 'test', '2019-07-27 20:16:06', null);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色信息表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1');
INSERT INTO `t_user_role` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for t_user_test
-- ----------------------------
DROP TABLE IF EXISTS `t_user_test`;
CREATE TABLE `t_user_test` (
  `id` bigint(20) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `money` decimal(19,2) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_test
-- ----------------------------
INSERT INTO `t_user_test` VALUES ('1', '20', '20.00', 'admin', 'admin');
