CREATE TABLE `karton_clanova_staba` (
`id` int NOT NULL AUTO_INCREMENT,

`client_id` int DEFAULT NULL,

`created_by` int DEFAULT NULL,
`created_on` datetime DEFAULT NULL,
`modified_by` int DEFAULT NULL,
`modified_on` datetime DEFAULT NULL,

`adresa_staba` varchar(255) DEFAULT NULL,
`fiksni_telefon_staba` varchar(255) DEFAULT NULL,
`mobilni_telefon_staba` varchar(255) DEFAULT NULL,
`email_staba` varchar(255) DEFAULT NULL,
`naziv_operativnog_staba` varchar(255) DEFAULT NULL,
`naziv_strucno_operativnog_tima` varchar(255) DEFAULT NULL,
`funkcija_u_stabu` varchar(255) DEFAULT NULL,
`puno_ime` varchar(255) DEFAULT NULL,
`naziv_organa_gde_radi` varchar(255) DEFAULT NULL,
`adresa_organa_gde_radi` varchar(255) DEFAULT NULL,
`funkcija_na_radu` varchar(255) DEFAULT NULL,
`fiksni_telefon_sluzbeni` varchar(255) DEFAULT NULL,
`mobilni_telefon_sluzbeni` varchar(255) DEFAULT NULL,
`email_sluzbeni` varchar(255) DEFAULT NULL,
`adresa_stanovanja` varchar(255) DEFAULT NULL,
`telefon_u_stanu` varchar(255) DEFAULT NULL,
`ucesce_na_obukama` varchar(255) DEFAULT NULL,

PRIMARY KEY (`id`),
FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`modified_by`) REFERENCES `user` (`user_id`),
FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB;