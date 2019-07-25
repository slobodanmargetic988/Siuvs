INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES ('51', '24', '3', '0', 'Преглед праваца евакуације');
INSERT INTO `table_column` (`column_id`, `table_definition_id`, `order`, `title`, `type`, `col_span`, `row2`, `col_span2`, `row3`, `col_span3`, `row4`) VALUES
('340', '51', '1', 'Место прикупљања', 'AGGREGATE', '1', NULL, '0', NULL, '0', NULL),
('341', '51', '2', 'Правац евакуације', 'ENUM', '2', 'Тип', '1', NULL, '0', NULL),
('342', '51', '3', NULL, 'STRING', '0', 'Правац', '1', NULL, '0', NULL),
('343', '51', '4', 'Средства за превоз', 'STRING', '2', 'Власник', '1', NULL, '0', NULL),
('344', '51', '5', NULL, 'STRING', '0', 'Врста/број', '1', NULL, '0', NULL),
('345', '51', '6', 'Место прихвата', 'STRING', '1', NULL, '0', NULL, '0', NULL);
INSERT INTO `table_column_value` (`id`, `column_id`, `value`, `order`) VALUES
('5', '341', 'Главни', '1'),
('6', '341', 'Помоћни', '2');