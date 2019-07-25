ALTER TABLE `dynamic_group_row` 
ADD COLUMN `custom_table_definition_id` INT(11) NULL AFTER `table_definition_id`,
ADD INDEX `FKeg8q9kfqh4fnuo1v2j3eqebtt` (`custom_table_definition_id` ASC);