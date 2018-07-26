-- ���Ἲ (Constraint) : �߸��� �ڷ��� �Է��� ���� ���� ��������
-- domain �������� : create �� Į���� ����, type, ũ�� ����(�� �Ӽ��� �¾ƾ� ��)
-- �⺻Ű �������� : �ߺ��� ���ʴ� Į���� ���� primary key ����
--                        �ߺ��ڷ� �Է� �Ұ�, not null, index ����
-- ����� ���� �������� : check(�÷��� ���� � Ư�� ������ ����), unique(�ߺ� ������� ����), foreign key(���̺� ����) ...

drop table aa;

-- �⺻Ű �������� : unique Į���� pk ���
-- ��� 1
create table aa(bun number primary key, irum char(10));
-- constraint name �� system �˾Ƽ� ����� ��. �Ϲ������� ���� ����ϴ� ���.

drop table aa;

-- ���2
create table aa(bun number constraint aa_bun_pk primary key, irum char(10));
-- constraint name�� ���� �� �� ����.

drop table aa;

-- ���3
create table aa(bun number, irum char(10),
constraint aa_bun_pk primary key(bun));
-- create�� �� ����� �������� �����ϴ� ���. 

-- Constraint name ���� ���
select table_name, constraint_type,
constraint_name from user_constraints
where table_name='AA'; -- ���̺���� �빮��

-- �������� �߰�
-- alter table ���̺�� add constraint �������Ǹ� ��������(Į����);
-- �������� ����
-- alter table ���̺�� drop constraint �������Ǹ�

-- check �������� : Ư��Į���� �Է��ڷῡ ���� ���� �˻�.
create table aa(bun number, irum char(10),
nai number(2) check (nai >= 20)); -- table ������ check �������� ����.

insert into aa values(1, 'tom', 12); -- nai check �������ǿ� ����Ǿ� �ڷᰡ �Էµ��� ����.
insert into aa values(1, 'tom', 22); -- O

drop table aa;

-- unique �������� : Ư�� Į���� ���� �� �Է� ����.
create table aa(bun number, irum char(10) unique);

insert into aa values(1, 'tom'); -- ���� �Է�
insert into aa values(1, 'tom'); -- irum�� unique�̱� ������ �������� ����Ǿ� �ڷᰡ �Էµ��� ����.

drop table aa;

-- foreign key(�ܺ�Ű, ����Ű, fk)�� ����� pk
-- �ٸ� ���̺��� Į������ ����
create table jikwon(sabun number primary key,
irum varchar2(10) not null, buser char(10));

insert into jikwon values(1,' ȫ�浿','�ѹ�');
insert into jikwon values(2,' ��浿','�ѹ�');
insert into jikwon values(3,' ȫ�浿','����');

select * from jikwon;

create table gajok(code number primary key,
gname varchar2(10) not null, 
gbirth date, 
jikwon_sabun number references jikwon(sabun)); -- ���� ���̺� ����� ���� ��.

create table gajok(code number primary key,
gname varchar2(10) not null, 
gbirth date, jikwon_sabun number,
constraint gajok_sabun_fk foreign key (jikwon_sabun) references jikwon(sabun)); -- foreign Ű �߰� �� �� �ִ� �ٸ� ���.

create table aa(bun number, irum char(10),
constraint aa_bun_pk primary key(bun));

-- on delete cascade : �θ� ���̺��� �� ���� �� �ڽ� ���̺� ������ �ڵ� ����

insert into gajok values(100,'ȫ�α�','2005-1-15',1);
insert into gajok values(200,'�ű���','1980-1-15',2);
insert into gajok values(300,'ȫ�繫','2006-11-15',5); -- jikwon ���̺� sabun �Ӽ�(Į��)�� �����ϰ� �ִµ� 5���� ����.
insert into gajok values(300,'ȫ�繫','2006-11-15',1);

select * from jikwon;
select * from gajok;

delete from jikwon where sabun=1; -- ���� ���ڵ尡 �ֱ� ������ �������� ����.
delete from jikwon where sabun=2;
delete from jikwon where sabun=3;

drop table jikwon; -- �ٸ� ���̺��� �����Ǵ� ���ڵ尡 �ֱ� ������ ���̺��� ���� �� �� ����.

delete from gajok where code=200;
delete from jikwon where sabun=2;

drop table gajok;
drop table jikwon; -- ���� �Ǿ� �ִ� ���̺� ���ڵ带 ���� �� ���̺� ����.

-- default : Ư��Į���� �ʱⰪ �ο�
create table aa(bun number, irum char(10),
addr varchar2(30) default '������ ������� 123');

insert into aa values(1,'tom','���ʱ� ���ʵ� 111');
insert into aa(bun,irum) values(2,'james');

select * from aa;

-- sequence : number type Į���� ���� ���� �ڵ� ����, �������� �������� ����.
create sequence seq_my minvalue 0 maxvalue 10000
increment by 1 start with 0;

drop table aa;

create table aa(bun number primary key, irum char(10));
create table bb(code number primary key, irum char(10));

insert into aa values(seq_my.nextval, 'tom'); -- create�� sequence��.mynextval �� ���� ��ȣ�� �ڵ����� �þ.
insert into aa values(seq_my.nextval, 'tom2');
insert into aa values(seq_my.nextval, 'tom3');
select * from aa;
insert into bb values(seq_my.nextval, 'tom4'); -- sequence ��ȣ�� ���� ���������� ���� �����.
select * from bb;

select seq_my.currval from dual; -- dual ������ ���̺��
select seq_my.nextval from dual;

insert into bb values(seq_my.nextval, 'tom5');
select * from bb;
insert into bb values(6, 'tom6');

insert into bb values(seq_my.nextval, 'tom7'); --x
select seq_my.currval from dual;

drop sequence seq_my; -- ������ ����

create sequence seq_our;
select seq_our.nextval from dual;
select seq_our.currval from dual;

select * from aa;

-- ���̺� ����
create table test(no number(5), name varchar2(10));

-- �ڷ� �����ϱ�
insert into test(no, name) values(1,'tom');
insert into test(no, name) values(1,'tom');

-- ���̺� Ȯ���ϱ�
Select * from test;

-- ���̺� �����ϱ�
drop table test;

-- ���̺� �����ϱ�
create table test(no number primary key, 
name varchar2(10) not null, 
tel varchar2(15),
inwon number, 
addr varchar(30));

-- ���̺� �Ӽ� ����
desc test;

-- ���ڵ� �߰��ϱ�
insert into test(no,name, tel, inwon, addr)
values (1,'�λ��','111-1111',5,'���ﵿ123');

-- ������ Į���̶� ���̶� ������ ����� ��.
insert into test(no, tel, inwon, addr, name)
values (2,'111-1112',8,'���ﵿ123','�Ǹ�1��');

-- ������ ���� Į�������� ���� ������ ���� ����.
insert into test
values (3,'�����','111-1113',6,'���̵�123');

insert into test(no,name)
values (4,'����2��');

insert into test(no, tel)
values(4,'222-2222'); -- err : name : not null �ʼ� �Է�

insert into test(no,name)
values (4,'����3��'); -- err : no : PK�̱� ������ �ߺ����� ���� �� ����.

insert into test(no,name)
values (5,'���� ����ϴ� �츮ȸ�� ��� �μ���'); -- err : name : �ִ밪�� 10�ε� �������� 52�̱� ������ ���� ����.

commit; --rollback;
-- Server�� ����ȭ�Ͽ� �ٸ� ������ �� �� �ֵ��� �Ͽ�����. 
-- Commit ���� ������ �ڱ� ��ǻ�Ϳ����� ��� ����. 
-- ��ǻ�͵��� �� ������ �������� ����Ǿ��ִ� ��.
-- Commit ���� ������ ��� ������ ���ư��� �Ǵ� ��.
-- sqlplus���� quit�ϰ� �Ǹ� �ڵ����� Commit ��.
-- Oracle�� ���� Commit������ ������ MySQL, JAVA ���� �ڵ� Commit.
-- �ڵ� Commit �ϰԵǸ� DB Server���� �����ϰ� �ɷ� ó���ϴµ� ���� �ð��� �ɸ� ���� ����.

-- �ڷ�����ϱ�
update test set inwon=19;

rollback; -- Commit�� �������� Rollback�ϰ� ��.

update test set inwon=19 where no=1; -- no�� 1���� ���ڵ常 ������.

-- Į���� ���ڵ��� ������ �������. select �� �� ���� �� �� ����.

update test set inwon=11, addr='�Ÿ���111' where no=2;

update test set name=null, addr='' where no=3; -- err : name : null=('')�� ��� �� �� ����.

-- �ڷ� ����
delete from test where no=1; -- no�� 1���� ���ڵ带 ������. delete�� commit, rollback�� ������ ����.
 
truncate table test; --truncate�� �����ϴ� ���ε� Auto Commit �Ǳ� ������ �ǵ��� ������� �ʵ��� ��.

drop table test;

select * from test;

-- ����1

-- �������̺� ����
create table ����(�����ڵ� number primary key,
������ varchar2(10), 
������ number check (������ >= 100 and ������ <= 500));

-- ������ ����
create sequence seq_school minvalue 0 maxvalue 10000
increment by 1 start with 1;

-- �������̺� ����
create table ����(�����ڵ� number primary key,
����� varchar2(10) unique,
����� varchar(20),
��米�� number references ����(�����ڵ�));

-- �л����̺� ����
create table �л�(�й� number primary key,
�л��� varchar2(10),
�������� number references ����(�����ڵ�),
�г� number default 1, check (�г� >= 1 and �г� <= 4));

-- �������̺� �ڷ� �Է�
insert into ���� values(10, '�ű���', 100);
insert into ���� values(20, '��¼��', 200);
insert into ���� values(30, 'ȫ�繫', 300);
-- insert into ���� values(30, 'ȫ�繫', 600); -- err : check ���� ���.

-- �������̺� �ڷ� �Է�
insert into ���� values(seq_school.nextval, '����', '����������', 10);
insert into ���� values(seq_school.nextval, '����', '�������', 20);
insert into ���� values(seq_school.nextval, '����', '����������', 30);
-- insert into ���� values(seq_school.nextval, '����', '�������', 40); -- err : ���� ��ȣ ����

-- �л����̺� �ڷ� �Է�
insert into �л�(�й�,�л���,��������) values(201801, '�ȳ���', 1);
insert into �л� values(201802, 'ȫ����', 2, 4);
insert into �л� values(201803, '����ö', 3, 3);
-- insert into �л� values(201804, '������', 4, 5); -- err : check ���� ���.

drop table ����;
drop table ����;

set timing on;
select * from aa;
-- index : Ư�� Į���� ���� ���� �˻��ӵ� ����
-- pk ���� Į���� �ڵ������� index ����
-- ��� ����
-- ���ڵ尡 ���� ���� ��� �˻��� ������ �ϱ� ����
-- join�� ���� ���� ���
-- null�� �����ϰ� �ִ� Į��
-- wherew ������ ����� ���

-- ����� �����ϴ� ���
-- �Է�, ����, ������ ����� ���̺�

create unique index ind_idx on aa(irum); -- �����ε��� 
create index ind_ir on aa(irum); -- ������ε��� : �ߺ��ڷᰡ ���� �� ���

select index_name, table_name from user_indexs;
select index_name, table_name from user_indexs
where table_name='AA';

alter index ind_ir rebuild; -- ���� �ε����� �����
drop index ind_ir;

set timing off;

-- ���̺� ����
-- create table ���̺�� ~
-- alter table ���̺�� ~
-- drop table ���̺�� ~
-- rename ���̸� to ���̸�

rename aa to kbs;
select * from kbs;
desc kbs;

-- ���̺� �Ӽ� �߰�
alter table kbs add(job_num number(3) default 100);

select * from kbs;

update kbs set job_num=777 where bun=1;
update kbs set job_num=7777 where bun=1; -- x number�� 3�ڸ��� ��Ҵµ� 4�ڸ� �� �ȵ�.

-- ���̺� �Ӽ� ����
alter table kbs modify(job_num number(4)); 
desc kbs;
alter table kbs modify(job_num number(2)); -- err : �ø��� �� �����ϳ� ���� �� ����(�����Ͱ� ���� ��쿡��)

