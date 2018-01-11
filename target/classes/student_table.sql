/*
Navicat MySQL Data Transfer

Source Server         : mcourse
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-14 17:15:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student_table
-- ----------------------------
DROP TABLE IF EXISTS `student_table`;
CREATE TABLE `student_table` (
  `studentname` varchar(11) NOT NULL,
  `studentsex` varchar(11) DEFAULT NULL,
  `studenage` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_table
-- ----------------------------
INSERT INTO `student_table` VALUES ('牛牛', '1', '23');
INSERT INTO `student_table` VALUES ('敏哥', '1', '24');
INSERT INTO `student_table` VALUES ('张三', '1', '28');
INSERT INTO `student_table` VALUES ('丹丹', '2', '20');
INSERT INTO `student_table` VALUES ('小丽', '2', '25');
