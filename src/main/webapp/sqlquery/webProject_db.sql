--회원정보를 담는 users 테이블 생성
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

delete from users where id='admin1';
commit;


--더미데이터 입력
insert into users (id, pass, name, email, nickname, birthday, gender)
values ('admin','admin','장희란',
'sallyjang88@naver.com','관리자','24/01/01','여');
commit;





/*******************
자유게시판
**********************/



-- 자유 게시판을 관리하는 Freeboard 테이블 생성 
CREATE TABLE freeboard (
    idx          NUMBER          PRIMARY KEY, 
    user_id      VARCHAR2(50)    NOT NULL 
                                CONSTRAINT fk_freeboard_user_id
                                REFERENCES users(id),                            
    title        VARCHAR2(200)   NOT NULL,    
    content      VARCHAR2(2000)  NOT NULL,
    postdate     DATE            DEFAULT SYSDATE NOT NULL, 
    visitcount   NUMBER          DEFAULT 0    
);

--자유 게시판 idx에 사용할 시퀀스 생성
create sequence seq_free_num
    increment by 1  /* 증가치: 1 */
    start with 1  /* 초기값(시작값) : 1 */
    minvalue 1     /* 최소값 : 1 */
    nomaxvalue    /* 최대값 : 없음 */
    nocycle           /* 최대값 도달 시 최소값부터 재시작할지 여부 : No */
    nocache;         /* 캐시메모리 사용여부 : No */

--만들어진 seq_free_num 시퀀스 확인
select * from user_sequences ;

-- freeboard 테이블 확인
select * from freeboard;

--자유 게시판 더미데이터 입력
insert into freeboard (idx, user_id, title, content)
values (seq_free_num.nextVal,'admin','테스트','테스트입니다.');
insert into freeboard (idx, user_id, title, content)
values (seq_free_num.nextVal,'admin','테스트1','테스트입니다.');
insert into freeboard (idx, user_id, title, content)
values (seq_free_num.nextVal,'admin','테스트2','테스트입니다.');
insert into freeboard (idx, user_id, title, content)
values (seq_free_num.nextVal,'admin','테스트3','테스트입니다.');
insert into freeboard (idx, user_id, title, content)
values (seq_free_num.nextVal,'admin','테스트4','테스트입니다.');
insert into freeboard (idx, user_id, title, content)
values (seq_free_num.nextVal,'admin','테스트5','테스트입니다.');
insert into freeboard (idx, user_id, title, content)
values (seq_free_num.nextVal,'banana','바나나','바나나난');

desc freeboard;

select 
    idx, title, freeboard.user_id, users.nickname, postdate, visitcount 
from freeboard 
    inner join users
        on freeboard.user_id = users.id
order by idx desc;




commit;


select 
    title, content
from freeboard 
    inner join users
        on freeboard.user_id = users.id
where nickname like '%바나나%';







/*****************
자료실 게시판
********************/


--자료실 게시판을 관리하는 multiBoard 테이블 생성
CREATE TABLE multiboard (
    idx          NUMBER          PRIMARY KEY, 
    user_id      VARCHAR2(50)    NOT NULL 
                                CONSTRAINT fk_multiboard_user_id
                                REFERENCES users(id),     
    title        VARCHAR2(200)   NOT NULL,    
    content      VARCHAR2(2000)  NOT NULL,
    postdate     DATE            DEFAULT SYSDATE NOT NULL,
    ofile        VARCHAR2(200),               
    sfile        VARCHAR2(50),                
    downcount    NUMBER          DEFAULT 0,   
    visitcount   NUMBER          DEFAULT 0    
);

--자료실 게시판 idx에 사용할 시퀀스 생성
create sequence seq_multib_num
    increment by 1  /* 증가치: 1 */
    start with 1  /* 초기값(시작값) : 1 */
    minvalue 1     /* 최소값 : 1 */
    nomaxvalue    /* 최대값 : 없음 */
    nocycle           /* 최대값 도달 시 최소값부터 재시작할지 여부 : No */
    nocache;         /* 캐시메모리 사용여부 : No */

--만들어진 seq_multib_num 시퀀스 확인
select * from user_sequences ;

--multiboard 확인
select * from multiboard;

desc multiboard;
--자료실 게시판 더미데이터 입력
insert into multiboard (idx, user_id, title, content )
values (seq_multib_num.nextval, 'admin','test','테스트');
insert into multiboard (idx, user_id, title, content )
values (seq_multib_num.nextval, 'admin','test1','테스트1');
insert into multiboard (idx, user_id, title, content )
values (seq_multib_num.nextval, 'admin','test2','테스트2');
insert into multiboard (idx, user_id, title, content )
values (seq_multib_num.nextval, 'admin','test3','테스트3');
insert into multiboard (idx, user_id, title, content )
values (seq_multib_num.nextval, 'admin','test4','테스트4');
insert into multiboard (idx, user_id, title, content )
values (seq_multib_num.nextval, 'admin','test5','테스트5');
commit;

UPDATE multiboard SET downcount = downcount + 1 WHERE idx = 10;

UPDATE multiboard SET visitcount = visitcount + 1 WHERE idx = 10;

select 
    idx, title, content, multiboard.user_id, users.nickname, 
    downcount, visitcount, postdate, ofile, sfile
from multiboard 
    inner join users
        on multiboard.user_id = users.id
order by idx desc;

select count(*) from multiboard 
    inner join users
        on multiboard.user_id = users.id
order by idx desc;

DELETE FROM multiboard where idx = 14;

--where 절 포함 쿼리

select 
    M.*, U.nickname
from multiboard M
    inner join users U
        on M.user_id = U.id
where idx = 10;

desc multiboard;