INSERT INTO `page` (`page_id`, `title`, `type`, `parent_id`, `order`) VALUES
(107, 'Преглед објеката према години изградње', 'TABLE', 1, 4),
(108, 'Преглед клизишта', 'TABLE', 2, 4);

UPDATE `table_definition` SET `page_id` = 107 WHERE `table_id` = 219;

UPDATE `table_definition` SET `page_id` = 108 WHERE `table_id` = 220;
