ALTER TABLE `user`
ADD CONSTRAINT `FKrl8au09hfjd9742b89li2rb3`
    FOREIGN KEY (`client_id`)
    REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;