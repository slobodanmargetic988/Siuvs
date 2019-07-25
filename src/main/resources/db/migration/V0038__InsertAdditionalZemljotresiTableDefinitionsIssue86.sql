INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES ('48', '28', '5', '0', 'Преглед склањања становника по склонишним објектима');
INSERT INTO `table_column` (`column_id`, `table_definition_id`, `title`, `type`, `col_span`, `row2`, `col_span2`, `row3`, `col_span3`, `row4`) VALUES
('318', '48', 'Месна заједница, назив склонишног објекта и адреса', 'STRING', '1', NULL, '0', NULL, '0', NULL),
('319', '48', 'Подаци о руководиоцу склоништа', 'STRING', '3', 'Име и презиме', '1', NULL, '0', NULL),
('320', '48', NULL, 'STRING', '0', 'Адреса становања', '1', NULL, '0', NULL),
('321', '48', NULL, 'STRING', '0', 'Телефони за контакт (посао, кућни, мобилни)', '1', NULL, '0', NULL),
('322', '48', 'Подаци о становништву које се склања', 'STRING', '3', 'Адреса становања', '1', NULL, '0', NULL),
('323', '48', NULL, 'STRING', '0', 'Кућни број', '1', NULL, '0', NULL),
('324', '48', NULL, 'NUMBER', '0', 'Број станара', '1', NULL, '0', NULL),
('325', '48', 'Примедба', 'STRING', '1', NULL, '0', NULL, '0', NULL);
INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES ('49', '28', '6', '0', 'Преглед чланова стручно - оперативног тима за склањање');
INSERT INTO `table_column` (`column_id`, `table_definition_id`, `title`, `type`, `col_span`, `row2`, `col_span2`, `row3`, `col_span3`, `row4`) VALUES
('326', '49', 'Назив стручно - оперативног тима', 'AGGREGATE', '1', NULL, '0', NULL, '0', NULL),
('327', '49', 'Дужност у тиму', 'STRING', '1', NULL, '0', NULL, '0', NULL),
('328', '49', 'Име и презиме', 'STRING', '1', NULL, '0', NULL, '0', NULL),
('329', '49', 'Контакт телефони', 'STRING', '3', 'мобилни', '1', NULL, '0', NULL),
('330', '49', NULL, 'STRING', '0', 'кућни', '1', NULL, '0', NULL),
('331', '49', NULL, 'STRING', '0', 'посао', '0', NULL, '0', NULL),
('332', '49', 'Примедба', 'STRING', '1', NULL, '0', NULL, '0', NULL);