-- ���̺� �÷��� ����
alter table kbs rename column job_num to job_bun;
desc kbs;

-- ���̺� �÷� ����
alter table kbs drop(job_bun);
desc kbs;

alter table kbs set unused(irum); -- ���� ����ɱ�.
alter table kbs drop unused columns; -- irum�� ���� �÷��� ������� ���� ��� drop��.

-- ������ export / import
show user;
select * from tab;
select * from emp;
select * from dept;
-- c:\~>
-- cmd���� C:\>exp scott/tiger tables=emp,dept file=c:\work\scott.dmp �ϸ� export ��.

drop table emp;
drop table dept;
select * from emp; -- ���� �ٿ��� ���� �߱� ������ �ƹ��͵� ����.

-- cmd���� C:\>imp scott/tiger tables=emp,dept file=c:\work\scott.dmp �ϸ� import ��.

-- ���̺� ����, ����, ���� ����
purge recyclebin;
select * from tab; -- ���� ������ ���̺� ��ü�� ������
drop table emp; -- ���� �� ������ �������°� �ƴ϶� recyclebin(����Ŭ ������)�� ����.

show recyclebin; -- ����Ŭ�����뿡 �ִ� �� ����.

flashback table emp to before drop; -- ����

select * from emp;

drop table emp;

show recyclebin;

purge recyclebin; -- ������ ��������

drop table dept purge; -- ���̺� ���� ����

select * from sangdata;

-- select ����
-- select [distinct] �����ڸ�.Į����[as ����], ...
-- [into ���̺��] from ���̺��
-- where ����...
-- order by ����Ű [asc/desc]...
-- group by 
-- having

select * from jikwon;
select jikwon_no, jikwon_name from jikwon;
select jikwon_name, jikwon_no from jikwon; -- ���ϴ� �÷��� ������ �ҷ� �� �� ����. �÷����� �� ������� ��������.

select jikwon_no as ���, jikwon_name ������ from jikwon; -- ������ �ٿ� �� �� ������, as�� �Ἥ ������ ������ �� ������ �׳� �ᵵ ��� ����.
-- ���߿� java���� �ҷ� �� ��?

select jikwon_no || jikwon_name from jikwon; -- ���ڿ� ���ϱ� ���� ||(���� ������). OR �ƴ�.
select jikwon_no || jikwon_name as �ڷ� from jikwon;

select 10, '�ȳ�', 12/3 from dual; -- dual : dummy table. dual�̶�� Ű���带 �̿��ϸ� �������̺� ��� �� �� ����.
select jikwon_no, jikwon_name, jikwon_pay from jikwon; 

select jikwon_no, jikwon_name, jikwon_pay,
jikwon_pay * 0.012 as tax from jikwon; -- ������ ����ؼ� �÷��� �߰� �� �� ����.

select jikwon_name || '��' from jikwon; -- �Ϲ� ���ڿ��� �ٿ��� ��� �� �� ����. 
-- ���ڿ��� ���Ѵٰ� DB Server�� ������ ������ ����.

select * from & table_name; -- ��ɾ� ���� �� ���̺���� ġ�� �� ���̺� ���� ������ �ҷ��� -- ����
select &column_name from jikwon; -- ����

-- ����(sort) : order by
select * from jikwon order by jikwon_pay asc; -- ��������
select * from jikwon order by jikwon_pay desc; -- ��������

-- order by�� ���� �׷캰�� �� �� ����.
select * from jikwon order by 
jikwon_gen;

select * from jikwon order by 
jikwon_gen asc, buser_num desc, jikwon_pay desc; -- ���������� ���� ���� �� �� ����.

select jikwon_no, jikwon_name, jikwon_pay / 100 * 100
from jikwon;

select jikwon_no, jikwon_name, jikwon_pay / 100 * 100 pay
from jikwon
order by pay asc; -- ������ �̿��ؼ� ���ĵ� �� �� ����.

select jikwon_no, jikwon_name from jikwon
order by 2 asc; -- ������ �ѹ�(Į���� ����)�� �༭ �� �� ����. no�� 1, name 2.

select jikwon_no, jikwon_name from jikwon
order by 2 asc, 1 desc;

select distinct jikwon_jik from jikwon; -- distinct �ߺ� ����. �ߺ��� ������ �ִ� �Ӽ��� �ҷ��� ��� �ؾ���. �ߺ����� �ʴ� �Ӽ��� �������� �� ������ ��.

select * from jikwon where jikwon_jik='�븮'; -- ������ �޾� �� ���ڵ常 ��� �� �� ����.
select * from jikwon where jikwon_no=3;
select * from jikwon where jikwon_no='3'; -- ���ڴ� ��������ǥ�� �ص� �׸� ���ص� �׸�.
select * from jikwon where jikwon_ibsail='2011/03/03';
select * from jikwon where jikwon_ibsail='2011-03-03'; -- data�� -, / ���߿� �ƹ��ų� �ᵵ ��� ����.

alter session set nls_date_format='YYYY-MM-DD';

-- and, or, ��� ����
select * from jikwon where  jikwon_no=3 or jikwon_no=5;
select * from jikwon where  jikwon_no=3 and jikwon_no=5;
select * from jikwon where  jikwon_jik='���' or jikwon_no=5;
select * from jikwon where  jikwon_jik='���' and jikwon_gen='��' and jikwon_pay <= (3000 + 500);

select * from jikwon where jikwon_no >= 5 and jikwon_no <= 10;

select * from jikwon
where jikwon_ibsail between '2001-1-1' and '2005-12-31'; -- ���ӵ� ���� ���� Ȯ��

select * from jikwon
where jikwon_ibsail between '��' and '��'; -- �ǹ� ����

select * from jikwon where jikwon_no < 5 or jikwon_no > 10;

select * from jikwon where not ( jikwon_no < 5 or jikwon_no > 10); -- not�� ���� ������ ������ �������� ������.
select * from jikwon where jikwon_no between 5 and 10; -- �ǵ��� �������� ���� ���� �������� ������ �ֵ��� �ؾ� ��. �ֳ� �ӵ��� ������.
select * from jikwon where jikwon_no not between 5 and 10; -- Ʋ���� ������ ���� ������ ����.

select * from jikwon where not jikwon_pay + 1000 >= 3000 + 500;

select * from jikwon where jikwon_gen='��';
select * from jikwon where jikwon_gen<>'��'; -- <> �� ǥ�ô� ���� ������ �ǹ�.

 select * from jikwon where jikwon_name='ȫ�浿';
 select * from jikwon where jikwon_name >='��'; -- ���ڿ��� ���ؼ��� ũ�⿬���� ��� ����. ���� ������ ������� �������
 
 select ascii('a'), ascii('A'), ascii(0), ascii('��'), ascii('��') from dual; -- �����Լ� ascii �� �̿��ϸ� �ڵ� ���� �� �� ����.

select * from jikwon where jikwon_name >='��' and jikwon_name <='��';
select * from jikwon where jikwon_name between '��' and '��'; -- ���ڿ��� ���ؼ� between ��밡��.

select * from jikwon where jikwon_jik='�븮' or jikwon_jik='����' or jikwon_jik='����';
select * from jikwon where jikwon_jik in('�븮', '����', '����'); -- ������ �ڵ带 �����ϰ� in�����ڸ� ����� ��� ����.
select * from jikwon where buser_num in(10, 30)
order by buser_num asc;
select jikwon_no, jikwon_name, buser_num from jikwon where buser_num in(10, 30)
order by buser_num asc;

select * from buser where buser_name in('�ѹ���', '�����');

-- like ����
select * from jikwon where jikwon_name like '��%'; -- '%'�� 0�� �̻��� ���ڿ��� ��Ÿ��
select * from jikwon where jikwon_name like '�̼�%';
select * from jikwon where jikwon_name like '��%��';
select * from jikwon where jikwon_name like '%��%';
select * from jikwon where jikwon_name like '��%��'; 
select * from jikwon where jikwon_name like '��_��';
select * from gogek where gogek_name like '__';
select * from jikwon where jikwon_name like '��%' or jikwon_name like '��__';
select * from gogek;

select * from gogek where gogek_jumin like '_______2%';
select * from gogek where gogek_jumin like '%-2%';

update jikwon set jikwon_jik=null where jikwon_no=5;
commit;


select * from jikwon;

select * from jikwon where jikwon_jik=null;
select * from jikwon where jikwon_jik is null;
select * from jikwon where jikwon_jik is not null;

-- ������ �켱����
-- () > ���(*,/, > +, -) > ���Ῥ���� > �񱳿����� > is null, like, in> [not] between 
-- > is null, like, in > [not] between

select jikwon_no as ���, jikwon_name ������,
jikwon_jik, jikwon_pay, jikwon_pay / 12 ���ʽ�,
jikwon_ibsail from jikwon where jikwon_jik in ('���', '�븮', '����')
and (jikwon_pay >= 2000 and jikwon_ibsail between '2000-1-1' and'2010-12-31')
order by jikwon_jik asc, jikwon_pay desc;

-- �����Լ� : ������ ������ ȿ���� ����
-- ������ �Լ� - �� ���ڵ� �� �۾�
-- �����Լ� 
select lower('Hello World'),upper('Hello World'), 
initcap('Hello world') from dual;
select concat ('Hello','World') from dual;

select ('Hello World', 3),
substr ('Hello World', 3, 3),
substr ('Hello World', -3, 3)
from dual;

select length('Hello World'), length('��� ����'),
instr('Hello World', 'o'),
instr('Hello World', 'o', 6),
instr('Hello World', 'o', 1,2),
trim(both ' ' from ' ab c   ')
from dual;

--��) jikwon ���̺��� �̸��� '��'�� ���Ե� ������ ������
-- '��'���� �� �� ���

select jikwon_name from jikwon where jikwon_name like '%��%';

or substr jikwon (3);

select jikwon_name from jikwon where jikwon_name like '%��%';

select substr (jikwon_name, 1 , 2) from jikwon where jikwon_name like '%��%';

select jikwon_name, substr(jikwon_name, instr(jikwon_name,'��'), 2) from jikwon 
where jikwon_name like '%��%'; 


--�����Լ� 

-- round : �Ҽ��� �ݿø�
select round (45.678), round (45.678,2), round (45.678,0), round (45.678,-1) from dual;
select jikwon_name, round(jikwon_pay * 0.0123) as tex 
from jikwon;

-- trunc : �Ҽ��� ���� ������
select trunc(45.678, 2), trunc(45.678, -1) from dual;

-- mod : ������ ���ϱ�
select mod(15,3),mod(15,2) from dual;

--��¥ �Լ� (����üũ ��)
select sysdate from dual;
select sysdate, sysdate + 10, sysdate + 10 ,sysdate + 2914000 from dual;

--�ٹ����� 5�� �ʰ� ���� ���
select jikwon_name, jikwon_ibsail from jikwon where (sysdate - jikwon_ibsail) * 24 > 50000;

select round ((sysdate - jikwon_ibsail) / 7) as ju from jikwon; --��¥�Լ��� ���絵 ����

select months_between('2018-08-10', '2018-01-10')from dual;

select add_months (sysdate, 3), add_months(sysdate, -3) from dual;

select sysdate, last_day(sysdate), next_day(sysdate, '��') from dual;

alter session set nls_language=korean;

-- ����ȯ �Լ�

select jikwon_pay * 0.5, jikwon_pay * '0.5' from jikwon;

-- to_date(), to_number(), to_char()

select sysdate - to_date*('2010-01-01','yyyy-mm-dd') from dual;

select to_char(sysdate + 3 / 24, 'yyyy"��"mm"��"dd"��"hh"��"') from dual;

select to_char(sysdate, 'yyyy') from dual;
select to_char(sysdate, 'DD-MON-RR RR:MI:SS') from dual;
select to_char(sysdate, 'WW') from dual;

select to_char(1234.567, '9,999.99'), 
to_char(12.567, '9,999.99'), to_char(12,'9,999.99')
from dual;

select to_char(1234.567, '0,009.99'), 
to_char(12.567, '9,0009.99'), to_char(12,'9,0009.99')
from dual;


-- ��Ÿ�Լ�
-- rank(), dense_rank()
select jikwon_no, jikwon_name, jikwon_pay,
rank() over(order by jikwon_pay desc) as rank, -- ��ũ�� ȥ�� ����. ��ũ�� �ߺ� ��� ����.
dense_rank() over(order by jikwon_pay desc) as rank2  -- ��ũ�� �ߺ���� ��.
from jikwon;

-- nvl(value1, value2)
select jikwon_name, jikwon_jik,
nvl(jikwon_jik, '�ӽ���')
from jikwon;

-- nvl2
select jikwon_name, jikwon_jik,
nvl2(jikwon_jik, '������', '�ӽ���') -- null ���� ����
from jikwon;

-- nullif(value1, vaule2)
select nullif(length('abcd'), length('1234')),
nullif(length('abcd'), length('123')) -- �ٸ���...?
from dual;

select jikwon_name, jikwon_jik,
nullif(jikwon_jik,'�븮') from jikwon;

-- ���� ǥ����
-- ���� 1 :
-- case ǥ���� when �񱳰�1 then ����� 1...else ����� n
select case 10 / 5
when 5 then '�ȳ�'
when 2 then '�ݰ���'
else '�߰�' end as result -- �񱳰��� ���� �� ������� ���, end as Į���� �߰�
from dual;

select jikwon_name, 
case jikwon_pay when 8200 then '������õ��'
when 5500 then '����5000��'
else '��Ÿ' end as ����
from jikwon;

select jikwon_name, jikwon_pay, jikwon_jik,
case jikwon_jik
when '����' then jikwon_pay * 0.05
when '����' then jikwon_pay * 0.04
when '����' then jikwon_pay * 0.03
else jikwon_pay * 0.03
end as donation
from jikwon where jikwon_gen='��';

-- ����2 :
-- case when ����1 then ���1... end
select jikwon_name, jikwon_gen,
case when jikwon_gen='��' then 'M'
when jikwon_gen='��' then 'F' end as gender
from jikwon;

select jikwon_name, jikwon_pay,
case
when jikwon_pay >= 7000 then '���'
when jikwon_pay >= 5000 then '�Ϲ�'
else '����'
end as result from jikwon
where jikwon_jik in('����','����','���');

-- decode : ����Ŭ ����
-- decode(Į��, �񱳰�1, ��ȯ��1, �񱳰�2, ��ȯ��2,...��ȯ��n)
select jikwon_name, jikwon_pay,
decode(trunc(jikwon_pay / 1000),4, 'A', 3, 'B', 2, 'C', 'etc') result
from jikwon;

select jikwon_name, jikwon_gen,
decode(jikwon_gen, '�� ', 'M', '�� ', 'F', ' ') as gender -- vchar2�� �ѱ� �� �� 2����Ʈ�� �ƴ� 3����Ʈ�� �Ҹ���.
from jikwon;

select jikwon_name, buser_num,
decode(buser_num, 10, '�ѹ�', 20, '����', ' ') as bu
from jikwon;

--��1) 10�� �̻� �ٹ��ϸ� '�ֿ��', 5�� �̻� �ٹ��ϸ� '���', �� �ܴ� '�Ϲ�'�̶� ǥ��
--     2000�� ���� �Ի��� ������ �ش�
--     Ư������(pay ����)  : 10�� �̻� 10%, 5�� �̻� 5%, �� �� 3% (�ݿø�:����)
--���  ���   ������   �ٹ����   ǥ��   Ư������
--      1      ȫ�浿      11     �ֿ��    ***
select jikwon_no as ���, jikwon_name as ������, round((sysdate - jikwon_ibsail)/365) as �ٹ����,
case
when round((sysdate - jikwon_ibsail)/365) >= 10 then '�ֿ��'
when round((sysdate - jikwon_ibsail)/365) >= 5 then '���'
else '�Ϲ�' end as ǥ�� ,
case 
when round((sysdate - jikwon_ibsail)/365) >= 10 then jikwon_pay * 0.1
when round((sysdate - jikwon_ibsail)/365) >= 5 then jikwon_pay * 0.05
else jikwon_pay * 0.03
end as Ư������ from jikwon
where to_char(jikwon_ibsail, 'yyyy') >= 2000;

--��2) 10�� �̻� �ٹ��ϸ� '�հ���', 5�� �̻� �ٹ��ϸ� '����', �� �ܴ� '�Ϲ�'�̶� ǥ��
--     null �� �ִ� �ڷ�� ����, ���� ��,��,��,ȫ ���� �۾��� ����
-- ���   ������    ����    �Ի�����    �ٹ�������    ����    �μ���
--           ȫ�浿    �븮    2002.2.5        5676       �հ���    �ѹ���
select jikwon_name as ������, jikwon_jik as ����, to_char(jikwon_ibsail,'yyyy.mm.dd') as �Ի�����, round(months_between(sysdate, jikwon_ibsail)) as �ٹ�������,
case
when round((sysdate - jikwon_ibsail)/365) >= 10 then '�հ���'
when round((sysdate - jikwon_ibsail)/365) >= 5  then '����'
else '�Ϲ�' end as ����,
decode(buser_num, 10, '�ѹ���', 20, '������', 30, '�����' ,40, '������') as �μ���
from jikwon
where jikwon_jik is not null and (jikwon_name like 'ȫ%' or  jikwon_name like '��%' or  jikwon_name like '��%' or  jikwon_name like '��%');

--��3) �� �μ���ȣ���� ������ ���� �޿��� �ٸ��� �λ��Ϸ� �Ѵ�. 
--     pay�� �������� 10���� 10%,30���� 20% �λ��ϰ� ������ �μ��� �����Ѵ�.
--     10�� �̻� ���ټ��� ǥ��
--     �ݾ��� 3�ڸ� ���� ,(�޸�) ǥ�� : ������ ���(�ݿø�)
-- ���   ���    ������   �μ�    ����    �λ󿬺�    ���ټ�
--             1     ȫ�浿    10    **,***     ****          O       <-- �ƴϸ� X ǥ��
select jikwon_no as ���, jikwon_name as ������, buser_num as �μ�, to_char(jikwon_pay, '99,999') as ����,
case
when buser_num = '10' then to_char(round(jikwon_pay * 1.1), '99,999') 
when buser_num = '30' then to_char(round(jikwon_pay * 1.2), '99,999')
else to_char(jikwon_pay, '99,999')  end as �λ󿬺�,
case
when trunc((sysdate - jikwon_ibsail)/365) >= 10 then 'O'
else 'X' end as ���ټ�
from jikwon;

-- ���� �� �Լ�(�׷� �Լ�) : null �� ���� ( count(*) ����)
select sum(jikwon_pay) as ��, avg(jikwon_pay) as ���
from jikwon;

select max(jikwon_pay) as �ִ밪, min(jikwon_pay) as �ּҰ�
from jikwon;

update jikwon set jikwon_pay= null where jikwon_no=1;
commit

select max(jikwon_pay) 15as �ִ밪, min(jikwon_pay) as /16
from jikwon;

select count(jikwon_no), count(jikwon_jik),
from jikwon
count(jikwond_pay),count;


-- ������ ���?
select count(*) as �ο��� 
from jikwon
where jikwon;

-- 2005�� ���Ŀ� �Ի��� ������ �޿���
select sum(jikwon_pay as �޿���), avg(jikwon_pay_from jik_gen)
where jikwon_ibsail >= '2005'-1-1 and jikwon_gen=ka;

-- group by : �Ұ� ���
-- select �׷� Į����,... ����Լ�(),...
-- from ���̺�� where ����
-- group by �׷�Į����,.. having ��°������
-- ���� : �׷�Į���� order by �� �� ����.
--         ��, ��°���� order by ����

-- ���� �޿��� ���, �ο��� ���
select jikwon_gen, avg(jikwon_pay), count(*) -- count�� null�� �͵� ���Խ��� ī��Ʈ
from jikwon
group by jikwon_gen;

-- �μ��� �޿���
select buser_num, sum(jikwon_pay)
from jikwon
group by buser_num;

-- �μ��� �޿��� : �޿����� 15000 �̻�
select buser_num, sum(jikwon_pay)
from jikwon
group by buser_num
having sum(jikwon_pay) >= 15000; -- group by�� ���� ����

-- �μ��� �޿��u : ���ڸ� ����
select buser_num, sum(jikwon_pay)
from jikwon
where jikwon_gen='��' -- group by �� ���� Ȯ��
group by buser_num;

-- �μ��� �޿��� : �޿����� 8000 �̻��� ���ڸ� ����
select buser_num, sum(jikwon_pay)
from jikwon
where jikwon_gen='��' -- group by �� ���� Ȯ��
group by buser_num
having sum(jikwon_pay) >= 8000;

-- select buser_num, sum(jikwon_pay)
-- from jikwon
-- order by buser_num
-- group by buser_num; -- group by ���� order by�ϸ� �ȵ�.

select buser_num, sum(jikwon_pay)
from jikwon
group by buser_num
order by sum(jikwon_pay) desc;

-- �׷��Լ� ��ø
select max(avg(jikwon_pay))
from jikwon
group by jikwon_pay;

-- ��1 : ���޺� �޿��� ��� (NULL�� ���� ����)
select jikwon_jik as ����, round(avg(jikwon_pay)) as �޿����
from jikwon
where jikwon_jik is not null
group by jikwon_jik
order by �޿���� desc;

-- ��2 : ����,���忡 ���� ���޺� �޿��� ����
select jikwon_jik as ����, sum(jikwon_pay) as �޿�����
from jikwon
where jikwon_jik in ('����', '����')
group by jikwon_jik
order by �޿����� desc;

-- ��3 : 2010�� ������ �Ի��� �ڷ� �� �⵵�� ������ ���
--select substr (jikwon_ibsail, 1, 2) as �Ի�⵵, count(*) as ������
select to_char(jikwon_ibsail, 'yyyy') as �Ի�⵵, count(*) as ������
from jikwon
where jikwon_ibsail < '2010-01-01'
group by to_char(jikwon_ibsail, 'yyyy')
order by to_char(jikwon_ibsail, 'yyyy') asc;

-- ��4 : ���޺� ���� �ο���, �޿��� ��� (NULL�� ������ �ӽ������� ǥ��)
select nvl(jikwon_jik, '�ӽ���') as ����, jikwon_gen as ����, count( jikwon_gen) as �ο���, sum(jikwon_pay) as �޿���
from jikwon
group by jikwon_jik, jikwon_gen
order by jikwon_jik asc;

-- ��5 : �μ���ȣ 10,20�� ���� �μ��� �޿� �� ���
select buser_num as �μ���ȣ, sum(jikwon_pay) as �޿���
from jikwon
group by buser_num
having buser_num in (10,20)
order by �޿��� desc;

-- ��6 : �޿��� ������ 7000 �̻��� ���� ���(NULL�� ������ �ӽ������� ǥ��)
select nvl(jikwon_jik, '�ӽ���') as �μ���ȣ, sum(jikwon_pay) as �޿���
from jikwon
group by jikwon_jik
having sum(jikwon_pay) >= 7000
order by �޿��� desc;

-- ��7 : ���޺� �ο���, �޿��հ踦 ���ϵ� �ο����� 3�� �̻��� ���޸� ���(NULL�� ������ �ӽ������� ǥ��)
select nvl(jikwon_jik, '�ӽ���') as ����, count(jikwon_jik) as �ο���, sum(jikwon_pay) as �޿��հ�
from jikwon

group by jikwon_jik
having count(jikwon_jik) >= 3
order by �޿��հ� desc;

select buser_num, jikwon_jik, sum(jikwon_pay)
from jikwon
group by rollup(buser_num,jikwon_jik) -- rollup : group�� �հ� ���� ��. ���α׷��ֿ��� �ҷ� ���µ� ��ְ� �߻� �� �� �־� �� ��������� ����.
order by buser_num asc;

select buser_num, jikwon_jik, sum(jikwon_pay)
from jikwon
group by cube(buser_num,jikwon_jik) -- cube : �¿�, ���Ʒ��� ���ؼ� �հ踦 ����. ���α׷��ֿ��� �ҷ� ���µ� ��ְ� �߻� �� �� �־� �� ��������� ����.
order by buser_num asc;

