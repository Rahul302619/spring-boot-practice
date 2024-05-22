CREATE TABLE IF NOT EXISTS `clause` (

    `id`      int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `clause_type`    varchar(100),
    `data`    varchar(100)

);

CREATE TABLE IF NOT EXISTS `term` (

    `id`      int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `term_type`    varchar(100),
    `data`    varchar(100)

);