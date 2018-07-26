-- 무결성 (Constraint) : 잘못된 자료의 입력을 막기 위한 제약조건
-- domain 제약조건 : create 시 칼럼에 순서, type, 크기 지정(즉 속성에 맞아야 함)
-- 기본키 제약조건 : 중복을 허용않는 칼럼에 대해 primary key 설정
--                        중복자료 입력 불가, not null, index 생성
-- 사용자 정의 제약조건 : check(컬럼의 값을 어떤 특정 범위로 제한), unique(중복 허용하지 않음), foreign key(테이블 참조) ...

drop table aa;

-- 기본키 제약조건 : unique 칼럼이 pk 대상
-- 방법 1
create table aa(bun number primary key, irum char(10));
-- constraint name 을 system 알아서 만들어 줌. 일반적으로 많이 사용하는 방법.

drop table aa;

-- 방법2
create table aa(bun number constraint aa_bun_pk primary key, irum char(10));
-- constraint name을 직접 줄 수 있음.

drop table aa;

-- 방법3
create table aa(bun number, irum char(10),
constraint aa_bun_pk primary key(bun));
-- create를 다 써놓고 마지막에 설정하는 방법. 

-- Constraint name 보는 방법
select table_name, constraint_type,
constraint_name from user_constraints
where table_name='AA'; -- 테이블명은 대문자

-- 제약조건 추가
-- alter table 테이블명 add constraint 제약조건명 제약조건(칼럼명);
-- 제약조건 삭제
-- alter table 테이블명 drop constraint 제약조건명

-- check 제약조건 : 특정칼럼의 입력자료에 대해 값을 검사.
create table aa(bun number, irum char(10),
nai number(2) check (nai >= 20)); -- table 생성시 check 제약조건 생성.

insert into aa values(1, 'tom', 12); -- nai check 제약조건에 위배되어 자료가 입력되지 않음.
insert into aa values(1, 'tom', 22); -- O

drop table aa;

-- unique 제약조건 : 특정 칼럼의 동일 값 입력 불허.
create table aa(bun number, irum char(10) unique);

insert into aa values(1, 'tom'); -- 먼저 입력
insert into aa values(1, 'tom'); -- irum이 unique이기 떄문에 제약조건 위배되어 자료가 입력되지 않음.

drop table aa;

-- foreign key(외부키, 참조키, fk)의 대상은 pk
-- 다른 테이블의 칼럼값을 참조
create table jikwon(sabun number primary key,
irum varchar2(10) not null, buser char(10));

insert into jikwon values(1,' 홍길동','총무');
insert into jikwon values(2,' 고길동','총무');
insert into jikwon values(3,' 홍길동','영업');

select * from jikwon;

create table gajok(code number primary key,
gname varchar2(10) not null, 
gbirth date, 
jikwon_sabun number references jikwon(sabun)); -- 직원 테이블에 사번을 참조 함.

create table gajok(code number primary key,
gname varchar2(10) not null, 
gbirth date, jikwon_sabun number,
constraint gajok_sabun_fk foreign key (jikwon_sabun) references jikwon(sabun)); -- foreign 키 추가 할 수 있는 다른 방법.

create table aa(bun number, irum char(10),
constraint aa_bun_pk primary key(bun));

-- on delete cascade : 부모 테이블의 행 삭제 시 자식 테이블 종속행 자동 삭제

insert into gajok values(100,'홍두깨','2005-1-15',1);
insert into gajok values(200,'신기해','1980-1-15',2);
insert into gajok values(300,'홍당무','2006-11-15',5); -- jikwon 테이블 sabun 속성(칼럼)을 참조하고 있는데 5번이 없음.
insert into gajok values(300,'홍당무','2006-11-15',1);

select * from jikwon;
select * from gajok;

delete from jikwon where sabun=1; -- 종속 레코드가 있기 때문에 지워지지 않음.
delete from jikwon where sabun=2;
delete from jikwon where sabun=3;

drop table jikwon; -- 다른 테이블에서 참조되는 레코드가 있기 때문에 테이블을 삭제 할 수 없음.

delete from gajok where code=200;
delete from jikwon where sabun=2;

drop table gajok;
drop table jikwon; -- 종속 되어 있는 테이블에 레코드를 삭제 후 테이블 삭제.

-- default : 특정칼럼에 초기값 부여
create table aa(bun number, irum char(10),
addr varchar2(30) default '강남구 테헤란로 123');

insert into aa values(1,'tom','서초구 서초동 111');
insert into aa(bun,irum) values(2,'james');

select * from aa;

-- sequence : number type 칼럼에 대해 숫자 자동 증가, 시퀀스는 수정하지 못함.
create sequence seq_my minvalue 0 maxvalue 10000
increment by 1 start with 0;

drop table aa;

create table aa(bun number primary key, irum char(10));
create table bb(code number primary key, irum char(10));

insert into aa values(seq_my.nextval, 'tom'); -- create한 sequence명.mynextval 을 쓰면 번호가 자동으로 늘어남.
insert into aa values(seq_my.nextval, 'tom2');
insert into aa values(seq_my.nextval, 'tom3');
select * from aa;
insert into bb values(seq_my.nextval, 'tom4'); -- sequence 번호는 같은 시퀀스명을 쓰면 연결됨.
select * from bb;

select seq_my.currval from dual; -- dual 가상의 테이블명
select seq_my.nextval from dual;

insert into bb values(seq_my.nextval, 'tom5');
select * from bb;
insert into bb values(6, 'tom6');

insert into bb values(seq_my.nextval, 'tom7'); --x
select seq_my.currval from dual;

drop sequence seq_my; -- 시퀀스 삭제

create sequence seq_our;
select seq_our.nextval from dual;
select seq_our.currval from dual;

select * from aa;

-- 테이블 생성
create table test(no number(5), name varchar2(10));

-- 자료 삽입하기
insert into test(no, name) values(1,'tom');
insert into test(no, name) values(1,'tom');

-- 테이블 확인하기
Select * from test;

-- 테이블 삭제하기
drop table test;

-- 테이블 생성하기
create table test(no number primary key, 
name varchar2(10) not null, 
tel varchar2(15),
inwon number, 
addr varchar(30));

-- 테이블 속성 보기
desc test;

-- 레코드 추가하기
insert into test(no,name, tel, inwon, addr)
values (1,'인사과','111-1111',5,'역삼동123');

-- 들어오는 칼럼이랑 값이랑 순서를 맞춰야 함.
insert into test(no, tel, inwon, addr, name)
values (2,'111-1112',8,'역삼동123','판매1과');

-- 들어오는 값이 칼럼순서와 명이 맞으면 생략 가능.
insert into test
values (3,'자재과','111-1113',6,'역촌동123');

insert into test(no,name)
values (4,'자재2과');

insert into test(no, tel)
values(4,'222-2222'); -- err : name : not null 필수 입력

insert into test(no,name)
values (4,'자재3과'); -- err : no : PK이기 때문에 중복값을 가질 수 없음.

insert into test(no,name)
values (5,'고객을 사랑하는 우리회사 모범 부서과'); -- err : name : 최대값은 10인데 실제값은 52이기 때문에 들어가지 않음.

commit; --rollback;
-- Server에 동기화하여 다른 곳에서 볼 수 있도록 하여야함. 
-- Commit 하지 않으면 자기 컴퓨터에서만 놀고 있음. 
-- 컴퓨터들은 한 서버에 원격으로 연결되어있는 것.
-- Commit 하지 않으면 모든 내용은 날아가게 되는 것.
-- sqlplus에서 quit하게 되면 자동으로 Commit 함.
-- Oracle는 수동 Commit이지만 나머지 MySQL, JAVA 등은 자동 Commit.
-- 자동 Commit 하게되면 DB Server에서 과부하가 걸려 처리하는데 오랜 시간이 걸릴 수도 있음.

-- 자료수정하기
update test set inwon=19;

rollback; -- Commit된 시점으로 Rollback하게 됨.

update test set inwon=19 where no=1; -- no가 1번인 레코드만 수정됨.

-- 칼럼과 레코드의 순서는 상관없다. select 할 때 설정 할 수 있음.

update test set inwon=11, addr='신림동111' where no=2;

update test set name=null, addr='' where no=3; -- err : name : null=('')이 들어 갈 수 없음.

-- 자료 삭제
delete from test where no=1; -- no가 1번인 레코드를 삭제함. delete는 commit, rollback의 영향을 받음.
 
truncate table test; --truncate는 삭제하는 것인데 Auto Commit 되기 때문에 되도록 사용하지 않도록 함.

drop table test;

select * from test;

-- 문제1

-- 교수테이블 생성
create table 교수(교수코드 number primary key,
교수명 varchar2(10), 
연구실 number check (연구실 >= 100 and 연구실 <= 500));

-- 시퀀스 생성
create sequence seq_school minvalue 0 maxvalue 10000
increment by 1 start with 1;

-- 과목테이블 생성
create table 과목(과목코드 number primary key,
과목명 varchar2(10) unique,
교재명 varchar(20),
담당교수 number references 교수(교수코드));

-- 학생테이블 생성
create table 학생(학번 number primary key,
학생명 varchar2(10),
수강과목 number references 과목(과목코드),
학년 number default 1, check (학년 >= 1 and 학년 <= 4));

-- 교수테이블 자료 입력
insert into 교수 values(10, '신기해', 100);
insert into 교수 values(20, '어쩌해', 200);
insert into 교수 values(30, '홍당무', 300);
-- insert into 교수 values(30, '홍당무', 600); -- err : check 범위 벗어남.

-- 과목테이블 자료 입력
insert into 과목 values(seq_school.nextval, '수학', '수학이조아', 10);
insert into 과목 values(seq_school.nextval, '영어', '영어가조아', 20);
insert into 과목 values(seq_school.nextval, '과학', '과학이조아', 30);
-- insert into 과목 values(seq_school.nextval, '국어', '국어가조아', 40); -- err : 교수 번호 없음

-- 학생테이블 자료 입력
insert into 학생(학번,학생명,수강과목) values(201801, '안녕해', 1);
insert into 학생 values(201802, '홍뭉이', 2, 4);
insert into 학생 values(201803, '전형철', 3, 3);
-- insert into 학생 values(201804, '아이해', 4, 5); -- err : check 범위 벗어남.

drop table 교수;
drop table 과목;

set timing on;
select * from aa;
-- index : 특정 칼럼의 값에 대한 검색속도 증진
-- pk 적용 칼럼은 자동적으로 index 생성
-- 사용 이유
-- 레코드가 수가 많은 경우 검색을 빠르게 하기 위함
-- join이 자주 사용된 경우
-- null를 포함하고 있는 칼럼
-- wherew 조건이 빈번한 경우

-- 사용을 자제하는 경우
-- 입력, 수정, 삭제가 빈번한 테이블

create unique index ind_idx on aa(irum); -- 고유인덱스 
create index ind_ir on aa(irum); -- 비고유인덱스 : 중복자료가 있을 때 사용

select index_name, table_name from user_indexs;
select index_name, table_name from user_indexs
where table_name='AA';

alter index ind_ir rebuild; -- 기존 인덱스를 재생성
drop index ind_ir;

set timing off;

-- 테이블 관련
-- create table 테이블명 ~
-- alter table 테이블명 ~
-- drop table 테이블명 ~
-- rename 옛이름 to 새이름

rename aa to kbs;
select * from kbs;
desc kbs;

-- 테이블 속성 추가
alter table kbs add(job_num number(3) default 100);

select * from kbs;

update kbs set job_num=777 where bun=1;
update kbs set job_num=7777 where bun=1; -- x number를 3자리로 잡았는데 4자리 라서 안됨.

-- 테이블 속성 변경
alter table kbs modify(job_num number(4)); 
desc kbs;
alter table kbs modify(job_num number(2)); -- err : 늘리는 건 가능하나 줄일 수 없음(데이터가 있을 경우에만)

-- 테이블 컬럼명 변경
alter table kbs rename column job_num to job_bun;
desc kbs;