-- join : ���� ���̺��� �ڷ� select
-- cross join : ��� ����� ����.
desc jikwon;
desc buser; -- ���� ���̺� �μ���ȣ�� ���� Į��, ���� ���� ���� ������ ���� �� �� ����.
desc gogek; -- �������̺� �����ȣ�� ���� Į��, �μ��� ���� ���� ������ ���� �� �� ����.

-- cross join �� �ǹ������� �� ������� ����.
select jikwon_name, buser_name
from jikwon, buser;

select jikwon_name, buser_name
from jikwon
cross join buser; -- ���� ����� ��ɾ�� ����.

-- equi join : inner, left outer, right outer, full
-- equi : ������(=)
-- innter join
insert into buser(buser_no, buser_name) values(50, '�񼭽�'); -- join Ȯ�� ���� �Ϻη� �ڷ� ����
select * from buser;
select * from jikwon;
desc jikwon;
alter table jikwon modify buser_num number(4) null; -- ���� ���̺� ���� �ٲ�.
update jikwon set buser_num=null where jikwon_no = 5;
select * from jikwon;
commit;

select jikwon_no, jikwon_name, buser_name -- equi join
from jikwon, buser
where jikwon.buser_num=buser.buser_no; -- Į������ �ٸ��� ���̺��� �����ص� ����. ������ ���̺���� ����� ��.

select jikwon_no, jikwon_name, buser_name -- inner join
from jikwon inner join buser
on buser_num=buser_no; -- on�� �����.

select jikwon_no, jikwon_name, buser_name -- ���� inner join ��ɾ�� ����.
from jikwon  join buser -- �⺻�� inner�̱� ������ inner�� �����ϰ� �ᵵ ������.
on buser_num=buser_no; -- on�� �����.

-- left outer join : �� �� ���̺� null�� �ڷ� ���
select jikwon_no, jikwon_name, buser_name
from jikwon, buser
where buser_no(+)=buser_num; -- ����ʿ� (+)�� ��Ŀ� ���� �ٸ�. null ���ʿ� (+) �Է����ָ� ����.

select jikwon_no, jikwon_name, buser_name -- ���� left outer join�� ����
from jikwon left outer join buser -- left outer join �� �� ���̺� ������ ���� null ���� �����°� �ٸ�
on buser_num = buser_no; -- ���⼭�� (+)�� �������.

-- right outer join : �� �� ���̺� null�� �ڷ� ���
select jikwon_no, jikwon_name, buser_name
from jikwon, buser
where buser_no=buser_num(+);

select jikwon_no, jikwon_name, buser_name -- ���� right outer join�� ����
from jikwon right outer join buser -- right outer join �� �� ���̺� ������ ���� null ���� �����°� �ٸ�
on buser_num = buser_no; -- ���⼭�� (+)�� �������.

-- full : �� �� ���̺� null�� �ڷ� ���
select jikwon_no, jikwon_name, buser_name
from jikwon full outer join buser
on buser_num=buser_no;

-- self join : �ϳ��� ���̺��� �� �� �˻�
select a.jikwon_name, b.jikwon_jik -- �ϳ��� ���̺��� �� �� �ΰ�ó�� ��� ����.
from jikwon a, jikwon b
where a.jikwon_no=b.jikwon_no;

-- non equi join : = �̿��� ������ ���(ũ�ų�, �۰ų�, ũ�ų� �۰ų�, �۰ų� ������)
create table paygrade(grade number(1) primary key,
lowpay number, highpay number); -- non equi join�� �ϱ� ���Ͽ� ���� ���̺� ����

insert into paygrade values(1,0,1999);
insert into paygrade values(2,2000,2999);
insert into paygrade values(3,3000,3999);
insert into paygrade values(4,4000,4999);
insert into paygrade values(5,5000,9999);-- non equi join�� �ϱ� ���Ͽ� ���� �ڷ� ����.

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

-- �μ� �� �ٹ��� ���(�μ��� ���� ������ ����)
select buser_name, jikwon_name, jikwon_jik, buser_tel
from jikwon inner join buser
on buser_num = buser_no
order by buser_name asc;

-- �������� �ִ� ������ ���ڷ�(name, tel)
select jikwon_no, jikwon_name, gogek_name, gogek_tel
from jikwon inner join gogek
on jikwon.jikwon_no = gogek.gogek_damsano
order by jikwon_name;

-- �μ��� �޿���
select nvl(buser_name, '�ӽú�') as �μ���, sum(jikwon_pay) as �޿���
from jikwon, buser
where jikwon.buser_num=buser.buser_no(+)
group by buser_name;



-- ��1) ������ ����� ������ �����ϴ� �� ���
-- ��� ==>  ���   �����   ����      ����    ����ȭ    ������
--                  3     �ѱ���   ���       ������    123-4567       ��
select jikwon_no  as ���, jikwon_name as �����, jikwon_jik as ����, gogek_name as ����, gogek_tel as ����ȭ, 
decode(substr(gogek_jumin,8,1),1, '��', 2, '��') as ������
-- case when gogek_jumin like '%-1%' then '��'
-- when gogek_jumin like '%-2%' then '��' end as ������
from gogek, jikwon
-- from jikwon inner join gogek on jikwon_no = gogek_damsano where jikwon.jikwon_jik = '���';
where (jikwon.jikwon_no(+)=gogek.gogek_damsano) and jikwon.jikwon_jik = '���';

-- ��2) ������ �� Ȯ�� ��  -- GROUP BY ���
--    - ��� ���� ����
select jikwon_name as �����̸�, count(*) as ��Ȯ����
from jikwon, gogek
-- from jikwon right outer join gogek on jikwon_no = gogek_damsano
where jikwon_no = gogek_damsano
group by jikwon_name;

select jikwon_no as ������ȣ, jikwon_name as �����̸�, count(gogek_no) as ��Ȯ����  -- �ٸ����1
from jikwon left outer join gogek 
on jikwon_no = gogek_damsano
group by jikwon_no, jikwon_name;

select jikwon_no as ������ȣ, jikwon_name as �����̸�, count(*) as ��Ȯ���� -- �ٸ����2
from jikwon, gogek
-- from jikwon right outer join gogek on jikwon_no = gogek_damsano
where jikwon_no = gogek_damsano
group by jikwon_no, jikwon_name;

-- ��3) ���� ��������� �ڷḦ ���� ���� �� ��, ������ �Է��ϸ�,  ������� �ڷ� ���  
--        :    ~ WHERE GOGEK_NAME='������'
-- ��� ==>  ������       ����
--                �ѱ���       ���

select jikwon_name as ������, jikwon_jik as ����
from jikwon, gogek
-- from jikwon inner join gogek on jikwon_no=gogek_damsano where gogek_name='�̿���';
where (jikwon.jikwon_no(+)=gogek.gogek_damsano) and gogek_name='�̿���';
    
-- ��4) �������� �Է��ϸ� ������ �ڷ� ��� 
--       : ~ WHERE SAWON_NAME='�ѱ���'
-- ��� ==> ����   ����ȭ          �ֹι�ȣ           ����
--               ������   123-4567    700512-1234567        38

select gogek_name as ����, gogek_tel as ����ȭ, gogek_jumin as �ֹι�ȣ, 
trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) as ����
--trunc(months_between(to_char(sysdate,'rrmmdd'),to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) as ����
--months_between(to_char(sysdate,'yymmdd'), to_date(substr(gogek_jumin,1,6),'yymmdd')) as ����
--to_char(sysdate,'yyyy') - (substr(gogek_jumin,1,2) + 1900) as ����
from jikwon, gogek
-- from jikwon inner join gogek on gogek_damsano = jikwon_no where = jikwon_name='ȫ�浿'
where (jikwon.jikwon_no=gogek.gogek_damsano(+)) and jikwon_name='ȫ�浿';

-- �� ���� ���̺� ����
select jikwon_name, buser_name, gogek_name
from jikwon, buser, gogek
where buser_num=buser_no and jikwon_no=gogek_damsano; -- �� ���� ���̺��� ���� �� ���� and�� ���, ������ �� ���� ��� and ���

select jikwon_name, buser_name, gogek_name
from jikwon
inner join buser on buser_num=buser_no
inner join gogek on jikwon_no=gogek_damsano; -- �� ���� ���̺� join �̿� �� �� ��� ���

-- ��1) �ѹ��ο��� �����ϴ� ���� ��� (�� 30�� �̻� �۾��� ����)
select buser_name as �μ���, count(*) as ����
from jikwon
left join buser on buser_num=buser_no
left join gogek on jikwon_no=gogek_damsano
where trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) >= 30 and buser_name = '�ѹ���'
group by buser_name;

-- ��2) �μ��� �� �ο��� (�μ��� ������ "���Ҽ�")
select nvl(buser_name,'���Ҽ�') as �μ���, count(*) as ����
from jikwon
inner join buser on buser_num=buser_no
right join gogek on jikwon_no=gogek_damsano
group by buser_name;

select nvl(buser_name,'���Ҽ�') as �μ���, count(gogek_no) as ����
from jikwon
full join buser on buser_num=buser_no
left join gogek on jikwon_no=gogek_damsano
group by buser_name;
 
-- ��3) ���� ��������� �ڷḦ ���� ���� �� ��, ������ �Է��ϸ�     ������� �ڷ� ���  
--        :    ~ WHERE GOGEK_NAME='������'
--          ��� ==>  
������    ����   �μ���  �μ���ȭ    ����
select jikwon_name as ������, jikwon_jik as ����, buser_name as �μ���, buser_tel as �μ���ȭ, jikwon_gen as ����
from jikwon
inner join buser on buser_num=buser_no
right join gogek on jikwon_no=gogek_damsano
where  gogek_name='������';

-- ��4) �μ��� �������� �Է��ϸ� ������ �ڷ� ���
--        ~ WHERE BUSER_NAME='������' AND SAWON_NAME='�̼���'
-- ��� ==>  ����    ����ȭ      ����
--                ������   123-4567       ��
select gogek_name as ����, gogek_tel as ����ȭ,  decode(substr(gogek_jumin,8,1),1, '��', 2, '��') as ������
from jikwon
inner join buser on buser_num=buser_no
left join gogek on jikwon_no=gogek_damsano
where  buser_name='������' and jikwon_name='�̼���';

-- union : ������ ��ġ�ϴ� �� �� �̻��� ���̺� �ڷ� ���ĺ���
create table pum1(bun int, pummok varchar2(10));
insert  into pum1 values(1,'���');
insert  into pum1 values(2,'����');
insert  into pum1 values(3,'�ٳ���');
insert  into pum1 values(50,'����');

select * from pum1;
drop table pum2;
create table pum2(num int, sangpum varchar2(10));
insert  into pum2 values(10,'����');
insert  into pum2 values(20,'����');
insert  into pum2 values(30,'�丶��');
insert  into pum2 values(40,'����');
insert  into pum2 values(50,'����');

select * from pum2;

select bun as ��ȣ, pummok as ��ǰ�� from pum1 -- Į���� ������ �����ؾ� ��.
union -- �ߺ� ������ ��� ��, union all�� �ߺ��� ���� ���
select num, sangpum from pum2;

select bun as ��ȣ, pummok as ��ǰ�� from pum1
minus -- �����Ͱ�, pum2�� ������ pum1�� ���
select num, sangpum from pum2;

select bun as ��ȣ, pummok as ��ǰ�� from pum1
intersect -- ���� �͸� ���(������)
select num, sangpum from pum2;

select jikwon_name from jikwon
union
select gogek_name from gogek;

-- ���� �����ϴ� ���� ��� ���
select jikwon_no, jikwon_name from jikwon
where jikwon_no in(select jikwon_no from jikwon
intersect select distinct gogek_damsano from gogek);

-- ���� �������� �ʴ� ���� ��� ���
select jikwon_no, jikwon_name from jikwon
where jikwon_no in(select jikwon_no from jikwon -- in�� or��!!
minus select gogek_damsano from gogek);

-- merge : ������ ��ġ�ϴ� �� �� �̻��� ���̺� �ڷḦ ����
--            �̹� ���� �ִٸ� ����, ���ٸ� �߰�
create table test1 as select jikwon_no, jikwon_name, 
jikwon_pay from jikwon where jikwon_no <= 10;
-- ���� ���̺��� �̿��Ͽ� ���ο� ���̺� ����� ���, pk�� ������� �ʱ� ������ ���� �������� ��.

