CREATE TABLE Comment(
    comment_num INT(12) unsigned NOT NULL AUTO_INCREMENT,
    notice_num INT(12) unsigned NOT NULL,
    member_id  VARCHAR(20) NOT NULL,
    comment_contents VARCHAR(255) NOT NULL,
    FOREIGN KEY (member_id) REFERENCES User (user_id) ON DELETE RESTRICT,
    FOREIGN KEY (notice_num) REFERENCES Notice (notice_num) ON DELETE CASCADE ON UPDATE CASCADE
);