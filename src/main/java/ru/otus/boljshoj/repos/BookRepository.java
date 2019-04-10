package ru.otus.boljshoj.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.boljshoj.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByAuthorId(Long authorId);

    @Override
    @Query("select b from Book b join fetch b.author join fetch b.genre")
    List<Book> findAll();
}