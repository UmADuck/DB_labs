-- Створити пакет, який вставляє 10 стрічок у довільну таблицю БД у форматі < Noname+№> ,
-- наприклад: Noname5, Noname6, Noname7 і т.д.
DROP PROCEDURE IF EXISTS CreateBunchOfPersonnel;

DELIMITER //
CREATE PROCEDURE CreateBunchOfPersonnel(OUT amount int)
BEGIN
    DECLARE increment INT;
    SET increment = 1;

    WHILE increment <= 10 DO
        INSERT INTO `personnel` (`name`, `company_card_id`) VALUES (CONCAT('company_', increment), '3');
        SET increment = increment + 1;
END WHILE;
SET amount = 10;
END //
-- ----------------------------------------------------------------------------------------------------------------------

-- Забезпечити параметризовану вставку нових значень у довільну таблицю.
DROP PROCEDURE IF EXISTS CreateNewCompany;

DELIMITER //
CREATE PROCEDURE CreateNewCompany(IN newCompanyName VARCHAR(45), OUT id int)
BEGIN
INSERT INTO company_card(company_name) VALUES(newCompanyName);
SELECT LAST_INSERT_ID() as id;
END //
DELIMITER ;

-- ----------------------------------------------------------------------------------------------------------------------

-- Написати користувацьку функцію, яка буде шукати Max, Min, Sum чи Avg для стовпця довільної таблиці у БД.
-- Написати процедуру, яка буде у SELECT викликати цю функцію.
DROP FUNCTION IF EXISTS GetMaxID;

DELIMITER //
CREATE FUNCTION GetMaxID() RETURNS INT DETERMINISTIC
BEGIN
	DECLARE max_id INT DEFAULT 0;
SELECT MAX(id) as id INTO max_id FROM company_card;
RETURN max_id;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS GetMaxID;

DELIMITER //
CREATE PROCEDURE GetMaxID(OUT maxId int)
BEGIN
SELECT GetMaxID();
END //
DELIMITER ;
-- ----------------------------------------------------------------------------------------------------------------------

-- Написати 1 процедуру із курсором для виконання однієї із наступних задач:
-- Використовуючи курсор, забезпечити динамічне створення баз даних з іменами,
-- взятими зі стовпця з довільної таблиці поточної БД, з випадковою кількістю таблиць для кожної БД (від 1 до 9).
-- Структура таблиць довільна. Імена таблиць відповідають імені БД з порядковим номером від 1 до 9.

DROP PROCEDURE IF EXISTS CreateDatabasesUsingCursors;

DELIMITER //
CREATE PROCEDURE CreateDatabasesUsingCursors()
BEGIN
	declare increment int default 1;
    declare random_number int default 1;

	declare done int default 0;
    declare db_name varchar(255);

    declare customer_surname_cursor cursor
    for SELECT `customer_surname` FROM customer_card;

declare continue handler
    for not found set done = 1;

open customer_surname_cursor;

while done = 0 do
select FLOOR( RAND() * (9-1) + 1) as rand_num INTO random_number;
fetch customer_surname_cursor into db_name;

set @create_db = concat('CREATE DATABASE IF NOT EXISTS ', db_name, ';');
prepare create_db from @create_db;
execute create_db;
deallocate prepare create_db;

while random_number >= increment do
            set @create_table = concat('CREATE TABLE IF NOT EXISTS ', db_name, '.', db_name, increment, ' (`id` INT NOT NULL AUTO_INCREMENT, PRIMARY KEY (`id`));');
prepare create_table from @create_table;
execute create_table;
set increment = increment+1;
deallocate prepare create_table;
end while;
        set increment = 1;
end while;
close customer_surname_cursor;
END //
DELIMITER ;
-- ---------------------------------------------------------------------------------------------------------------------