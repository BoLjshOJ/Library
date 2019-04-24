package ru.otus.boljshoj.repos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.boljshoj.config.MongoConf;
import ru.otus.boljshoj.domain.Comment;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataMongoTest
@Import(MongoConf.class)
@DirtiesContext
class BookCustomRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("находит всех авторов")
    void findAllAuthors() {
        assertThat(bookRepository.findAllAuthors())
                .hasSize(4)
                .extracting("name", "surname")
                .contains(tuple("Михаил", "Булгаков"),
                        tuple("Джейн", "Остин"),
                        tuple("Джоан", "Роулинг"),
                        tuple("Лев", "Толстой"));
    }

    @Test
    @DisplayName("находит все жанры")
    void findAllGenres() {
        assertThat(bookRepository.findAllGenres())
                .hasSize(3)
                .extracting("name")
                .contains("Мистика", "Роман", "Фэнтези");
    }

    @Test
    @DisplayName("добавляет комментарий к книге")
    void addCommentByBookId() {
        bookRepository.addCommentByBookId("3", new Comment("Тест"));
        assertThat(bookRepository.findById("3").get().getComments())
                .hasSize(1)
                .extracting("text")
                .contains("Тест");
    }

    @Test
    @DisplayName("удаляет комментарии у книги")
    void deleteCommentsByBookId() {
        bookRepository.deleteCommentsByBookId("1");
        assertThat(bookRepository.findById("1").get().getComments()).isNull();
    }
}