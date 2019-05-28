package ru.otus.boljshoj.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Genre {

    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}