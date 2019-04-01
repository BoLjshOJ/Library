package ru.otus.boljshoj.dao;

import ru.otus.boljshoj.domain.Book;

import java.util.List;

public interface BookDao {
    int count();
    void insert(Book book);
    Book getById(Long id);
    List<Book> getAll();
    void deleteById(Long id);
    List<Book> getByAuthorId(Long id);
}
