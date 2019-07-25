UPDATE `dynamic_group_row` t1
JOIN (
	SELECT ordered.`group_row_id`, @rowno :=
    if(
    ((@grp1 IS NULL AND `table_definition_id` IS NULL) OR @grp1 = `table_definition_id`)
    AND
    ((@grp2 IS NULL AND `custom_table_definition_id` IS NULL) OR @grp2 = `custom_table_definition_id`)
    AND
    ((@grp3 IS NULL AND `client_id` IS NULL) OR @grp3 = `client_id`)
    , @rowno + 1, 1)
    AS `order`, @grp1 := `table_definition_id`, @grp2 := `custom_table_definition_id`, @grp3 := `client_id`
    FROM (SELECT * FROM dynamic_group_row ORDER BY `client_id` ASC, `table_definition_id` ASC, `custom_table_definition_id` ASC, `group_row_id` ASC) ordered
    CROSS JOIN (SELECT @rowno := 0, @grp1 := null, @grp2 := null, @grp3 := null) generator
) t2 ON t1.group_row_id = t2.group_row_id
SET t1.`order` = t2.`order`;