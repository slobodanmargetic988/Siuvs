UPDATE `table_column` SET `type`='NUMBER' WHERE `column_id` IN ('561', '562', '563');
UPDATE `table_column` SET `title`='Укупан број болесних и лица са посебним потребама', `type`='SUM' WHERE `column_id`='564';
INSERT INTO `table_column_sum` (`column_id`, `sum_column_id`) VALUES ('564', '562'), ('564', '563');