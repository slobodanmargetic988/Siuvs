
UPDATE `table_column` SET `order`= 1 WHERE `column_id`=60042;
UPDATE `table_column` SET `order`= 2 WHERE `column_id`=60041;
UPDATE `table_column` SET `title`= 'Назив јединице (мора садржати јачину: оделење,вод,чета и назив насељеног места)' WHERE `column_id`=60041;


UPDATE `table_column` SET `col_span`=12, `row2`='Екипа за дезинсекцију',`col_span2`=2,`row3`='број екипа',col_span3=1 WHERE `column_id_internal`=500414;
UPDATE `table_column` SET `col_span`=0, `row2`=NULL,`col_span2`=0,`row3`='укупан број људи',`col_span3`=1 WHERE `column_id_internal`=500415;

UPDATE `table_column` SET  `row2`='Екипа за дезинфекцију',`col_span2`=2,`row3`='број екипа',`col_span3`=1 WHERE `column_id_internal`=500416;
UPDATE `table_column` SET `col_span`=0, `row2`=NULL,`col_span2`=0,`row3`='укупан број људи',`col_span3`=1 WHERE `column_id_internal`=500417;

UPDATE `table_column` SET  `row2`='Екипа за дератизацију',`col_span2`=2,`row3`='број екипа',`col_span3`=1 WHERE `column_id_internal`=500418;
INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`,`type`, `is_dynamic`, `col_span`, `row2`,`col_span2`,`row3`,`col_span3`) VALUES
('500419', '5004','19',NULL,'NUMBER', '0','0',NULL, '0','укупан број људи','1'),
('500420', '5004','20',NULL,'NUMBER', '0','0','Екипа за ддд', '2','број екипа','1'),
('500421', '5004','21',NULL,'NUMBER', '0','0',NULL, '0','укупан број људи','1'),
('500422', '5004','22',NULL,'NUMBER', '0','0','Екипа за поправку водоводне мреже', '2','број екипа','1'),
('500423', '5004','23',NULL,'NUMBER', '0','0',NULL, '0','укупан број људи','1'),
('500424', '5004','24',NULL,'NUMBER', '0','0','Екипа за поправку канализационе мреже', '2','број екипа','1'),
('500425', '5004','25',NULL,'NUMBER', '0','0',NULL, '0','укупан број људи','1');


UPDATE `table_column` SET `col_span`=18, `row2`='Здравствена екипа теренска',`col_span2`=2,`row3`='број екипа',col_span3=1 WHERE `column_id_internal`=500314;
UPDATE `table_column` SET `col_span`=0, `row2`=NULL,`col_span2`=0,`row3`='укупан број људи',`col_span3`=1 WHERE `column_id_internal`=500315;
UPDATE `table_column` SET  `row2`='Екипа хитне медицинске помоћи',`col_span2`=2,`row3`='број екипа',`col_span3`=1 WHERE `column_id_internal`=500316;
UPDATE `table_column` SET `col_span`=0, `row2`=NULL,`col_span2`=0,`row3`='укупан број људи',`col_span3`=1 WHERE `column_id_internal`=500317;
UPDATE `table_column` SET  `row2`='Екипа за контролу квалитета и исправности воде',`col_span2`=2,`row3`='број екипа',`col_span3`=1 WHERE `column_id_internal`=500318;
UPDATE `table_column` SET `col_span`=0, `row2`=NULL,`col_span2`=0,`row3`='укупан број људи',`col_span3`=1 WHERE `column_id_internal`=500319;
UPDATE `table_column` SET  `row2`='Екипа за контролу квалитета и исправности хране',`col_span2`=2,`row3`='број екипа',`col_span3`=1 WHERE `column_id_internal`=500320;
UPDATE `table_column` SET `col_span`=0, `row2`=NULL,`col_span2`=0,`row3`='укупан број људи',`col_span3`=1 WHERE `column_id_internal`=500321;
UPDATE `table_column` SET  `row2`='Екипа за имунизацију људства против заразних болести',`col_span2`=2,`row3`='број екипа',`col_span3`=1 WHERE `column_id_internal`=500322;
UPDATE `table_column` SET `col_span`=0, `row2`=NULL,`col_span2`=0,`row3`='укупан број људи',`col_span3`=1 WHERE `column_id_internal`=500323;
UPDATE `table_column` SET  `row2`='Екипа за санитарни надзор',`col_span2`=2,`row3`='број екипа',`col_span3`=1 WHERE `column_id_internal`=500324;
UPDATE `table_column` SET `col_span`=0, `row2`=NULL,`col_span2`=0,`row3`='укупан број људи',`col_span3`=1 WHERE `column_id_internal`=500325;



INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `title`, `type`, `is_dynamic`, `col_span`, `row2`, `col_span2`) VALUES

('500326', '5003', '26', NULL, 'NUMBER', '0', '0','Хемијска лабораторија- број лабораторијских анализа/дневно', '1'),
('500327', '5003', '27', NULL, 'NUMBER', '0', '0','Биохемијска лабораторија- број лабораторијских анализа/дневно', '1'),
('500328', '5003', '28', NULL, 'NUMBER', '0', '0','Медицинско-биохемијска лабораторија - број лабораторијских анализа/дневно', '1'),
('500329', '5003', '29', NULL, 'NUMBER', '0', '0','Микробиолошка лабораторија- број лабораторијских анализа/дневно', '1'),
('500330', '5003', '30', NULL, 'NUMBER', '0', '0','Ветеринарска лобораторија- број лабораторијских анализа/дневно', '1'),
('500331', '5003', '31', NULL, 'NUMBER', '0', '0','Болнички смештај- број планираних болничких кревета', '1');
