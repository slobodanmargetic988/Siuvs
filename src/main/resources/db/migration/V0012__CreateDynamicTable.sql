CREATE TABLE `dynamic_table` (
  `table_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `table_definition_id` int(11) NOT NULL,
  PRIMARY KEY (`table_id`),
  KEY `FK97gn6r1yu13fdyf1lypehwa32` (`client_id`),
  KEY `FKsuwye04aypilvukeccv8kxnh` (`table_definition_id`),
  CONSTRAINT `FK97gn6r1yu13fdyf1lypehwa32` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  CONSTRAINT `FKsuwye04aypilvukeccv8kxnh` FOREIGN KEY (`table_definition_id`) REFERENCES `table_definition` (`table_id`)
) ENGINE=InnoDB;