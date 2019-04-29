package ru.otus.boljshoj.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.boljshoj.domain.Author;
import ru.otus.boljshoj.domain.Book;
import ru.otus.boljshoj.domain.Comment;
import ru.otus.boljshoj.domain.Genre;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class BookCustomRepositoryImpl implements BookCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Author> findAllAuthors() {
        return mongoTemplate.aggregate(newAggregation(group("author.name", "author.surname")), Book.class, Author.class)
                .getMappedResults();
    }

    @Override
    public List<Genre> findAllGenres() {
        return mongoTemplate.findDistinct("genre.name", Book.class, Genre.class);
    }

    @Override
    public List<Comment> findAllComments() {
        return mongoTemplate.findDistinct("comments.text", Book.class, Comment.class);
    }

    @Override
    public void addCommentByBookId(String bookId, Comment comment) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(bookId));
        Update update = new Update();
        update.push("comments", comment);
        mongoTemplate.updateFirst(query, update, Book.class);
    }

    @Override
    public void deleteCommentsByBookId(String bookId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(bookId));
        Update update = new Update();
        update.unset("comments");
        mongoTemplate.updateFirst(query, update, Book.class);
    }
}