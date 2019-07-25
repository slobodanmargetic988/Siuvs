ALTER TABLE `dynamic_row`
ADD COLUMN `group_row_id` INT NULL AFTER `table_id`,
ADD INDEX `FK5tnicxaxjolpnqfv3pmgq0v6` (`group_row_id` ASC);