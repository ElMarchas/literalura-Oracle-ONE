package com.marchas.literalura.main;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.marchas.literalura.model.Book;
import com.marchas.literalura.model.DataBook;
import com.marchas.literalura.model.DataQuery;
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
        Integer option = -1;
        String menu = """
                Elija una opción con número
                1 - Buscar libro por titulo
                2 - Lista de libros guardados
                3 - Lista de autores guardados
                4 - Lista de autores guardados vivos en determinado año
                5 - Lista de libros por idioma

                0 - Salir""";

        while (option != 0) {
            printHeader(option, menu);
            try {
                option = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                option = -2;
                input.nextLine();
            }

            switch (option) {
                case 1:
                    fetchBookByTitle();
                    break;
                case 2:
                    getSavedBooks();
                    break;
                case 3:
                    getSavedAuthors();
                    break;
                case 4:
                    getSavedAuthorsByYear();
                    break;
                case 5:
                    getBooksByLang();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    option = -2;
            }

        }

    }

    private void fetchBookByTitle() {
        System.out.print("\n\nPor favor escribe el nombre del libro que deseas buscar.\n └──>");
        var tittleBook = input.nextLine();

        String json = queryAPI.getData(URL_BASE + "?search=" + URLEncoder.encode(tittleBook,
                StandardCharsets.UTF_8));
        var data = converter.getData(json, DataQuery.class);

        List<Book> queryBooks = data.books().stream()
                .filter(b -> b.title().toLowerCase().contains(tittleBook.toLowerCase())).map(b -> new Book(b))
                .collect(Collectors.toList());

        queryBooks.stream().limit(10).forEach(System.out::println);

        repository.save(queryBooks.get(0));
    }

    private void getBooksByLang() {
        System.out.print("\n\nPor favor escribe el nombre del libro que deseas buscar.\n └──>");
        var tittleBook = input.nextLine();

        String json = queryAPI.getData(URL_BASE + "?search=" + URLEncoder.encode(tittleBook,
                StandardCharsets.UTF_8));
        var data = converter.getData(json, DataQuery.class);

        System.out.println(data);

        /*
         * List<Book> queryBooks = data.books().stream()
         * .filter(b ->
         * b.title().toLowerCase().contains(tittleBook.toLowerCase())).map(b -> new
         * Book(b))
         * .collect(Collectors.toList());
         * 
         * queryBooks.stream().limit(10)
         * .forEach(System.out::println);
         */
    }

    private void getSavedAuthorsByYear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSavedAuthorsByYear'");
    }

    private void getSavedAuthors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSavedAuthors'");
    }

    private void getSavedBooks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSavedBooks'");
    }

    private void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void printHeader(Integer option, String menu) {
        // cleanScreen();
        System.out.println("--------------- --------------- ---------------");
        System.out.println("LiterAlura alimentada por Gutendex");
        System.out.println("--------------- --------------- ---------------");
        System.out.println(menu);
        if (option == -2) {
            System.out.println("xxxxxxxxxxxxxxx      ERROR      xxxxxxxxxxxxxxx");
            System.out.println("Comando no reconocido, intente de nuevo");
        }
        System.out.printf("\n └──>");
    }

}
