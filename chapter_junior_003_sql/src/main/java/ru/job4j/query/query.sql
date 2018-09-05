-- Создание базы данны для задания
create database queryBase;
\c querybase;

-- Создание таблицы типов
create table type (
    id serial primary key,
    name text
);

-- Создание таблицы продуктов
create table product (
    id serial primary key,
    name text,
    type_id integer references type(id),
    expired_date date,
    price real,
    quantity real
);

-- Наполнение таблицы типов
insert into type (name) values ('Сыр');
insert into type (name) values ('Молоко');
insert into type (name) values ('Мучное');

-- Наполнение таблицы продуктов
insert into product (type_id, name, expired_date, price, quantity) values (2, 'Мороженное','2018-12-31', 100.50, 50);
insert into product (type_id, name, expired_date, price, quantity) values (3, 'Пироженное','2018-09-30', 45.80, 15);
insert into product (type_id, name, expired_date, price, quantity) values (1, 'Сыр с плесенью','2020-12-31', 800.00, 10);
insert into product (type_id, name, expired_date, price, quantity) values (2, 'Молоко','2018-09-15', 115.50, 60);
insert into product (type_id, name, expired_date, price, quantity) values (3, 'Хлеб','2018-09-25', 25.10, 5);
insert into product (type_id, name, expired_date, price, quantity) values (2, 'Сметана','2018-09-25', 45.10, 10);
insert into product (type_id, name, expired_date, price, quantity) values (1, 'Сырок (хоть он и не из сыра)','2018-10-31', 15.00, 10);

-- Скрипты на выборку из Бд по заданию:
-- 1) Написать запрос получение всех продуктов с типом "СЫР"
select * from type as t inner join product as prod on t.id = type_id where t.name = 'Сыр';

-- 2) Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from type as t inner join product as prod on t.id = prod.type_id where prod.name like 'Мороженное';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from type as t inner join product as prod on t.id = prod.type_id where prod.expired_date > now() and prod.expired_date < '2018.10.31';

-- 4. Написать запрос, который выводит самый дорогой продукт.
select max(prod.price) from type as t inner join product as prod on t.id = prod.type_id;

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
select count(*) from type as t inner join product as prod on t.id = type_id where t.name = 'Сыр';
select count(*) from type as t inner join product as prod on t.id = type_id where t.name = 'Молоко';
select count(*) from type as t inner join product as prod on t.id = type_id where t.name = 'Мучное';

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from type as t inner join product as prod on t.id = type_id where t.name = 'Сыр';
select * from type as t inner join product as prod on t.id = type_id where t.name = 'Молоко';

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
-- Тут пришлось добавить новое поле (не предусмотренное задачей, так как по дефолту нету поля количества каждого продукта)
select t.name from type as t inner join product as prod on t.id = prod.type_id where prod.quantity < 10;

--  8. Вывести все продукты и их тип.
select prod.name, t.name from type as t inner join product as prod on t.id = type_id;
