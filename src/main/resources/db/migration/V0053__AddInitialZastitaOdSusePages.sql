INSERT INTO `page` (`page_id`,`title`,`type`,`parent_id`,`order`) VALUES
(49,'Процена ризика','MENU',7,1),
(50,'План заштите и спасавања','TABLE',7,2),
(51,'Мере и задаци цивилне заштите','MENU',7,3),
(52,'Највероватнији нежељени догађај','ASSESSMENT',49,1),
(53,'Нежељени догађај са најтежим могућим последицама','ASSESSMENT',49,2);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(73, 50, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају суше');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(485,73,1,'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0,NULL),
(486,73,2,'Делатност','STRING',0,NULL,0,NULL,0,NULL),
(487,73,3,'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0,NULL),
(488,73,4,'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0,NULL),
(489,73,5,NULL,'STRING',0,'телефони',2,'службени/факс',1,NULL),
(490,73,6,NULL,'STRING',0,NULL,0,'мобилни',1,NULL),
(491,73,7,'Имејл','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(74,50,2,0,'Преглед обавеза (мера и задатака) учесника у заштити и спасавању од суше');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(492,74,1,'Мера / задатак','STRING',1,NULL,0,NULL,0,NULL),
(493,74,2,'Носилац','STRING',1,NULL,0,NULL,0,NULL),
(494,74,3,'Оперативни поступак','STRING',1,NULL,0,NULL,0,NULL),
(495,74,4,'Извршилац оперативног поступка','STRING',1,NULL,0,NULL,0,NULL),
(496,74,5,'Напомена (прилог – план)','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(75,50,4,1,'Преглед чланова стручно – оперативног тима за');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(497,75,1,'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0,NULL,0,NULL),
(498,75,2,'Дужност утиму','STRING',1,NULL,0,NULL,0,NULL),
(499,75,3,'Име и презиме ','STRING',1,NULL,0,NULL,0,NULL),
(500,75,4,'Контакт телефони','STRING',3,'мобилни',1,NULL,0,NULL),
(501,75,5,NULL,'STRING',0,'кућни',1,NULL,0,NULL),
(502,75,6,NULL,'STRING',0,'посао',1,NULL,0,NULL),
(503,75,7,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(76,50,5,0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања у случају суше');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(504,76,1,'Задатак','STRING',1,NULL,0,NULL,0,NULL),
(505,76,2,'Одговорно лице','STRING',1,NULL,0,NULL,0,NULL),
(506,76,3,'Сарађује','STRING',1,NULL,0,NULL,0,NULL),
(507,76,4,'Рок извршења','STRING',1,NULL,0,NULL,0,NULL),
(508,76,5,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(77,50,6,0,'Преглед мелиорационих кнала, извора и бунара');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(509,77,1,'Мелиорациони канал','STRING',3,'Локација',1,NULL,0,NULL),
(510,77,2,NULL,'STRING',0,'Ознака/назив',1,NULL,0,NULL),
(511,77,3,NULL,'STRING',0,'Дужина',1,NULL,0,NULL),
(512,77,4,'Извор/бунар','STRING',3,'Дужина',1,NULL,0,NULL),
(513,77,5,NULL,'STRING',0,'Ознака/назив',1,NULL,0,NULL),
(514,77,6,NULL,'STRING',0,'Капацитет',1,NULL,0,NULL);