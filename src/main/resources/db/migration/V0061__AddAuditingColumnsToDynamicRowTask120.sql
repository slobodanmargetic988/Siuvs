ALTER TABLE `dynamic_row`
ADD COLUMN `created_by` INT(11) NULL AFTER `group_row_id`,
ADD COLUMN `created_on` DATETIME NULL AFTER `created_by`,
ADD COLUMN `modified_by` INT(11) NULL AFTER `created_on`,
ADD COLUMN `modified_on` DATETIME NULL AFTER `modified_by`,
ADD INDEX `fk_created_by_idx` (`created_by` ASC),
ADD INDEX `fk_modified_by_idx` (`modified_by` ASC);