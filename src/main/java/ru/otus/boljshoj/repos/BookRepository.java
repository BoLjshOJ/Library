package ru.otus.boljshoj.repos;

import ru.otus.boljshoj.domain.Book;

import java.util.List;

public interface BookRepository {
    int count();
    void insert(Book book);
    Book getById(Long id);
    List<Book> getAll();
    void deleteById(Long id);
    List<Book> getByAuthorId(Long id);
}