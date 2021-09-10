


CREATE TABLE `zanimanja` (
`id` int NOT NULL AUTO_INCREMENT,
`naziv` varchar(255) DEFAULT NULL ,
PRIMARY KEY (`id`)
) ENGINE=InnoDB;




CREATE TABLE `delatnost` (
`id` int NOT NULL AUTO_INCREMENT,
`naziv` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `kartonsubjekti` (
`id` int NOT NULL AUTO_INCREMENT,
`maticnibroj` int DEFAULT NULL,
`pib` int DEFAULT NULL,
`punnaziv` varchar(255) DEFAULT NULL,
`skraceninaziv` varchar(255) DEFAULT NULL,
`brojtekucegracuna` varchar(255) DEFAULT NULL,
`posta_ime_pb` varchar(255) DEFAULT NULL,
`adresa` varchar(255) DEFAULT NULL,
`geosirina` varchar(255) DEFAULT NULL,
`geoduzina` varchar(255) DEFAULT NULL,
`tel` varchar(255) DEFAULT NULL,
`fax` varchar(255) DEFAULT NULL,
`email` varchar(255) DEFAULT NULL,
`website` varchar(255) DEFAULT NULL,
`rukovodilac_ime` varchar(255) DEFAULT NULL,
`rukovodilac_tel` varchar(255) DEFAULT NULL,
`rukovodilac_mob` varchar(255) DEFAULT NULL,

`oblik_organizovanja` varchar(255) DEFAULT NULL,
`nivo_odredjivanja` varchar(255) DEFAULT NULL,
`nadleznaSVS` varchar(255) DEFAULT NULL,
`kontakt_ime` varchar(255) DEFAULT NULL,
`kontakt_adresa` varchar(255) DEFAULT NULL,
`kontakt_telposao` varchar(255) DEFAULT NULL,
`kontakt_telstan` varchar(255) DEFAULT NULL,
`kontakt_mob` varchar(255) DEFAULT NULL,


`client_id` int DEFAULT NULL,

`delatnost_id`int DEFAULT NULL,



`created_by` int DEFAULT NULL,
`created_on` datetime DEFAULT NULL,
`modified_by` int DEFAULT NULL,
`modified_on` datetime DEFAULT NULL,

PRIMARY KEY (`id`),
FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
FOREIGN KEY (`delatnost_id`) REFERENCES `delatnost` (`id`)
) ENGINE=InnoDB;


CREATE TABLE `kadrovi` (
`id` int NOT NULL AUTO_INCREMENT,
`zanimanje_id` int DEFAULT NULL,
`kartonsubjekti_id` int default null,
`broj` int,
PRIMARY KEY (`id`),
FOREIGN KEY (`zanimanje_id`) REFERENCES `zanimanja` (`id`),
FOREIGN KEY (`kartonsubjekti_id`) REFERENCES `kartonsubjekti` (`id`)
) ENGINE=InnoDB;