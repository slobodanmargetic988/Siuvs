ALTER TABLE `dynamic_group_row`
ADD CONSTRAINT `FKeg8q9kfqh4fnuo1v2j3eqebtt`
  FOREIGN KEY (`custom_table_definition_id`)
  REFERENCES `custom_table_definition` (`custom_table_definition_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;