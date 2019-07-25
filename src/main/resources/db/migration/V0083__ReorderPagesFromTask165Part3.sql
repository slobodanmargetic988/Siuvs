UPDATE `table_definition` SET `title` = 'Списак припадника јединице цивилне заштите опште намене' WHERE (`table_id` = '36');

UPDATE `table_definition` SET `title` = 'Подсетник / упутство за рад одговорног лица за спровођење заштите и спасавања од земљотреса' WHERE (`table_id` = '37');

UPDATE `table_definition` SET `order` = '6', `title` = 'Преглед угрожених подручја и објеката у случају земљотреса' WHERE (`table_id` = '45');
UPDATE `table_definition` SET `order` = '1' WHERE (`table_id` = '30');
UPDATE `table_definition` SET `order` = '2' WHERE (`table_id` = '96');
UPDATE `table_definition` SET `order` = '3' WHERE (`table_id` = '50');
UPDATE `table_definition` SET `order` = '4' WHERE (`table_id` = '37');
UPDATE `table_definition` SET `order` = '5' WHERE (`table_id` = '7');
UPDATE `table_definition` SET `order` = '7' WHERE (`table_id` = '6');

UPDATE `table_column` SET `row2` = 'Број мостова' WHERE (`column_id` = '288');

UPDATE `table_definition` SET `order` = '5' WHERE (`table_id` = '53');
UPDATE `table_definition` SET `order` = '3' WHERE (`table_id` = '54');
UPDATE `table_definition` SET `order` = '4' WHERE (`table_id` = '55');
UPDATE `table_definition` SET `order` = '6', `title` = 'Преглед угрожених подручја и објеката у случају клизишта' WHERE (`table_id` = '57');
UPDATE `table_definition` SET `order` = '8' WHERE (`table_id` = '56');
UPDATE `table_definition` SET `order` = '7' WHERE (`table_id` = '58');


INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('122', '30', '2', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у случају одрона, клизишта и ерозија');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('819', '122', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('820', '122', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('821', '122', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('822', '122', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');