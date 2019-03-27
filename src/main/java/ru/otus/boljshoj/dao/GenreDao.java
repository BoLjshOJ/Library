package ru.otus.boljshoj.dao;

import ru.otus.boljshoj.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    void insert(Genre genre);
    Genre getById(Long id);
    List<Genre> getAll();
    void deleteById(Long id);
}