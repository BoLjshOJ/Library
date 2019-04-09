package ru.otus.boljshoj.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.boljshoj.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByBookId(Long bookId);
}
