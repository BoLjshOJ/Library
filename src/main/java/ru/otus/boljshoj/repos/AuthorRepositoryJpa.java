package ru.otus.boljshoj.repos;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.boljshoj.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList().size();
    }

    @Override
    @Transactional
    public void insert(Author author) {
        em.persist(author);
    }

    @Override
    public Author getById(Long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Author author = em.find(Author.class, id);
        em.remove(author);
    }
}