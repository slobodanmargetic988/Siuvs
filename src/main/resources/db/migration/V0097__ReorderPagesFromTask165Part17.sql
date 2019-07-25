DELETE FROM `table_definition` WHERE (`table_id` = '38');
UPDATE `table_definition` SET `order` = '2' WHERE (`table_id` = '51');
UPDATE `table_definition` SET `order` = '5' WHERE (`table_id` = '39');
UPDATE `table_definition` SET `order` = '6' WHERE (`table_id` = '8');
UPDATE `table_definition` SET `order` = '7' WHERE (`table_id` = '9');

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(166, 24, 3, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у евакуацији');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1255, 166, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1256, 166, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1257, 166, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1258, 166, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1259, 166, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1260, 166, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1261, 166, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('167', '24', '4', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у евакуацији');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('1262', '167', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('1263', '167', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('1264', '167', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('1265', '167', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

DELETE FROM `table_definition` WHERE (`table_id` = '40');
UPDATE `table_definition` SET `order` = '3' WHERE (`table_id` = '32');
UPDATE `table_definition` SET `order` = '4' WHERE (`table_id` = '11');
UPDATE `table_definition` SET `order` = '7' WHERE (`table_id` = '41');
UPDATE `table_definition` SET `order` = '8' WHERE (`table_id` = '12');
UPDATE `table_definition` SET `order` = '9' WHERE (`table_id` = '13');

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(168, 25, 5, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у збрињавању');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1266, 168, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1267, 168, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1268, 168, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1269, 168, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1270, 168, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1271, 168, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1272, 168, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('169', '25', '6', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у збрињавању');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('1273', '169', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('1274', '169', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('1275', '169', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('1276', '169', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

UPDATE `table_definition` SET `order` = '4' WHERE (`table_id` = '43');
UPDATE `table_definition` SET `order` = '5' WHERE (`table_id` = '14');
UPDATE `table_definition` SET `order` = '6' WHERE (`table_id` = '15');

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(170, 26, 2, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у евакуацији');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1277, 170, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1278, 170, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1279, 170, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1280, 170, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1281, 170, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1282, 170, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1283, 170, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('171', '26', '3', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у евакуацији');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('1284', '171', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('1285', '171', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('1286', '171', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('1287', '171', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

UPDATE `table_definition` SET `order` = '3' WHERE (`table_id` = '44');
UPDATE `table_definition` SET `order` = '4' WHERE (`table_id` = '17');
UPDATE `table_definition` SET `order` = '5' WHERE (`table_id` = '18');

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(172, 27, 1, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у асанацији');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1288, 172, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1289, 172, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1290, 172, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1291, 172, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1292, 172, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1293, 172, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1294, 172, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('173', '27', '2', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у асанацији');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('1295', '173', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('1296', '173', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('1297', '173', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('1298', '173', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

DELETE FROM `table_definition` WHERE (`table_id` = '47');
DELETE FROM `table_definition` WHERE (`table_id` = '97');
DELETE FROM `table_definition` WHERE (`table_id` = '19');
UPDATE `table_definition` SET `order` = '1' WHERE (`table_id` = '98');
UPDATE `table_definition` SET `order` = '2' WHERE (`table_id` = '48');
UPDATE `table_definition` SET `order` = '5' WHERE (`table_id` = '49');
UPDATE `table_definition` SET `order` = '6' WHERE (`table_id` = '20');
UPDATE `table_definition` SET `order` = '7' WHERE (`table_id` = '21');

INSERT INTO `table_definition` (`table_id`,`page_id`, `order`, `user_defined`, `title`) VALUES
(174, 28, 3, 0, 'Преглед субјеката система заштите и спасавања који се ангажују у склањању');
INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1299, 174, 1, 'Назив субјекта / адреса /','STRING',0,NULL,0,NULL,0),
(1300, 174, 2, 'Делатност','STRING',0,NULL,0,NULL,0),
(1301, 174, 3, 'Активност у којој се ангажује','STRING',0,NULL,0,NULL,0),
(1302, 174, 4, 'Одговорно лице','STRING',3,'Име и презиме',1,NULL,0),
(1303, 174, 5, NULL,'STRING',0,'телефони',2,'службени/факс',1),
(1304, 174, 6, NULL,'STRING',0,NULL,0,'мобилни',1),
(1305, 174, 7, 'Имејл','STRING',1,NULL,0,NULL,0);

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('175', '28', '4', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у склањању');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('1306', '175', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('1307', '175', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('1308', '175', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('1309', '175', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

