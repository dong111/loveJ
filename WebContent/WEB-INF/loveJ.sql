/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : lovej_s

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2012-04-18 18:07:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `lovej_article`
-- ----------------------------
DROP TABLE IF EXISTS `lovej_article`;
CREATE TABLE `lovej_article` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`content`  longtext NOT NULL ,
`modifyTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`postTime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`quote`  varchar(255) NULL DEFAULT NULL ,
`status`  varchar(10) NOT NULL ,
`summary`  longtext NULL ,
`title`  varchar(255) NOT NULL ,
`view`  int(11) NOT NULL ,
`categoryId`  int(11) NOT NULL ,
`trash`  bit(1) NULL DEFAULT NULL ,
`topTime`  timestamp NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`categoryId`) REFERENCES `lovej_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FKBCEFE2EF751C3FCC` (`categoryId`) USING BTREE 
);

-- ----------------------------
-- Table structure for `lovej_attach`
-- ----------------------------
DROP TABLE IF EXISTS `lovej_attach`;
CREATE TABLE `lovej_attach` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`articleId`  int(11) NOT NULL ,
`description`  varchar(255) NULL DEFAULT NULL ,
`url`  varchar(255) NOT NULL ,
`download`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`articleId`) REFERENCES `lovej_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `articleId` (`articleId`) USING BTREE 
);

-- ----------------------------
-- Table structure for `lovej_category`
-- ----------------------------
DROP TABLE IF EXISTS `lovej_category`;
CREATE TABLE `lovej_category` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`createTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`description`  varchar(255) NULL DEFAULT NULL ,
`name`  varchar(50) NOT NULL ,
`priority`  int(11) NOT NULL ,
`trash`  bit(1) NOT NULL ,
`type`  varchar(10) NOT NULL ,
`parentId`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`parentId`) REFERENCES `lovej_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK2D4E662594DA44A0` (`parentId`) USING BTREE 
);

-- ----------------------------
-- Table structure for `lovej_comment`
-- ----------------------------
DROP TABLE IF EXISTS `lovej_comment`;
CREATE TABLE `lovej_comment` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`content`  longtext NOT NULL ,
`email`  varchar(255) NOT NULL ,
`name`  varchar(50) NOT NULL ,
`postIP`  varchar(20) NOT NULL ,
`postTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`site`  varchar(255) NULL DEFAULT NULL ,
`status`  varchar(20) NOT NULL ,
`articleId`  int(11) NOT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`articleId`) REFERENCES `lovej_article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK213D045839BDCCE8` (`articleId`) USING BTREE 
);

-- ----------------------------
-- Table structure for `lovej_contact`
-- ----------------------------
DROP TABLE IF EXISTS `lovej_contact`;
CREATE TABLE `lovej_contact` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`content`  longtext NOT NULL ,
`email`  varchar(255) NOT NULL ,
`name`  varchar(50) NOT NULL ,
`postIP`  varchar(20) NOT NULL ,
`postTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`site`  varchar(255) NULL DEFAULT NULL ,
`status`  varchar(10) NOT NULL ,
`trash`  bit(1) NOT NULL ,
PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for `lovej_link`
-- ----------------------------
DROP TABLE IF EXISTS `lovej_link`;
CREATE TABLE `lovej_link` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`createTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`description`  varchar(255) NULL DEFAULT NULL ,
`name`  varchar(50) NOT NULL ,
`site`  varchar(255) NULL DEFAULT NULL ,
`status`  varchar(10) NOT NULL ,
`trash`  bit(1) NOT NULL ,
PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for `lovej_role`
-- ----------------------------
DROP TABLE IF EXISTS `lovej_role`;
CREATE TABLE `lovej_role` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`createTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`description`  varchar(255) NULL DEFAULT NULL ,
`name`  varchar(20) NOT NULL ,
PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for `lovej_site_config`
-- ----------------------------
DROP TABLE IF EXISTS `lovej_site_config`;
CREATE TABLE `lovej_site_config` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`about`  varchar(255) NOT NULL ,
`contactDescription`  longtext NULL ,
`icp`  varchar(50) NULL DEFAULT NULL ,
`name`  varchar(50) NOT NULL ,
`url`  varchar(255) NOT NULL ,
PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for `lovej_user`
-- ----------------------------
DROP TABLE IF EXISTS `lovej_user`;
CREATE TABLE `lovej_user` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`age`  int(11) NULL DEFAULT NULL ,
`birthday`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`email`  varchar(255) NULL DEFAULT NULL ,
`gender`  bit(1) NOT NULL ,
`nickname`  varchar(20) NOT NULL ,
`password`  varchar(32) NOT NULL ,
`qq`  int(11) NULL DEFAULT NULL ,
`username`  varchar(20) NOT NULL ,
PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for `lovej_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `lovej_user_role`;
CREATE TABLE `lovej_user_role` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`roleId`  int(11) NOT NULL ,
`userId`  int(11) NOT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`userId`) REFERENCES `lovej_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`roleId`) REFERENCES `lovej_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK33657023724825CC` (`roleId`) USING BTREE ,
INDEX `FK336570231772E9AC` (`userId`) USING BTREE 
);