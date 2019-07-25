UPDATE `table_column` SET `title` = 'Особа задужена за преглед' WHERE (`column_id` = '1359');

UPDATE `table_column` SET `title` = 'Назив субјекта' WHERE (`column_id` = '1361');
DELETE FROM `table_column` WHERE (`column_id` = '1362');
DELETE FROM `table_column` WHERE (`column_id` = '1363');


INSERT INTO `table_column`
(`column_id_internal`,`table_definition_id`,`order`,`title`,`type`,`col_span`,`row2`,`col_span2`,`row3`,`col_span3`) VALUES
(1673, 183, 2, 'Адреса','STRING',0,NULL,0,NULL,0);

UPDATE `table_column` SET `col_span` = '10' WHERE (`column_id` = '658');
