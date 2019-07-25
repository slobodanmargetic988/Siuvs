INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(227, 20, 9, 0,'Подсетник / упутство за лица одговорна за активирање');
INSERT INTO `table_column` (`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1674, 227, 1, 'Задатак', 'STRING', 1),
(1675, 227, 2, 'Одговорно лице', 'STRING', 1),
(1676, 227, 3, 'Сарађује', 'STRING', 1),
(1677, 227, 4, 'Рок извршења', 'STRING', 1),
(1678, 227, 5, 'Примедба', 'STRING', 1);

UPDATE `table_definition` SET `title` = 'Списак курира-позивара' WHERE (`table_id` = '28');
UPDATE `table_column` SET `order` = '2', `title` = 'Правац / рејон' WHERE (`column_id` = '166');
UPDATE `table_column` SET `order` = '3', `title` = 'Адреса на послу' WHERE (`column_id` = '162');
UPDATE `table_column` SET `order` = '4', `title` = 'Кућна адреса', `col_span` = '1', `row2` = NULL, `col_span2` = '0' WHERE (`column_id` = '165');
UPDATE `table_column` SET `order` = '5', `title` = 'Телефон', `col_span` = '2' WHERE (`column_id` = '163');
UPDATE `table_column` SET `order` = '6', `row2` = 'На послу / мобилни' WHERE (`column_id` = '164');

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(228, 20, 10, 0, 'Преглед екипе за позивање телефоном');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1679, 228, 1, 'Име и презиме','STRING',1,NULL,0),
(1680, 228, 2, 'Радно место','STRING',1,NULL,0),
(1681, 228, 3, 'Адреса на послу','STRING',1,NULL,0),
(1682, 228, 4, 'Кућна адреса','STRING',1,NULL,0),
(1683, 228, 5, 'Телефон','STRING',2,'Кућни',1),
(1684, 228, 6, NULL,'STRING',0,'На послу / мобилни',1);
