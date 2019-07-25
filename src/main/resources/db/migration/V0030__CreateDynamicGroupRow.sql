CREATE TABLE `dynamic_group_row` (
  `group_row_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `table_definition_id` int(11) NOT NULL,
  PRIMARY KEY (`group_row_id`),
  KEY `FKeu179r6l16cap6vrdfu529cpn` (`table_definition_id`),
  KEY `FKika6b7du1c0tadjsquf7nbdai` (`client_id`),
  CONSTRAINT `FKeu179r6l16cap6vrdfu529cpn` FOREIGN KEY (`table_definition_id`) REFERENCES `table_definition` (`table_id`),
  CONSTRAINT `FKika6b7du1c0tadjsquf7nbdai` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB;