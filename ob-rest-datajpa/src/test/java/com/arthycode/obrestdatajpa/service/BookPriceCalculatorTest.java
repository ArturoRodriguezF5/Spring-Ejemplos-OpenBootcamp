package com.arthycode.obrestdatajpa.service;

import com.arthycode.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {
        Book book = new Book(1L, "El señor d elos anillos", "Author", 1000, 49.56, LocalDate.now(),true);
        BookPriceCalculator calculator = new BookPriceCalculator();
        // Se ejecuta el comportamiento a testear
        double price = calculator.calculatePrice(book);
        System.out.println(price);
        // Comprobaciones aserciones
        assertTrue(price > 0);
    }
}