package ru.otus.boljshoj.repos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
    @DisplayName("должен возвращать корректное количество книг в БД")
    void shouldReturnCorrectBooksCount() {
        assertThat(bookRepository.findAll().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("должен корректно сохранять книгу в БД")
    void shouldCorrectSaveBook() {
        Book newBook = new Book(authorRepository.findById(1L).get(), genreRepository.findById(1L).get(), "newBook");
        bookRepository.save(newBook);
        Book find = bookRepository.findById(6L).get();
        assertThat(find.getTitle()).isEqualTo(newBook.getTitle());
    }

    @Test
    @DisplayName("должен получать корректную книгу по ID")
    void shouldReturnBookById() {
        assertThat(bookRepository.findById(3L).get())
                .hasFieldOrPropertyWithValue("title", "book3");
    }

    @Test
    @DisplayName("должен возвращать все книги из БД")
    void shouldReturnAllBooks() {
        assertThat(bookRepository.findAll().size()).isEqualTo(5);
        assertThat(bookRepository.findAll()).extracting("title").contains("book1", "book2", "book3", "book4", "book5");
    }

    @Test
    @DisplayName("должен удалять книгу из БД по ID")
    void shouldDeleteBookById() {
        Book bookForDelete = bookRepository.findById(5L).get();
        bookRepository.deleteById(5L);
        assertThat(bookRepository.findAll().size()).isEqualTo(4);
        assertThat(bookRepository.findAll()).doesNotContain(bookForDelete);
    }

    @Test
    @DisplayName("должен возвращать все книги автора по его ID")
    void shouldReturnCorrectBooksByAuthorId() {
        assertThat(bookRepository.findBooksByAuthorId(1L)).contains(bookRepository.findById(1L).get(), bookRepository.findById(2L).get());
    }
}