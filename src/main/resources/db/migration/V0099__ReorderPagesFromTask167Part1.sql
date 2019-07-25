UPDATE `page` SET `order` = '1' WHERE (`page_id` = '19');
UPDATE `page` SET `order` = '2' WHERE (`page_id` = '20');
UPDATE `page` SET `order` = '3' WHERE (`page_id` = '76');
UPDATE `page` SET `order` = '4' WHERE (`page_id` = '22');
UPDATE `page` SET `parent_id` = '69', `order` = '5' WHERE (`page_id` = '73');

UPDATE `table_definition` SET `title` = 'Подсетник / упутство за рад одговорног лица за спровођење склањања становништва' WHERE (`table_id` = '20');
UPDATE `table_definition` SET `title` = 'Преглед мера и активности  у организовању и спровођењу склањања становништва' WHERE (`table_id` = '21');

DELETE FROM `table_definition` WHERE (`table_id` = '109');
DELETE FROM `table_definition` WHERE (`table_id` = '110');
DELETE FROM `table_definition` WHERE (`table_id` = '111');

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(176, 78, 7, 0, 'Преглед ветеринарских установа');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1310, 176, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1311, 176, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1312, 176, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1313, 176, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1314, 176, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1315, 176, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1316, 176, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
(177, 72, 6, 0,'Преглед објеката водоснабдевања');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`,`row4`) VALUES
(1317, 177, 1, 'Врста воде', 'STRING', 10, 'Питка вода', 6, 'извори', 2, 'локација'),
(1318, 177, 2, NULL, 'NUMBER', 0, NULL, 0, NULL, 0, 'капацитет m<sup>3</sup>'),
(1319, 177, 3, NULL, 'STRING', 0, NULL, 0, 'резервоари', 2, 'локација'),
(1320, 177, 4, NULL, 'NUMBER', 0, NULL, 0, NULL, 0, 'капацитет m<sup>3</sup>'),
(1321, 177, 5, NULL, 'STRING', 0, NULL, 0, 'бунaри', 2, 'локација'),
(1322, 177, 6, NULL, 'NUMBER', 0, NULL, 0, NULL, 0, 'капацитет m<sup>3</sup>'),
(1323, 177, 7, NULL, 'STRING', 0, 'Минерална вода', 2, 'локација', 1, NULL),
(1324, 177, 8, NULL, 'NUMBER', 0, NULL, 0, 'капацитет m<sup>3</sup>', 1, NULL),
(1325, 177, 7, NULL, 'STRING', 0, 'Термална вода', 2, 'локација', 1, NULL),
(1326, 177, 8, NULL, 'NUMBER', 0, NULL, 0, 'капацитет m<sup>3</sup>', 1, NULL);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(178, 72, 7, 0, 'Преглед привредних друштава које се баве производњом воде');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1327, 178, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1328, 178, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1329, 178, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1330, 178, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1331, 178, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1332, 178, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1333, 178, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(179, 72, 8, 0, 'Преглед привредних друштава и других правних лица који се баве производњом животних намирница');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1334, 179, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1335, 179, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1336, 179, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1337, 179, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1338, 179, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1339, 179, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1340, 179, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(180, 72, 9, 0, 'Преглед силоса, складишта и хладњача');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1341, 180, 1, 'Назив привредног друштва и др. правног лица (власника силоса/складишта)','STRING',1,NULL,0,NULL,0),
(1342, 180, 2, 'Локација (адреса и телефон)','STRING',1,NULL,0,NULL,0),
(1343, 180, 3, 'Силос','STRING',1,NULL,0,NULL,0),
(1344, 180, 4, 'Капацитет (тона)','STRING',1,NULL,0,NULL,0),
(1345, 180, 5, 'Складиште','STRING',1,NULL,0,NULL,0),
(1346, 180, 6, 'Капацитет (тона)','STRING',1,NULL,0,NULL,0),
(1347, 180, 6, 'Одговорно лице, телефон','STRING',1,NULL,0,NULL,0),
(1348, 180, 7, 'Примедба','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(181, 72, 10, 0, 'Преглед фарми');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1349, 181, 1, 'Назив фарме','STRING',1,NULL,0,NULL,0),
(1350, 181, 2, 'Врста фарме','STRING',1,NULL,0,NULL,0),
(1351, 181, 3, 'Облик својине','STRING',1,NULL,0,NULL,0),
(1352, 181, 4, 'Укупан капацитет','STRING',2,'јединица мере',1,NULL,0),
(1353, 181, 5, NULL,'NUMBER',0,'бројчани показатељ',1,NULL,0),
(1354, 181, 6, 'Слободан капацитет','STRING',2,'јединица мере',1,NULL,0),
(1355, 181, 7, NULL,'NUMBER',0,'бројчани показатељ',1,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(182, 72, 11, 0, 'Преглед залииха сточне хране');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1356, 182, 1, 'Врста хране','STRING',1,NULL,0,NULL,0),
(1357, 182, 2, 'Количина у kg','NUMBER',1,NULL,0,NULL,0),
(1358, 182, 3, 'Место/адреса за чување','STRING',1,NULL,0,NULL,0),
(1359, 182, 4, 'Особа задужена за пеглед','STRING',1,NULL,0,NULL,0),
(1360, 182, 5, 'Контакт особе задужене за преглед','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(183, 72, 12, 0, 'Преглед ветеринарских установа');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1361, 183, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1362, 183, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1363, 183, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1364, 183, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1365, 183, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1366, 183, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1367, 183, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(184, 72, 13, 0, 'Преглед материјалних и културно-историјских добара која подлежу заштити');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1368, 184, 1, 'Назив добра које подлеже заштити','STRING',1,NULL,0,NULL,0),
(1369, 184, 2, 'Врста','STRING',1,NULL,0,NULL,0),
(1370, 184, 3, 'Субјекат који се стара о добру','STRING',1,NULL,0,NULL,0),
(1371, 184, 4, 'Адреса (локација)','STRING',1,NULL,0,NULL,0),
(1372, 184, 5, 'Начин очувања','STRING',1,NULL,0,NULL,0),
(1373, 184, 6, 'Примедба','STRING',1,NULL,0,NULL,0);