select * from test1;

create table test2 as select jikwon_no, jikwon_name, -- merge �ϱ� ���� �Ϻη� ���� ��.
jikwon_pay from jikwon where jikwon_no <= 10 and jikwon_jik='���';

select * from test2;

insert into test1 values(100,'�����',5000); -- �Ϻη� �ڷ� ����.
insert into test2 values(200,'�ҳ���',7000);

merge into test1 a using test2 b on(a.jikwon_no=b.jikwon_no)
when matched then update set a.jikwon_name=b.jikwon_name, a.jikwon_pay=b.jikwon_pay
when not matched then insert values(b.jikwon_no, b.jikwon_name,b.jikwon_pay);

select * from test1;

-- sub query : query �ӿ� query�� �ִ� ����
-- (���� ���� query�� ����� �ٱ��� query���� �����ϰ� �ȴ�)
select * from jikwon;

-- �̼��Ű� ������ ���� ���� ���
select jikwon_jik
from jikwon
where jikwon_name='�̼���';
select * from jikwon where jikwon_jik='����';

-- ��ɹ�1 + ��ɹ�2
select * -- ���忡 ���� ��� ������ �������.
from jikwon
where jikwon_jik=( -- ���� ������ ������ �Ѱ���.
          select jikwon_jik 
          from jikwon
          where jikwon_name='�̼���');

-- ������ �븮 �� ���� ���� �Ի��� �����?
select min(jikwon_ibsail)
from jikwon
where jikwon_jik='�븮';

select *
from jikwon
where jikwon_ibsail='2010/11/04'; -- 2010/11/04 �� �Ի��� ��� ������ ������� ����. �׷��� ������ ���� ����.

select *
from jikwon
where jikwon_jik='�븮' and jikwon_ibsail=( -- 2010/11/04 �� �Ի��߰� ������ �븮�� ���
          select min(jikwon_ibsail)
          from jikwon
          where jikwon_jik='�븮'); -- -- 2010/11/04 �� �Ի��� ��� ����

-- ��� ������ '�ѱ���'�� �� �ڷ� ���
select * 
from gogek
where gogek_damsano=(
          select jikwon_no 
          from jikwon 
          where jikwon_name='�ѱ���');
          
-- �� �� '�̺�'�� ���̰� ���� �� ��θ� ���
select *
from gogek
where substr(gogek_jumin,1,2)=(
          select substr(gogek_jumin,1,2)
          from gogek
          where gogek_name='�̺�');
          
-- ��õ���� �ٹ��ϴ� ���� ���
select * 
from jikwon
where buser_num=(
          select buser_no
          from buser
          where buser_loc='��õ');
          
-- ��õ �̿ܿ��� �ٹ��ϴ� ���� ���
select * 
from jikwon
where buser_num in( -- �� ���̻��� ���ϰ��� =���� ���� �� ����. �� ���̻��� ���� ���� in�� ���
          select buser_no
          from buser
          where not buser_loc='��õ');

-- 2�� ������ ������ ����, ������ ����� ������ ��տ������� ������ ���� ����� ���
select jikwon_no, jikwon_name
from jikwon
where jikwon_jik=(
          select jikwon_jik
          from jikwon
          where jikwon_no=2) and jikwon_pay >= (
          select avg(jikwon_pay) 
          from jikwon 
          where jikwon_jik='���');
          

-- JIKWON, BUSER, GOGEK ���̺��� ����Ѵ�.
-- ��1) 2010�� ���Ŀ� �Ի��� ���� �� �޿��� ���� ���� �޴� ������?
select jikwon_name as �����̸�
from jikwon
where jikwon_ibsail >= '2010-01-01' and jikwon_gen = '��' and jikwon_pay =(
          select max(jikwon_pay)
          from jikwon
          where jikwon_ibsail >= '2010-01-01' and jikwon_gen = '��');

select * from jikwon;

-- ��2)  ��ձ޿����� �޿��� ���� �޴� ������?
select jikwon_name as �����̸�
from jikwon
where jikwon_pay >(
          select avg(jikwon_pay)
          from jikwon);

-- ��3) '�ѱ���' ������ �Ի� ���Ŀ� �Ի��� ������?
select jikwon_name as �����̸�
from jikwon
where jikwon_ibsail > (
          select jikwon_ibsail
          from jikwon
          where jikwon_name='�ѱ���');

-- ��4) 2000 ~ 2010 ���̿� �Ի��� �ѹ���,������,����� ���� �� �޿��� ���� ���� �����?
--       (������ NULL�� �ڷ�� �۾����� ����)
          
                                                                                                            
select jikwon_name as �����̸� 
from jikwon inner join buser 
on buser_num = buser_no 
where jikwon_jik is not null and buser_name in ('�ѹ���','������','�����') and  (jikwon_ibsail between '2000-01-01' and '2010-12-31') and jikwon_pay = (
                  select min(jikwon_pay) 
                  from jikwon inner join buser 
                  on buser_num = buser_no
                  where buser_name in ('�ѹ���','������','�����') and ( jikwon_ibsail between '2000-01-01' and '2010-12-31'));

-- ��5) �ѱ���, �̼��Ű� ������ ���� ����� �����ΰ�?
select jikwon_name as �����̸�
from jikwon
where jikwon_jik in(
          select jikwon_jik 
          from jikwon
          where jikwon_name in ('�̼���','�ѱ���'));

-- ��6) ���� �߿��� �ִ�޿�, �ּұ޿��� �޴� �����?
select jikwon_name as �ִ��ּұ޿�����
from jikwon
where jikwon_pay =(
          select max(jikwon_pay)
          from jikwon
          where jikwon_jik='����')
union
select jikwon_name as �ִ��ּұ޿�����
from jikwon
where jikwon_pay =(
          select min(jikwon_pay)
          from jikwon
          where jikwon_jik='����');
          
select * 
from jikwon 
where jikwon_jik = '����' and
jikwon_pay in (
                (select min(jikwon_pay) from jikwon where jikwon_jik = '����'),
                (select max(jikwon_pay) from jikwon where jikwon_jik = '����'));

-- ��7) 20�� �μ��� �ּұ޿����� ���� �����?
select jikwon_name as �����̸�
from jikwon
where jikwon_pay > (
          select min(jikwon_pay)
          from jikwon
          where buser_num=20);
          
select * from jikwon;

-- ��8) 30�� �μ��� ��ձ޿����� �޿��� ���� '�븮' �� ����ΰ�?
select jikwon_name as �����̸�
from jikwon
where jikwon_jik = '�븮' and jikwon_pay >(
          select avg(jikwon_pay)
          from jikwon
          where buser_num=30);

-- ��9) ���� Ȯ���ϰ� �ִ� �������� �̸�, ����, �μ����� �Ի��� ���� ����϶�.
select jikwon_name as �����̸�, jikwon_jik as ����, buser_name as �μ���, jikwon_ibsail as �Ի���
from jikwon left join buser 
on buser_num = buser_no
where jikwon_no in(
          select gogek_damsano
          from gogek)
order by jikwon_ibsail asc;

-- ��10) �̼��Ű� ���� �μ��� �ٹ��ϴ� ������ �ش� ������ �����ϴ� �� ���
-- (���� ���̰� 30 ���ϸ� 'û��', 40 ���ϸ� '�߳�', �� �ܴ� '���'���� ǥ���ϰ�, �� ������ ���� ���)
-- ��� ==>  ������    �μ���     �μ���ȭ     ����      ����    ����ȭ    ������
--                �Ѽ���    �ѹ���     123-1111    ���      �����    333-3333    û��   
select jikwon_name as ������, buser_name as �μ���, buser_tel as �μ���ȭ, jikwon_jik as ���, gogek_name as ����, gogek_tel as ����ȭ,
case
when trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) <= 30 then 'û��'
when trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) <= 40 then '�߳�'
else '���' end as ������
from jikwon
inner join buser on buser_num=buser_no
left join gogek on jikwon_no=gogek_damsano
where buser_num in(
          select buser_num
          from jikwon
          where jikwon_name='�̼���')
order by trunc(months_between(sysdate,to_date(substr(gogek_jumin,1,6),'rrmmdd'))/12) desc;

-- �ѹ��� �� �ٹ��������� �����ϴ� �� ���
-- subquery
select gogek_no, gogek_name, gogek_tel 
from gogek
where gogek_damsano in(
          select jikwon_no
          from jikwon
          where buser_num=(
                    select buser_no
                    from buser
                    where buser_name='�ѹ���'));
                    
-- join
select gogek_no, gogek_name, gogek_tel 
from gogek
inner join jikwon on jikwon_no=gogek_damsano
inner join buser on buser_num=buser_no
where buser_name='�ѹ���';

-- any / all
-- < any : subquery�� ��ȯ�� �� �ִ밪 ���� ���� ~
-- > any : subquery�� ��ȯ�� �� �ִ밪 ���� ū ~
-- < all : subquery�� ��ȯ�� �� �ּҰ� ���� ���� ~
-- > all : subquery�� ��ȯ�� �� �ִ밪 ���� ū ~

-- �븮�� �ִ밪 ���� ���� ������ �޴� ���� ���
select jikwon_no, jikwon_name, jikwon_pay 
from jikwon
where jikwon_pay < any (
          select jikwon_pay 
          from jikwon 
          where jikwon_jik='�븮');
          
-- 30�� �μ��� �ְ� �޿��� ���� �޿��� ���� ���� ���
select jikwon_no, jikwon_name
from jikwon
where jikwon_pay > all (
          select jikwon_pay 
          from jikwon
          where buser_num=30);
          
-- �����������(�� ��������� ������, �˾Ƶα�� �� ��)
-- ���� query�� �ٱ��� ������ �����ϰ� �� ����� �ٱ��ʿ� query���� �����ϴ� ����

-- �� �μ��� �ִ� �޿��ڴ�?
select * 
from jikwon a
where a.jikwon_pay = (
          select max(b.jikwon_pay) 
          from jikwon b 
          where a.buser_num=b.buser_num) -- ���⼭ �ٱ��� ������ �����ϰ� ����.
order by a.buser_num asc;

-- �޿����� 3�� �̳��� �ڷ� ���(��������)
select a.jikwon_name, a.jikwon_pay
from jikwon a
where (select count(*) from jikwon b where a. jikwon_pay < b.jikwon_pay) < 3
-- b�� ��ü
-- a�� ����
-- b���� a�� ���� ���� ����Ȯ�� �� 
-- �࿡ ���� ���ٸ��� 3���� ���
and jikwon_pay is not null
order by jikwon_pay desc;

-- inline view : from ���� sub query
-- ��ü ��ձ޿��� �ִ�޿� ������ �޿��� �޴� �������
select jikwon_no, jikwon_name, jikwon_pay
from jikwon a, (select avg(jikwon_pay) as avgs, max(jikwon_pay) as maxs from jikwon) b
where a. jikwon_pay between b.avgs and b.maxs;

-- �� �μ����� �ְ�޿��� �޴� ������?
select a.jikwon_no, a.jikwon_name, a.jikwon_pay
from jikwon a, ( select buser_num, max(jikwon_pay) as maxpay
                      from jikwon group by buser_num) b
where a.buser_num=b.buser_num and a.jikwon_pay = b.maxpay;

-- having ���� subquery
-- �μ��� ��ձ޿� �� 30�� �μ��� ��ձ޿����� �޿��� ���� �ڷ� ���
select buser_num, avg(jikwon_pay)
from jikwon
group by buser_num
having avg(jikwon_pay) > (
          select avg(jikwon_pay)
          from jikwon
          where buser_num=30);
          
-- exists ������ : ���������� ��� ������ Ȯ��
-- ������ �ִ� �μ� ���
select buser_name, buser_loc
from buser bu
where exists ( -- not exists�� �ݴ�
          select 'imsi' 
          from jikwon
          where buser_num=bu.buser_no);
-- ������ ���� �μ� ���          
select buser_name, buser_loc
from buser bu
where not exists (
          select 'imsi' 
          from jikwon
          where buser_num=bu.buser_no);
          
