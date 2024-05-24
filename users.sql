DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE users (
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  enabled tinyint NOT NULL,
  PRIMARY KEY (username)
);

INSERT INTO `users` 
VALUES 
('makarand','{noop}test123',1),
('mahesh','{noop}test123',1),
('gaurav','{noop}test123',1);

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_uk_1` (`username`,`authority`),
  CONSTRAINT `authorities_fk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) 

INSERT INTO `authorities` VALUES 
('makarand','ROLE_EMPLOYEE'),
('mahesh','ROLE_EMPLOYEE'),
('mahesh','ROLE_MANAGER'),
('gaurav','ROLE_EMPLOYEE'),
('gaurav','ROLE_MANAGER'),
('gaurav','ROLE_ADMIN');