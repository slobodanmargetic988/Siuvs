DELETE FROM dynamic_table WHERE table_definition_id = 37;

DELETE FROM custom_table_definition WHERE table_definition_id = 37;

UPDATE `table_definition` SET `user_defined` = '0' WHERE (`table_id` = '37');
