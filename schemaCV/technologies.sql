CREATE TABLE `technologies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `technology` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tecnology_UNIQUE` (`technology`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci