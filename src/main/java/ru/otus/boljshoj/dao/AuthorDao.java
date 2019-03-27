package ru.otus.boljshoj.dao;

import ru.otus.boljshoj.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();
    void insert(Author author);
    Author getById(Long id);
    List<Author> getAll();
    void deleteById(Long id);
}
