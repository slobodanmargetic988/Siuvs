CREATE TABLE `table_column` (
  `column_id` INT NOT NULL AUTO_INCREMENT,
  `table_definition_id` INT NOT NULL,
  `title` VARCHAR(1024) NOT NULL,
  `type` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`column_id`),
  KEY `FKs66e84ix6hs7gyyecqgcdqpm5` (`table_definition_id`),
  CONSTRAINT `FKs66e84ix6hs7gyyecqgcdqpm5` FOREIGN KEY (`table_definition_id`) REFERENCES `table_definition` (`table_id`)
) ENGINE=InnoDB;