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

 Date: 09/02/2020 23:59:27 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `xtwh_ryxx`
-- ----------------------------
DROP TABLE IF EXISTS `xtwh_ryxx`;
CREATE TABLE `xtwh_ryxx` (
  `ryid` int NOT NULL AUTO_INCREMENT,
  `rydm` varchar(50) DEFAULT NULL COMMENT '账号',
  `rymc` varchar(100) DEFAULT NULL COMMENT '名称',
  `sjjgid` varchar(100) DEFAULT NULL COMMENT '所属部门id',
  `mm` varchar(100) DEFAULT NULL COMMENT '密码',
  `lxdh` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `bz` varchar(200) DEFAULT NULL COMMENT '备注',
  `lrrid` int DEFAULT NULL COMMENT '录入人ID',
  `lrsj` varchar(255) DEFAULT NULL COMMENT '录入时间',
  `yxbz` tinyint(1) DEFAULT NULL COMMENT '有效标识 1 有效 0无效',
  PRIMARY KEY (`ryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
INSERT INTO `xtwh_zyxx` VALUES ('01', '系统管理', null, '#', null, '系统管理', '0', '1', '99'), ('0101', '用户管理', '01', '/xtwh/rygl', '', '用户管理', '0', '1', '1'), ('0102', '部门管理', '01', '/xtwh/jggl', null, '部门管理', '0', '1', '2'), ('0103', '资源管理', '01', '/xtwh/zygl', null, '资源管理', '0', '1', '3'), ('0104', '角色管理', '01', '/xtwh/jsgl', null, '角色管理', '0', '1', '4');
COMMIT;

-- ----------------------------
--  Table structure for `yygl_jgxx`
-- ----------------------------
DROP TABLE IF EXISTS `yygl_jgxx`;
CREATE TABLE `yygl_jgxx` (
  `jgid` varchar(40) NOT NULL DEFAULT '0' COMMENT '机构id',
  `jgdm` varchar(100) DEFAULT NULL COMMENT '机构代码',
  `jgmc` varchar(200) DEFAULT NULL COMMENT '机构名称',
  `jgjb` bigint DEFAULT NULL COMMENT '机构级别1公司、2部门',
  `sjjgid` varchar(40) DEFAULT NULL COMMENT '所属机构id 0为平台提供者',
  `lrrq` datetime DEFAULT NULL COMMENT '录入时间',
  `lrrid` varchar(100) DEFAULT NULL COMMENT '录入人代码',
  PRIMARY KEY (`jgid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='机构管理';

SET FOREIGN_KEY_CHECKS = 1;
