INSERT INTO `page` (`page_id`,`title`,`type`,`parent_id`,`order`) VALUES
(59,'Процена ризика','MENU',9,1),
(60,'План заштите и спасавања','TABLE',9,2),
(61,'Мере и задаци цивилне заштите','MENU',9,3),
(62,'Највероватнији нежељени догађај','ASSESSMENT',59,1),
(63,'Нежељени догађај са најтежим могућим последицама','ASSESSMENT',59,2);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(84, 60, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају недостатка воде за пиће');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(565,84,1,'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0,NULL),
(566,84,2,'Делатност','STRING',0,NULL,0,NULL,0,NULL),
(567,84,3,'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0,NULL),
(568,84,4,'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0,NULL),
(569,84,5,NULL,'STRING',0,'телефони',2,'службени/факс',1,NULL),
(570,84,6,NULL,'STRING',0,NULL,0,'мобилни',1,NULL),
(571,84,7,'Имејл','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(85,60,2,0,'Преглед обавеза (мера и задатака) учесника у заштити и спасавању од недостатка воде за пиће');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(572,85,1,'Мера / задатак','STRING',1,NULL,0,NULL,0,NULL),
(573,85,2,'Носилац','STRING',1,NULL,0,NULL,0,NULL),
(574,85,3,'Оперативни поступак','STRING',1,NULL,0,NULL,0,NULL),
(575,85,4,'Извршилац оперативног поступка','STRING',1,NULL,0,NULL,0,NULL),
(576,85,5,'Напомена (прилог – план)','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(86,60,4,1,'Преглед чланова стручно – оперативног тима за');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(577,86,1,'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0,NULL,0,NULL),
(578,86,2,'Дужност утиму','STRING',1,NULL,0,NULL,0,NULL),
(579,86,3,'Име и презиме ','STRING',1,NULL,0,NULL,0,NULL),
(580,86,4,'Контакт телефони','STRING',3,'мобилни',1,NULL,0,NULL),
(581,86,5,NULL,'STRING',0,'кућни',1,NULL,0,NULL),
(582,86,6,NULL,'STRING',0,'посао',1,NULL,0,NULL),
(583,86,7,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(87,60,5,0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања у случају недостатка воде за пиће');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(584,87,1,'Задатак','STRING',1,NULL,0,NULL,0,NULL),
(585,87,2,'Одговорно лице','STRING',1,NULL,0,NULL,0,NULL),
(586,87,3,'Сарађује','STRING',1,NULL,0,NULL,0,NULL),
(587,87,4,'Рок извршења','STRING',1,NULL,0,NULL,0,NULL),
(588,87,5,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(88,60,6,0,'Преглед извора и бунара');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(589,88,1,'Назив насеља','STRING',1,NULL,0,NULL,0,NULL),
(590,88,2,'Извор/бунар','STRING',3,'Локација',1,NULL,0,NULL),
(591,88,3,NULL,'STRING',0,'Ознака/назив',1,NULL,0,NULL),
(592,88,4,NULL,'STRING',0,'Капацитет',1,NULL,0,NULL);