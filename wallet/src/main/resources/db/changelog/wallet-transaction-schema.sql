-- liquibase formatted sql
-- changeset israel:setup-wallet-and-transactions-tables

create table IF NOT EXISTS wallet (
 id int(11) NOT NULL AUTO_INCREMENT,
 account_id INT(11) NOT NULL,
 balance FLOAT(24),

 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=543 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS transaction(
   id int(11) NOT NULL AUTO_INCREMENT,
   reference VARCHAR(50),
   account_id INT(11) NOT NULL,
   amount FLOAT(24) NOT NULL,
   status VARCHAR(50) NOT NULL,
   type VARCHAR(50) NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=543 DEFAULT CHARSET=utf8;
