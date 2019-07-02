CREATE DATABASE  IF NOT EXISTS `warehouse` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `warehouse`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: warehouse
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `instore_details`
--

DROP TABLE IF EXISTS `instore_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instore_details` (
  `Instore_Sheet_ID` int(11) NOT NULL,
  `Product_ID` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Instore_Sheet_ID`,`Product_ID`),
  KEY `In_idx` (`Instore_Sheet_ID`),
  KEY `Product_ID_idx` (`Product_ID`),
  CONSTRAINT `FK_Instore_Sheet_Details` FOREIGN KEY (`Instore_Sheet_ID`) REFERENCES `instore_sheet` (`InStore_Sheet_ID`),
  CONSTRAINT `FK_Product_ID_In_Details` FOREIGN KEY (`Product_ID`) REFERENCES `product` (`Product_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instore_details`
--

LOCK TABLES `instore_details` WRITE;
/*!40000 ALTER TABLE `instore_details` DISABLE KEYS */;
INSERT INTO `instore_details` VALUES (9,7,23,'编程'),(9,12,567,'东方航空就'),(9,14,543,'东方红交付给'),(10,15,345,'FGSR'),(10,22,3436,'XDSHSR'),(10,32,47,'SFDGSDF'),(10,34,7856,'SDFGVWERW'),(11,7,1241,'adf'),(12,12,65,'无');
/*!40000 ALTER TABLE `instore_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instore_sheet`
--

DROP TABLE IF EXISTS `instore_sheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instore_sheet` (
  `Instore_Sheet_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Date_At` datetime NOT NULL,
  `Department` varchar(45) NOT NULL,
  `Checker` varchar(45) NOT NULL,
  `Modify_Date_At` datetime DEFAULT NULL,
  `Modifier` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Instore_Sheet_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instore_sheet`
--

LOCK TABLES `instore_sheet` WRITE;
/*!40000 ALTER TABLE `instore_sheet` DISABLE KEYS */;
INSERT INTO `instore_sheet` VALUES (9,'2015-05-11 15:59:02','CQU','LWX','2015-05-12 12:59:43','LWX'),(10,'2015-05-11 23:24:51','MICROSOFT','LWX',NULL,NULL),(11,'2015-05-13 09:18:01','哦泡腾片','LWX','2015-05-13 09:57:59','LWX'),(12,'2015-05-13 09:58:35','PrP','LWX',NULL,NULL);
/*!40000 ALTER TABLE `instore_sheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outstore_details`
--

DROP TABLE IF EXISTS `outstore_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `outstore_details` (
  `Outstore_Sheet_ID` int(11) NOT NULL,
  `Product_ID` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Outstore_Sheet_ID`,`Product_ID`),
  KEY `FK_Outstore_Sheet_ID_idx` (`Outstore_Sheet_ID`),
  KEY `FK_Product_ID3_idx` (`Product_ID`),
  CONSTRAINT `FK_Outstore_Sheet_Details` FOREIGN KEY (`Outstore_Sheet_ID`) REFERENCES `outstore_sheet` (`Outstore_Sheet_ID`),
  CONSTRAINT `FK_Product_ID_Out_Details` FOREIGN KEY (`Product_ID`) REFERENCES `product` (`Product_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outstore_details`
--

LOCK TABLES `outstore_details` WRITE;
/*!40000 ALTER TABLE `outstore_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `outstore_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outstore_sheet`
--

DROP TABLE IF EXISTS `outstore_sheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `outstore_sheet` (
  `Outstore_Sheet_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Date_At` datetime NOT NULL,
  `Department` varchar(45) NOT NULL,
  `Checker` varchar(45) NOT NULL,
  `Modify_Date_At` datetime DEFAULT NULL,
  `Modifier` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Outstore_Sheet_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outstore_sheet`
--

LOCK TABLES `outstore_sheet` WRITE;
/*!40000 ALTER TABLE `outstore_sheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `outstore_sheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `Product_ID` int(11) NOT NULL,
  `Product_Name` varchar(45) NOT NULL,
  `Model` varchar(45) NOT NULL,
  `Unit` varchar(10) NOT NULL,
  `Price` float NOT NULL,
  `Supplier` varchar(50) NOT NULL,
  PRIMARY KEY (`Product_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (7,'得力A4打印纸','DL-33127','包',16,'得力文具\r'),(12,'50ml圆底玻璃试管','YD-200','根',0.7,'思齐教仪\r'),(14,'闪迪u盘16G','CZ48 16G','个',38,'闪迪专卖店\r'),(15,'环氧树脂AB胶','FZS-01','瓶',35,'迪乐耗材\r'),(22,'L型钢尺','L-600','把',8,'得力文具\r'),(32,'2ml塑料离心管','XN8020','个',0.06,'思齐教仪\r'),(34,'砂纸','S-600#','张',2,'迪乐耗材\r'),(37,'PVC发泡板','344678899','片',9.8,'迪乐耗材\r'),(45,'250ml玻璃烧杯','GG-17','个',4.2,'思齐教仪\r'),(55,'3m网线','PAK-3','根',9,'连邦数码专营\r'),(62,'卡西欧计算器','DY-120','台',45,'金力办公用品\r'),(65,'轻木板','200*100*2','张',2.7,'迪乐耗材\r'),(87,'3M口罩','9001V','包',25,'3M专卖 \r'),(89,'加厚吸水纸','XSZ-40','张',2,'思齐教仪\r'),(96,'佳能扫描仪','LIDE 120','台',350,'佳茂办公用品\r'),(129,'雷克兰氯丁橡胶手套','EC30F','副',20,'凯淳防护用品\r');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_amount`
--

DROP TABLE IF EXISTS `store_amount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_amount` (
  `Month_At` int(11) NOT NULL,
  `Product_ID` int(11) NOT NULL,
  `This_Month_Amount` int(11) DEFAULT NULL,
  `Remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Month_At`,`Product_ID`),
  KEY `pro_idx` (`Product_ID`),
  CONSTRAINT `FK_Product_ID_Store_Amount` FOREIGN KEY (`Product_ID`) REFERENCES `product` (`Product_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_amount`
--

LOCK TABLES `store_amount` WRITE;
/*!40000 ALTER TABLE `store_amount` DISABLE KEYS */;
INSERT INTO `store_amount` VALUES (201505,7,1264,''),(201505,12,632,''),(201505,14,543,''),(201505,15,345,''),(201505,22,3436,''),(201505,32,47,''),(201505,34,7856,'');
/*!40000 ALTER TABLE `store_amount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `User_ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_Name` varchar(45) NOT NULL,
  `User_Password` varchar(45) NOT NULL,
  `User_Email` varchar(45) DEFAULT NULL,
  `User_Level` varchar(45) NOT NULL,
  `User_Real_Name` varchar(45) NOT NULL,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'xdxlwx','1',NULL,'1','LWX'),(2,'Gery','0827',NULL,'1','XNX');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-13 12:39:45
