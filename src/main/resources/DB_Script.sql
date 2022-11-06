CREATE SCHEMA IF NOT EXISTS `korol_iot_14` DEFAULT CHARACTER SET utf8 ;
USE `korol_iot_14` ;
DROP TABLE IF EXISTS parking_spots_info;
DROP TABLE IF EXISTS parking_address;
DROP TABLE IF EXISTS company_additional_info;
DROP TABLE IF EXISTS parking;
DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS parking_ticket;
DROP TABLE IF EXISTS customer_card;
DROP TABLE IF EXISTS company_card;
DROP TABLE IF EXISTS loyalty_card_discounts;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS region;

-- -----------------------------------------------------
-- Table `korol_iot_14`.`parking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`parking` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `number_of_parking_spots` VARCHAR(5) NOT NULL,
    `company_owner_name` VARCHAR(30) NULL,
    `occupied_parking_spots` VARCHAR(5) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `korol_iot_14`.`region`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`region` (
    `name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`name`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `korol_iot_14`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`city` (
    `name` VARCHAR(15) NOT NULL,
    `region_name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`name`),
    INDEX `fk_city_region_idx` (`region_name` ASC) VISIBLE,
    CONSTRAINT `fk_city_region`
    FOREIGN KEY (`region_name`)
    REFERENCES `korol_iot_14`.`region` (`name`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `korol_iot_14`.`loyalty_card_discounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`loyalty_card_discounts` (
    `discount_in_percantage` INT NOT NULL,
    PRIMARY KEY (`discount_in_percantage`))
    ENGINE = InnoDB;


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
    `discount_in_percantage` INT NULL,
    `company_card_id` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_customer_card_city1_idx` (`city_name` ASC) VISIBLE,
    INDEX `fk_customer_card_loyalty_card_discounts1_idx` (`discount_in_percantage` ASC) VISIBLE,
    INDEX `fk_customer_card_company_card1_idx` (`company_card_id` ASC) VISIBLE,
    CONSTRAINT `fk_customer_card_city1`
    FOREIGN KEY (`city_name`)
    REFERENCES `korol_iot_14`.`city` (`name`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `fk_customer_card_loyalty_card_discounts1`
    FOREIGN KEY (`discount_in_percantage`)
    REFERENCES `korol_iot_14`.`loyalty_card_discounts` (`discount_in_percantage`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT `fk_customer_card_company_card1`
    FOREIGN KEY (`company_card_id`)
        REFERENCES `korol_iot_14`.`company_card` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `korol_iot_14`.`parking_ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`parking_ticket` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `parked_car_number_plate` VARCHAR(15) NOT NULL,
    `spot_occupation_time` DATETIME NULL,
    `customer_card_id` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_parking_ticket_customer_card1_idx` (`customer_card_id` ASC) VISIBLE,
    CONSTRAINT `fk_parking_ticket_customer_card1`
    FOREIGN KEY (`customer_card_id`)
         REFERENCES `korol_iot_14`.`customer_card` (`id`)
         ON DELETE NO ACTION
         ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `korol_iot_14`.`company_additional_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`company_additional_info` (
     `company_id` INT NOT NULL,
     `street_name` VARCHAR(20) NOT NULL,
     `street_number` VARCHAR(5) NOT NULL,
     `number_of_workers` INT NULL,
     `company_bank_number` VARCHAR(10) NOT NULL,
     `city_name` VARCHAR(15) NOT NULL,
     INDEX `fk_company_additional_info_company_card1_idx` (`company_id` ASC) VISIBLE,
     INDEX `fk_company_additional_info_city1_idx` (`city_name` ASC) VISIBLE,
     PRIMARY KEY (`company_id`),
     CONSTRAINT `fk_company_additional_info_company_card1`
        FOREIGN KEY (`company_id`)
            REFERENCES `korol_iot_14`.`company_card` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
     CONSTRAINT `fk_company_additional_info_city1`
        FOREIGN KEY (`city_name`)
            REFERENCES `korol_iot_14`.`city` (`name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `korol_iot_14`.`parking_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`parking_address` (
                                                                `parking_id` INT NOT NULL,
                                                                `city_name` VARCHAR(15) NOT NULL,
                                                                `street_name` VARCHAR(20) NOT NULL,
                                                                `street_number` VARCHAR(5) NOT NULL,
                                                                PRIMARY KEY (`parking_id`),
                                                                INDEX `fk_parking_address_city1_idx` (`city_name` ASC) VISIBLE,
                                                                CONSTRAINT `fk_parking_address_parking1`
                                                                    FOREIGN KEY (`parking_id`)
                                                                        REFERENCES `korol_iot_14`.`parking` (`id`)
                                                                        ON DELETE NO ACTION
                                                                        ON UPDATE NO ACTION,
                                                                CONSTRAINT `fk_parking_address_city1`
                                                                    FOREIGN KEY (`city_name`)
                                                                        REFERENCES `korol_iot_14`.`city` (`name`)
                                                                        ON DELETE NO ACTION
                                                                        ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `korol_iot_14`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`booking` (
                                                        `id` INT NOT NULL AUTO_INCREMENT,
                                                        `booking_datetime` DATETIME NULL,
                                                        `customer_card_id` INT NOT NULL,
                                                        `parked_car_number_plate` VARCHAR(10) NOT NULL,
                                                        PRIMARY KEY (`id`),
                                                        INDEX `fk_booking_customer_card1_idx` (`customer_card_id` ASC) VISIBLE,
                                                        CONSTRAINT `fk_booking_customer_card1`
                                                            FOREIGN KEY (`customer_card_id`)
                                                                REFERENCES `korol_iot_14`.`customer_card` (`id`)
                                                                ON DELETE NO ACTION
                                                                ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `korol_iot_14`.`parking_spots_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `korol_iot_14`.`parking_spots_info` (
    `parking_spot_number` INT NOT NULL,
    `parking_id` INT NOT NULL,
    `is_booked` TINYINT NULL DEFAULT 0,
    `booking_id` INT NULL,
    `is_free` TINYINT NULL DEFAULT 0,
    `parking_ticket_id` INT NULL,
    `spot_is_free_since` DATETIME NULL,
    PRIMARY KEY (`parking_spot_number`, `parking_id`),
    INDEX `fk_parking_spots_info_parking1_idx` (`parking_id` ASC) VISIBLE,
    INDEX `fk_parking_spots_info_booking1_idx` (`booking_id` ASC) VISIBLE,
    INDEX `fk_parking_spots_info_parking_ticket1_idx` (`parking_ticket_id` ASC) VISIBLE,
    CONSTRAINT `fk_parking_spots_info_parking1`
        FOREIGN KEY (`parking_id`)
            REFERENCES `korol_iot_14`.`parking` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_parking_spots_info_booking1`
        FOREIGN KEY (`booking_id`)
            REFERENCES `korol_iot_14`.`booking` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_parking_spots_info_parking_ticket1`
         FOREIGN KEY (`parking_ticket_id`)
            REFERENCES `korol_iot_14`.`parking_ticket` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION)
    ENGINE = InnoDB;

INSERT INTO region(name) VALUES 
('Lviv'), ('Kyiv'), ('Kharkiv'), ('Dnipro'),
('Rivne');

INSERT INTO city(name, region_name) VALUES 
('Lviv', 'Lviv'), ('Kyiv', 'Kyiv'), ('Kharkiv', 'Kharkiv'), ('Dnipro', 'Dnipro'),
('Rivne', 'Rivne');

INSERT INTO loyalty_card_discounts(discount_in_percantage) VALUES
(5), (10), (15), (20), (25);

INSERT INTO company_card(id, company_name) VALUES
(1, 'Smart toys'), (2, 'Silpo'), (3, 'Brocard'),
(4, 'LC Waikiki'), (5, 'Mc Donalds');

INSERT INTO company_additional_info(company_id, street_name, street_number, number_of_workers,
company_bank_number, city_name) VALUES
(1, 'fhdjsk', 432, 2000, 643728470, 'Lviv'), (2, 'fhdjsk', 42, 20000, 463728432, 'Kyiv'),
(3, 'hdfjksf', 483, 500, 473247904, 'Kharkiv'), (4, 'fdkcjl', 90, 800, 8439084392, 'Dnipro'),
(5, 'huksnk', 83, 50000, 4398084940, 'Rivne');

INSERT INTO customer_card(id, customer_name, customer_surname, city_name, 
discount_in_percantage, company_card_id) VALUES
(1, 'Petro', 'Mohyla', 'Lviv', NULL, NULL), (2, 'Mykola', 'Mohyla', 'Lviv', NULL , 1),
(3, 'Andrii', 'Zalizo', 'Kyiv', 5, NULL), (4, 'Hryhir', 'Veselka', 'Kyiv', 10, NULL),
(5, 'Anton', 'Palii', 'Kharkiv', NULL, 2), (6, 'Andrii', 'Veselyi', 'Kharkiv', NULL, 2),
(7, 'Olena', 'Metaleva', 'Dnipro', NULL, 3), (8, 'Anna', 'Perekonana', 'Dnipro', NULL, 4),
(9, 'Artem', 'Ivaniv', 'Rivne', NULL, 5), (10, 'Oleh', 'Hrechka', 'Rivne', 15, NULL);

INSERT INTO booking(id, booking_datetime, customer_card_id, parked_car_number_plate) VALUES
(1, NULL, 1, '65784'), (2, NULL, 3, 'gfdgd'), (3, NULL, 6, 'fdjjw'), (4, NULL, 8, 'fdcwe'), (5, NULL, 9, 'hjdks'); 

INSERT INTO parking_ticket(id, parked_car_number_plate, spot_occupation_time, customer_card_id) VALUES
(1, 'fdsfs', NULL, NULL), (2, 'hfdwio', NULL, 4), (3, 'gregfds', NULL, 5), (4, 'gresg', NULL, NULL)
, (5, 'gresgfds', NULL, NULL), (6, 'fewffew', NULL, NULL), 
(7, 'fref', NULL, 2), (8, 'grsgs', NULL, 7), (9, 'grswgs', NULL, NULL), (10, 'fdsfs', NULL, NULL);

INSERT INTO  parking( id, number_of_parking_spots, company_owner_name, occupied_parking_spots) VALUES
(1, 15, NULL, NULL), (2, 25, NULL, NULL), (3, 16, NULL, NULL), (4, 30, NULL, NULL), (5, 50, NULL, NULL);

INSERT INTO parking_address(parking_id, city_name, street_name, street_number) VALUES
(1, 'Lviv', 'fdsf', '543'), (2, 'Kyiv', 'gfev', '12'), (3, 'Kharkiv', 'vfdgre', '140'),
(4, 'Dnipro', 'grefre', '53'), (5, 'Rivne', 'gteg', '16');

INSERT INTO parking_spots_info(parking_spot_number, parking_id, is_booked, 
booking_id, is_free, parking_ticket_id, spot_is_free_since) VALUES
(1,1, NULL, NULL, NULL, NULL, NULL), (2,1, NULL, NULL, NULL, NULL, NULL),
 (3,1, NULL, NULL, NULL, NULL, NULL), (4,1, NULL, NULL, NULL, NULL, NULL),
 (5,1, NULL, NULL, NULL, NULL, NULL), (6,1, NULL, NULL, NULL, NULL, NULL), 
 (7,1, NULL, NULL, NULL, NULL, NULL), (8,1, NULL, NULL, NULL, NULL, NULL), 
 (9,1, NULL, NULL, NULL, NULL, NULL), (10,1, NULL, NULL, NULL, NULL, NULL);

CREATE INDEX number_plate_ticket ON parking_ticket(parked_car_number_plate);

CREATE INDEX number_plate_booked ON booking(parked_car_number_plate);

CREATE UNIQUE INDEX city_name ON city(name);











