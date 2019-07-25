UPDATE `table_definition` SET `order` = '5' WHERE (`table_id` = '65');
UPDATE `table_definition` SET `order` = '3' WHERE (`table_id` = '66');
UPDATE `table_definition` SET `order` = '4' WHERE (`table_id` = '67');
UPDATE `table_definition` SET `order` = '7' WHERE (`table_id` = '68');

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('125', '40', '2', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у случају града');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('851', '125', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('852', '125', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('853', '125', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('854', '125', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
(126, 40, 6, 0,'Преглед угрожених подручја и објеката у случају града');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `col_span`, `row2`, `col_span2`) VALUES
(855, 126, 1,  'Насеље','STRING',1,NULL,0),
(856, 126, 2,  'Угрожени објекти','NUMBER',2,'Број стамбених објеката',1),
(857, 126, 3,  NULL,'NUMBER',0,'Број привредних објеката',1),
(858, 126, 4,  'Угрожено становништво','NUMBER',2,'Број угрожених домаћинстава',1),
(859, 126, 5,  NULL,'NUMBER',0,'Број становника',1),
(860, 126, 6,  'Угрожене саобраћајнице','NUMBER',7,'Државни путеви IA реда (км)',1),
(861, 126, 7,  NULL,'NUMBER',0,'Државни путеви IB реда (км)',1),
(862, 126, 8,  NULL,'NUMBER',0,'Државни путеви IIA реда (км)',1),
(863, 126, 9,  NULL,'NUMBER',0,'Државни путеви IIB реда (км)',1),
(864, 126, 10, NULL,'NUMBER',0,'Oпштински путеви (км)',1),
(865, 126, 11, NULL,'NUMBER',0,'Железничка пруга (км)',1),
(866, 126, 12, NULL,'NUMBER',0,'Број мостова',1),
(867, 126, 13, 'Угрожени остали објекти инфраструктуре','NUMBER',12,'Водоводна (км)',1),
(868, 126, 14, NULL,'NUMBER',0,'Водоводна (број објеката)',1),
(869, 126, 15, NULL,'NUMBER',0,'Електро (км)',1),
(870, 126, 16, NULL,'NUMBER',0,'Електро (број објеката)',1),
(871, 126, 17, NULL,'NUMBER',0,'ПТТ (км)',1),
(872, 126, 18, NULL,'NUMBER',0,'ПТТ (број објеката)',1),
(873, 126, 19, NULL,'NUMBER',0,'Гасовод (км)',1),
(874, 126, 20, NULL,'NUMBER',0,'Госовод (број објеката)',1),
(875, 126, 21, NULL,'NUMBER',0,'Топловод (км)',1),
(876, 126, 22, NULL,'NUMBER',0,'Топловод (број објеката)',1),
(877, 126, 23, NULL,'NUMBER',0,'Канализација (км)',1),
(878, 126, 24, NULL,'NUMBER',0,'Канализација (број објеката)',1);
