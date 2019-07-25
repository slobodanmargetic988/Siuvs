DELETE FROM `table_column_value` WHERE `id` IN ('5', '6', '24', '25', '29', '30', '31');
INSERT INTO `table_column_value` (`id`, `column_id`, `value`, `order`) VALUES
('5', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '341' LIMIT 1), 'Главни', '1'),
('6', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '341' LIMIT 1), 'Помоћни', '2'),
('24', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '1623' LIMIT 1), 'I реда', '1'),
('25', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '1623' LIMIT 1), 'II реда', '2'),
('29', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '1655' LIMIT 1), 'I категорија', '1'),
('30', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '1655' LIMIT 1), 'II категорија', '2'),
('31', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '1655' LIMIT 1), 'III категорија', '3');