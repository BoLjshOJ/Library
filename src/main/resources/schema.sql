DROP TABLE IF EXISTS authors;
CREATE TABLE authors(
    id int8 not null,
    name varchar(255),
    surname varchar(255),
    book_id int8,
    primary key (id)
);

DROP TABLE IF EXISTS books;
CREATE TABLE books(
    id int8 not null,
    title varchar(255),
    author_id int8,
    primary key (id)
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres(
    id int8 not null,
    name varchar(255),
    book_id int8,
    primary key (id)
);