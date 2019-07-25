CREATE TABLE `table_column_sum` (
  `column_id` INT NOT NULL,
  `sum_column_id` INT NOT NULL,
  PRIMARY KEY (`column_id`, `sum_column_id`),
  INDEX `fk_table_column_sum_2_idx` (`sum_column_id` ASC),
  CONSTRAINT `fk_table_column_sum_1`
    FOREIGN KEY (`column_id`)
    REFERENCES `table_column` (`column_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_table_column_sum_2`
    FOREIGN KEY (`sum_column_id`)
    REFERENCES `table_column` (`column_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
