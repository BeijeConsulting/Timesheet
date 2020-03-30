CREATE TABLE `formazione` (
  `id_cv` int NOT NULL,
  `id_formazione` int NOT NULL AUTO_INCREMENT,
  `istituto` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `titolo_studio` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `corso_studio` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `anno_inizio` varchar(10) DEFAULT NULL,
  `anno_fine` varchar(10) DEFAULT NULL,
  `valutazione` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `valutazione_max` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tecnologie` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_formazione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
