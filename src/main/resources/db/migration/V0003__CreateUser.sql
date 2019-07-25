CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `created_by` int(11) NOT NULL,
  `created_on` datetime NOT NULL,
  `modified_by` int(11) NOT NULL,
  `modified_on` datetime NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_user_user_created_by` (`created_by`),
  KEY `FK_user_user_modified_by` (`modified_by`),
  CONSTRAINT `FKdltbr5t0nljpuuo4isxgslt82` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKo4omy56oktgee21ndp4c7vkkb` FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB;