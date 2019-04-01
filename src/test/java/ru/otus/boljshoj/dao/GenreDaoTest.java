package ru.otus.boljshoj.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.boljshoj.domain.Genre;

import java.util.List;

import static org.junit.Assert.*;

@JdbcTest
@RunWith(SpringRunner.class)
@Import(GenreDaoImpl.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application.properties")
public class GenreDaoTest {
    @Autowired
    private GenreDao genreDao;

    @Test
    public void testGetAll(){
        List<Genre> genres = genreDao.getAll();
        assertEquals(3, genres.size());
    }

    @Test
    public void testGenreName(){
        List<Genre> genres = genreDao.getAll();
        genres.forEach(genre -> assertTrue(genre.getName().matches("^genre[123]$")));
    }

    @Test
    public void testCreateGenre(){
        Genre newGenre = new Genre((long) 4, "genre4");
        genreDao.insert(newGenre);
        assertEquals(newGenre.getId(), genreDao.getById((long) 4).getId());
        assertEquals(newGenre.getName(), genreDao.getById((long) 4).getName());
    }
}