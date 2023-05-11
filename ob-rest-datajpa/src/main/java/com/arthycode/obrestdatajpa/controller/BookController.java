package com.arthycode.obrestdatajpa.controller;

import com.arthycode.obrestdatajpa.entities.Book;
import com.arthycode.obrestdatajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    // Atributos encapsulados
    private final Logger log = LoggerFactory.getLogger(BookController.class);
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
    public ResponseEntity<Book> create(@RequestBody Book book) {
        if (book.getId() != null) {
            log.warn("Book already exists");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookRepository.save(book));
    }

    // Actualizar un libro existente en base de datos
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book) {
        if (book.getId() == null) {
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if (!bookRepository.existsById(book.getId())) {
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookRepository.save(book));
    }
    // Borrar un libro en base de datos
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Borrar todos los libros de la base de datos
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll() {
        if (bookRepository.count() != 0) {
            bookRepository.deleteAll();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
