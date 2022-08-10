-- liquibase formatted sql
-- changeset israel:setup-tables

create table betslip (
 id int(11) NOT NULL AUTO_INCREMENT,
 account_id INT(11) NOT NULL,
 stake INT(11),
 status VARCHAR (50) DEFAULT 'pending',
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=543 DEFAULT CHARSET=utf8;

CREATE TABLE bet(
   id int(11) NOT NULL AUTO_INCREMENT,
   name VARCHAR(50),
   home_odd int(11) NOT NULL,
   away_odd int(11) NOT NULL,
   draw_odd int(11) NOT NULL,
   status VARCHAR(50) DEFAULT 'pending',
   outcome VARCHAR(50),

   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=543 DEFAULT CHARSET=utf8;

CREATE TABLE bet_item(
   id int(11) NOT NULL AUTO_INCREMENT,
   bet_id INT(11),
   betslip_id INT(11),

   PRIMARY KEY (`id`),
   FOREIGN KEY(bet_id) REFERENCES bet(id) ON DELETE CASCADE,
   FOREIGN KEY(betslip_id) REFERENCES betslip(id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=543 DEFAULT CHARSET=utf8;