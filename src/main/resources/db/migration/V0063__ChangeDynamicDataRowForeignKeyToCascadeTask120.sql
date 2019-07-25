  ALTER TABLE `dynamic_data`
  DROP FOREIGN KEY `FKtfhow2u5thfukbtepmcw2br0y`;
  ALTER TABLE `dynamic_data`
  ADD CONSTRAINT `FKtfhow2u5thfukbtepmcw2br0y`
    FOREIGN KEY (`row_id`)
    REFERENCES `dynamic_row` (`row_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE;