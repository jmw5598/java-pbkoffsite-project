-- MySQL dump 10.16  Distrib 10.1.14-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: pbkoffsite
-- ------------------------------------------------------
-- Server version	10.1.14-MariaDB

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
-- Table structure for table `cycle_count`
--

DROP TABLE IF EXISTS `cycle_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cycle_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `performed_by_id` int(11) NOT NULL,
  `performed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `stockroom_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_performed_by_id_idx` (`performed_by_id`),
  KEY `fk_stockroom_id_counted_idx` (`stockroom_id`),
  KEY `fk_cycle_count_location_id_idx` (`location_id`),
  CONSTRAINT `fk_cycle_count_location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cycle_count_performed_by_id` FOREIGN KEY (`performed_by_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cycle_count_stockroom_id_counted` FOREIGN KEY (`stockroom_id`) REFERENCES `stockroom` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cycle_count`
--

LOCK TABLES `cycle_count` WRITE;
/*!40000 ALTER TABLE `cycle_count` DISABLE KEYS */;
/*!40000 ALTER TABLE `cycle_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cycle_count_counted`
--

DROP TABLE IF EXISTS `cycle_count_counted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cycle_count_counted` (
  `cycle_count_id` int(11) NOT NULL,
  `sku_id` int(11) NOT NULL,
  PRIMARY KEY (`cycle_count_id`,`sku_id`),
  KEY `fk_cycle_count_id_idx` (`cycle_count_id`),
  KEY `fk_sku_id_counted_idx` (`sku_id`),
  CONSTRAINT `fk_cycle_count_counted_cycle_count_id` FOREIGN KEY (`cycle_count_id`) REFERENCES `cycle_count` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cycle_count_counted_sku_id_counted` FOREIGN KEY (`sku_id`) REFERENCES `sku` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cycle_count_counted`
--

LOCK TABLES `cycle_count_counted` WRITE;
/*!40000 ALTER TABLE `cycle_count_counted` DISABLE KEYS */;
/*!40000 ALTER TABLE `cycle_count_counted` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location_url` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'/resources/img/icon-image-default.png');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location_id` int(11) NOT NULL,
  `date_added` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `added_by_id` int(11) NOT NULL,
  `date_removed` timestamp NULL DEFAULT NULL,
  `removed_by_id` int(11) DEFAULT NULL,
  `removed_reason_id` int(11) DEFAULT NULL,
  `additional_notes` varchar(45) DEFAULT NULL,
  `is_available` tinyint(4) NOT NULL DEFAULT '1',
  `sku_id` int(11) NOT NULL,
  `stockroom_id` int(11) NOT NULL,
  `item_condition_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sku_id_idx` (`sku_id`),
  KEY `stockroom_id_idx` (`stockroom_id`),
  KEY `condition_id_idx` (`item_condition_id`),
  KEY `removed_reason_id_idx` (`removed_reason_id`),
  KEY `added_by_id_idx` (`added_by_id`),
  KEY `removed_by_id_idx` (`removed_by_id`),
  KEY `location_id_idx` (`location_id`),
  CONSTRAINT `fk_item_added_by_id` FOREIGN KEY (`added_by_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_item_condition_id` FOREIGN KEY (`item_condition_id`) REFERENCES `item_condition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_removed_by_id` FOREIGN KEY (`removed_by_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_removed_reason_id` FOREIGN KEY (`removed_reason_id`) REFERENCES `removed_reason` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_sku_id` FOREIGN KEY (`sku_id`) REFERENCES `sku` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_stockroom_id` FOREIGN KEY (`stockroom_id`) REFERENCES `stockroom` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,1,'2016-06-08 20:26:59',2,'2016-06-12 22:55:03',2,1,'',0,8398612,2,1),(2,1,'2016-06-08 20:28:41',2,NULL,NULL,NULL,'',1,8398612,2,1),(3,1,'2016-06-08 20:28:42',2,'2016-06-12 23:31:00',2,1,'',0,8398612,2,1),(4,1,'2016-06-08 20:30:58',1,NULL,NULL,NULL,'',1,8399453,2,1),(5,1,'2016-06-08 20:31:03',2,NULL,NULL,NULL,'',1,8399453,2,1),(6,1,'2016-06-08 20:31:07',4,NULL,NULL,NULL,'',1,8399453,2,1),(7,1,'2016-06-08 20:31:12',4,NULL,NULL,NULL,'',1,8399453,2,1),(8,1,'2016-06-08 20:31:30',4,'2016-06-13 13:23:26',2,1,'',0,8399768,2,1),(9,1,'2016-06-08 20:31:57',1,NULL,NULL,NULL,'Returned, okay to sell',1,8399768,2,2),(10,1,'2016-06-08 20:32:12',1,'2016-06-15 19:06:50',2,1,'Returned, okay to sell',0,8399768,2,2),(11,1,'2016-06-08 20:32:31',4,NULL,NULL,NULL,'',1,8399768,2,1),(12,3,'2016-06-08 20:33:23',1,NULL,NULL,NULL,'',1,8398638,2,1),(13,3,'2016-06-08 20:34:14',1,NULL,NULL,NULL,'',1,8398638,2,1),(14,3,'2016-06-08 20:34:16',1,NULL,NULL,NULL,'',1,8398638,2,1),(15,3,'2016-06-08 20:34:17',1,NULL,NULL,NULL,'',1,8398638,2,1),(16,3,'2016-06-08 20:34:24',2,NULL,NULL,NULL,'',1,8398638,2,1),(17,3,'2016-06-08 20:34:29',4,NULL,NULL,NULL,'',1,8398638,2,1),(18,3,'2016-06-08 20:35:08',4,NULL,NULL,NULL,'',1,8398646,2,5),(19,3,'2016-06-08 20:35:32',4,NULL,NULL,NULL,'',1,8398646,2,6),(20,3,'2016-06-08 20:35:52',4,NULL,NULL,NULL,'Used on floorset, scuffed up',1,8398646,2,6),(21,3,'2016-06-08 20:36:01',4,NULL,NULL,NULL,'',1,8398646,2,1),(22,3,'2016-06-08 20:36:29',2,NULL,NULL,NULL,'',1,8399156,2,1),(23,3,'2016-06-08 20:36:30',2,NULL,NULL,NULL,'',1,8399156,2,1),(24,3,'2016-06-08 20:36:31',2,NULL,NULL,NULL,'',1,8399156,2,1),(25,2,'2016-06-08 20:37:01',2,NULL,NULL,NULL,'',1,8398646,2,1),(26,2,'2016-06-08 20:37:02',2,NULL,NULL,NULL,'',1,8398646,2,1),(27,2,'2016-06-08 20:37:08',4,NULL,NULL,NULL,'',1,8398646,2,1),(28,2,'2016-06-08 20:37:14',1,NULL,NULL,NULL,'',1,8398646,2,1),(29,3,'2016-06-08 20:56:44',2,NULL,NULL,NULL,'',1,3756483,2,1),(30,3,'2016-06-08 20:56:49',2,NULL,NULL,NULL,'',1,3756483,2,1),(31,3,'2016-06-08 20:57:11',2,NULL,NULL,NULL,'Used on floorset, like new',1,3756483,2,5),(32,3,'2016-06-08 20:57:26',1,NULL,NULL,NULL,'',1,3756483,2,1),(34,3,'2016-06-08 20:58:20',4,NULL,NULL,NULL,'',1,3756525,2,1),(35,3,'2016-06-08 20:58:36',4,NULL,NULL,NULL,'',1,3756525,2,1),(36,3,'2016-06-08 20:58:57',4,NULL,NULL,NULL,'Returned without box, New',1,3756525,2,2),(37,2,'2016-06-08 21:00:33',1,NULL,NULL,NULL,'',1,3756541,2,1),(38,2,'2016-06-08 21:00:34',1,NULL,NULL,NULL,'',1,3756541,2,1),(39,2,'2016-06-08 21:00:39',2,NULL,NULL,NULL,'',1,3756541,2,1),(40,2,'2016-06-08 21:01:08',2,NULL,NULL,NULL,'Used in lighting gallery, GOOD',1,3756608,2,5),(41,2,'2016-06-08 21:01:16',2,NULL,NULL,NULL,'',1,3756608,2,1),(42,2,'2016-06-08 21:01:20',1,NULL,NULL,NULL,'',1,3756608,2,1),(43,2,'2016-06-08 21:01:24',4,NULL,NULL,NULL,'',1,3756608,2,1),(44,4,'2016-06-08 21:08:11',2,NULL,NULL,NULL,'',1,6484612,2,1),(45,4,'2016-06-08 21:08:13',2,NULL,NULL,NULL,'',1,6484612,2,1),(46,4,'2016-06-08 21:08:16',1,NULL,NULL,NULL,'',1,6484612,2,1),(47,4,'2016-06-08 21:08:17',1,NULL,NULL,NULL,'',1,6484612,2,1),(48,4,'2016-06-08 21:08:37',1,NULL,NULL,NULL,'Used on multiple floorsets',1,6484612,2,3),(49,4,'2016-06-08 21:08:38',1,NULL,NULL,NULL,'Used on multiple floorsets',1,6484612,2,3),(50,4,'2016-06-08 21:09:06',1,NULL,NULL,NULL,'',1,6485403,2,1),(51,4,'2016-06-08 21:09:11',4,NULL,NULL,NULL,'',1,6485403,2,1),(52,4,'2016-06-08 21:09:13',4,NULL,NULL,NULL,'',1,6485403,2,1),(53,4,'2016-06-08 21:09:19',2,NULL,NULL,NULL,'',1,6485403,2,1),(54,4,'2016-06-08 21:09:36',2,NULL,NULL,NULL,'Returned without box',1,6485403,2,2),(55,4,'2016-06-08 21:09:53',1,NULL,NULL,NULL,'',1,6485403,2,1),(56,4,'2016-06-08 21:10:07',1,NULL,NULL,NULL,'',1,6485585,2,1),(57,4,'2016-06-08 21:10:21',1,NULL,NULL,NULL,'Used on floorset, no hardware',1,6485585,2,4),(58,4,'2016-06-08 21:10:22',1,NULL,NULL,NULL,'Used on floorset, no hardware',1,6485585,2,4),(59,4,'2016-06-08 21:10:35',2,NULL,NULL,NULL,'',1,6485585,2,1),(60,4,'2016-06-08 21:10:44',1,NULL,NULL,NULL,'',1,6485585,2,1),(61,4,'2016-06-08 21:10:45',1,NULL,NULL,NULL,'',1,6485585,2,1),(62,5,'2016-06-08 21:11:40',1,NULL,NULL,NULL,'',1,6485569,2,1),(63,5,'2016-06-08 21:11:43',1,NULL,NULL,NULL,'',1,6485569,2,1),(64,5,'2016-06-08 21:11:47',2,NULL,NULL,NULL,'',1,6485569,2,1),(65,5,'2016-06-08 21:11:51',4,NULL,NULL,NULL,'',1,6485569,2,1),(66,5,'2016-06-08 21:11:55',1,NULL,NULL,NULL,'',1,6485569,2,1),(67,5,'2016-06-08 21:11:56',1,NULL,NULL,NULL,'',1,6485569,2,1),(68,5,'2016-06-08 21:12:13',1,NULL,NULL,NULL,'',1,6484737,2,1),(69,5,'2016-06-08 21:12:14',1,NULL,NULL,NULL,'',1,6484737,2,1),(70,5,'2016-06-08 21:12:33',1,NULL,NULL,NULL,'Returned, new condition',1,6484737,2,2),(71,5,'2016-06-08 21:13:45',1,NULL,NULL,NULL,'',1,6484620,2,1),(72,5,'2016-06-08 21:13:46',1,NULL,NULL,NULL,'',1,6484620,2,1),(73,5,'2016-06-08 21:13:49',2,NULL,NULL,NULL,'',1,6484620,2,1),(74,5,'2016-06-08 21:13:53',4,NULL,NULL,NULL,'',1,6484620,2,1),(75,5,'2016-06-08 21:14:04',4,NULL,NULL,NULL,'',1,6485411,2,1),(76,5,'2016-06-08 21:14:06',4,NULL,NULL,NULL,'',1,6485411,2,1),(77,6,'2016-06-08 21:14:36',1,NULL,NULL,NULL,'',1,6484638,2,1),(78,6,'2016-06-08 21:14:38',1,NULL,NULL,NULL,'',1,6484638,2,1),(79,6,'2016-06-08 21:14:41',1,NULL,NULL,NULL,'',1,6484638,2,1),(80,6,'2016-06-08 21:14:44',2,NULL,NULL,NULL,'',1,6484638,2,1),(81,6,'2016-06-08 21:15:13',2,NULL,NULL,NULL,'Floorset, no hardware',1,6484729,2,3),(82,6,'2016-06-08 21:15:18',2,NULL,NULL,NULL,'Floorset, no hardware',1,6484729,2,4),(83,6,'2016-06-08 21:15:33',1,NULL,NULL,NULL,'',1,6484729,2,1),(84,6,'2016-06-08 21:15:53',1,NULL,NULL,NULL,'',1,6485429,2,1),(85,6,'2016-06-08 21:15:55',1,NULL,NULL,NULL,'',1,6485429,2,1),(86,6,'2016-06-08 21:16:00',4,NULL,NULL,NULL,'',1,6485429,2,1),(87,6,'2016-06-08 21:16:03',2,NULL,NULL,NULL,'',1,6485429,2,1),(88,10,'2016-06-08 22:19:43',1,NULL,NULL,NULL,'',1,4369476,3,1),(89,10,'2016-06-08 22:19:45',1,NULL,NULL,NULL,'',1,4369476,3,1),(90,10,'2016-06-08 22:19:50',2,NULL,NULL,NULL,'',1,4369476,3,1),(91,10,'2016-06-08 22:19:54',4,NULL,NULL,NULL,'',1,4369476,3,1),(92,10,'2016-06-08 22:20:08',1,NULL,NULL,NULL,'',1,4369476,3,1),(93,10,'2016-06-08 22:20:13',4,NULL,NULL,NULL,'',1,4369476,3,1),(94,10,'2016-06-08 22:20:18',2,NULL,NULL,NULL,'',1,4369476,3,1),(95,10,'2016-06-08 22:20:49',2,NULL,NULL,NULL,'',1,4389771,3,1),(96,10,'2016-06-08 22:20:50',2,NULL,NULL,NULL,'',1,4389771,3,1),(97,10,'2016-06-08 22:20:51',2,NULL,NULL,NULL,'',1,4389771,3,1),(98,10,'2016-06-08 22:20:55',1,NULL,NULL,NULL,'',1,4389771,3,1),(99,11,'2016-06-08 22:21:25',1,'2016-06-12 23:31:37',2,2,'',0,4388799,3,1),(100,11,'2016-06-08 22:21:27',1,'2016-06-13 16:47:08',2,3,'',0,4388799,3,1),(101,11,'2016-06-08 22:21:30',4,'2016-06-12 23:31:22',2,3,'',0,4388799,3,1),(102,12,'2016-06-08 22:22:07',1,NULL,NULL,NULL,'',1,4371811,3,1),(103,12,'2016-06-08 22:22:08',1,NULL,NULL,NULL,'',1,4371811,3,1),(104,12,'2016-06-08 22:22:10',1,NULL,NULL,NULL,'',1,4371811,3,1),(105,12,'2016-06-08 22:22:25',1,NULL,NULL,NULL,'',1,4371811,3,1),(106,12,'2016-06-08 22:22:28',2,'2016-06-12 23:32:48',2,1,'',0,4371811,3,1),(107,12,'2016-06-08 22:22:31',2,NULL,NULL,NULL,'',1,4371811,3,1),(108,12,'2016-06-08 22:22:35',4,NULL,NULL,NULL,'',1,4371811,3,1),(109,15,'2016-06-08 22:23:03',4,NULL,NULL,NULL,'',1,4371647,3,1),(110,15,'2016-06-08 22:23:05',4,NULL,NULL,NULL,'',1,4371647,3,1),(111,15,'2016-06-08 22:23:08',1,NULL,NULL,NULL,'',1,4371647,3,1),(112,15,'2016-06-08 22:23:09',1,NULL,NULL,NULL,'',1,4371647,3,1),(113,15,'2016-06-08 22:23:15',2,NULL,NULL,NULL,'',1,4371647,3,1),(114,15,'2016-06-08 22:23:17',2,NULL,NULL,NULL,'',1,4371647,3,1),(115,13,'2016-06-08 22:23:43',2,NULL,NULL,NULL,'',1,4371449,3,1),(116,13,'2016-06-08 22:23:44',2,NULL,NULL,NULL,'',1,4371449,3,1),(117,13,'2016-06-08 22:23:48',1,NULL,NULL,NULL,'',1,4371449,3,1),(118,14,'2016-06-08 22:24:24',1,NULL,NULL,NULL,'',1,4370615,3,1),(119,14,'2016-06-08 22:24:25',1,NULL,NULL,NULL,'',1,4370615,3,1),(120,14,'2016-06-08 22:24:26',1,NULL,NULL,NULL,'',1,4370615,3,1),(121,14,'2016-06-08 22:24:30',4,'2016-06-12 23:47:18',1,1,'',0,4370615,3,1),(122,14,'2016-06-08 22:24:35',4,NULL,NULL,NULL,'',1,4370615,3,1),(123,16,'2016-06-08 22:25:17',4,'2016-06-12 23:31:42',2,1,'',0,4370656,3,1),(124,16,'2016-06-08 22:25:18',4,'2016-06-12 23:32:19',2,1,'',0,4370656,3,1),(125,16,'2016-06-08 22:25:22',1,NULL,NULL,NULL,'',1,4370656,3,1),(126,16,'2016-06-08 22:25:23',1,'2016-06-12 23:32:11',2,1,'',0,4370656,3,1),(127,16,'2016-06-08 22:25:34',1,NULL,NULL,NULL,'',1,4389920,3,1),(128,16,'2016-06-08 22:25:35',1,NULL,NULL,NULL,'',1,4389920,3,1),(129,16,'2016-06-08 22:25:36',1,NULL,NULL,NULL,'',1,4389920,3,1),(130,16,'2016-06-08 22:25:40',2,NULL,NULL,NULL,'',1,4389920,3,1),(131,16,'2016-06-08 22:25:56',2,NULL,NULL,NULL,'',1,4392981,3,1),(132,16,'2016-06-08 22:25:57',2,NULL,NULL,NULL,'',1,4392981,3,1),(133,1,'2016-06-11 16:39:02',2,NULL,NULL,NULL,'',1,8398646,1,1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_condition`
