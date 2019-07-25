CREATE TABLE `client` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_on` datetime NOT NULL,
  `modified_by` int(11) NOT NULL,
  `modified_on` datetime NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `UK_client_name` (`name`),
  KEY `FK_client_user_created_by` (`created_by`),
  KEY `FK_client_user_modified_by` (`modified_by`),
  CONSTRAINT `FK7hjret7kif1b6mq30npno4cd6` FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKr68itn3rehaehlox31rmeaucx` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB;
