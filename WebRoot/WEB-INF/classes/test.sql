/*
 Navicat Premium Data Transfer

 Source Server         : fuck
 Source Server Type    : MySQL
 Source Server Version : 50638
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50638
 File Encoding         : 65001

 Date: 26/10/2018 17:26:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `from` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `id`(`id`, `name`, `from`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('1', '计科', '计算机学院');
INSERT INTO `major` VALUES ('2', '环境', '土建学院');


-- ----------------------------
-- Table structure for shadule
-- ----------------------------
DROP TABLE IF EXISTS `shadule`;
CREATE TABLE `shadule`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `teacher` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `place` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id_major` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `scope` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `day` int(4) NOT NULL,
  `ord` int(2) NOT NULL,
  `index` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`index`) USING BTREE,
  INDEX `id_major`(`id_major`) USING BTREE,
  CONSTRAINT `shadule_ibfk_1` FOREIGN KEY (`id_major`) REFERENCES `major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shadule
-- ----------------------------
INSERT INTO `shadule` VALUES ('fdsfd', 'asdfsdf', 'asfsdf', 'asfsdf', 'adsffds', '2', '2018-2019-2', 1, 1, 0001);
INSERT INTO `shadule` VALUES ('asfsdfds', 'asfdsdf', 'asfsdf', 'asdfsdf', 'asfsdf', '2', '2018-2019-2', 2, 1, 0002);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `level` int(4) UNSIGNED ZEROFILL NOT NULL,
  `id_major` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_major`(`id_major`) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`id_major`) REFERENCES `major` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('张建', '16722075', 'zhang1997', 2016, '1');
INSERT INTO `student` VALUES ('张凯淞', '16722076', 'zhang1997', 2016, '1');

-- ----------------------------
-- Table structure for userfile
-- ----------------------------
DROP TABLE IF EXISTS `userfile`;
CREATE TABLE `userfile`  (
  `uId` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resume` longblob NULL,
  `updateTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `valid` int(1) NULL DEFAULT 1,
  `hidden` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`fId`) USING BTREE,
  INDEX `uId`(`uId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userfile
-- ----------------------------
INSERT INTO `userfile` VALUES ('16722075', '12e577a4-3128-442d-aae0-e660a9ff6468', '__init__', 'home/我爱看的黄片/', 'none', NULL, '2018-10-26 12:06:58', 1, 1);
INSERT INTO `userfile` VALUES ('16722076', '1ba85190-62bb-4199-8efd-a5f63ffcbd05', '__init__', 'home/新建文件夹/新建文件夹/', 'none', NULL, '2018-10-26 11:47:05', 1, 1);
INSERT INTO `userfile` VALUES ('16722075', '287ddf26-2ddf-48f3-afcb-33cea98d8414', 'c5c8b7e59b6cd17904e065cfd8fc2a05', 'home/我爱看的黄片/新建文件夹/', 'png', NULL, '2018-10-26 12:28:56', 1, 0);
INSERT INTO `userfile` VALUES ('16722075', '3e429e70-3375-4269-ae90-0af71720f8cc', '968e648de0c35e61bd6c148334c71eba', 'home/我爱看的黄片/新建文件夹/', 'jpg', NULL, '2018-10-26 12:30:03', 1, 0);
INSERT INTO `userfile` VALUES ('16722076', '4bcc5e7b-1ab4-4ab3-9585-594a3bd5e323', '%E5%AD%A6%E4%BD%8D%E5%B8%BD%E6%AF%95%E4%B8%9A%E8%AE%BE%E8%AE%A1%E7%AD%94%E8%BE%A9PPT%E6%A8%A1%E6%9D%BF(1)(1)', 'home/', 'docx', NULL, '2018-10-26 12:03:21', 0, 0);
INSERT INTO `userfile` VALUES ('16722075', '4c48b088-adab-40ac-9374-044e765f28a4', '瀹為獙鎶ュ憡妯℃澘', 'home/我爱看的黄片/', 'wps', NULL, '2018-10-26 12:15:24', 1, 0);
INSERT INTO `userfile` VALUES ('16722076', '4deb44b6-8bbb-48d9-be74-29e0f7736275', '__init__', 'home/新建文件夹/', 'none', NULL, '2018-10-26 11:46:46', 1, 1);
INSERT INTO `userfile` VALUES ('16722075', '550c786a-5ab9-4e78-9552-9d1d9a0bef0d', '__init__', 'home/我爱看的黄片/新建文件夹/', 'none', NULL, '2018-10-26 12:15:29', 1, 1);
INSERT INTO `userfile` VALUES ('16722075', '67583306-2b2c-42a8-8261-7db460e17b20', '【BD 720P 包含外挂字幕】【感谢魔穗字幕组%26LittleBakas】【 vol.1-3】【01-06集】', 'home/', 'rar', NULL, '2018-10-26 12:06:39', 1, 0);
INSERT INTO `userfile` VALUES ('16722076', '73fba689-5ba8-4fdc-b86e-b539882babd1', '%E5%AD%A6%E4%BD%8D%E5%B8%BD%E6%AF%95%E4%B8%9A%E8%AE%BE%E8%AE%A1%E7%AD%94%E8%BE%A9PPT%E6%A8%A1%E6%9D%BF', 'home/', 'docx', NULL, '2018-10-26 12:01:24', 0, 0);
INSERT INTO `userfile` VALUES ('16722075', '80ae906a-5054-4bf4-99b5-f08d053e4b2b', '33aa59903eccca757b33f93d5f43fb1f', 'home/', 'jpg', NULL, '2018-10-26 12:18:37', 1, 0);
INSERT INTO `userfile` VALUES ('16722076', '87543f0b-30f8-4313-852c-894fd1ae8ff9', '__init__', 'home/新建文件夹2/', 'none', NULL, '2018-10-26 11:46:51', 1, 1);
INSERT INTO `userfile` VALUES ('16722076', '8a99c381-9a0e-41b7-b151-701c526cb559', '__init__', 'home/新建文件夹/新建文件夹/新建文件夹/', 'none', NULL, '2018-10-26 11:47:09', 1, 1);
INSERT INTO `userfile` VALUES ('16722076', '8c6af19f-2efd-4572-8110-a70fda778844', '【BD 720P 包含外挂字幕】【感谢魔穗字幕组%26LittleBakas】【 vol.1-3】【01-06集】', 'home/新建文件夹/新建文件夹/新建文件夹/', 'rar', NULL, '2018-10-26 11:56:35', 1, 0);
INSERT INTO `userfile` VALUES ('16722076', '8f28c924-b44b-4ae6-a7ff-d4e963150c06', '=_UTF8_B_MjAxNi0yMDE35a2m5bm056ys_= =_UTF8_B_5LqM5a2m5pyf5pyf5pyr6ICD6K+V5a6J5o6SLnhscw==_= (1)', 'home/', 'wps', NULL, '2018-10-26 12:02:04', 0, 0);
INSERT INTO `userfile` VALUES ('16722075', '99f8b42c-e7b6-4203-8f99-a24cbb45958a', '33aa59903eccca757b33f93d5f43fb1f(1)', 'home/', 'jpg', NULL, '2018-10-26 12:28:45', 1, 0);
INSERT INTO `userfile` VALUES ('16722076', 'a26f9988-4f54-449a-98ae-86dff88b4c47', '__init__', 'home/新建文件夹3/', 'none', NULL, '2018-10-26 11:46:54', 1, 1);
INSERT INTO `userfile` VALUES ('16722076', 'a7763127-5c8a-412e-bd5b-882601724510', '%E5%AD%A6%E4%BD%8D%E5%B8%BD%E6%AF%95%E4%B8%9A%E8%AE%BE%E8%AE%A1%E7%AD%94%E8%BE%A9PPT%E6%A8%A1%E6%9D%BF(1)', 'home/', 'docx', NULL, '2018-10-26 12:02:52', 0, 0);
INSERT INTO `userfile` VALUES ('16722075', 'a7f8de99-9cd4-4eea-9007-44aa4b544628', 'eclipse_v4.5.0', 'home/我爱看的黄片/', 'exe', NULL, '2018-10-26 12:15:07', 1, 0);
INSERT INTO `userfile` VALUES ('16722075', 'a8f9a9f2-dfc2-4285-9bf5-d1539fa4ff5f', 'b4d391e37226205f417b939e2d887846', 'home/我爱看的黄片/', 'png', NULL, '2018-10-26 12:07:13', 1, 0);
INSERT INTO `userfile` VALUES ('16722076', 'b837c839-4c46-422d-87f8-d3cad78ad39b', '__init__', 'home/', 'none', NULL, '2018-10-26 11:27:54', 1, 1);
INSERT INTO `userfile` VALUES ('16722075', 'd748c501-993b-4404-8d44-4c3f2da1827c', '__init__', 'home/', 'none', NULL, '2018-10-26 12:06:08', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
