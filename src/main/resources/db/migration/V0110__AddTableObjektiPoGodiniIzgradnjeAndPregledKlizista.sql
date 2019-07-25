INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(219, 21, 8, 0, 'Преглед објеката према години изградње');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1596, 219, 1, 'Град/општина', 'STRING', 1, NULL, 0),
(1597, 219, 2, 'Укупно', 'SUM', 1, NULL, 0),
(1598, 219, 3, '1919', 'NUMBER', 1, NULL, 0),
(1599, 219, 4, '1919-1945', 'NUMBER', 1, NULL, 0),
(1600, 219, 5, '1946-1960', 'NUMBER', 1, NULL, 0),
(1601, 219, 6, '1961-1970', 'NUMBER', 1, NULL, 0),
(1602, 219, 7, '1971-1980', 'NUMBER', 1, NULL, 0),
(1603, 219, 8, '1981-1990', 'NUMBER', 1, NULL, 0),
(1604, 219, 9, '1991-2000', 'NUMBER', 1, NULL, 0),
(1605, 219, 10, '2001-2005', 'NUMBER', 1, NULL, 0),
(1606, 219, 11, '2006 или касније', 'NUMBER', 1, NULL, 0),
(1607, 219, 12, 'Непозната година', 'NUMBER', 1, NULL, 0);

INSERT INTO `table_column_sum` (`column_id`, `sum_column_id`) VALUES
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1597 LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1598 LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1597 LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1599 LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1597 LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1600 LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1597 LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1601 LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1597 LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1602 LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1597 LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1603 LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1597 LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1604 LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1597 LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1605 LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1597 LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1606 LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1597 LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = 1607 LIMIT 1)
);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
(220, 30, 8, 0, 'Преглед клизишта');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `type`, `title`, `col_span`, `row2`, `col_span2`) VALUES
(1608, 220, 1, 'STRING', 'Ознака клизишта/Месна заједница', 1, NULL, 0),
(1609, 220, 2, 'NUMBER', 'активно/пасивно', 1, NULL, 0),
(1610, 220, 3, 'NUMBER', 'дужина у m', 1, NULL, 0),
(1611, 220, 4, 'NUMBER', 'ширина у m', 1, NULL, 0),
(1612, 220, 5, 'NUMBER', 'површина у m<sup>2</sup>', 1, NULL, 0),
(1613, 220, 6, 'NUMBER', 'Угрожене штићене вредности', 9, 'Објекти', 1),
(1614, 220, 7, 'NUMBER', NULL, 0, 'Пољопривредно земљиште (hа)', 1),
(1615, 220, 8, 'NUMBER', NULL, 0, 'Општински путеви (km)', 1),
(1616, 220, 9, 'NUMBER', NULL, 0, 'Некатегорисани путеви (km)', 1),
(1617, 220, 10, 'NUMBER', NULL, 0, 'Трафо станице', 1),
(1618, 220, 11, 'NUMBER', NULL, 0, 'Ел. мрежа (km)', 1),
(1619, 220, 12, 'NUMBER', NULL, 0, 'ПТТ мрежа (km)', 1),
(1620, 220, 13, 'NUMBER', NULL, 0, 'Мостови', 1),
(1621, 220, 14, 'NUMBER', NULL, 0, 'Водовод (km)', 1);
