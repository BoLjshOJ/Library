package ru.otus.boljshoj.repos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.boljshoj.domain.Genre;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.tuple;

@DataJpaTest
@RunWith(SpringRunner.class)
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("должен возвращать корректное кол-во жанров в БД")
    public void shouldReturnCorrectGenresCount() {
        assertThat(genreRepository.findAll().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("должен корректно сохранять жанр в БД")
    public void shouldCorrectSaveGenre() {
        Genre newGenre = new Genre("newGenre");
        genreRepository.save(newGenre);
        Genre find = genreRepository.findById(newGenre.getId()).get();
        assertThat(find.getName()).isEqualTo("newGenre");
    }

    @Test
    @DisplayName("должен получать корректный жанр по ID")
    public void shouldGetCorrectGenreById() {
        assertThat(genreRepository.findById(3L).get())
                .hasFieldOrPropertyWithValue("name", "genre3");
    }

    @Test
    @DisplayName("должен возвращать все жанры из БД")
    public void shouldReturnAllGenres() {
        assertThat(genreRepository.findAll())
                .extracting("id", "name")
                .contains(tuple(1L, "genre1"),
                        tuple(2L, "genre2"),
                        tuple(3L, "genre3"),
                        tuple(4L, "genre4"));
    }

    @Test
    @DisplayName("должен удалять жанр из БД по ID")
    public void shouldDeleteGenreById() {
        Genre genreForDelete = genreRepository.findById(4L).get();
        genreRepository.deleteById(4L);
        assertThat(genreRepository.findAll().size()).isEqualTo(3);
        assertThat(genreRepository.findAll()).doesNotContain(genreForDelete);
    }
}