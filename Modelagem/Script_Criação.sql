CREATE SCHEMA cers DEFAULT CHARACTER SET utf8 ;

use cers;

CREATE TABLE notification (
  idnotification INTEGER NOT NULL AUTO_INCREMENT,
  title VARCHAR(100) NULL,
  descriptionn VARCHAR(100) NULL,
  date_publication DATE NULL,
  date_visualization DATE NULL,
  PRIMARY KEY(idnotification)
);


