CREATE TABLE `page` (
  `page_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) NOT NULL,
  `type` varchar(16) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `order` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`page_id`),
  KEY `FK_page_page_parent_id_idx` (`parent_id`),
  CONSTRAINT `FKm3her2s210fu9951dqn3lmvjn` FOREIGN KEY (`parent_id`) REFERENCES `page` (`page_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;