/*
 Navicat Premium Data Transfer

 Source Server         : 47.104.189.223
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 47.104.189.223:3306
 Source Schema         : shangcg

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 08/11/2020 12:20:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_t
-- ----------------------------
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(1024) DEFAULT NULL,
  `password` varchar(1024) DEFAULT NULL,
  `age` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_t
-- ----------------------------
BEGIN;
INSERT INTO `user_t` VALUES (1, 'shang', '123', 20);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
