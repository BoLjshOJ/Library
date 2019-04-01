DROP TABLE IF EXISTS authors CASCADE;
CREATE TABLE authors(
    author_id int8 not null,
    author_name varchar(255),
    author_surname varchar(255),
    primary key (author_id)
);

DROP TABLE IF EXISTS genres CASCADE;
CREATE TABLE genres(
    genre_id int8 not null,
    genre_name varchar(255),
    primary key (genre_id)
);

DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE books(
    book_id int8 not null,
    author_id int8,
    genre_id int8,
    book_title varchar(255),
    primary key (book_id)
);

alter table books
    add constraint books_author_fk
        foreign key (author_id) references authors;

alter table books
    add constraint books_genre_fk
        foreign key (genre_id) references genres;