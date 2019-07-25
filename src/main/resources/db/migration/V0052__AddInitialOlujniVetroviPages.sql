INSERT INTO `page` (`page_id`,`title`,`type`,`parent_id`,`order`) VALUES
(44,'Процена ризика','MENU',5,1),
(45,'План заштите и спасавања','TABLE',5,2),
(46,'Мере и задаци цивилне заштите','MENU',5,3),
(47,'Највероватнији нежељени догађај','ASSESSMENT',44,1),
(48,'Нежељени догађај са најтежим могућим последицама','ASSESSMENT',44,2);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(69, 45, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају олујних ветрова');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(461,69,1,'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0,NULL),
(462,69,2,'Делатност','STRING',0,NULL,0,NULL,0,NULL),
(463,69,3,'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0,NULL),
(464,69,4,'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0,NULL),
(465,69,5,NULL,'STRING',0,'телефони',2,'службени/факс',1,NULL),
(466,69,6,NULL,'STRING',0,NULL,0,'мобилни',1,NULL),
(467,69,7,'Имејл','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(70,45,2,0,'Преглед обавеза (мера и задатака) учесника у заштити и спасавању од олујних ветрова');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(468,70,1,'Мера / задатак','STRING',1,NULL,0,NULL,0,NULL),
(469,70,2,'Носилац','STRING',1,NULL,0,NULL,0,NULL),
(470,70,3,'Оперативни поступак','STRING',1,NULL,0,NULL,0,NULL),
(471,70,4,'Извршилац оперативног поступка','STRING',1,NULL,0,NULL,0,NULL),
(472,70,5,'Напомена (прилог – план)','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(71,45,4,1,'Преглед чланова стручно – оперативног тима за');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(473,71,1,'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0,NULL,0,NULL),
(474,71,2,'Дужност утиму','STRING',1,NULL,0,NULL,0,NULL),
(475,71,3,'Име и презиме ','STRING',1,NULL,0,NULL,0,NULL),
(476,71,4,'Контакт телефони','STRING',3,'мобилни',1,NULL,0,NULL),
(477,71,5,NULL,'STRING',0,'кућни',1,NULL,0,NULL),
(478,71,6,NULL,'STRING',0,'посао',1,NULL,0,NULL),
(479,71,7,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(72,45,5,0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања у случају олујних ветрова');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(480,72,1,'Задатак','STRING',1,NULL,0,NULL,0,NULL),
(481,72,2,'Одговорно лице','STRING',1,NULL,0,NULL,0,NULL),
(482,72,3,'Сарађује','STRING',1,NULL,0,NULL,0,NULL),
(483,72,4,'Рок извршења','STRING',1,NULL,0,NULL,0,NULL),
(484,72,5,'Примедба','STRING',1,NULL,0,NULL,0,NULL);