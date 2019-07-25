UPDATE `page` SET `type`='TABLE' WHERE `page_id`='71';

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
('99', '71', '1', '1', 'Преглед обавеза (мера и задатака) учесника у заштити и спасавању од опасности'),
('100', '71', '2', '0', 'Преглед субјеката система заштите и спасавања'),
('101', '71', '3', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у заштити и спасавању'),
('102', '71', '4', '1', 'Преглед чланова стручно - оперативног тима за'),
('103', '71', '5', '0', 'Подсетник (упутство) за рад одговорног лица за спровођење заштите и спасавања');

INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('668', '99', '1', 'Мера / Задатак', 'STRING', '0', '1'),
('669', '99', '2', 'Носилац', 'STRING', '0', '1'),
('670', '99', '3', 'Оперативни поступак', 'STRING', '0', '1'),
('671', '99', '4', 'Извршилац оперативног поступка', 'STRING', '0', '1'),
('672', '99', '5', 'Напомена (прилог - план)', 'STRING', '0', '1');

INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`, `row2`, `col_span2`, `row3`, `col_span3`) VALUES
('673', '100', '1', 'Назив субјекта / адреса', 'STRING', '0', '1', NULL, '0', NULL, '0'),
('674', '100', '2', 'Делатност', 'STRING', '0', '1', NULL, '0', NULL, '0'),
('675', '100', '3', 'Активност у којој се ангажује', 'STRING', '0', '1', NULL, '0', NULL, '0'),
('676', '100', '4', 'Одговорно лице', 'STRING', '0', '3', 'Име и презиме', '1', NULL, '0'),
('677', '100', '5', NULL, 'STRING', '0', '0', 'Телефони', '2', 'Службени/факс', '1'),
('678', '100', '6', NULL, 'STRING', '0', '0', NULL, '0', 'Мобилни', '1'),
('679', '100', '7', 'е-маил', 'STRING', '0', '1', NULL, '0', NULL, '0');

INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`, `row2`, `col_span2`) VALUES
('680', '101', '1', 'Снаге заштите и спасавања', 'ENUM', '0', '1', NULL, '0'),
('681', '101', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1', NULL, '0'),
('682', '101', '3', 'Врста капацитета / јединица мере', 'STRING', '0', '4', 'Људство', '1'),
('683', '101', '4', NULL, 'STRING', '0', '0', 'Јединице ЦЗ ОН', '1'),
('684', '101', '5', NULL, 'STRING', '0', '0', 'Повереник и заменик пов. ЦЗ', '1'),
('685', '101', '6', NULL, 'STRING', '0', '0', 'Члан ОШ ВС', '1'),
('686', '101', '7', 'Врста материјално техничких средстава / јединица мере', 'STRING', '0', '11', 'Камиони', '1'),
('687', '101', '8', NULL, 'STRING', '0', '0', 'Булдожери', '1'),
('688', '101', '9', NULL, 'STRING', '0', '0', 'Цистерна за воду', '1'),
('689', '101', '10', NULL, 'STRING', '0', '0', 'Лопата', '1'),
('690', '101', '11', NULL, 'STRING', '0', '0', 'Крамп', '1'),
('691', '101', '12', NULL, 'STRING', '0', '0', 'Моторна тестера', '1'),
('692', '101', '13', NULL, 'STRING', '0', '0', 'Грабуља', '1'),
('693', '101', '14', NULL, 'STRING', '0', '0', 'Пумпа за воду', '1'),
('694', '101', '15', NULL, 'STRING', '0', '0', 'ПП апарати', '1'),
('695', '101', '16', NULL, 'STRING', '0', '0', 'Аутодизалица', '1'),
('696', '101', '17', NULL, 'STRING', '0', '0', 'Ватрогасна возила', '1');

INSERT INTO `table_column_value` (`id`, `column_id`, `value`, `order`) VALUES
('11', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Земљотреси', '1'),
('12', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Одрони, клизишта и ерозије', '2'),
('13', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Поплаве', '3'),
('14', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Град', '4'),
('15', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Олујни ветар', '5'),
('16', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Топли талас', '6'),
('17', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Суша', '7'),
('18', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Снежне мећаве, наноси и поледица, хладни талас', '8'),
('19', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Недостак воде за пиће', '9'),
('20', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Епидемије и пандемије', '10'),
('21', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Болести животиња', '11'),
('22', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Пожари и експлозије, пожари на отвореном', '12'),
('23', (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '680' LIMIT 1), 'Техничко-технолошки удеси', '13');

INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`, `row2`, `col_span2`) VALUES
('697', '102', '1', 'Назив стручно - оперативног тима', 'AGGREGATE', '0', '1', NULL, '0'),
('698', '102', '2', 'Дужност утиму', 'STRING', '0', '1', NULL, '0'),
('699', '102', '3', 'Име и презиме ', 'STRING', '0', '1', NULL, '0'),
('700', '102', '4', 'Контакт телефони', 'STRING', '0', '3', 'мобилни', '1'),
('701', '102', '5', NULL, 'STRING', '0', '0', 'кућни', '0'),
('702', '102', '6', NULL, 'STRING', '0', '0', 'посао', '0'),
('703', '102', '7', 'Примедба', 'STRING', '0', '1', NULL, '0');

INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('704', '103', '1', 'Задатак', 'STRING', '0', '1'),
('705', '103', '2', 'Одговорно лице', 'STRING', '0', '1'),
('706', '103', '3', 'Сарађује', 'STRING', '0', '1'),
('707', '103', '4', 'Рок извршења', 'STRING', '0', '1'),
('708', '103', '5', 'Примедба', 'STRING', '0', '1');