drop table person;

drop sequence seq_person_id;

create table person (
    person_id number(5),
    name varchar2(30) not null,
    hp varchar2(20),
    company varchar2(20)
);

create sequence seq_person_id
increment by 1
start with 1
NOCACHE;

insert into person values (seq_person_id.nextval, '이효리', '010-1111-1111', '02-1111-1111');
insert into person values (seq_person_id.nextval, '정우성', '010-2222-2222', '02-2222-2222');
insert into person values (seq_person_id.nextval, '유재석', '010-3333-3333', '02-3333-3333');
insert into person values (seq_person_id.nextval, '이정재', '010-4444-4444', '02-4444-4444');
insert into person values (seq_person_id.nextval, '서장훈', '010-5555-5555', '02-5555-5555');

select  person_id,
        name,
        hp,
        company
from person;

update person
set hp = '010-9999-9999',
    company = '02-9999-9999'
where person_id = 4;

delete person
where person_id =5;