-- 테이블 컬럼 삭제
alter table kbs drop(job_bun);
desc kbs;

alter table kbs set unused(irum); -- 삭제 예약걸기.
alter table kbs drop unused columns; -- irum에 대한 컬럼을 사용하지 않을 경우 drop함.

-- 데이터 export / import
show user;
select * from tab;
select * from emp;
select * from dept;
-- c:\~>
-- cmd에서 C:\>exp scott/tiger tables=emp,dept file=c:\work\scott.dmp 하면 export 됨.

drop table emp;
drop table dept;
select * from emp; -- 위에 줄에서 삭제 했기 때문에 아무것도 없음.

-- cmd에서 C:\>imp scott/tiger tables=emp,dept file=c:\work\scott.dmp 하면 import 됨.

-- 테이블 삭제, 복원, 완전 삭제
purge recyclebin;
select * from tab; -- 현재 계정에 테이블 전체를 보여줌
drop table emp; -- 삭제 시 완전히 지워지는게 아니라 recyclebin(오라클 휴지통)에 보관.

show recyclebin; -- 오라클휴지통에 있는 것 보기.

flashback table emp to before drop; -- 복원

select * from emp;

drop table emp;

show recyclebin;

purge recyclebin; -- 휴지통 완전삭제

drop table dept purge; -- 테이블 완전 삭제

select * from sangdata;

-- select 연습
-- select [distinct] 소유자명.칼럼명[as 별명], ...
-- [into 테이블명] from 테이블명
-- where 조건...
-- order by 정렬키 [asc/desc]...
-- group by 
-- having

select * from jikwon;
select jikwon_no, jikwon_name from jikwon;
select jikwon_name, jikwon_no from jikwon; -- 원하는 컬럼명만 넣으면 불러 올 수 있음. 컬럼명을 쓴 순서대로 나열해줌.

select jikwon_no as 사번, jikwon_name 직원명 from jikwon; -- 별명을 붙여 줄 수 있으며, as를 써서 별명을 지어줄 수 있으나 그냥 써도 상관 없음.
-- 나중에 java에서 불러 올 때?

select jikwon_no || jikwon_name from jikwon; -- 문자열 더하기 연산 ||(연결 연산자). OR 아님.
select jikwon_no || jikwon_name as 자료 from jikwon;

select 10, '안녕', 12/3 from dual; -- dual : dummy table. dual이라는 키워드를 이용하면 가상테이블 사용 할 수 있음.
select jikwon_no, jikwon_name, jikwon_pay from jikwon; 

select jikwon_no, jikwon_name, jikwon_pay,
jikwon_pay * 0.012 as tax from jikwon; -- 계산식을 사용해서 컬럼을 추가 할 수 있음.

select jikwon_name || '님' from jikwon; -- 일반 문자열도 붙여서 사용 할 수 있음. 
-- 문자열을 더한다고 DB Server에 영향을 주지는 않음.

select * from & table_name; -- 명령어 실행 후 테이블명을 치면 그 테이블에 대한 내용을 불러옴 -- 참고
select &column_name from jikwon; -- 참고

-- 정렬(sort) : order by
select * from jikwon order by jikwon_pay asc; -- 오름차순
select * from jikwon order by jikwon_pay desc; -- 내림차순

-- order by를 쓰면 그룹별로 볼 수 있음.
select * from jikwon order by 
jikwon_gen;

select * from jikwon order by 
jikwon_gen asc, buser_num desc, jikwon_pay desc; -- 순차적으로 정렬 시켜 볼 수 있음.

select jikwon_no, jikwon_name, jikwon_pay / 100 * 100
from jikwon;

select jikwon_no, jikwon_name, jikwon_pay / 100 * 100 pay
from jikwon
order by pay asc; -- 별명을 이용해서 정렬도 할 수 있음.

select jikwon_no, jikwon_name from jikwon
order by 2 asc; -- 포지션 넘버(칼럼의 순서)를 줘서 할 수 있음. no가 1, name 2.

select jikwon_no, jikwon_name from jikwon
order by 2 asc, 1 desc;

select distinct jikwon_jik from jikwon; -- distinct 중복 제거. 중복된 내용이 있는 속성만 불러서 사용 해야함. 중복되지 않는 속성을 가져오면 다 나오게 됨.

select * from jikwon where jikwon_jik='대리'; -- 조건을 달아 그 레코드만 출력 할 수 있음.
select * from jikwon where jikwon_no=3;
select * from jikwon where jikwon_no='3'; -- 숫자는 작은따옴표를 해도 그만 안해도 그만.
select * from jikwon where jikwon_ibsail='2011/03/03';
select * from jikwon where jikwon_ibsail='2011-03-03'; -- data는 -, / 둘중에 아무거나 써도 상관 없음.

alter session set nls_date_format='YYYY-MM-DD';

-- and, or, 산술 연산
select * from jikwon where  jikwon_no=3 or jikwon_no=5;
select * from jikwon where  jikwon_no=3 and jikwon_no=5;
select * from jikwon where  jikwon_jik='사원' or jikwon_no=5;
select * from jikwon where  jikwon_jik='사원' and jikwon_gen='여' and jikwon_pay <= (3000 + 500);

select * from jikwon where jikwon_no >= 5 and jikwon_no <= 10;

select * from jikwon
where jikwon_ibsail between '2001-1-1' and '2005-12-31'; -- 연속된 값에 대해 확인

select * from jikwon
where jikwon_ibsail between '김' and '박'; -- 의미 없음

select * from jikwon where jikwon_no < 5 or jikwon_no > 10;

select * from jikwon where not ( jikwon_no < 5 or jikwon_no > 10); -- not을 쓰면 조건을 제외한 나머지를 보여줌.
select * from jikwon where jikwon_no between 5 and 10; -- 되도록 부정적인 조건 보다 긍정적인 조건을 주도록 해야 함. 왜냐 속도가 떨어짐.
select * from jikwon where jikwon_no not between 5 and 10; -- 틀리진 않지만 별로 좋지는 않음.

select * from jikwon where not jikwon_pay + 1000 >= 3000 + 500;

select * from jikwon where jikwon_gen='남';
select * from jikwon where jikwon_gen<>'남'; -- <> 이 표시는 같지 않음을 의미.

 select * from jikwon where jikwon_name='홍길동';
 select * from jikwon where jikwon_name >='박'; -- 문자열에 대해서도 크기연산자 사용 가능. 박을 제외한 사람들을 출력해줌
 
 select ascii('a'), ascii('A'), ascii(0), ascii('가'), ascii('나') from dual; -- 내장함수 ascii 를 이용하면 코드 값을 알 수 있음.

select * from jikwon where jikwon_name >='김' and jikwon_name <='최';
select * from jikwon where jikwon_name between '김' and '최'; -- 문자열에 대해서 between 사용가능.

select * from jikwon where jikwon_jik='대리' or jikwon_jik='과장' or jikwon_jik='부장';
select * from jikwon where jikwon_jik in('대리', '과장', '부장'); -- 위에줄 코드를 간단하게 in연산자를 사용해 출력 가능.
select * from jikwon where buser_num in(10, 30)
order by buser_num asc;
select jikwon_no, jikwon_name, buser_num from jikwon where buser_num in(10, 30)
order by buser_num asc;

select * from buser where buser_name in('총무부', '전산부');

-- like 연산
select * from jikwon where jikwon_name like '이%'; -- '%'는 0개 이상의 문자열을 나타냄
select * from jikwon where jikwon_name like '이순%';
select * from jikwon where jikwon_name like '이%라';
select * from jikwon where jikwon_name like '%순%';
select * from jikwon where jikwon_name like '이%라'; 
select * from jikwon where jikwon_name like '이_라';
select * from gogek where gogek_name like '__';
select * from jikwon where jikwon_name like '이%' or jikwon_name like '김__';
select * from gogek;

select * from gogek where gogek_jumin like '_______2%';
select * from gogek where gogek_jumin like '%-2%';

update jikwon set jikwon_jik=null where jikwon_no=5;
commit;


select * from jikwon;

select * from jikwon where jikwon_jik=null;
select * from jikwon where jikwon_jik is null;
select * from jikwon where jikwon_jik is not null;

-- 연산자 우선순위
-- () > 산술(*,/, > +, -) > 연결연산자 > 비교연산자 > is null, like, in> [not] between 
-- > is null, like, in > [not] between

select jikwon_no as 사번, jikwon_name 직원명,
jikwon_jik, jikwon_pay, jikwon_pay / 12 보너스,
jikwon_ibsail from jikwon where jikwon_jik in ('사원', '대리', '과장')
and (jikwon_pay >= 2000 and jikwon_ibsail between '2000-1-1' and'2010-12-31')
order by jikwon_jik asc, jikwon_pay desc;

-- 내장함수 : 데이터 조작의 효율성 목적
-- 단일행 함수 - 각 레코드 별 작업
-- 문자함수 
select lower('Hello World'),upper('Hello World'), 
initcap('Hello world') from dual;
select concat ('Hello','World') from dual;

select ('Hello World', 3),
substr ('Hello World', 3, 3),
substr ('Hello World', -3, 3)
from dual;

select length('Hello World'), length('헬로 월드'),
instr('Hello World', 'o'),
instr('Hello World', 'o', 6),
instr('Hello World', 'o', 1,2),
trim(both ' ' from ' ab c   ')
from dual;

--문) jikwon 테이블에서 이름에 '이'가 포함된 직원이 있으면
-- '이'부터 두 자 출력

select jikwon_name from jikwon where jikwon_name like '%이%';

or substr jikwon (3);

select jikwon_name from jikwon where jikwon_name like '%이%';

select substr (jikwon_name, 1 , 2) from jikwon where jikwon_name like '%이%';

select jikwon_name, substr(jikwon_name, instr(jikwon_name,'이'), 2) from jikwon 
where jikwon_name like '%이%'; 


--숫자함수 

-- round : 소수점 반올림
select round (45.678), round (45.678,2), round (45.678,0), round (45.678,-1) from dual;
select jikwon_name, round(jikwon_pay * 0.0123) as tex 
from jikwon;

-- trunc : 소수점 이하 버리기
select trunc(45.678, 2), trunc(45.678, -1) from dual;

-- mod : 나머지 구하기
select mod(15,3),mod(15,2) from dual;

--날짜 함수 (윤년체크 함)
select sysdate from dual;
select sysdate, sysdate + 10, sysdate + 10 ,sysdate + 2914000 from dual;

--근무한지 5만 초과 직원 목록
select jikwon_name, jikwon_ibsail from jikwon where (sysdate - jikwon_ibsail) * 24 > 50000;

select round ((sysdate - jikwon_ibsail) / 7) as ju from jikwon; --날짜함수는 연사도 가능

select months_between('2018-08-10', '2018-01-10')from dual;

select add_months (sysdate, 3), add_months(sysdate, -3) from dual;

select sysdate, last_day(sysdate), next_day(sysdate, '토') from dual;

alter session set nls_language=korean;

-- 형변환 함수

select jikwon_pay * 0.5, jikwon_pay * '0.5' from jikwon;

-- to_date(), to_number(), to_char()

select sysdate - to_date*('2010-01-01','yyyy-mm-dd') from dual;

select to_char(sysdate + 3 / 24, 'yyyy"년"mm"월"dd"일"hh"시"') from dual;

select to_char(sysdate, 'yyyy') from dual;
select to_char(sysdate, 'DD-MON-RR RR:MI:SS') from dual;
select to_char(sysdate, 'WW') from dual;

select to_char(1234.567, '9,999.99'), 
to_char(12.567, '9,999.99'), to_char(12,'9,999.99')
from dual;

select to_char(1234.567, '0,009.99'), 
to_char(12.567, '9,0009.99'), to_char(12,'9,0009.99')
from dual;


-- 기타함수
-- rank(), dense_rank()
select jikwon_no, jikwon_name, jikwon_pay,
rank() over(order by jikwon_pay desc) as rank, -- 랭크는 혼자 못씀. 랭크에 중복 허용 안함.
dense_rank() over(order by jikwon_pay desc) as rank2  -- 랭크에 중복허용 함.
from jikwon;

