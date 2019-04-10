package ru.otus.boljshoj.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.boljshoj.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}