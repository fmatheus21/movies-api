-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: localhost    Database: movies
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_person` int NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(70) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_person_contact_idx` (`id_person`),
  CONSTRAINT `fk_person_contact` FOREIGN KEY (`id_person`) REFERENCES `person` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `error_system`
--

DROP TABLE IF EXISTS `error_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `error_system` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name_class` varchar(100) NOT NULL,
  `name_method` varchar(100) NOT NULL,
  `message` longtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `error_system`
--

LOCK TABLES `error_system` WRITE;
/*!40000 ALTER TABLE `error_system` DISABLE KEYS */;
INSERT INTO `error_system` VALUES (1,'2022-02-05 23:53:42','com.fmatheus.app.controller.storage.test.FilesStorageServiceImpl','save','upload/media/movies/1644105221948.png'),(2,'2022-02-06 00:09:54','com.fmatheus.app.controller.storage.test.FilesStorageServiceImpl','save','MultipartFile resource [file] cannot be resolved to absolute file path'),(3,'2022-02-06 00:11:11','com.fmatheus.app.controller.storage.test.FilesStorageServiceImpl','save','MultipartFile resource [file] cannot be resolved to absolute file path'),(4,'2022-02-06 01:39:52','com.fmatheus.app.controller.storage.FilesStorageServiceImpl','save','Cannot invoke \"String.isEmpty()\" because \"str\" is null'),(5,'2022-02-06 01:40:39','com.fmatheus.app.controller.storage.FilesStorageServiceImpl','save','Cannot invoke \"String.isEmpty()\" because \"str\" is null'),(6,'2022-02-06 01:42:27','com.fmatheus.app.controller.storage.FilesStorageServiceImpl','save','Cannot invoke \"String.isEmpty()\" because \"str\" is null'),(7,'2022-02-06 01:42:54','com.fmatheus.app.controller.storage.FilesStorageServiceImpl','save','Cannot invoke \"String.isEmpty()\" because \"str\" is null'),(8,'2022-02-06 01:54:19','com.fmatheus.app.controller.storage.FilesStorageServiceImpl','save','Specified format is not supported: .png');
/*!40000 ALTER TABLE `error_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code_imdb` varchar(15) NOT NULL,
  `title` varchar(255) NOT NULL,
  `year` int NOT NULL,
  `rating` decimal(2,1) NOT NULL,
  `url_trailer` longtext,
  `image` varchar(30) NOT NULL,
  `created_by` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` int NOT NULL,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`),
  UNIQUE KEY `image_UNIQUE` (`image`),
  UNIQUE KEY `code_imdb_UNIQUE` (`code_imdb`),
  KEY `fk_created_idx` (`created_by`),
  KEY `fk_updated_idx` (`updated_by`),
  CONSTRAINT `fk_created` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`) ON DELETE RESTRICT,
  CONSTRAINT `fk_updated` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (25,'3402236','Assassinato No Expresso Do Oriente',2017,6.5,'https://www.youtube.com/watch?v=1LqXLJEq4sw','1644117911140.png',1,'2022-02-06 03:25:11',1,'2022-02-06 03:25:11'),(26,'11083552','Infiltrado',2021,7.1,'https://www.youtube.com/watch?v=U6lq_G4VR60','1644118109073.png',1,'2022-02-06 03:28:29',1,'2022-02-06 03:28:29'),(27,'7888964','Anônimo',2021,7.4,'https://www.youtube.com/watch?v=Fn1MT681zeE','1644118456183.png',1,'2022-02-06 03:34:17',1,'2022-02-06 03:34:17');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_person_type` int NOT NULL,
  `name` varchar(70) NOT NULL,
  `document` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `document_UNIQUE` (`document`),
  KEY `fk_person_type_idx` (`id_person_type`),
  CONSTRAINT `fk_person_type` FOREIGN KEY (`id_person_type`) REFERENCES `person_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,1,'FERNANDO MATHUES','00000000000'),(2,1,'GUSTAVO RAMOS','11111111111');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_type`
--

DROP TABLE IF EXISTS `person_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_type`
--

LOCK TABLES `person_type` WRITE;
/*!40000 ALTER TABLE `person_type` DISABLE KEYS */;
INSERT INTO `person_type` VALUES (1,'PESSOA FÍSICA'),(2,'PESSOA JURÍDICA');
/*!40000 ALTER TABLE `person_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `method` varchar(10) NOT NULL,
  `role` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `role_UNIQUE` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'GET','LIST_MOVIES','LISTAR FILMES'),(2,'GET','VIEW_MOVIES','VISUALIZAR FILME'),(3,'POST','CREATE_MOVIES','CRIAR FILME'),(4,'PUT','UPDATE_MOVIES','ATUALIZAR FILME'),(5,'DELETE','DELETE_MOVIES','EXCLUIR FILME');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_person` int NOT NULL,
  `id_user_status` int NOT NULL,
  `avatar` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(70) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `avatar_UNIQUE` (`avatar`),
  KEY `fk_status_idx` (`id_user_status`),
  KEY `fk_person_idx` (`id_person`),
  CONSTRAINT `fk_person_user` FOREIGN KEY (`id_person`) REFERENCES `person` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_status` FOREIGN KEY (`id_user_status`) REFERENCES `user_status` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,1,'1628041520118.png','FMATHEUS','$2a$10$LvtCtBtxJyrviMbU.C/Re.nfj3xRBbRVdbzNcgj8pjURJAN9XlIWC','2022-01-19 22:18:22','2022-01-19 22:18:22'),(2,2,1,'1628041597038.png','GRAMOS','$2a$10$LvtCtBtxJyrviMbU.C/Re.nfj3xRBbRVdbzNcgj8pjURJAN9XlIWC','2022-01-19 22:22:23','2022-01-19 22:22:23');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_join`
--

DROP TABLE IF EXISTS `user_role_join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role_join` (
  `id_user` int NOT NULL,
  `id_role` int NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `fk_permission_idx` (`id_role`),
  CONSTRAINT `fk_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`) ON DELETE RESTRICT,
  CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_join`
--

LOCK TABLES `user_role_join` WRITE;
/*!40000 ALTER TABLE `user_role_join` DISABLE KEYS */;
INSERT INTO `user_role_join` VALUES (1,1),(2,1),(1,2),(2,2),(1,3),(1,4),(1,5);
/*!40000 ALTER TABLE `user_role_join` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_status`
--

DROP TABLE IF EXISTS `user_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_status`
--

LOCK TABLES `user_status` WRITE;
/*!40000 ALTER TABLE `user_status` DISABLE KEYS */;
INSERT INTO `user_status` VALUES (1,'ATIVO'),(2,'INATIVO');
/*!40000 ALTER TABLE `user_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-06 22:00:15
