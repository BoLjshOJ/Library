package ru.otus.boljshoj.repos;

import ru.otus.boljshoj.domain.Author;
import ru.otus.boljshoj.domain.Comment;
import ru.otus.boljshoj.domain.Genre;

import java.util.List;

public interface BookCustomRepository {
    List<Author> findAllAuthors();
    List<Genre> findAllGenres();
    List<Comment> findAllComments();
    void addCommentByBookId(String bookId, Comment comment);
    void deleteCommentsByBookId(String bookId);
}