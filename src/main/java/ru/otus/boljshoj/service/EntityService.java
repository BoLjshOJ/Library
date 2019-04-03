package ru.otus.boljshoj.service;

public interface EntityService {
    void count(String entity);
    void get(String entity, Long id);
    void all(String entity);
    void delete(String entity, Long id);
    void add(String entity);
    void getBookByAuthorID(Long id);
    void getCommentsByBookID(Long id);
}