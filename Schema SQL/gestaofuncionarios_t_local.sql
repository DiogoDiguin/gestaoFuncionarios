-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: gestaofuncionarios
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.27-MariaDB

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
-- Table structure for table `t_local`
--

DROP TABLE IF EXISTS `t_local`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_local` (
  `idLocal` int(11) NOT NULL AUTO_INCREMENT,
  `enderecoRua` varchar(100) NOT NULL,
  `codigoPostal` varchar(15) NOT NULL,
  `cidade` varchar(45) NOT NULL,
  `estadoProvincia` varchar(5) NOT NULL,
  `pais` int(11) NOT NULL,
  PRIMARY KEY (`idLocal`),
  KEY `paisLocal` (`pais`),
  CONSTRAINT `paisLocal` FOREIGN KEY (`pais`) REFERENCES `t_pais` (`idPais`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_local`
--

LOCK TABLES `t_local` WRITE;
/*!40000 ALTER TABLE `t_local` DISABLE KEYS */;
INSERT INTO `t_local` VALUES (1,'123 Rua Principal','12345','Cidade A','AA',1),(2,'456 Rua Secundária','54321','Cidade B','BB',16),(3,'789 Avenida Central','98765','Cidade C','CC',1),(4,'1011 Praça Central','56789','Cidade D','DD',18),(5,'1213 Rua Principal','24680','Cidade E','EE',19),(6,'1415 Avenida Central','13579','Cidade F','FF',20),(7,'1617 Rua Secundária','98765','Cidade G','GG',21),(8,'1819 Praça Central','12345','Cidade H','HH',22),(9,'2021 Avenida Central','54321','Cidade I','II',23),(10,'2223 Rua X','98777','Cidade J','SP',24);
/*!40000 ALTER TABLE `t_local` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-04  9:30:55
