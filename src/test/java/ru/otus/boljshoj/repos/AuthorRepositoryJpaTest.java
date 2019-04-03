package ru.otus.boljshoj.repos;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.boljshoj.domain.Author;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataJpaTest
@RunWith(SpringRunner.class)
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void testCount() {
        assertThat(authorRepository.getAll().size()).isEqualTo(4);
    }

    @Test
    void testInsert() {
        Author newAuthor = new Author("newAuthorName", "newAuthorSurname");
        authorRepository.insert(newAuthor);
        Author find = authorRepository.getById(newAuthor.getId());
        assertThat(find.getName()).isEqualTo("newAuthorName");
        assertThat(find.getSurname()).isEqualTo("newAuthorSurname");
    }

    @Test
    void testGetById() {
        assertThat(authorRepository.getById(1L))
                .hasFieldOrPropertyWithValue("name", "authorName1")
                .hasFieldOrPropertyWithValue("surname", "authorSurname1");
    }

    @Test
    void testGetAll() {
        assertThat(authorRepository.getAll())
                .extracting("id", "name", "surname")
                .contains(tuple(1L, "authorName1", "authorSurname1"),
                        tuple(2L, "authorName2", "authorSurname2"),
                        tuple(3L, "authorName3", "authorSurname3"),
                        tuple(4L, "authorName4", "authorSurname4"));
    }

    @Test
    void testDeleteById() {
        Author authorForDelete = authorRepository.getById(4L);
        authorRepository.deleteById(4L);
        assertThat(authorRepository.getAll().size()).isEqualTo(3);
        assertThat(authorRepository.getAll()).doesNotContain(authorForDelete);
    }
}