-- sub query�� �̿��� table ����, �ڷ��� �߰�, ����, ����
create table sa1 as select * from jikwon; -- �⺻Ű�� ������ ������ ���̺�� �ڷ���� ����.
select * from sa1;
desc sa1;

create table sa2 as select * from jikwon where 1 = 0; -- ���ǿ� �´� ���� ���� ������ ������ �������.
select * from sa2;

insert into sa2 select * from jikwon where jikwon_jik='����'; -- ���� ������ �̿��� ���ǿ� �°� ���̺� �ڷḦ �����ؼ� �־���.
insert into sa2(jikwon_no, jikwon_name, buser_num)
select jikwon_no, jikwon_name, buser_num from jikwon where jikwon_jik='�븮'; -- ���� Į���� ������ �������� null�� ����.
select * from sa2;

create table sa3 as select jikwon_no, jikwon_name, jikwon_pay from jikwon where 1 = 0;
desc sa3;

-- �������̺� ���� Į�� insert
create table sa4 as select jikwon_no, jikwon_name, jikwon_jik from jikwon where 1 = 0;
create table sa5 as select jikwon_no, jikwon_name, jikwon_pay, jikwon_gen from jikwon where 1 = 0;

select * from sa4;
select * from sa5;

insert all -- �ٸ� ���̺� ���� �ڷḦ �����Ͽ� ����� ���̺� �Ѳ����� ���� �ְ� ���� ��
into sa4 values(jikwon_no, jikwon_name,jikwon_jik)
into sa5 values(jikwon_no, jikwon_name,jikwon_pay,jikwon_gen)
select jikwon_no, jikwon_name, jikwon_jik,jikwon_pay,jikwon_gen
from jikwon
where buser_num = 10;

select * from sa4;
select * from sa5;

-- ���ǿ� ���� insert
insert all 
when jikwon_jik='���' then
into sa4 values(jikwon_no, jikwon_name,jikwon_jik)
when jikwon_gen='��' then
into sa5 values(jikwon_no, jikwon_name,jikwon_pay,jikwon_gen)
select jikwon_no, jikwon_name, jikwon_jik,jikwon_pay,jikwon_gen
from jikwon
where buser_num in (20, 30);

-- update
select * from sa1;

update sa1 set jikwon_jik = ( -- ���忡 ���� ������ �������� �ٲ�.
          select jikwon_jik -- ����
          from jikwon
          where jikwon_name='�ѱ���')
where jikwon_no=1;

delete from sa1 where jikwon_no in ( -- ���� �����ϰ� �ִ� ����� ����
          select distinct gogek_damsano  
          from gogek);
          
-- rownum(���� ������ ����)�� �̿��� tom-n(���ȣ�� �� �� ����)�� �� �� �ִ�.
-- (My-sql�� limit)
-- �����ڴ� <, <= �� ���
select rownum, jikwon_name, jikwon_pay from jikwon;

-- �޿� ���� 3�� �̳� ���
select rownum, jikwon_no, jikwon_name, jikwon_pay
from (
        select * 
        from jikwon
        where jikwon_pay is not null -- null�� ����
        order by jikwon_pay desc)
where rownum <= 3;

-- ���� �ֱٿ� �Ի��� ���� 5�� �̳� ���
select rownum, jikwon_no, jikwon_name, jikwon_ibsail
from (
        select *
        from jikwon
        order by jikwon_ibsail desc)
where rownum <= 5;

-- transaction : ������ ������ ó��
-- DML ���� �� commit, rollback�� ������ ������ ó���� ����.
select * from sa1;
delete from sa1 where jikwon_no=8; -- delete �ߴٰ� ������ ���� �� ���� �ƴ�
commit; -- commit�� �Ǿ��� ������ ���� ��.
delete from sa1 where jikwon_no=10;
select * from sa1;
rollback; -- delete �� �ڷḦ �ٽ� �ǵ���. commit ���� �ʾұ� ������ rollback�� ����. 
-- ����, ����, ���� ���� Log ���Ͽ� ��� ��. �׷��� commit, rollback�� ���� ��� ���������� ���� �� �ǵ����Ⱑ ���� �� ��.

-- transaction ��
update sa1 set jikwon_name = '����' where jikwon_no=11;
savepoint a; -- rollback ������ ����
update sa1 set jikwon_name = 'ȣȣ' where jikwon_no=13;
rollback to savepoint a; -- savepoint a �� rollback �������� ���ư�
select * from sa1;
rollback; -- commit �������� ���ư�.
update sa1 set jikwon_name = '����' where jikwon_no=14;
commit;
select * from sa1;
update sa1 set jikwon_name = '����' where jikwon_no=10; -- transaction ���� / ������ ���� ����
-- cmd.exe���� delete ���� �ȵ�(transaction ���۵Ǿ��� ������) <- �̷��� �� ��� Deadlock�� �ɸ�
rollback;

-- view file : �������� ���̺��� �ٰŷ� ������ ���� ���̺� �ۼ�
-- select ���� ������ ���Ϸ� ����� ���̺� ó�� ���
-- ���� : �����ϰ� �� select ���� view�� �ܼ�ȭ �� �� �ִ�.
--          ������ ��ȭ �� �� �ִ�.
--          �ڷ��� �������� Ȯ�� �� �� �ִ�.
--          �������� ������ �������� �ʴ´�.
-- ���� : create [or replace] view �����ϸ� as select��
-- or replace�� ����� ����.
create table vjikwon as select * from jikwon;
select * from vjikwon;

select jikwon_no, jikwon_name, jikwon_pay
from vjikwon
where jikwon_ibsail >= '2005-01-01';

-- ������ �������� create view�� ���� ������ scott���� �ο�.
-- grant create view to scott : create view ���Ѻο� ��ɾ�

create or replace view v_a as -- scott ������ �信 ���� ������ ��� ������. ���Ѻο� �� ����� ����.
select jikwon_no, jikwon_name, jikwon_pay
from vjikwon
where jikwon_ibsail >= '2005-01-01';

select * from v_a;
desc v_a;
select sum(jikwon_pay) from v_a;
select view_name, text from user_views; -- ���� ���� ��ɾ�, ������ �� �� ����.

create view v_b as 
select * 
from vjikwon
where jikwon_name like '��%' or jikwon_name like '��%';

select * from v_b;

select jikwon_name as �̸�, jikwon_pay as ����
from v_b;

rename vjikwon to myjikwon; -- ���̺�� ����
select * from v_a; -- �̸��� ���� �߱� ������ ���� �� �������� ���̺��� ��� ������ ����.
select * from v_b;
rename myjikwon to vjikwon;

-- drop view ~ -- �� ����
-- alter view ~ -- �� ��������

create view v_c as 
select * 
from vjikwon
order by jikwon_pay desc;

select * from v_c;

create view v_d as
select jikwon_name, jikwon_pay * 10000 as ypay-- ������ ��� �� ��� �信���� ������ ������� ��.
from vjikwon;

select * from v_d;

select * from v_d where ypay >= 60000000;

create view v_e as 
select *
from v_d -- view�� view�� �ۼ� �� �� ����.
where ypay >= 60000000;

select * from v_e;

update v_e set jikwon_name='���' -- v_e���� update�ϸ� ���������� ����� ��� ���̺� �����.
where jikwon_name = '�ѱ���'; 

select * from v_e;
select * from v_d;
select * from vjikwon; 

delete from v_d where jikwon_name='ȫ�浿'; -- ������ ����. delete�ϸ� ���޾� ����� ���̺� ������.
select * from v_d;
select * from vjikwon; 

update v_d set ypay = 770000000 -- �������̺�, �÷��̱� ������ �����÷��� �̿��� ���� �� �� ����.
where jikwon_name='���';

update vjikwon set jikwon_pay=1234 where jikwon_name='���'; -- ���������̺��� ������ ���̺� ������ �����ϸ� ���� ���̺� ���� �ٲ�.
select * from v_d;

create or replace view v_f as
select jikwon_no, jikwon_name, buser_num, jikwon_pay
from vjikwon
order by jikwon_no asc; -- create �� �� ���ĵ� ����.

select * from v_f;

insert into v_f values(1,'�ű���',10,5000); -- �信 ���� ����.

select * from v_f;
select * from vjikwon; -- ���Ե� �信�� �����ص� ���������̺� ���Ե�.

create or replace view v_f as
select jikwon_no, jikwon_name, buser_num, jikwon_pay
from vjikwon
where jikwon_pay >= 5000
order by jikwon_no asc;

insert into v_f values(20,'�ű��',20,5500);
insert into v_f values(21,'������',30,4500); -- ���������̺��� ������ �� ���̺� ���� ���� pay�� 5000 �̻��̱� ������ ������ Ȯ���ϰ� ������.

select * from v_f;
 
create or replace view v_g as
select jikwon_jik as jik, sum(jikwon_pay) as hap, round(avg(jikwon_pay),1) as ave
from vjikwon
group by jikwon_jik;

select * from v_g;
select * from v_g where ave >=5000;
select * from v_g where jikwon_jik = '�븮'; -- view�� jikwon_jik��� �÷��� ����
select * from v_g where jik = '�븮'; -- view�� �ִ� �����÷��� ����ϸ� ����.

-- view�� �����Լ�, group by, distinct .... �� ������ ���
-- ���� ����, ���� �� �� ����.

create or replace view v_h as
select jikwon_no, jikwon_name, buser_name, jikwon_jik
from vjikwon inner join buser -- join�� ���� �䵵 ���� �� ����.
on buser_num=buser_no
where buser_num in (10, 30);

select * from v_h;
 
 
-- ��1) ���  �̸�    �μ�  ����  �ٹ����  ��Ȯ��
--      1   ȫ�浿  ������ ���     6    O   or  X
-- ���� : ������ ������ �ӽ���, ����� �ڷ�� ����
-- ���� ����� ���� ������ v_exam1�� �ۼ�
create or replace view v_exam1 as
select jikwon_no as ���, jikwon_name as �̸�, buser_name as �μ�, nvl(jikwon_jik,'�ӽ���') as ����, round((sysdate - jikwon_ibsail)/365) as �ٹ����, 
case 
when jikwon_no in (
         select distinct gogek_damsano
         from gogek) 
then 'O'
else 'X' end as ��Ȯ��
from vjikwon 
left join buser on buser_num=buser_no
where buser_name in ('�ѹ���', '������', '������') or buser_name is null
order by jikwon_no asc;

select * from v_exam1;

-- ��2) �μ���   �ο���
--     ������     7
-- ���� : �������� ���� ���� �μ� ���
-- ���� ����� ���� ������ v_exam2�� �ۼ�
-- ��� 1
create or replace view v_exam2 as
select buser_name as �μ���, count(*) as �ο���
from vjikwon
inner join buser on buser_num=buser_no
group by buser_name
order by �ο��� desc;

select * from v_exam2 where rownum <= 1;

-- ��� 2
create or replace view v_exam2 as
select buser_name as �μ���, count(*) as �ο���
from vjikwon 
inner join buser on buser_num=buser_no
where buser_name = (
                      select �μ���
                      from(
                            select buser_name as �μ���, count(*) as �ο���
                            from jikwon inner join buser on buser_num=buser_no
                            group by buser_name
                            order by �ο��� desc
                      )
                      where rownum <=1
                      )
group by buser_name;
--having buser_num > all (select conut(buser_num) from jikwon right join buser group by buser_name);
       
select * from v_exam2;

-- ��3) ���� ���� ������ �Ի��� ���Ͽ� �Ի��� ���� ���
--   ������   ����     �μ���   �μ���ȭ
--   �ѱ���  ������    �����   222-2222
-- ���� ����� ���� ������ v_exam3�� �ۼ�  
-- select to_char(sawon_ibsail,'DAY') from sawon;
-- ��� 1
create or replace view v_exam3 as
select jikwon_name as ������, to_char(jikwon_ibsail,'DAY') as ����, buser_name as �μ���, buser_tel as �μ���ȭ
from vjikwon 
inner join buser on buser_num=buser_no;

select * 
from v_exam3 
where ���� = (
                select to_char(jikwon_ibsail,'DAY') 
                from vjikwon 
                group by to_char(jikwon_ibsail,'DAY')
                having count(to_char(jikwon_ibsail,'DAY')) >= all ( -- having count(��~��)�� ���� �ϳ��� all�� �ִ� ���� ��� ��.
                                                  select count(to_char(jikwon_ibsail,'DAY')) 
                                                  from vjikwon 
                                                  group by to_char(jikwon_ibsail,'DAY')));
