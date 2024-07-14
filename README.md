# LiterAlura reto ORACLE ONE 6

Este repositorio es un intento de resolver el [desafío literalura JAVA](https://trello.com/b/WDyMPDMb/literalura-challenge-java) propuesto en alura. 

Tenemos un periodo de tiempo de dos semanas para desarrollar el proyecto **(del 18 de Mayo 2024 al 31 de Mayo 2024)** 

Desarrollaremos una aplicación java por terminal.

## Requisitos 
### API externa
Utilizamos [gutendex](https://gutendex.com/) como api externa para extraer los datos de los libros y asi almacenarlos en nuestra base de datos de postgres local. toda la documentacion en dicha pagina.

### Base de datos

La aplicación contará con una base de datos en mysql local con 2 tablas diferentes conectadas.

- Libros [id, descargas, idioma, titulo, autor]

- Autor [id, nacimiento, muerte, nombre]


### Menú en terminal
```
Elija una opción con número
1 - Buscar libro por titulo
2 - Lista de libros guardados
3 - Lista de autores guardados
4 - Lista de autores guardados vivos en determinado año
5 - Lista de libros por idioma
```

## Resultados
Al no mapear bien la relación muchos a muchos de ambas tablas no se pudo inyectar la propiedad autor en la classe libro, asi que al mostrar el libro por terminal se encontraba ausente ese dato. por otra parte esa funcionalidad no venia requerida en el menú de terminal. Por lo tanto lo dejamos asi.