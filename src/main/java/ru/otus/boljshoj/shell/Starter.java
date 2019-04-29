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

    @ShellMethod("print all chosen entity")
    public void all(String entity){
        entityService.all(entity);
    }

    @ShellMethod("add new book or comment")
    public void add(String entity) {
        entityService.add(entity);
    }

    @ShellMethod("delete by param")
    public void del(String param) {
        entityService.del(param);
    }

    @ShellMethod("find books by param")
    public void find(String param) {
        entityService.find(param);
    }
}