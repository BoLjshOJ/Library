package ru.otus.boljshoj.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.boljshoj.domain.Author;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@JdbcTest
@RunWith(SpringRunner.class)
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application.properties")
public class AuthorDaoTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testGetAll(){
        List<Author> authors = authorDao.getAll();
        assertEquals(3, authors.size());
    }

    @Test
    public void testAuthorNameAndSurname(){
        List<Author> authors = authorDao.getAll();
        authors.forEach(author -> assertTrue(author.getName().matches("^authorName[123]$")));
        authors.forEach(author -> assertTrue(author.getSurname().matches("^authorSurname[123]$")));
    }

    @Test
    public void testGetAuthorById(){
        Author author = authorDao.getById((long) 1);
        assertEquals("authorName1", author.getName());
        assertEquals("authorSurname1", author.getSurname());
    }

    @Test
    public void testCreateAuthor(){
        Author newAuthor = new Author((long) 4, "authorName4", "authorSurname4");
        authorDao.insert(newAuthor);
        assertEquals(newAuthor.getId(), authorDao.getById((long) 4).getId());
        assertEquals(newAuthor.getName(), authorDao.getById((long) 4).getName());
        assertEquals(newAuthor.getSurname(), authorDao.getById((long) 4).getSurname());
    }
}