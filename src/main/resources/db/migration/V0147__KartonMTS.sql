CREATE TABLE `grupaMTS` (
`id` int NOT NULL AUTO_INCREMENT,
`naziv` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `vrstaMTS` (
`id` int NOT NULL AUTO_INCREMENT,
`naziv` varchar(255) DEFAULT NULL,
`grupa_id` int NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`grupa_id`) REFERENCES `grupaMTS` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `pod_vrstaMTS` (
`id` int NOT NULL AUTO_INCREMENT,
`naziv` varchar(255) DEFAULT NULL,
`vrsta_id` int NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`vrsta_id`) REFERENCES `vrstaMTS` (`id`)
) ENGINE=InnoDB;