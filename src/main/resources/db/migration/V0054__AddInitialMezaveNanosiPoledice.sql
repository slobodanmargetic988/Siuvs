INSERT INTO `page` (`page_id`,`title`,`type`,`parent_id`,`order`) VALUES
(54,'Процена ризика','MENU',8,1),
(55,'План заштите и спасавања','TABLE',8,2),
(56,'Мере и задаци цивилне заштите','MENU',8,3),
(57,'Највероватнији нежељени догађај','ASSESSMENT',54,1),
(58,'Нежељени догађај са најтежим могућим последицама','ASSESSMENT',54,2);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(78, 55, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају снежних мећава, наноса и поледица и хладног таласа');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(515,78,1,'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0,NULL),
(516,78,2,'Делатност','STRING',0,NULL,0,NULL,0,NULL),
(517,78,3,'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0,NULL),
(518,78,4,'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0,NULL),
(519,78,5,NULL,'STRING',0,'телефони',2,'службени/факс',1,NULL),
(520,78,6,NULL,'STRING',0,NULL,0,'мобилни',1,NULL),
(521,78,7,'Имејл','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(79,55,2,0,'Преглед обавеза (мера и задатака) учесника у заштити и спасавању од снежних мећава, наноса и поледица и хладног таласа');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(522,79,1,'Мера / задатак','STRING',1,NULL,0,NULL,0,NULL),
(523,79,2,'Носилац','STRING',1,NULL,0,NULL,0,NULL),
(524,79,3,'Оперативни поступак','STRING',1,NULL,0,NULL,0,NULL),
(525,79,4,'Извршилац оперативног поступка','STRING',1,NULL,0,NULL,0,NULL),
(526,79,5,'Напомена (прилог – план)','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(80,55,4,1,'Преглед чланова стручно – оперативног тима за');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(527,80,1,'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0,NULL,0,NULL),
(528,80,2,'Дужност утиму','STRING',1,NULL,0,NULL,0,NULL),
(529,80,3,'Име и презиме ','STRING',1,NULL,0,NULL,0,NULL),
(530,80,4,'Контакт телефони','STRING',3,'мобилни',1,NULL,0,NULL),
(531,80,5,NULL,'STRING',0,'кућни',1,NULL,0,NULL),
(532,80,6,NULL,'STRING',0,'посао',1,NULL,0,NULL),
(533,80,7,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(81,55,5,0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања у случају снежних мећава, наноса и поледица и хладног таласа');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(534,81,1,'Задатак','STRING',1,NULL,0,NULL,0,NULL),
(535,81,2,'Одговорно лице','STRING',1,NULL,0,NULL,0,NULL),
(536,81,3,'Сарађује','STRING',1,NULL,0,NULL,0,NULL),
(537,81,4,'Рок извршења','STRING',1,NULL,0,NULL,0,NULL),
(538,81,5,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(82,55,6,0,'Преглед угрожених подручја од снежних наноса');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(539,82,1,'Угрожено подручје (назив насеља)','STRING',1,NULL,0,NULL,0,NULL),
(540,82,2,'Угрожено становништво','NUMBER',2,'Број домаћинстава',1,NULL,0,NULL),
(541,82,3,NULL,'NUMBER',0,'Број становника',1,NULL,0,NULL),
(542,82,4,'Угрожене саобраћајнице','NUMBER',7,'Државни путеви IA реда (км)',1,NULL,0,NULL),
(543,82,5,NULL,'NUMBER',0,'Државни путеви IB реда (км)',1,NULL,0,NULL),
(544,82,6,NULL,'NUMBER',0,'Државни путеви IIA реда (км)',1,NULL,0,NULL),
(545,82,7,NULL,'NUMBER',0,'Државни путеви IIB реда (км)',1,NULL,0,NULL),
(546,82,8,NULL,'NUMBER',0,'Oпштински путеви (км)',1,NULL,0,NULL),
(547,82,9,NULL,'NUMBER',0,'Железничка пруга (км)',1,NULL,0,NULL),
(548,82,10,NULL,'NUMBER',0,'Мостови (број)',1,NULL,0,NULL),
(549,82,11,'остали објекти угроже инфраструктуре','NUMBER',8,'Водоводна (км)',1,NULL,0,NULL),
(550,82,12,NULL,'NUMBER',0,'Водоводна (број објеката)',1,NULL,0,NULL),
(551,82,13,NULL,'NUMBER',0,'Електро (км)',1,NULL,0,NULL),
(552,82,14,NULL,'NUMBER',0,'Електро (број објеката)',1,NULL,0,NULL),
(553,82,15,NULL,'NUMBER',0,'ПТТ (км)',1,NULL,0,NULL),
(554,82,16,NULL,'NUMBER',0,'ПТТ (број објеката)',1,NULL,0,NULL),
(555,82,17,NULL,'NUMBER',0,'Топловод (км)',1,NULL,0,NULL),
(556,82,18,NULL,'NUMBER',0,'Топловод (број објеката)',1,NULL,0,NULL),
(557,82,19,'Угрожен сточни фонд','NUMBER',3,'Крупна стока (број)',1,NULL,0,NULL),
(558,82,20,NULL,'NUMBER',0,'Ситна стока (број)',1,NULL,0,NULL),
(559,82,21,NULL,'NUMBER',0,'Живина (број)',1,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(83,55,7,0,'Преглед подручја која могу бити „одсечена“ услед снежних наноса');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(560,83,1,'Назив насеља','STRING',1,NULL,0,NULL,0,NULL),
(561,83,2,'Број становика','STRING',1,NULL,0,NULL,0,NULL),
(562,83,3,'Болесна и немоћних лица','STRING',1,NULL,0,NULL,0,NULL),
(563,83,4,'Лица са посебним потребама','STRING',1,NULL,0,NULL,0,NULL),
(564,83,5,'Укупно','STRING',1,NULL,0,NULL,0,NULL);