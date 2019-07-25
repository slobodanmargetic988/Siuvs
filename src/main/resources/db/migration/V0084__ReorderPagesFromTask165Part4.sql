UPDATE `table_column` SET `title` = 'Капацитет-површина m<sup>2</sup>', `type` = 'NUMBER' WHERE (`column_id` = '24');

ALTER TABLE `table_column`
DROP FOREIGN KEY `FKs66e84ix6hs7gyyecqgcdqpm5`;
ALTER TABLE `table_column`
ADD CONSTRAINT `FKs66e84ix6hs7gyyecqgcdqpm5`
  FOREIGN KEY (`table_definition_id`)
  REFERENCES `table_definition` (`table_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `dynamic_data`
DROP FOREIGN KEY `FKo55liqgi8lf124bm647u0q4t1`;
ALTER TABLE `dynamic_data`
ADD CONSTRAINT `FKo55liqgi8lf124bm647u0q4t1`
  FOREIGN KEY (`column_id`)
  REFERENCES `table_column` (`column_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `dynamic_table`
DROP FOREIGN KEY `FKsuwye04aypilvukeccv8kxnh`;
ALTER TABLE `dynamic_table`
ADD CONSTRAINT `FKsuwye04aypilvukeccv8kxnh`
  FOREIGN KEY (`table_definition_id`)
  REFERENCES `table_definition` (`table_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `dynamic_row`
DROP FOREIGN KEY `FKa50hnsv7lvbmvrqh6uk4a5dck`;
ALTER TABLE `dynamic_row`
ADD CONSTRAINT `FKa50hnsv7lvbmvrqh6uk4a5dck`
  FOREIGN KEY (`table_id`)
  REFERENCES `dynamic_table` (`table_id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

DELETE FROM `table_definition` WHERE (`table_id` = '56');
