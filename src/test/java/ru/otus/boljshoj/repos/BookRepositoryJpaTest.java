package ru.otus.boljshoj.repos;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.boljshoj.domain.Book;

import static org.assertj.core.api.Java6Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@Import({BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class})
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    void testCount() {
        assertThat(bookRepository.getAll().size()).isEqualTo(5);
    }

    @Test
    void testInsert() {
        Book newBook = new Book(authorRepository.getById(1L), genreRepository.getById(1L), "newBook");
        bookRepository.insert(newBook);
        Book find = bookRepository.getById(6L);
        assertThat(find.getTitle()).isEqualTo(newBook.getTitle());
    }

    @Test
    void testGetById() {
        assertThat(bookRepository.getById(3L))
                .hasFieldOrPropertyWithValue("title", "book3");
    }

    @Test
    void testGetAll() {
        assertThat(bookRepository.getAll().size()).isEqualTo(5);
        Book one = bookRepository.getById(1L);
        Book two = bookRepository.getById(2L);
        Book three = bookRepository.getById(3L);
        Book four = bookRepository.getById(4L);
        Book five = bookRepository.getById(5L);
        assertThat(bookRepository.getAll()).contains(one, two, three, four, five);
    }

    @Test
    void testDeleteById() {
        Book bookForDelete = bookRepository.getById(5L);
        bookRepository.deleteById(5L);
        assertThat(bookRepository.getAll().size()).isEqualTo(4);
        assertThat(bookRepository.getAll()).doesNotContain(bookForDelete);
    }

    @Test
    void testGetByAuthorId() {
        assertThat(bookRepository.getByAuthorId(1L)).contains(bookRepository.getById(1L), bookRepository.getById(2L));
    }
}