package ru.otus.boljshoj.repos;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.boljshoj.domain.Book;
import ru.otus.boljshoj.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public int count() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList().size();
    }

    @Override
    @Transactional
    public void insert(Comment comment) {
        Book book = em.find(Book.class, comment.getBook().getId());
        comment.setBook(book);
        em.merge(comment);
    }

    @Override
    public Comment getById(Long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }

    @Override
    public List<Comment> getByBookId(Long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c join c.book b where b.id = :bookId", Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }
}