package com.marchas.literalura.main;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.marchas.literalura.model.Author;
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
    private List<Book> books;
    private List<Author> authors;

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
        cleanScreen();
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
        var indexBook = 0;

        System.out.print("\n\nPor favor escribe el nombre del libro que deseas buscar.\n └──>");
        var tittleBook = input.nextLine();

        String json = queryAPI.getData(URL_BASE + "?search=" + URLEncoder.encode(tittleBook,
                StandardCharsets.UTF_8));
        var data = converter.getData(json, DataQuery.class);

        cleanScreen();

        List<Book> queryBooks = data.books().stream()
                .filter(b -> b.title().toLowerCase().contains(tittleBook.toLowerCase())).map(b -> new Book(b))
                .collect(Collectors.toList());

        if (queryBooks.size() < 1) {
            System.out.println("no se encontró ningun libro, intente de nuevo");
            return;
        }

        for (int i = 0; i < queryBooks.size(); i++) {
            if (i >= 15) {
                break;
            }
            Book b = queryBooks.get(i);
            System.out.printf("%d.- %s %s %d %n", i + 1, b.getTitle(), b.getAuthors(),
                    b.getDownload_count());

        }

        System.out.print(
                "\n\nIngresa el numero que corresponde a tu libro, de lo contrario escribe 0 para regresar al menú.\n └──>");
        try {
            indexBook = input.nextInt();
            input.nextLine();
        } catch (Exception e) {
            indexBook = 0;
            input.nextLine();
        }
        cleanScreen();
        if (indexBook == 0 || indexBook > queryBooks.size() + 1) {
            System.out.println("no se agregó ningun libro nuevo");
            return;
        }
        try {
            repository.save(queryBooks.get(indexBook - 1));
            System.out.printf(
                    "\n\n El libro %s fue almacenado con exito.\n\n", queryBooks.get(indexBook - 1).getTitle());
        } catch (Exception e) {
            System.out.println("Ocurrio un error o el libro ya se encontraba almacenado en la base de datos");
        }

    }

    private void getSavedBooks() {
        books = repository.findAll();

        books.stream()
                .forEach(System.out::println);
    }

    private void getSavedAuthors() {
        authors = repository.findAuthors();

        authors.stream()
                .forEach(System.out::println);
    }

    private void getSavedAuthorsByYear() {
        var fecha = 1880;

        authors = repository.findAuthors();

        authors.stream()
                .filter(a -> a.getBirth_year() < fecha && a.getDeath_year() > fecha)
                .forEach(System.out::println);
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