-- any / all
-- < any : subquery�� ��ȯ�� �� �ִ밪 ���� ���� ~
-- > any : subquery�� ��ȯ�� �� �ִ밪 ���� ū ~
-- < all : subquery�� ��ȯ�� �� �ּҰ� ���� ���� ~
-- > all : subquery�� ��ȯ�� �� �ִ밪 ���� ū ~                                                  

-- ��� 2
create or replace view v_exam3 as
select jikwon_name as ������, to_char(jikwon_ibsail,'DAY') as ����, buser_name as �μ���, buser_tel as �μ���ȭ
from vjikwon left join buser on buser_num=buser_no
where to_char(jikwon_ibsail,'DAY') = (
                                      select ���� --view�� ������� ������ �̰� ���� ��. �ȿ����� ���� ���������� �ۿ��� �������̱� ������ �ۿ����� ���� �� �� ����.
                                      from (
                                            select to_char(jikwon_ibsail,'DAY') as ����, count(*) as �ο���
                                            from vjikwon
                                            group by to_char(jikwon_ibsail,'DAY')
                                            order by �ο��� desc)
                                      where rownum <= 1
                                      );

select * from v_exam3;

-- ����(�����) ���� �� ����, ����
-- �����ڰ� �� client���� ������ ������ �ְ�, �� �������� DB�ڷḦ ����
-- ����)
-- create user ������ identified by ��й�ȣ
-- drop user ������ [cascade]
-- alter user ������ identified by ��й�ȣ

-- system cmd
-- SQL> show user;
-- USER�� "SYSTEM"�Դϴ�
-- SQL> select * from all_users; -- ��� ������ �� �� ����.

-- system cmd
-- SQL> create user kbs identified by 111; -- ����� ���� �����
-- ����ڰ� �����Ǿ����ϴ�.

-- kbs cmd
-- ERROR: -- kbs �α��� �������� �ʾ� ������ ��. 
-- ORA-01045: user KBS lacks CREATE SESSION privilege; logon denied

-- system cmd
-- SQL> grant connect to kbs; -- �α��� ���� �ֱ�.
-- ������ �ο��Ǿ����ϴ�. 

-- kbs cmd
-- SQL> create table aa(bun int, -- create ������ ���� ������ ���� �� �� ����.
--  2  irum varchar2(10));
-- 1�࿡ ����:
-- ORA-01031: ������ ������մϴ�

-- system cmd
-- SQL> grant resource to kbs;
-- ������ �ο��Ǿ����ϴ�.

-- resource ���� : �⺻���� ��ü(table,trigger,index,cluster,sequence��)�� drop,alter,create, 
--                 �÷��� insert,update,delete �� �� �ִ� ������ ��Ƴ��� role 


-- kbs cmd
-- SQL> show user; -- ���� �ο� ��  ������ �� ���̺� ����
-- USER�� "KBS"�Դϴ�
-- SQL> create table aa(bun int,
--   2  irum varchar2(10));
-- ���̺��� �����Ǿ����ϴ�.

-- kbs cmd
-- SQL> insert into aa values(1, 'tom'); -- insert�� ���� ���Ѻο��� ���� ����.
-- insert into aa values(1, 'tom')
-- 1�࿡ ����:
-- ORA-01950: ���̺����̽� 'USERS'�� ���� ������ �����ϴ�. -- ���ͳݿ� ���� �˻��ϸ� �� ����. ã�Ƽ� ��� �� ��.

-- system cmd
-- SQL> alter user kbs default tablespace users quota unlimited on users; -- users�� ���� ���� �ο�
-- ����ڰ� ����Ǿ����ϴ�.

-- kbs cmd
-- SQL> insert into aa values(1, 'tom');
-- 1 ���� ���� ����������ϴ�.

-- kbs cmd
-- SQL> commit; -- ��������� Ŀ��
-- Ŀ���� �Ϸ�Ǿ����ϴ�.

-- kbs cmd
-- SQL> select * from aa;
--       BUN IRUM
------------ --------------------
--         1 tom

-- kbs cmd
-- SQL> select * from session_privs; -- ���� ������ ���� ���� Ȯ��.
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
-- 10 ���� ���õǾ����ϴ�.

-- system cmd
-- SQL> create user mbc identified by 111; -- mbc ����� ���� ����.
-- ����ڰ� �����Ǿ����ϴ�.

-- system cmd
-- SQL> grant connect, resource to mbc; -- mbc ���� �ο�.
-- ������ �ο��Ǿ����ϴ�.

-- mbc cmd
-- SQL> show user;
-- USER�� "MBC"�Դϴ�

-- scott cmd
-- SQL> select jikwon_name
--   2  from scott.vjikwon
--
-- JIKWON_NAME
----------------------
-- ���
-- �̼���
-- �̶̹�
-- �̼���
-- ����ȭ
-- ��θ�
-- ��⸸
-- ä��ȭ
-- ��ġ��
-- �����
-- �ں���
--
-- JIKWON_NAME
----------------------
-- �ڸ�ȭ
-- �ڱ�ȭ
-- ä�̸�
-- ������
-- �ű���
-- �ű��
-- ������
--
-- 18 ���� ���õǾ����ϴ�.

-- system cmd
-- SQL> select * from scott.vjikwon;
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--          2 ���                         20 ����                       1234
-- 03/12/03 ��
--
--          3 �̼���                       20 ����                       6500
-- 11/03/03 ��
--
--          4 �̶̹�                       30 �븮                       5500
-- 10/11/04 ��
--
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--          5 �̼���                                                     3000
-- 13/08/05 ��
--
--          6 ����ȭ                       20 ���                       2950
-- 14/08/05 ��
--
--          7 ��θ�                       40 ����                       8000
-- 04/01/05 ��
--
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--          8 ��⸸                       20 ����                       7000
-- 09/01/01 ��
--
--          9 ä��ȭ                       30 �븮                       5500
-- 11/03/02 ��
--
--         10 ��ġ��                       10 ���                       3700
-- 14/03/02 ��
--
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--         11 �����                       30 ���                       2900
-- 15/09/06 ��
--
--         12 �ں���                       40 ����                       5300
-- 10/03/05 ��
--
--         13 �ڸ�ȭ                       10 �븮                       4900
-- 12/05/01 ��
--
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--         14 �ڱ�ȭ                       40 ���                       2800
-- 17/01/05 ��
--
--         15 ä�̸�                       20 ���                       3200
-- 15/01/03 ��
--
--         16 ������                       10 ���                       3900
-- 13/02/01 ��
--
--
--  JIKWON_NO JIKWON_NAME           BUSER_NUM JIKWON_JIK           JIKWON_PAY
------------ -------------------- ---------- -------------------- ----------
-- JIKWON_I JIKWON_G
---------- --------
--          1 �ű���                       10                            5000
--
--
--         20 �ű��                       20                            5500
--
--
--         21 ������                       30                            4500
--
-- 18 ���� ���õǾ����ϴ�.

-- kbs cmd
-- SQL> select jikwon_name from scott.vjikwon; -- ������ ���� ������ �� �� ����.
-- select jikwon_name from scott.vjikwon
-- 1�࿡ ����:
-- ORA-00942: ���̺� �Ǵ� �䰡 �������� �ʽ��ϴ�

-- scott cmd
-- SQL> select * from kbs.aa;
-- select * from kbs.aa
-- 1�࿡ ����:
-- ORA-00942: ���̺� �Ǵ� �䰡 �������� �ʽ��ϴ�

-- kbs cmd
-- SQL> grant select on aa to scott; -- �Ѳ����� �Ұ���. ���̺� ������ �����ϸ�, ���� �������� ���� �ο� �� �� ����.
-- ������ �ο��Ǿ����ϴ�.

-- scot cmd
-- SQL> insert into kbs.aa values(3, 'james');
-- insert into kbs.aa values(3, 'james')
-- 1�࿡ ����:
-- ORA-01031: ������ ������մϴ�

-- kbs cmd
-- SQL> grant insert on aa to scott; -- insert ���� �ο�.
-- ������ �ο��Ǿ����ϴ�.

-- scott cmd
-- SQL> insert into kbs.aa values(3, 'james'); -- scott���� kbs�� ����.
-- 1 ���� ���� ����������ϴ�.

-- SQL> select * from kbs.aa;
--
--        BUN IRUM
------------ --------------------
--          1 tom
--          3 james
         
-- kbs cmd
-- SQL> grant select on aa to scott, mbc; -- ���Ѻο��� �޸��� �����ؼ� �������� �ο� �� �� ����.
-- ������ �ο��Ǿ����ϴ�.

-- mbc cmd
-- SQL> select * from kbs.aa;
--
--        BUN IRUM
------------ --------------------
--          1 tom

-- kbs cmd 
-- SQL> revoke select on aa from mbc; -- ���Ѻο� ���.
-- ������ ��ҵǾ����ϴ�.

-- kbs cmd
-- SQL> grant all on aa to scott; -- ��� ����(DML)�� ���� ��� scott���� ��.
-- ������ �ο��Ǿ����ϴ�.

-- kbs cmd
-- SQL> revoke all on aa from scott; -- ��� ����(DML)�� ���� ���.
-- ������ ��ҵǾ����ϴ�.

-- system cmd
-- SQL> create role insa; -- �ѷ� ���Ϻο� �� �� ����.. insa�� �ѿ� ������.
-- ���� �����Ǿ����ϴ�.

-- system cmd
-- SQL> grant insa to kbs;
-- ������ �ο��Ǿ����ϴ�.

-- system cmd
-- SQL> create role insa;
-- ���� �����Ǿ����ϴ�.

-- system cmd
-- SQL> grant insa to scott;
-- ������ �ο��Ǿ����ϴ�.

-- systme cmd 
-- SQL> grant insa to mbc, kbs;
-- ������ �ο��Ǿ����ϴ�.

-- scott cmd
-- SQL> select * from kbs.aa;
-- select * from kbs.aa
-- 1�࿡ ����:
-- ORA-00942: ���̺� �Ǵ� �䰡 �������� �ʽ��ϴ�

-- SQL> select * from kbs.aa;
--     BUN IRUM
---------- --------------------
--        1 tom

-- mbc cmd
-- SQL> select * from kbs.aa;
-- select * from kbs.aa
-- 1�࿡ ����:
-- ORA-00942: ���̺� �Ǵ� �䰡 �������� �ʽ��ϴ�

-- kbs cmd
-- SQL> grant selete,delete on aa to insa;

-- scott cmd
-- select * from kbs.aa; -- �ϸ� ����.
-- mbc cmd
-- select * from kbs.aa; -- �ϸ� ����.

-- kbs cmd
-- SQL> revoke all on aa from insa; -- role�� ���� ���� ���
-- ������ ��ҵǾ����ϴ�.

-- system cmd
-- SQL> revoke insa from mbc,kbs;
-- ������ ��ҵǾ����ϴ�.

-- system cmd
-- SQL> drop user mbc; -- ���� ���̸� ���� �Ұ���. ���� �� ����.
-- drop user mbc
-- 1�࿡ ����:
-- ORA-01940: ���� ���ӵǾ� �ִ� ����ڴ� ������ �� �����ϴ�

-- system cmd
-- SQL> drop user mbc;
-- ����ڰ� �����Ǿ����ϴ�.

-- system cmd
-- SQL> drop user kbs;
-- drop user kbs
-- 1�࿡ ����:
-- ORA-01940: ���� ���ӵǾ� �ִ� ����ڴ� ������ �� �����ϴ�

-- system cmd
-- SQL> drop user kbs;
-- drop user kbs
-- 1�࿡ ����:
-- ORA-01922: 'KBS'(��)�� �����Ϸ��� CASCADE�� �����Ͽ��� �մϴ� -- casecad : ���ѿ� ���� ���� ����

-- system cmd
-- SQL> drop user kbs cascade;
-- ����ڰ� �����Ǿ����ϴ�.

-- synonym(���Ǿ�)

