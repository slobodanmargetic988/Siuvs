# SIUVS #

Sistem za integrisano upravljanje vanrednim situacijama

[TOC]

## Kako da pokrenem program? ##

### Summary of set up ###

### Zavisnosti ###

* MySQL Connector/J: https://dev.mysql.com/downloads/connector/j/
    ```
    #!bash
    sudo ln -s /path/to/mysql-connector-java-<version>/mysql-connector-java-<version>-bin.jar /usr/share/java/mysql-connector-java.jar
    ```

### Opsta podesavanja ###

Na kraj ~/.bashrc fajla dodati
```
#!bash
export CLASSPATH=.:<project_root_not_git_root>:/usr/share/java/mysql-connector-java.jar
```

### Podesavanje DB servera ###

U fajl /etc/mysql/mysql.conf.d/mysqld.cnf dopisati:

```
#!bash
sql_mode = STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
```

### Podesavanje baze ###

Pre build-ovanja projekta neophodno je kreirati bazu koju ce program koristiti:


```
#!SQL

CREATE SCHEMA `siuvs-test` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
```
Obratiti posebnu paznju da je neophodno navesti **utf8_unicode_ci** kao collation, kako bi se cirilica ispravno sortirala.

### Build-ovanje projekta ###
```
#!bash
./mvnw clean package
```

### Pokretanje projekta ###

Projekat se pokrece iz konzole, iz direktorijuma projekta, komandom:

```
#!bash
java -jar target/siuvs-0.1.0-SNAPSHOT.jar
```

Prilikom prvog pokretanja projekta, Hibernate ce sam kreirati potrebne tabele u bazi, ako one prethodno nisu postojale. Kako su te tabele inicijalno prazne, a za formu za kreiranje korisnika je neophodno ulogovati se, potrebno je rucno kreirati prvog korisnika.

Prvo treba dodati admin rolu:

```
#!SQL

INSERT INTO `role` (`role`) VALUES ('ADMIN', 'CLIENT', 'MUP');
```
Potom kreirati korisnika:


```
#!SQL

INSERT INTO `user` (`active`, `email`, `last_name`, `name`, `password`) VALUES ('1', '<neki_email>', '<neko_prezime>', '<neko_ime>', '<bcrypt_hash_sifre>');
```
I na kraju je potrebno novokreiranom korisniku dodeliti admin rolu:


```
#!SQL

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '1');
```


### Pokretanje testova ###

### Uputstva za deploy ###

## Uputstva za rad ##

### Koding standard ###

### Pisanje testova ###

### Code review ###

## Podešavanje servera ##


