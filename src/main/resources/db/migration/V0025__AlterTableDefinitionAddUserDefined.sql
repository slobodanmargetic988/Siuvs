ALTER TABLE `table_definition`
ADD COLUMN `user_defined` TINYINT NOT NULL DEFAULT 0 AFTER `order`;
