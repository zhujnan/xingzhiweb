/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : xingzhi

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-05-26 16:37:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `s#` int(11) NOT NULL,
  `sname` varchar(255) DEFAULT NULL,
  `sage` date DEFAULT NULL,
  `ssex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`s#`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '赵雷', '1990-01-01', '男');
INSERT INTO `student` VALUES ('2', '钱电', '1990-12-21', '男');
INSERT INTO `student` VALUES ('3', '孙风', '1990-05-20', '男');
INSERT INTO `student` VALUES ('4', '李云', '1990-08-06', '男');
INSERT INTO `student` VALUES ('5', '周梅', '1991-12-01', '女');
INSERT INTO `student` VALUES ('6', '吴兰', '1992-03-01', '女');
INSERT INTO `student` VALUES ('7', '郑竹', '1989-07-01', '女');
INSERT INTO `student` VALUES ('8', '王菊', '1990-01-20', '女');


-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `c#` int(11) NOT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `t#` int(11) DEFAULT NULL,
  PRIMARY KEY (`c#`),
  KEY `t#` (`t#`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`t#`) REFERENCES `teacher` (`t#`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '语文', '2');
INSERT INTO `course` VALUES ('2', '语文', '1');
INSERT INTO `course` VALUES ('3', '语文', '3');


-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `t#` int(11) NOT NULL,
  `tname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`t#`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '张三');
INSERT INTO `teacher` VALUES ('2', '李四');
INSERT INTO `teacher` VALUES ('3', '王五');





-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `s#` int(11) DEFAULT NULL,
  `c#` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  KEY `s#` (`s#`),
  KEY `c#` (`c#`),
  CONSTRAINT `sc_ibfk_1` FOREIGN KEY (`s#`) REFERENCES `student` (`s#`),
  CONSTRAINT `sc_ibfk_2` FOREIGN KEY (`c#`) REFERENCES `course` (`c#`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('1', '1', '90');
INSERT INTO `sc` VALUES ('1', '2', '80');
INSERT INTO `sc` VALUES ('1', '3', '90');
INSERT INTO `sc` VALUES ('2', '1', '70');
INSERT INTO `sc` VALUES ('2', '2', '60');
INSERT INTO `sc` VALUES ('2', '3', '80');
INSERT INTO `sc` VALUES ('3', '1', '80');
INSERT INTO `sc` VALUES ('3', '2', '80');
INSERT INTO `sc` VALUES ('3', '3', '80');
INSERT INTO `sc` VALUES ('4', '1', '50');
INSERT INTO `sc` VALUES ('4', '2', '30');
INSERT INTO `sc` VALUES ('4', '3', '20');
INSERT INTO `sc` VALUES ('5', '1', '76');
INSERT INTO `sc` VALUES ('5', '2', '87');
INSERT INTO `sc` VALUES ('6', '1', '31');
INSERT INTO `sc` VALUES ('6', '3', '34');
INSERT INTO `sc` VALUES ('7', '2', '89');
INSERT INTO `sc` VALUES ('7', '3', '98');
