Delete from `page` where `title` = '6. Прегледи снага и капацитета';

insert into `page` (`page_id`,`title`,`type`,`risk_type`,`attachable_photos`,`parent_id`,`order`) values
 ('554','6. Прегледи снага и капацитета','MENU',0,0,null,6)
;

INSERT INTO `page` (`page_id`,`title`,`type`,`risk_type`,`attachable_photos`,`parent_id`,`order`) values
('555','Збирни обрасци','ZBIRNI',0,0,'554',1),
('556','Евиденциони картони','KARTONI',0,0,'554',2),
('557', 'Преглед капацитета за спровођење мера цивилне заштите који се ангажују у','TABLE',0,0,'554',3);

INSERT INTO `table_definition` (`table_id`,`page_id`,`order`,`user_defined`,`title`) VALUES
('5001', '557', '1', '0', 'Преглед капацитета за спровођење мера цивилне заштите који се ангажују у евакуацији'),
('5002', '557', '2', '0', 'Преглед капацитета за спровођење мера цивилне заштите који се ангажују у збрињавању угрожених и настрадалих'),
('5003', '557', '3', '0', 'Преглед капацитета за спровођење мера цивилне заштите који се ангажују у првој и медицинској помоћи и лабараторије'),
('5004', '557', '4', '0', 'Преглед капацитета за спровођење мера цивилне заштите који се ангажују у асанацији терена');


INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`, `row2`, `col_span2`) VALUES
('50011', '5001', '1', 'Власник капацитета за спровођење мере ЦЗ', 'STRING', '0', '1', NULL, '0'),
('50012', '5001', '2', 'Адреса', 'STRING', '0', '1', NULL, '0'),
('50013', '5001', '3', 'Телефон', 'STRING', '0', '1', NULL, '0'),
('50014', '5001', '4', 'Е-маил адреса', 'STRING', '0', '1', NULL, '0'),
('50015', '5001', '5', 'Одговорно лице', 'STRING', '0', '4','Име и презиме ', '1'),
('50016', '5001', '6', NULL, 'STRING', '0', '0', 'Телефон фиксни', '1'),
('50017', '5001', '7', NULL, 'STRING', '0', '0', 'Телефон мобилни', '1'),
('50018', '5001', '8', NULL, 'STRING', '0', '0', 'Е-маил адреса', '1'), 

('50019', '5001', '9', 'Адреса локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500110', '5001', '10', 'Географска ширина локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500111', '5001', '11', 'Географска дужина  локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500112', '5001', '12', 'Надлежна организациона јединица СВС која води евиденцију', 'STRING', '0', '1', NULL, '0'),

('500113', '5001', '13', 'Напомена (опис капацитета)', 'STRING', '0', '1', NULL, '0'),

('500114', '5001', '14', 'Врста капацитета/јединица мере','NUMBER', '0','6', 'Превоз људи/број особа у једној тури)','1'),
('500115', '5001', '15', NULL, 'NUMBER', '0', '0', 'Превоз сточног фонда/број грла крупне стоке у једној тури', '1'),
('500116', '5001', '16', NULL, 'NUMBER', '0', '0','Превоз сточног фонда/број ситне стоке у једној тури', '1'),
('500117', '5001', '17', NULL, 'NUMBER', '0', '0','Превоз сточног фонда/број живине у једној тури', '1'),
('500118', '5001', '18', NULL, 'NUMBER', '0', '0','Превоз материјалних и културних добара/м3 у једној тури', '1'),
('500119', '5001', '19', NULL, 'NUMBER', '0', '0','Превоз материјалних и културних добара/тона у једној тури', '1');




INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`, `row2`, `col_span2`) VALUES
('50021', '5002', '1', 'Власник капацитета за спровођење мере ЦЗ', 'STRING', '0', '1', NULL, '0'),
('50022', '5002', '2', 'Адреса', 'STRING', '0', '1', NULL, '0'),
('50023', '5002', '3', 'Телефон', 'STRING', '0', '1', NULL, '0'),
('50024', '5002', '4', 'Е-маил адреса', 'STRING', '0', '1', NULL, '0'),

('50025', '5002', '5', 'Одговорно лице', 'STRING', '0', '4','Име и презиме ', '1'),
('50026', '5002', '6', NULL, 'STRING', '0', '0', 'Телефон фиксни', '1'),
('50027', '5002', '7', NULL, 'STRING', '0', '0', 'Телефон мобилни', '1'),
('50028', '5002', '8', NULL, 'STRING', '0', '0', 'Е-маил адреса', '1'), 

('50029', '5002', '9', 'Адреса локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500210', '5002', '10', 'Географска ширина локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500211', '5002', '11', 'Географска дужина  локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500212', '5002', '12', 'Надлежна организациона јединица СВС која води евиденцију', 'STRING', '0', '1', NULL, '0'),
('500213', '5002', '13', 'Напомена (опис капацитета)', 'STRING', '0', '1', NULL, '0'),

('500214', '5002', '14', 'Врста капацитета/јединица мере','NUMBER', '0','7', 'Смештај угрожених и настрадалих/број особа који се може сместити)', '1'),
('500215', '5002', '15', NULL, 'NUMBER', '0', '0','Исхрана угрожених и настрадалих/број топлих оброка који се дневно може припремити', '1'),
('500216', '5002', '16', NULL, 'NUMBER', '0', '0','Исхрана угрожених и настрадалих/број сувих оброка', '1'),
('500217', '5002', '17', NULL, 'NUMBER', '0', '0','Снабдевање флашираном водом/литара на дневном нивоу', '1'),
('500218', '5002', '18', NULL, 'NUMBER', '0', '0','Смештај сточног фонда/број грла крупне стоке', '1'),
('500219', '5002', '19', NULL, 'NUMBER', '0', '0','Смештај сточног фонда/број ситне стоке', '1'),
('500220', '5002', '20', NULL, 'NUMBER', '0', '0','Смештај сточног фонда/број живине', '1');

INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`, `row2`, `col_span2`) VALUES
('50031', '5003', '1', 'Власник капацитета за спровођење мере ЦЗ', 'STRING', '0', '1', NULL, '0'),
('50032', '5003', '2', 'Адреса', 'STRING', '0', '1', NULL, '0'),
('50033', '5003', '3', 'Телефон', 'STRING', '0', '1', NULL, '0'),
('50034', '5003', '4', 'Е-маил адреса', 'STRING', '0', '1', NULL, '0'),

('50035', '5003', '5', 'Одговорно лице', 'STRING', '0', '4','Име и презиме ', '1'),
('50036', '5003', '6', NULL, 'STRING', '0', '0', 'Телефон фиксни', '1'),
('50037', '5003', '7', NULL, 'STRING', '0', '0', 'Телефон мобилни', '1'),
('50038', '5003', '8', NULL, 'STRING', '0', '0', 'Е-маил адреса', '1'), 

('50039', '5003', '9', 'Адреса локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500310', '5003', '10', 'Географска ширина локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500311', '5003', '11', 'Географска дужина  локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500312', '5003', '12', 'Надлежна организациона јединица СВС која води евиденцију', 'STRING', '0', '1', NULL, '0'),
('500313', '5003', '13', 'Напомена (опис капацитета)', 'STRING', '0', '1', NULL, '0'),

('500314', '5003', '14', 'Врста капацитета/јединица мере','NUMBER', '0','12', 'Здравствена екипа теренска- број екипа/број људи у екипи)', '1'),
('500315', '5003', '15', NULL, 'NUMBER', '0', '0','Екипа хитне медицинске помоћи- број екипа/број људи у екипи', '1'),
('500316', '5003', '16', NULL, 'NUMBER', '0', '0','Хемијска лабораторија- број лабораторијских анализа/час', '1'),
('500317', '5003', '17', NULL, 'NUMBER', '0', '0','Биохемијска лабораторија- број лабораторијских анализа/час', '1'),
('500318', '5003', '18', NULL, 'NUMBER', '0', '0','Медицинско-биохемијска лабораторија - број лабораторијских анализа/час', '1'),
('500319', '5003', '19', NULL, 'NUMBER', '0', '0','Микробиолошка лабораторија- број лабораторијских анализа/час', '1'),
('500320', '5003', '20', NULL, 'NUMBER', '0', '0','Ветеринарска лобораторија- број лабораторијских анализа/час', '1'),
('500321', '5003', '21', NULL, 'NUMBER', '0', '0','Болнички смештај- број планираних болничких кревета', '1'),
('500322', '5003', '22', NULL, 'NUMBER', '0', '0','Екипа за контролу квалитета и исправности воде- број екипа/број људи у екипи', '1'),
('500323', '5003', '23', NULL, 'NUMBER', '0', '0','Екипа за контролу квалитета и исправности хране- број екипа/број људи у екипи', '1'),
('500324', '5003', '24', NULL, 'NUMBER', '0', '0','Екипа за имунизацију људства против заразних болести- број екипа/број људи у екипи', '1'),
('500325', '5003', '25', NULL, 'NUMBER', '0', '0','Екипа за санитарни надзор- број екипа/број људи у екипи', '1');

INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`, `row2`, `col_span2`) VALUES
('50041', '5004', '1', 'Власник капацитета за спровођење мере ЦЗ', 'STRING', '0', '1', NULL, '0'),
('50042', '5004', '2', 'Адреса', 'STRING', '0', '1', NULL, '0'),
('50043', '5004', '3', 'Телефон', 'STRING', '0', '1', NULL, '0'),
('50044', '5004', '4', 'Е-маил адреса', 'STRING', '0', '1', NULL, '0'),

('50045', '5004', '5', 'Одговорно лице', 'STRING', '0', '4','Име и презиме ', '1'),
('50046', '5004', '6', NULL, 'STRING', '0', '0', 'Телефон фиксни', '1'),
('50047', '5004', '7', NULL, 'STRING', '0', '0', 'Телефон мобилни', '1'),
('50048', '5004', '8', NULL, 'STRING', '0', '0', 'Е-маил адреса', '1'), 

('50049', '5004', '9', 'Адреса локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500410', '5004', '10', 'Географска ширина локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500411', '5004', '11', 'Географска дужина  локације капацитета', 'STRING', '0', '1', NULL, '0'),
('500412', '5004', '12', 'Надлежна организациона јединица СВС која води евиденцију', 'STRING', '0', '1', NULL, '0'),
('500413', '5004', '13', 'Напомена (опис капацитета)', 'STRING', '0', '1', NULL, '0'),

('500414', '5004', '14', 'Врста капацитета/јединица мере','NUMBER', '0','5', 'Екипа за дезинсекцију- број екипа/број људи у екипи', '1'),
('500415', '5004', '15', NULL, 'NUMBER', '0', '0','Екипа за дезинфекцију- број екипа/број људи у екипи', '1'),
('500416', '5004', '16', NULL, 'NUMBER', '0', '0','Екипа за дератизацију- број екипа/број људи у екипи', '1'),
('500417', '5004', '17', NULL, 'NUMBER', '0', '0','Екипа за поправку водоводне мреже- број екипа/број људи у екипи', '1'),
('500418', '5004', '18', NULL, 'NUMBER', '0', '0','Екипа за поправку канализационе мреже- број екипа/број људи у екипи', '1');
