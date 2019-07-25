UPDATE `table_definition` SET `order` = '5' WHERE (`table_id` = '90');
UPDATE `table_definition` SET `order` = '3' WHERE (`table_id` = '91');
UPDATE `table_definition` SET `order` = '4' WHERE (`table_id` = '92');
UPDATE `table_definition` SET `order` = '7' WHERE (`table_id` = '93');
UPDATE `table_definition` SET `order` = '8' WHERE (`table_id` = '94');
UPDATE `table_definition` SET `order` = '9' WHERE (`table_id` = '95');

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('141', '65', '2', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у случају епидемија и пандемија');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('1043', '141', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('1044', '141', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('1045', '141', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('1046', '141', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
(142, 65, 6, 0,'Преглед угрожених подручја и објеката у случају епидемија и пандемија');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `col_span`, `row2`, `col_span2`) VALUES
(1047, 142, 1,  'Насеље','STRING',1,NULL,0),
(1048, 142, 2,  'Угрожени објекти','NUMBER',2,'Број стамбених објеката',1),
(1049, 142, 3,  NULL,'NUMBER',0,'Број привредних објеката',1),
(1050, 142, 4,  'Угрожено становништво','NUMBER',2,'Број угрожених домаћинстава',1),
(1051, 142, 5,  NULL,'NUMBER',0,'Број становника',1),
(1052, 142, 6,  'Угрожене саобраћајнице','NUMBER',7,'Државни путеви IA реда (км)',1),
(1053, 142, 7,  NULL,'NUMBER',0,'Државни путеви IB реда (км)',1),
(1054, 142, 8,  NULL,'NUMBER',0,'Државни путеви IIA реда (км)',1),
(1055, 142, 9,  NULL,'NUMBER',0,'Државни путеви IIB реда (км)',1),
(1056, 142, 10, NULL,'NUMBER',0,'Oпштински путеви (км)',1),
(1057, 142, 11, NULL,'NUMBER',0,'Железничка пруга (км)',1),
(1058, 142, 12, NULL,'NUMBER',0,'Број мостова',1),
(1059, 142, 13, 'Угрожени остали објекти инфраструктуре','NUMBER',12,'Водоводна (км)',1),
(1060, 142, 14, NULL,'NUMBER',0,'Водоводна (број објеката)',1),
(1061, 142, 15, NULL,'NUMBER',0,'Електро (км)',1),
(1062, 142, 16, NULL,'NUMBER',0,'Електро (број објеката)',1),
(1063, 142, 17, NULL,'NUMBER',0,'ПТТ (км)',1),
(1064, 142, 18, NULL,'NUMBER',0,'ПТТ (број објеката)',1),
(1065, 142, 19, NULL,'NUMBER',0,'Гасовод (км)',1),
(1066, 142, 20, NULL,'NUMBER',0,'Госовод (број објеката)',1),
(1067, 142, 21, NULL,'NUMBER',0,'Топловод (км)',1),
(1068, 142, 22, NULL,'NUMBER',0,'Топловод (број објеката)',1),
(1069, 142, 23, NULL,'NUMBER',0,'Канализација (км)',1),
(1070, 142, 24, NULL,'NUMBER',0,'Канализација (број објеката)',1);
