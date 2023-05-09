package com.arthycode.obrestdatajpa.controller;

import com.arthycode.obrestdatajpa.entities.Book;
import com.arthycode.obrestdatajpa.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    // Atributos encapsulados
    private BookRepository bookRepository;
    // Constructores

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // -------------------CRUD sobre la entidad Book

    // Buscar todos los libros

    /**
     * https://localhost:8080/api/books
     * @return books
     */
    @GetMapping("/api/books")
    public List<Book> findAll() {
        // recuperar y devolver los libros de la base de datos
        return bookRepository.findAll();
    }

    // Buscar un libro en base a su id

    /**
     * Request
     * Response
     * @param id
     * @return
     */
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            return ResponseEntity.ok(bookOpt.get());
        } else{
            return ResponseEntity.notFound().build();
        }
        // Programación funcional
        //return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // Agregar un libro a base de datos
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book) {
        // Guardar el libro en la base de datos
        return bookRepository.save(book);
    }

    // Actualizar un libro existente en base de datos

    // Borrar un libro en base de datos

}
