UPDATE table_definition SET user_defined=0 WHERE table_id=39;

DELETE FROM custom_table_definition WHERE table_definition_id = 39;
DELETE FROM dynamic_data WHERE row_id IN (SELECT row_id FROM dynamic_row WHERE table_id IN (SELECT table_id FROM dynamic_table WHERE table_definition_id = 39));
DELETE FROM dynamic_row WHERE table_id IN (SELECT table_id FROM dynamic_table WHERE table_definition_id = 39);
DELETE FROM dynamic_table WHERE table_definition_id = 39;
DELETE FROM dynamic_group_data WHERE group_row_id IN (SELECT group_row_id FROM dynamic_group_row WHERE table_definition_id=39);
DELETE FROM dynamic_group_row WHERE table_definition_id=39;