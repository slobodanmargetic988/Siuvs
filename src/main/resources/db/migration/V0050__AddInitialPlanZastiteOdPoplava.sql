INSERT INTO `page` (`page_id`,`title`,`type`,`parent_id`,`order`) VALUES
(34,'Процена ризика','MENU',3,1),
(35,'План заштите и спасавања','TABLE',3,2),
(36,'Мере и задаци цивилне заштите','MENU',3,3),
(37,'Највероватнији нежељени догађај','ASSESSMENT',34,1),
(38,'Нежељени догађај са најтежим могућим последицама','ASSESSMENT',34,2);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(59, 35, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају поплава');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(402,59,1,'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0,NULL),
(403,59,2,'Делатност','STRING',0,NULL,0,NULL,0,NULL),
(404,59,3,'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0,NULL),
(405,59,4,'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0,NULL),
(406,59,5,NULL,'STRING',0,'телефони',2,'службени/факс',1,NULL),
(407,59,6,NULL,'STRING',0,NULL,0,'мобилни',1,NULL),
(408,59,7,'Имејл','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(60,35,2,0,'Преглед обавеза (мера и задатака) учесника у заштити и спасавању од поплава');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(409,60,1,'Мера / задатак','STRING',1,NULL,0,NULL,0,NULL),
(410,60,2,'Носилац','STRING',1,NULL,0,NULL,0,NULL),
(411,60,3,'Оперативни поступак','STRING',1,NULL,0,NULL,0,NULL),
(412,60,4,'Извршилац оперативног поступка','STRING',1,NULL,0,NULL,0,NULL),
(413,60,5,'Напомена (прилог – план)','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(61,35,4,1,'Преглед чланова стручно – оперативног тима за');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(414,61,1,'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0,NULL,0,NULL),
(415,61,2,'Дужност утиму','STRING',1,NULL,0,NULL,0,NULL),
(416,61,3,'Име и презиме ','STRING',1,NULL,0,NULL,0,NULL),
(417,61,4,'Контакт телефони','STRING',3,'мобилни',1,NULL,0,NULL),
(418,61,5,NULL,'STRING',0,'кућни',1,NULL,0,NULL),
(419,61,6,NULL,'STRING',0,'посао',1,NULL,0,NULL),
(420,61,7,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(62,35,5,0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања у случају поплава');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(421,62,1,'Задатак','STRING',1,NULL,0,NULL,0,NULL),
(422,62,2,'Одговорно лице','STRING',1,NULL,0,NULL,0,NULL),
(423,62,3,'Сарађује','STRING',1,NULL,0,NULL,0,NULL),
(424,62,4,'Рок извршења','STRING',1,NULL,0,NULL,0,NULL),
(425,62,5,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(63,35,8,0,'Преглед водотокова и хидроакумулација са потенцијално угроженим рејонима');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(426,63,1,'Назив водотока','STRING',1,NULL,0,NULL,0,NULL),
(427,63,2,'Назив бране - акумулације','STRING',1,NULL,0,NULL,0,NULL),
(428,63,3,'Угрожена насеља','STRING',1,NULL,0,NULL,0,NULL),
(429,63,4,'Број угроженог становништва','STRING',1,NULL,0,NULL,0,NULL),
(430,63,5,'Узбуњивање становништва','STRING',1,NULL,0,NULL,0,NULL),
(431,63,6,'Време пражњења акумулације','STRING',1,NULL,0,NULL,0,NULL);