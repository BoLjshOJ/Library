package ru.otus.boljshoj.repos;

import ru.otus.boljshoj.domain.Comment;

import java.util.List;

public interface CommentRepository {
    int count();
    void insert(Comment comment);
    Comment getById(Long id);
    List<Comment> getAll();
    void deleteById(Long id);
    List<Comment> getByBookId(Long bookId);
}
