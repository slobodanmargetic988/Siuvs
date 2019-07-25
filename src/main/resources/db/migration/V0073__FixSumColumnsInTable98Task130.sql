UPDATE `table_column` SET `type` = 'SUM' WHERE `column_id_internal` IN ('664', '665');

UPDATE `dynamic_data` SET `value` = '' WHERE `column_id` =
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '664');
UPDATE `dynamic_data` SET `value` = '' WHERE `column_id` =
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '665');

UPDATE `table_column` SET `col_span2` = '2' WHERE `column_id_internal` = '666';

INSERT INTO `table_column`
(`column_id_internal`, `table_definition_id`, `order`, `type`, `is_dynamic`, `title`, `col_span`, `row2`, `col_span2`, `row3`, `col_span3`, `row4`) VALUES
('667', '98', '12', 'NUMBER', '0', NULL, '0', NULL, '0', NULL, '0', '%');

DELETE FROM `table_column_sum`
WHERE `column_id` = (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '666' LIMIT 1)
AND `sum_column_id` = (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '665' LIMIT 1)
LIMIT 1;

INSERT INTO `table_column_sum` (`column_id`, `sum_column_id`) VALUES
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '664' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '660' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '664' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '662' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '665' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '661' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '665' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '663' LIMIT 1)
);