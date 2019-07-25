INSERT INTO `page` (`page_id`,`title`,`type`,`parent_id`,`order`) VALUES (69,'План заштите и спасавања','MENU',NULL,1);

UPDATE `page` SET parent_id = 69 WHERE page_id IN (18, 19, 20, 22, 23);

UPDATE `page` SET `order` = `order` - 1 WHERE page_id IN (22, 23);

UPDATE `page` SET `order` = 1 WHERE page_id = 21;

INSERT INTO `page` (`page_id`,`title`,`type`,`parent_id`,`order`) VALUES (70,'План заштите и спасавања по врстама опасности','MENU',NULL,2);

UPDATE `page` SET parent_id = 70 WHERE page_id IN (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

UPDATE `page` SET parent_id = 1, `order` = 2, title = 'План заштите и спасавања' WHERE page_id = 21;

DELETE FROM `page` WHERE page_id = 15;

DELETE FROM `page` WHERE page_id IN (15, 31, 36, 41, 46, 51, 56, 61, 66);
