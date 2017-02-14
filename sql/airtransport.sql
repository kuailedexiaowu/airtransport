/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : airtransport

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-02-14 14:24:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for air_order
-- ----------------------------
DROP TABLE IF EXISTS `air_order`;
CREATE TABLE `air_order` (
  `id` varchar(50) NOT NULL,
  `send_id` varchar(50) NOT NULL,
  `receiver_id` varchar(50) NOT NULL,
  `good_id` varchar(50) NOT NULL,
  `cabin_id` varchar(50) NOT NULL,
  `pay` varchar(10) NOT NULL,
  `status` varchar(10) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of air_order
-- ----------------------------
INSERT INTO `air_order` VALUES ('c2d2a212-0292-42f2-8212-720f28292226', '41002715-9f45-4512-d4d4-5ce55333wedf', 'b7902230-5789-4224-b4bf-05572118607b', '11213115-2141-2112-d1d1-5c1514131e1f', '31332715-3f45-3512-d3d3-5ce55443wedf', '收货方', '运输中', '2017-02-13 14:27:29');
INSERT INTO `air_order` VALUES ('c3d3a916-0e92-49ff-861d-7a0f5869b576', '63a13f8f-3bff-4eda-aed9-0de21566730d', 'b7902230-5789-4224-b4bf-05572118607b', '21233715-2f45-2512-d2d3-5c2524432edf', '31332715-3f45-3512-d3d3-5ce55443wedf', '发货方', '已发货', '2017-02-08 15:54:19');

-- ----------------------------
-- Table structure for cabin
-- ----------------------------
DROP TABLE IF EXISTS `cabin`;
CREATE TABLE `cabin` (
  `id` varchar(50) NOT NULL,
  `start` varchar(20) NOT NULL,
  `end` varchar(20) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cabin
-- ----------------------------
INSERT INTO `cabin` VALUES ('31332715-3f45-3512-d3d3-5ce55443wedf', '兰州', '北京', '运输中');
INSERT INTO `cabin` VALUES ('96d399d1-ecfa-48ac-90c1-6b23c29b6891', '兰州', '上海', '可用');
INSERT INTO `cabin` VALUES ('cbb915a3-53e8-4a5f-bbda-5e1d6ee2214b', '兰州', '洛阳', '可用');

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `code` varchar(10) NOT NULL,
  `address` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('35002750-9f05-45c1-b1d15ce55302e4f2 ', '李继虎', '15555555555', '730050', '甘肃省兰州市彭家坪区10000号');
INSERT INTO `client` VALUES ('41002715-9f45-4512-d4d4-5ce55333wedf', '惠文华', '16666666666', '730050', '甘肃省武威市古浪县');
INSERT INTO `client` VALUES ('62b86329-cee7-4559-be8b-6fc68a18114e', '韩亚蛟', '133333333', '730050', '甘肃省');
INSERT INTO `client` VALUES ('63a13f8f-3bff-4eda-aed9-0de21566730d', '楼畅', '1111111111', '730051', '浙江省金华市');
INSERT INTO `client` VALUES ('b7902230-5789-4224-b4bf-05572118607b', '刘继帅', '1222222222', '730051', '甘肃省金昌市');

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `id` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  `size` float(11,0) NOT NULL,
  `weight` float(11,0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES ('11213115-2141-2112-d1d1-5c1514131e1f', '衣服', '1', '1');
INSERT INTO `good` VALUES ('21233715-2f45-2512-d2d3-5c2524432edf', '水果', '1', '5');

-- ----------------------------
-- Table structure for order_path
-- ----------------------------
DROP TABLE IF EXISTS `order_path`;
CREATE TABLE `order_path` (
  `id` varchar(50) NOT NULL,
  `order_id` varchar(50) NOT NULL,
  `path_id` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_path
-- ----------------------------
INSERT INTO `order_path` VALUES ('46d499d1-e4fa-44ac-94c1-6423c49b4891', 'c3d3a916-0e92-49ff-861d-7a0f5869b576', '96d388d1-zsad-48ac-qwea-6b23c2566891');
INSERT INTO `order_path` VALUES ('56285b82-ac9b-4af4-90c0-90ae9f8df478', 'c3d3a916-0e92-49ff-861d-7a0f5869b576', '6a377cc6-3692-4d0a-aec4-7e736455ed45');
INSERT INTO `order_path` VALUES ('997e314d-c7b1-4013-ad86-e4439f02bc07', 'c2d2a212-0292-42f2-8212-720f28292226', '1c2edca2-8d18-4271-afb2-22519207e34a');

-- ----------------------------
-- Table structure for path
-- ----------------------------
DROP TABLE IF EXISTS `path`;
CREATE TABLE `path` (
  `id` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sort` tinyint(4) NOT NULL,
  `arrive_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of path
-- ----------------------------
INSERT INTO `path` VALUES ('1c2edca2-8d18-4271-afb2-22519207e34a', '兰州', '1', '2017-02-10 16:56:58');
INSERT INTO `path` VALUES ('6a377cc6-3692-4d0a-aec4-7e736455ed45', '西安', '2', '2017-02-10 08:00:00');
INSERT INTO `path` VALUES ('96d388d1-zsad-48ac-qwea-6b23c2566891', '兰州', '1', '2017-02-09 16:05:16');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13240229', '941E946E4E5C691971E797EF2496BE3C');
