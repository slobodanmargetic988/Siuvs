INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(160, 82, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају техничко-технолошких несрећа');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1203, 160, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1204, 160, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1205, 160, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1206, 160, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1207, 160, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1208, 160, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1209, 160, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('161', '82', '2', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у случају техничко-технолошких несрећа');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('1210', '161', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('1211', '161', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('1212', '161', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('1213', '161', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(162, 82, 3, 1, 'Преглед чланова стручно – оперативног тима за');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1214, 162, 1, 'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0),
(1215, 162, 2, 'Дужност утиму','STRING',1,NULL,0),
(1216, 162, 3, 'Име и презиме ','STRING',1,NULL,0),
(1217, 162, 4, 'Контакт телефони','STRING',3,'мобилни',1),
(1218, 162, 5, NULL,'STRING',0,'кућни',1),
(1219, 162, 6, NULL,'STRING',0,'посао',1),
(1220, 162, 7, 'Примедба','STRING',1,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(163, 82, 4, 0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања у случају техничко-технолошких несрећа');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1221, 163, 1, 'Задатак','STRING',1),
(1222, 163, 2, 'Одговорно лице','STRING',1),
(1223, 163, 3, 'Сарађује','STRING',1),
(1224, 163, 4, 'Рок извршења','STRING',1),
(1225, 163, 5, 'Примедба','STRING',1);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(164, 82, 5, 0, 'Преглед обавеза (мера и задатака) учесника у заштити и спасавању од техничко-технолошких несрећа');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1226, 164, 1, 'Мера / задатак','STRING',1),
(1227, 164, 2, 'Носилац','STRING',1),
(1228, 164, 3, 'Оперативни поступак','STRING',1),
(1229, 164, 4, 'Извршилац оперативног поступка','STRING',1),
(1230, 164, 5, 'Напомена (прилог – план)','STRING',1);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
(165, 82, 6, 0,'Преглед угрожених подручја и објеката у случају техничко-технолошких несрећа');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `col_span`, `row2`, `col_span2`) VALUES
(1231, 165, 1,  'Насеље','STRING',1,NULL,0),
(1232, 165, 2,  'Угрожени објекти','NUMBER',2,'Број стамбених објеката',1),
(1233, 165, 3,  NULL,'NUMBER',0,'Број привредних објеката',1),
(1234, 165, 4,  'Угрожено становништво','NUMBER',2,'Број угрожених домаћинстава',1),
(1235, 165, 5,  NULL,'NUMBER',0,'Број становника',1),
(1236, 165, 6,  'Угрожене саобраћајнице','NUMBER',7,'Државни путеви IA реда (км)',1),
(1237, 165, 7,  NULL,'NUMBER',0,'Државни путеви IB реда (км)',1),
(1238, 165, 8,  NULL,'NUMBER',0,'Државни путеви IIA реда (км)',1),
(1239, 165, 9,  NULL,'NUMBER',0,'Државни путеви IIB реда (км)',1),
(1240, 165, 10, NULL,'NUMBER',0,'Oпштински путеви (км)',1),
(1241, 165, 11, NULL,'NUMBER',0,'Железничка пруга (км)',1),
(1242, 165, 12, NULL,'NUMBER',0,'Број мостова',1),
(1243, 165, 13, 'Угрожени остали објекти инфраструктуре','NUMBER',12,'Водоводна (км)',1),
(1244, 165, 14, NULL,'NUMBER',0,'Водоводна (број објеката)',1),
(1245, 165, 15, NULL,'NUMBER',0,'Електро (км)',1),
(1246, 165, 16, NULL,'NUMBER',0,'Електро (број објеката)',1),
(1247, 165, 17, NULL,'NUMBER',0,'ПТТ (км)',1),
(1248, 165, 18, NULL,'NUMBER',0,'ПТТ (број објеката)',1),
(1249, 165, 19, NULL,'NUMBER',0,'Гасовод (км)',1),
(1250, 165, 20, NULL,'NUMBER',0,'Госовод (број објеката)',1),
(1251, 165, 21, NULL,'NUMBER',0,'Топловод (км)',1),
(1252, 165, 22, NULL,'NUMBER',0,'Топловод (број објеката)',1),
(1253, 165, 23, NULL,'NUMBER',0,'Канализација (км)',1),
(1254, 165, 24, NULL,'NUMBER',0,'Канализација (број објеката)',1);
