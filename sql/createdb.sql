-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema testdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema testdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `testdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `testdb` ;

-- -----------------------------------------------------
-- Table `testdb`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testdb`.`roles` (
                                                `id` INT NOT NULL,
                                                `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testdb`.`accounts` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `username` VARCHAR(100) NOT NULL,
    `email` VARCHAR(255) NULL DEFAULT NULL,
    `password` VARCHAR(32) NOT NULL,
    `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `role_id` INT NOT NULL,
    `active` TINYINT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_account_role1_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `fk_account_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `testdb`.`roles` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`subjects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testdb`.`subjects` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`tests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testdb`.`tests` (
                                                `id` INT NOT NULL AUTO_INCREMENT,
                                                `name` VARCHAR(45) NOT NULL,
    `complexity` VARCHAR(45) NOT NULL,
    `desision_time` TIME NULL DEFAULT NULL,
    `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NULL DEFAULT NULL,
    `subject_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
    INDEX `fk_test_subject1_idx` (`subject_id` ASC) VISIBLE,
    CONSTRAINT `fk_test_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `testdb`.`subjects` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`accountshastests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testdb`.`accountshastests` (
                                                           `account_id` INT NOT NULL,
                                                           `test_id` INT NOT NULL,
                                                           `result` FLOAT NULL DEFAULT NULL,
                                                           PRIMARY KEY (`account_id`, `test_id`),
    INDEX `fk_account_has_test_test1_idx` (`test_id` ASC) VISIBLE,
    INDEX `fk_account_has_test_account1_idx` (`account_id` ASC) VISIBLE,
    CONSTRAINT `fk_account_has_test_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `testdb`.`accounts` (`id`),
    CONSTRAINT `fk_account_has_test_test1`
    FOREIGN KEY (`test_id`)
    REFERENCES `testdb`.`tests` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testdb`.`questions` (
                                                    `id` INT NOT NULL AUTO_INCREMENT,
                                                    `text` TEXT NOT NULL,
                                                    `test_id` INT NOT NULL,
                                                    PRIMARY KEY (`id`),
    INDEX `fk_question_test1_idx` (`test_id` ASC) VISIBLE,
    CONSTRAINT `fk_question_test1`
    FOREIGN KEY (`test_id`)
    REFERENCES `testdb`.`tests` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testdb`.`answers` (
                                                  `id` INT NOT NULL AUTO_INCREMENT,
                                                  `text` TEXT NULL DEFAULT NULL,
                                                  `correct` TINYINT NULL DEFAULT NULL,
                                                  `question_id` INT NOT NULL,
                                                  PRIMARY KEY (`id`),
    INDEX `fk_answer_question1_idx` (`question_id` ASC) VISIBLE,
    CONSTRAINT `fk_answer_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `testdb`.`questions` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
