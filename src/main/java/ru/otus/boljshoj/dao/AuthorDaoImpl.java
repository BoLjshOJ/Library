package ru.otus.boljshoj.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.boljshoj.domain.Author;

import java.util.Collections;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    private static RowMapper<Author> authorRowMapper = ((resultSet, i) -> {
        Author author = new Author(
                resultSet.getLong("author_id"),
                resultSet.getString("author_name"),
                resultSet.getString("author_surname")
        );
        return author;
    });

    public AuthorDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject(
                "select count(*) from authors",
                Collections.emptyMap(),
                Integer.class
        );
    }

    @Override
    public void insert(Author author) {
        jdbc.update(
                "insert into authors (author_id, author_name, author_surname) values (:id, :name, :surname)",
                new MapSqlParameterSource()
                    .addValue("id", author.getId())
                    .addValue("name", author.getName())
                    .addValue("surname", author.getSurname())
        );
    }

    @Override
    public Author getById(Long id) {
        return jdbc.queryForObject(
                "select * from authors where author_id = :id",
                new MapSqlParameterSource()
                    .addValue("id", id),
                authorRowMapper
        );
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query(
                "select * from authors",
                authorRowMapper
        );
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update(
                "delete from authors where author_id = :id",
                new MapSqlParameterSource()
                    .addValue("id", id)
        );
    }
}