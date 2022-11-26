USE korol_iot_14;

DROP TABLE IF EXISTS `personnel`;

CREATE TABLE `personnel` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL,
    `company_card_id` INT NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `personnel` VALUES (50,'Oleh',1),(51,'Mykola',2),(52,'Petro',3),(53,'Stanislav',4);
-- ---------------------------------------------------------------------------------------------------------------------
-- Додати до БД 1 додаткову довільну таблицю і зв’язати з іншою існуючою таблицею зв’язком  1:M.
-- Однак для забезпечення цілісності значень використати тригери замість фізичного зовнішнього ключа.

DROP TRIGGER IF EXISTS CheckIfCompanyExistsBeforeCreate;
DROP TRIGGER IF EXISTS CheckIfCompanyExistsBeforeUpdate;
DROP TRIGGER IF EXISTS DeleteAllRecordsInCountriesTableAfterCompanyWasDeleted;

DELIMITER //
CREATE TRIGGER CheckIfCompanyExistsBeforeCreate
    BEFORE INSERT
    ON personnel
    FOR EACH ROW
BEGIN
    DECLARE company_card_id INT;
    DECLARE message TEXT;
    SELECT id INTO company_card_id FROM company_card WHERE id = new.company_card_id;
    SET message = concat("Cannot add a record: the company id `", new.company_card_id, "` is not found");
    IF company_card_id IS NULL THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = message;
END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER CheckIfCompanyExistsBeforeUpdate
    BEFORE UPDATE
    ON personnel
    FOR EACH ROW
BEGIN
    DECLARE company_card_id INT;
    DECLARE message TEXT;
    SELECT id INTO company_card_id FROM company_card WHERE id = new.company_card_id;
    SET message = concat("Cannot update a record: the company id `", new.company_card_id, "` is not found");
    IF company_card_id IS NULL THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = message;
END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER DeleteAllRecordsInCountriesTableAfterCompanyWasDeleted
    BEFORE DELETE
    ON company_card
    FOR EACH ROW
BEGIN
    DELETE FROM `personnel` WHERE (`company_card_id` = old.id);
END //
DELIMITER ;