-- nvl(value1, value2)
select jikwon_name, jikwon_jik,
nvl(jikwon_jik, '임시직')
from jikwon;

-- nvl2
select jikwon_name, jikwon_jik,
nvl2(jikwon_jik, '정규직', '임시직') -- null 값에 넣음
from jikwon;

-- nullif(value1, vaule2)
select nullif(length('abcd'), length('1234')),
nullif(length('abcd'), length('123')) -- 다른거...?
from dual;

select jikwon_name, jikwon_jik,
nullif(jikwon_jik,'대리') from jikwon;

-- 조건 표현식
-- 형식 1 :
-- case 표현식 when 비교값1 then 결과값 1...else 결과값 n
select case 10 / 5
when 5 then '안녕'
when 2 then '반가워'
else '잘가' end as result -- 비교값에 대한 참 결과값을 출력, end as 칼럼명 추가
from dual;

select jikwon_name, 
case jikwon_pay when 8200 then '연봉팔천대'
when 5500 then '연봉5000대'
else '기타' end as 연봉
from jikwon;

select jikwon_name, jikwon_pay, jikwon_jik,
case jikwon_jik
when '사장' then jikwon_pay * 0.05
when '부장' then jikwon_pay * 0.04
when '과장' then jikwon_pay * 0.03
else jikwon_pay * 0.03
end as donation
from jikwon where jikwon_gen='남';

-- 형식2 :
-- case when 조건1 then 결과1... end
select jikwon_name, jikwon_gen,
case when jikwon_gen='남' then 'M'
when jikwon_gen='여' then 'F' end as gender
from jikwon;

select jikwon_name, jikwon_pay,
case
when jikwon_pay >= 7000 then '우수'
when jikwon_pay >= 5000 then '일반'
else '부족'
end as result from jikwon
where jikwon_jik in('부장','과장','사원');

-- decode : 오라클 전용
-- decode(칼럼, 비교값1, 반환값1, 비교값2, 반환값2,...반환값n)
select jikwon_name, jikwon_pay,
decode(trunc(jikwon_pay / 1000),4, 'A', 3, 'B', 2, 'C', 'etc') result
from jikwon;

select jikwon_name, jikwon_gen,
decode(jikwon_gen, '남 ', 'M', '여 ', 'F', ' ') as gender -- vchar2는 한글 쓸 때 2바이트가 아닌 3바이트를 소모함.
from jikwon;

select jikwon_name, buser_num,
decode(buser_num, 10, '총무', 20, '전산', ' ') as bu
from jikwon;

--문1) 10년 이상 근무하면 '최우수', 5년 이상 근무하면 '우수', 그 외는 '일반'이라 표시
--     2000년 이후 입사한 직원만 해당
--     특별수당(pay 기준)  : 10년 이상 10%, 5년 이상 5%, 그 외 3% (반올림:정수)
--출력  사번   직원명   근무년수   표현   특별수당
--      1      홍길동      11     최우수    ***
select jikwon_no as 사번, jikwon_name as 직원명, round((sysdate - jikwon_ibsail)/365) as 근무년수,
case
when round((sysdate - jikwon_ibsail)/365) >= 10 then '최우수'
when round((sysdate - jikwon_ibsail)/365) >= 5 then '우수'
else '일반' end as 표현 ,
case 
when round((sysdate - jikwon_ibsail)/365) >= 10 then jikwon_pay * 0.1
when round((sysdate - jikwon_ibsail)/365) >= 5 then jikwon_pay * 0.05
else jikwon_pay * 0.03
end as 특별수당 from jikwon
where to_char(jikwon_ibsail, 'yyyy') >= 2000;

--문2) 10년 이상 근무하면 '왕고참', 5년 이상 근무하면 '고참', 그 외는 '일반'이라 표시
--     null 이 있는 자료는 제외, 성이 김,이,박,홍 씨만 작업에 참여
-- 출력   직원명    직급    입사년월일    근무개월수    구분    부서명
--           홍길동    대리    2002.2.5        5676       왕고참    총무부
select jikwon_name as 직원명, jikwon_jik as 직급, to_char(jikwon_ibsail,'yyyy.mm.dd') as 입사년원일, round(months_between(sysdate, jikwon_ibsail)) as 근무개월수,
case
when round((sysdate - jikwon_ibsail)/365) >= 10 then '왕고참'
when round((sysdate - jikwon_ibsail)/365) >= 5  then '고참'
else '일반' end as 구분,
decode(buser_num, 10, '총무부', 20, '영업부', 30, '전산부' ,40, '관리부') as 부서명
from jikwon
where jikwon_jik is not null and (jikwon_name like '홍%' or  jikwon_name like '김%' or  jikwon_name like '이%' or  jikwon_name like '박%');

--문3) 각 부서번호별로 실적에 따라 급여를 다르게 인상하려 한다. 
--     pay를 기준으로 10번은 10%,30번은 20% 인상하고 나머지 부서는 동결한다.
--     10년 이상 장기근속을 표시
--     금액은 3자리 마다 ,(콤마) 표시 : 정수만 출력(반올림)
-- 출력   사번    직원명   부서    연봉    인상연봉    장기근속
--             1     홍길동    10    **,***     ****          O       <-- 아니면 X 표시
select jikwon_no as 사번, jikwon_name as 직원명, buser_num as 부서, to_char(jikwon_pay, '99,999') as 연봉,
case
when buser_num = '10' then to_char(round(jikwon_pay * 1.1), '99,999') 
when buser_num = '30' then to_char(round(jikwon_pay * 1.2), '99,999')
else to_char(jikwon_pay, '99,999')  end as 인상연봉,
case
when trunc((sysdate - jikwon_ibsail)/365) >= 10 then 'O'
else 'X' end as 장기근속
from jikwon;

-- 복수 행 함수(그룹 함수) : null 값 무시 ( count(*) 예외)
select sum(jikwon_pay) as 합, avg(jikwon_pay) as 평균
from jikwon;

select max(jikwon_pay) as 최대값, min(jikwon_pay) as 최소값
from jikwon;

update jikwon set jikwon_pay= null where jikwon_no=1;
commit

select max(jikwon_pay) 15as 최대값, min(jikwon_pay) as /16
from jikwon;

select count(jikwon_no), count(jikwon_jik),
from jikwon
count(jikwond_pay),count;


-- 과장은 몇명?
select count(*) as 인원수 
from jikwon
where jikwon;

-- 2005년 이후에 입사한 여직원 급여함
select sum(jikwon_pay as 급여함), avg(jikwon_pay_from jik_gen)
where jikwon_ibsail >= '2005'-1-1 and jikwon_gen=ka;

-- group by : 소계 출력
-- select 그룹 칼럼명,... 계산함수(),...
-- from 테이블명 where 조건
-- group by 그룹칼럼명,.. having 출력결과조건
-- 참고 : 그룹칼럼에 order by 할 수 없다.
--         단, 출력결과는 order by 가능

-- 성별 급여의 평균, 인원수 출력
select jikwon_gen, avg(jikwon_pay), count(*) -- count는 null인 것도 포함시켜 카운트
from jikwon
group by jikwon_gen;

-- 부서별 급여함
select buser_num, sum(jikwon_pay)
from jikwon
group by buser_num;

-- 부서별 급여합 : 급여합이 15000 이상
select buser_num, sum(jikwon_pay)
from jikwon
group by buser_num
having sum(jikwon_pay) >= 15000; -- group by에 대한 조건

-- 부서별 급여햡 : 남자만 참여
select buser_num, sum(jikwon_pay)
from jikwon
where jikwon_gen='남' -- group by 전 조건 확인
group by buser_num;

-- 부서별 급여합 : 급여합이 8000 이상인 여자만 참여
select buser_num, sum(jikwon_pay)
from jikwon
where jikwon_gen='여' -- group by 전 조건 확인
group by buser_num
having sum(jikwon_pay) >= 8000;

-- select buser_num, sum(jikwon_pay)
-- from jikwon
-- order by buser_num
-- group by buser_num; -- group by 전에 order by하면 안됨.

select buser_num, sum(jikwon_pay)
from jikwon
group by buser_num
order by sum(jikwon_pay) desc;

-- 그룹함수 중첩
select max(avg(jikwon_pay))
from jikwon
group by jikwon_pay;

-- 문1 : 직급별 급여의 평균 (NULL인 직급 제외)
select jikwon_jik as 직급, round(avg(jikwon_pay)) as 급여평균
from jikwon
where jikwon_jik is not null
group by jikwon_jik
order by 급여평균 desc;

-- 문2 : 부장,과장에 대해 직급별 급여의 총합
select jikwon_jik as 직급, sum(jikwon_pay) as 급여총합
from jikwon
where jikwon_jik in ('부장', '과장')
group by jikwon_jik
order by 급여총합 desc;

-- 문3 : 2010년 이전에 입사한 자료 중 년도별 직원수 출력
--select substr (jikwon_ibsail, 1, 2) as 입사년도, count(*) as 직원수
select to_char(jikwon_ibsail, 'yyyy') as 입사년도, count(*) as 직원수
from jikwon
where jikwon_ibsail < '2010-01-01'
group by to_char(jikwon_ibsail, 'yyyy')
order by to_char(jikwon_ibsail, 'yyyy') asc;

-- 문4 : 직급별 성별 인원수, 급여합 출력 (NULL인 직급은 임시직으로 표현)
select nvl(jikwon_jik, '임시직') as 직급, jikwon_gen as 성별, count( jikwon_gen) as 인원수, sum(jikwon_pay) as 급여합
from jikwon
group by jikwon_jik, jikwon_gen
order by jikwon_jik asc;

-- 문5 : 부서번호 10,20에 대한 부서별 급여 합 출력
select buser_num as 부서번호, sum(jikwon_pay) as 급여합
from jikwon
group by buser_num
having buser_num in (10,20)
order by 급여합 desc;

-- 문6 : 급여의 총합이 7000 이상인 직급 출력(NULL인 직급은 임시직으로 표현)
select nvl(jikwon_jik, '임시직') as 부서번호, sum(jikwon_pay) as 급여합
from jikwon
group by jikwon_jik
having sum(jikwon_pay) >= 7000
order by 급여합 desc;

-- 문7 : 직급별 인원수, 급여합계를 구하되 인원수가 3명 이상인 직급만 출력(NULL인 직급은 임시직으로 표현)
select nvl(jikwon_jik, '임시직') as 직급, count(jikwon_jik) as 인원수, sum(jikwon_pay) as 급여합계
from jikwon

group by jikwon_jik
having count(jikwon_jik) >= 3
order by 급여합계 desc;

select buser_num, jikwon_jik, sum(jikwon_pay)
from jikwon
group by rollup(buser_num,jikwon_jik) -- rollup : group별 합계 구할 때. 프로그래밍에서 불러 오는데 장애가 발생 할 수 있어 잘 사용하지는 않음.
order by buser_num asc;

select buser_num, jikwon_jik, sum(jikwon_pay)
from jikwon
group by cube(buser_num,jikwon_jik) -- cube : 좌우, 위아래에 대해서 합계를 구함. 프로그래밍에서 불러 오는데 장애가 발생 할 수 있어 잘 사용하지는 않음.
order by buser_num asc;

-- join : 복수 테이블에서 자료 select
-- cross join : 모든 내용과 맵핑.
desc jikwon;
desc buser; -- 직원 테이블에 부서번호가 공통 칼럼, 고객과 관련 없기 때문에 조인 할 수 없음.
desc gogek; -- 직원테이블에 사원번호가 공통 칼럼, 부서와 관련 없기 때문에 조인 할 수 없음.

-- cross join 는 실무적으로 잘 사용하지 않음.
select jikwon_name, buser_name
from jikwon, buser;

select jikwon_name, buser_name
from jikwon
cross join buser; -- 위에 사용한 명령어와 동일.

