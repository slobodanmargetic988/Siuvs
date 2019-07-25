ALTER TABLE `table_definition`
ADD CONSTRAINT `FKokkc59nt4meqkc7evckyqmyr0`
  FOREIGN KEY (`page_id`)
  REFERENCES `page` (`page_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;