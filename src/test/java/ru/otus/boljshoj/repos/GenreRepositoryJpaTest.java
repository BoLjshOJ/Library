package ru.otus.boljshoj.repos;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.boljshoj.domain.Genre;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataJpaTest
@RunWith(SpringRunner.class)
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void testCount() {
        assertThat(genreRepository.getAll().size()).isEqualTo(4);
    }

    @Test
    void testInsert() {
        Genre newGenre = new Genre("newGenre");
        genreRepository.insert(newGenre);
        Genre find = genreRepository.getById(newGenre.getId());
        assertThat(find.getName()).isEqualTo("newGenre");
    }

    @Test
    void testGetById() {
        assertThat(genreRepository.getById(3L))
                .hasFieldOrPropertyWithValue("name", "genre3");
    }

    @Test
    void testGetAll() {
        assertThat(genreRepository.getAll())
                .extracting("id", "name")
                .contains(tuple(1L, "genre1"),
                        tuple(2L, "genre2"),
                        tuple(3L, "genre3"),
                        tuple(4L, "genre4"));
    }

    @Test
    void testDeleteById() {
        Genre genreForDelete = genreRepository.getById(4L);
        genreRepository.deleteById(4L);
        assertThat(genreRepository.getAll().size()).isEqualTo(3);
        assertThat(genreRepository.getAll()).doesNotContain(genreForDelete);
    }
}