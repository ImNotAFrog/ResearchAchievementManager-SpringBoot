/*
Navicat MySQL Data Transfer

Source Server         : 本地8.0
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : research_achievement_management

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-10-31 17:10:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for achievement
-- ----------------------------
DROP TABLE IF EXISTS `achievement`;
CREATE TABLE `achievement` (
  `a_id` bigint(20) NOT NULL,
  `name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `valid_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of achievement
-- ----------------------------
INSERT INTO `achievement` VALUES ('7610944913592320', '测试色', 'thesis', '20170001', '1', '2018-10-21 00:24:48', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:18', '50', '100');
INSERT INTO `achievement` VALUES ('7611440046985216', '看一看有没有', 'thesis', '20170001', '4', '2018-10-29 20:18:11', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:24', '50', '100');
INSERT INTO `achievement` VALUES ('7940924952530944', '顺丰到付按时', 'thesis', '20170001', '4', '2018-10-21 18:15:20', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:24', '50', '100');
INSERT INTO `achievement` VALUES ('7953357179965440', '已通过的项目', 'thesis', '20170001', '4', '2018-10-29 17:06:12', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:24', '50', '100');
INSERT INTO `achievement` VALUES ('7953507709341696', '待审核的论文', 'thesis', '20170001', '4', '2018-10-29 17:04:48', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:25', '50', '100');
INSERT INTO `achievement` VALUES ('7953601951158272', '已上报的论文', 'thesis', '20170001', '4', '2018-10-22 14:09:26', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:25', '50', '100');
INSERT INTO `achievement` VALUES ('8028059323752448', '这是个同名论文', 'thesis', '20170001', '4', '2018-10-29 16:20:14', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:25', '50', '100');
INSERT INTO `achievement` VALUES ('8295236949999616', '测试改驳回手动阀', 'project', '20170001', '-1', '2018-10-29 14:35:20', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:25', '50', '100');
INSERT INTO `achievement` VALUES ('8376756372951040', '手动阀阿斯蒂芬', 'project', '20170001', '-1', '2018-10-29 14:26:02', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:25', '50', '100');
INSERT INTO `achievement` VALUES ('8398060383817728', '高级管理员修改', 'project', '20170001', '4', '2018-10-29 09:57:58', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:25', '50', '100');
INSERT INTO `achievement` VALUES ('8532637945425920', '但是手动阀的', 'project', '20170001', '-1', '2018-10-29 17:06:33', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:25', '50', '100');
INSERT INTO `achievement` VALUES ('8588101852753920', '', 'textbook', '20170001', '-1', '2018-10-29 21:29:16', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:26', '50', '100');
INSERT INTO `achievement` VALUES ('8588397651849216', null, 'textbook', '20170001', '4', '2018-10-23 13:07:46', 'TRAINNING', 'RESEARCH', '2018-10-30 23:25:50', '50', '100');
INSERT INTO `achievement` VALUES ('8589542071889920', null, 'project', '20170001', '1', '2018-10-23 13:12:19', 'TRAINNING', 'RESEARCH', '2018-10-30 23:25:51', '50', '100');
INSERT INTO `achievement` VALUES ('8590005764780032', '', 'textbook', '20170001', '-1', '2018-10-29 21:28:54', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:26', '50', '100');
INSERT INTO `achievement` VALUES ('8591055712317440', '论著成果名称:', 'textbook', '20170001', '1', '2018-10-23 13:18:42', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:26', '50', '100');
INSERT INTO `achievement` VALUES ('8592962224812032', '现在可以存isbn了', 'textbook', '20170001', '4', '2018-10-23 21:55:21', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:26', '50', '100');
INSERT INTO `achievement` VALUES ('8593247554924544', '我现在删除了个文件  我现在修改一下', 'textbook', '20170001', '-1', '2018-10-29 22:04:00', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:26', '50', '100');
INSERT INTO `achievement` VALUES ('8629754667909120', '看一下上传能不能把书号存进去', 'textbook', '20170001', '4', '2018-10-23 16:38:38', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:26', '50', '100');
INSERT INTO `achievement` VALUES ('8647252696055808', '手动阀艾尔 我司超级挂你要', 'patent', '20170001', '-1', '2018-10-23 18:58:46', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:26', '50', '100');
INSERT INTO `achievement` VALUES ('8648949216526336', 'test1', 'patent', '20170001', '3', '2018-10-23 18:19:30', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:27', '50', '100');
INSERT INTO `achievement` VALUES ('8692401794867200', '法律、法规成果名称:', 'laws', '20170001', '-1', '2018-10-23 20:02:13', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:27', '50', '100');
INSERT INTO `achievement` VALUES ('8694300807946240', '法律、法规名称:（给）', 'laws', '20170001', '-1', '2018-10-23 20:58:11', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:27', '50', '100');
INSERT INTO `achievement` VALUES ('8707472906416128', '管理员修改', 'laws', '20170001', '4', '2018-10-23 21:22:26', 'TRAINNING', 'RESEARCH', '2018-10-30 23:25:51', '50', '100');
INSERT INTO `achievement` VALUES ('8713915810344960', '教改项目名称:（我改）', 'reformProject', '20170001', '4', '2018-10-23 21:36:26', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:27', '50', '100');
INSERT INTO `achievement` VALUES ('8716821036953600', '上环送审', 'reformProject', '20170001', '4', '2018-10-23 21:38:22', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:27', '50', '100');
INSERT INTO `achievement` VALUES ('8717458143342592', '顶顶顶顶顶顶顶', 'reformProject', '20170001', '4', '2018-10-23 21:51:43', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:27', '50', '100');
INSERT INTO `achievement` VALUES ('9040217905991680', '卡你看', 'project', '20170001', '1', '2018-10-24 19:03:14', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:28', '50', '100');
INSERT INTO `achievement` VALUES ('9040753468280832', '', 'project', '20170001', '1', '2018-10-24 19:05:20', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:28', '50', '100');
INSERT INTO `achievement` VALUES ('9042304786141184', 'sdfasd撒的发送到', 'project', '20170001', '1', '2018-10-24 19:11:30', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:28', '50', '100');
INSERT INTO `achievement` VALUES ('9044274548740096', null, 'project', '20170001', '1', '2018-10-24 19:19:16', 'TRAINNING', 'RESEARCH', '2018-10-30 23:26:05', '50', '100');
INSERT INTO `achievement` VALUES ('9044677063512064', null, 'project', '20170001', '1', '2018-10-24 19:20:52', 'TRAINNING', 'RESEARCH', '2018-10-30 23:26:05', '50', '100');
INSERT INTO `achievement` VALUES ('9044694050443264', null, 'project', '20170001', '1', '2018-10-24 19:20:56', 'TRAINNING', 'RESEARCH', '2018-10-30 23:26:05', '50', '100');
INSERT INTO `achievement` VALUES ('9047028113510400', null, 'project', '20170001', '1', '2018-10-24 19:30:12', 'TRAINNING', 'RESEARCH', '2018-10-30 23:26:05', '50', '100');
INSERT INTO `achievement` VALUES ('9047233491800064', null, 'project', '20170001', '1', '2018-10-24 19:31:01', 'TRAINNING', 'RESEARCH', '2018-10-30 23:26:05', '50', '100');
INSERT INTO `achievement` VALUES ('9047405479235584', null, 'project', '20170001', '1', '2018-10-24 19:31:42', 'TRAINNING', 'RESEARCH', '2018-10-30 23:26:05', '50', '100');
INSERT INTO `achievement` VALUES ('9047918660718592', '', 'project', '20170001', '-1', '2018-10-24 19:34:34', 'TRAINNING', 'RESEARCH', '2018-10-30 23:26:05', '50', '100');
INSERT INTO `achievement` VALUES ('9048770926514176', '我改', 'project', '20170001', '4', '2018-10-29 00:41:26', 'TRAINNING', 'RESEARCH', '2018-10-30 23:47:47', '50', '100');
INSERT INTO `achievement` VALUES ('9056062845652992', null, 'project', '20170001', '1', '2018-10-24 20:06:06', 'TRAINNING', 'RESEARCH', '2018-10-30 23:47:49', '50', '100');
INSERT INTO `achievement` VALUES ('9056167350931456', '发过火赶回翻过德国法', 'patent', '20170001', '4', '2018-10-29 23:33:35', 'TRAINNING', 'RESEARCH', '2018-10-30 23:47:50', '50', '100');
INSERT INTO `achievement` VALUES ('9056567080685568', '发过火规范dfg', 'patent', '20170001', '4', '2018-10-29 23:15:02', 'TRAINNING', 'RESEARCH', '2018-10-30 23:47:50', '50', '100');
INSERT INTO `achievement` VALUES ('9056596906381312', '高级管理员修改', 'textbook', '20170001', '4', '2018-10-29 21:57:43', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:29', '50', '100');
INSERT INTO `achievement` VALUES ('9429758387060736', '123', 'project', '20170001', '4', '2018-10-25 20:51:02', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:29', '50', '100');
INSERT INTO `achievement` VALUES ('10462497554993152', null, 'project', '20170001', '-1', '2018-10-28 17:14:47', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:30', '50', '100');
INSERT INTO `achievement` VALUES ('10463295903010816', '1', 'project', '20170001', '4', '2018-10-28 17:21:32', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:30', '50', '100');
INSERT INTO `achievement` VALUES ('10786917251190784', '课题名称:', 'project', '20170001', '1', '2018-10-29 14:44:10', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:30', '50', '100');
INSERT INTO `achievement` VALUES ('10788247336292352', '发斯蒂芬asd', 'project', '20170001', '2', '2018-10-29 14:49:20', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:30', '50', '100');
INSERT INTO `achievement` VALUES ('10788338046504960', '闪电法师', 'project', '20170001', '2', '2018-10-29 14:49:36', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:30', '50', '100');
INSERT INTO `achievement` VALUES ('10788752489877504', '123', 'project', '20170001', '2', '2018-10-29 14:51:12', 'TRAINNING', 'RESEARCH', '2018-10-30 23:23:12', '50', '100');
INSERT INTO `achievement` VALUES ('10788773356539904', '123', 'project', '20170001', '4', '2018-10-29 14:51:18', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:30', '50', '100');
INSERT INTO `achievement` VALUES ('10792451454963712', '2.1上传', 'thesis', '20170001', '4', '2018-10-29 15:06:15', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:30', '50', '100');
INSERT INTO `achievement` VALUES ('10880938900439040', 'ce  ', 'thesis', '20170001', '1', '2018-10-29 20:57:47', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:31', '50', '100');
INSERT INTO `achievement` VALUES ('10882685131800576', '我改', 'textbook', '20170001', '4', '2018-10-29 21:13:44', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:31', '50', '100');
INSERT INTO `achievement` VALUES ('10885112610406400', '测试哈', 'textbook', '20170001', '2', '2018-10-29 21:14:22', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:31', '50', '100');
INSERT INTO `achievement` VALUES ('10885562038468608', '现在是课题信息', 'project', '20170001', '2', '2018-10-29 21:16:12', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:31', '50', '100');
INSERT INTO `achievement` VALUES ('10885766695337984', '论著成果名称:', 'textbook', '20170001', '2', '2018-10-29 21:16:56', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:31', '50', '100');
INSERT INTO `achievement` VALUES ('10886041032179712', '手动阀', 'textbook', '20170001', '2', '2018-10-29 21:17:56', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:31', '50', '100');
INSERT INTO `achievement` VALUES ('10911080154578944', '专利或软件著作权信息（哈哈哈）', 'patent', '20170001', '4', '2018-10-29 23:12:25', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:31', '50', '100');
INSERT INTO `achievement` VALUES ('10923812916477952', '教学改革项目信息（我改）sss', 'reformProject', '20170001', '4', '2018-10-30 00:17:20', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:31', '50', '100');
INSERT INTO `achievement` VALUES ('10923946316316672', '2018年10月29日23:48:30', 'reformProject', '20170001', '4', '2018-10-30 00:28:54', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:32', '50', '100');
INSERT INTO `achievement` VALUES ('10936207751692288', 'sdfsdfasdf', 'laws', '20170001', '-1', '2018-10-30 00:44:22', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:32', '50', '100');
INSERT INTO `achievement` VALUES ('10940387694395392', 'sdfsd的所发生的阿斯顿发阿斯顿发', 'laws', '20170001', '3', '2018-10-30 00:53:56', 'TRAINNING', 'RESEARCH', '2018-10-30 21:16:32', '50', '100');
INSERT INTO `achievement` VALUES ('11430263451693056', null, 'reformProject', '20170001', '1', '2018-10-31 09:20:20', null, null, null, null, null);
INSERT INTO `achievement` VALUES ('11430279142584320', '法律、法规名称:', 'laws', '20170001', '1', '2018-10-31 09:20:45', 'TRAINNING', 'RESEARCH', '2018-10-31 08:00:00', null, null);
INSERT INTO `achievement` VALUES ('11431282994720768', 'sdsaf', 'laws', '20170001', '1', '2018-10-31 09:24:43', 'TRAINNING', 'RESEARCH', '2018-10-25 08:00:00', null, null);
INSERT INTO `achievement` VALUES ('11435552456454144', 'vvvv', 'laws', '20170001', '3', '2018-10-31 09:43:46', 'TRAINNING', 'RESEARCH', '2018-10-27 08:00:00', null, null);

-- ----------------------------
-- Table structure for laws
-- ----------------------------
DROP TABLE IF EXISTS `laws`;
CREATE TABLE `laws` (
  `l_id` bigint(20) NOT NULL,
  `l_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `l_num` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `involvement` int(11) DEFAULT NULL,
  `words_count` int(11) DEFAULT NULL,
  `attachment` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `publish_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of laws
-- ----------------------------
INSERT INTO `laws` VALUES ('8692401794867200', '法律、法规成果名称:', '法律、法规编号:', '2', '2', '9999', '1540296128135-GOPR2338_Moment.jpg', '20170001', '2018-10-23 20:02:13', null, null, 'TRAINNING', 'RESEARCH', null);
INSERT INTO `laws` VALUES ('8694300807946240', '法律、法规名称:（给）', '法律、法规编号:（给）', '1', '1', '100', '1540296603549-老鹰_Moment.jpg', '20170001', '2018-10-23 20:58:11', null, null, 'TRAINNING', 'RESEARCH', null);
INSERT INTO `laws` VALUES ('8707472906416128', '管理员修改', '手动阀撒地方', '2', '2', '123456', null, '20170001', '2018-10-23 21:22:26', null, null, null, null, null);
INSERT INTO `laws` VALUES ('10936207751692288', 'sdfsdfasdf', 'asdfasdf', '2', '2', '2343432', '1540831037015-暴风截图20138203878953.jpg', '20170001', '2018-10-30 00:44:22', '20', '90', 'TRAINNING', 'RESEARCH', null);
INSERT INTO `laws` VALUES ('10940387694395392', 'sdfsd的所发生的阿斯顿发阿斯顿发', '啥打法是否阿斯顿发', '2', '1', '4', null, '20170001', '2018-10-30 00:53:56', '30', '90', 'TRAINNING', 'RESEARCH', null);
INSERT INTO `laws` VALUES ('11430279142584320', '法律、法规名称:', '法律、法规名称:', '2', '2', '900', '1540948843998-第十次作业.zip', '20170001', '2018-10-31 09:20:45', '20', '90', 'TRAINNING', 'RESEARCH', null);
INSERT INTO `laws` VALUES ('11431282994720768', 'sdsaf', 'sadf', '2', '2', '23123', null, '20170001', '2018-10-31 09:24:43', '20', '90', 'TRAINNING', 'RESEARCH', null);
INSERT INTO `laws` VALUES ('11435552456454144', 'vvvv', 'dsfdfsd', '3', '2', '2019', null, '20170001', '2018-10-31 09:43:46', '10', '45', 'TRAINNING', 'RESEARCH', '2018-10-27 08:00:00');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `n_id` bigint(20) NOT NULL,
  `title` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `upload_date` timestamp NULL DEFAULT NULL,
  `uploader` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('10552489379819520', null, null, '2018-10-28 23:12:22', 'admin001', '1');
INSERT INTO `news` VALUES ('10552996064325632', null, null, '2018-10-28 23:47:04', 'admin001', '2');

-- ----------------------------
-- Table structure for patent
-- ----------------------------
DROP TABLE IF EXISTS `patent`;
CREATE TABLE `patent` (
  `pa_id` bigint(20) NOT NULL,
  `pa_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `author_rank` int(11) DEFAULT NULL,
  `pa_num` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pa_owner` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `publish_date` timestamp NULL DEFAULT NULL,
  `attachment` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`pa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of patent
-- ----------------------------
INSERT INTO `patent` VALUES ('8647252696055808', '手动阀艾尔 我司超级挂你要', '4', '10', '手动阀ads', '手动阀大', '2018-10-23 08:00:00', null, '20170001', '2018-10-23 18:58:46', null, null, 'TRAINNING', 'RESEARCH');
INSERT INTO `patent` VALUES ('8648949216526336', 'test1', '4', '10', 'tes委屈翁', 'tes', '2018-10-23 08:00:00', '1540289840508-老鹰_Moment.jpg', '20170001', '2018-10-23 18:19:30', null, null, 'TRAINNING', 'RESEARCH');
INSERT INTO `patent` VALUES ('9056167350931456', '发过火赶回翻过德国法', '3', '3', '452', '1', '2018-10-29 08:00:00', null, '20170001', '2018-10-29 23:33:35', '20', '12', 'TRAINNING', 'RESEARCH');
INSERT INTO `patent` VALUES ('9056567080685568', '发过火规范dfg', '3', '1', '个dfg个', 'dfg梵蒂冈翻过飞', '2018-10-29 08:00:00', '1540826096948-暴风截图20138203864828.jpg', '20170001', '2018-10-29 23:15:02', '4', '12', 'TRAINNING', 'RESEARCH');
INSERT INTO `patent` VALUES ('10911080154578944', '专利或软件著作权信息（哈哈哈）', '4', '6', '的沙发斯蒂芬四方达', '手动阀撒地方阿斯顿发', '2018-10-29 08:00:00', '1540825053321-暴风截图20138203040343.jpg|1540825925872-暴风截图20138203878953.jpg', '20170001', '2018-10-29 23:12:25', '0', '12', 'TRAINNING', 'RESEARCH');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `p_id` bigint(20) NOT NULL,
  `involvement` int(11) DEFAULT NULL,
  `p_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `subject` int(11) DEFAULT NULL,
  `attachment` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `project_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('8295236949999616', '1', '测试改驳回手动阀', '3', '2', '1540225849536-GOPR2338_Moment.jpg', '20170001', '2018-10-29 14:35:20', '5', '15', 'TRAINNING', 'RESEARCH', '1', '2018-10-23 08:00:00');
INSERT INTO `project` VALUES ('8376756372951040', '2', '手动阀阿斯蒂芬', '2', '1', '1540220815459-GOPR2338_Moment.jpg|1540785570021-暴风截图20138203045968.jpg|1540794194084-暴风截图20138203040343.jpg', '20170001', '2018-10-29 14:26:02', '10', '90', 'TRAINNING', 'RESEARCH', '1', '2018-10-23 08:00:00');
INSERT INTO `project` VALUES ('8398060383817728', '2', '高级管理员修改', '6', '2', '1540226164804-老鹰_Moment.jpg|1540264961677-老鹰_Moment.jpg', '20170001', '2018-10-29 09:57:58', '20', null, 'TRAINNING', 'RESEARCH', '1', '2018-10-20 08:00:00');
INSERT INTO `project` VALUES ('8532637945425920', '2', '但是手动阀的', '5', '2', '1540264575121-打招呼11_Moment.jpg', '20170001', '2018-10-29 17:06:33', '0', '0', 'TRAINNING', 'RESEARCH', '1', '2018-10-23 08:00:00');
INSERT INTO `project` VALUES ('8589542071889920', null, null, null, '2', null, '20170001', '2018-10-23 13:12:19', null, null, null, null, null, null);
INSERT INTO `project` VALUES ('9040217905991680', '1', '卡你看', '1', '2', null, '20170001', '2018-10-24 19:03:14', null, null, 'TRAINNING', 'RESEARCH', '1', null);
INSERT INTO `project` VALUES ('9040753468280832', '1', '', '1', '2', null, '20170001', '2018-10-24 19:05:20', null, null, 'TRAINNING', 'RESEARCH', '1', null);
INSERT INTO `project` VALUES ('9042304786141184', '1', 'sdfasd撒的发送到', '1', '2', null, '20170001', '2018-10-24 19:11:30', null, null, 'TRAINNING', 'RESEARCH', '1', null);
INSERT INTO `project` VALUES ('9044274548740096', null, null, null, '2', null, '20170001', '2018-10-24 19:19:16', null, null, null, null, null, null);
INSERT INTO `project` VALUES ('9044677063512064', null, null, null, '2', null, '20170001', '2018-10-24 19:20:52', null, null, null, null, null, null);
INSERT INTO `project` VALUES ('9044694050443264', null, null, null, '2', null, '20170001', '2018-10-24 19:20:56', null, null, null, null, null, null);
INSERT INTO `project` VALUES ('9047028113510400', null, null, null, '2', null, '20170001', '2018-10-24 19:30:12', null, null, null, null, null, null);
INSERT INTO `project` VALUES ('9047233491800064', null, null, null, '2', null, '20170001', '2018-10-24 19:31:01', null, null, null, null, null, null);
INSERT INTO `project` VALUES ('9047405479235584', null, null, null, '2', null, '20170001', '2018-10-24 19:31:42', null, null, null, null, null, null);
INSERT INTO `project` VALUES ('9047918660718592', '1', '', '1', '2', null, '20170001', '2018-10-24 19:34:34', null, null, 'TRAINNING', 'RESEARCH', '1', null);
INSERT INTO `project` VALUES ('9048770926514176', '1', '我改', '3', '2', null, '20170001', '2018-10-29 00:41:26', '3', null, 'TRAINNING', 'RESEARCH', '1', '2018-10-26 08:00:00');
INSERT INTO `project` VALUES ('9056062845652992', null, null, null, '2', null, '20170001', '2018-10-24 20:06:06', null, null, null, null, null, null);
INSERT INTO `project` VALUES ('9429758387060736', null, null, null, '2', null, '20170001', '2018-10-25 20:51:02', '0', '0', null, null, null, null);
INSERT INTO `project` VALUES ('10462497554993152', null, null, null, '2', null, '20170001', '2018-10-28 17:14:47', '0', '0', null, null, null, null);
INSERT INTO `project` VALUES ('10463295903010816', '2', '1', '3', '2', '1540718288662-暴风截图2013821214953.jpg', '20170001', '2018-10-28 17:21:32', '0', '0', 'TRAINNING', 'RESEARCH', '2', '2018-10-28 08:00:00');
INSERT INTO `project` VALUES ('10786917251190784', '2', '课题名称:', '1', '2', '1540795446919-暴风截图20138203040343.jpg|1540795446919-暴风截图2013821214953.jpg', '20170001', '2018-10-29 14:44:10', '6.6', '100', 'TRAINNING', 'RESEARCH', '2', '2018-10-24 08:00:00');
INSERT INTO `project` VALUES ('10788247336292352', '2', '发斯蒂芬asd', '2', '1', null, '20170001', '2018-10-29 14:49:20', '10', '90', 'TRAINNING', 'RESEARCH', '2', null);
INSERT INTO `project` VALUES ('10788338046504960', '1', '闪电法师', '1', '1', null, '20170001', '2018-10-29 14:49:36', '100', '300', 'TRAINNING', 'RESEARCH', '1', null);
INSERT INTO `project` VALUES ('10788752489877504', null, null, null, null, null, '20170001', '2018-10-29 14:51:12', '0', '0', null, null, null, null);
INSERT INTO `project` VALUES ('10788773356539904', '1', '', '1', '1', null, '20170001', '2018-10-29 14:51:18', '100', '300', 'TRAINNING', 'RESEARCH', '1', null);
INSERT INTO `project` VALUES ('10885562038468608', '2', '现在是课题信息', '2', '2', '1540818971054-暴风截图20138203895843.jpg', '20170001', '2018-10-29 21:16:12', '3.3', '30', 'TRAINNING', 'RESEARCH', '2', '2018-10-27 08:00:00');

-- ----------------------------
-- Table structure for reform_project
-- ----------------------------
DROP TABLE IF EXISTS `reform_project`;
CREATE TABLE `reform_project` (
  `r_p_id` bigint(20) NOT NULL,
  `involvement` int(11) DEFAULT NULL,
  `r_p_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `attachment` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `project_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`r_p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of reform_project
-- ----------------------------
INSERT INTO `reform_project` VALUES ('8713915810344960', '2', '教改项目名称:（我改）', '2', '1540301212403-打招呼11_Moment.jpg', '20170001', '2018-10-23 21:36:26', null, null, 'TRAINNING', 'RESEARCH', '2018-10-30 08:00:00');
INSERT INTO `reform_project` VALUES ('8716821036953600', '2', '上环送审', '2', '1540301900587-老鹰_Moment.jpg', '20170001', '2018-10-23 21:38:22', null, null, 'TRAINNING', 'RESEARCH', '2018-10-23 08:00:00');
INSERT INTO `reform_project` VALUES ('8717458143342592', '1', '顶顶顶顶顶顶顶', '2', null, '20170001', '2018-10-23 21:51:43', null, null, 'TRAINNING', 'RESEARCH', '2018-10-23 08:00:00');
INSERT INTO `reform_project` VALUES ('10923812916477952', '1', '教学改革项目信息（我改）sss', '1', '1540828086029-暴风截图2013821214953.jpg|1540828604518-暴风截图2013821214953.jpg', '20170001', '2018-10-30 00:17:20', '888', '24', 'TRAINNING', 'RESEARCH', '2018-10-19 08:00:00');
INSERT INTO `reform_project` VALUES ('10923946316316672', '1', '2018年10月29日23:48:30', '2', '1540828119319-暴风截图20138203040343.jpg', '20170001', '2018-10-30 00:28:54', '200', '24', 'TRAINNING', 'RESEARCH', '2018-10-18 08:00:00');
INSERT INTO `reform_project` VALUES ('11430263451693056', null, null, null, null, '20170001', '2018-10-31 09:20:20', '0', '0', null, null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `u_id` bigint(20) NOT NULL,
  `account` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `roles` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_password_reset_date` datetime DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `username_UNIQUE` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('658294259580928', 'huangkeli', '$2a$10$Y/l8XdKtU6w3JbdlDCKyGu2dcJBlKBp628mAAZJrOVtLcFtxzh2xi', 'ROLE_TEACHER', '2018-10-01 15:56:22');
INSERT INTO `sys_user` VALUES ('658366133174272', 'admin001', '$2a$10$PcpaSJn.n.jVeUwagJ1Ngeb51l31YxgmajMCajdAmE2/CccoJ4xY.', 'ROLE_ADMIN_01', '2018-10-01 15:56:40');
INSERT INTO `sys_user` VALUES ('658394549583872', 'admin002', '$2a$10$qkKUF1Gn1cPtcnlApCr.8OvFGnXvDKYaVDXzxI6cwXH9PmNkjceVO', 'ROLE_ADMIN_02', '2018-10-01 15:56:46');
INSERT INTO `sys_user` VALUES ('658460563734528', '20170001', '$2a$10$3MMeoeTawYKwbQl9vjyozudqwW99c0/oa2l3.qBpATnAWk0P9Qim6', 'ROLE_TEACHER', '2018-10-01 15:57:02');

-- ----------------------------
-- Table structure for textbook
-- ----------------------------
DROP TABLE IF EXISTS `textbook`;
CREATE TABLE `textbook` (
  `tb_id` bigint(20) NOT NULL,
  `tb_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `involvement` int(11) DEFAULT NULL,
  `publisher` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ISBN` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `publish_date` timestamp NULL DEFAULT NULL,
  `attachment` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tb_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of textbook
-- ----------------------------
INSERT INTO `textbook` VALUES ('8588101852753920', '', null, null, '', '', null, '1540819753422-暴风截图20138203878953.jpg', '20170001', '2018-10-29 21:29:16', '0', '0', 'TRAINNING', 'RESEARCH');
INSERT INTO `textbook` VALUES ('8588397651849216', null, null, null, null, null, null, null, '20170001', '2018-10-23 13:07:46', null, null, null, null);
INSERT INTO `textbook` VALUES ('8590005764780032', '', null, '2', '手动阀阿斯顿发', '', null, '1540271680400-打招呼11_Moment.jpg', '20170001', '2018-10-29 21:28:54', '0', '0', 'TRAINNING', 'RESEARCH');
INSERT INTO `textbook` VALUES ('8591055712317440', '论著成果名称:', '2', '2', '123456789', null, '2018-10-23 08:00:00', '1540271920962-老鹰_Moment.jpg', '20170001', '2018-10-23 13:18:42', null, null, 'TRAINNING', 'RESEARCH');
INSERT INTO `textbook` VALUES ('8592962224812032', '现在可以存isbn了', '2', '2', '手动阀撒地方', '是否asd', '2018-10-23 08:00:00', '1540272390698-打招呼11_Moment.jpg', '20170001', '2018-10-23 21:55:21', null, null, 'TRAINNING', 'RESEARCH');
INSERT INTO `textbook` VALUES ('8593247554924544', '我现在删除了个文件  我现在修改一下', '3', '3', '手动阀阿斯蒂芬', '', '2018-10-23 08:00:00', '1540272427191-打招呼11_Moment.jpg', '20170001', '2018-10-29 22:04:00', '20', null, 'TRAINNING', 'RESEARCH');
INSERT INTO `textbook` VALUES ('8629754667909120', '看一下上传能不能把书号存进去', '1', '2', '翻过手动阀', '我改了二楼哦', '2018-10-23 08:00:00', null, '20170001', '2018-10-23 16:38:38', null, null, 'TRAINNING', 'RESEARCH');
INSERT INTO `textbook` VALUES ('9056596906381312', '高级管理员修改', '2', '2', '手动阀', '手动阀', '2018-10-29 08:00:00', null, '20170001', '2018-10-29 21:57:43', '90', null, 'TRAINNING', 'RESEARCH');
INSERT INTO `textbook` VALUES ('10882685131800576', '我改', '2', '2', '手动阀', '阿斯顿发', '2018-10-29 08:00:00', '1540818820917-暴风截图20138203895843.jpg', '20170001', '2018-10-29 21:13:44', '0', '0', 'TRAINNING', 'RESEARCH');
INSERT INTO `textbook` VALUES ('10885112610406400', '测试哈', '3', '2', '手动阀', '手动阀', '2018-10-29 08:00:00', '1540818859806-暴风截图20138203045968.jpg', '20170001', '2018-10-29 21:14:22', '0', '0', 'TRAINNING', 'RESEARCH');
INSERT INTO `textbook` VALUES ('10885766695337984', '论著成果名称:', '2', '2', '都感到', '手动阀', '2018-10-29 08:00:00', '1540819015106-暴风截图20138203911781.jpg', '20170001', '2018-10-29 21:16:56', '0', '0', 'TRAINNING', 'RESEARCH');
INSERT INTO `textbook` VALUES ('10886041032179712', '手动阀', '2', '2', '手动阀', '手动阀', '2018-10-29 08:00:00', null, '20170001', '2018-10-29 21:17:56', '0', '0', 'TRAINNING', 'RESEARCH');

-- ----------------------------
-- Table structure for thesis
-- ----------------------------
DROP TABLE IF EXISTS `thesis`;
CREATE TABLE `thesis` (
  `t_id` bigint(20) NOT NULL,
  `t_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `journal_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `journal_level` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `journal_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `attachment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `publish_date` datetime DEFAULT NULL,
  `upload_date` datetime DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of thesis
-- ----------------------------
INSERT INTO `thesis` VALUES ('7610944913592320', '测试色', '是达芬奇', '4', '手动阀测试赛测试的的', '1540049408927-header.html|1540049964536-index.html|1540202154995-老鹰_Moment.jpg', '20170001', '2018-10-20 08:00:00', '2018-10-21 00:24:48', null, null, 'TRAINNING', 'RESEARCH');
INSERT INTO `thesis` VALUES ('7611440046985216', '看一看有没有', '论人类', '1', '0007', '1540038849931-login.html', '20170001', '2018-10-20 08:00:00', '2018-10-29 20:18:11', '20', null, 'TRAINNING', 'RESEARCH');
INSERT INTO `thesis` VALUES ('7940924952530944', '看一看有没有', '大师傅', '3', '萨达发斯蒂芬', '1540116915062-upload2.html', '20170001', '2018-10-21 08:00:00', '2018-10-21 18:15:20', null, null, 'TRAINNING', 'RESEARCH');
INSERT INTO `thesis` VALUES ('7953357179965440', '已通过的项目', 'asd', '1', 'asd', null, '20170001', '2018-10-29 08:00:00', '2018-10-29 17:06:12', '50', '500', 'TRAINNING', 'RESEARCH');
INSERT INTO `thesis` VALUES ('7953507709341696', '待审核的论文', '', '1', '', null, '20170001', null, '2018-10-29 17:04:48', '50', '500', 'TRAINNING', 'RESEARCH');
INSERT INTO `thesis` VALUES ('7953601951158272', '已上报的论文', '这是管理员修改内容', '1', '', '1540134051061-upload.html|1540134051068-upload2.html|1540187005685-打招呼11_Moment.jpg', '20170001', null, '2018-10-22 14:09:26', null, null, 'TRAINNING', 'RESEARCH');
INSERT INTO `thesis` VALUES ('8028059323752448', '这是个同名论文', '', '1', '', '1540801212272-暴风截图20138203878953.jpg', '20170001', null, '2018-10-29 16:20:14', '50', '500', 'TRAINNING', 'RESEARCH');
INSERT INTO `thesis` VALUES ('10792451454963712', '2.1上传', '发表', '3', '看奥', '1540796773658-暴风截图20138203040343.jpg', '20170001', '2018-10-29 08:00:00', '2018-10-29 15:06:15', '1', '500', 'TRAINNING', 'RESEARCH');
INSERT INTO `thesis` VALUES ('10880938900439040', 'ce  ', '手动阀', '2', '98', '1540817865345-暴风截图20138203040343.jpg', '20170001', '2018-11-02 08:00:00', '2018-10-29 20:57:47', '1', '500', 'TRAINNING', 'RESEARCH');

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `account` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `position` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `position_level` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES ('20170001', '孙俊龙', null, null, null, 'TRAINNING', 'RESEARCH');
INSERT INTO `user_profile` VALUES ('admin001', '超级管理员超人', '主任', null, '军长', 'TRAINNING', 'RESEARCH');
INSERT INTO `user_profile` VALUES ('admin002', '超人', '副主任', '讲师', '连长', 'TRAINNING', 'RESEARCH');
INSERT INTO `user_profile` VALUES ('huangkeli', '黄科力', '副主任', '讲师', '连长', 'TRAINNING', 'RESEARCH');
