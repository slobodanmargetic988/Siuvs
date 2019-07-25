INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(143, 78, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају болести животиња');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1071, 143, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1072, 143, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1073, 143, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1074, 143, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1075, 143, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1076, 143, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1077, 143, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('144', '78', '2', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у случају болести животиња');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('1078', '144', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('1079', '144', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('1080', '144', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('1081', '144', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(145, 78, 3, 1, 'Преглед чланова стручно – оперативног тима за');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1082, 145, 1, 'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0),
(1083, 145, 2, 'Дужност утиму','STRING',1,NULL,0),
(1084, 145, 3, 'Име и презиме ','STRING',1,NULL,0),
(1085, 145, 4, 'Контакт телефони','STRING',3,'мобилни',1),
(1086, 145, 5, NULL,'STRING',0,'кућни',1),
(1087, 145, 6, NULL,'STRING',0,'посао',1),
(1088, 145, 7, 'Примедба','STRING',1,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(146, 78, 4, 0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања од болести животиња');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1089, 146, 1, 'Задатак','STRING',1),
(1090, 146, 2, 'Одговорно лице','STRING',1),
(1091, 146, 3, 'Сарађује','STRING',1),
(1092, 146, 4, 'Рок извршења','STRING',1),
(1093, 146, 5, 'Примедба','STRING',1);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(147, 78, 5, 0, 'Преглед обавеза (мера и задатака) учесника у заштити и спасавању у случају болести животиња');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1094, 147, 1, 'Мера / задатак','STRING',1),
(1095, 147, 2, 'Носилац','STRING',1),
(1096, 147, 3, 'Оперативни поступак','STRING',1),
(1097, 147, 4, 'Извршилац оперативног поступка','STRING',1),
(1098, 147, 5, 'Напомена (прилог – план)','STRING',1);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
(148, 78, 6, 0,'Преглед угрожених подручја и објеката у случају болести животиња');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `col_span`, `row2`, `col_span2`) VALUES
(1099, 148, 1,  'Насеље','STRING',1,NULL,0),
(1100, 148, 2,  'Угрожени објекти','NUMBER',2,'Број стамбених објеката',1),
(1101, 148, 3,  NULL,'NUMBER',0,'Број привредних објеката',1),
(1102, 148, 4,  'Угрожено становништво','NUMBER',2,'Број угрожених домаћинстава',1),
(1103, 148, 5,  NULL,'NUMBER',0,'Број становника',1),
(1104, 148, 6,  'Угрожене саобраћајнице','NUMBER',7,'Државни путеви IA реда (км)',1),
(1105, 148, 7,  NULL,'NUMBER',0,'Државни путеви IB реда (км)',1),
(1106, 148, 8,  NULL,'NUMBER',0,'Државни путеви IIA реда (км)',1),
(1107, 148, 9,  NULL,'NUMBER',0,'Државни путеви IIB реда (км)',1),
(1108, 148, 10, NULL,'NUMBER',0,'Oпштински путеви (км)',1),
(1109, 148, 11, NULL,'NUMBER',0,'Железничка пруга (км)',1),
(1110, 148, 12, NULL,'NUMBER',0,'Број мостова',1),
(1111, 148, 13, 'Угрожени остали објекти инфраструктуре','NUMBER',12,'Водоводна (км)',1),
(1112, 148, 14, NULL,'NUMBER',0,'Водоводна (број објеката)',1),
(1113, 148, 15, NULL,'NUMBER',0,'Електро (км)',1),
(1114, 148, 16, NULL,'NUMBER',0,'Електро (број објеката)',1),
(1115, 148, 17, NULL,'NUMBER',0,'ПТТ (км)',1),
(1116, 148, 18, NULL,'NUMBER',0,'ПТТ (број објеката)',1),
(1117, 148, 19, NULL,'NUMBER',0,'Гасовод (км)',1),
(1118, 148, 20, NULL,'NUMBER',0,'Госовод (број објеката)',1),
(1119, 148, 21, NULL,'NUMBER',0,'Топловод (км)',1),
(1120, 148, 22, NULL,'NUMBER',0,'Топловод (број објеката)',1),
(1121, 148, 23, NULL,'NUMBER',0,'Канализација (км)',1),
(1122, 148, 24, NULL,'NUMBER',0,'Канализација (број објеката)',1);

UPDATE `table_definition` SET `page_id` = '78', `order` = '8' WHERE (`table_id` = '16');
