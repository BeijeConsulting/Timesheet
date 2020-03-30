CREATE TABLE `certification` (
  `id_certification` int NOT NULL,
  `id_cv` int DEFAULT NULL,
  `title` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `institution` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rating` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `technologies` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_certification`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci