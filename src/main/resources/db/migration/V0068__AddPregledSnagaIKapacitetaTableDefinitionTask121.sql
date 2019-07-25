INSERT INTO `table_definition` (`table_id`, `page_id`, `order`, `user_defined`, `title`) VALUES
('96', '21', '4', '0', 'Преглед снага и расположивих капацитета заштите и спасавања који се ангажују у случају земљотреса');
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`) VALUES
('641', '96', '1', 'Снаге заштите и спасавања', 'STRING', '0', '1'),
('642', '96', '2', 'Број људи који се ангажује', 'NUMBER', '0', '1'),
('643', '96', '3', 'Врсте капацитета / јединица мере', 'STRING', '1', '1'),
('644', '96', '4', 'Врста материјално техничких средстава  / јединица мере', 'STRING', '1', '1');