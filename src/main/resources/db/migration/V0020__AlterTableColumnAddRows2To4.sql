ALTER TABLE `table_column`
ADD COLUMN `col_span` INT NOT NULL DEFAULT 0 AFTER `type`,
ADD COLUMN `row2` VARCHAR(1024) NULL AFTER `col_span`,
ADD COLUMN `col_span2` INT NULL DEFAULT 0 AFTER `row2`,
ADD COLUMN `row3` VARCHAR(1024) NULL AFTER `col_span2`,
ADD COLUMN `col_span3` INT NULL DEFAULT 0 AFTER `row3`,
ADD COLUMN `row4` VARCHAR(1024) NULL AFTER `col_span3`;

