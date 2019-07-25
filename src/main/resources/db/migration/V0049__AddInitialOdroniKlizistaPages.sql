INSERT INTO `page` (`page_id`,`title`,`type`,`parent_id`,`order`) VALUES
(29,'Процена ризика','MENU',2,1),
(30,'План заштите и спасавања','TABLE',2,2),
(31,'Мере и задаци цивилне заштите','MENU',2,3),
(32,'Највероватнији нежељени догађај','ASSESSMENT',29,1),
(33,'Нежељени догађај са најтежим могућим последицама','ASSESSMENT',29,2);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(52, 30, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у случају одрона, клизишта и ерозије');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(346,52,1,'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0,NULL),
(347,52,2,'Делатност','STRING',0,NULL,0,NULL,0,NULL),
(348,52,3,'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0,NULL),
(349,52,4,'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0,NULL),
(350,52,5,NULL,'STRING',0,'телефони',2,'службени/факс',1,NULL),
(351,52,6,NULL,'STRING',0,NULL,0,'мобилни',1,NULL),
(352,52,7,'Имејл','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(53,30,2,0,'Преглед обавеза (мера и задатака) учесника у заштити и спасавању од одрона, клизишта и ерозије');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(353,53,1,'Мера / задатак','STRING',1,NULL,0,NULL,0,NULL),
(354,53,2,'Носилац','STRING',1,NULL,0,NULL,0,NULL),
(355,53,3,'Оперативни поступак','STRING',1,NULL,0,NULL,0,NULL),
(356,53,4,'Извршилац оперативног поступка','STRING',1,NULL,0,NULL,0,NULL),
(357,53,5,'Напомена (прилог – план)','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(54,30,4,1,'Преглед чланова стручно – оперативног тима за');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(358,54,1,'Назив стручно-оперативног тима','AGGREGATE',1,NULL,0,NULL,0,NULL),
(359,54,2,'Дужност утиму','STRING',1,NULL,0,NULL,0,NULL),
(360,54,3,'Име и презиме ','STRING',1,NULL,0,NULL,0,NULL),
(361,54,4,'Контакт телефони','STRING',3,'мобилни',1,NULL,0,NULL),
(362,54,5,NULL,'STRING',0,'кућни',1,NULL,0,NULL),
(363,54,6,NULL,'STRING',0,'посао',1,NULL,0,NULL),
(364,54,7,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(55,30,5,0,'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања у случају одрона, клизишта и ерозије');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(365,55,1,'Задатак','STRING',1,NULL,0,NULL,0,NULL),
(366,55,2,'Одговорно лице','STRING',1,NULL,0,NULL,0,NULL),
(367,55,3,'Сарађује','STRING',1,NULL,0,NULL,0,NULL),
(368,55,4,'Рок извршења','STRING',1,NULL,0,NULL,0,NULL),
(369,55,5,'Примедба','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(56,30,6,0,'Преглед карактеристика и локације клизишта');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(377,56,1,'Ознака клизишта','STRING',1,NULL,0,NULL,0,NULL),
(378,56,2,'Локација/Координате','STRING',1,NULL,0,NULL,0,NULL),
(379,56,3,'Статус','ENUM',1,NULL,0,NULL,0,NULL),
(380,56,4,'Димензије у m','STRING',3,'Дужина',1,NULL,0,NULL),
(381,56,5,NULL,'STRING',0,'Ширина',1,NULL,0,NULL),
(382,56,6,NULL,'STRING',0,'Дубина',1,NULL,0,NULL),
(383,56,7,'Запремина m<sup>3</sup>','STRING',1,NULL,0,NULL,0,NULL);

INSERT INTO `table_column_value` (`id`,`column_id`,`value`,`order`) VALUES
(7,379,'Активно',1),
(8,379,'Санирано-стабилизовано',2),
(9,379,'Тренутно умирено',3),
(10,379,'Реактивно',4);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(57,30,7,0,'Преглед угрожених подручја, места или грађевина у случају клизишта');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(384,57,1,'Насеље','STRING',1,NULL,0,NULL,0,NULL),
(385,57,2,'Угрожени објекти','NUMBER',2,'Број стамбених објеката',1,NULL,0,NULL),
(386,57,3,NULL,'NUMBER',0,'Број привредних објеката',1,NULL,0,NULL),
(387,57,4,'Угрожено становништво','NUMBER',2,'Број угрожених домаћинстава',1,NULL,0,NULL),
(388,57,5,NULL,'NUMBER',0,'Број становника',1,NULL,0,NULL),
(389,57,6,'Угрожене саобраћајнице','NUMBER',3,'Државни путеви (км)',1,NULL,0,NULL),
(390,57,7,NULL,'NUMBER',0,'Oпштински путеви (км)',1,NULL,0,NULL),
(391,57,8,NULL,'NUMBER',0,'Железничка пруга (км)',1,NULL,0,NULL),
(392,57,9,'Угрожени остали објекти инфраструктуре','NUMBER',6,'Водоводна (км)',1,NULL,0,NULL),
(393,57,10,NULL,'NUMBER',0,'Електро (км)',1,NULL,0,NULL),
(394,57,11,NULL,'NUMBER',0,'ПТТ (км)',1,NULL,0,NULL),
(395,57,12,NULL,'NUMBER',0,'Гасовод (км)',1,NULL,0,NULL),
(396,57,13,NULL,'NUMBER',0,'Топловод (км)',1,NULL,0,NULL),
(397,57,14,NULL,'NUMBER',0,'Канализација (км)',1,NULL,0,NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(58,30,8,0,'Преглед локација за одлагање отпадног грађевинског и другог материјала ');

INSERT INTO `table_column` (`column_id`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(398,58,1,'Назив локације и адреса','STRING',1,NULL,0,NULL,0,NULL),
(399,58,2,'Капацитет-површина m<sup>2</sup>','NUMBER',1,NULL,0,NULL,0,NULL),
(400,58,3,'Власништво','STRING',1,NULL,0,NULL,0,NULL),
(401,58,4,'Примедба','STRING',1,NULL,0,NULL,0,NULL);