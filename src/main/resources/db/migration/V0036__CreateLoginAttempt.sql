CREATE TABLE `login_attempt` (
  `login_attempt_id` INT NOT NULL AUTO_INCREMENT,
  `ip` VARCHAR(45) NOT NULL DEFAULT '',
  `email` VARCHAR(255) NOT NULL DEFAULT '',
  `created_on` DATETIME NOT NULL,
  PRIMARY KEY (`login_attempt_id`));
