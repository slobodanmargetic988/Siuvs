CREATE TABLE `dynamic_row` (
  `row_id` int(11) NOT NULL AUTO_INCREMENT,
  `table_id` int(11) NOT NULL,
  PRIMARY KEY (`row_id`),
  KEY `FKa50hnsv7lvbmvrqh6uk4a5dck` (`table_id`),
  CONSTRAINT `FKa50hnsv7lvbmvrqh6uk4a5dck` FOREIGN KEY (`table_id`) REFERENCES `dynamic_table` (`table_id`)
) ENGINE=InnoDB;