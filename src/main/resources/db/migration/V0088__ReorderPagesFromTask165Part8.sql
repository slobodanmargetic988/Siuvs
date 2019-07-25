INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(129, 77, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају топлог таласа');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(907, 129, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(908, 129, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(909, 129, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(910, 129, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(911, 129, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(912, 129, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(913, 129, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('130', '77', '2', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у случају топлог таласа');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('914', '130', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('915', '130', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('916', '130', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('917', '130', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(131, 77, 3, 1, 'Преглед чланова стручно – оперативног тима за');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(918, 131, 1, 'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0),
(919, 131, 2, 'Дужност утиму','STRING',1,NULL,0),
(920, 131, 3, 'Име и презиме ','STRING',1,NULL,0),
(921, 131, 4, 'Контакт телефони','STRING',3,'мобилни',1),
(922, 131, 5, NULL,'STRING',0,'кућни',1),
(923, 131, 6, NULL,'STRING',0,'посао',1),
(924, 131, 7, 'Примедба','STRING',1,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(132, 77, 4, 0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања у случају топлог таласа');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(925, 132, 1, 'Задатак','STRING',1),
(926, 132, 2, 'Одговорно лице','STRING',1),
(927, 132, 3, 'Сарађује','STRING',1),
(928, 132, 4, 'Рок извршења','STRING',1),
(929, 132, 5, 'Примедба','STRING',1);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(133, 77, 5, 0, 'Преглед обавеза (мера и задатака) учесника у заштити и спасавању од топлог таласа');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(930, 133, 1, 'Мера / задатак','STRING',1),
(931, 133, 2, 'Носилац','STRING',1),
(932, 133, 3, 'Оперативни поступак','STRING',1),
(933, 133, 4, 'Извршилац оперативног поступка','STRING',1),
(934, 133, 5, 'Напомена (прилог – план)','STRING',1);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
(134, 77, 6, 0,'Преглед угрожених подручја и објеката у случају топлог таласа');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `col_span`, `row2`, `col_span2`) VALUES
(935, 134, 1,  'Насеље','STRING',1,NULL,0),
(936, 134, 2,  'Угрожени објекти','NUMBER',2,'Број стамбених објеката',1),
(937, 134, 3,  NULL,'NUMBER',0,'Број привредних објеката',1),
(938, 134, 4,  'Угрожено становништво','NUMBER',2,'Број угрожених домаћинстава',1),
(939, 134, 5,  NULL,'NUMBER',0,'Број становника',1),
(940, 134, 6,  'Угрожене саобраћајнице','NUMBER',7,'Државни путеви IA реда (км)',1),
(941, 134, 7,  NULL,'NUMBER',0,'Државни путеви IB реда (км)',1),
(942, 134, 8,  NULL,'NUMBER',0,'Државни путеви IIA реда (км)',1),
(943, 134, 9,  NULL,'NUMBER',0,'Државни путеви IIB реда (км)',1),
(944, 134, 10, NULL,'NUMBER',0,'Oпштински путеви (км)',1),
(945, 134, 11, NULL,'NUMBER',0,'Железничка пруга (км)',1),
(946, 134, 12, NULL,'NUMBER',0,'Број мостова',1),
(947, 134, 13, 'Угрожени остали објекти инфраструктуре','NUMBER',12,'Водоводна (км)',1),
(948, 134, 14, NULL,'NUMBER',0,'Водоводна (број објеката)',1),
(949, 134, 15, NULL,'NUMBER',0,'Електро (км)',1),
(950, 134, 16, NULL,'NUMBER',0,'Електро (број објеката)',1),
(951, 134, 17, NULL,'NUMBER',0,'ПТТ (км)',1),
(952, 134, 18, NULL,'NUMBER',0,'ПТТ (број објеката)',1),
(953, 134, 19, NULL,'NUMBER',0,'Гасовод (км)',1),
(954, 134, 20, NULL,'NUMBER',0,'Госовод (број објеката)',1),
(955, 134, 21, NULL,'NUMBER',0,'Топловод (км)',1),
(956, 134, 22, NULL,'NUMBER',0,'Топловод (број објеката)',1),
(957, 134, 23, NULL,'NUMBER',0,'Канализација (км)',1),
(958, 134, 24, NULL,'NUMBER',0,'Канализација (број објеката)',1);
