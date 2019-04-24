package ru.otus.boljshoj.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.boljshoj.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>, BookCustomRepository {
       List<Book> findByAuthorNameAndAuthorSurname(String authorName, String authorSurname);
       List<Book> findByGenreName(String genreName);
       void removeByAuthorNameAndAuthorSurname(String authorName, String authorSurname);
       void removeByGenreName(String genreName);
}