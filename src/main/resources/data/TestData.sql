INSERT INTO company_card(id, company_name) VALUES
(1, 'Smart toys'), (2, 'Silpo'), (3, 'Brocard'),
(4, 'LC Waikiki'), (5, 'Mc Donalds');

INSERT INTO customer_card(id, customer_name, customer_surname) VALUES
(1, 'Petro', 'Mohyla'), (2, 'Mykola', 'Mohyla'),
(3, 'Andrii', 'Zalizo'), (4, 'Hryhir', 'Veselka'),
(5, 'Anton', 'Palii'), (6, 'Andrii', 'Veselyi'),
(7, 'Olena', 'Metaleva'), (8, 'Anna', 'Perekonana'),
(9, 'Artem', 'Ivaniv'), (10, 'Oleh', 'Hrechka');

INSERT INTO booking(id, customer_card_id, parked_car_number_plate) VALUES
(1, 1, '65784'), (2, 3, 'gfdgd'), (3, 6, 'fdjjw'), (4, 8, 'fdcwe'), (5, 9, 'hjdks');

INSERT INTO parking_ticket(id, parked_car_number_plate, customer_card_id) VALUES
(1, 'fdsfs', NULL), (2, 'hfdwio', 4), (3, 'gregfds', 5), (4, 'gresg', NULL),
(5, 'gresgfds', NULL), (6, 'fewffew', NULL),
(7, 'fref', 2), (8, 'grsgs', 7), (9, 'grswgs', NULL), (10, 'fdsfs', NULL);