-- equi join : inner, left outer, right outer, full
-- equi : 같으면(=)
-- innter join
insert into buser(buser_no, buser_name) values(50, '비서실'); -- join 확인 위해 일부러 자료 삽입
select * from buser;
select * from jikwon;
desc jikwon;
alter table jikwon modify buser_num number(4) null; -- 직원 테이블 구조 바꿈.
update jikwon set buser_num=null where jikwon_no = 5;
select * from jikwon;
commit;

select jikwon_no, jikwon_name, buser_name -- equi join
from jikwon, buser
where jikwon.buser_num=buser.buser_no; -- 칼럼명이 다르면 테이블을 생략해도 가능. 같으면 테이블명을 써줘야 함.

select jikwon_no, jikwon_name, buser_name -- inner join
from jikwon inner join buser
on buser_num=buser_no; -- on을 써야함.

select jikwon_no, jikwon_name, buser_name -- 위에 inner join 명령어와 동일.
from jikwon  join buser -- 기본이 inner이기 때문에 inner를 생략하고 써도 무방함.
on buser_num=buser_no; -- on을 써야함.

-- left outer join : 한 쪽 테이블에 null인 자료 출력
select jikwon_no, jikwon_name, buser_name
from jikwon, buser
where buser_no(+)=buser_num; -- 어느쪽에 (+)을 썼냐에 따라 다름. null 인쪽에 (+) 입력해주면 나옴.

select jikwon_no, jikwon_name, buser_name -- 위에 left outer join과 동일
from jikwon left outer join buser -- left outer join 할 때 테이블 순서에 따라 null 값이 나오는게 다름
on buser_num = buser_no; -- 여기서는 (+)는 상관없음.

-- right outer join : 한 쪽 테이블에 null인 자료 출력
select jikwon_no, jikwon_name, buser_name
from jikwon, buser
where buser_no=buser_num(+);

select jikwon_no, jikwon_name, buser_name -- 위에 right outer join과 동일
from jikwon right outer join buser -- right outer join 할 때 테이블 순서에 따라 null 값이 나오는게 다름
on buser_num = buser_no; -- 여기서는 (+)는 상관없음.

-- full : 양 쪽 테이블에 null인 자료 출력
select jikwon_no, jikwon_name, buser_name
from jikwon full outer join buser
on buser_num=buser_no;

-- self join : 하나의 테이블을 두 번 검색
select a.jikwon_name, b.jikwon_jik -- 하나에 테이블을 두 개 인것처럼 사용 가능.
from jikwon a, jikwon b
where a.jikwon_no=b.jikwon_no;

-- non equi join : = 이외의 조건을 사용(크거나, 작거나, 크거나 작거나, 작거나 같으면)
create table paygrade(grade number(1) primary key,
lowpay number, highpay number); -- non equi join을 하기 위하여 임의 테이블 생성

insert into paygrade values(1,0,1999);
insert into paygrade values(2,2000,2999);
insert into paygrade values(3,3000,3999);
insert into paygrade values(4,4000,4999);
insert into paygrade values(5,5000,9999);-- non equi join을 하기 위하여 임의 자료 삽입.

select * from paygrade;

select jikwon_no, jikwon_name, jikwon_pay, grade
from jikwon, paygrade
where jikwon_pay >= lowpay and jikwon_pay <= highpay;

select jikwon_no, jikwon_name, jikwon_pay, grade
from jikwon inner join paygrade
on jikwon_pay >= lowpay and jikwon_pay <= highpay;

select jikwon_no, jikwon_name, jikwon_jik, gogek_name, gogek_tel
from jikwon inner join gogek
on jikwon_no = gogek_damsano;

-- 부서 내 근무자 목록(부서가 없는 직원은 제외)
select buser_name, jikwon_name, jikwon_jik, buser_tel
from jikwon inner join buser
on buser_num = buser_no
order by buser_name asc;

-- 관리고객이 있는 직원만 고객자료(name, tel)
select jikwon_no, jikwon_name, gogek_name, gogek_tel
from jikwon inner join gogek
on jikwon.jikwon_no = gogek.gogek_damsano
order by jikwon_name;

-- 부서명별 급여함
select nvl(buser_name, '임시부') as 부서명, sum(jikwon_pay) as 급여합
from jikwon, buser
where jikwon.buser_num=buser.buser_no(+)
group by buser_name;



-- 문1) 직급이 사원인 직원이 관리하는 고객 출력
-- 출력 ==>  사번   사원명   직급      고객명    고객전화    고객성별
--                  3     한국인   사원       우주인    123-4567       남
select jikwon_no  as 사번, jikwon_name as 사원명, jikwon_jik as 직급, gogek_name as 고객명, gogek_tel as 고객전화, 
decode(substr(gogek_jumin,8,1),1, '남', 2, '여') as 고객성별
-- case when gogek_jumin like '%-1%' then '남'
-- when gogek_jumin like '%-2%' then '여' end as 고객성별
from gogek, jikwon
-- from jikwon inner join gogek on jikwon_no = gogek_damsano where jikwon.jikwon_jik = '사원';
where (jikwon.jikwon_no(+)=gogek.gogek_damsano) and jikwon.jikwon_jik = '사원';

-- 문2) 직원별 고객 확보 수  -- GROUP BY 사용
--    - 모든 직원 참여
select jikwon_name as 직원이름, count(*) as 고객확보수
from jikwon, gogek
-- from jikwon right outer join gogek on jikwon_no = gogek_damsano
where jikwon_no = gogek_damsano
group by jikwon_name;

select jikwon_no as 직원번호, jikwon_name as 직원이름, count(gogek_no) as 고객확보수  -- 다른방법1
from jikwon left outer join gogek 
on jikwon_no = gogek_damsano
group by jikwon_no, jikwon_name;

select jikwon_no as 직원번호, jikwon_name as 직원이름, count(*) as 고객확보수 -- 다른방법2
from jikwon, gogek
-- from jikwon right outer join gogek on jikwon_no = gogek_damsano
where jikwon_no = gogek_damsano
group by jikwon_no, jikwon_name;

-- 문3) 고객이 담당직원의 자료를 보고 싶을 때 즉, 고객명을 입력하면,  담당직원 자료 출력  
--        :    ~ WHERE GOGEK_NAME='강나루'
-- 출력 ==>  직원명       직급
--                한국인       사원

select jikwon_name as 직원명, jikwon_jik as 직급
from jikwon, gogek
-- from jikwon inner join gogek on jikwon_no=gogek_damsano where gogek_name='이영희';
where (jikwon.jikwon_no(+)=gogek.gogek_damsano) and gogek_name='이영희';
    
-- 문4) 직원명을 입력하면 관리고객 자료 출력 
--       : ~ WHERE SAWON_NAME='한국인'
-- 출력 ==> 고객명   고객전화          주민번호           나이
--               강나루   123-4567    700512-1234567        38

select gogek_name as 고객명, gogek_tel as 고객전화, gogek_jumin as 주민번호, 
trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) as 나이
--trunc(months_between(to_char(sysdate,'rrmmdd'),to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) as 나이
--months_between(to_char(sysdate,'yymmdd'), to_date(substr(gogek_jumin,1,6),'yymmdd')) as 나이
--to_char(sysdate,'yyyy') - (substr(gogek_jumin,1,2) + 1900) as 나이
from jikwon, gogek
-- from jikwon inner join gogek on gogek_damsano = jikwon_no where = jikwon_name='홍길동'
where (jikwon.jikwon_no=gogek.gogek_damsano(+)) and jikwon_name='홍길동';

-- 세 개의 테이블 조인
select jikwon_name, buser_name, gogek_name
from jikwon, buser, gogek
where buser_num=buser_no and jikwon_no=gogek_damsano; -- 세 개의 테이블을 조인 할 때는 and를 사용, 여러개 할 때는 계속 and 사용

select jikwon_name, buser_name, gogek_name
from jikwon
inner join buser on buser_num=buser_no
inner join gogek on jikwon_no=gogek_damsano; -- 세 개의 테이블 join 이용 할 때 사용 방법

-- 문1) 총무부에서 관리하는 고객수 출력 (고객 30살 이상만 작업에 참여)
select buser_name as 부서명, count(*) as 고객수
from jikwon
left join buser on buser_num=buser_no
left join gogek on jikwon_no=gogek_damsano
where trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) >= 30 and buser_name = '총무부'
group by buser_name;

-- 문2) 부서명별 고객 인원수 (부서가 없으면 "무소속")
select nvl(buser_name,'무소속') as 부서명, count(*) as 고객수
from jikwon
inner join buser on buser_num=buser_no
right join gogek on jikwon_no=gogek_damsano
group by buser_name;

select nvl(buser_name,'무소속') as 부서명, count(gogek_no) as 고객수
from jikwon
full join buser on buser_num=buser_no
left join gogek on jikwon_no=gogek_damsano
group by buser_name;
 
-- 문3) 고객이 담당직원의 자료를 보고 싶을 때 즉, 고객명을 입력하면     담당직원 자료 출력  
--        :    ~ WHERE GOGEK_NAME='강나루'
--          출력 ==>  
직원명    직급   부서명  부서전화    성별
select jikwon_name as 직원명, jikwon_jik as 직급, buser_name as 부서명, buser_tel as 부서전화, jikwon_gen as 성별
from jikwon
inner join buser on buser_num=buser_no
right join gogek on jikwon_no=gogek_damsano
where  gogek_name='강나루';

-- 문4) 부서와 직원명을 입력하면 관리고객 자료 출력
--        ~ WHERE BUSER_NAME='영업부' AND SAWON_NAME='이순신'
-- 출력 ==>  고객명    고객전화      성별
--                강나루   123-4567       남
select gogek_name as 고객명, gogek_tel as 고객전화,  decode(substr(gogek_jumin,8,1),1, '남', 2, '여') as 고객성별
from jikwon
inner join buser on buser_num=buser_no
left join gogek on jikwon_no=gogek_damsano
where  buser_name='영업부' and jikwon_name='이순신';

-- union : 구조가 일치하는 두 개 이상의 테이블 자료 합쳐보기
create table pum1(bun int, pummok varchar2(10));
insert  into pum1 values(1,'사과');
insert  into pum1 values(2,'포도');
insert  into pum1 values(3,'바나나');
insert  into pum1 values(50,'레몬');

select * from pum1;
drop table pum2;
create table pum2(num int, sangpum varchar2(10));
insert  into pum2 values(10,'수박');
insert  into pum2 values(20,'참외');
insert  into pum2 values(30,'토마토');
insert  into pum2 values(40,'딸기');
insert  into pum2 values(50,'레몬');

select * from pum2;

select bun as 번호, pummok as 상품명 from pum1 -- 칼럼명 갯수는 동일해야 함.
union -- 중복 제거한 모든 것, union all은 중복도 같이 출력
select num, sangpum from pum2;

select bun as 번호, pummok as 상품명 from pum1
minus -- 같은것과, pum2를 제외한 pum1을 출력
select num, sangpum from pum2;

select bun as 번호, pummok as 상품명 from pum1
intersect -- 같은 것만 출력(교집합)
select num, sangpum from pum2;

select jikwon_name from jikwon
union
select gogek_name from gogek;

-- 고객을 관리하는 직원 목록 출력
select jikwon_no, jikwon_name from jikwon
where jikwon_no in(select jikwon_no from jikwon
intersect select distinct gogek_damsano from gogek);

-- 고객을 관리하지 않는 직원 목록 출력
select jikwon_no, jikwon_name from jikwon
where jikwon_no in(select jikwon_no from jikwon -- in은 or다!!
minus select gogek_damsano from gogek);

-- merge : 구조가 일치하는 두 개 이상의 테이블 자료를 병합
--            이미 행이 있다면 갱신, 없다면 추가
create table test1 as select jikwon_no, jikwon_name, 
jikwon_pay from jikwon where jikwon_no <= 10;
-- 기존 테이블을 이용하여 새로운 테이블 만드는 방법, pk는 따라오지 않기 때문에 따로 만들어줘야 함.

select * from test1;

