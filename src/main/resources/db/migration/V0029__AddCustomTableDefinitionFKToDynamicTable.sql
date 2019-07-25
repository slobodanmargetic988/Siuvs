ALTER TABLE `dynamic_table`
ADD CONSTRAINT `FKoh0pac037pnvpm4dfktem40i8`
  FOREIGN KEY (`custom_table_definition_id`)
  REFERENCES `custom_table_definition` (`custom_table_definition_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;