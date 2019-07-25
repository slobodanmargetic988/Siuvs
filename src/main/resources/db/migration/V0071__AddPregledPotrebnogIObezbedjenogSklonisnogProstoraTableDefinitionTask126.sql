INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('98', '28', '4', '0', 'Преглед потребног и обезбеђеног склонишног простора');

INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `type`, `is_dynamic`, `title`, `col_span`, `row2`, `col_span2`, `row3`, `col_span3`, `row4`) VALUES
('656', '98', '1', 'STRING', '0', 'Насеље, месна заједница, адреса', '1', NULL, '0', NULL, '0', NULL),
('657', '98', '2', 'NUMBER', '0', 'Потребан број склонишних места', '1', NULL, '0', NULL, '0', NULL),
('658', '98', '3', 'NUMBER', '0', 'Обезбеђен број места за склањање', '9', 'Склоништа', '2', NULL, '0', 'Број'),
('659', '98', '4', 'NUMBER', '0', NULL, '0', NULL, '0', NULL, '0', 'Капацитет'),
('660', '98', '5', 'NUMBER', '0', NULL, '0', 'Заклони – прилагођени простори', '6', 'Подрумске и друге просторије у зградама', '2', 'Број'),
('661', '98', '6', 'NUMBER', '0', NULL, '0', NULL, '0', NULL, '0', 'Капацитет'),
('662', '98', '7', 'NUMBER', '0', NULL, '0', NULL, '0', 'Природни комунални и други објекти', '2', 'Број'),
('663', '98', '8', 'NUMBER', '0', NULL, '0', NULL, '0', NULL, '0', 'Капацитет'),
('664', '98', '9', 'NUMBER', '0', NULL, '0', NULL, '0', 'Укупно заклона', '2', 'Број'),
('665', '98', '10', 'NUMBER', '0', NULL, '0', NULL, '0', NULL, '0', 'Капацитет'),
('666', '98', '11', 'SUM', '0', NULL, '0', 'Укупно обезбеђено', '1', NULL, '0', 'Капацитет');


INSERT INTO `table_column_sum` (`column_id`, `sum_column_id`) VALUES
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '666' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '659' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '666' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '661' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '666' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '663' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '666' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '665' LIMIT 1)
);