-- liquibase formatted sql
-- changeset israel:setup-wallet-and-transactions-tables

CREATE TABLE IF NOT EXISTS users(
   id int(11) NOT NULL AUTO_INCREMENT,
   email VARCHAR(50) NOT NULL UNIQUE,
   password TEXT NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=543 DEFAULT CHARSET=utf8;
