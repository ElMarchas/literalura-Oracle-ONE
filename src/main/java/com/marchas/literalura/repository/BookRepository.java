package com.marchas.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marchas.literalura.model.Author;
import com.marchas.literalura.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT a FROM Author a ")
    List<Author> findAuthors();
}
