package ru.otus.boljshoj.repos;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.boljshoj.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public int count() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList().size();
    }

    @Override
    @Transactional
    public void insert(Genre genre) {
        em.persist(genre);
    }

    @Override
    public Genre getById(Long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Genre genre = em.find(Genre.class, id);
        em.remove(genre);
    }
}
