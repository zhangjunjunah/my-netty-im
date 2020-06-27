/*
 Navicat Premium Data Transfer

 Source Server         : 39.99.129.88
 Source Server Type    : MySQL
 Source Server Version : 50634
 Source Host           : 39.99.129.88:3306
 Source Schema         : im

 Target Server Type    : MySQL
 Target Server Version : 50634
 File Encoding         : 65001

 Date: 27/06/2020 09:28:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for im_friend_rel
-- ----------------------------
DROP TABLE IF EXISTS `im_friend_rel`;
CREATE TABLE `im_friend_rel`
(
    `friend_rel`      bigint(20)                                             NOT NULL,
    `group_id`        bigint(20)                                             NULL DEFAULT NULL,
    `group_name`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `friend_id`       bigint(20)                                             NULL DEFAULT NULL,
    `friend_name`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `remark_name`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `parent_group_id` bigint(20)                                             NULL DEFAULT NULL,
    `user_id`         bigint(20)                                             NULL DEFAULT NULL,
    PRIMARY KEY (`friend_rel`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for im_message
-- ----------------------------
DROP TABLE IF EXISTS `im_message`;
CREATE TABLE `im_message`
(
    `message_id`       bigint(20)                                              NOT NULL,
    `message_type`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `message_content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `message_sender`   bigint(20)                                              NULL DEFAULT NULL,
    `message_receiver` bigint(20)                                              NULL DEFAULT NULL,
    `send_time`        DATE COLLATE utf8_general_ci                            NULL DEFAULT NULL,
    `send_status`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for im_user
-- ----------------------------
DROP TABLE IF EXISTS `im_user`;
CREATE TABLE `im_user`
(
    `user_id`     bigint(20)                                              NOT NULL,
    `user_name`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `password`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `nick_name`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `avatar_src`  varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `phone_num`   varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `mail`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
    `create_time` date                                                    NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Compact;
