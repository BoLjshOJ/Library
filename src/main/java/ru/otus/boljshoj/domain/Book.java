package ru.otus.boljshoj.domain;

public class Book {
    private Long id;
    private Author author;
    private Genre genre;
    private String title;

    public Book(Long id, Author author, Genre genre, String title) {
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book {" +
                "id= " + id +
                ", author= " + author +
                ", genre= " + genre +
                ", title='" + title + '\'' +
                '}';
    }
}