--

DROP TABLE IF EXISTS `item_condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_condition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `description_UNIQUE` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_condition`
--

LOCK TABLES `item_condition` WRITE;
/*!40000 ALTER TABLE `item_condition` DISABLE KEYS */;
INSERT INTO `item_condition` VALUES (1,'New In Box'),(2,'New Out Of Box'),(5,'Used Good'),(4,'Used Ok'),(3,'Used Poor'),(6,'Used Rough');
/*!40000 ALTER TABLE `item_condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_cycle_count`
--

DROP TABLE IF EXISTS `item_cycle_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_cycle_count` (
  `item_id` int(11) NOT NULL,
  `cycle_count_id` int(11) NOT NULL,
  PRIMARY KEY (`item_id`,`cycle_count_id`),
  KEY `fk_cycle_count_item_id_idx` (`item_id`),
  KEY `fk_cycle_count_id_idx` (`cycle_count_id`),
  CONSTRAINT `fk_item_cycle_count_cycle_count_id` FOREIGN KEY (`cycle_count_id`) REFERENCES `cycle_count` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_cycle_count_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_cycle_count`
--

LOCK TABLES `item_cycle_count` WRITE;
/*!40000 ALTER TABLE `item_cycle_count` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_cycle_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_image`
--

DROP TABLE IF EXISTS `item_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_image` (
  `item_id` int(11) NOT NULL,
  `image_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`item_id`,`image_id`),
  KEY `image_id_idx` (`image_id`),
  CONSTRAINT `fk_item_image_image_id` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_image_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_image`
--

LOCK TABLES `item_image` WRITE;
/*!40000 ALTER TABLE `item_image` DISABLE KEYS */;
INSERT INTO `item_image` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(34,1),(35,1),(36,1),(37,1),(38,1),(39,1),(40,1),(41,1),(42,1),(43,1),(44,1),(45,1),(46,1),(47,1),(48,1),(49,1),(50,1),(51,1),(52,1),(53,1),(54,1),(55,1),(56,1),(57,1),(58,1),(59,1),(60,1),(61,1),(62,1),(63,1),(64,1),(65,1),(66,1),(67,1),(68,1),(69,1),(70,1),(71,1),(72,1),(73,1),(74,1),(75,1),(76,1),(77,1),(78,1),(79,1),(80,1),(81,1),(82,1),(83,1),(84,1),(85,1),(86,1),(87,1),(88,1),(89,1),(90,1),(91,1),(92,1),(93,1),(94,1),(95,1),(96,1),(97,1),(98,1),(99,1),(100,1),(101,1),(102,1),(103,1),(104,1),(105,1),(106,1),(107,1),(108,1),(109,1),(110,1),(111,1),(112,1),(113,1),(114,1),(115,1),(116,1),(117,1),(118,1),(119,1),(120,1),(121,1),(122,1),(123,1),(124,1),(125,1),(126,1),(127,1),(128,1),(129,1),(130,1),(131,1),(132,1);
/*!40000 ALTER TABLE `item_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `description_UNIQUE` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'A1'),(2,'A2'),(3,'A3'),(4,'B1'),(5,'B2'),(6,'B3'),(7,'C1'),(8,'C2'),(9,'C3'),(10,'D1'),(11,'D2'),(12,'D3'),(13,'E1'),(14,'E2'),(15,'E3'),(16,'F1'),(17,'F2'),(18,'F3'),(19,'G1'),(20,'G2'),(21,'G3'),(22,'H1'),(23,'H2'),(24,'H3'),(25,'I1'),(26,'I2'),(27,'I3'),(28,'J1'),(29,'J2'),(30,'J3'),(31,'K1'),(32,'K2'),(33,'K3');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `removed_reason`
--

DROP TABLE IF EXISTS `removed_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `removed_reason` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `removed_reason`
--

LOCK TABLES `removed_reason` WRITE;
/*!40000 ALTER TABLE `removed_reason` DISABLE KEYS */;
INSERT INTO `removed_reason` VALUES (1,'Sold'),(2,'Replacement'),(3,'Floorset');
/*!40000 ALTER TABLE `removed_reason` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_READ'),(2,'ROLE_WRITE'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sku`
--

DROP TABLE IF EXISTS `sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sku` (
  `id` int(11) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sku`
--

LOCK TABLES `sku` WRITE;
/*!40000 ALTER TABLE `sku` DISABLE KEYS */;
INSERT INTO `sku` VALUES (839412,'Chelsea Lamp Table:AN'),(1031509,'Oliver Lamp ACC:BRZ'),(1047703,'Perry Tufted Rug 5x8:Neutral'),(1048255,'Perry Tufted Rug 3x5:Neutral'),(1103621,'Solid Fringed Rug 8x10:Grey'),(1103696,'Solid Fringed Rug 5x8:Grey'),(1103746,'Solid Fringed Rug 3x5:Grey'),(1122738,'Reed Tufted Rug 5x8:Stone'),(1123322,'Reed Tufted Rug 3x5:Stone'),(1128057,'Cortona Bedside TBL:VSPR'),(1166115,'Griffin Coffee Table'),(1180363,'Weldon Side Table Base:Natural'),(1180371,'Weldon Side Table Top:Natural'),(1189406,'Parquet Coffee Table'),(1214931,'Silvia Pedestal Table Base'),(1215904,'Silvia Pedestal Table Top'),(1224948,'Griffin Side Table Rect'),(1233808,'Branford Bedside TBL:HBLK'),(1295054,'Basic Cowhide Shade SM'),(1295179,'Basic Cowhide Shade MD'),(1329481,'Parquet Console Table'),(1329580,'Parquet Side Table'),(1363589,'Nash Desk Chair SWVL:LCARAMEL'),(1527613,'Courtney Ceramic Lamp LG TBL:RED'),(1581909,'Wynn Dining Side Chair:GSWHT'),(1583723,'Wingate Desk Chair:ESP'),(1583731,'Wingate Desk Chair:Pecan'),(1591072,'Chandelier Cord Cover:Burlap'),(1597848,'Benchwright Dining TBL FX:RMHG'),(1616788,'Dawson File Cabinet Lateral:WBLK'),(1622794,'Paxton Pendant 11.75 TL GLS'),(1622885,'Paxton Pendant 7.75 SH GLS'),(1627751,'Standard Rug Pad 10x14'),(1627918,'Standard Rug Pad 5x8'),(1628007,'Standard Rug Pad 3x5'),(1648666,'Wingate Desk Chair SWVL Cushion:BC'),(1668599,'Palmetto Bar Console:Natural'),(1714377,'Solid Fringed Rug 3x5:Wheat'),(1714468,'Solid Fringed Rug 5x8:Wheat'),(1714542,'Solid Fringed Rug 8x10:Wheat'),(1739036,'Sectional Floor Shade:WHT'),(1777077,'Kensington Mirror REG Oval:PN'),(1876929,'Nelson Patchwork Rug 5x8:Gray'),(1876945,'Nelson Patchwork Rug 8x10:Gray'),(1886266,'Arlington Rug 3x5:Multi Red'),(1886274,'Arlington Rug 5x8:Multi Red'),(1886290,'Arlington Rug 8x10:Multi Red'),(1886324,'Arlington Rug 9x12:Multi Red'),(1887561,'Hearst Rug 5x8:Multi Red'),(1947886,'PB Classic Med Cab Rec XL:ESP'),(1947894,'PB Classic Med Cab Rec REG:ESP'),(2009942,'Dog Toy Basket LG'),(2022259,'Bosworth Rug 2.5x9:Gray'),(2022275,'Bosworth Rug 3x5:Gray'),(2022283,'Bosworth Rug 5x8:Gray'),(2022291,'Bosworth Rug 8x10:Gray'),(2111706,'McCoy Barstool Adjust:ESP'),(2111722,'McCoy Barstool Adjust:BLK'),(2111748,'McCoy Barstool Adjust:WHT'),(2263929,'Aaron Dining Side Chair:RPN'),(2267672,'Stacked Mercury Lamp FLR:BRZ'),(2267847,'Stacked Mercury Lamp TBL:BRZ'),(2273282,'Ashby Bedside TBL:RMHG'),(2275717,'Benchwright Console:RMHG'),(2277622,'Tivoli Console Table:ABLK'),(2280394,'Wynn Dining Side Chair:TCHST'),(2287746,'Ava Desk:ESP'),(2294106,'Chandelier Shades S3:Burlap'),(2298198,'Printers Writing Desk:TCHST'),(2422509,'Cozy Dog Bed Cover SM:Ivory'),(2444735,'Bosworth Rug 5x8:Blue'),(2444776,'Bosworth Rug 3x5:Blue'),(2471894,'Bosworth Rug 2.5x9:Blue'),(2472538,'Calhoun Pendant:BRZ'),(2472652,'Fallon Rug 3x5:Neutral'),(2472660,'Fallon Rug 5x8:Neutral'),(2539971,'Martine Persian Rug 3x5:Red'),(2680098,'Clarissa Chandelier RND:ANTSLVR'),(2750461,'Benchwright TV Stand:RMHG'),(2805125,'Whitney File Cabinet 3DR:AWHT'),(2805273,'Whitney Single Ped Cabinet:AWHT'),(2807204,'Whitney Desk Top Straight:AWHT'),(2808681,'Whitney File Cabinet Lateral:AWHT'),(2808897,'Whitney Desk Top Corner:AWHT'),(2841831,'Rhodes Canister LG'),(2841849,'Rhodes Canister MD'),(2919439,'Stratton Bedside TBL:AWHT'),(3032919,'Basic Linen Shade ACC:Bisque'),(3120474,'Chesapeake Dining Bench:Euca'),(3190485,'Concrete Umbrella Base:60lbs'),(3193695,'Abbott Island Table:Zinc'),(3344108,'Stacked Crystal Lamp Bedside'),(3344116,'Stacked Crystal Lamp Floor'),(3344124,'Stacked Crystal Lamp Table'),(3390259,'Manchester Desk Chair SWVL:ESLW'),(3421732,'Cortona Dining Side Chair:AFBRN'),(3427234,'Manchester Barstool 26in:ESLW'),(3427572,'Manchester Barstool 30in:ESLW'),(3427622,'Manchester Barstool 26in:ALDB'),(3558939,'Eli Natural Fiber Rug 8x10'),(3562006,'Bryson Rug 3x5:Multi Blue'),(3566122,'Hearst Rug 5x8:Neutral'),(3568151,'Chunky Wool Jute 3x5:Gray'),(3568185,'Chunky Wool Jute 5x8:Gray'),(3568235,'Chunky Wool Jute 8x10:Gray'),(3568565,'Chunky Wool Jute 9x12:Gray'),(3576808,'Duncan Diamond 9x12:Ivory'),(3577319,'Duncan Diamond 9x12:Neutral'),(3577368,'Duncan Diamond 8x10:Neutral'),(3577665,'Duncan Diamond 8x10:Ivory'),(3577715,'Duncan Diamond 5x8:Neutral'),(3578267,'Duncan Diamond 5x8:Ivory'),(3578382,'Duncan Diamond 3x5:Neutral'),(3578614,'Duncan Diamond 3x5:Ivory'),(3581402,'Ellsworth Patchwork Rug 5x8:Red'),(3647799,'Whitney Bookcase:HESP'),(3666815,'Calla Mercury Lamp RND TBL'),(3677077,'Channing Rug 9x12:Multi Reg'),(3677168,'Channing Rug 8x10:Multi Red'),(3677184,'Channing Rug 5x8:Multi Red'),(3677556,'Channing Rug 3x5:Multi Red'),(3716248,'Sadie Persian Rug 5x8:Green'),(3756483,'Glendale Task Lamp Floor:AB'),(3756525,'Glendale Task Lamp Floor:AN'),(3756541,'Glendale Task Lamp Table:AB'),(3756608,'Glendale Task Lamp Table:AN'),(3767688,'Ludlow Trunk Bar:BLK'),(3786548,'Grand Phone:BLK'),(3815214,'Conway Trunk:VBLUE'),(3858313,'Dawson Pedestal TBL Top'),(3858354,'Dawson Pedestal TBL Base'),(3863974,'Bedford Office Tower:WHT'),(3864006,'Bedford Office Tower:ESP'),(3868866,'Bedford Bookcase Narrow:ESP'),(3906260,'Carter Folding Tray Table'),(3934932,'Seagrass X Stool:HAV'),(3990355,'Wynn Dining Side Chair:AFBRN'),(4009882,'Bedford File Cabinet 2DR:WHT'),(4009890,'Bedford Desk Top Straight:WHT'),(4009908,'Bedford File Cabinet 3DR:WHT'),(4026449,'Andover Cabinet:Red'),(4026506,'Andover Cabinet:Walnet'),(4039053,'Printers Writing Desk:WHT'),(4046603,'Henley Rug 8x10:Taupe'),(4076725,'Bollinger Ottoman Rect:SDDLCOCO'),(4102786,'Seagrass Barstool BK MD:HAV'),(4103677,'Seagrass Barstool BK TL:HAV'),(4155123,'Rosario Kilim Rug 2x3'),(4155610,'Rosario Kilim Rug 3x5'),(4155784,'Rosario Kilim Rug 5x8'),(4155792,'Rosario Kilim Rug 8x10'),(4224598,'Lizzie Tufted Rug 3x5:Gray'),(4224622,'Lizzie Tufted Rug 3x5:Wheat'),(4322533,'Benchwright Tower:RMHG'),(4353934,'Reynolds Bedside TBL:ABLK'),(4357562,'Tivoli Dining TBL EXT Post:TCHST'),(4357570,'Tivoli Dining TBL EXT Top:TCH'),(4369476,'Chatham Stacking Chair ARM'),(4370615,'Chatham Occ Armchair'),(4370656,'Chatham Dining Table EXT BF 74in'),(4371449,'Chatham Sectional Ottoman'),(4371647,'Chatham Sectional Corner'),(4371811,'Chatham Sectional Armless'),(4388799,'Chatham Dining Bench 48in'),(4389771,'Chatham Folding Chair ARM'),(4389920,'Chatham Folding Table RND'),(4392981,'Chatham Dining Table FX 60in'),(4395992,'Photographer\'s TP TBL:BRZ'),(4396065,'Photographer\'s TP TBL:AN'),(4428686,'Glendale Task Lamp Floor:BZ'),(4428694,'Glendale Task Lamp Table:BZ'),(4442315,'Rebecca Trunk'),(4463246,'York Town Clock'),(4464111,'Seagrass SWVL Barstool BK MD:HAV'),(4464137,'Seagrass SWVL Barstool BK TL:HAV'),(4492443,'Cambria Pet Food Storage:Stone'),(4549143,'Abbott Grand Coffee SQ Euca'),(4603346,'Chatham Sectional Left Arm'),(4603510,'Chatham Sectional Right Arm'),(4609319,'Caden Stool 18in:CHOC'),(4660767,'Franklin Rug 8x10:Multi'),(4660783,'Franklin Rug 5x8:Multi'),(4660809,'Franklin Rug 3x5:Multi'),(4701561,'Grayson Dining Side Chiar:CHOC'),(4725065,'Benchwright Dining TBL EXT SM:RMHG'),(4727376,'Benchwright Dining TBL EXT SM:VSPR'),(4727558,'Benchwright Dining TBL EXT SM:AFBRN'),(4768990,'Ryden Desk Chair Frame'),(4786539,'Aaron Dining Side Chair:FWHT'),(4852059,'Bedford Desk Top Corner:WHT'),(4852539,'PB Basic Shade Linen SM:BSQ'),(4852554,'PB Basic Shade Linen MD:BSQ'),(4852570,'PB Basic Shade Linen LG:BSQ'),(4860342,'Wood Gallery Frame 9 Multi:BLK'),(4872925,'Studio Mirror 30x40 Rect:PT'),(4876082,'Brooke Bar Cart:PN'),(4898177,'Sectional Floor Shade:Burlap'),(4984233,'Printers Writing Desk:ABLK'),(4985016,'Tivoli Console Table:TCHST'),(5070354,'Tibetan Stool 26in:MHG'),(5150297,'Toscana Dining TBL EXT SM:VSPR'),(5150354,'Toscana Dining TBL EXT SM:AFBRN'),(5191762,'Star Pendant GLS SM:Brass'),(5195243,'Star Pendant GLS LG:Brass'),(5221783,'Toscana Dining TBL EXT SM:TCHST'),(5230727,'Barret Rug 3x5:Gray'),(5231055,'Barret Rug 5x8:Gray'),(5231063,'Barret Rug 8x10:Gray'),(5233838,'Marston Crystal Lamp TBL:BRZ'),(5233895,'Marston Crystal Lamp SMTBL:BRZ'),(5235809,'Tanner Coffee Cube Base'),(5235817,'Tanner Coffee Cube Top'),(5235833,'Tanner Console Long Top'),(5235866,'Tanner Console Long Base'),(5235924,'Tanner Console Reg Base'),(5235981,'Tanner Console Reg Top'),(5238092,'Langley Printed Rug 3x5:Blue'),(5238464,'Tanner Coffee Table Rect Base'),(5238696,'Tanner Coffee Round Base'),(5238720,'Tanner Coffee Round Top'),(5238803,'Tanner Side Round Top'),(5243365,'Aaron Dining Side Chair:RMHG'),(5250774,'Tanner Coffee Table Rect Top'),(5257886,'Nola Textured Lamp TBL:BRZ'),(5258140,'Nola Textured Lamp SMTBL:BRZ'),(5287370,'Farmhouse Bedside TBL:ESP'),(5292883,'Sumner Jute Rug 9x12:Neutral'),(5371612,'Redford Bedside TBL:VBURLAP:Mixed'),(5374582,'Abbott Island Hutch:Natural'),(5400312,'Filament Bulb 60W:Glass'),(5411780,'Dog Bed Insert Poly MD Rect'),(5416110,'Statement Clock'),(5417860,'Wood Gallery Frame OS 18x18:WHT'),(5425442,'Torrey Coffee Table RND:ESP'),(5529680,'Tanner Side Round Base'),(5541370,'Chelsea Lamp Accent:AGBRS'),(5582366,'Kai Shell Lamp TBL CYL:Espresso'),(5591821,'Driftwood Lamp Floor:Natural'),(5698568,'Kai Shell Lamp SM TBL CYL:Ivory'),(5817945,'Abbott Dining Table 84in Rect'),(5830922,'Kensington Mirror REG Rect:PN'),(5830930,'Kensington Mirror REG Rect:SN'),(5830963,'Kensington Mirror XL Rect:PN'),(5830971,'Kensington Mirror XL Rect:SN'),(5963058,'Bamilike Accent Table:Black'),(5963645,'Tile Top Side Table:BLKWHT'),(5963686,'Tile Top Bistro Table RND:BLUWHT'),(5975755,'Tile Top Side Table:BLUWHT'),(6027929,'Evelyn Chandelier MD:Wood'),(6033443,'Sumner Dining TBL RECT EXT:RMHG'),(6033484,'Sumn Dining TBL RECT EXT:RPN'),(6036979,'X-Back Bistro Chair Metal:BLK'),(6041008,'Studio Mirror 20x30 Rect:PT'),(6121821,'X-Back Bistro Chair Metal:WHT'),(6122196,'Darby Bench Cushion:Raffia'),(6133144,'Hazen Med Rug 3x5:Indigo'),(6134167,'Preston Herringbone Rug 9x12:Natural'),(6134498,'Preston Herringbone Rug 8x10:Natural'),(6136832,'Preston Herringbone Rug 5x8:Natural'),(6137483,'Preston Herringbone Rug 3x5:Natural'),(6140321,'Rexford Zig-Zag Rug 9x12:Multi'),(6140479,'Rexford Zig-Zag Rug 8x10:Multi'),(6141675,'Rexford Zig-Zag Rug 5x8:Multi'),(6142970,'Rexford Zig-Zag Rug 3x5:Multi'),(6148738,'Malika Persian Rug 3x5'),(6148746,'Malika Persian Rug 5x8'),(6148753,'Malika Persian Rug 8x10'),(6150411,'Linen Drum Shade MD:WHT'),(6168967,'Torrey Armchair:ESP'),(6171078,'Torrey Cube SQ:ESP'),(6171771,'Torrey Coffee Table SQ:ESP'),(6195478,'Amelia Chandelier LG:WHT'),(6213318,'Perry Divided Hamper:SAV'),(6341606,'Bryson Rug 3x5:Navy'),(6354971,'Bryson Rug 8x10:Navy'),(6385553,'Arden Shag Rug 9x12:Red'),(6386171,'Arden Shag Rug 8x10:Red'),(6387112,'Arden Shag Rug 5x8:Red'),(6387393,'Arden Shag Rug 3x5:Red'),(6443956,'Savannah Recycle Bin'),(6484612,'Crown Ledge 2ft White'),(6484620,'Crown Ledge 3ft White'),(6484638,'Crown Ledge 4ft White'),(6484729,'Crown Ledge 4ft Black'),(6484737,'Crown Ledge 3ft Black'),(6485403,'Crown Shelf 2ft Black'),(6485411,'Crown Shelf 3ft Black'),(6485429,'Crown Shelf 4ft Black'),(6485569,'Crown Shelf 3ft White'),(6485585,'Crown Shelf 2ft White'),(6521132,'Holloway Printed Rug 8x10:Gold'),(6528640,'Holloway Printed Rug 5x8:Gold'),(6529127,'Holloway Printed Rug 3x5:Gold'),(6532282,'Tile Top Bistro Table RND:BLKWHT'),(6555890,'Bedford Bookcase 2 Shelf:WHT'),(6555908,'Bedford File Cabinet Lateral:WHT'),(6576144,'Seagrass Occ Chair Ottoman'),(6576185,'Seagrass Occ Wingback Chair'),(6587620,'PB Basic Shade Linen LG:WHT'),(6587638,'PB Basic Shade Linen MD:WHT'),(6587646,'PB Basic Shade Linen SM:WHT'),(6661367,'Crown Ledge 2ft Espresso'),(6661375,'Crown Ledge 3ft Espresso'),(6661409,'Crown Ledge 4ft Espresso'),(6661466,'Crown Shelf 2ft Espresso'),(6661524,'Crown Shelf 4ft Espresso'),(6671762,'Hundi Lantern GLS:BRZ'),(6718050,'Clift Glass Pendant 18in'),(6775027,'Bevel Glass Mirror 22x26'),(6836670,'Franklin Rug 10x14:Multi'),(6890784,'Hudson Bedside 4-DR:MHG'),(6899645,'Bellora Chandelier:BRZ'),(6924450,'Sicily Console Table'),(7048887,'Photographer\'s TP FLR:BRZ'),(7152713,'Henley Rug 5x8:Ivory'),(7175151,'Scroll Tile Rug 8x10:Gray'),(7175789,'Scroll Tile Rug 5x8:Gray'),(7176407,'Scroll Tile Rug 3x5:Gray'),(7197072,'Eva Rug 3x5:Red'),(7197080,'Eva Rug 5x8:Red'),(7202450,'Taza Metal Pendant LG:Brass'),(7310550,'Chunky Wool Jute 3x5:Natural'),(7310568,'Chunky Wool Jute 5x8:Natural'),(7310576,'Chunky Wool Jute 8x10:Natural'),(7310584,'Chunky Wool Jute 9x12:Natural'),(7315047,'Bedford Desk Top w/ Legs:WHT'),(7353824,'Malika Persian Rug 9x12'),(7378359,'Adeline Rug 3x5'),(7378367,'Adeline Rug 5x8'),(7378375,'Adeline Rug 8x10'),(7379522,'Beachcomber Basket XL Nest'),(7387491,'Adeline Rug 9x12'),(7387509,'Adeline Rug 2.5x9'),(7436884,'Daily System 24in Pinboard:WHT'),(7446289,'Linen Drum Shade XL:WHT'),(7652514,'Potrero Bistro Chair Side'),(7692783,'Zebra Printed Rug 9x12:Neutral'),(7692932,'Zebra Printed Rug 8x10:Neutral'),(7693047,'Zebra Printed Rug 5x8:Neutral'),(7693096,'Zebra Printed Rug 3x5:Neutral'),(7766439,'Heather Chenille Jute Rug 2.5x9'),(7766447,'Heather Chenille Jute Rug 3x5'),(7766454,'Heather Chenille Jute Rug 5x8'),(7766462,'Heather Chenille Jute Rug 8x10'),(7827983,'Alana Lamp Cylinder SM TBL:Indigo'),(7866866,'Seagrass Barstool BL MD:HAV'),(7867088,'Seagrass Barstool BL TL:HAV'),(8018566,'Whitney File Cabinet 2DR:HESP'),(8018723,'Whitney File Cabinet 3DR:HESP'),(8018806,'Whitney Single Ped Cabinet:HESP'),(8019275,'Whitney Desk Top Straight:HESP'),(8019341,'Whitney Desk Top Corner:HESP'),(8019614,'Whitney File Cabinet Lateral:HESP'),(8019937,'Caden Ottoman SQ:CHOC'),(8019994,'Caden Ottoman RECT:CHOC'),(8043689,'Ludlow Side Table Acc:AWHT'),(8117004,'Wood Gallery Frame OS 18x18:BLK'),(8117053,'Wood Gallery Frame OS 16x20:BLK'),(8188096,'Ava Desk Top:Nickel'),(8188138,'Ava Desk Base:Nickel'),(8200719,'Andorra Tile Rug 9x12:Terra'),(8200750,'Andorra Tile Rug 8x10:Terra'),(8200909,'Andorra Tile Rug 5x8:Terra'),(8201048,'Andorra Tile Rug 3x5:Terra'),(8210221,'Griffin Console Metal Base'),(8210239,'Griffin Console Wood Top'),(8220279,'Blaine Coffee Table Round'),(8243255,'Bamboo Basket LG Lidded'),(8249559,'PB Classic Wall Vase:PN'),(8252215,'Marnie Bedside TBL:Mirrored'),(8264699,'Griffin Coffee Cube'),(8268377,'Benchwright Coffee Square:RMHG'),(8268435,'Vince Garden Stool:Brass'),(8268484,'Juniper Side Table Acc:Marble'),(8268518,'Merritt Bistro Accent TBL'),(8350720,'PB Basic Shade Burlap LG:BL'),(8350738,'PB Basic Shade Burlap LG:NT'),(8350746,'PB Basic Shade Burlap MD:BL'),(8350779,'PB Basic Shade Burlap MD:NT'),(8351009,'PB Basic Shade Burlap SM:BL'),(8351033,'PB Basic Shade Burlap SM:NT'),(8398612,'Chelsea Lamp Bedside:AGBRS'),(8398638,'Chelsea Lamp Floor:AGBRS'),(8398646,'Chelsea Lamp Table:AGBRS'),(8399156,'Chelsea Lamp Floor:AN'),(8399453,'Chelsea Lamp Bedside:BRZ'),(8399768,'Chelsea Lamp Bedside:AN'),(8427866,'Parquet Coffee Table RND'),(8431652,'Branford Bedside TBL:MHG'),(8432494,'Bartlett Console Table'),(8432510,'Bartlett Side Table'),(8433658,'Bartlett Coffee Table'),(8436768,'Marnie Mirrored Coffee TBL'),(8436792,'Marnie Mirrored Console TBL'),(8436834,'Marnie Mirrored Side TBL'),(8502114,'Photographer\'s TBL:BRZ'),(8539181,'Griffin Console Media TV'),(8539728,'Granger Coffee Table:Rustic'),(8539827,'Tanner Coffee Table Grand Top'),(8539876,'Tanner Coffee Table Grand Base'),(8540775,'Toscana Dining TBL FX:TCHST'),(8579062,'Beachcomber Basked LG Rect'),(8580706,'Benchwright Mirror 30x42:VSPR'),(8627432,'PB Basic Shade Burlap SM:CH'),(8627473,'PB Basic Shade Burlap MD:CH'),(8627549,'PB Basic Shade Burlap LG:CH'),(8659336,'Estelle Lamp LG TBL GLS:Bronze'),(8672222,'Courtney Ceramic Lamp SM TBL:IV'),(8714859,'Sidney Task Lamp TBL:BRZ'),(8768814,'PB Basic Shade Linen LG:FLAX'),(8768822,'PB Basic Shade Linen MD:FLAX'),(8768830,'PB Basic Shade Linen SM:FLAX'),(8769044,'Vintage Glass Hood Clear'),(8778961,'Clift Lamp Table:Green'),(8792269,'Wood Gallery Frame 9 Multi:ESP'),(8804452,'Wood Gallery Frame OS 18x18:ESP'),(8804528,'Wood Gallery Frame OS 16x20:ESP'),(8900037,'Murano Lamp GRAND GLS:BRZ'),(8900094,'Murano Lamp TBL GLS:BRZ'),(8921272,'Lily Task Lamp Mini TBL:AN'),(8932907,'Estelle Lamp SM TBL GLS:Bronze'),(8943888,'Griffen Bar TBL Top'),(8943920,'Griffen Bar TBL Base'),(8985616,'Chelsea Lamp Floor Tray:BRZ'),(9055633,'Brock Desk Chair SWVL:Oatmeal'),(9056011,'Decker Barstool BH Leather:CHOC'),(9056052,'Decker Barstool CH Leather:CHOC'),(9210360,'Premium Rug Pad 9x12'),(9210378,'Premium Rug Pad 10x14'),(9210436,'Premium Rug Pad 3x5'),(9210444,'Premium Rug Pad 5x8'),(9210469,'Premium Rug Pad 8x10'),(9272816,'Chelsea Lamp Accent:AN'),(9272824,'Chelsea Lamp Accent:BRZ'),(9296591,'Calais Barstool BK MD Leather:BRN'),(9296609,'Calais Barstool BK TL Leather:BRN'),(9296740,'Calais Barstool BK MD Suede:WHEAT '),(9296807,'Calais Barstool BK RL Suede:WHEAT'),(9326471,'Wynn Dining Side Chair:RMHG'),(9351180,'Wynn Dining Side Chair:BLK'),(9355371,'Photographer\'s TBL:AN'),(9355389,'Photographer\'s TP FLR:AN'),(9475294,'Benchwright Pedestal TBL:RMHG'),(9484502,'Camilla Chandelier LG:AGBRS'),(9484510,'Camilla Chandelier SM:AGBRS'),(9540352,'Ashby Bedside TBL:RPN'),(9612995,'Willow Accent Table:Iron'),(9618208,'Basic Cowhide Shade LG'),(9633306,'McKenna Jewelry Box Armoir:PBlue'),(9653247,'Bedford File Cabinet 2DR:ESP'),(9653486,'Bedford File Cabinet 3DR:ESP'),(9653601,'Bedford Desk Top Straight:ESP'),(9653643,'Bedford Desk Top Corner:ESP'),(9653684,'Bedford Bookcase 2 Shelf:ESP'),(9653759,'Bedford File Cabinet Lateral:ESP'),(9653882,'Bedford Desk Top Project:ESP'),(9653940,'Bedford Bookcase 3x3:ESP'),(9751306,'Harrison Basket OS Utility'),(9756214,'Benchwright Coffee Rect:RMHG'),(9816802,'Mendoze Accent Table:Red'),(9828427,'Brandon Rug 10x14:Espresso'),(9828443,'Brandon Rug 3x5:Espresso'),(9828450,'Brandon Rug 5x8:Espresso'),(9828468,'Brandon Rug 8x10:Espresso'),(9828484,'Brandon Rug 9x12:Espresso'),(9829847,'Benchwright Side RND:RMHG'),(9842147,'Tibetan Stool 30in:BLK'),(9842154,'Tibetan Stool 30in:MHG'),(9916545,'Architects Task Floor:BRZ'),(9917873,'Leona Side Table SQ:Brass'),(9932716,'Leona Console Table Mirror:Brass'),(9953340,'Wilton Dining Side Chair:SMKPN'),(9986142,'Shayne Kitchen TBL RND:WHT'),(9993882,'Helana Console Table');
/*!40000 ALTER TABLE `sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockroom`
--

DROP TABLE IF EXISTS `stockroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stockroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockroom`
--

LOCK TABLES `stockroom` WRITE;
/*!40000 ALTER TABLE `stockroom` DISABLE KEYS */;
INSERT INTO `stockroom` VALUES (1,'Backstock'),(2,'Basement'),(3,'Southside');
/*!40000 ALTER TABLE `stockroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$06$yuEINKOR8DuVaSzO73AxverRt5WW1n7SofIoWmEgIjPMXOSdPo3MG','Danielle','Gjurovski',1),(2,'jmw','$2a$06$p.QgOYqeySB5CBzGhOfiFOVl4ywWShpIIOzEQzHgx2savoOEKwMMO','Jason','White',1),(3,'dlw','$2a$06$5Z/sSg/FXttx9vw9ZDlYf.qtOjmQQNOWhLeZpQsx/v.yvZ4qJKCqK','Dillard','White',1),(4,'jmtv','$2a$06$W23H7Js/9jgt5DvgqVG6MOrvQ3TbCc8MQIDFCmGVwwefXKSkklL/6','John','Vital',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_role_id_idx` (`role_id`),
  CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,3),(2,2),(3,1),(4,2);
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

-- Dump completed on 2016-06-16 10:38:28
