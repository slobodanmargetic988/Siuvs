UPDATE `table_column` t1
JOIN (
	SELECT ordered.`column_id`, @rowno := if(@grp = `table_definition_id`, @rowno + 1, 1) AS `order`, @grp := `table_definition_id`
    FROM (SELECT * FROM table_column ORDER BY `column_id` ASC) ordered
    CROSS JOIN (SELECT @rowno := 0, @grp := null) generator
) t2
ON t1.column_id = t2.column_id
SET t1.`order` = t2.`order`;