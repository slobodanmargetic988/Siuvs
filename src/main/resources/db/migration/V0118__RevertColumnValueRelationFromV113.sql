ALTER TABLE `table_column_value` DROP FOREIGN KEY `FK_column_id_internal`;
ALTER TABLE `table_column_value` CHANGE COLUMN `column_id_internal` `column_id` INT(11) NOT NULL;

ALTER TABLE `table_column_value`
ADD CONSTRAINT `FK_column_id`
  FOREIGN KEY (`column_id`)
  REFERENCES `table_column` (`column_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `table_column_value`
ADD UNIQUE INDEX `unique_column_id_value` (`column_id`, `value`);