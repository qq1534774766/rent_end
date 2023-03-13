/*
Navicat MySQL Data Transfer

Source Server         : 120.76.137.145_3306
Source Server Version : 50735
Source Host           : 120.76.137.145:3306
Source Database       : rent

Target Server Type    : MYSQL
Target Server Version : 50735
File Encoding         : 65001

Date: 2022-10-30 17:53:51
*/

SET FOREIGN_KEY_CHECKS=0;
use rent;

-- ----------------------------
-- Table structure for archi_b_building
-- ----------------------------
DROP TABLE IF EXISTS `archi_b_building`;
CREATE TABLE `archi_b_building` (
  `BUILDING_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '楼房ID',
  `NAME` varchar(90) NOT NULL COMMENT '楼房名称',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '地址',
  `CREATED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`BUILDING_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='楼房表';

-- ----------------------------
-- Records of archi_b_building
-- ----------------------------
INSERT INTO `archi_b_building` VALUES ('1', 'A号楼', '广东海珠区', '2021-11-20 14:00:00', '2021-11-20 14:00:00');
INSERT INTO `archi_b_building` VALUES ('2', 'B号楼', '清远英德', '2021-11-20 14:14:09', '2021-11-20 14:14:09');
INSERT INTO `archi_b_building` VALUES ('3', 'C号楼', '广东佛山', '2021-11-20 14:46:55', '2021-11-20 14:46:55');
INSERT INTO `archi_b_building` VALUES ('4', 'D号楼', '广东江门', '2021-11-20 14:48:26', '2021-11-20 14:48:26');
INSERT INTO `archi_b_building` VALUES ('5', 'F号楼', '广东清远', '2021-11-20 14:49:15', '2021-11-20 14:49:15');

-- ----------------------------
-- Table structure for archi_r_room
-- ----------------------------
DROP TABLE IF EXISTS `archi_r_room`;
CREATE TABLE `archi_r_room` (
  `ROOM_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '房屋ID',
  `BUILDING_ID` int(11) NOT NULL COMMENT '楼房ID',
  `HOUSE_NUMBER` varchar(90) NOT NULL COMMENT '门牌号',
  `CREATED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ROOM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='房屋表';

-- ----------------------------
-- Records of archi_r_room
-- ----------------------------
INSERT INTO `archi_r_room` VALUES ('1', '1', '101', '2021-11-20 15:45:05', '2021-11-20 15:45:05');
INSERT INTO `archi_r_room` VALUES ('2', '3', '102', '2021-11-20 15:47:26', '2021-11-20 15:47:26');
INSERT INTO `archi_r_room` VALUES ('5', '2', '101', '2021-11-20 16:00:30', '2021-11-20 16:00:30');
INSERT INTO `archi_r_room` VALUES ('6', '2', '102', '2021-11-20 16:00:41', '2021-11-20 16:00:41');
INSERT INTO `archi_r_room` VALUES ('7', '1', '601', '2021-11-25 18:34:49', '2021-11-25 18:34:49');

-- ----------------------------
-- Table structure for renting
-- ----------------------------
DROP TABLE IF EXISTS `renting`;
CREATE TABLE `renting` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '租用ID',
  `USER_ID` int(11) NOT NULL COMMENT '用户ID',
  `ROOM_ID` int(11) NOT NULL COMMENT '房屋ID',
  `STOP_TIME` datetime DEFAULT NULL COMMENT '停租时间',
  `CREATED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `ROOM_ID` (`ROOM_ID`),
  CONSTRAINT `renting_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `u_user` (`USER_ID`),
  CONSTRAINT `renting_ibfk_2` FOREIGN KEY (`ROOM_ID`) REFERENCES `archi_r_room` (`ROOM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='租房关系表';

-- ----------------------------
-- Records of renting
-- ----------------------------
INSERT INTO `renting` VALUES ('1', '1', '1', '2021-11-10 17:03:49', '2021-11-20 17:04:34', '2021-11-20 17:09:09');
INSERT INTO `renting` VALUES ('2', '2', '2', null, '2021-11-20 17:16:40', '2021-11-20 17:16:40');
INSERT INTO `renting` VALUES ('3', '1', '1', '2021-11-20 16:29:44', '2021-11-20 17:28:54', '2021-11-20 17:29:48');
INSERT INTO `renting` VALUES ('4', '4', '6', null, '2021-11-24 10:32:13', '2021-11-24 10:32:13');

-- ----------------------------
-- Table structure for u_role
-- ----------------------------
DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `NAME` varchar(90) NOT NULL COMMENT '角色名',
  `DESCRIPTION` varchar(255) NOT NULL COMMENT '权限描述',
  `CREATED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of u_role
-- ----------------------------
INSERT INTO `u_role` VALUES ('1', 'client', '客户', '2021-11-11 05:46:13', '2021-11-25 20:51:08');
INSERT INTO `u_role` VALUES ('2', 'manage', '房东', '2021-11-11 07:24:48', '2021-11-11 11:19:58');
INSERT INTO `u_role` VALUES ('3', 'admin', '超级用户', '2021-11-11 07:24:48', '2021-11-25 20:51:09');

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `ROLE_ID` int(11) NOT NULL DEFAULT '2' COMMENT '角色ID',
  `USERNAME` varchar(90) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(255) NOT NULL COMMENT '用户密码',
  `SEX` varchar(32) DEFAULT '2' COMMENT '性别',
  `NAME` varchar(90) NOT NULL COMMENT '姓名',
  `PHONE_NUMBER` varchar(90) NOT NULL COMMENT '电话号码',
  `IDENTITY` varchar(32) DEFAULT NULL COMMENT '身份证号码',
  `LOCKED` varchar(1) DEFAULT '0' COMMENT '锁定状态',
  `ENABLE` varchar(1) DEFAULT '1' COMMENT '可用状态',
  `CREATED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`),
  KEY `ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `u_user_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `u_role` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='用户名表';

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('1', '2', 'admin', '$2a$10$UCfJEivS0SSvoVFvua5vnelyoF9TNBp7MzWfp0J5sXBu.v1Ta20aG', '0', 'admin', '18127226035', '440103199003071275', '0', '1', '2021-11-12 22:39:54', '2021-11-24 10:09:45');
INSERT INTO `u_user` VALUES ('2', '3', 'root', '$2a$10$nKboipGMRvFD3ZO60xZpC.qsxSQ6NbBTxHzW6qMDR3sm.aIhplMnC', '1', 'cola', '12345678912', '440103199003071419', '0', '1', '2021-11-13 00:12:27', '2021-12-15 19:14:03');
INSERT INTO `u_user` VALUES ('3', '2', '广财大', '$2a$10$2C5.bjK3ufKTR0rzaOlbm.BRPqATMG6f9j8dM5ZwIEZL/lvKbjwxy', '2', 'aaa广财', '18127226035', '110101201303079026', '0', '1', '2021-11-13 01:58:53', '2021-11-24 10:09:19');
INSERT INTO `u_user` VALUES ('4', '2', 'aguo', '$2a$10$tsN18eAqc2fv/nMgznavF..MfW1QKQ8DvHKFKI6PTbrvVukM9UTwm', '1', 'aguo', '18127226035', null, '0', '1', '2021-11-21 13:47:42', '2021-11-24 10:09:45');
INSERT INTO `u_user` VALUES ('5', '2', '123456789', '$2a$10$A9Daw5Ya.sbHlzXot.JexueT7JM/zJoouF.WAvyhqk1bjzau4NS4O', '2', '123456789', '12345678911', null, '0', '1', '2021-11-24 14:58:35', '2021-11-24 14:58:35');
INSERT INTO `u_user` VALUES ('6', '3', 'aguo22', '$2a$10$811QTKTaKEml6ane632p6.wzPWqsEAkDjDMegF0lxMPdD137WuIVu', '1', 'aguo22', '18127226035', '441881200008317418', '0', '1', '2021-11-25 21:10:55', '2021-11-25 21:10:55');
INSERT INTO `u_user` VALUES ('7', '3', 'aguo1', '$2a$10$ML.fC7vpZeETLDG9Vc3SeuW0w3B6qDBySnjhxziqPaUTMqCYvlBtG', '1', 'aguo1', '18127226035', '441881200008317418', '0', '1', '2021-11-25 21:11:19', '2021-11-25 21:11:19');
INSERT INTO `u_user` VALUES ('8', '3', 'aguo3', '$2a$10$WZnYicjLCLwlgdQIyZ51KuWJrInfzakbs6G12XH1E4pemdbye/4jK', '1', 'aguo3', '18127226035', '441881200008317418', '0', '1', '2021-11-25 21:11:32', '2021-11-25 21:11:32');
INSERT INTO `u_user` VALUES ('9', '3', 'aguo4', '$2a$10$2bo6/mTaJh9l/eCmjZZEru6WSZ.d7ADSJyjHxQv.o.DGNgcnZ59Am', '1', 'aguo4', '18127226035', '441881200008317418', '0', '1', '2021-11-25 21:11:38', '2021-11-25 21:11:38');
INSERT INTO `u_user` VALUES ('10', '3', 'aguo6', '$2a$10$VVMycYH5Ggor2dUAf30UJOkF3mrzmxftPG2Aci6RK78wfrvAbMbp2', '1', 'aguo6', '18127226035', '441881200008317418', '0', '1', '2021-11-25 21:12:52', '2021-11-25 21:12:52');
INSERT INTO `u_user` VALUES ('11', '3', 'aguo7', '$2a$10$YH2q/cuXpgQgwthRhAhawu.JbXXNvTjPLVbviXdOVmYJH4CbEUr0W', '1', 'aguo7', '18127226035', '441881200008317418', '0', '1', '2021-11-25 21:14:35', '2021-11-25 21:14:35');
INSERT INTO `u_user` VALUES ('15', '3', 'aguozi', '$2a$10$Lfi21g5Yo./5yUldFIyJXeMY6Hcd0XEKWhwwT9LEYVOfH/P3o.jgu', '1', 'aguozi', '18127226035', '', '1', '1', '2021-11-25 21:26:31', '2021-11-25 21:26:31');
INSERT INTO `u_user` VALUES ('16', '3', 'aguozi2', '$2a$10$V/3h6zNBMnGZffTA0XHDpOFgqj6qKSQaxRK1oe8sB5m6pAC2vOQNC', '1', '温桥港', '18127226035', '', '0', '0', '2021-11-25 21:28:35', '2021-11-25 21:51:06');
INSERT INTO `u_user` VALUES ('18', '2', 'peiqi2', '$2a$10$jGZhTKWhbSFykwGrf7UAOuHAlGMngzWlPDRnGCxILSWNLg3HoRpRS', '1', 'pig', '18211582052', '', '0', '1', '2021-11-29 21:20:50', '2021-11-29 21:20:50');
INSERT INTO `u_user` VALUES ('20', '2', 'amo1', '$2a$10$qquXZSmAPp2IkNBUbe2gIO6kBTL3wv4ody1I90T.2eI59NiczQNzu', '0', 'amo', '12345678912', '', '1', '1', '2021-12-16 20:01:49', '2021-12-16 20:01:49');
INSERT INTO `u_user` VALUES ('21', '2', 'fang', '$2a$10$vpbX4Yx9jTbFhIS0/9v/0.AHOtyyRVOe9oGOy4Uos9Do/l/292Wq.', '2', 'fang', '13015698048', null, '0', '1', '2022-04-08 13:49:26', '2022-04-08 13:49:26');
