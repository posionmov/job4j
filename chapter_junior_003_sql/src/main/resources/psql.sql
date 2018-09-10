-- Создание БД если такой еще нет
create database selectcompany;

-- Переход в даннуб БД
\\c selectcompany;

-- Создание таблицы компаний если такой еще нет
create table company (id integer NOT NULL, name character varying, CONSTRAINT company_pkey PRIMARY KEY (id));

-- Создание таблицы людей если такой еще нет
create table person(id integer NOT NULL, name character varying, company_id integer, CONSTRAINT person_pkey PRIMARY KEY (id));

-- Получить одиним запросом имена людей, которые не входят в компанию с id = 5, а так же имена компаний, в которых они есть
select (per.name, comp.name) from person as per left outer join company as comp on per.company_id = comp.id where comp.id <> 5;

-- Получить Имя компании с самым большим количеством сотрудников, а так же количество данных сотрудников
select comp.name as company_name, count(comp.name) as total_counts from person as per left outer join company as comp on per.company_id = comp.id group by comp.name order by comp.name asc limit 1;
