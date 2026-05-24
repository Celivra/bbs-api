-- MySQL dump 10.13  Distrib 9.5.0, for macos15 (arm64)
--
-- Host: localhost    Database: bbs
-- ------------------------------------------------------
-- Server version	9.5.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT (now()),
  `avatar_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comments_post_id_fk` (`post_id`),
  KEY `comments_user_id_fk` (`user_id`),
  CONSTRAINT `comments_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `comments_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (3,1,15,NULL,'celivra','a nice wallpaper','2025-12-20 09:21:40','celivra_url_avater'),(4,1,17,NULL,'celivra','aaa','2025-12-20 12:06:49','celivra_url_avater'),(5,1,17,NULL,'celivra','gagaga','2025-12-20 12:09:26','celivra_url_avater'),(6,1,15,NULL,'celivra','aaaa','2025-12-20 12:17:44','celivra_url_avater'),(7,5,15,NULL,'123','i use archlinux btw','2025-12-20 12:18:08','hhh_url_avatar'),(8,8,19,NULL,'cell','good','2025-12-21 09:01:57','cell_url_avatar'),(10,5,17,NULL,'123','hello','2026-05-20 16:57:31','hhh_url_avatar'),(11,5,17,4,'123','nihao','2026-05-20 17:07:37','hhh_url_avatar'),(14,5,19,8,'123','123','2026-05-20 17:17:44','hhh_url_avatar'),(15,5,19,8,'123','回复cell:nihao','2026-05-20 17:25:58','hhh_url_avatar');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '通知接收者',
  `from_user_id` int NOT NULL COMMENT '触发通知的人',
  `related_id` int DEFAULT NULL COMMENT '关联的资源',
  `type` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '可选内容，如: xxx 点赞了你的帖子',
  `is_read` tinyint DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `notification_user_fk` (`user_id`),
  CONSTRAINT `notification_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (24,8,5,8,'REPLY','123回复了你的评论',1,'2026-05-20 17:17:44'),(25,8,5,8,'REPLY','123回复了你的评论',0,'2026-05-20 17:25:58');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `ptype` varchar(255) DEFAULT NULL,
  `content` text,
  `create_time` datetime DEFAULT (now()),
  PRIMARY KEY (`id`),
  KEY `post_user_id_fk` (`user_id`),
  CONSTRAINT `post_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (2,1,'hi',NULL,'this is my first post','2025-12-03 19:47:47'),(4,5,'test',NULL,'sans','2025-12-04 16:30:33'),(14,1,'aa',NULL,'aaaa','2025-12-19 12:10:55'),(15,1,'windows',NULL,'this is a windows wallpaper','2025-12-20 09:20:55'),(17,5,'test video',NULL,'this is a test video','2025-12-20 10:08:21'),(19,8,'my test post',NULL,'content','2025-12-21 09:01:37'),(21,5,'bbs revalution!!!1',NULL,'hhahahahah','2026-05-20 17:13:08'),(22,5,'bbs revalution!!!22222',NULL,'hhahahahah','2026-05-20 17:13:43');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_media`
--

DROP TABLE IF EXISTS `post_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_media` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `media_url` varchar(255) DEFAULT NULL,
  `media_type` tinyint DEFAULT NULL COMMENT '1=photo, 2=video',
  PRIMARY KEY (`id`),
  KEY `post_media_post_id_fk` (`post_id`),
  CONSTRAINT `post_media_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_media`
--

LOCK TABLES `post_media` WRITE;
/*!40000 ALTER TABLE `post_media` DISABLE KEYS */;
INSERT INTO `post_media` VALUES (12,21,'/upload/media/1779268388555.PNG',1),(13,22,'/upload/media/1779268423601.png',1),(14,22,'/upload/media/1779268423626.PNG',1);
/*!40000 ALTER TABLE `post_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'52xiaozhai@gmail.com','18766971045','celivra','940727','2025-11-28 16:20:36'),(5,'12312312@gmail.com','12312313','123','123456','2025-11-29 13:24:17'),(6,'52xxx@gmail.com','111111','ce','123','2025-12-03 13:15:08'),(7,'nienie@gmail.com','10086','nie','123','2025-12-20 09:41:47'),(8,'11@qq.com','11111100','cell','123','2025-12-21 09:01:03'),(9,'11@gmail.com','10086000','testuser','123','2025-12-22 16:26:05'),(10,'yuanyu@gmail.com','12312313131231','yuanyu','123456','2026-05-21 15:31:46');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_profile` (
  `id` int NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_profile_user_id_fk` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (1,'celivra','celivra_url_avater','能力额同学们',1,'1900-01-13'),(5,'haaa','hhh_url_avatar','',1,''),(6,'!人类','human_url_avatar','不是人类',1,'2025-12-27'),(7,'nie','nie_url_avatar','',0,''),(8,'cell','cell_url_avatar','bio...',1,'2025-12-21'),(9,'testuser','test_url_avatar','bio',1,'2025-12-19'),(10,'10','10_avava',NULL,NULL,NULL);
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

-- Dump completed on 2026-05-24 21:22:59
