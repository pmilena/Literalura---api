package com.milena.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") Integer numeroDownloads,
        @JsonAlias("authors") List<DadosAutor> autor,
        @JsonAlias("languages") List<String> idioma
) {}
