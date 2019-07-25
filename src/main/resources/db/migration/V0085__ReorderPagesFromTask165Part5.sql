DELETE FROM table_definition WHERE table_id IN (23, 1, 2, 24, 25);

DELETE FROM `page` WHERE (`page_id` = '18');

UPDATE `table_definition` SET `order` = '3' WHERE (`table_id` = '61');
UPDATE `table_definition` SET `order` = '4' WHERE (`table_id` = '62');
UPDATE `table_definition` SET `order` = '7' WHERE (`table_id` = '63');
UPDATE `table_definition` SET `order` = '5' WHERE (`table_id` = '60');

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('123', '35', '2', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у случају поплава');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('823', '123', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('824', '123', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('825', '123', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('826', '123', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');

INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
(124,35,6,0,'Преглед угрожених подручја и објеката у случају поплава');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `col_span`, `row2`, `col_span2`) VALUES
(827,124,1,'Насеље','STRING',1,NULL,0),
(828,124,2,'Угрожени објекти','NUMBER',2,'Број стамбених објеката',1),
(829,124,3,NULL,'NUMBER',0,'Број привредних објеката',1),
(830,124,4,'Угрожено становништво','NUMBER',2,'Број угрожених домаћинстава',1),
(831,124,5,NULL,'NUMBER',0,'Број становника',1),
(832,124,6,'Угрожене саобраћајнице','NUMBER',7,'Државни путеви IA реда (км)',1),
(833,124,7,NULL,'NUMBER',0,'Државни путеви IB реда (км)',1),
(834,124,8,NULL,'NUMBER',0,'Државни путеви IIA реда (км)',1),
(835,124,9,NULL,'NUMBER',0,'Државни путеви IIB реда (км)',1),
(836,124,10,NULL,'NUMBER',0,'Oпштински путеви (км)',1),
(837,124,11,NULL,'NUMBER',0,'Железничка пруга (км)',1),
(838,124,12,NULL,'NUMBER',0,'Број мостова',1),
(839,124,13,'Угрожени остали објекти инфраструктуре','NUMBER',12,'Водоводна (км)',1),
(840,124,14,NULL,'NUMBER',0,'Водоводна (број објеката)',1),
(841,124,15,NULL,'NUMBER',0,'Електро (км)',1),
(842,124,16,NULL,'NUMBER',0,'Електро (број објеката)',1),
(843,124,17,NULL,'NUMBER',0,'ПТТ (км)',1),
(844,124,18,NULL,'NUMBER',0,'ПТТ (број објеката)',1),
(845,124,19,NULL,'NUMBER',0,'Гасовод (км)',1),
(846,124,20,NULL,'NUMBER',0,'Госовод (број објеката)',1),
(847,124,21,NULL,'NUMBER',0,'Топловод (км)',1),
(848,124,22,NULL,'NUMBER',0,'Топловод (број објеката)',1),
(849,124,23,NULL,'NUMBER',0,'Канализација (км)',1),
(850,124,24,NULL,'NUMBER',0,'Канализација (број објеката)',1);
