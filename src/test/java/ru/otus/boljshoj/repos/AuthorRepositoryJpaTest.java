package ru.otus.boljshoj.repos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.boljshoj.domain.Author;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataJpaTest
@RunWith(SpringRunner.class)
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("должен возвращать корректное кол-во авторов в БД")
    public void shouldReturnCorrectAuthorsCount() {
        assertThat(authorRepository.findAll().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("должен корректно сохранять автора")
    public void shouldCorrectSaveAuthor() {
        Author newAuthor = new Author("newAuthorName", "newAuthorSurname");
        authorRepository.save(newAuthor);
        Author find = authorRepository.findById(newAuthor.getId()).get();
        assertThat(find.getName()).isEqualTo("newAuthorName");
        assertThat(find.getSurname()).isEqualTo("newAuthorSurname");
    }

    @Test
    @DisplayName("должен получать корректного автора по ID")
    public void shouldGetCorrectAuthorById() {
        assertThat(authorRepository.findById(1L).get())
                .hasFieldOrPropertyWithValue("name", "authorName1")
                .hasFieldOrPropertyWithValue("surname", "authorSurname1");
    }

    @Test
    @DisplayName("должен возвращать всех авторов из БД")
    public void shouldReturnAllAuthors() {
        assertThat(authorRepository.findAll())
                .extracting("id", "name", "surname")
                .contains(tuple(1L, "authorName1", "authorSurname1"),
                        tuple(2L, "authorName2", "authorSurname2"),
                        tuple(3L, "authorName3", "authorSurname3"),
                        tuple(4L, "authorName4", "authorSurname4"));
    }

    @Test
    @DisplayName("должен удалять автора из БД по ID")
    public void shouldDeleteAuthorById() {
        Author authorForDelete = authorRepository.findById(4L).get();
        authorRepository.deleteById(4L);
        assertThat(authorRepository.findAll().size()).isEqualTo(3);
        assertThat(authorRepository.findAll()).doesNotContain(authorForDelete);
    }
}