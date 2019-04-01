package ru.otus.boljshoj.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.boljshoj.domain.Genre;

import java.util.Collections;
import java.util.List;

@Repository
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    private static RowMapper<Genre> genreRowMapper = (resultSet, i) -> {
        Genre genre = new Genre(
                resultSet.getLong("genre_id"),
                resultSet.getString("genre_name")
        );
        return genre;
    };

    public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject(
                "select count(*) from genres",
                Collections.emptyMap(),
                Integer.class
        );
    }

    @Override
    public void insert(Genre genre) {
        jdbc.update(
                "insert into genres (genre_id, genre_name) values (:id, :name)",
                new MapSqlParameterSource()
                        .addValue("id", genre.getId())
                        .addValue("name", genre.getName())
        );
    }

    @Override
    public Genre getById(Long id) {
       return jdbc.queryForObject(
               "select * from genres where genre_id = :id",
               new MapSqlParameterSource()
                    .addValue("id", id),
               genreRowMapper);
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query(
                "select * from genres",
                genreRowMapper
        );
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update(
                "delete from genres where genre_id = :id",
                new MapSqlParameterSource()
                    .addValue("id", id)
        );
    }
}