package ru.otus.boljshoj.contollers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.boljshoj.domain.Author;
import ru.otus.boljshoj.domain.Book;
import ru.otus.boljshoj.domain.Genre;
import ru.otus.boljshoj.repos.BookRepository;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MainContoller.class)
@DirtiesContext
@AutoConfigureDataMongo
class MainContollerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void initTest() {
        Book bookForTest = new Book("Title1", new Author("Name1", "Surname1"), new Genre("Genre1"), new ArrayList<>());
        Book bookForTest2 = new Book("Title2", new Author("Name1", "Surname1"), new Genre("Genre2"), new ArrayList<>());
        Book bookForTest3 = new Book("Title3", new Author("Name2", "Surname2"), new Genre("Genre2"), new ArrayList<>());
        bookRepository.save(bookForTest);
        bookRepository.save(bookForTest2);
        bookRepository.save(bookForTest3);
    }

    @Test
    @DisplayName("отображает список книг")
    void listPage() throws Exception {
        this.mvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attribute("books", hasSize(bookRepository.findAll().size())))
                .andExpect(model().attribute("books", hasItem(allOf(hasProperty("title", is("Title1"))))));
    }

    @Test
    @DisplayName("переходит на страницу редактирования")
    void editPage() throws Exception {
        String id = bookRepository.findByGenreName("Genre1").get(0).getId();
        this.mvc.perform(get("/edit/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attribute("book", hasProperty("title", is("Title1"))));
    }

    @Test
    @DisplayName("добавляет книгу")
    void save() throws Exception {
        this.mvc.perform(
                post("/list")
                .param("title", "test")
                .param("authorName", "test")
                .param("authorSurname", "test")
                .param("genreName", "test")
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/list"));
    }

    @Test
    @DisplayName("удаляет книгу")
    void delete() throws Exception {
        String id = bookRepository.findByGenreName("Genre1").get(0).getId();
        this.mvc.perform(post("/delete/" + id))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/list"));
    }
}