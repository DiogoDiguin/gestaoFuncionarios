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
-- Table structure for table `t_departamento`
--

DROP TABLE IF EXISTS `t_departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_departamento` (
  `idDpto` int(11) NOT NULL AUTO_INCREMENT,
  `nomeDpto` varchar(60) NOT NULL,
  `gerente` int(11) NOT NULL,
  `local` int(11) NOT NULL,
  PRIMARY KEY (`idDpto`),
  KEY `gerenteDpto` (`gerente`),
  KEY `localDpto` (`local`),
  CONSTRAINT `gerenteDpto` FOREIGN KEY (`gerente`) REFERENCES `t_funcionario` (`idFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `localDpto` FOREIGN KEY (`local`) REFERENCES `t_local` (`idLocal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_departamento`
--

LOCK TABLES `t_departamento` WRITE;
/*!40000 ALTER TABLE `t_departamento` DISABLE KEYS */;
INSERT INTO `t_departamento` VALUES (1,'Vendas',1,1),(2,'Recursos Humanos',5,2),(3,'Desenvolvimento',22,10),(4,'Contabilidade',9,4),(5,'Marketing',11,5),(6,'Atendimento ao Cliente',2,1),(7,'Produtivo',1,8),(8,'Logística',6,3),(9,'Tecnologia da Informação',8,4),(11,'Tecnologia',7,6),(12,'Gerência',10,10);
/*!40000 ALTER TABLE `t_departamento` ENABLE KEYS */;
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
