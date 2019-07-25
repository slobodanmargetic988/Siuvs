ALTER TABLE `table_column_value` DROP FOREIGN KEY `FK_column_id`;
ALTER TABLE `table_column_value` 
CHANGE COLUMN `column_id` `column_id_internal` INT(11) NOT NULL ,
DROP INDEX `unique_column_id_value` ;

ALTER TABLE `table_column_value` 
ADD CONSTRAINT `FK_column_id_internal`
  FOREIGN KEY (`column_id_internal`)
  REFERENCES `table_column` (`column_id_internal`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
