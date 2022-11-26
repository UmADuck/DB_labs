USE korol_iot_14;

-- Написати 3 довільні тригери для таблиць поточної БД:

-- Створити таблицю-журнал, в якій вести логи зі штампом часу при модифікації даних для таблиці
DELIMITER //
CREATE TRIGGER AfterInsertSettlementMeasure
    AFTER INSERT
    ON customer_card
    FOR EACH ROW
BEGIN
    DECLARE customer_data VARCHAR(255);
    DECLARE company_data VARCHAR(255);
    SELECT CONCAT(id, ' (', customer_name, ':', customer_surname, ')') INTO customer_data FROM customer_card WHERE id = new.id;
    SELECT company_name INTO company_data FROM company_card WHERE id = new.id;
    INSERT INTO logger (company_data, customer_data, action, time_stamp, user)
    VALUES (customer_data, company_data, 'INSERTED', NOW(), USER());
END //
DELIMITER ;

-- Створити таблицю-журнал, в якій вести логи зі штампом часу при видаленні даних для певної таблиці
DELIMITER //
CREATE TRIGGER BeforeDeleteSettlementMeasure
    BEFORE DELETE
    ON customer_card
    FOR EACH ROW
BEGIN
    DECLARE customer_data VARCHAR(255);
    DECLARE company_data VARCHAR(255);
    SELECT CONCAT(id, ' (', customer_name, ':', customer_surname, ')') INTO customer_data FROM customer_card WHERE id = new.id;
    SELECT company_name INTO company_data FROM company_card WHERE id = new.id;
    INSERT INTO logger (company_data, customer_data, action, time_stamp, user)
    VALUES (customer_data, company_data, 'INSERTED', NOW(), USER());
END //
DELIMITER ;

-- Значення певного стовпця не містити два нулі
DELIMITER //
CREATE TRIGGER ValidateMeasureWaterLevel
    BEFORE INSERT
    ON company_card
    FOR EACH ROW
    IF  (new.company_name RLIKE('*00')) THEN
	SIGNAL SQLSTATE '45000'
	SET MESSAGE_TEXT = 'Company name can not have 2 zero numbers on name';
END IF //;
DELIMITER ;