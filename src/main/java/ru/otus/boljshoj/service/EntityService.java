package ru.otus.boljshoj.service;

public interface EntityService {
    void count(String entity);
    void all(String entity);
    void add(String entity);
    void del(String param);
    void find(String param);
}