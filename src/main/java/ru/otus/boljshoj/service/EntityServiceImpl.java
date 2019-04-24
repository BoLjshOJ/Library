package ru.otus.boljshoj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.boljshoj.domain.Author;
import ru.otus.boljshoj.domain.Book;
import ru.otus.boljshoj.domain.Comment;
import ru.otus.boljshoj.domain.Genre;
import ru.otus.boljshoj.repos.BookRepository;

import java.util.ArrayList;

@Service
public class EntityServiceImpl implements EntityService{
    private BookRepository bookRepository;
    private IOService ioService;

    @Autowired
    public EntityServiceImpl(BookRepository bookRepository, IOService ioService) {
        this.bookRepository = bookRepository;
        this.ioService = ioService;
    }

    public void count(String entity){
        String paramToPrinter = "Count of %s: %d";
        switch (entity) {
            case "authors":
                ioService.printMessage(paramToPrinter, entity, bookRepository.findAllAuthors().size());
                break;
            case "books":
                ioService.printMessage(paramToPrinter, entity, bookRepository.findAll().size());
                break;
            case "genres":
                ioService.printMessage(paramToPrinter, entity, bookRepository.findAllGenres().size());
                break;
            case "comments":
                ioService.printMessage(paramToPrinter, entity, bookRepository.findAllComments().size());
                break;
            default:
                ioService.printMessage("You must choose entity: authors, books, genres or comments");
        }
    }

    @Override
    public void all(String entity) {
        switch (entity) {
            case "authors":
                ioService.printMessage(bookRepository.findAllAuthors().toString());
                break;
            case "books":
                ioService.printMessage(bookRepository.findAll().toString());
                break;
            case "genres":
                ioService.printMessage(bookRepository.findAllGenres().toString());
                break;
            case "comments":
                ioService.printMessage(bookRepository.findAllComments().toString());
                break;
            default:
                ioService.printMessage("You must choose entity: authors, books, genres or comments");
        }
    }

    @Override
    public void add(String entity) {
        switch (entity) {
            case "book":
                ioService.printMessage("Input title of book");
                String titleBook = ioService.getStringFromUser();
                ioService.printMessage("Input author name");
                String authorName = ioService.getStringFromUser();
                ioService.printMessage("Input author surname");
                String authorSurname = ioService.getStringFromUser();
                ioService.printMessage("Input genre of book");
                String genreName = ioService.getStringFromUser();
                Book newBook = new Book(titleBook, new Author(authorName, authorSurname), new Genre(genreName), new ArrayList<>());
                bookRepository.save(newBook);
                ioService.printMessage("Book \"%s\" was successfully added", newBook.getTitle());
                break;
            case "comment":
                ioService.printMessage("Input book ID");
                String bookID = ioService.getStringFromUser();
                ioService.printMessage("Input comment for book \"%s\"", bookRepository.findById(bookID).get().getTitle());
                String textOfNewComment = ioService.getStringFromUser();
                bookRepository.addCommentByBookId(bookID, new Comment(textOfNewComment));
                ioService.printMessage("comment was added");
                break;
            default:
                ioService.printMessage("You must choose entity: book or comment");
        }

    }

    @Override
    public void del(String param) {
        switch (param) {
            case "bookById":
                ioService.printMessage("Input book ID for delete");
                bookRepository.deleteById(ioService.getStringFromUser());
                ioService.printMessage("delete was success");
                break;
            case "booksByAuthor":
                ioService.printMessage("Input author name");
                String authorName = ioService.getStringFromUser();
                ioService.printMessage("Input author surname");
                String authorSurname = ioService.getStringFromUser();
                bookRepository.removeByAuthorNameAndAuthorSurname(authorName, authorSurname);
                ioService.printMessage("delete was success");
                break;
            case "commentsByBookId":
                ioService.printMessage("Input book ID for delete comments");
                bookRepository.deleteCommentsByBookId(ioService.getStringFromUser());
                ioService.printMessage("comments was delete");
                break;
            case "booksByGenreName":
                ioService.printMessage("Input genre name for delete books");
                bookRepository.removeByGenreName(ioService.getStringFromUser());
                ioService.printMessage("delete was success");
                break;
            default:
                ioService.printMessage("Please choose param");
        }
    }

    @Override
    public void find(String param) {
        switch (param) {
            case "booksByAuthor":
                ioService.printMessage("Input author name");
                String authorName = ioService.getStringFromUser();
                ioService.printMessage("Input author surname");
                String authorSurname = ioService.getStringFromUser();
                ioService.printMessage(bookRepository.findByAuthorNameAndAuthorSurname(authorName, authorSurname).toString());
                break;
            case "booksByGenre":
                ioService.printMessage("Input genre");
                ioService.printMessage(bookRepository.findByGenreName(ioService.getStringFromUser()).toString());
                break;
            default:
                ioService.printMessage("Please choose param");
        }
    }
}