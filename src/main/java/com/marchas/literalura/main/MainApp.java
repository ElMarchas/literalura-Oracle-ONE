package com.marchas.literalura.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.marchas.literalura.model.DataBook;
import com.marchas.literalura.repository.BookRepository;
import com.marchas.literalura.service.DataConverter;
import com.marchas.literalura.service.QueryAPI;

public class MainApp {
    private Scanner input = new Scanner(System.in);
    private QueryAPI queryAPI = new QueryAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private DataConverter converter = new DataConverter();
    private List<DataBook> dataBooks = new ArrayList<>();
    private BookRepository repository;

    public MainApp(BookRepository repository) {
        this.repository = repository;
    }

    public void run() {
        var opcion = -1;
        var menu = """
                1 - test1
                2 - test2
                3 - test3

                0 - Salir
                """;
        

        while (opcion != 0) {
            printHeader();
            System.out.println(menu);
            try {
                opcion = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Comando no reconocido, intente de nuevo");
                opcion = -1;
                input.nextLine();
            }

            System.out.println("afuera" + opcion);

        }

    }

    private void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void printHeader() {
        cleanScreen();
        System.out.println("--------------- --------------- ---------------");
        System.out.println("LiterAlura alimentado por Gutendex");
    }

}
