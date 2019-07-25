CREATE TABLE `login_log` (
  `login_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_on` datetime NOT NULL,
  PRIMARY KEY (`login_log_id`),
  KEY `FKa9ca70emkdxbpw4u0voihgers` (`user_id`),
  CONSTRAINT `FKa9ca70emkdxbpw4u0voihgers` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;