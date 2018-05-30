CREATE TABLE NoticeContents(
    notice_num INT(12) unsigned NOT NULL ,
    notice_contents VARCHAR(255) NOT NULL,
    FOREIGN KEY (notice_num) REFERENCES Notice (notice_num) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX notice_idx ON NoticeContents (notice_num);
