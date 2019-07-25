CREATE TABLE `table_column_value` (
  `id` int(11) NOT NULL,
  `column_id` int(11) NOT NULL,
  `value` varchar(64) NOT NULL,
  `order` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_column_id_value` (`column_id`,`value`),
  KEY `fk_table_column_values_1_idx` (`column_id`),
  CONSTRAINT `FK_column_id`
    FOREIGN KEY (`column_id`)
    REFERENCES `table_column` (`column_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;