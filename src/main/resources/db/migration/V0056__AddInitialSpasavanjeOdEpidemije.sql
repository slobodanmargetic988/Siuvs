INSERT INTO `page` (`page_id`,`title`,`type`,`parent_id`,`order`) VALUES
(64,'Процена ризика','MENU',10,1),
(65,'План заштите и спасавања','TABLE',10,2),
(66,'Мере и задаци цивилне заштите','MENU',10,3),
(67,'Највероватнији нежељени догађај','ASSESSMENT',64,1),
(68,'Нежељени догађај са најтежим могућим последицама','ASSESSMENT',64,2);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(89, 65, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају епидемија и пандемија');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(593,89,1,'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0,NULL),
(594,89,2,'Делатност','STRING',0,NULL,0,NULL,0,NULL),
(595,89,3,'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0,NULL),
(596,89,4,'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0,NULL),
(597,89,5,NULL,'STRING',0,'телефони',2,'службени/факс',1,NULL),
(598,89,6,NULL,'STRING',0,NULL,0,'мобилни',1,NULL),
(599,89,7,'Имејл','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(90,65,2,0,'Преглед обавеза (мера и задатака) учесника у заштити и спасавању од епидемија и пандемија');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(600,90,1,'Мера / задатак','STRING',1,NULL,0,NULL,0,NULL),
(601,90,2,'Носилац','STRING',1,NULL,0,NULL,0,NULL),
(602,90,3,'Оперативни поступак','STRING',1,NULL,0,NULL,0,NULL),
(603,90,4,'Извршилац оперативног поступка','STRING',1,NULL,0,NULL,0,NULL),
(604,90,5,'Напомена (прилог – план)','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(91,65,4,1,'Преглед чланова стручно – оперативног тима за');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(605,91,1,'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0,NULL,0,NULL),
(606,91,2,'Дужност утиму','STRING',1,NULL,0,NULL,0,NULL),
(607,91,3,'Име и презиме ','STRING',1,NULL,0,NULL,0,NULL),
(608,91,4,'Контакт телефони','STRING',3,'мобилни',1,NULL,0,NULL),
(609,91,5,NULL,'STRING',0,'кућни',1,NULL,0,NULL),
(610,91,6,NULL,'STRING',0,'посао',1,NULL,0,NULL),
(611,91,7,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(92,65,5,0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања у случају епидемија и пандемија');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(612,92,1,'Задатак','STRING',1,NULL,0,NULL,0,NULL),
(613,92,2,'Одговорно лице','STRING',1,NULL,0,NULL,0,NULL),
(614,92,3,'Сарађује','STRING',1,NULL,0,NULL,0,NULL),
(615,92,4,'Рок извршења','STRING',1,NULL,0,NULL,0,NULL),
(616,92,5,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(93,65,6,0,'Преглед здравствених установа');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(617,93,1,'Здравствена установа, адреса, телефон/факс','STRING',1,NULL,0,NULL,0,NULL),
(618,93,2,'Одговорно лице, телефон','STRING',1,NULL,0,NULL,0,NULL),
(619,93,3,'Облик својине','STRING',1,NULL,0,NULL,0,NULL),
(620,93,4,'Број здравствених радника','NUMBER',1,NULL,0,NULL,0,NULL),
(621,93,5,'Број болничких кревета','NUMBER',1,NULL,0,NULL,0,NULL),
(622,93,6,'Екипе опште медицинске помоћи','NUMBER',1,NULL,0,NULL,0,NULL),
(623,93,7,'Врсте специјал. служби','NUMBER',1,NULL,0,NULL,0,NULL),
(624,93,8,'Број санитетских возила','NUMBER',1,NULL,0,NULL,0,NULL),
(625,93,9,'Лабораторија','NUMBER',1,NULL,0,NULL,0,NULL),
(626,93,10,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(94,65,7,0,'Преглед екипа црвеног крста');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(627,94,1,'Назив организације Црвеног Крста','STRING',1,NULL,0,NULL,0,NULL),
(628,94,2,'Адреса, телефон/факс','STRING',1,NULL,0,NULL,0,NULL),
(629,94,3,'Број екипа ПП','NUMBER',1,NULL,0,NULL,0,NULL),
(630,94,4,'Број екипа за кућну негу','NUMBER',1,NULL,0,NULL,0,NULL),
(631,94,5,'Број екипа за социјални рад','NUMBER',1,NULL,0,NULL,0,NULL),
(632,94,6,'Број припадника у екипама','NUMBER',1,NULL,0,NULL,0,NULL),
(633,94,7,'Вођа Екипе ПП','STRING',2,'Адреса',1,NULL,0,NULL),
(634,94,8,NULL,'STRING',0,'Телефон',1,NULL,0,NULL),
(635,94,9,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(95,65,8,0,'Преглед стручних лица служби хитне медицинске помоћи');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(636,95,1,'Назив службе хитне медицинске помоћи','STRING',1,NULL,0,NULL,0,NULL),
(637,95,2,'Број здрав.рад.','NUMBER',1,NULL,0,NULL,0,NULL),
(638,95,3,'Лекара','NUMBER',1,NULL,0,NULL,0,NULL),
(639,95,4,'Медицинских техничара','NUMBER',1,NULL,0,NULL,0,NULL),
(640,95,5,'Број возила','NUMBER',1,NULL,0,NULL,0,NULL);