package com.marchas.literalura.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer birth_year;
    private Integer death_year;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author() {
    }

    public Author(DataAuthor a) {
        this.name = a.name();
        this.birth_year = a.birth();
        this.death_year = a.death();
    }

    @Override
    public String toString() {
        return "Author [id=" + id + ", name=" + name + ", birth_year=" + birth_year + ", death_year=" + death_year
                + ", books=" + books + "]";
    }

}

/*
 * "birth_year": <number or null>,
 * "death_year": <number or null>,
 * "name": <string>
 */