create table test2 as select jikwon_no, jikwon_name, -- merge 하기 위해 일부로 만든 것.
jikwon_pay from jikwon where jikwon_no <= 10 and jikwon_jik='사원';

select * from test2;

insert into test1 values(100,'공기밥',5000); -- 일부로 자료 삽입.
insert into test2 values(200,'소나기',7000);

merge into test1 a using test2 b on(a.jikwon_no=b.jikwon_no)
when matched then update set a.jikwon_name=b.jikwon_name, a.jikwon_pay=b.jikwon_pay
when not matched then insert values(b.jikwon_no, b.jikwon_name,b.jikwon_pay);

select * from test1;

-- sub query : query 속에 query가 있는 형태
-- (보통 안쪽 query의 결과를 바깥쪽 query에서 참조하게 된다)
select * from jikwon;

-- 이순신과 직급이 같은 직원 출력
select jikwon_jik
from jikwon
where jikwon_name='이순신';
select * from jikwon where jikwon_jik='과장';

-- 명령문1 + 명령문2
select * -- 과장에 대한 모든 내용을 출력해줌.
from jikwon
where jikwon_jik=( -- 서브 쿼리는 과장을 넘겨줌.
          select jikwon_jik 
          from jikwon
          where jikwon_name='이순신');

-- 직급이 대리 중 가장 먼저 입사한 사람은?
select min(jikwon_ibsail)
from jikwon
where jikwon_jik='대리';

select *
from jikwon
where jikwon_ibsail='2010/11/04'; -- 2010/11/04 에 입사한 모든 직원을 출력해준 것임. 그렇기 때문에 맞지 않음.

select *
from jikwon
where jikwon_jik='대리' and jikwon_ibsail=( -- 2010/11/04 에 입사했고 직위가 대리인 사람
          select min(jikwon_ibsail)
          from jikwon
          where jikwon_jik='대리'); -- -- 2010/11/04 에 입사한 모든 직원

-- 담당 직원이 '한국남'인 고객 자료 출력
select * 
from gogek
where gogek_damsano=(
          select jikwon_no 
          from jikwon 
          where jikwon_name='한국남');
          
-- 고객 중 '이분'과 나이가 같은 고객 모두를 출력
select *
from gogek
where substr(gogek_jumin,1,2)=(
          select substr(gogek_jumin,1,2)
          from gogek
          where gogek_name='이분');
          
-- 인천에서 근무하는 직원 출력
select * 
from jikwon
where buser_num=(
          select buser_no
          from buser
          where buser_loc='인천');
          
-- 인천 이외에서 근무하는 직원 출력
select * 
from jikwon
where buser_num in( -- 두 개이상의 리턴값는 =으로 받을 수 없음. 두 개이상의 리턴 값은 in을 사용
          select buser_no
          from buser
          where not buser_loc='인천');

-- 2번 직원과 직급이 같고, 직급이 사원인 직원의 평균연봉보다 연봉이 많은 사람을 출력
select jikwon_no, jikwon_name
from jikwon
where jikwon_jik=(
          select jikwon_jik
          from jikwon
          where jikwon_no=2) and jikwon_pay >= (
          select avg(jikwon_pay) 
          from jikwon 
          where jikwon_jik='사원');
          

-- JIKWON, BUSER, GOGEK 테이블을 사용한다.
-- 문1) 2010년 이후에 입사한 남자 중 급여를 가장 많이 받는 직원은?
select jikwon_name as 직원이름
from jikwon
where jikwon_ibsail >= '2010-01-01' and jikwon_gen = '남' and jikwon_pay =(
          select max(jikwon_pay)
          from jikwon
          where jikwon_ibsail >= '2010-01-01' and jikwon_gen = '남');

select * from jikwon;

-- 문2)  평균급여보다 급여를 많이 받는 직원은?
select jikwon_name as 직원이름
from jikwon
where jikwon_pay >(
          select avg(jikwon_pay)
          from jikwon);

-- 문3) '한국남' 직원의 입사 이후에 입사한 직원은?
select jikwon_name as 직원이름
from jikwon
where jikwon_ibsail > (
          select jikwon_ibsail
          from jikwon
          where jikwon_name='한국남');

-- 문4) 2000 ~ 2010 사이에 입사한 총무부,영업부,전산부 직원 중 급여가 가장 적은 사람은?
--       (직급이 NULL인 자료는 작업에서 제외)
          
                                                                                                            
select jikwon_name as 직원이름 
from jikwon inner join buser 
on buser_num = buser_no 
where jikwon_jik is not null and buser_name in ('총무부','영업부','전산부') and  (jikwon_ibsail between '2000-01-01' and '2010-12-31') and jikwon_pay = (
                  select min(jikwon_pay) 
                  from jikwon inner join buser 
                  on buser_num = buser_no
                  where buser_name in ('총무부','영업부','전산부') and ( jikwon_ibsail between '2000-01-01' and '2010-12-31'));

-- 문5) 한국남, 이순신과 직급이 같은 사람은 누구인가?
select jikwon_name as 직원이름
from jikwon
where jikwon_jik in(
          select jikwon_jik 
          from jikwon
          where jikwon_name in ('이순신','한국남'));

-- 문6) 과장 중에서 최대급여, 최소급여를 받는 사람은?
select jikwon_name as 최대최소급여직원
from jikwon
where jikwon_pay =(
          select max(jikwon_pay)
          from jikwon
          where jikwon_jik='과장')
union
select jikwon_name as 최대최소급여직원
from jikwon
where jikwon_pay =(
          select min(jikwon_pay)
          from jikwon
          where jikwon_jik='과장');
          
select * 
from jikwon 
where jikwon_jik = '과장' and
jikwon_pay in (
                (select min(jikwon_pay) from jikwon where jikwon_jik = '과장'),
                (select max(jikwon_pay) from jikwon where jikwon_jik = '과장'));

-- 문7) 20번 부서의 최소급여보다 많은 사람은?
select jikwon_name as 직원이름
from jikwon
where jikwon_pay > (
          select min(jikwon_pay)
          from jikwon
          where buser_num=20);
          
select * from jikwon;

-- 문8) 30번 부서의 평균급여보다 급여가 많은 '대리' 는 몇명인가?
select jikwon_name as 직원이름
from jikwon
where jikwon_jik = '대리' and jikwon_pay >(
          select avg(jikwon_pay)
          from jikwon
          where buser_num=30);

-- 문9) 고객을 확보하고 있는 직원들의 이름, 직급, 부서명을 입사일 별로 출력하라.
select jikwon_name as 직원이름, jikwon_jik as 직급, buser_name as 부서명, jikwon_ibsail as 입사일
from jikwon left join buser 
on buser_num = buser_no
where jikwon_no in(
          select gogek_damsano
          from gogek)
order by jikwon_ibsail asc;

-- 문10) 이순신과 같은 부서에 근무하는 직원과 해당 직원이 관리하는 고객 출력
-- (고객은 나이가 30 이하면 '청년', 40 이하면 '중년', 그 외는 '노년'으로 표시하고, 고객 연장자 부터 출력)
-- 출력 ==>  직원명    부서명     부서전화     직급      고객명    고객전화    고객구분
--                한송이    총무부     123-1111    사원      백송이    333-3333    청년   
select jikwon_name as 직원명, buser_name as 부서명, buser_tel as 부서전화, jikwon_jik as 사원, gogek_name as 고객명, gogek_tel as 고객전화,
case
when trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) <= 30 then '청년'
when trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) <= 40 then '중년'
else '노년' end as 고객구분
from jikwon
inner join buser on buser_num=buser_no
left join gogek on jikwon_no=gogek_damsano
where buser_num in(
          select buser_num
          from jikwon
          where jikwon_name='이순신')
order by trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) desc;

-- 총무부 내 근무직원들이 관리하는 고객 출력
-- subquery
select gogek_no, gogek_name, gogek_tel 
from gogek
where gogek_damsano in(
          select jikwon_no
          from jikwon
          where buser_num=(
                    select buser_no
                    from buser
                    where buser_name='총무부'));
                    
-- join
select gogek_no, gogek_name, gogek_tel 
from gogek
inner join jikwon on jikwon_no=gogek_damsano
inner join buser on buser_num=buser_no
where buser_name='총무부';

-- any / all
-- < any : subquery의 반환값 중 최대값 보다 작은 ~
-- > any : subquery의 반환값 중 최대값 보다 큰 ~
-- < all : subquery의 반환값 중 최소값 보다 작은 ~
-- > all : subquery의 반환값 중 최대값 보다 큰 ~

-- 대리의 최대값 보다 작은 연봉을 받는 직원 출력
select jikwon_no, jikwon_name, jikwon_pay 
from jikwon
where jikwon_pay < any (
          select jikwon_pay 
          from jikwon 
          where jikwon_jik='대리');
          
-- 30번 부서의 최고 급여자 보다 급여가 많은 직원 출력
select jikwon_no, jikwon_name
from jikwon
where jikwon_pay > all (
          select jikwon_pay 
          from jikwon
          where buser_num=30);
          
-- 상관서브쿼리(잘 사용하지는 않으나, 알아두기는 할 것)
-- 안쪽 query가 바깥쪽 쿼리를 참조하고 그 결과를 바깥쪽에 query에서 참조하는 형태

-- 각 부서의 최대 급여자는?
select * 
from jikwon a
where a.jikwon_pay = (
          select max(b.jikwon_pay) 
          from jikwon b 
          where a.buser_num=b.buser_num) -- 여기서 바깥쪽 쿼리를 참조하고 있음.
order by a.buser_num asc;

-- 급여순위 3위 이내의 자료 출력(내림차순)
select a.jikwon_name, a.jikwon_pay
from jikwon a
where (select count(*) from jikwon b where a. jikwon_pay < b.jikwon_pay) < 3
-- b는 전체
-- a는 참조
-- b보다 a가 적을 때를 전부확인 후 
-- 행에 갯수 보다많은 3개를 출력
and jikwon_pay is not null
order by jikwon_pay desc;

-- inline view : from 절에 sub query
-- 전체 평균급여와 최대급여 사이의 급여를 받는 직원출력
select jikwon_no, jikwon_name, jikwon_pay
from jikwon a, (select avg(jikwon_pay) as avgs, max(jikwon_pay) as maxs from jikwon) b
where a. jikwon_pay between b.avgs and b.maxs;

-- 각 부서별로 최고급여를 받는 직원은?
select a.jikwon_no, a.jikwon_name, a.jikwon_pay
from jikwon a, ( select buser_num, max(jikwon_pay) as maxpay
                      from jikwon group by buser_num) b
where a.buser_num=b.buser_num and a.jikwon_pay = b.maxpay;

-- having 절에 subquery
-- 부서별 평균급여 중 30번 부서의 평균급여보다 급여가 많은 자료 출력
select buser_num, avg(jikwon_pay)
from jikwon
group by buser_num
having avg(jikwon_pay) > (
          select avg(jikwon_pay)
          from jikwon
          where buser_num=30);
          
-- exists 연산자 : 서브쿼리의 결과 유무를 확인
-- 직원이 있는 부서 출력
select buser_name, buser_loc
from buser bu
where exists ( -- not exists는 반대
          select 'imsi' 
          from jikwon
          where buser_num=bu.buser_no);
-- 직원이 없는 부서 출력          
select buser_name, buser_loc
from buser bu
where not exists (
          select 'imsi' 
          from jikwon
          where buser_num=bu.buser_no);
          
-- sub query를 이용한 table 생성, 자료의 추가, 수정, 삭제
create table sa1 as select * from jikwon; -- 기본키를 제외한 나머지 테이블과 자료들을 만듬.
select * from sa1;
desc sa1;

create table sa2 as select * from jikwon where 1 = 0; -- 조건에 맞는 것이 없기 때문에 구조만 만들어짐.
select * from sa2;

insert into sa2 select * from jikwon where jikwon_jik='과장'; -- 서브 쿼리를 이용해 조건에 맞게 테이블 자료를 복사해서 넣어줌.
insert into sa2(jikwon_no, jikwon_name, buser_num)
select jikwon_no, jikwon_name, buser_num from jikwon where jikwon_jik='대리'; -- 만들 칼럼을 제외한 나머지는 null로 만듬.
select * from sa2;

