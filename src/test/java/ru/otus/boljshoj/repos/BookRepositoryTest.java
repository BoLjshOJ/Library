package ru.otus.boljshoj.repos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.boljshoj.domain.Author;
import ru.otus.boljshoj.domain.Book;
import ru.otus.boljshoj.domain.Genre;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataMongoTest
@DirtiesContext
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void initTest() {
        Book bookForTest = new Book("Title1", new Author("Name1", "Surname1"), new Genre("Genre1"), new ArrayList<>());
        Book bookForTest2 = new Book("Title2", new Author("Name1", "Surname1"), new Genre("Genre2"), new ArrayList<>());
        Book bookForTest3 = new Book("Title3", new Author("Name2", "Surname2"), new Genre("Genre2"), new ArrayList<>());
        bookRepository.save(bookForTest);
        bookRepository.save(bookForTest2);
        bookRepository.save(bookForTest3);
    }

    @Test
    @DisplayName("находит книги по автору")
    void findByAuthorNameAndAuthorSurname() {
        assertThat(bookRepository.findByAuthorNameAndAuthorSurname("Name1", "Surname1"))
                .extracting("title")
                .contains("Title1", "Title2");
    }

    @Test
    @DisplayName("находит книги по жанру")
    void findByGenreName() {
        assertThat(bookRepository.findByGenreName("Genre2"))
                .extracting("title")
                .contains("Title2", "Title3");
    }

    @Test
    @DisplayName("удаляет книги автора")
    void removeByAuthorNameAndAuthorSurname() {
        bookRepository.removeByAuthorNameAndAuthorSurname("Name1", "Surname1");
        assertThat(bookRepository.findAll())
                .extracting("author.name", "author.surname")
                .doesNotContain(tuple("Name1", "Surname1"));
    }

    @Test
    @DisplayName("удаляет книги жанра")
    void removeByGenreName() {
        bookRepository.removeByGenreName("Genre2");
        assertThat(bookRepository.findAll())
                .extracting("genre.name")
                .doesNotContain(tuple("Genre2"));
    }
}