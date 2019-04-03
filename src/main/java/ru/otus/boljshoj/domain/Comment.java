package ru.otus.boljshoj.domain;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private Book book;

    public Comment() {
    }

    public Comment(Long id, Book book, String text){
        this.id = id;
        this.book = book;
        this.text = text;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return book.getTitle() + " : " + text;
    }
}