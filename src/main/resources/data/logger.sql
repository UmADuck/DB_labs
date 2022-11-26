USE korol_iot_14;

CREATE TABLE IF NOT EXISTS logger
(
    id                  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    company_data        VARCHAR(255) NOT NULL,
    customer_data       VARCHAR(255) NOT NULL,
    action              VARCHAR(100) NOT NULL,
    time_stamp          DATETIME NOT NULL,
    user                VARCHAR(50) NULL
    );