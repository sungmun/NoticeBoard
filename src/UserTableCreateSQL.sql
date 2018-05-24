CREATE TABLE User(
    user_id VARCHAR(20) NOT NULL,
    user_password   VARCHAR(25) NOT NULL,
    user_firstname  VARCHAR(5) NOT NULL,
    user_secondname VARCHAR(10) NOT NULL,
    joindate   DATETIME,
    user_phone      VARCHAR(12) NOT NULL,
    user_email      VARCHAR(50) NOT NULL,
    user_image      VARCHAR(150), 
    PRIMARY KEY (user_id)
);


CREATE TRIGGER TodayInsert BEFORE INSERT ON User FOR EACH ROW SET NEW.joindate = NOW() ;