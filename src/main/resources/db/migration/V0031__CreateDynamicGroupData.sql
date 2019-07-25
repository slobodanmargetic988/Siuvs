CREATE TABLE `dynamic_group_data` (
  `group_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_row_id` int(11) NOT NULL,
  `column_id` int(11) NOT NULL,
  `value` varchar(16384),
  `created_by` int(11) NOT NULL,
  `created_on` datetime NOT NULL,
  `modified_by` int(11) NOT NULL,
  `modified_on` datetime NOT NULL,
  PRIMARY KEY (`group_data_id`),
  KEY `FKcewga9ew3u04dfg66613g2wwe` (`column_id`),
  KEY `FK6y2rplpe9e1vaqkenbnk0krrf` (`group_row_id`),
  KEY `FKcgaw9pd11785s7kkddtnlf5r2` (`created_by`),
  KEY `FKd3mhia1imb4653ilu6qspmki8` (`modified_by`),
  CONSTRAINT `FKcewga9ew3u04dfg66613g2wwe` FOREIGN KEY (`column_id`) REFERENCES `table_column` (`column_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK6y2rplpe9e1vaqkenbnk0krrf` FOREIGN KEY (`group_row_id`) REFERENCES `dynamic_group_row` (`group_row_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKcgaw9pd11785s7kkddtnlf5r2` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKd3mhia1imb4653ilu6qspmki8` FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;