create table sa3 as select jikwon_no, jikwon_name, jikwon_pay from jikwon where 1 = 0;
desc sa3;

-- 복수테이블에 복수 칼럼 insert
create table sa4 as select jikwon_no, jikwon_name, jikwon_jik from jikwon where 1 = 0;
create table sa5 as select jikwon_no, jikwon_name, jikwon_pay, jikwon_gen from jikwon where 1 = 0;

select * from sa4;
select * from sa5;

insert all -- 다른 테이블에 대한 자료를 복사하여 만드는 테이블에 한꺼번에 집어 넣고 싶을 때
into sa4 values(jikwon_no, jikwon_name,jikwon_jik)
into sa5 values(jikwon_no, jikwon_name,jikwon_pay,jikwon_gen)
select jikwon_no, jikwon_name, jikwon_jik,jikwon_pay,jikwon_gen
from jikwon
where buser_num = 10;

select * from sa4;
select * from sa5;

-- 조건에 따른 insert
insert all 
when jikwon_jik='사원' then
into sa4 values(jikwon_no, jikwon_name,jikwon_jik)
when jikwon_gen='여' then
into sa5 values(jikwon_no, jikwon_name,jikwon_pay,jikwon_gen)
select jikwon_no, jikwon_name, jikwon_jik,jikwon_pay,jikwon_gen
from jikwon
where buser_num in (20, 30);

-- update
select * from sa1;

update sa1 set jikwon_jik = ( -- 사장에 대한 직위가 부장으로 바뀜.
          select jikwon_jik -- 부장
          from jikwon
          where jikwon_name='한국남')
where jikwon_no=1;

delete from sa1 where jikwon_no in ( -- 고객을 관리하고 있는 사람을 삭제
          select distinct gogek_damsano  
          from gogek);
          
-- rownum(행이 읽혀진 순서)을 이용해 tom-n(행번호를 알 수 있음)을 할 수 있다.
-- (My-sql의 limit)
-- 연산자는 <, <= 만 사용
select rownum, jikwon_name, jikwon_pay from jikwon;

-- 급여 순위 3위 이내 출력
select rownum, jikwon_no, jikwon_name, jikwon_pay
from (
        select * 
        from jikwon
        where jikwon_pay is not null -- null을 제외
        order by jikwon_pay desc)
where rownum <= 3;

-- 가장 최근에 입사한 직원 5위 이내 출력
select rownum, jikwon_no, jikwon_name, jikwon_ibsail
from (
        select *
        from jikwon
        order by jikwon_ibsail desc)
where rownum <= 5;

-- transaction : 단위별 데이터 처리
-- DML 수행 후 commit, rollback에 단위별 데이터 처리가 종료.
select * from sa1;
delete from sa1 where jikwon_no=8; -- delete 했다고 완전히 삭제 된 것은 아님
commit; -- commit이 되야지 완전히 삭제 됨.
delete from sa1 where jikwon_no=10;
select * from sa1;
rollback; -- delete 한 자료를 다시 되돌림. commit 되지 않았기 때문에 rollback이 가능. 
-- 삭제, 갱신, 삽입 등은 Log 파일에 기록 됨. 그래서 commit, rollback을 했을 경우 원본파일을 저장 및 되돌리기가 가능 한 것.

-- transaction 예
update sa1 set jikwon_name = '하하' where jikwon_no=11;
savepoint a; -- rollback 시점을 저장
update sa1 set jikwon_name = '호호' where jikwon_no=13;
rollback to savepoint a; -- savepoint a 의 rollback 시점으로 돌아감
select * from sa1;
rollback; -- commit 시점으로 돌아감.
update sa1 set jikwon_name = '허허' where jikwon_no=14;
commit;
select * from sa1;
update sa1 set jikwon_name = '관우' where jikwon_no=10; -- transaction 시작 / 마무리 짓지 않음
-- cmd.exe에서 delete 진행 안됨(transaction 시작되없기 때문에) <- 이렇게 될 경우 Deadlock락 걸림
rollback;

-- view file : 물리적인 테이블을 근거로 논리적인 가상 테이블 작성
-- select 문의 조건을 파일로 만들어 테이블 처럼 사용
-- 장점 : 복잡하고 긴 select 문을 view로 단순화 할 수 있다.
--          보안을 강화 할 수 있다.
--          자료의 독립성을 확보 할 수 있다.
--          물리적인 공간을 차지하지 않는다.
-- 형식 : create [or replace] view 뷰파일명 as select문
-- or replace는 덮어쓰기 가능.
create table vjikwon as select * from jikwon;
select * from vjikwon;

select jikwon_no, jikwon_name, jikwon_pay
from vjikwon
where jikwon_ibsail >= '2005-01-01';

-- 관리자 계정으로 create view에 대한 권한을 scott에게 부여.
-- grant create view to scott : create view 권한부여 명령어

create or replace view v_a as -- scott 계정이 뷰에 대한 계정이 없어서 못만듬. 권한부여 후 만들면 가능.
select jikwon_no, jikwon_name, jikwon_pay
from vjikwon
where jikwon_ibsail >= '2005-01-01';

select * from v_a;
desc v_a;
select sum(jikwon_pay) from v_a;
select view_name, text from user_views; -- 뷰의 만든 명령어, 내용을 볼 수 있음.

create view v_b as 
select * 
from vjikwon
where jikwon_name like '김%' or jikwon_name like '박%';

select * from v_b;

select jikwon_name as 이름, jikwon_pay as 연봉
from v_b;

rename vjikwon to myjikwon; -- 테이블명 변경
select * from v_a; -- 이름을 변경 했기 때문에 참조 할 물리적인 테이블이 없어서 나오지 않음.
select * from v_b;
rename myjikwon to vjikwon;

-- drop view ~ -- 뷰 삭제
-- alter view ~ -- 뷰 설정변경

create view v_c as 
select * 
from vjikwon
order by jikwon_pay desc;

select * from v_c;

create view v_d as
select jikwon_name, jikwon_pay * 10000 as ypay-- 수식을 사용 할 경우 뷰에서는 별명을 지어줘야 함.
from vjikwon;

select * from v_d;

select * from v_d where ypay >= 60000000;

create view v_e as 
select *
from v_d -- view로 view를 작성 할 수 있음.
where ypay >= 60000000;

select * from v_e;

update v_e set jikwon_name='김밥' -- v_e에서 update하면 연속적으로 연결된 뷰와 테이블도 변경됨.
where jikwon_name = '한국남'; 

select * from v_e;
select * from v_d;
select * from vjikwon; 

delete from v_d where jikwon_name='홍길동'; -- 삭제도 동일. delete하면 연달아 연결된 테이블도 삭제됨.
select * from v_d;
select * from vjikwon; 

update v_d set ypay = 770000000 -- 가상테이블, 컬럼이기 때문에 가상컬럼을 이용해 변경 할 수 없음.
where jikwon_name='김밥';

update vjikwon set jikwon_pay=1234 where jikwon_name='김밥'; -- 물리적데이블을 참조한 테이블에 내용을 변경하면 논리적 테이블도 같이 바뀜.
select * from v_d;

create or replace view v_f as
select jikwon_no, jikwon_name, buser_num, jikwon_pay
from vjikwon
order by jikwon_no asc; -- create 할 때 정렬도 가능.

select * from v_f;

insert into v_f values(1,'신기해',10,5000); -- 뷰에 삽입 가능.

select * from v_f;
select * from vjikwon; -- 삽입도 뷰에서 삽입해도 물리적테이블도 삽입됨.

create or replace view v_f as
select jikwon_no, jikwon_name, buser_num, jikwon_pay
from vjikwon
where jikwon_pay >= 5000
order by jikwon_no asc;

insert into v_f values(20,'신기루',20,5500);
insert into v_f values(21,'지루해',30,4500); -- 물리적테이블에는 들어갔지만 뷰 테이블에 대한 조건 pay가 5000 이상이기 때문에 조건을 확인하고 보여줌.

select * from v_f;
 
create or replace view v_g as
select jikwon_jik as jik, sum(jikwon_pay) as hap, round(avg(jikwon_pay),1) as ave
from vjikwon
group by jikwon_jik;

select * from v_g;
select * from v_g where ave >=5000;
select * from v_g where jikwon_jik = '대리'; -- view에 jikwon_jik라는 컬럼이 없음
select * from v_g where jik = '대리'; -- view에 있는 별명컬럼을 사용하면 가능.

-- view가 집합함수, group by, distinct .... 을 포함한 경우
-- 행을 수정, 삭제 할 수 없다.

create or replace view v_h as
select jikwon_no, jikwon_name, buser_name, jikwon_jik
from vjikwon inner join buser -- join에 의한 뷰도 만들 수 있음.
on buser_num=buser_no
where buser_num in (10, 30);

select * from v_h;
 
 
-- 문1) 사번  이름    부서  직급  근무년수  고객확보
--      1   홍길동  영업부 사원     6    O   or  X
-- 조건 : 직급이 없으면 임시직, 전산부 자료는 제외
-- 위의 결과를 위한 뷰파일 v_exam1을 작성
create or replace view v_exam1 as
select jikwon_no as 사번, jikwon_name as 이름, buser_name as 부서, nvl(jikwon_jik,'임시직') as 직급, round((sysdate - jikwon_ibsail)/365) as 근무년수, 
case 
when jikwon_no in (
         select distinct gogek_damsano
         from gogek) 
then 'O'
else 'X' end as 고객확보
from vjikwon 
left join buser on buser_num=buser_no
where buser_name in ('총무부', '관리부', '영업부') or buser_name is null
order by jikwon_no asc;

select * from v_exam1;

-- 문2) 부서명   인원수
--     영업부     7
-- 조건 : 직원수가 가장 많은 부서 출력
-- 위의 결과를 위한 뷰파일 v_exam2을 작성
-- 방법 1
create or replace view v_exam2 as
select buser_name as 부서명, count(*) as 인원수
from vjikwon
inner join buser on buser_num=buser_no
group by buser_name
order by 인원수 desc;

select * from v_exam2 where rownum <= 1;

-- 방법 2
create or replace view v_exam2 as
select buser_name as 부서명, count(*) as 인원수
from vjikwon 
inner join buser on buser_num=buser_no
where buser_name = (
                      select 부서명
                      from(
                            select buser_name as 부서명, count(*) as 인원수
                            from jikwon inner join buser on buser_num=buser_no
                            group by buser_name
                            order by 인원수 desc
                      )
                      where rownum <=1
                      )
group by buser_name;
--having buser_num > all (select conut(buser_num) from jikwon right join buser group by buser_name);
       
select * from v_exam2;

-- 문3) 가장 많은 직원이 입사한 요일에 입사한 직원 출력
--   직원명   요일     부서명   부서전화
--   한국인  수요일    전산부   222-2222
-- 위의 결과를 위한 뷰파일 v_exam3을 작성  
-- select to_char(sawon_ibsail,'DAY') from sawon;
-- 방법 1
create or replace view v_exam3 as
select jikwon_name as 직원명, to_char(jikwon_ibsail,'DAY') as 요일, buser_name as 부서명, buser_tel as 부서전화
from vjikwon 
inner join buser on buser_num=buser_no;

select * 
from v_exam3 
where 요일 = (
                select to_char(jikwon_ibsail,'DAY') 
                from vjikwon 
                group by to_char(jikwon_ibsail,'DAY')
                having count(to_char(jikwon_ibsail,'DAY')) >= all ( -- having count(월~일)에 대해 하나씩 all에 있는 것을 모두 비교.
                                                  select count(to_char(jikwon_ibsail,'DAY')) 
                                                  from vjikwon 
                                                  group by to_char(jikwon_ibsail,'DAY')));
-- any / all
-- < any : subquery의 반환값 중 최대값 보다 작은 ~
-- > any : subquery의 반환값 중 최대값 보다 큰 ~
-- < all : subquery의 반환값 중 최소값 보다 작은 ~
-- > all : subquery의 반환값 중 최대값 보다 큰 ~                                                  

