UPDATE `dynamic_row` t1
JOIN (
	SELECT ordered.`row_id`, @rowno := if(@grp = `table_id`, @rowno + 1, 1) AS `order`, @grp := `table_id`
    FROM (SELECT * FROM dynamic_row ORDER BY `table_id` ASC, `row_id` ASC) ordered
    CROSS JOIN (SELECT @rowno := 0, @grp := null) generator
) t2 ON t1.row_id = t2.row_id
SET t1.`order` = t2.`order`;