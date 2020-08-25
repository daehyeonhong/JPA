select*from users;
select*from board;
delete from users where id ='12'
desc board;

CREATE TABLE roles(
	role VARCHAR2(20) PRIMARY KEY,
	roleName VARCHAR2(30),
	description VARCHAR2(50)
);

INSERT INTO roles VALUES('admin','admin','관리자권한');
INSERT INTO roles VALUES('user','user','일반권한');
INSERT INTO roles VALUES('operation','operation','사용자권한');
select*from users;