CREATE DATABASE  IF NOT EXISTS `votoelettronico` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `votoelettronico`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: votoelettronico
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `candidato`
--

DROP TABLE IF EXISTS `candidato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidato` (
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `nomePartito` varchar(45) DEFAULT NULL,
  `numVoti` int DEFAULT NULL,
  `posizione` int DEFAULT NULL,
  `preferenza` tinyint DEFAULT NULL,
  PRIMARY KEY (`nome`,`cognome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidato`
--

LOCK TABLES `candidato` WRITE;
/*!40000 ALTER TABLE `candidato` DISABLE KEYS */;
INSERT INTO `candidato` VALUES ('Giacomo','Verdi','PD',0,0,0),('Sara','Sbarossi','M5S',1,0,0);
/*!40000 ALTER TABLE `candidato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elettore`
--

DROP TABLE IF EXISTS `elettore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elettore` (
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `sesso` varchar(1) DEFAULT NULL,
  `nascita` varchar(20) DEFAULT NULL,
  `citta` varchar(45) DEFAULT NULL,
  `stato` varchar(45) DEFAULT NULL,
  `codFiscale` varchar(16) NOT NULL,
  `countVoto` int DEFAULT NULL,
  PRIMARY KEY (`codFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elettore`
--

LOCK TABLES `elettore` WRITE;
/*!40000 ALTER TABLE `elettore` DISABLE KEYS */;
INSERT INTO `elettore` VALUES ('DARIO','GIALLO','M','10/02/1995','MILANO','Colombia','GLLDRA95B50Z604S',0),('FRANCESCA','GRAZIA','F','09/02/1998','MILANO','Egitto','GRZFNC98B49Z336E',0),('ALESSANDRO','PROVOLA','M','06/02/1992','MILANO','Colombia','PRVLSN92B46Z604B',0),('MARCO','ROSSI','M','14/02/1997','MILANO','Italia','RSSMRC97B54F205V',0);
/*!40000 ALTER TABLE `elettore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partito`
--

DROP TABLE IF EXISTS `partito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partito` (
  `pnome` varchar(45) NOT NULL,
  `posizione` int DEFAULT NULL,
  `preferenza` tinyint DEFAULT NULL,
  `idSchedaP` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`pnome`,`idSchedaP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partito`
--

LOCK TABLES `partito` WRITE;
/*!40000 ALTER TABLE `partito` DISABLE KEYS */;
INSERT INTO `partito` VALUES ('M5S',0,0,456693);
/*!40000 ALTER TABLE `partito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referendum`
--

DROP TABLE IF EXISTS `referendum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `referendum` (
  `domanda` varchar(100) NOT NULL,
  `quorum` double DEFAULT NULL,
  `numVotiPositivi` int DEFAULT NULL,
  `numVotiNegativi` int DEFAULT NULL,
  `numVoti` int DEFAULT NULL,
  PRIMARY KEY (`domanda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referendum`
--

LOCK TABLES `referendum` WRITE;
/*!40000 ALTER TABLE `referendum` DISABLE KEYS */;
INSERT INTO `referendum` VALUES ('Esami in presenza?',0,1,0,1);
/*!40000 ALTER TABLE `referendum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheda`
--

DROP TABLE IF EXISTS `scheda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scheda` (
  `idScheda` int NOT NULL,
  `voto` varchar(40) NOT NULL,
  `vincita` varchar(40) NOT NULL,
  `partito` varchar(45) NOT NULL,
  `referendum` varchar(100) NOT NULL,
  PRIMARY KEY (`idScheda`,`partito`,`referendum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheda`
--

LOCK TABLES `scheda` WRITE;
/*!40000 ALTER TABLE `scheda` DISABLE KEYS */;
INSERT INTO `scheda` VALUES (113362,'referendum','referendumSenzaQuorum','null','Esami in presenza?'),(456693,'categorico','maggioranza','M5S','null');
/*!40000 ALTER TABLE `scheda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `username` varchar(40) NOT NULL,
  `password` varchar(45) NOT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES ('ale','00',1),('alessandro','prova',0),('dario','giallo',0),('francesca','grazia',0),('francesca','lorenzina',0),('francesco','mario',0),('marco','rossi',0);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-23 10:20:14