--
---------------------
-- Synonym(���Ǿ�)
---------------------
--
-- A. ����
-- - ���Ǿ�(Synonym)�� Table, View, SnapShot,Sequence,Procedure, Function, Package�� ���� ��Ī�̴�.
-- - ���� �� ���� ���Ǿ��� �ΰ��� ������ �ִ�. ���뵿�Ǿ�� public�̶�� Ư������� �׷쿡��
--   �����ϸ� DB�� ��� ����ڰ� ����Ҽ� �ִ�. ���뵿�Ǿ�� �ٸ� ����ڿ� ���� ���뵿�Ǿ��� 
--   ���뼺�� �����Ҽ� �ִ� Ư�� ������� ��Ű���� ����ִ�.
-- B. ����
-- - ������� scott�� Schema�� ���Ե� Emp Table�� ���� puiblic_emp��� ���� Synonym����
-- - Create public synonym public_emp for scott.emp;
-- - �̻�� ���� �������� �����ϸ� Oracle�� �ٸ� ����ڴ� public_emp��� ��Ī�� ����Ͽ� Query �Ҽ��ִ�.
-- - tiger��� User�� public_emp��� ��Ī�� ����Ͽ� ������ ���� Query �Ҽ��ִ�.
-- Sqlplus>select * from public_emp;
-- C. ����
-- - drop public synonym public_emp
--
-------------------------------------------------------------------------
-- ���� Synonym�� �����̶�� ������ ������ public�̶�� Option�� �Ⱦ��� �ȴ�.
 

-- system cmd 
-- SQL> show user
-- USER�� "SYSTEM"�Դϴ�

-- scott cmd
-- SQL> show user;
-- USER�� "SCOTT"�Դϴ�

-- system cmd
-- SQL> create user tom identified by 111;
-- ����ڰ� �����Ǿ����ϴ�.

-- SQL> grant connect, resource to tom;
-- ������ �ο��Ǿ����ϴ�.

-- tom cmd
-- SQL> show user
-- USER�� "TOM"�Դϴ�

-- scott cmd
-- SQL> grant select on jikwon to tom;
-- ������ �ο��Ǿ����ϴ�.

-- tom cmd
-- SQL> create synonym nice for scott.jikwon;
-- create synonym nice for scott.jikwon
-- *
-- 1�࿡ ����:
-- ORA-01031: ������ ������մϴ�

-- tom cmd
-- SQL> select * from user_role_privs; -- ���� synonym�� ���� ������ ����.

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
-- ������ �ο��Ǿ����ϴ�.

-- SQL> grant create public synonym to tom; -- public
-- ������ �ο��Ǿ����ϴ�.

-- tom cmd
-- SQL> create synonym nice for scott.jikwon; -- private ���Ǿ� ����
-- ���Ǿ �����Ǿ����ϴ�.

-- SQL> create public synonym pnice for scott.jikwon; -- public ���Ǿ� ����
-- ���Ǿ �����Ǿ����ϴ�.

-- SQL> select jikwon_name from nice; -- private
--
-- JIKWON_NAME
----------------------
-- ȫ�浿
-- ���
-- �̼���
-- �̶̹�
-- �̼���
-- ����ȭ
-- ��θ�
-- ��⸸
-- ä��ȭ
-- ��ġ��
-- �����
--
-- JIKWON_NAME
----------------------
-- �ں���
-- �ڸ�ȭ
-- �ڱ�ȭ
-- ä�̸�
-- ������
--
-- 16 ���� ���õǾ����ϴ�.
--
-- SQL> select jikwon_name from pnice; -- public
--
-- JIKWON_NAME
----------------------
-- ȫ�浿
-- ���
-- �̼���
-- �̶̹�
-- �̼���
-- ����ȭ
-- ��θ�
-- ��⸸
-- ä��ȭ
-- ��ġ��
-- �����
-- 
-- JIKWON_NAME
----------------------
-- �ں���
-- �ڸ�ȭ
-- �ڱ�ȭ
-- ä�̸�
-- ������
-- 
-- 16 ���� ���õǾ����ϴ�.

-- system cmd
-- SQL> create user oscar identified by 111;
--����ڰ� �����Ǿ����ϴ�.

-- SQL> grant connect, resource to oscar;
-- ������ �ο��Ǿ����ϴ�.

-- scott cmd
-- SQL> grant select on jikwon to oscar; -- select ���� �־���� ��.
-- ������ �ο��Ǿ����ϴ�.

-- oscar cmd
-- SQL> select jikwon_name from pnice; -- public���� �Ǿ��ֱ� ������ tom�� ���� �͵� �� �� ����. ������ nice�� private�̱� ������ �Ⱥ�����.
--
-- JIKWON_NAME
----------------------
-- ȫ�浿
-- ���
-- �̼���
-- �̶̹�
-- �̼���
-- ����ȭ
-- ��θ�
-- ��⸸
-- ä��ȭ
-- ��ġ��
-- �����
--
-- JIKWON_NAME
----------------------
-- �ں���
-- �ڸ�ȭ
-- �ڱ�ȭ
-- ä�̸�
-- ������
--
-- 16 ���� ���õǾ����ϴ�.

-- tom cmd 
-- SQL> drop synonym nice; -- �ٸ� ����� ������� �ʰ� �ֱ� ������ ���� ����.
-- ���Ǿ �����Ǿ����ϴ�.
--
-- SQL> drop synonym pnice; -- �ٸ� ����� ����ϰ� �ֱ� ������ ���� �Ұ���.
-- drop synonym pnice
-- 1�࿡ ����:
-- ORA-01434: ������ ����� ���Ǿ �������� �ʽ��ϴ�.

-- system cmd
-- SQL> drop public synonym pnice; -- public�� system�� �ͼ� �����ؾ� ��.
-- ���Ǿ �����Ǿ����ϴ�.

-- ����)
-- ��� : buser_no   buser_name  inwon
--         10          �ѹ���        2
--         40          ������       1
-- ���� : scott �������� �ٹ� �ο��� 3�� ������ �μ��� ������� v_account �並 �ۼ�.
--        mbc, kbs ����(��ȣ�� �� �� p123)�� ����� broadcast �ѿ�  ��� �� ��,
--        �� �� ������ v_account �並 ����� �� �ֵ��� �Ͻÿ�.
--        �� mbc �������� happy��� ����� synonym�� ����� ����Ͻÿ�.

create or replace view v_account as
select buser_no, buser_name, count(*) as inwon
from jikwon 
inner join buser on buser_num=buser_no
where buser_name in (
                      select �μ���
                      from(
                            select buser_name as �μ���, count(*) as �ο���
                            from jikwon inner join buser on buser_num=buser_no
                            group by buser_name
                            order by �ο��� desc
                      )
                      where �ο��� <= 3
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

-- ��ü cmd

-- system cmd
-- SQL> create user mbc identified by p123;
-- ����ڰ� �����Ǿ����ϴ�.

-- SQL> create user kbs identified by p123;
-- ����ڰ� �����Ǿ����ϴ�.

-- SQL> create role broadcast;
-- ���� �����Ǿ����ϴ�.

-- SQL> grant connect, resource to broadcast;
-- ������ �ο��Ǿ����ϴ�.

-- SQL> grant broadcast to mbc, kbs;
-- ������ �ο��Ǿ����ϴ�.

-- scott cmd
-- SQL> grant select, delete on v_account to broadcast;
-- ������ �ο��Ǿ����ϴ�.

-- kbs cmd
-- SQL> select * from scott.v_account;
--  BUSER_NO BUSER_NAME                INWON
------------ -------------------- ----------
--        30 �����                        3
--        40 ������                        3

-- mbc cmd
-- SQL> create synonym happy for scott.v_account;
-- ���Ǿ �����Ǿ����ϴ�.
-- SQL> select * from happy;
--  BUSER_NO BUSER_NAME                INWON
------------ -------------------- ----------
--        30 �����                        3
--        40 ������                        3

-- kbs cmd
-- SQL> select * from happy;
-- select * from happy
--              *
-- 1�࿡ ����:
-- ORA-00942: ���̺� �Ǵ� �䰡 �������� �ʽ��ϴ�

-- PL/SQL �� Stored Procedure (ī�� DB-RDBMS 40��)
create table aa(bun number, munja varchar2(20), su number);
set serveroutput on; -- pl/sql ���� ����� �ַܼ� ���
select * from aa;

-- ���� ó��
declare
  no number := 0; -- ġȯ
begin
  no := 500 + 300;
  dbms_output.put_line(no);
  insert into aa(bun) values(no);
end;

declare
  type result is record(a number, b number);
  type test is varray(100) of result; -- �迭
  test1 test := test(); -- �迭 �ʱ�ȭ �۾��� ����� ��.
begin
  test1.extend(50); -- ���� �Ҵ�.
  test1(1).a := 10; -- ������ �� �ֱ�.
  test1(1).b := 20;
  dbms_output.put_line(test1(1).a); -- ��¹�.
  dbms_output.put_line(test1(1).b);
end;

-- exception
declare
  counter number(3) := 10;
  re number;
begin
  re := counter / 0; -- ������ ���� 2�� ���� ���� ������ ���� ������, 0���� ���� �� exception���� �Ѿ ���� ���.
  dbms_output.put_line('�����' || re); -- ||�� ���ڿ� ���ϱ�
exception when zero_divide then
  dbms_output.put_line('error');
  when others then -- ���� ���� ���� ��, java�� catch���� ���.
  dbms_output.put_line('��Ÿ error');
end;

create table jiktest as select * from jikwon;
select * from jiktest;

-- �ش� ���̺� ������ ���� ����
declare
  v_a jiktest%rowtype; -- v_a �� jiktest rowtype
begin
  select * into v_a from jiktest where jikwon_no = 1; -- into ������ : into ������ �ִ´ٴ� ��
  insert into aa values(v_a.jikwon_no, v_a.jikwon_name, v_a.jikwon_pay); -- ������.sql
end;

select * from aa;

-- �ش� ���̺� Į�� ������ ���� ����
declare
  a jiktest.jikwon_no%type; -- a�� �����׽�Ʈ �����ѹ��� Ÿ���̶�� ��.
  b jiktest.jikwon_name%type;
  c jiktest.jikwon_pay%type;
begin
  select jikwon_no, jikwon_name,jikwon_pay into a,b,c
  from jiktest
  where jikwon_no=3;
  dbms_output.put_line(a || ' ' || b || ' ' || c);
end;

-- �����Ǵܹ� if
declare
  v_a jiktest%rowtype;
  v_str varchar2(20);
begin
  select * into v_a from jiktest where jikwon_no = 4;
  if (v_a.buser_num=10) 
    then v_str := concat(v_a.jikwon_name, ' 10'); -- concat : ���ڿ� ���ϱ�
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
  dbms_output.put_line('����� ' || v_str);
end;

declare
  v_a jiktest%rowtype;
  v_str varchar2(20);
begin
  select * into v_a from jiktest where jikwon_no = 4;
  if (v_a.buser_num=10) 
    then v_str := concat(v_a.jikwon_name, ' 10'); -- concat : ���ڿ� ���ϱ�
  elsif (v_a.buser_num=20) 
    then v_str := concat(v_a.jikwon_name, ' 20');
  elsif (v_a.buser_num=30) 
    then v_str := concat(v_a.jikwon_name, ' 30');
  elsif (v_a.buser_num=40) 
    then v_str := concat(v_a.jikwon_name, ' 40');
  else
    v_str := concat(v_a.jikwon_name, ' ��Ÿ');
  end if;
  dbms_output.put_line('����� ' || v_str);
end;

-- �ݺ��� for
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

-- �ݺ��� while
declare
  V_count number := 1;
begin
  while(v_count <= 10) loop
    dbms_output.put_line(v_count);
    exit when(v_count = 5);-- Java�� break�� ����.
    v_count := v_count + 1;
  end loop;
end;

-- �ݺ��� while
declare
  v_a number := 0;
  v_b number := 0;
begin
  while(v_a < 10) loop
    v_a := v_a+1;
    if mod(v_a, 2) = 0 then 
      v_b := v_b + 10;
      dbms_output.put_line(v_a || ' ¦�� ' || v_b);
    else
      v_b := v_b + 5;
      dbms_output.put_line(v_a || ' Ȧ�� ' || v_b);
    end if;
  end loop;
end;
