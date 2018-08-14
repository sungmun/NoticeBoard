CREATE TABLE Comment(
    notice_num INT(12) unsigned NOT NULL,
    member_id  VARCHAR(20) NOT NULL,
    comment_contents VARCHAR(255) NOT NULL,
    FOREIGN KEY (member_id) REFERENCES User (user_id) ON DELETE RESTRICT,
    FOREIGN KEY (notice_num) REFERENCES Notice (notice_num) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE Comment ADD comment_day DATETIME;

CREATE TRIGGER TodayInsertComment BEFORE INSERT ON Comment FOR EACH ROW SET NEW.comment_day = NOW() ;

ALTER TABLE Comment ADD comment_num INT(12) unsigned NOT NULL auto_increment PRIMARY KEY;
ALTER TABLE Comment ADD re_comment_group INT(11) unsigned NOT NULL;
ALTER TABLE Comment MODIFY re_comment_group INT(11) unsigned;

DELIMITER $$
CREATE TRIGGER ReCommentInsert AFTER
INSERT ON Comment 
FOR EACH ROW 
BEGIN
IF NEW.re_comment_group IS NULL THEN
UPDATE Comment
SET 
re_comment_group = NEW.comment_num
WHERE
comment_num=NEW.comment_num;
END IF;
END $$
DELIMITER ;