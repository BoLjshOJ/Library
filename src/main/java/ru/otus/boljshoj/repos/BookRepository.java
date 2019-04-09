package ru.otus.boljshoj.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.boljshoj.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByAuthorId(Long authorId);
}