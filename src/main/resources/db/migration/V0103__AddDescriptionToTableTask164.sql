ALTER TABLE `dynamic_table`
ADD COLUMN `description` VARCHAR(2048) NULL DEFAULT '' AFTER `custom_table_definition_id`;
