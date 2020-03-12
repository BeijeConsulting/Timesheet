-- LAST UPDATE 20200312

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `street` varchar(100) NOT NULL,
  `city` varchar(45) NOT NULL,
  `province` varchar(2) NOT NULL,
  `cap` varchar(5) NOT NULL,
  `country` char(2) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  `doc_type` varchar(45) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `upload_date` datetime DEFAULT NULL,
  `attachment_user` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bank_credentials`
--

DROP TABLE IF EXISTS `bank_credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_credentials` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `accountholder` varchar(100) NOT NULL,
  `iban` varchar(27) NOT NULL,
  `swift` varchar(15) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `notes` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `bank_credentials_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `computer`
--

DROP TABLE IF EXISTS `computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `computer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL,
  `model` varchar(20) NOT NULL,
  `cpu` varchar(15) NOT NULL,
  `ram` int(11) NOT NULL,
  `hard_disk` varchar(30) NOT NULL,
  `serial_number` varchar(45) NOT NULL,
  `operating_system` varchar(20) NOT NULL,
  `purchase_date` date NOT NULL,
  `disposal_date` date DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `maintenance` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `serial_number_UNIQUE` (`serial_number`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contract_type`
--

DROP TABLE IF EXISTS `contract_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract_type` (
  `cod` char(1) NOT NULL,
  `description` varchar(30) NOT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contratto`
--

DROP TABLE IF EXISTS `contratto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contratto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `contract_type` char(1) NOT NULL,
  `ccnl` varchar(50) DEFAULT NULL,
  `liv` smallint(6) DEFAULT NULL,
  `minimo_contrattuale` double DEFAULT NULL,
  `superminimo` double DEFAULT NULL,
  `retribuzione_mensile` double DEFAULT NULL,
  `ral` double DEFAULT NULL,
  `netto_mensile` double DEFAULT NULL,
  `costo_interno` double NOT NULL DEFAULT '0',
  `note` varchar(200) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `contract_type` (`contract_type`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `contratto_ibfk_1` FOREIGN KEY (`contract_type`) REFERENCES `contract_type` (`cod`),
  CONSTRAINT `contratto_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(30) NOT NULL,
  `secondary_email` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `fiscal_code` varchar(16) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `birth_place` varchar(50) DEFAULT NULL,
  `nationality` varchar(30) DEFAULT NULL,
  `document` varchar(50) DEFAULT NULL,
  `id_skype` varchar(100) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `archive_date` date DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `pic_url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `fiscal_code` (`fiscal_code`),
  UNIQUE KEY `document` (`document`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_computer`
--

DROP TABLE IF EXISTS `user_computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_computer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `note` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `id_computer` int(11) NOT NULL,
  `mouse` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `lan_adapter` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `id_user_idx` (`id_user`),
  KEY `id_computer_idx` (`id_computer`),
  CONSTRAINT `id_computer` FOREIGN KEY (`id_computer`) REFERENCES `computer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
