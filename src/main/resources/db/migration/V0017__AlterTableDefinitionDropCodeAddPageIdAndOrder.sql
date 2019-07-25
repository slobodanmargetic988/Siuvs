ALTER TABLE `table_definition`
DROP COLUMN `code`,
ADD COLUMN `page_id` INT NOT NULL AFTER `table_id`,
ADD COLUMN `order` INT NOT NULL AFTER `page_id`;
