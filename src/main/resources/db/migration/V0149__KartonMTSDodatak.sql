

CREATE TABLE `vlasnikMTS` (
`id` int NOT NULL AUTO_INCREMENT,

`client_id` int DEFAULT NULL,
`created_by` int DEFAULT NULL,
`created_on` datetime DEFAULT NULL,
`modified_by` int DEFAULT NULL,
`modified_on` datetime DEFAULT NULL,

`vlasnik_naziv` varchar(255) DEFAULT NULL,
`adresa` varchar(255) DEFAULT NULL,
`gradOpstina` varchar(255) DEFAULT NULL,
`upravni_okrug` varchar(255) DEFAULT NULL,
`telefon` varchar(255) DEFAULT NULL,
`email` varchar(255) DEFAULT NULL,
`odgovorno_lice` varchar(255) DEFAULT NULL,
`fiksni_tel_odg_lica` varchar(255) DEFAULT NULL,
`mobilni_tel_odg_lica` varchar(255) DEFAULT NULL,
`email_odg_lica` varchar(255) DEFAULT NULL,

PRIMARY KEY (`id`),
FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB;


CREATE TABLE `orgJedinicaMTS` (
`id` int NOT NULL AUTO_INCREMENT,

`client_id` int DEFAULT NULL,
`created_by` int DEFAULT NULL,
`created_on` datetime DEFAULT NULL,
`modified_by` int DEFAULT NULL,
`modified_on` datetime DEFAULT NULL,

`vlasnik_id` int DEFAULT NULL,

`naziv_org_jedinice` varchar(255) DEFAULT NULL,
`odgovorno_lice` varchar(255) DEFAULT NULL,
`fiksni_tel_odg_lica` varchar(255) DEFAULT NULL,
`mobilni_tel_odg_lica` varchar(255) DEFAULT NULL,
`email_odg_lica` varchar(255) DEFAULT NULL,
`mts_adresa` varchar(255) DEFAULT NULL,
`geografska_sirina_mts` varchar(255) DEFAULT NULL,
`geografska_duzina_mts` varchar(255) DEFAULT NULL,
`nadlezna_org_jednica_svs` varchar(255) DEFAULT NULL,

PRIMARY KEY (`id`),
FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
FOREIGN KEY (`vlasnik_id`) REFERENCES `vlasnikMTS` (`id`)

) ENGINE=InnoDB;



CREATE TABLE `detaljiMTS` (
`id` int NOT NULL AUTO_INCREMENT,

`client_id` int DEFAULT NULL,
`created_by` int DEFAULT NULL,
`created_on` datetime DEFAULT NULL,
`modified_by` int DEFAULT NULL,
`modified_on` datetime DEFAULT NULL,

`vlasnik_id` int DEFAULT NULL,
`grupa_id` int DEFAULT NULL,
`vrsta_id` int DEFAULT NULL,
`podvrsta_id` int DEFAULT NULL,
`orgJedinicaMTS_id` int DEFAULT NULL,

`marka` varchar(255) DEFAULT NULL,
`brojMTS_kolicina` varchar(255) DEFAULT NULL,
`tip` varchar(255) DEFAULT NULL,
`registracija` varchar(255) DEFAULT NULL,
`godina_proizvodnje` varchar(255) DEFAULT NULL,
`snaga` varchar(255) DEFAULT NULL,
`nosivost` varchar(255) DEFAULT NULL,
`kapacitet` varchar(255) DEFAULT NULL,
`pogonsko_gorivo` varchar(255) DEFAULT NULL,
`opisMTS` varchar(255) DEFAULT NULL,
`napomena` varchar(255) DEFAULT NULL,

PRIMARY KEY (`id`),
FOREIGN KEY (`vlasnik_id`) REFERENCES `vlasnikMTS` (`id`),
FOREIGN KEY (`grupa_id`) REFERENCES `grupaMTS` (`id`),
FOREIGN KEY (`vrsta_id`) REFERENCES `vrstaMTS` (`id`),
FOREIGN KEY (`podvrsta_id`) REFERENCES `pod_vrstaMTS` (`id`),
FOREIGN KEY (`orgJedinicaMTS_id`) REFERENCES `orgJedinicaMTS` (`id`),
FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)

) ENGINE=InnoDB;
