CREATE TABLE `password_reset_token` (
  `token_id` INT NOT NULL AUTO_INCREMENT,
  `token` VARCHAR(64) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `expires_on` DATETIME NOT NULL,
  PRIMARY KEY (`token_id`),
  UNIQUE INDEX `token_UNIQUE` (`token` ASC),
  INDEX `fk_password_reset_token_user_id_index` (`user_id` ASC),
    CONSTRAINT `FK5lwtbncug84d4ero33v3cfxvl`
      FOREIGN KEY (`user_id`)
      REFERENCES `user` (`user_id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE);