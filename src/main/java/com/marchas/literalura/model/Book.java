package com.marchas.literalura.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    @Id
    private Long id;
    @Column(unique = true)
    private String title;
    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Author> authors;
    private List<String> languages;
    private Integer download_count;

}

/*
 * "id": <number of Project Gutenberg ID>,
 * "title": <string>,
 * "authors": <array of Persons>,
 * "languages": <array of strings>,
 * "download_count": <number>
 */
