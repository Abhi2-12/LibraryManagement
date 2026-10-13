package com.library.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.api.repository.BookRepository;
import com.library.api.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    //Get all book
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //get one book by id
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookRepository.findById(id).orElse(null);
    }

    //add a new book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
    
    
}
