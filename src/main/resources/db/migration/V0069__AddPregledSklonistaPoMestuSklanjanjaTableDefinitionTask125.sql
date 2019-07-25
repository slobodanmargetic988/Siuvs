INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('97', '28', '2', '0', 'Преглед склоништа по месту склањања');

INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `type`, `is_dynamic`, `title`, `col_span`, `row2`, `col_span2`, `row3`) VALUES
('645', '97', '1', 'STRING', '0', 'Насеље, месна заједница, адреса', '1', NULL, '0', NULL),
('646', '97', '2', 'NUMBER', '0', 'Склоништа по месту становања', '4', 'Кућна', '2', 'Број'),
('647', '97', '3', 'NUMBER', '0', NULL, '0', NULL, '0', 'Капацитет'),
('648', '97', '4', 'NUMBER', '0', NULL, '0', 'Блоковска', '2', 'Број'),
('649', '97', '5', 'NUMBER', '0', NULL, '0', NULL, '0', 'Капацитет'),
('650', '97', '6', 'NUMBER', '0', 'Склоништа по месту рада', '2', 'Предузећа/установе', '2', 'Број'),
('651', '97', '7', 'NUMBER', '0', NULL, '0', NULL, '0', 'Капацитет'),
('652', '97', '8', 'NUMBER', '0', 'Склоништа на јавном месту', '2', 'Јавна', '2', 'Број'),
('653', '97', '9', 'NUMBER', '0', NULL, '0', NULL, '0', 'Капацитет'),
('654', '97', '10', 'SUM', '0', 'Ukupno', '2', NULL, '2', 'Број'),
('655', '97', '11', 'SUM', '0', NULL, '0', NULL, '0', 'Капацитет');

INSERT INTO `table_column_sum` (`column_id`, `sum_column_id`) VALUES
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '654' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '646' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '654' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '648' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '654' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '650' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '654' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '652' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '655' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '647' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '655' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '649' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '655' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '651' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '655' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '653' LIMIT 1)
);