INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('50', '21', '5', '1', 'Преглед чланова стручно - оперативног тима за');
INSERT INTO `table_column` (`column_id`, `table_definition_id`, `order`, `title`, `type`, `col_span`, `row2`, `col_span2`, `row3`, `col_span3`, `row4`) VALUES
('333', '50', '1', 'Назив стручно - оперативног тима', 'AGGREGATE', '1', NULL, '0', NULL, '0', NULL),
('334', '50', '2', 'Дужност у тиму', 'STRING', '1', NULL, '0', NULL, '0', NULL),
('335', '50', '3', 'Име и презиме', 'STRING', '1', NULL, '0', NULL, '0', NULL),
('336', '50', '4', 'Контакт телефони', 'STRING', '3', 'мобилни', '1', NULL, '0', NULL),
('337', '50', '5', NULL, 'STRING', '0', 'кућни', '1', NULL, '0', NULL),
('338', '50', '6', NULL, 'STRING', '0', 'посао', '0', NULL, '0', NULL),
('339', '50', '7', 'Примедба', 'STRING', '1', NULL, '0', NULL, '0', NULL);