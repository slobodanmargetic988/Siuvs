INSERT INTO `page` (`page_id`,`title`,`type`,`parent_id`,`order`) VALUES
(39,'Процена ризика','MENU',4,1),
(40,'План заштите и спасавања','TABLE',4,2),
(41,'Мере и задаци цивилне заштите','MENU',4,3),
(42,'Највероватнији нежељени догађај','ASSESSMENT',39,1),
(43,'Нежељени догађај са најтежим могућим последицама','ASSESSMENT',39,2);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(64, 40, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају града');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(432,64,1,'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0,NULL),
(433,64,2,'Делатност','STRING',0,NULL,0,NULL,0,NULL),
(434,64,3,'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0,NULL),
(435,64,4,'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0,NULL),
(436,64,5,NULL,'STRING',0,'телефони',2,'службени/факс',1,NULL),
(437,64,6,NULL,'STRING',0,NULL,0,'мобилни',1,NULL),
(438,64,7,'Имејл','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(65,40,2,0,'Преглед обавеза (мера и задатака) учесника у заштити и спасавању од града');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(439,65,1,'Мера / задатак','STRING',1,NULL,0,NULL,0,NULL),
(440,65,2,'Носилац','STRING',1,NULL,0,NULL,0,NULL),
(441,65,3,'Оперативни поступак','STRING',1,NULL,0,NULL,0,NULL),
(442,65,4,'Извршилац оперативног поступка','STRING',1,NULL,0,NULL,0,NULL),
(443,65,5,'Напомена (прилог – план)','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(66,40,4,1,'Преглед чланова стручно – оперативног тима за');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(444,66,1,'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0,NULL,0,NULL),
(445,66,2,'Дужност утиму','STRING',1,NULL,0,NULL,0,NULL),
(446,66,3,'Име и презиме ','STRING',1,NULL,0,NULL,0,NULL),
(447,66,4,'Контакт телефони','STRING',3,'мобилни',1,NULL,0,NULL),
(448,66,5,NULL,'STRING',0,'кућни',1,NULL,0,NULL),
(449,66,6,NULL,'STRING',0,'посао',1,NULL,0,NULL),
(450,66,7,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(67,40,5,0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања у случају града');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(451,67,1,'Задатак','STRING',1,NULL,0,NULL,0,NULL),
(452,67,2,'Одговорно лице','STRING',1,NULL,0,NULL,0,NULL),
(453,67,3,'Сарађује','STRING',1,NULL,0,NULL,0,NULL),
(454,67,4,'Рок извршења','STRING',1,NULL,0,NULL,0,NULL),
(455,67,5,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(68,40,6,0,'Преглед противградних станица');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(456,68,1,'Насеље/месна заједница','STRING',1,NULL,0,NULL,0,NULL),
(457,68,2,'Назив/ознака противградне станице','STRING',1,NULL,0,NULL,0,NULL),
(458,68,3,'Локација','STRING',1,NULL,0,NULL,0,NULL),
(459,68,4,'Стрелац','STRING',1,NULL,0,NULL,0,NULL),
(460,68,5,'Напомена / број противградних ракета','STRING',1,NULL,0,NULL,0,NULL);