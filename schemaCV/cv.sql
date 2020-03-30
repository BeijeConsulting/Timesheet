CREATE TABLE `cv` (
  `id_cv` int NOT NULL,
  `id_user` int NOT NULL,
  `title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notes` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_cv`,`id_user`),
  UNIQUE KEY `id_cv_UNIQUE` (`id_cv`),
  UNIQUE KEY `id_utente_UNIQUE` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci