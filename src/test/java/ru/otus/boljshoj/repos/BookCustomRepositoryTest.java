package ru.otus.boljshoj.repos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.boljshoj.domain.Author;
import ru.otus.boljshoj.domain.Book;
import ru.otus.boljshoj.domain.Comment;
import ru.otus.boljshoj.domain.Genre;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataMongoTest
@DirtiesContext
class BookCustomRepositoryTest {

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
    @DisplayName("находит всех авторов")
    void findAllAuthors() {
        assertThat(bookRepository.findAllAuthors())
                .extracting("name", "surname")
                .contains(tuple("Name1", "Surname1"),
                        tuple("Name2", "Surname2"));
    }

    @Test
    @DisplayName("находит все жанры")
    void findAllGenres() {
        assertThat(bookRepository.findAllGenres())
                .extracting("name")
                .contains("Genre1", "Genre2");
    }

    @Test
    @DisplayName("добавляет комментарий по ID книги")
    void addCommentByBookId(){
        String id = bookRepository.findAll().get(0).getId();
        bookRepository.deleteCommentsByBookId(id);
        bookRepository.addCommentByBookId(id, new Comment("Test"));
        assertThat(bookRepository.findById(id).get().getComments().get(0).getText()).isEqualTo("Test");
    }

    @Test
    @DisplayName("удаляет комментарий по ID книги")
    void delCommentByBookId(){
        String id = bookRepository.findAll().get(0).getId();
        bookRepository.deleteCommentsByBookId(id);
        assertThat(bookRepository.findById(id).get().getComments()).isNull();
    }
}