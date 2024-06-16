package com.marchas.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
                @JsonAlias("id") Long id,
                @JsonAlias("title") String title,
                @JsonAlias("authors") List<DataAuthor> authors,
                @JsonAlias("languages") List<String> languages,
                @JsonAlias("download_count") Integer download_count) {

}