-- Создание базы данны для задания
create database CreateUpdateInsert;
\c createupdateinsert;

-- -----------СОЗДАНИЕ ТАБЛИЦ ДЛЯ БАЗЫ ДАННЫХ-----------
-- Таблица статусов заявок
create table state_of_bid (
    id_state serial primary key,
    name_of_status text,
    desc_of_status text
);

-- Таблица категорий заявок
create table category_of_bid (
    id_category serial primary key,
    name_of_category text,
    desc_of_category text
);

-- Таблица файлов к заявкам
create table files_for_bids (
    id_file serial primary key,
    file_name text,
    file_description text,
    file_path text
);

-- Таблица прав ролей пользователей
create table access_rights (
    id_access serial primary key,
    name_right text,
    description text
);

-- Таблица ролей, ссылающаяся на таблицу прав ролей
create table roles (
    id_role serial primary key,
    name_of_role text,
    access integer references access_rights(id_access)
);

-- Таблица пользователей
create table users (
    id_user serial primary key,
    name_user text,
    role_user integer references roles(id_role)
);

-- Таблица заявок
create table bids (
    id_bid serial primary key,
    bid_date timestamp,
    bid_description text,
    bid_category integer references category_of_bid(id_category),
    user_id integer references users(id_user),
    bid_status integer references state_of_bid(id_state),
    bid_files integer references files_for_bids(id_file)
);

-- Таблица комментарий к заявкам
create table comments_for_bids (
    id_comment serial primary key,
    user_id integer references users(id_user),
    text_comment text,
    date_comment timestamp,
    id_bid integer references bids(id_bid)
);

-- ----------- НАПОЛНЕНИЕ ТАБЛИЦ БАЗЫ ДАННЫХ ПЕРВОНАЧАЛЬНЫМИ ЗНАЧЕНИЯМИ-----------

-- Первоначальное наполнение таблицы статусов заявок
insert into state_of_bid (name_of_status, desc_of_status) values ('Открыта', 'Заявка открыта и ожидает выполнения');
insert into state_of_bid (name_of_status, desc_of_status) values ('Закрыта', 'Заявка закрыта и не требует выполнения');

-- Первоначальное наполнение таблицы категорий заявок
insert into category_of_bid (name_of_category, desc_of_category) values ('Ученическая заявка', 'Заявка от ученика');
insert into category_of_bid (name_of_category, desc_of_category) values ('Рабочая заявка', 'Заявка от работодателей');

-- Первоначальное наполнение таблицы файлов к заявкам
insert into files_for_bids (file_name, file_description, file_path) values ('ФотоУч.jpeg', 'Фотография ученика', 'path/usr/local/studentsPhotos/');
insert into files_for_bids (file_name, file_description, file_path) values ('ЛогоФирмы.jpeg', 'Логотип фирмы-раотодателя', 'path/usr/local/firmsLogos');

-- Первоначальное наполнение таблицы прав ролей пользователей
insert into access_rights (name_right, description) values ('Обычный пользователь', 'Имеет право на записьм новых заявок и комментариев');
insert into access_rights (name_right, description) values ('Администратор', 'Имеет право читать, редактировать и удалять любые комментарии и заявки');

-- Первоначальное наполнение таблицы ролей
insert into roles (name_of_role, access) values ('Обычный пользователь', 1);
insert into roles (name_of_role, access) values ('Администратор', 2);

-- Первоначальное наполнение таблицы пользователей
insert into users (name_user, role_user) values ('Иван', 1);
insert into users (name_user, role_user) values ('ООО Ромашка', 2);

-- Первоначальное наполнение таблицы заявок
insert into bids (bid_date, bid_description, bid_category, user_id, bid_status, bid_files) values ('2018-09-04 17:15:30', 'Проверка заданий', 1, 1, 2, 1);
insert into bids (bid_date, bid_description, bid_category, user_id, bid_status, bid_files) values ('2018-09-04 18:15:30', 'Открытая вакансия в нашей фирме', 2, 1, 1, 2);