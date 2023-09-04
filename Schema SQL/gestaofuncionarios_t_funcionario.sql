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
-- Table structure for table `t_funcionario`
--

DROP TABLE IF EXISTS `t_funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_funcionario` (
  `idFuncionario` int(11) NOT NULL AUTO_INCREMENT,
  `primeiroNome` varchar(15) NOT NULL,
  `ultimoNome` varchar(15) DEFAULT NULL,
  `salario` double NOT NULL,
  `departamento` int(11) NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  KEY `departamentoFuncionario` (`departamento`),
  CONSTRAINT `departamentoFuncionario` FOREIGN KEY (`departamento`) REFERENCES `t_departamento` (`idDpto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_funcionario`
--

LOCK TABLES `t_funcionario` WRITE;
/*!40000 ALTER TABLE `t_funcionario` DISABLE KEYS */;
INSERT INTO `t_funcionario` VALUES (1,'Jão','',4400,11),(2,'Maria',NULL,4200,3),(3,'Pedro','Ferreira',2800,11),(4,'Ana','Rodrigues',3900,6),(5,'Carlos',NULL,3200,11),(6,'Sofia','Oliveira',4800,7),(7,'Miguel','Costa',2900,2),(8,'Beatriz','Araújo',3960,4),(9,'Ricardo','Pereira',4100,9),(10,'Catarina','Cardoso',3400,5),(11,'André',NULL,2700,1),(13,'Fernando','Martins',3100,3),(14,'Lúcia','Ribeiro',4500,8),(15,'Hugo','Lopes',3300,6),(20,'Diogo','Vitor',1520,11),(22,'Luiz','Neto',2500,6),(26,'Diogão','Zika',1553.53,11),(27,'Diguin','Brabo',2.2,8);
/*!40000 ALTER TABLE `t_funcionario` ENABLE KEYS */;
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
