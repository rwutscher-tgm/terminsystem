CREATE USER 'testwu' IDENTIFIED BY 'rootpw';

CREATE USER 'testwu'@'%' IDENTIFIED BY 'rootpw';

GRANT ALL ON *.* to 'testwu';

GRANT ALL PRIVILEGES ON *.* TO 'testwu'@'*' IDENTIFIED BY 'rootpw';

DROP DATABASE IF EXISTS terminsystem;
CREATE DATABASE terminsystem;

USE terminsystem;

CREATE TABLE user(
  participantID VARCHAR(255),
  email VARCHAR(255),
  PRIMARY KEY(participantID)
)ENGINE=InnoDB;


CREATE  TABLE testy(
  id INT,
  test VARCHAR(255),
  PRIMARY KEY (id)
);

