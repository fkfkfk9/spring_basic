CREATE TABLE erd_test.`user` (
	serialNo INT auto_increment NULL COMMENT 'pk',
	userId varchar(100) NULL COMMENT '유저 아이디',
	userName varchar(100) NULL COMMENT '이름',
	userPhone varchar(100) NULL COMMENT '전화번호',
	userAge INT NULL COMMENT '나이',
	userMail varchar(100) NULL COMMENT '메일',
	CONSTRAINT user_pk PRIMARY KEY (serialNo)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='연습용 회원테이블';