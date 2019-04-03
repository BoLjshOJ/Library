insert into authors (name, surname) values ('George', 'Orwell'), ('Arthur', 'Konan Doil');
insert into genres (name) values ('anthiutopia'), ('detective');
insert into books (author_id, genre_id, title) values (2, 2, 'Sherlock'), (1, 1, '1984');
insert into comments (text, book_id) values ('nice book, you should read it', 1), ('awesome', 2);