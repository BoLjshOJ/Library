package ru.otus.boljshoj.repos;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.boljshoj.domain.Author;
import ru.otus.boljshoj.domain.Book;
import ru.otus.boljshoj.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public int count() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList().size();
    }

    @Override
    @Transactional
    public void insert(Book book) {
        Author author = em.find(Author.class, book.getAuthor().getId());
        Genre genre = em.find(Genre.class, book.getGenre().getId());
        book.setAuthor(author);
        book.setGenre(genre);
        em.merge(book);
    }

    @Override
    public Book getById(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Book book = em.find(Book.class, id);
        em.remove(book);
    }

    @Override
    public List<Book> getByAuthorId(Long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b join b.author a where a.id = :authorId", Book.class);
        query.setParameter("authorId", id);
        return query.getResultList();
    }
}
