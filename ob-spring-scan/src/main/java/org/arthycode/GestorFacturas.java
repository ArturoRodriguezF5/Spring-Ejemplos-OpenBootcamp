package org.arthycode;

import org.springframework.stereotype.Component;

@Component
public class GestorFacturas {
    // 1. atributos
    Calculadora calculadora;

    //2. constructores
    public GestorFacturas(Calculadora calculadora){
        System.out.println("Ejecutando constructor GestorFacturas");
        this.calculadora = calculadora;
    }

    // 3. metodos....
}
