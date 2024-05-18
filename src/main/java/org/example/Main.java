package org.example;

import java.io.FileNotFoundException;

import org.example.model.GeneradorMatrices;
import org.example.model.InterfazMenu;

public class Main {
    public static void main(String[] args) {

        GeneradorMatrices.generarMatricesA();
    
        new InterfazMenu();
    }
}

