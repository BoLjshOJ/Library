package ru.otus.boljshoj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.boljshoj.dao.AuthorDao;
import ru.otus.boljshoj.dao.BookDao;
import ru.otus.boljshoj.dao.GenreDao;
import ru.otus.boljshoj.domain.Author;
import ru.otus.boljshoj.domain.Book;
import ru.otus.boljshoj.domain.Genre;

@Service
public class EntityServiceImpl implements EntityService{
    private AuthorDao authorDao;
    private BookDao bookDao;
    private GenreDao genreDao;
    private IOService ioService;

    @Autowired
    public EntityServiceImpl(AuthorDao authorDao, BookDao bookDao, GenreDao genreDao, IOService ioService) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.ioService = ioService;
    }

    public void count(String entity){
        String paramToPrinter = "Count of %s: %d";
        switch (entity) {
            case "authors":
                ioService.printMessage(paramToPrinter, entity, authorDao.count());
                break;
            case "books":
                ioService.printMessage(paramToPrinter, entity, bookDao.count());
                break;
            case "genres":
                ioService.printMessage(paramToPrinter, entity, genreDao.count());
                break;
            default:
                ioService.printMessage("You must choose entity: authors, books or genres");
        }
    }

    @Override
    public void get(String entity, Long id) {
        switch (entity) {
            case "author":
                ioService.printMessage(authorDao.getById(id).toString());
                break;
            case "book":
                ioService.printMessage(bookDao.getById(id).toString());
                break;
            case "genre":
                ioService.printMessage(genreDao.getById(id).toString());
                break;
            default:
                ioService.printMessage("You must choose entity: author, book or genre");
        }
    }

    @Override
    public void all(String entity) {
        switch (entity) {
            case "authors":
                ioService.printMessage(authorDao.getAll().toString());
                break;
            case "books":
                ioService.printMessage(bookDao.getAll().toString());
                break;
            case "genres":
                ioService.printMessage(genreDao.getAll().toString());
                break;
            default:
                ioService.printMessage("You must choose entity: authors, books or genres");
        }
    }

    @Override
    public void delete(String entity, Long id) {
        String paramToPrinter = "%s with id %d was succesfully deleted";
        switch (entity) {
            case "author":
                authorDao.deleteById(id);
                ioService.printMessage(paramToPrinter, entity, id);
                break;
            case "book":
                bookDao.deleteById(id);
                ioService.printMessage(paramToPrinter, entity, id);
                break;
            case "genre":
                genreDao.deleteById(id);
                ioService.printMessage(paramToPrinter, entity, id);
                break;
            default:
                ioService.printMessage("You must choose entity: author, book or genre");
        }
    }

    @Override
    public void add(String entity) {
        String paramToPrinter = "%s was succesfully added";
        switch (entity) {
            case "author":
                Long idOfNewAuthor = (long) authorDao.count() + 1;
                ioService.printMessage("Input %s name", entity);
                String nameOfNewAuthor = ioService.getStringFromUser();
                ioService.printMessage("Input %s surname", entity);
                String surnameOfNewAuthor = ioService.getStringFromUser();
                authorDao.insert(new Author(idOfNewAuthor, nameOfNewAuthor, surnameOfNewAuthor));
                ioService.printMessage(paramToPrinter, entity);
                break;
            case "book":
                Long idOfNewBook = (long) bookDao.count() + 1;
                ioService.printMessage("Input %s name", entity);
                String nameOfNewBook = ioService.getStringFromUser();
                bookDao.insert(new Book(idOfNewBook, nameOfNewBook));
                ioService.printMessage(paramToPrinter, entity);
                break;
            case "genre":
                Long idOfNewGenre = (long) genreDao.count() + 1;
                ioService.printMessage("Input %s name", entity);
                String nameOfNewGenre = ioService.getStringFromUser();
                genreDao.insert(new Genre(idOfNewGenre, nameOfNewGenre));
                ioService.printMessage(paramToPrinter, entity);
                break;
            default:
                ioService.printMessage("You must choose entity: author, book or genre");
        }
    }
}

