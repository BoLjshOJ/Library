package ru.otus.boljshoj.repos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.boljshoj.config.MongoConf;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataMongoTest
@Import(MongoConf.class)
@DirtiesContext
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("находит книги по автору")
    void findByAuthorNameAndAuthorSurname() {
        assertThat(bookRepository.findByAuthorNameAndAuthorSurname("Михаил", "Булгаков"))
                .extracting("title")
                .contains("Мастер и Маргарита", "Собачье сердце");
    }

    @Test
    @DisplayName("находит книги по жанру")
    void findByGenreName() {
        assertThat(bookRepository.findByGenreName("Роман"))
                .extracting("title")
                .contains("Гордость и предубеждение", "Война и Мир");
    }

    @Test
    @DisplayName("удаляет книги автора")
    void removeByAuthorNameAndAuthorSurname() {
        bookRepository.removeByAuthorNameAndAuthorSurname("Михаил", "Булгаков");
        assertThat(bookRepository.findAll())
                .extracting("author.name", "author.surname")
                .doesNotContain(tuple("Михаил", "Булгаков"));
    }

    @Test
    @DisplayName("удаляет книги жанра")
    void removeByGenreName() {
        bookRepository.removeByGenreName("Фэнтези");
        assertThat(bookRepository.findAll())
                .extracting("genre.name")
                .doesNotContain(tuple("Фэнтези"));
    }
}