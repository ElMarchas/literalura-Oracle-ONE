package com.marchas.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marchas.literalura.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
