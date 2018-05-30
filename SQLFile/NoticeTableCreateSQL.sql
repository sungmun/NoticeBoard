CREATE TABLE Notice(
    notice_num INT(12) unsigned NOT NULL AUTO_INCREMENT,
    notice_title VARCHAR(50) NOT NULL,
    member_id  VARCHAR(20) NOT NULL,
    notice_date datetime,
    notice_contents VARCHAR(255) NOT NULL,
    notice_count INT(16) NOT NULL default '0',
    PRIMARY KEY (notice_num),
    FOREIGN KEY (member_id) REFERENCES User (user_id) ON DELETE RESTRICT
);

ALTER TABLE Notice DROP notice_contents;