-- 방법 2
create or replace view v_exam3 as
select jikwon_name as 직원명, to_char(jikwon_ibsail,'DAY') as 요일, buser_name as 부서명, buser_tel as 부서전화
from vjikwon left join buser on buser_num=buser_no
where to_char(jikwon_ibsail,'DAY') = (
                                      select 요일 --view로 만들었기 때문에 이것 또한 뷰. 안에서는 참조 가능하지만 밖에는 물리적이기 때문에 밖에서는 참조 될 수 없음.
                                      from (
                                            select to_char(jikwon_ibsail,'DAY') as 요일, count(*) as 인원수
                                            from vjikwon
                                            group by to_char(jikwon_ibsail,'DAY')
                                            order by 인원수 desc)
                                      where rownum <= 1
                                      );

select * from v_exam3;

-- 계정(사용자) 생성 및 권한, 보안
-- 관리자가 각 client에게 별도의 계정을 주고, 각 계정으로 DB자료를 공유
-- 형식)
-- create user 계정명 identified by 비밀번호
-- drop user 계정명 [cascade]
-- alter user 계정명 identified by 비밀번호

-- system cmd
-- SQL> show user;
-- USER은 "SYSTEM"입니다
-- SQL> select * from all_users; -- 모든 계정을 볼 수 있음.

-- system cmd
-- SQL> create user kbs identified by 111; -- 사용자 계정 만들기
-- 사용자가 생성되었습니다.

-- kbs cmd
-- ERROR: -- kbs 로그인 권한주지 않아 에러가 남. 
-- ORA-01045: user KBS lacks CREATE SESSION privilege; logon denied

-- system cmd
-- SQL> grant connect to kbs; -- 로그인 권한 주기.
-- 권한이 부여되었습니다. 

-- kbs cmd
-- SQL> create table aa(bun int, -- create 권한이 없기 때문에 삽입 할 수 없음.
--  2  irum varchar2(10));
-- 1행에 오류:
-- ORA-01031: 권한이 불충분합니다

-- system cmd
-- SQL> grant resource to kbs;
-- 권한이 부여되었습니다.

-- resource 권한 : 기본적인 객체(table,trigger,index,cluster,sequence등)를 drop,alter,create, 
--                 컬럼을 insert,update,delete 할 수 있는 권한을 모아놓은 role 


-- kbs cmd
-- SQL> show user; -- 권한 부여 후  재접속 후 테이블 생성
-- USER은 "KBS"입니다
-- SQL> create table aa(bun int,
--   2  irum varchar2(10));
-- 테이블이 생성되었습니다.

-- kbs cmd
-- SQL> insert into aa values(1, 'tom'); -- insert에 대한 권한부여가 되지 않음.
-- insert into aa values(1, 'tom')
-- 1행에 오류:
-- ORA-01950: 테이블스페이스 'USERS'에 대한 권한이 없습니다. -- 인터넷에 오류 검색하면 다 나옴. 찾아서 사용 할 것.

-- system cmd
-- SQL> alter user kbs default tablespace users quota unlimited on users; -- users에 대한 권한 부여
-- 사용자가 변경되었습니다.

-- kbs cmd
-- SQL> insert into aa values(1, 'tom');
-- 1 개의 행이 만들어졌습니다.

-- kbs cmd
-- SQL> commit; -- 만들었으니 커밋
-- 커밋이 완료되었습니다.

-- kbs cmd
-- SQL> select * from aa;
--       BUN IRUM
------------ --------------------
--         1 tom

-- kbs cmd
-- SQL> select * from session_privs; -- 현재 계정에 대한 권한 확인.
--
-- PRIVILEGE
----------------------------------------------------------------------------------
-- SET CONTAINER
-- CREATE INDEXTYPE
-- CREATE OPERATOR
-- CREATE TYPE
-- CREATE TRIGGER
-- CREATE PROCEDURE
-- CREATE SEQUENCE
-- CREATE CLUSTER
-- CREATE TABLE
-- CREATE SESSION
--
-- 10 행이 선택되었습니다.

-- system cmd
-- SQL> create user mbc identified by 111; -- mbc 사용자 계정 만듬.
-- 사용자가 생성되었습니다.

-- system cmd
-- SQL> grant connect, resource to mbc; -- mbc 권한 부여.
-- 권한이 부여되었습니다.

-- mbc cmd
-- SQL> show user;
-- USER은 "MBC"입니다

-- scott cmd
-- SQL> select jikwon_name
--   2  from scott.vjikwon
--
-- JIKWON_NAME
----------------------
-- 김밥
-- 이순신
-- 이미라
-- 이순라
-- 김이화
-- 김부만
-- 김기만
-- 채송화
-- 박치기
-- 김부해
-- 박별나
--
-- JIKWON_NAME
----------------------
-- 박명화
-- 박궁화
-- 채미리
-- 이유가
-- 신기해
-- 신기루
-- 지루해
--
-- 18 행이 선택되었습니다.

-- system cmd
-- SQL> select * from scott.vjikwon;
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--          2 김밥                         20 부장                       1234
-- 03/12/03 남
--
--          3 이순신                       20 과장                       6500
-- 11/03/03 남
--
--          4 이미라                       30 대리                       5500
-- 10/11/04 여
--
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--          5 이순라                                                     3000
-- 13/08/05 여
--
--          6 김이화                       20 사원                       2950
-- 14/08/05 여
--
--          7 김부만                       40 부장                       8000
-- 04/01/05 남
--
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--          8 김기만                       20 과장                       7000
-- 09/01/01 남
--
--          9 채송화                       30 대리                       5500
-- 11/03/02 여
--
--         10 박치기                       10 사원                       3700
-- 14/03/02 남
--
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--         11 김부해                       30 사원                       2900
-- 15/09/06 남
--
--         12 박별나                       40 과장                       5300
-- 10/03/05 여
--
--         13 박명화                       10 대리                       4900
-- 12/05/01 남
--
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--         14 박궁화                       40 사원                       2800
-- 17/01/05 여
--
--         15 채미리                       20 사원                       3200
-- 15/01/03 여
--
--         16 이유가                       10 사원                       3900
-- 13/02/01 여
--
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--          1 신기해                       10                            5000
--
--
--         20 신기루                       20                            5500
--
--
--         21 지루해                       30                            4500
--
-- 18 행이 선택되었습니다.

-- kbs cmd
-- SQL> select jikwon_name from scott.vjikwon; -- 권한이 없기 때문에 볼 수 없음.
-- select jikwon_name from scott.vjikwon
-- 1행에 오류:
-- ORA-00942: 테이블 또는 뷰가 존재하지 않습니다

-- scott cmd
-- SQL> select * from kbs.aa;
-- select * from kbs.aa
-- 1행에 오류:
-- ORA-00942: 테이블 또는 뷰가 존재하지 않습니다

-- kbs cmd
-- SQL> grant select on aa to scott; -- 한꺼번에 불가능. 테이블 단위로 가능하며, 혜당 계정에서 권한 부여 할 수 있음.
-- 권한이 부여되었습니다.

-- scot cmd
-- SQL> insert into kbs.aa values(3, 'james');
-- insert into kbs.aa values(3, 'james')
-- 1행에 오류:
-- ORA-01031: 권한이 불충분합니다

-- kbs cmd
-- SQL> grant insert on aa to scott; -- insert 권한 부여.
-- 권한이 부여되었습니다.

-- scott cmd
-- SQL> insert into kbs.aa values(3, 'james'); -- scott에서 kbs에 삽입.
-- 1 개의 행이 만들어졌습니다.

-- SQL> select * from kbs.aa;
--
--        BUN IRUM
------------ --------------------
--          1 tom
--          3 james
         
-- kbs cmd
-- SQL> grant select on aa to scott, mbc; -- 권한부여는 콤마로 구분해서 여러명에게 부여 할 수 있음.
-- 권한이 부여되었습니다.

-- mbc cmd
-- SQL> select * from kbs.aa;
--
--        BUN IRUM
------------ --------------------
--          1 tom

-- kbs cmd 
-- SQL> revoke select on aa from mbc; -- 권한부여 취소.
-- 권한이 취소되었습니다.

-- kbs cmd
-- SQL> grant all on aa to scott; -- 모든 권한(DML)에 대해 모두 scott에게 줌.
-- 권한이 부여되었습니다.

-- kbs cmd
-- SQL> revoke all on aa from scott; -- 모든 권한(DML)에 대해 취소.
-- 권한이 취소되었습니다.

-- system cmd
-- SQL> create role insa; -- 롤로 권하부여 할 수 있음.. insa는 롤에 변수명.
-- 롤이 생성되었습니다.

-- system cmd
-- SQL> grant insa to kbs;
-- 권한이 부여되었습니다.

-- system cmd
-- SQL> create role insa;
-- 롤이 생성되었습니다.

-- system cmd
-- SQL> grant insa to scott;
-- 권한이 부여되었습니다.

-- systme cmd 
-- SQL> grant insa to mbc, kbs;
-- 권한이 부여되었습니다.

-- scott cmd
-- SQL> select * from kbs.aa;
-- select * from kbs.aa
-- 1행에 오류:
-- ORA-00942: 테이블 또는 뷰가 존재하지 않습니다

-- SQL> select * from kbs.aa;
--     BUN IRUM
---------- --------------------
--        1 tom

-- mbc cmd
-- SQL> select * from kbs.aa;
-- select * from kbs.aa
-- 1행에 오류:
-- ORA-00942: 테이블 또는 뷰가 존재하지 않습니다

-- kbs cmd
-- SQL> grant selete,delete on aa to insa;

-- scott cmd
-- select * from kbs.aa; -- 하면 나옴.
-- mbc cmd
-- select * from kbs.aa; -- 하면 나옴.

-- kbs cmd
-- SQL> revoke all on aa from insa; -- role에 대한 권한 취소
-- 권한이 취소되었습니다.

-- system cmd
-- SQL> revoke insa from mbc,kbs;
-- 권한이 취소되었습니다.

-- system cmd
-- SQL> drop user mbc; -- 접속 중이면 삭제 불가능. 종료 후 가능.
-- drop user mbc
-- 1행에 오류:
-- ORA-01940: 현재 접속되어 있는 사용자는 삭제할 수 없습니다

-- system cmd
-- SQL> drop user mbc;
-- 사용자가 삭제되었습니다.

-- system cmd
-- SQL> drop user kbs;
-- drop user kbs
-- 1행에 오류:
-- ORA-01940: 현재 접속되어 있는 사용자는 삭제할 수 없습니다

-- system cmd
-- SQL> drop user kbs;
-- drop user kbs
-- 1행에 오류:
-- ORA-01922: 'KBS'(을)를 삭제하려면 CASCADE를 지정하여야 합니다 -- casecad : 권한에 대한 연쇄 삭제

-- system cmd
-- SQL> drop user kbs cascade;
-- 사용자가 삭제되었습니다.

-- synonym(동의어)

--
---------------------
-- Synonym(동의어)
---------------------
--
-- A. 개념
-- - 동의어(Synonym)은 Table, View, SnapShot,Sequence,Procedure, Function, Package에 대한 별칭이다.
-- - 공용 및 전용 동의어의 두가지 종류가 있다. 공용동의어는 public이라는 특정사용자 그룹에서
--   소유하며 DB의 모든 사용자가 사용할수 있다. 전용동의어는 다른 사용자에 대해 전용동의어의 
--   가용성을 제어할수 있는 특정 사용자의 스키마에 들어있다.
-- B. 생성
-- - 예를들어 scott의 Schema에 포함된 Emp Table에 대해 puiblic_emp라는 공용 Synonym생성
-- - Create public synonym public_emp for scott.emp;
-- - 이상과 같이 공용으로 생성하면 Oracle의 다른 사용자는 public_emp라는 별칭을 사용하여 Query 할수있다.
-- - tiger라는 User는 public_emp라는 별칭을 사용하여 다음과 같이 Query 할수있다.
-- Sqlplus>select * from public_emp;
-- C. 삭제
-- - drop public synonym public_emp
--
-------------------------------------------------------------------------
-- 만약 Synonym이 전용이라면 생성과 삭제시 public이라는 Option을 안쓰면 된다.
 

