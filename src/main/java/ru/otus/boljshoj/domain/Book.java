package ru.otus.boljshoj.domain;

import java.util.List;

public class Book {
    private Long id;
    private String title;
    private List<Author> authors;

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book {" +
                "id= " + id +
                ", title= '" + title + '\'' +
                '}';
    }
}