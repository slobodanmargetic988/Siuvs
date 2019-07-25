ALTER TABLE `custom_table_definition`
DROP FOREIGN KEY `FKadiiroyhjkhqddvfht8g48j7l`;
ALTER TABLE `custom_table_definition`
ADD CONSTRAINT `FKadiiroyhjkhqddvfht8g48j7l`
  FOREIGN KEY (`table_definition_id`)
  REFERENCES `table_definition` (`table_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `dynamic_table` 
DROP FOREIGN KEY `FKoh0pac037pnvpm4dfktem40i8`;
ALTER TABLE `dynamic_table` 
ADD CONSTRAINT `FKoh0pac037pnvpm4dfktem40i8`
  FOREIGN KEY (`custom_table_definition_id`)
  REFERENCES `custom_table_definition` (`custom_table_definition_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `dynamic_group_row` 
DROP FOREIGN KEY `FKeg8q9kfqh4fnuo1v2j3eqebtt`;
ALTER TABLE `dynamic_group_row` 
ADD CONSTRAINT `FKeg8q9kfqh4fnuo1v2j3eqebtt`
  FOREIGN KEY (`custom_table_definition_id`)
  REFERENCES `custom_table_definition` (`custom_table_definition_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `dynamic_row` 
DROP FOREIGN KEY `FK5tnicxaxjolpnqfv3pmgq0v6`;
ALTER TABLE `dynamic_row` 
ADD CONSTRAINT `FK5tnicxaxjolpnqfv3pmgq0v6`
  FOREIGN KEY (`group_row_id`)
  REFERENCES `dynamic_group_row` (`group_row_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `dynamic_group_data` 
DROP FOREIGN KEY `FK6y2rplpe9e1vaqkenbnk0krrf`,
DROP FOREIGN KEY `FKcewga9ew3u04dfg66613g2wwe`;
ALTER TABLE `dynamic_group_data` 
ADD CONSTRAINT `FK6y2rplpe9e1vaqkenbnk0krrf`
  FOREIGN KEY (`group_row_id`)
  REFERENCES `dynamic_group_row` (`group_row_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FKcewga9ew3u04dfg66613g2wwe`
  FOREIGN KEY (`column_id`)
  REFERENCES `table_column` (`column_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

UPDATE `page` SET `title` = 'Очување добара битних за опстанак', `parent_id` = '22' WHERE (`page_id` = '72');
UPDATE `page` SET `title` = 'Употреба снага и субјеката заштите и спасавања', `parent_id` = '22' WHERE (`page_id` = '73');

DELETE FROM `table_definition` WHERE (`table_id` = '22');
DELETE FROM `table_definition` WHERE (`table_id` = '116');
DELETE FROM `table_definition` WHERE (`table_id` = '118');
DELETE FROM `table_definition` WHERE (`table_id` = '117');
DELETE FROM `table_definition` WHERE (`table_id` = '119');
DELETE FROM `table_definition` WHERE (`table_id` = '120');
DELETE FROM `table_definition` WHERE (`table_id` = '121');
DELETE FROM `table_definition` WHERE (`table_id` = '99');
DELETE FROM `table_definition` WHERE (`table_id` = '100');
DELETE FROM `table_definition` WHERE (`table_id` = '101');
DELETE FROM `table_definition` WHERE (`table_id` = '102');
DELETE FROM `table_definition` WHERE (`table_id` = '103');

DELETE FROM `page` WHERE (`page_id` = '74');
DELETE FROM `page` WHERE (`page_id` = '75');
DELETE FROM `page` WHERE (`page_id` = '23');
DELETE FROM `page` WHERE (`page_id` = '71');
