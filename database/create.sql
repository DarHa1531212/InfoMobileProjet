CREATE DATABASE `androidproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `tblcategorie` (
  `idCategorie` int NOT NULL AUTO_INCREMENT,
  `nomCategorie` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategorie`),
  UNIQUE KEY `nomCategorie_UNIQUE` (`nomCategorie`),
  UNIQUE KEY `idCategorie_UNIQUE` (`idCategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tblrecette` (
  `idRecette` int NOT NULL AUTO_INCREMENT,
  `nomRecette` varchar(45) NOT NULL,
  `txtRecette` longtext NOT NULL,
  `image` longblob,
  PRIMARY KEY (`idRecette`),
  UNIQUE KEY `nomRecette_UNIQUE` (`nomRecette`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tblrecettecategorie` (
  `idRecetteCat` int NOT NULL AUTO_INCREMENT,
  `idRecette` int NOT NULL,
  `idCategorie` int NOT NULL,
  PRIMARY KEY (`idRecetteCat`),
  UNIQUE KEY `idRecetteCat_UNIQUE` (`idRecetteCat`),
  KEY `idCategorie_idx` (`idCategorie`),
  KEY `idRecette_idx` (`idRecette`),
  CONSTRAINT `idCategorie` FOREIGN KEY (`idCategorie`) REFERENCES `tblcategorie` (`idCategorie`),
  CONSTRAINT `idRecette` FOREIGN KEY (`idRecette`) REFERENCES `tblrecette` (`idRecette`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbluser` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `userPassword` varchar(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `userName_UNIQUE` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbluserpreference` (
  `idUserPreference` int NOT NULL AUTO_INCREMENT,
  `idUser` int NOT NULL,
  `idPreferenceCategorie` int NOT NULL,
  PRIMARY KEY (`idUserPreference`),
  UNIQUE KEY `idUserPreference_UNIQUE` (`idUserPreference`),
  KEY `iduser_idx` (`idUser`),
  KEY `idPreference_idx` (`idPreferenceCategorie`),
  CONSTRAINT `idPreference` FOREIGN KEY (`idPreferenceCategorie`) REFERENCES `tblcategorie` (`idCategorie`),
  CONSTRAINT `iduser` FOREIGN KEY (`idUser`) REFERENCES `tbluser` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


