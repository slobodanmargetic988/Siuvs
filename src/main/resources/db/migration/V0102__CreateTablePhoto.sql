CREATE TABLE `photo` (
  `photo_id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `page_id` INT NOT NULL,
  `title` VARCHAR(1024) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `filename` CHAR(36) NOT NULL,
  `created_by` INT NOT NULL,
  `created_on` DATETIME NOT NULL,
  PRIMARY KEY (`photo_id`),
  INDEX `fk_photo_client_idx` (`client_id` ASC),
  INDEX `fk_photo_page_idx` (`page_id` ASC),
  INDEX `fk_photo_created_by_idx` (`created_by` ASC),
  CONSTRAINT `fk_photo_client`
    FOREIGN KEY (`client_id`)
    REFERENCES `client` (`client_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_photo_page`
    FOREIGN KEY (`page_id`)
    REFERENCES `page` (`page_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_photo_created_by`
    FOREIGN KEY (`created_by`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
