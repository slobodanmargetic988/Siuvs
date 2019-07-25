ALTER TABLE `dynamic_row`
ADD CONSTRAINT `fk_row_user_created_by`
  FOREIGN KEY (`created_by`)
  REFERENCES `user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_row_user_modified_by`
  FOREIGN KEY (`modified_by`)
  REFERENCES `user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;