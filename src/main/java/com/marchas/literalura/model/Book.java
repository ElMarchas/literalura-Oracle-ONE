package com.marchas.literalura.model;

import java.util.List;
import java.util.stream.Collectors;

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

    public Book() {
    }

    public Book(DataBook b) {
        List<Author> autors = b.authors().stream()
                .map(a -> new Author(a)).collect(Collectors.toList());

        this.id = b.id();
        this.title = b.title();
        this.authors = autors;
        this.languages = b.languages();
        this.download_count = b.download_count();
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", authors=" + authors.toString() + ", languages=" + languages
                + ", download_count=" + download_count + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }

}

/*
 * "id": <number of Project Gutenberg ID>,
 * "title": <string>,
 * "authors": <array of Persons>,
 * "languages": <array of strings>,
 * "download_count": <number>
 */
