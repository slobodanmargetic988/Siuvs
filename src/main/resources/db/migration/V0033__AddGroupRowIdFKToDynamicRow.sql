ALTER TABLE `dynamic_row`
ADD CONSTRAINT `FK5tnicxaxjolpnqfv3pmgq0v6`
  FOREIGN KEY (`group_row_id`)
  REFERENCES `dynamic_group_row` (`group_row_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;