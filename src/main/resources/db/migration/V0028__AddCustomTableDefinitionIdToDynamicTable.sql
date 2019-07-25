ALTER TABLE `dynamic_table`
ADD COLUMN `custom_table_definition_id` INT NULL AFTER `table_definition_id`,
ADD INDEX `FKoh0pac037pnvpm4dfktem40i8` (`custom_table_definition_id` ASC);
