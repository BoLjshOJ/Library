package ru.otus.boljshoj.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.boljshoj.domain.Author;
import ru.otus.boljshoj.domain.Book;
import ru.otus.boljshoj.domain.Genre;
import ru.otus.boljshoj.repos.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainContoller {

    private BookRepository bookRepository;

    @Autowired
    public MainContoller(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/list")
    public String listOfBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @PostMapping("/list")
    public String addBook(
            @RequestParam String title,
            @RequestParam String authorName,
            @RequestParam String authorSurname,
            @RequestParam String genreName,
            Model model
    ) {
        Book newBook = new Book(title, new Author(authorName, authorSurname), new Genre(genreName), new ArrayList<>());
        bookRepository.save(newBook);
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String editBook(
        @PathVariable("id") String id,
        Model model
    ) {
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(
            @PathVariable("id") String id,
            @RequestParam String title,
            @RequestParam String authorName,
            @RequestParam String authorSurname,
            @RequestParam String genreName
    ) {
        Book updatedBook = bookRepository.findById(id).get();
        updatedBook.setAuthor(new Author(authorName, authorSurname));
        updatedBook.setGenre(new Genre(genreName));
        updatedBook.setTitle(title);
        bookRepository.save(updatedBook);
        return "redirect:/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") String id, Model model){
        bookRepository.deleteById(id);
        return "redirect:/list";
    }
}