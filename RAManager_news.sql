-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: research_achievement_management
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `achievement`
--

DROP TABLE IF EXISTS `achievement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `achievement` (
  `a_id` bigint(20) NOT NULL,
  `name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `upload_date` datetime DEFAULT NULL,
  `department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  FULLTEXT KEY `idx_achievement_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievement`
--

LOCK TABLES `achievement` WRITE;
/*!40000 ALTER TABLE `achievement` DISABLE KEYS */;
INSERT INTO `achievement` VALUES (6349463433691136,'没有手动阀','thesis','20170001',0,'2018-10-17 09:06:04','TRAINNING',NULL),(6354790182993920,'手动阀','thesis','20170001',0,'2018-10-18 08:34:10','TRAINNING',NULL),(6357891568812032,'手动阀很多','thesis','20170001',0,'2018-10-18 09:09:30','TRAINNING',NULL),(6358278426247168,'这是一个有关于，手动阀的论文','thesis','20170001',3,'2018-10-18 09:10:31','TRAINNING','RESEARCH'),(6359320601739264,NULL,'thesis','20170001',3,'2018-10-18 09:24:11','TRAINNING','RESEARCH'),(6360465852252160,NULL,'thesis','20170001',3,'2018-10-17 09:34:50','TRAINNING','RESEARCH'),(7311165490868224,NULL,'thesis','20170001',3,'2018-10-20 00:32:30',NULL,'RESEARCH'),(7311304641097728,NULL,'thesis','20170001',2,'2018-10-20 01:59:17','TRAINNING','RESEARCH'),(8389900197679104,NULL,'project','20170001',3,'2018-10-22 23:59:01',NULL,'RESEARCH'),(8390948702375936,NULL,'project','20170001',1,'2018-10-23 00:03:11',NULL,'RESEARCH'),(8632146046562304,'论著1','textbook','20170001',1,'2018-10-23 16:21:22','TRAINNING','RESEARCH');
/*!40000 ALTER TABLE `achievement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laws`
--

DROP TABLE IF EXISTS `laws`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `laws` (
  `l_id` bigint(20) NOT NULL,
  `l_name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `l_num` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `involvement` int(11) DEFAULT NULL,
  `words_count` int(11) DEFAULT NULL,
  `attachment` varchar(10000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laws`
--

LOCK TABLES `laws` WRITE;
/*!40000 ALTER TABLE `laws` DISABLE KEYS */;
/*!40000 ALTER TABLE `laws` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `news` (
  `n_id` bigint(20) NOT NULL,
  `title` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `upload_date` timestamp NULL DEFAULT NULL,
  `uploader` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (10552489379819520,NULL,NULL,'2018-10-28 15:12:22','admin001',1),(10552996064325632,NULL,NULL,'2018-10-28 15:47:04','admin001',2);
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patent`
--

DROP TABLE IF EXISTS `patent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `patent` (
  `pa_id` bigint(20) NOT NULL,
  `pa_name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `author_rank` int(11) DEFAULT NULL,
  `pa_num` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pa_owner` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `publish_date` timestamp NULL DEFAULT NULL,
  `attachment` varchar(10000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`pa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patent`
--

LOCK TABLES `patent` WRITE;
/*!40000 ALTER TABLE `patent` DISABLE KEYS */;
/*!40000 ALTER TABLE `patent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `project` (
  `p_id` bigint(20) NOT NULL,
  `involvement` int(11) DEFAULT NULL,
  `p_name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `subject` int(11) DEFAULT NULL,
  `attachment` varchar(10000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `project_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (8389900197679104,NULL,NULL,NULL,NULL,NULL,'20170001','2018-10-22 15:59:01',NULL,NULL,NULL,NULL,NULL,NULL),(8390948702375936,NULL,NULL,NULL,NULL,NULL,'20170001','2018-10-22 16:03:11',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reform_project`
--

DROP TABLE IF EXISTS `reform_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reform_project` (
  `r_p_id` bigint(20) NOT NULL,
  `involvement` int(11) DEFAULT NULL,
  `r_p_name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `attachment` varchar(10000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `project_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`r_p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reform_project`
--

LOCK TABLES `reform_project` WRITE;
/*!40000 ALTER TABLE `reform_project` DISABLE KEYS */;
/*!40000 ALTER TABLE `reform_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user` (
  `u_id` bigint(20) NOT NULL,
  `account` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `roles` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_password_reset_date` datetime DEFAULT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `username_UNIQUE` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (658294259580928,'huangkeli','$2a$10$Y/l8XdKtU6w3JbdlDCKyGu2dcJBlKBp628mAAZJrOVtLcFtxzh2xi','ROLE_TEACHER','2018-10-01 15:56:22'),(658366133174272,'admin001','$2a$10$PcpaSJn.n.jVeUwagJ1Ngeb51l31YxgmajMCajdAmE2/CccoJ4xY.','ROLE_ADMIN_01','2018-10-01 15:56:40'),(658394549583872,'admin002','$2a$10$qkKUF1Gn1cPtcnlApCr.8OvFGnXvDKYaVDXzxI6cwXH9PmNkjceVO','ROLE_ADMIN_02','2018-10-01 15:56:46'),(658460563734528,'20170001','$2a$10$3MMeoeTawYKwbQl9vjyozudqwW99c0/oa2l3.qBpATnAWk0P9Qim6','ROLE_TEACHER','2018-10-01 15:57:02');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `textbook`
--

DROP TABLE IF EXISTS `textbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `textbook` (
  `tb_id` bigint(20) NOT NULL,
  `tb_name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `involvement` int(11) DEFAULT NULL,
  `publisher` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ISBN` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `publish_date` timestamp NULL DEFAULT NULL,
  `attachment` varchar(10000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tb_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `textbook`
--

LOCK TABLES `textbook` WRITE;
/*!40000 ALTER TABLE `textbook` DISABLE KEYS */;
INSERT INTO `textbook` VALUES (8632146046562304,'论著1',NULL,NULL,NULL,'512341234',NULL,NULL,'20170001','2018-10-23 08:21:22',NULL,NULL,'TRAINNING',NULL);
/*!40000 ALTER TABLE `textbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thesis`
--

DROP TABLE IF EXISTS `thesis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `thesis` (
  `t_id` bigint(20) NOT NULL,
  `t_name` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `journal_name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `journal_level` int(11) DEFAULT NULL,
  `journal_id` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `attachment` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploader` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `publish_date` datetime DEFAULT NULL,
  `upload_date` datetime DEFAULT NULL,
  `score` float DEFAULT NULL,
  `max_score` float DEFAULT NULL,
  `department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thesis`
--

LOCK TABLES `thesis` WRITE;
/*!40000 ALTER TABLE `thesis` DISABLE KEYS */;
INSERT INTO `thesis` VALUES (1,'啊啊啊',NULL,NULL,NULL,NULL,'20170001',NULL,'2018-10-16 16:11:58',50,NULL,'TRAINNING',NULL),(6349463433691136,NULL,NULL,NULL,NULL,'null|1539737892712-1.mp4|1539737991244-1.mp4|1539738363503-2.jpg','20170001',NULL,'2018-10-17 09:06:04',NULL,NULL,'TRAINNING',NULL),(6354790182993920,NULL,NULL,NULL,NULL,'null','20170001',NULL,'2018-10-18 08:34:10',NULL,NULL,'TRAINNING',NULL),(6357891568812032,NULL,NULL,NULL,NULL,'null','20170001',NULL,'2018-10-18 09:09:30',NULL,NULL,'TRAINNING',NULL),(6358278426247168,NULL,NULL,NULL,NULL,'null','20170001',NULL,'2018-10-18 09:10:31',NULL,NULL,'TRAINNING',NULL),(6359320601739264,NULL,NULL,NULL,NULL,NULL,'20170001',NULL,'2018-10-18 09:24:11',NULL,NULL,'TRAINNING',NULL),(6360465852252160,NULL,NULL,NULL,NULL,'null|1539740089804-5.jpg','20170001',NULL,'2018-10-17 09:34:50',NULL,NULL,'TRAINNING',NULL),(7311165490868224,NULL,NULL,NULL,NULL,NULL,'20170001',NULL,'2018-10-20 00:32:30',NULL,NULL,NULL,NULL),(7311304641097728,NULL,NULL,NULL,NULL,'1539971968715-2.jpg|1539971968723-5.jpg|1539971968715-3.jpg|1539971968715-1.jpg|1539971968948-4.jpg','20170001',NULL,'2018-10-20 01:59:17',NULL,NULL,'TRAINNING',NULL);
/*!40000 ALTER TABLE `thesis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_profile` (
  `account` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `position` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `position_level` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_department` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES ('20170001','孙俊龙',NULL,NULL,NULL,'TRAINNING',NULL),('admin001','admin',NULL,NULL,NULL,'TRAINNING','RESEARCH'),('admin002','admin',NULL,NULL,NULL,'TRAINNING','RESEARCH'),('huangkeli','黄科力','副主任','讲师','连长','TRAINNING','RESEARCH');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-29  9:43:06
