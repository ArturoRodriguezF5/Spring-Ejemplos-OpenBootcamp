package com.arthycode.obrestdatajpa;

import com.arthycode.obrestdatajpa.entities.Book;
import com.arthycode.obrestdatajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repo = context.getBean(BookRepository.class);

		// Crear un libro
		Book book = new Book(null, "Homo Deus", "yuval Noah", 450, 29.99,
				LocalDate.of(2018, 12, 1), true);
		Book book2 = new Book(null, "Homo Sapiens", "yuval Noah", 410, 19.99,
				LocalDate.of(2013, 12, 1), true);
		System.out.println("Hay: " + repo.count() + " libros en el repo.");
		// Almacenar un libro
		repo.save(book);
		repo.save(book2);
		// Recuperar todos los libros
		System.out.println(repo.findAll());
		System.out.println("Hay: " + repo.count() + " libros en el repo.");
		// Borrar un libro
		//repo.deleteById(1L);
		System.out.println(repo.findAll());
	}
}
