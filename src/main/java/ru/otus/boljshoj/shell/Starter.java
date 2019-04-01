package ru.otus.boljshoj.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.boljshoj.service.EntityService;

@ShellComponent
public class Starter {
    private EntityService entityService;

    @Autowired
    public Starter(EntityService entityService){
        this.entityService = entityService;
    }

    @ShellMethod("count entity")
    public void count(String entity){
        entityService.count(entity);
    }

    @ShellMethod("get entity by id")
    public void get(String entity, Long id){
        entityService.get(entity, id);
    }

    @ShellMethod("print all chosen entity")
    public void all(String entity){
        entityService.all(entity);
    }

    @ShellMethod("get all books by author ID")
    public void getbyauthor(Long id){
        entityService.getBookByAuthorID(id);
    }

    @ShellMethod("delete entity by ID")
    public void delete(String entity, Long id){
        entityService.delete(entity, id);
    }

    @ShellMethod("create new entity")
    public void add(String entity) {
        entityService.add(entity);
    }
}