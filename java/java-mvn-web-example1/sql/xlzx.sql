/*
 Navicat Premium Data Transfer

 Source Server         : localhost-3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost
 Source Database       : xlzx

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : utf-8

 Date: 09/06/2020 19:36:56 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `xtwh_bmxx`
-- ----------------------------
DROP TABLE IF EXISTS `xtwh_bmxx`;
CREATE TABLE `xtwh_bmxx` (
  `bmid` varchar(40) DEFAULT NULL COMMENT '部门id',
  `bmbh` varchar(100) DEFAULT NULL COMMENT '部门编号',
  `bmmc` varchar(200) DEFAULT NULL COMMENT '部门名称',
  `sjbmid` varchar(40) DEFAULT NULL COMMENT '所属部门id ',
  `bmms` varchar(200) DEFAULT NULL COMMENT '部门描述',
  `lrrq` datetime DEFAULT NULL COMMENT '录入时间',
  `lrrid` int DEFAULT NULL COMMENT '录入人id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `xtwh_bmxx`
-- ----------------------------
BEGIN;
INSERT INTO `xtwh_bmxx` VALUES ('0001', '00000001', '运营部', '', '运营部', '2020-09-06 10:24:08', '1'), ('0002', '00000002', '人事部', '', '人事部', '2020-09-06 10:42:56', '1');
COMMIT;

-- ----------------------------
--  Table structure for `xtwh_jsqx`
-- ----------------------------
DROP TABLE IF EXISTS `xtwh_jsqx`;
CREATE TABLE `xtwh_jsqx` (
  `jsid` int NOT NULL DEFAULT '0' COMMENT '角色id',
  `zyid` varchar(20) NOT NULL DEFAULT 'null' COMMENT '资源id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色权限';

-- ----------------------------
--  Records of `xtwh_jsqx`
-- ----------------------------
BEGIN;
INSERT INTO `xtwh_jsqx` VALUES ('7', '01'), ('7', '0102'), ('7', '010201'), ('7', '010202'), ('7', '010203'), ('5', '01'), ('5', '0103'), ('5', '010301'), ('5', '010302'), ('5', '010303'), ('5', '0104'), ('5', '010401'), ('5', '010402'), ('5', '010403'), ('5', '010404'), ('6', '01'), ('6', '0101'), ('6', '010101'), ('6', '010102'), ('6', '010103'), ('6', '010104');
COMMIT;

-- ----------------------------
--  Table structure for `xtwh_jsxx`
-- ----------------------------
DROP TABLE IF EXISTS `xtwh_jsxx`;
CREATE TABLE `xtwh_jsxx` (
  `jsid` int NOT NULL AUTO_INCREMENT,
  `jsbh` varchar(40) DEFAULT NULL,
  `jsmc` varchar(100) DEFAULT NULL,
  `jsms` varchar(200) DEFAULT NULL,
  `lrrq` datetime DEFAULT NULL,
  `lrrid` int DEFAULT NULL,
  PRIMARY KEY (`jsid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `xtwh_jsxx`
-- ----------------------------
BEGIN;
INSERT INTO `xtwh_jsxx` VALUES ('5', '00000001', '权限管理员', '管理资源、角色信息', '2020-09-06 10:27:43', '1'), ('6', '00000002', '用户管理员', '管理后台登录用户', '2020-09-06 10:28:26', '1'), ('7', '00000003', '部门管理', '部门管理', '2020-09-06 10:40:34', '1');
COMMIT;

-- ----------------------------
--  Table structure for `xtwh_ry_jsxx`
-- ----------------------------
DROP TABLE IF EXISTS `xtwh_ry_jsxx`;
CREATE TABLE `xtwh_ry_jsxx` (
  `ryid` int NOT NULL COMMENT '人员id',
  `jsid` int NOT NULL COMMENT '角色id',
  PRIMARY KEY (`ryid`,`jsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='人员角色信息';

-- ----------------------------
--  Records of `xtwh_ry_jsxx`
-- ----------------------------
BEGIN;
INSERT INTO `xtwh_ry_jsxx` VALUES ('5', '5'), ('8', '6'), ('9', '7'), ('10', '6'), ('10', '7');
COMMIT;

-- ----------------------------
--  Table structure for `xtwh_ryxx`
-- ----------------------------
DROP TABLE IF EXISTS `xtwh_ryxx`;
CREATE TABLE `xtwh_ryxx` (
  `ryid` int NOT NULL AUTO_INCREMENT,
  `rybh` varchar(20) DEFAULT NULL,
  `ryzh` varchar(50) DEFAULT NULL COMMENT '人员登录账号',
  `mm` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `rymc` varchar(50) DEFAULT NULL COMMENT '人员姓名',
  `lxdh` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `ssbmid` varchar(40) DEFAULT NULL COMMENT '所属部门id',
  `sfmr` tinyint(1) DEFAULT '0' COMMENT '是否默认，默认人员不能删除',
  `bz` varchar(200) DEFAULT NULL COMMENT '备注信息',
  `lrrid` int DEFAULT NULL,
  `lrrq` datetime DEFAULT NULL,
  `yxbz` tinyint(1) DEFAULT '1' COMMENT '有效标识 1有效 0无效',
  PRIMARY KEY (`ryid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `xtwh_ryxx`
-- ----------------------------
BEGIN;
INSERT INTO `xtwh_ryxx` VALUES ('1', '00000001', 'admin', 'e5993fa7c613986082b5cd6a62e91119', '系统管理员', null, null, '1', '系统管理员', null, null, '1'), ('5', '00000002', 'zhangsan', 'e5993fa7c613986082b5cd6a62e91119', '张三', '13422223333', '0001', '0', '运用中心张三', '1', '2020-09-06 10:26:59', '1'), ('8', '00000003', 'lisi', 'e5993fa7c613986082b5cd6a62e91119', '李四', '13444445555', '0002', '0', '李四 管理用户', '1', '2020-09-06 10:43:48', '1'), ('9', '00000004', 'wangwu', 'e5993fa7c613986082b5cd6a62e91119', '王五', '13466667777', '0002', '0', '王五 管理部门信息', '1', '2020-09-06 10:44:13', '1'), ('10', '00000005', 'zhaoliu', 'e5993fa7c613986082b5cd6a62e91119', '赵六', '13477778888', '0002', '0', '赵六 管理用户和部门', '1', '2020-09-06 10:45:13', '1');
COMMIT;

-- ----------------------------
--  Table structure for `xtwh_zyxx`
-- ----------------------------
DROP TABLE IF EXISTS `xtwh_zyxx`;
CREATE TABLE `xtwh_zyxx` (
  `zyid` varchar(20) NOT NULL DEFAULT 'null' COMMENT '资源id',
  `zymc` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `sjzyid` varchar(20) DEFAULT NULL COMMENT '上级资源id',
  `zylj` varchar(500) DEFAULT NULL COMMENT '资源链接',
  `zytb` varchar(20) DEFAULT NULL,
  `zyms` varchar(200) DEFAULT NULL COMMENT '资源描述',
  `zylx` bigint DEFAULT NULL COMMENT '资源类型0菜单1功能',
  `sfxs` bigint DEFAULT '1' COMMENT '是否显示1显示0不显示',
  `zypx` bigint DEFAULT NULL,
  PRIMARY KEY (`zyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='资源信息';

-- ----------------------------
--  Records of `xtwh_zyxx`
-- ----------------------------
BEGIN;
INSERT INTO `xtwh_zyxx` VALUES ('01', '系统管理', '', '#', null, '系统管理', '0', '1', '99'), ('0101', '用户管理', '01', '/xtwh/rygl', '', '用户管理', '0', '1', '1'), ('010101', '新增用户', '0101', '/xtwh/rygl/addRyxx', '', '新增用户', '1', '1', '1'), ('010102', '编辑用户', '0101', '/xtwh/rygl/updRyxx', '', '编辑用户', '1', '1', '2'), ('010103', '删除用户', '0101', '/xtwh/rygl/delRyxx', '', '删除用户', '1', '1', '3'), ('010104', '用户授权', '0101', '/xtwh/rygl/sqRyxx', '', '用户授权角色', '1', '1', '4'), ('010105', '重置用户密码', '0101', '/xtwh/rygl/resetPwd', '', '重置用户密码', '1', '1', '5'), ('010106', '禁用用户', '0101', '/xtwh/rygl/jyRy', '', '停用用户', '1', '1', '6'), ('010107', '启用用户', '0101', '/xtwh/rygl/qyRy', '', '启用用户', '1', '1', '7'), ('0102', '部门管理', '01', '/xtwh/bmgl', '', '部门管理', '0', '1', '2'), ('010201', '新增部门', '0102', '/xtwh/bmgl/addBm', '', '新增部门', '1', '1', '1'), ('010202', '编辑部门', '0102', '/xtwh/bmgl/updBm', '', '编辑部门', '1', '1', '2'), ('010203', '删除部门', '0102', '/xtwh/bmgl/delBm', '', '删除部门', '1', '1', '3'), ('0103', '资源管理', '01', '/xtwh/zygl', null, '资源管理', '0', '1', '3'), ('010301', '新增资源', '0103', '/xtwh/zygl/addZy', '', '新增资源', '1', '1', '1'), ('010302', '编辑资源', '0103', '/xtwh/zygl/updZy', '', '编辑资源', '0', '1', '2'), ('010303', '删除资源', '0103', '/xtwh/zygl/delZy', '', '删除资源', '1', '1', '3'), ('0104', '角色管理', '01', '/xtwh/jsgl', null, '角色管理', '0', '1', '4'), ('010401', '新增角色', '0104', '/xtwh/jsgl/addJs', '', '新增角色', '1', '1', '1'), ('010402', '编辑角色', '0104', '/xtwh/jsgl/updJs', '', '编辑角色', '1', '1', '2'), ('010403', '删除角色', '0104', '/xtwh/jsgl/delJs', '', '删除角色', '1', '1', '3'), ('010404', '角色授权', '0104', '/xtwh/jsgl/jssq', '', '角色授权', '1', '1', '4');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
