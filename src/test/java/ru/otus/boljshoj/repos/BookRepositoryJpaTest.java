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
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    void testCount() {
        assertThat(bookRepository.findAll().size()).isEqualTo(5);
    }

    @Test
    void testInsert() {
        Book newBook = new Book(authorRepository.findById(1L).get(), genreRepository.findById(1L).get(), "newBook");
        bookRepository.save(newBook);
        Book find = bookRepository.findById(6L).get();
        assertThat(find.getTitle()).isEqualTo(newBook.getTitle());
    }

    @Test
    void testGetById() {
        assertThat(bookRepository.findById(3L).get())
                .hasFieldOrPropertyWithValue("title", "book3");
    }

    @Test
    void testGetAll() {
        assertThat(bookRepository.findAll().size()).isEqualTo(5);
        assertThat(bookRepository.findAll()).extracting("title").contains("book1", "book2", "book3", "book4", "book5");
    }

    @Test
    void testDeleteById() {
        Book bookForDelete = bookRepository.findById(5L).get();
        bookRepository.deleteById(5L);
        assertThat(bookRepository.findAll().size()).isEqualTo(4);
        assertThat(bookRepository.findAll()).doesNotContain(bookForDelete);
    }

    @Test
    void testGetByAuthorId() {
        assertThat(bookRepository.findBooksByAuthorId(1L)).contains(bookRepository.findById(1L).get(), bookRepository.findById(2L).get());
    }
}