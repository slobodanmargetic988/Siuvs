UPDATE `page` SET `title` = '1. Процена ризика', `order` = '1' WHERE (`page_id` = '70');
UPDATE `page` SET `title` = '2. План заштите и спасавања', `order` = '2' WHERE (`page_id` = '69');
UPDATE `page` SET `order` = '3' WHERE (`page_id` = '71');

UPDATE `page` SET `order` = '10' WHERE (`page_id` = '23');
UPDATE `page` SET `order` = '9' WHERE (`page_id` = '75');
UPDATE `page` SET `order` = '8' WHERE (`page_id` = '74');
UPDATE `page` SET `order` = '7' WHERE (`page_id` = '73');
UPDATE `page` SET `order` = '6' WHERE (`page_id` = '72');
INSERT INTO `page` (`page_id`, `title`, `type`, `parent_id`, `order`) VALUES ('76', 'План заштите и спасавања по врстама опасности', 'MENU', '69', '5');

UPDATE `page` SET `order` = '14' WHERE (`page_id` = '13');
UPDATE `page` SET `order` = '13' WHERE (`page_id` = '12');
INSERT INTO `page` (`page_id`, `title`, `type`, `parent_id`, `order`) VALUES ('79', 'Болести биљака', 'MENU', '70', '12');

UPDATE `page` SET `parent_id` = '1' WHERE (`page_id` = '16');
UPDATE `page` SET `parent_id` = '1' WHERE (`page_id` = '17');
DELETE FROM `page` WHERE (`page_id` = '14');
UPDATE `page` SET `title` = 'Земљотреси', `parent_id` = '76', `order` = '1' WHERE (`page_id` = '21');

UPDATE `page` SET `parent_id` = '2' WHERE (`page_id` = '32');
UPDATE `page` SET `parent_id` = '2' WHERE (`page_id` = '33');
DELETE FROM `page` WHERE (`page_id` = '29');
UPDATE `page` SET `title` = 'Одрони, клизишта и ерозије', `parent_id` = '76' WHERE (`page_id` = '30');

UPDATE `page` SET `parent_id` = '3' WHERE (`page_id` = '37');
UPDATE `page` SET `parent_id` = '3' WHERE (`page_id` = '38');
DELETE FROM `page` WHERE (`page_id` = '34');
UPDATE `page` SET `title` = 'Поплаве', `parent_id` = '76', `order` = '3' WHERE (`page_id` = '35');

UPDATE `page` SET `parent_id` = '4' WHERE (`page_id` = '42');
UPDATE `page` SET `parent_id` = '4' WHERE (`page_id` = '43');
DELETE FROM `page` WHERE (`page_id` = '39');
UPDATE `page` SET `title` = 'Град', `parent_id` = '76', `order` = '4' WHERE (`page_id` = '40');

UPDATE `page` SET `parent_id` = '5' WHERE (`page_id` = '47');
UPDATE `page` SET `parent_id` = '5' WHERE (`page_id` = '48');
DELETE FROM `page` WHERE (`page_id` = '44');
UPDATE `page` SET `title` = 'Олујни ветар', `parent_id` = '76', `order` = '5' WHERE (`page_id` = '45');

INSERT INTO `page` (`page_id`, `title`, `type`, `parent_id`, `order`) VALUES ('77', 'Топли талас', 'TABLE', '76', '6');

UPDATE `page` SET `parent_id` = '7' WHERE (`page_id` = '52');
UPDATE `page` SET `parent_id` = '7' WHERE (`page_id` = '53');
DELETE FROM `page` WHERE (`page_id` = '49');
UPDATE `page` SET `title` = 'Суша', `parent_id` = '76', `order` = '7' WHERE (`page_id` = '50');

UPDATE `page` SET `parent_id` = '8' WHERE (`page_id` = '57');
UPDATE `page` SET `parent_id` = '8' WHERE (`page_id` = '58');
DELETE FROM `page` WHERE (`page_id` = '54');
UPDATE `page` SET `title` = 'Снежне мећаве, наноси и поледица, хладни талас', `parent_id` = '76', `order` = '8' WHERE (`page_id` = '55');

UPDATE `page` SET `parent_id` = '9' WHERE (`page_id` = '62');
UPDATE `page` SET `parent_id` = '9' WHERE (`page_id` = '63');
DELETE FROM `page` WHERE (`page_id` = '59');
UPDATE `page` SET `title` = 'Недостак воде за пиће', `parent_id` = '76', `order` = '9' WHERE (`page_id` = '60');

UPDATE `page` SET `parent_id` = '10' WHERE (`page_id` = '67');
UPDATE `page` SET `parent_id` = '10' WHERE (`page_id` = '68');
DELETE FROM `page` WHERE (`page_id` = '64');
UPDATE `page` SET `title` = 'Епидемије и пандемије', `parent_id` = '76', `order` = '10' WHERE (`page_id` = '65');

INSERT INTO `page` (`page_id`, `title`, `type`, `parent_id`, `order`) VALUES ('78', 'Болести животиња', 'TABLE', '76', '11');

INSERT INTO `page` (`page_id`, `title`, `type`, `parent_id`, `order`) VALUES ('80', 'Болести биљака', 'TABLE', '76', '12');

UPDATE `page` SET `title` = 'Пожари, експлозије и пожари на отвореном' WHERE (`page_id` = '12');

INSERT INTO `page` (`page_id`, `title`, `type`, `parent_id`, `order`) VALUES ('81', 'Пожари, експлозије и пожари на отвореном', 'TABLE', '76', '13');

UPDATE `page` SET `title` = 'Техничко-технолошке несреће' WHERE (`page_id` = '13');

INSERT INTO `page` (`page_id`, `title`, `type`, `parent_id`, `order`) VALUES ('82', 'Техничко-технолошке несреће', 'TABLE', '76', '14');