-- system cmd 
-- SQL> show user
-- USER은 "SYSTEM"입니다

-- scott cmd
-- SQL> show user;
-- USER은 "SCOTT"입니다

-- system cmd
-- SQL> create user tom identified by 111;
-- 사용자가 생성되었습니다.

-- SQL> grant connect, resource to tom;
-- 권한이 부여되었습니다.

-- tom cmd
-- SQL> show user
-- USER은 "TOM"입니다

-- scott cmd
-- SQL> grant select on jikwon to tom;
-- 권한이 부여되었습니다.

-- tom cmd
-- SQL> create synonym nice for scott.jikwon;
-- create synonym nice for scott.jikwon
-- *
-- 1행에 오류:
-- ORA-01031: 권한이 불충분합니다

-- tom cmd
-- SQL> select * from user_role_privs; -- 보면 synonym에 대한 권한이 없음.

-- USERNAME
----------------------------------------------------------------------------------
-- GRANTED_ROLE
----------------------------------------------------------------------------------
-- ADMIN_ DELEGA DEFAUL OS_GRA COMMON
-------- ------ ------ ------ ------
-- TOM
-- CONNECT
-- NO     NO     YES    NO     NO
--
-- TOM
-- RESOURCE
-- NO     NO     YES    NO     NO
--
-- USERNAME
----------------------------------------------------------------------------------
-- GRANTED_ROLE
----------------------------------------------------------------------------------
-- ADMIN_ DELEGA DEFAUL OS_GRA COMMON
-------- ------ ------ ------ ------

-- system cmd
-- SQL> grant create synonym to tom; -- private
-- 권한이 부여되었습니다.

-- SQL> grant create public synonym to tom; -- public
-- 권한이 부여되었습니다.

-- tom cmd
-- SQL> create synonym nice for scott.jikwon; -- private 동의어 생성
-- 동의어가 생성되었습니다.

-- SQL> create public synonym pnice for scott.jikwon; -- public 동의어 생성
-- 동의어가 생성되었습니다.

-- SQL> select jikwon_name from nice; -- private
--
-- JIKWON_NAME
----------------------
-- 홍길동
-- 김밥
-- 이순신
-- 이미라
-- 이순라
-- 김이화
-- 김부만
-- 김기만
-- 채송화
-- 박치기
-- 김부해
--
-- JIKWON_NAME
----------------------
-- 박별나
-- 박명화
-- 박궁화
-- 채미리
-- 이유가
--
-- 16 행이 선택되었습니다.
--
-- SQL> select jikwon_name from pnice; -- public
--
-- JIKWON_NAME
----------------------
-- 홍길동
-- 김밥
-- 이순신
-- 이미라
-- 이순라
-- 김이화
-- 김부만
-- 김기만
-- 채송화
-- 박치기
-- 김부해
-- 
-- JIKWON_NAME
----------------------
-- 박별나
-- 박명화
-- 박궁화
-- 채미리
-- 이유가
-- 
-- 16 행이 선택되었습니다.

-- system cmd
-- SQL> create user oscar identified by 111;
--사용자가 생성되었습니다.

-- SQL> grant connect, resource to oscar;
-- 권한이 부여되었습니다.

-- scott cmd
-- SQL> grant select on jikwon to oscar; -- select 권한 주어야지 됨.
-- 권한이 부여되었습니다.

-- oscar cmd
-- SQL> select jikwon_name from pnice; -- public으로 되어있기 때문에 tom이 만든 것도 볼 수 있음. 하지만 nice는 private이기 때문에 안보여짐.
--
-- JIKWON_NAME
----------------------
-- 홍길동
-- 김밥
-- 이순신
-- 이미라
-- 이순라
-- 김이화
-- 김부만
-- 김기만
-- 채송화
-- 박치기
-- 김부해
--
-- JIKWON_NAME
----------------------
-- 박별나
-- 박명화
-- 박궁화
-- 채미리
-- 이유가
--
-- 16 행이 선택되었습니다.

-- tom cmd 
-- SQL> drop synonym nice; -- 다른 사람이 사용하지 않고 있기 때문에 삭제 가능.
-- 동의어가 삭제되었습니다.
--
-- SQL> drop synonym pnice; -- 다른 사람이 사용하고 있기 때문에 삭제 불가능.
-- drop synonym pnice
-- 1행에 오류:
-- ORA-01434: 삭제할 비공개 동의어가 존재하지 않습니다.

-- system cmd
-- SQL> drop public synonym pnice; -- public은 system에 와서 삭제해야 됨.
-- 동의어가 삭제되었습니다.

-- 문제)
-- 출력 : buser_no   buser_name  inwon
--         10          총무부        2
--         40          관리부       1
-- 조건 : scott 계정에서 근무 인원이 3명 이하인 부서를 대상으로 v_account 뷰를 작성.
--        mbc, kbs 계정(암호는 둘 다 p123)을 만들어 broadcast 롤에  등록 한 후,
--        이 들 계정이 v_account 뷰를 사용할 수 있도록 하시오.
--        또 mbc 계정에는 happy라는 비공개 synonym을 만들어 사용하시오.

create or replace view v_account as
select buser_no, buser_name, count(*) as inwon
from jikwon 
inner join buser on buser_num=buser_no
where buser_name in (
                      select 부서명
                      from(
                            select buser_name as 부서명, count(*) as 인원수
                            from jikwon inner join buser on buser_num=buser_no
                            group by buser_name
                            order by 인원수 desc
                      )
                      where 인원수 <= 3
                      )
group by buser_no, buser_name
order by inwon desc;

drop view v_account;

create or replace view v_account as
select buser_no, buser_name, count(jikwon_no) as inwon
from buser
left join jikwon on buser_num=buser_no 
group by buser_no, buser_name
having count(jikwon_no) <= 3
order by inwon desc;

select * from v_account;

commit;

-- 전체 cmd

-- system cmd
-- SQL> create user mbc identified by p123;
-- 사용자가 생성되었습니다.

-- SQL> create user kbs identified by p123;
-- 사용자가 생성되었습니다.

-- SQL> create role broadcast;
-- 롤이 생성되었습니다.

-- SQL> grant connect, resource to broadcast;
-- 권한이 부여되었습니다.

-- SQL> grant broadcast to mbc, kbs;
-- 권한이 부여되었습니다.

-- scott cmd
-- SQL> grant select, delete on v_account to broadcast;
-- 권한이 부여되었습니다.

-- kbs cmd
-- SQL> select * from scott.v_account;
--  BUSER_NO BUSER_NAME                INWON
------------ -------------------- ----------
--        30 전산부                        3
--        40 관리부                        3

-- mbc cmd
-- SQL> create synonym happy for scott.v_account;
-- 동의어가 생성되었습니다.
-- SQL> select * from happy;
--  BUSER_NO BUSER_NAME                INWON
------------ -------------------- ----------
--        30 전산부                        3
--        40 관리부                        3

-- kbs cmd
-- SQL> select * from happy;
-- select * from happy
--              *
-- 1행에 오류:
-- ORA-00942: 테이블 또는 뷰가 존재하지 않습니다

-- PL/SQL 및 Stored Procedure (카페 DB-RDBMS 40번)
create table aa(bun number, munja varchar2(20), su number);
set serveroutput on; -- pl/sql 수행 결과를 콘솔로 출력
select * from aa;

-- 변수 처리
declare
  no number := 0; -- 치환
begin
  no := 500 + 300;
  dbms_output.put_line(no);
  insert into aa(bun) values(no);
end;

declare
  type result is record(a number, b number);
  type test is varray(100) of result; -- 배열
  test1 test := test(); -- 배열 초기화 작업을 해줘야 함.
begin
  test1.extend(50); -- 공간 할당.
  test1(1).a := 10; -- 변수에 값 넣기.
  test1(1).b := 20;
  dbms_output.put_line(test1(1).a); -- 출력문.
  dbms_output.put_line(test1(1).b);
end;

-- exception
declare
  counter number(3) := 10;
  re number;
begin
  re := counter / 0; -- 나누는 값을 2로 했을 때는 에러가 나지 않지만, 0으로 했을 때 exception으로 넘어가 에러 출력.
  dbms_output.put_line('결과는' || re); -- ||는 문자열 더하기
exception when zero_divide then
  dbms_output.put_line('error');
  when others then -- 다중 에러 잡을 때, java에 catch문과 비슷.
  dbms_output.put_line('기타 error');
end;

create table jiktest as select * from jikwon;
select * from jiktest;

-- 해당 테이블 형으로 변수 선언
declare
  v_a jiktest%rowtype; -- v_a 는 jiktest rowtype
begin
  select * into v_a from jiktest where jikwon_no = 1; -- into 변수명 : into 변수명에 넣는다는 것
  insert into aa values(v_a.jikwon_no, v_a.jikwon_name, v_a.jikwon_pay); -- 변수명.sql
end;

select * from aa;

-- 해당 테이블 칼럼 형으로 변수 선언
declare
  a jiktest.jikwon_no%type; -- a는 직원테스트 직원넘버에 타입이라는 것.
  b jiktest.jikwon_name%type;
  c jiktest.jikwon_pay%type;
begin
  select jikwon_no, jikwon_name,jikwon_pay into a,b,c
  from jiktest
  where jikwon_no=3;
  dbms_output.put_line(a || ' ' || b || ' ' || c);
end;

-- 조건판단문 if
declare
  v_a jiktest%rowtype;
  v_str varchar2(20);
begin
  select * into v_a from jiktest where jikwon_no = 4;
  if (v_a.buser_num=10) 
    then v_str := concat(v_a.jikwon_name, ' 10'); -- concat : 문자열 더하기
  end if;
  if (v_a.buser_num=20) 
    then v_str := concat(v_a.jikwon_name, ' 20');
  end if;
  if (v_a.buser_num=30) 
    then v_str := concat(v_a.jikwon_name, ' 30');
  end if;
  if (v_a.buser_num=40) 
    then v_str := concat(v_a.jikwon_name, ' 40');
  end if;
  dbms_output.put_line('결과는 ' || v_str);
end;

declare
  v_a jiktest%rowtype;
  v_str varchar2(20);
begin
  select * into v_a from jiktest where jikwon_no = 4;
  if (v_a.buser_num=10) 
    then v_str := concat(v_a.jikwon_name, ' 10'); -- concat : 문자열 더하기
  elsif (v_a.buser_num=20) 
    then v_str := concat(v_a.jikwon_name, ' 20');
  elsif (v_a.buser_num=30) 
    then v_str := concat(v_a.jikwon_name, ' 30');
  elsif (v_a.buser_num=40) 
    then v_str := concat(v_a.jikwon_name, ' 40');
  else
    v_str := concat(v_a.jikwon_name, ' 기타');
  end if;
  dbms_output.put_line('결과는 ' || v_str);
end;

-- 반복문 for
declare
  dan number(2) := 2;
  i number(2) := 0;
  tot number := 0;
begin
  for i in 1..9 loop
    tot := dan * i;
    dbms_output.put_line(dan || '*' || i || '=' || tot);
  end loop;
end;

-- 반복문 while
declare
  V_count number := 1;
begin
  while(v_count <= 10) loop
    dbms_output.put_line(v_count);
    exit when(v_count = 5);-- Java에 break와 동일.
    v_count := v_count + 1;
  end loop;
end;

-- 반복문 while
declare
  v_a number := 0;
  v_b number := 0;
begin
  while(v_a < 10) loop
    v_a := v_a+1;
    if mod(v_a, 2) = 0 then 
      v_b := v_b + 10;
      dbms_output.put_line(v_a || ' 짝수 ' || v_b);
    else
      v_b := v_b + 5;
      dbms_output.put_line(v_a || ' 홀수 ' || v_b);
    end if;
  end loop;
end;
