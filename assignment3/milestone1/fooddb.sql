-- MySQL Script generated by MySQL Workbench
-- Thu Aug  9 18:32:13 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fooddb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fooddb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fooddb` DEFAULT CHARACTER SET utf8 ;
USE `fooddb` ;

-- -----------------------------------------------------
-- Table `fooddb`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fooddb`.`item` ;

CREATE TABLE IF NOT EXISTS `fooddb`.`item` (
  `iditem` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(500) NULL,
  `price` INT NULL,
  `Restaurant_idRestaurant` INT NOT NULL,
  `quantity` VARCHAR(45) NULL,
  PRIMARY KEY (`iditem`, `Restaurant_idRestaurant`),
  INDEX `fk_item_Restaurant1_idx` (`Restaurant_idRestaurant` ASC) ,
  CONSTRAINT `fk_item_Restaurant1`
    FOREIGN KEY (`Restaurant_idRestaurant`)
    REFERENCES `fooddb`.`Restaurant` (`idRestaurant`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fooddb`.`Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fooddb`.`Location` ;

CREATE TABLE IF NOT EXISTS `fooddb`.`Location` (
  `idLocation` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(250) NOT NULL,
  `latitude` VARCHAR(50) NULL,
  `longitude` VARCHAR(50) NULL,
  `User_idUser` INT NOT NULL,
  `User_Location_idLocation` INT NOT NULL,
  PRIMARY KEY (`idLocation`, `User_idUser`, `User_Location_idLocation`),
  INDEX `fk_Location_User1_idx` (`User_idUser` ASC, `User_Location_idLocation` ASC) ,
  CONSTRAINT `fk_Location_User1`
    FOREIGN KEY (`User_idUser` , `User_Location_idLocation`)
    REFERENCES `fooddb`.`User` (`idUser` , `Location_idLocation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fooddb`.`Login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fooddb`.`Login` ;

CREATE TABLE IF NOT EXISTS `fooddb`.`Login` (
  `idLogin` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password_hash` VARCHAR(512) NULL,
  `password_salt` VARCHAR(45) NULL,
  `password_algo` VARCHAR(45) NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`idLogin`, `User_idUser`, `username`),
  INDEX `fk_Login_User1_idx` (`User_idUser` ASC) ,
  CONSTRAINT `fk_Login_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `fooddb`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fooddb`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fooddb`.`order` ;

CREATE TABLE IF NOT EXISTS `fooddb`.`order` (
  `idorder` INT NOT NULL AUTO_INCREMENT,
  `payment_status` INT NOT NULL,
  `delivery_status` INT NOT NULL,
  `price` INT NOT NULL,
  `Restaurant_idRestaurant` INT NOT NULL,
  `Location_idLocation` INT NOT NULL,
  `rating` INT NULL,
  `feedback` VARCHAR(250) NULL,
  `timestamp` DATETIME NULL,
  PRIMARY KEY (`idorder`, `Restaurant_idRestaurant`, `Location_idLocation`),
  INDEX `fk_order_Restaurant1_idx` (`Restaurant_idRestaurant` ASC) ,
  INDEX `fk_order_Location1_idx` (`Location_idLocation` ASC) ,
  CONSTRAINT `fk_order_Restaurant1`
    FOREIGN KEY (`Restaurant_idRestaurant`)
    REFERENCES `fooddb`.`Restaurant` (`idRestaurant`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_Location1`
    FOREIGN KEY (`Location_idLocation`)
    REFERENCES `fooddb`.`Location` (`idLocation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fooddb`.`order_has_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fooddb`.`order_has_item` ;

CREATE TABLE IF NOT EXISTS `fooddb`.`order_has_item` (
  `order_idorder` INT NOT NULL AUTO_INCREMENT,
  `item_iditem` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`order_idorder`, `item_iditem`),
  INDEX `fk_order_has_item_item1_idx` (`item_iditem` ASC) ,
  INDEX `fk_order_has_item_order1_idx` (`order_idorder` ASC) ,
  CONSTRAINT `fk_order_has_item_order1`
    FOREIGN KEY (`order_idorder`)
    REFERENCES `fooddb`.`order` (`idorder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_item_item1`
    FOREIGN KEY (`item_iditem`)
    REFERENCES `fooddb`.`item` (`iditem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fooddb`.`Restaurant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fooddb`.`Restaurant` ;

CREATE TABLE IF NOT EXISTS `fooddb`.`Restaurant` (
  `idRestaurant` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(90) NOT NULL,
  `Address` VARCHAR(250) NULL,
  `location_lat` VARCHAR(45) NULL,
  `location_long` VARCHAR(45) NULL,
  PRIMARY KEY (`idRestaurant`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fooddb`.`review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fooddb`.`review` ;

CREATE TABLE IF NOT EXISTS `fooddb`.`review` (
  `idreview` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `feedback` VARCHAR(250) NULL,
  `Restaurant_idRestaurant` INT NOT NULL,
  `User_idUser` INT NOT NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`idreview`, `Restaurant_idRestaurant`, `User_idUser`),
  INDEX `fk_review_Restaurant1_idx` (`Restaurant_idRestaurant` ASC) ,
  INDEX `fk_review_User1_idx` (`User_idUser` ASC) ,
  CONSTRAINT `fk_review_Restaurant1`
    FOREIGN KEY (`Restaurant_idRestaurant`)
    REFERENCES `fooddb`.`Restaurant` (`idRestaurant`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `fooddb`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fooddb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fooddb`.`User` ;

CREATE TABLE IF NOT EXISTS `fooddb`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Contactno` VARCHAR(15) NULL,
  `Location_idLocation` INT NOT NULL,
  `isActive` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`idUser`, `Location_idLocation`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fooddb`.`User_upvotes_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fooddb`.`User_upvotes_review` ;

CREATE TABLE IF NOT EXISTS `fooddb`.`User_upvotes_review` (
  `review_idreview` INT NOT NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`review_idreview`, `User_idUser`),
  INDEX `fk_review_has_User_User1_idx` (`User_idUser` ASC) ,
  INDEX `fk_review_has_User_review1_idx` (`review_idreview` ASC) ,
  CONSTRAINT `fk_review_has_User_review1`
    FOREIGN KEY (`review_idreview`)
    REFERENCES `fooddb`.`review` (`idreview`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_has_User_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `fooddb`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
