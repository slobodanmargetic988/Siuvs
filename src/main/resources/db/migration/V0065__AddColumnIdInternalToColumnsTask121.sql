ALTER TABLE `table_column`
ADD COLUMN `column_id_internal` INT(11) NULL DEFAULT NULL AFTER `column_id`,
ADD UNIQUE INDEX `column_id_internal_UNIQUE` (`column_id_internal` ASC);