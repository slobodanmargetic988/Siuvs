INSERT INTO `page` (`page_id`, `title`, `type`, `parent_id`, `order`) VALUES
(109, 'Преглед водотокова', 'TABLE', 3, 4),
(110, 'Пољопривредне површине', 'TABLE', 4, 4),
(111, 'Преглед штете по пољопривредним културама', 'TABLE', 7, 4),
(112, 'Преглед изворишта', 'TABLE', 9, 4),
(113, 'Преглед броја становника по насељеним местима', 'TABLE', 10, 4),
(114, 'Преглед категоризације објеката са аспекта угрожености од пожара', 'TABLE', 12, 4);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(221, 109, 1, 0, 'Преглед водотокова');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1622, 221, 1, 'Назив водотока', 'STRING', 1),
(1623, 221, 2, 'Приоритет', 'ENUM', 1),
(1624, 221, 3, 'Дужина водотока у km', 'NUMBER', 1),
(1625, 221, 4, 'Слабе тачке', 'STRING', 1);
INSERT INTO `table_column_value` (`id`,`column_id`,`value`,`order`) VALUES
(24, 1623, 'I реда', 1),
(25, 1623, 'II реда', 2);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(222, 110, 1, 0, 'Пољопривредне површине');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1657, 222, 1, 'Оранице и баште, укупно (ha)', 'NUMBER', 1),
(1658, 222, 2, 'Оранице и баште, укупно (%)', 'NUMBER', 1),
(1659, 222, 3, 'Жита (ha)', 'NUMBER', 1),
(1660, 222, 4, 'Жита (%)', 'NUMBER', 1),
(1661, 222, 5, 'Кромпир (ha)', 'NUMBER', 1),
(1662, 222, 6, 'Кромпир (%)', 'NUMBER', 1),
(1663, 222, 7, 'Шећерна репа (ha)', 'NUMBER', 1),
(1664, 222, 8, 'Шећерна репа (%)', 'NUMBER', 1),
(1665, 222, 9, 'Сунцокрет (ha)', 'NUMBER', 1),
(1666, 222, 10, 'Сунцокрет (%)', 'NUMBER', 1),
(1667, 222, 11, 'Соја (ha)', 'NUMBER', 1),
(1668, 222, 12, 'Соја (%)', 'NUMBER', 1),
(1669, 222, 13, 'Поврће (ha)', 'NUMBER', 1),
(1670, 222, 14, 'Поврће (%)', 'NUMBER', 1),
(1671, 222, 15, 'Крмно биље (ha)', 'NUMBER', 1),
(1672, 222, 16, 'Крмно биље (%)', 'NUMBER', 1);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(223, 111, 1, 0, 'Преглед штете по пољопривредним културама');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1634, 223, 1, 'Култура', 'STRING', 1),
(1635, 223, 2, 'Површина под културом (ха)', 'NUMBER', 1),
(1636, 223, 3, 'Процењен просечан принос (t/ха)', 'STRING', 1),
(1637, 223, 4, '% Умањења просечног приноса', 'STRING', 1),
(1638, 223, 5, 'Процена производње у текућој години  (t)', 'STRING', 1),
(1639, 223, 6, 'Произ. на бази вишегодишњег прос. приноса у тонама', 'STRING', 1),
(1640, 223, 7, 'Процена умањења производње у текућој години  у тонама', 'STRING', 1),
(1641, 223, 8, 'Цена (дин/кг)', 'STRING', 1),
(1642, 223, 9, 'Процена вредности умањења прихода у 000 дин.', 'NUMBER', 1);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(224, 112, 1, 0, 'Преглед изворишта');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1643, 224, 1, 'Назив изворишта/фабрике воде', 'STRING', 1),
(1644, 224, 2, 'Насељена места које извориште покрива', 'STRING', 1),
(1645, 224, 3, 'Капацитет постројења за третман воде (Q, l/s)', 'NUMBER', 1),
(1646, 224, 4, 'Исправност воде', 'STRING', 1),
(1647, 224, 5, 'Напомена', 'STRING', 1);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(225, 113, 1, 0, 'Преглед броја становника по насељеним местима');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1648, 225, 1, 'Насеље', 'STRING', 1),
(1649, 225, 2, 'Број становника', 'NUMBER', 1);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(226, 114, 1, 0, 'Преглед категоризације објеката са аспекта угрожености од пожара');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1653, 226, 1, 'Назив објекта', 'STRING', 1),
(1654, 226, 2, 'Адреса', 'STRING', 1),
(1655, 226, 3, 'Категорија', 'ENUM', 1);
INSERT INTO `table_column_value` (`id`,`column_id`,`value`,`order`) VALUES
(29, 1655, 'I категорија', 1),
(30, 1655, 'II категорија', 2),
(31, 1655, 'III категорија', 3);
