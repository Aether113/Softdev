CREATE TABLE `Person` (
  `PersonID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(100) NULL,
  `Firstname` VARCHAR(100) NULL,
  `Birthdate` DATE NULL,
  PRIMARY KEY (`PersonID`));
  
INSERT INTO `Person` (`PersonID`,`Name`,`Firstname`,`Birthdate`) VALUES (1, 'Schumacher', 'Michael', '1969-01-03');
INSERT INTO `Person` (`PersonID`,`Name`,`Firstname`,`Birthdate`) VALUES (2, 'Bryan', 'Astrid', '1983-01-25');
INSERT INTO `Person` (`PersonID`,`Name`,`Firstname`,`Birthdate`) VALUES (3, 'Yao', 'Ming', '1980-09-12');
INSERT INTO `Person` (`PersonID`,`Name`,`Firstname`,`Birthdate`) VALUES (4, 'Sovannpanha', 'Chhet', '1986-01-09');
INSERT INTO `Person` (`PersonID`,`Name`,`Firstname`,`Birthdate`) VALUES (5, 'Wilmots', 'Marc', '1969-02-22');
INSERT INTO `Person` (`PersonID`,`Name`,`Firstname`,`Birthdate`) VALUES (6, 'Clijsters', 'Kim', '1983-06-08');
INSERT INTO `Person` (`PersonID`,`Name`,`Firstname`,`Birthdate`) VALUES (7, 'Mathilde', 'Queen', '1973-01-20');