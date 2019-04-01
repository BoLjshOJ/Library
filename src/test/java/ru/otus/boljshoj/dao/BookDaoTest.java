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
import ru.otus.boljshoj.domain.Book;
import ru.otus.boljshoj.domain.Genre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

@JdbcTest
@RunWith(SpringRunner.class)
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class})
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application.properties")
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GenreDao genreDao;

    @Test
    public void testGetAll(){
        List<Book> books = bookDao.getAll();
        assertEquals(4, books.size());
    }

    @Test
    public void testGetById(){
        Book book = bookDao.getById((long) 1);
        assertEquals("book1", book.getTitle());
    }

    @Test
    public void testCreateBook(){
        Author author = authorDao.getById((long) 1);
        Genre genre = genreDao.getById((long) 1);
        Book newBook = new Book((long) 5, author, genre, "book5");
        bookDao.insert(newBook);
        assertEquals(author.getName(), bookDao.getById((long) 5).getAuthor().getName());
        assertEquals(genre.getName(), bookDao.getById((long) 5).getGenre().getName());
        assertEquals(newBook.getTitle(), bookDao.getById((long) 5).getTitle());
    }

    @Test
    public void testDeleteBook(){
        int sizeBeforeDelete = bookDao.count();
        bookDao.deleteById((long) 1);
        assertTrue(sizeBeforeDelete > bookDao.count());
    }
}
