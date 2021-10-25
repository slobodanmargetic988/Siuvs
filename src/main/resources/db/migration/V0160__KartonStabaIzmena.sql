Drop table `karton_clanova_staba`;


CREATE TABLE `stab_za_vanredne_situacije` (
`id` int NOT NULL AUTO_INCREMENT,

`client_id` int DEFAULT NULL,

`created_by` int DEFAULT NULL,
`created_on` datetime DEFAULT NULL,
`modified_by` int DEFAULT NULL,
`modified_on` datetime DEFAULT NULL,

`naziv` varchar(255) DEFAULT NULL,
`adresa_staba` varchar(255) DEFAULT NULL,
`fiksni_telefon` varchar(255) DEFAULT NULL,
`mobilni_telefon` varchar(255) DEFAULT NULL,
`email` varchar(255) DEFAULT NULL,

PRIMARY KEY (`id`),
FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB;

CREATE TABLE `strucno_operativni_tim` (
`id` int NOT NULL AUTO_INCREMENT,

`client_id` int DEFAULT NULL,

`created_by` int DEFAULT NULL,
`created_on` datetime DEFAULT NULL,
`modified_by` int DEFAULT NULL,
`modified_on` datetime DEFAULT NULL,

`naziv` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB;


CREATE TABLE `osoba` (
`id` int NOT NULL AUTO_INCREMENT,

`created_by` int DEFAULT NULL,
`created_on` datetime DEFAULT NULL,
`modified_by` int DEFAULT NULL,
`modified_on` datetime DEFAULT NULL,

`puno_ime` varchar(255) DEFAULT NULL,
`naziv_radnog_mesta` varchar(255) DEFAULT NULL,
`adresa_radnog_mesta` varchar(255) DEFAULT NULL,
`funkcija_radno_mesto` varchar(255) DEFAULT NULL,
`telefon_sluzbeni_fiksni` varchar(255) DEFAULT NULL,
`telefon_sluzbeni_mobilni` varchar(255) DEFAULT NULL,
`email_sluzbeni` varchar(255) DEFAULT NULL,
`email_licni` varchar(255) DEFAULT NULL,
`adresa_stanovanja` varchar(255) DEFAULT NULL,
`telefon_u_stanu` varchar(255) DEFAULT NULL,
`ucesce_u_obukama` varchar(1255) DEFAULT NULL,

PRIMARY KEY (`id`),
FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`)

) ENGINE=InnoDB;


CREATE TABLE `osoba_stab` (
`id` int NOT NULL AUTO_INCREMENT,

`created_by` int DEFAULT NULL,
`created_on` datetime DEFAULT NULL,
`modified_by` int DEFAULT NULL,
`modified_on` datetime DEFAULT NULL,


`funkcija_u_stabu` varchar(1255) DEFAULT NULL,
`osoba_id` int,
`stab_id` int,

PRIMARY KEY (`id`),
FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`osoba_id`) REFERENCES `osoba` (`id`),
FOREIGN KEY (`stab_id`) REFERENCES `stab_za_vanredne_situacije` (`id`)

) ENGINE=InnoDB;

CREATE TABLE `osoba_sot` (
`id` int NOT NULL AUTO_INCREMENT,
`created_by` int DEFAULT NULL,
`created_on` datetime DEFAULT NULL,
`modified_by` int DEFAULT NULL,
`modified_on` datetime DEFAULT NULL,


`funkcija_u_sot` varchar(1255) DEFAULT NULL,
`osoba_id` int,
`sot_id` int,
PRIMARY KEY (`id`),
FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`osoba_id`) REFERENCES `osoba` (`id`),
FOREIGN KEY (`sot_id`) REFERENCES `strucno_operativni_tim` (`id`)
) ENGINE=InnoDB;