package ru.otus.boljshoj.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.boljshoj.domain.Author;
import ru.otus.boljshoj.domain.Book;
import ru.otus.boljshoj.domain.Genre;

import java.util.Collections;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    private final String SELECT_ALL =
            "select b.book_id, a.author_id, a.author_name, a.author_surname, g.genre_id, g.genre_name, b.book_title from books b inner join authors a on a.author_id = b.author_id inner join genres g on g.genre_id = b.genre_id";

    private static RowMapper<Book> bookRowMapper = (resultSet, i) -> {
        long id = resultSet.getLong("book_id");
        String title = resultSet.getString("book_title");

        Author author = new Author(
                resultSet.getLong("author_id"),
                resultSet.getString("author_name"),
                resultSet.getString("author_surname")
        );

        Genre genre = new Genre(
                resultSet.getLong("genre_id"),
                resultSet.getString("genre_name")
        );

        return new Book(id, author, genre, title);
    };

    public BookDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.queryForObject(
                "select count(*) from books",
                Collections.emptyMap(),
                Integer.class
        );
    }

    @Override
    public void insert(Book book) {
        jdbc.update(
                "insert into books (book_id, author_id, genre_id, book_title) values (:id, :author_id, :genre_id, :title)",
                new MapSqlParameterSource()
                    .addValue("id", book.getId())
                    .addValue("author_id", book.getAuthor().getId())
                    .addValue("genre_id", book.getGenre().getId())
                    .addValue("title", book.getTitle())
        );
    }

    @Override
    public Book getById(Long id) {
        return jdbc.queryForObject(
                SELECT_ALL + " and b.book_id = :id",
                new MapSqlParameterSource()
                        .addValue("id", id),
                bookRowMapper
        );
    }

    @Override
    public List<Book> getByAuthorId(Long id) {
        return jdbc.query(
                SELECT_ALL + " and a.author_id = :id",
                new MapSqlParameterSource()
                    .addValue("id", id),
                bookRowMapper
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                SELECT_ALL,
                bookRowMapper
        );
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update(
                "delete from books where book_id = :id",
                new MapSqlParameterSource()
                        .addValue("id", id)
        );
    }
}