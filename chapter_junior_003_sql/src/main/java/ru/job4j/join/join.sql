-- Создание базы данны для задания
create database outerjoin;
\c outerjoin;

-- Создание таблицы кузовов
create table carcases (
    id serial primary key,
    name varchar(200)
);

-- Создание таблицы двигателей
create table engines (
    id serial primary key,
    name varchar(200)
);

-- Создание таблицы коробок передач
create table transmissions (
    id serial primary key,
    name varchar(200)
);

-- Создание таблцы машин
create table cars (
    id serial primary key,
    name varchar(200),
    id_carcase integer references carcases(id) not null,
    id_engine integer references engines(id) not null,
    id_transmission integer references transmissions(id) not null
);

-- Заполнение таблиц
-- 1) Заполнение таблицы кузовов
insert into carcases (name) values ('Седан');
insert into carcases (name) values ('Купе');
insert into carcases (name) values ('Внедорожник');

-- 2) Заполнение таблицы двигателей
insert into engines (name) values ('Спортивный');
insert into engines (name) values ('Внедорожник');
insert into engines (name) values ('Экономный');

-- 3) Заполнение таблицы коробок передач
insert into transmissions (name) values ('4-х ступенчатая');
insert into transmissions (name) values ('6-ти ступенчатая');
insert into transmissions (name) values ('7-ми ступенчатая');

-- 4) Заполнение таблицы машин
insert into cars (name, id_carcase, id_engine, id_transmission) values ('Машина отца', 3, 2, 2);
insert into cars (name, id_carcase, id_engine, id_transmission) values ('Машина сына', 2, 1, 3);
insert into cars (name, id_carcase, id_engine, id_transmission) values ('Машина дочери', 1, 3, 1);

-- Создание SQL запросов
-- 1) Вывести список всех машин и все привязанные к ним детали.
select (c.name, cases.name, eng.name, trans.name) from
    cars as c
    left outer join carcases as cases on cases.id = c.id_carcase
    left outer join engines as eng on eng.id = c.id_engine
    left outer join transmissions as trans on trans.id = c.id_transmission;


-- 2) Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
-- A) Вывод списка деталей, которые не используются в машине отца
-- A.1) Вывод неиспользуемых кузовов
select (cases.name) from cars as c right outer join carcases as cases on cases.id = c.id_carcase where c.id <> 1;

-- A.2) Вывод неиспользуемых двигателей
select (eng.name) from cars as c right outer join engines as eng on eng.id =c.id_engine where c.id <> 1;

-- A.3) Вывод неиспользуемых коробок передач
select (tr.name) from cars as c right outer join transmissions as tr on tr.id = c.id_transmission where c.id <> 1;


-- Б) Вывод списка деталей, которые не используются в машине сына
-- Б.1) Вывод неиспользуемых кузовов
select (cases.name) from cars as c right outer join carcases as cases on cases.id = c.id_carcase where c.id <> 2;

-- Б.2) Вывод неиспользуемых двигателей
select (eng.name) from cars as c right outer join engines as eng on eng.id =c.id_engine where c.id <> 2;

-- Б.3) Вывод неиспользуемых коробок передач
select (tr.name) from cars as c right outer join transmissions as tr on tr.id = c.id_transmission where c.id <> 2;


-- С) Вывод списка деталей, которые не используются в машине дочери
-- С.1) Вывод неиспользуемых кузовов
select (cases.name) from cars as c right outer join carcases as cases on cases.id = c.id_carcase where c.id <> 3;

-- С.2) Вывод неиспользуемых двигателей
select (eng.name) from cars as c right outer join engines as eng on eng.id =c.id_engine where c.id <> 3;

-- С.3) Вывод неиспользуемых коробок передач
select (tr.name) from cars as c right outer join transmissions as tr on tr.id = c.id_transmission where c.id <> 3;