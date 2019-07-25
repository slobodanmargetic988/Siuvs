ALTER TABLE `table_column`
ADD COLUMN `parent_column_id` INT(11) NULL DEFAULT NULL AFTER `column_id_internal`,
ADD COLUMN `is_dynamic` TINYINT(4) NOT NULL DEFAULT '0' AFTER `type`;
