CREATE SCHEMA IF NOT EXISTS `korol_iot_14` DEFAULT CHARACTER SET utf8 ;
USE `korol_iot_14` ;

    -- -----------------------------------------------------
-- Table `korol_iot_14`.`company_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`company_card` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `company_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `korol_iot_14`.`customer_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`customer_card` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `customer_name` VARCHAR(45) NOT NULL,
    `customer_surname` VARCHAR(45) NOT NULL,
    `city_name` VARCHAR(15) NOT NULL,
    `company_card_id` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_customer_card_company_card1_idx` (`company_card_id` ASC) VISIBLE,
    REFERENCES `korol_iot_14`.`city` (`name`)
    FOREIGN KEY (`company_card_id`)
    REFERENCES `korol_iot_14`.`company_card` (`id`)
    )
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `korol_iot_14`.`parking_ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`parking_ticket` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `parked_car_number_plate` VARCHAR(15) NOT NULL,
    `customer_card_id` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_parking_ticket_customer_card1_idx` (`customer_card_id` ASC) VISIBLE,
    CONSTRAINT `fk_parking_ticket_customer_card1`
    FOREIGN KEY (`customer_card_id`)
    REFERENCES `korol_iot_14`.`customer_card` (`id`))
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `korol_iot_14`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`booking` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `customer_card_id` INT NOT NULL,
   `parked_car_number_plate` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_booking_customer_card1_idx` (`customer_card_id` ASC) VISIBLE,
    CONSTRAINT `fk_booking_customer_card1`
    FOREIGN KEY (`customer_card_id`)
    REFERENCES `korol_iot_14`.`customer_card` (`id`))
    ENGINE = InnoDB;
