UPDATE `table_column` SET `type`='ENUM' WHERE `column_id` IN ('244', '231');
INSERT INTO `table_column_value` (`id`, `column_id`, `value`, `order`) VALUES 
('1', '244', 'Руководилац', '1'),
('2', '244', 'Члан', '2'),
('3', '231', 'Руководилац', '1'),
('4', '231', 'Члан', '2');