-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: management_information_system
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `company_details`
--

DROP TABLE IF EXISTS `company_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_details` (
  `company_id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) DEFAULT NULL,
  `industry_type` varchar(45) DEFAULT NULL,
  `company_size` varchar(100) DEFAULT NULL,
  `company_logo` blob,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_details`
--

LOCK TABLES `company_details` WRITE;
/*!40000 ALTER TABLE `company_details` DISABLE KEYS */;
INSERT INTO `company_details` VALUES (1,NULL,NULL,NULL,NULL),(2,'Torry Harris Integration Solutions Pvt Ltd','5665','IT',NULL),(3,'Mphasis Pvt Ltd','5665','IT',NULL),(4,'TCS India Pvt Ltd','5665','IT',NULL),(5,'Rayies Global Pvt Ltd','45','IT',NULL),(6,'PWC Pvt Ltd','45','IT',NULL),(7,'Infosys Pvt Ltd','7888','IT',NULL),(8,'Accenture Pvt Ltd','IT','7888',NULL),(9,'CropIn Pvt Ltd','IT','10000',NULL),(10,'EY Pvt Ltd','IT','10000',NULL),(11,'JP Morgon Pvt Ltd','IT','10000',NULL),(12,'Blue Hill','Pre-school','10000',NULL),(13,'Capemini Indian Co pvt','IT','2500',NULL),(14,'Wipro India PVT LTD','IT','56908',NULL),(15,'SecureLabs','IT','56908',NULL),(16,'CropIn','IT','5559',NULL),(17,'Collabera','IT','7888',NULL),(18,'TCS','IT','21222',NULL),(19,'Qulammm','IT','3455',NULL),(20,'Bosch','IT','8777',NULL),(21,'Byjus','IT','8777',NULL),(22,'GenPact','IT','12222',NULL),(23,'InfoTech','IT','14555',NULL),(24,'InfoGain','IT','3455',NULL);
/*!40000 ALTER TABLE `company_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER'),(3,'MANAGER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_details` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone_No` varchar(45) DEFAULT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `base_location` varchar(45) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `role_id_idx` (`role_id`),
  KEY `company_id_idx` (`company_id`),
  CONSTRAINT `company_id` FOREIGN KEY (`company_id`) REFERENCES `company_details` (`company_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES (1,'Suraj S','$2a$12$AsmTPAM4F2DyfhooSH6XZ.zla916pp/7U88TMpjGw/fwsSUHiT8bO',NULL,NULL,'System Admin',1,NULL,10),(2,'Deepika S','$2a$12$fTan8DD88Zf7h3lPGqNwUOWqVDcHqjP1wBE3oenUJPlfLhCWJN2Mu',NULL,NULL,'System Admin',1,NULL,11),(3,'Chandrakala GN','$2a$12$X/Q/2/aZiDGgAF.wolIrU.JUcgZzXRgXavA9MePsgFRiT1uztDJdO',NULL,NULL,'System Admin',1,NULL,12),(4,'Manasa GS','$2a$12$aPgx4aL8AJA2CvHbRFm6g.Dnm3YaaF.GCiFPSM/WyqR.6M6.mZBm6',NULL,NULL,'System Admin',1,NULL,13),(5,'Om Prakash','$2a$12$c641KTyje15GFU3KsXtoBekg.55AxsaJCb1oE9D0cejeVkT84iyfC',NULL,NULL,'System Admin',1,NULL,14),(6,'RajKumar MR','$2a$12$s2Z9uFDjv.yBQuKJdm7iVe8NGP5FCHUB5rT6fU2Pg0m93HQsmMXSu',NULL,NULL,'System Admin',1,NULL,15),(7,'Shyam','$2a$12$zDBzGWkwLVzN9wR5BMlHveMYq2iLAB8ytfwbkfSmKxzBp.jGXIObi',NULL,NULL,'System Admin',1,NULL,16),(8,'Harish S','$2a$12$4CbZ2M.o.fBJ5KwabZRWj.txHCWcN2OfsbhwPlvhsI0Mb4L9ghrRW',NULL,NULL,'System Admin',1,NULL,17),(9,'Meghu','$2a$12$J1Aq0i6pC11vAHzfv869fOJOX2dq6aLYhdtmYT2M87XyoeHXk96XS',NULL,NULL,'System Admin',1,NULL,18),(10,'Saurav','$2a$12$43qorrssvNBS0nlypeHLG.pL5JAa5BCBaOrtCIJFUJ4EXfuIFA3L2',NULL,NULL,'System Admin',1,NULL,19),(11,'Akhila','$2a$12$reBkGJLYTHwH4jU4IeoTyOLwud3Uv/OA4pZyAlG/wqHl7IUZr5ZPS',NULL,NULL,'System Admin',1,NULL,20),(12,'Ruthvik','$2a$12$dnAOAVf.9Rn2J6TNw8QZEOFDPM.WKIz.wMWiSZfVK7J4DxlEG92L2',NULL,NULL,'System Admin',1,NULL,21),(13,'Sachin','$2a$12$1nOTwxV4AtDGelfmYYNvt.tHJ4Ru1u4KJ8FDBexqKSTJELedi/wfq',NULL,NULL,'System Admin',1,NULL,22),(14,'Prem_Sagar','$2a$12$0VgTx8mBb6oGd1fbjLfCgOS4DeIGcu0yXFwgZUviubKQBakEtPDgm',NULL,NULL,'System Admin',1,NULL,23),(15,'Deepa','$2a$12$vIQIwx3vLCfsaiSv2a19FuaeWvtHH.DgQHPU5qgzURpgisQrADvx.',NULL,NULL,'System Admin',1,NULL,24);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-24 20:48:16
