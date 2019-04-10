package ru.otus.boljshoj.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.boljshoj.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}