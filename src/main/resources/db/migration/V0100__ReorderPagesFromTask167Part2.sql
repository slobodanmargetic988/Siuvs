UPDATE `table_definition` SET `title` = 'Преглед залиха сточне хране' WHERE (`table_id` = '182');
DELETE FROM `table_definition` WHERE (`table_id` = '112');
UPDATE `table_definition` SET `order` = '5' WHERE (`table_id` = '113');
UPDATE `table_definition` SET `order` = '7' WHERE (`table_id` = '114');
UPDATE `table_definition` SET `order` = '8' WHERE (`table_id` = '115');

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(185, 73, 1, 0,'Преглед употребе републичких оперативних снага');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1374, 185, 1, 'Назив','STRING',1),
(1375, 185, 2, 'Рејон употребе','STRING',1),
(1376, 185, 3, 'Број припадника','STRING',1),
(1377, 185, 4, 'Задатак','STRING',1),
(1378, 185, 5, 'Напомена','STRING',1);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(186, 73, 2, 0,'Преглед употребе јединице цивилне заштите');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`) VALUES
(1379, 186, 1, 'Назив','STRING',1),
(1380, 186, 2, 'Рејон употребе','STRING',1),
(1381, 186, 3, 'Број припадника','STRING',1),
(1382, 186, 4, 'Задатак','STRING',1),
(1383, 186, 5, 'Напомена','STRING',1);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(187, 73, 3, 0, 'Преглед употребе субјеката од посебног значаја за заштиту и спасавање');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1384, 187, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1385, 187, 2, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1386, 187, 3, 'Рејон употребе','STRING',0,NULL,0,NULL,0),
(1387, 187, 4, 'Преглед средстава који се користе у ангажовању','STRING',0,NULL,0,NULL,0),
(1388, 187, 5, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1389, 187, 6, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1390, 187, 7, NULL,'STRING',0,NULL,0,'мобилни',1),
(1391, 187, 8, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(188, 73, 4, 0, 'Преглед употребе повереника и заменика повереника цивилне заштите');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1392, 188, 1, 'Насеље-месна заједница / правно лице','AGGREGATE',1,NULL,0),
(1393, 188, 2, 'Име  и презиме  повереника / заменика повереника','STRING',1,NULL,0),
(1394, 188, 3, 'Адреса становања','STRING',1,NULL,0),
(1395, 188, 4, 'Контакт телефони','STRING',3,'мобилни',1),
(1396, 188, 5, NULL,'STRING',0,'кућни',1),
(1397, 188, 6, NULL,'STRING',0,'посао',1),
(1398, 188, 7, 'Примедба','STRING',1,NULL,0);

UPDATE `table_definition` SET `title` = 'Преглед чланова штаба за ванредне ситуације' WHERE (`table_id` = '26');

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
(189, 81, 6, 0,'Преглед угрожених подручја и објеката у случају пожара, експлозија и пожара на отвореном');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `col_span`, `row2`, `col_span2`) VALUES
(1399, 189, 1,  'Насеље','STRING',1,NULL,0),
(1400, 189, 2,  'Угрожени објекти','NUMBER',2,'Број стамбених објеката',1),
(1401, 189, 3,  NULL,'NUMBER',0,'Број привредних објеката',1),
(1402, 189, 4,  'Угрожено становништво','NUMBER',2,'Број угрожених домаћинстава',1),
(1403, 189, 5,  NULL,'NUMBER',0,'Број становника',1),
(1404, 189, 6,  'Угрожене саобраћајнице','NUMBER',7,'Државни путеви IA реда (км)',1),
(1405, 189, 7,  NULL,'NUMBER',0,'Државни путеви IB реда (км)',1),
(1406, 189, 8,  NULL,'NUMBER',0,'Државни путеви IIA реда (км)',1),
(1407, 189, 9,  NULL,'NUMBER',0,'Државни путеви IIB реда (км)',1),
(1408, 189, 10, NULL,'NUMBER',0,'Oпштински путеви (км)',1),
(1409, 189, 11, NULL,'NUMBER',0,'Железничка пруга (км)',1),
(1410, 189, 12, NULL,'NUMBER',0,'Број мостова',1),
(1411, 189, 13, 'Угрожени остали објекти инфраструктуре','NUMBER',12,'Водоводна (км)',1),
(1412, 189, 14, NULL,'NUMBER',0,'Водоводна (број објеката)',1),
(1413, 189, 15, NULL,'NUMBER',0,'Електро (км)',1),
(1414, 189, 16, NULL,'NUMBER',0,'Електро (број објеката)',1),
(1415, 189, 17, NULL,'NUMBER',0,'ПТТ (км)',1),
(1416, 189, 18, NULL,'NUMBER',0,'ПТТ (број објеката)',1),
(1417, 189, 19, NULL,'NUMBER',0,'Гасовод (км)',1),
(1418, 189, 20, NULL,'NUMBER',0,'Госовод (број објеката)',1),
(1419, 189, 21, NULL,'NUMBER',0,'Топловод (км)',1),
(1420, 189, 22, NULL,'NUMBER',0,'Топловод (број објеката)',1),
(1421, 189, 23, NULL,'NUMBER',0,'Канализација (км)',1),
(1422, 189, 24, NULL,'NUMBER',0,'Канализација (број објеката)',1);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(190, 81, 7, 0, 'Преглед места и објеката за обезбеђење воде за гашење пожара');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`) VALUES
(1423, 190, 1, 'Место – објекат водоснабдевања','AGGREGATE',1,NULL,0),
(1424, 190, 2, 'Локација','STRING',1,NULL,0),
(1425, 190, 3, 'Основне карактеристике места - објекта','STRING',1,NULL,0),
(1426, 190, 4, 'Капацитет (m<sup>3</sup>;број)','NUMBER',1,NULL,0),
(1427, 190, 5, 'Напомена','STRING',1,NULL,0);

UPDATE `table_definition` SET `title` = 'Преглед обавеза (мера и задатака) у спровођењу евакуације угроженог становништва' WHERE (`table_id` = '9');
UPDATE `table_definition` SET `title` = 'Преглед обавеза (мера и задатака) у спровођењу збрињавања угроженог становништва' WHERE (`table_id` = '13');
UPDATE `table_definition` SET `title` = 'Преглед обавеза (мера и задатака) у спровођењу прве и медицинске помоћи' WHERE (`table_id` = '15');
UPDATE `table_definition` SET `title` = 'Преглед обавеза (мера и задатака) у спровођењу асанације терена' WHERE (`table_id` = '18');
UPDATE `table_definition` SET `title` = 'Преглед обавеза (мера и задатака) у спровођењу склањања становништва' WHERE (`table_id` = '21');
