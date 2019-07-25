CREATE TABLE `custom_table_definition` (
  `custom_table_definition_id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `table_definition_id` INT NOT NULL,
  `title` VARCHAR(1024) NOT NULL,
  `created_by` INT NOT NULL,
  `created_on` DATETIME NOT NULL,
  `modified_by` INT NOT NULL,
  `modified_on` DATETIME NOT NULL,
  PRIMARY KEY (`custom_table_definition_id`),
  KEY `FK8h679hoidqpe6lrnw545uprl3` (`client_id`),
  KEY `FKmgax2hlslg17d3h85odmcju20` (`created_by`),
  KEY `FK6sa7aqr4x4rwqsx4fr7pm9rra` (`modified_by`),
  KEY `FKadiiroyhjkhqddvfht8g48j7l` (`table_definition_id`),
  CONSTRAINT `FK6sa7aqr4x4rwqsx4fr7pm9rra` FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK8h679hoidqpe6lrnw545uprl3` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  CONSTRAINT `FKadiiroyhjkhqddvfht8g48j7l` FOREIGN KEY (`table_definition_id`) REFERENCES `table_definition` (`table_id`),
  CONSTRAINT `FKmgax2hlslg17d3h85odmcju20` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`)
);
