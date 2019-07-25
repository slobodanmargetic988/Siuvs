INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(149, 80, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају болести биљака');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1123, 149, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1124, 149, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1125, 149, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1126, 149, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1127, 149, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1128, 149, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1129, 149, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('150', '80', '2', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у случају болести биљака');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('1130', '150', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('1131', '150', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('1132', '150', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('1133', '150', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(151, 80, 3, 1, 'Преглед чланова стручно – оперативног тима за');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1134, 151, 1, 'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0),
(1135, 151, 2, 'Дужност утиму','STRING',1,NULL,0),
(1136, 151, 3, 'Име и презиме ','STRING',1,NULL,0),
(1137, 151, 4, 'Контакт телефони','STRING',3,'мобилни',1),
(1138, 151, 5, NULL,'STRING',0,'кућни',1),
(1139, 151, 6, NULL,'STRING',0,'посао',1),
(1140, 151, 7, 'Примедба','STRING',1,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(152, 80, 4, 0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања од болести биљака');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1141, 152, 1, 'Задатак','STRING',1),
(1142, 152, 2, 'Одговорно лице','STRING',1),
(1143, 152, 3, 'Сарађује','STRING',1),
(1144, 152, 4, 'Рок извршења','STRING',1),
(1145, 152, 5, 'Примедба','STRING',1);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(153, 80, 5, 0, 'Преглед обавеза (мера и задатака) учесника у заштити и спасавању у случају болести биљака');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1146, 153, 1, 'Мера / задатак','STRING',1),
(1147, 153, 2, 'Носилац','STRING',1),
(1148, 153, 3, 'Оперативни поступак','STRING',1),
(1149, 153, 4, 'Извршилац оперативног поступка','STRING',1),
(1150, 153, 5, 'Напомена (прилог – план)','STRING',1);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
(154, 80, 6, 0,'Преглед угрожених подручја и објеката у случају болести биљака');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `col_span`, `row2`, `col_span2`) VALUES
(1151, 154, 1,  'Насеље','STRING',1,NULL,0),
(1152, 154, 2,  'Угрожени објекти','NUMBER',2,'Број стамбених објеката',1),
(1153, 154, 3,  NULL,'NUMBER',0,'Број привредних објеката',1),
(1154, 154, 4,  'Угрожено становништво','NUMBER',2,'Број угрожених домаћинстава',1),
(1155, 154, 5,  NULL,'NUMBER',0,'Број становника',1),
(1156, 154, 6,  'Угрожене саобраћајнице','NUMBER',7,'Државни путеви IA реда (км)',1),
(1157, 154, 7,  NULL,'NUMBER',0,'Државни путеви IB реда (км)',1),
(1158, 154, 8,  NULL,'NUMBER',0,'Државни путеви IIA реда (км)',1),
(1159, 154, 9,  NULL,'NUMBER',0,'Државни путеви IIB реда (км)',1),
(1160, 154, 10, NULL,'NUMBER',0,'Oпштински путеви (км)',1),
(1161, 154, 11, NULL,'NUMBER',0,'Железничка пруга (км)',1),
(1162, 154, 12, NULL,'NUMBER',0,'Број мостова',1),
(1163, 154, 13, 'Угрожени остали објекти инфраструктуре','NUMBER',12,'Водоводна (км)',1),
(1164, 154, 14, NULL,'NUMBER',0,'Водоводна (број објеката)',1),
(1165, 154, 15, NULL,'NUMBER',0,'Електро (км)',1),
(1166, 154, 16, NULL,'NUMBER',0,'Електро (број објеката)',1),
(1167, 154, 17, NULL,'NUMBER',0,'ПТТ (км)',1),
(1168, 154, 18, NULL,'NUMBER',0,'ПТТ (број објеката)',1),
(1169, 154, 19, NULL,'NUMBER',0,'Гасовод (км)',1),
(1170, 154, 20, NULL,'NUMBER',0,'Госовод (број објеката)',1),
(1171, 154, 21, NULL,'NUMBER',0,'Топловод (км)',1),
(1172, 154, 22, NULL,'NUMBER',0,'Топловод (број објеката)',1),
(1173, 154, 23, NULL,'NUMBER',0,'Канализација (км)',1),
(1174, 154, 24, NULL,'NUMBER',0,'Канализација (број објеката)',1);
