CREATE TABLE `dynamic_data` (
  `row_id` int(11) NOT NULL,
  `column_id` int(11) NOT NULL,
  `value` varchar(16384) NOT NULL,
  PRIMARY KEY (`row_id`,`column_id`),
  KEY `FKo55liqgi8lf124bm647u0q4t1` (`column_id`),
  CONSTRAINT `FKo55liqgi8lf124bm647u0q4t1` FOREIGN KEY (`column_id`) REFERENCES `table_column` (`column_id`),
  CONSTRAINT `FKtfhow2u5thfukbtepmcw2br0y` FOREIGN KEY (`row_id`) REFERENCES `dynamic_row` (`row_id`)
) ENGINE=InnoDB;