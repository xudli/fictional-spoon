CREATE DATABASE  IF NOT EXISTS `demo4j` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `demo4j`;
-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: demo4j
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `is_deleted` tinyint(4) DEFAULT NULL,
  `permission_name` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `permission_name_cn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'2017-03-09 09:10:27','2017-03-09 09:15:39',2,0,'user:read','PermissionDO-9ff1663d-ae42-4508-8f71-e041b2b90fb2','用户：列表'),(2,'2017-03-09 09:12:23','2017-03-09 09:15:25',2,0,'user:write','PermissionDO-eba3598d-929c-42ad-8d2c-944b29cdcf2e','用户：修改'),(3,'2017-03-09 09:13:22','2017-03-09 09:15:04',1,0,'role:read','PermissionDO-9bf0b364-8857-4bda-9014-c5c8eca33f8e','角色：列表'),(4,'2017-03-09 09:14:49','2017-03-09 09:14:49',0,0,'role:write','PermissionDO-e4decdf1-11ea-473b-93f0-65968d4b91f8','角色：修改'),(5,'2017-03-09 09:16:17','2017-03-09 09:16:17',0,0,'permission:read','PermissionDO-65b23852-d958-4870-a172-2ae9114312a4','权限：列表'),(6,'2017-03-09 09:16:33','2017-03-09 09:16:33',0,0,'permission:write','PermissionDO-59ca1865-2cdf-4a1f-a367-37f906f26485','权限：修改');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `is_deleted` tinyint(4) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'2017-03-09 09:10:09','2017-03-09 09:10:09',0,0,'超级管理员','RoleDO-a35855a5-04fe-4c64-8751-19d326f5fa79');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `role_uuid` varchar(255) DEFAULT NULL,
  `permission_uuid` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES ('RoleDO-a35855a5-04fe-4c64-8751-19d326f5fa79','PermissionDO-9ff1663d-ae42-4508-8f71-e041b2b90fb2'),('RoleDO-a35855a5-04fe-4c64-8751-19d326f5fa79','PermissionDO-eba3598d-929c-42ad-8d2c-944b29cdcf2e'),('RoleDO-a35855a5-04fe-4c64-8751-19d326f5fa79','PermissionDO-9bf0b364-8857-4bda-9014-c5c8eca33f8e'),('RoleDO-a35855a5-04fe-4c64-8751-19d326f5fa79','PermissionDO-e4decdf1-11ea-473b-93f0-65968d4b91f8'),('RoleDO-a35855a5-04fe-4c64-8751-19d326f5fa79','PermissionDO-65b23852-d958-4870-a172-2ae9114312a4'),('RoleDO-a35855a5-04fe-4c64-8751-19d326f5fa79','PermissionDO-59ca1865-2cdf-4a1f-a367-37f906f26485');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `is_deleted` tinyint(4) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2017-03-09 09:09:56','2017-03-09 12:05:44',4,0,'admin','5e543256c480ac577d30f76f9120eb74','UserDO-26324ca0-ecc0-42b1-8f30-dcaec025a337');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_uuid` varchar(255) DEFAULT NULL,
  `role_uuid` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('UserDO-26324ca0-ecc0-42b1-8f30-dcaec025a337','RoleDO-a35855a5-04fe-4c64-8751-19d326f5fa79');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-09 12:39:01
