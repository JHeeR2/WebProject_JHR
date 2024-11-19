--회원정보를 담는 members 테이블 생성
create table users (
    id varchar2(50) primary key,
    pass varchar2(255) Not Null,
    name varchar2(100) Not Null,
    email varchar2(100) CONSTRAINT email_uniq UNIQUE,
    nickname varchar2(50) CONSTRAINT nickname_uniq UNIQUE,
    birthday date ,
    gender varchar2(10),
    regidate date default sysdate Not Null
);

select * from users;

--더미데이터 입력
insert into users (id, pass, name, email, nickname, birthday, gender)
values ('admin','admin','장희란',
'sallyjang88@naver.com','관리자','24/01/01','여');
commit;



