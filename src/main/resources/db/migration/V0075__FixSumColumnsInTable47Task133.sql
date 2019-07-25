UPDATE `table_column` SET `type` = 'SUM' WHERE `column_id_internal` IN ('316', '317');

UPDATE `dynamic_data` SET `value` = '' WHERE `column_id` =
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '316');
UPDATE `dynamic_data` SET `value` = '' WHERE `column_id` =
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '317');

INSERT INTO `table_column_sum` (`column_id`, `sum_column_id`) VALUES
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '316' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '310' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '316' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '312' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '316' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '314' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '317' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '311' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '317' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '313' LIMIT 1)
),
(
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '317' LIMIT 1),
    (SELECT `column_id` FROM `table_column` WHERE `column_id_internal` = '315' LIMIT 1)
);