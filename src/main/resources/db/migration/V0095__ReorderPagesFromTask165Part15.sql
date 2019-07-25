INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(155, 81, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају пожара, експлозија и пожара на отвореном');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1175, 155, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1176, 155, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1177, 155, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1178, 155, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1179, 155, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1180, 155, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1181, 155, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('156', '81', '2', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у случају пожара, експлозија и пожара на отвореном');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('1182', '156', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('1183', '156', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('1184', '156', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('1185', '156', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(157, 81, 3, 1, 'Преглед чланова стручно – оперативног тима за');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1186, 157, 1, 'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0),
(1187, 157, 2, 'Дужност утиму','STRING',1,NULL,0),
(1188, 157, 3, 'Име и презиме ','STRING',1,NULL,0),
(1189, 157, 4, 'Контакт телефони','STRING',3,'мобилни',1),
(1190, 157, 5, NULL,'STRING',0,'кућни',1),
(1191, 157, 6, NULL,'STRING',0,'посао',1),
(1192, 157, 7, 'Примедба','STRING',1,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(158, 81, 4, 0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања од пожара, експлозија и пожара на отвореном');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1193, 158, 1, 'Задатак','STRING',1),
(1194, 158, 2, 'Одговорно лице','STRING',1),
(1195, 158, 3, 'Сарађује','STRING',1),
(1196, 158, 4, 'Рок извршења','STRING',1),
(1197, 158, 5, 'Примедба','STRING',1);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(159, 81, 5, 0, 'Преглед обавеза (мера и задатака) учесника у заштити и спасавању у случају пожара, експлозија и пожара на отвореном');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1198, 159, 1, 'Мера / задатак','STRING',1),
(1199, 159, 2, 'Носилац','STRING',1),
(1200, 159, 3, 'Оперативни поступак','STRING',1),
(1201, 159, 4, 'Извршилац оперативног поступка','STRING',1),
(1202, 159, 5, 'Напомена (прилог – план)','STRING',1);
