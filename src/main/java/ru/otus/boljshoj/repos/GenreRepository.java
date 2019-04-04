package ru.otus.boljshoj.repos;

import ru.otus.boljshoj.domain.Genre;

import java.util.List;

public interface GenreRepository {
    int count();
    void insert(Genre genre);
    Genre getById(Long id);
    List<Genre> getAll();
    void deleteById(Long id);
}