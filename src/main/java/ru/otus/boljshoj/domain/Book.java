package ru.otus.boljshoj.domain;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Author author;
    @ManyToOne
    private Genre genre;

    private String title;

    public Book() {
    }

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