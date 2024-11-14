package com.milena.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private Integer numeroDownloads;

    @Column(name = "idioma")
    private String idioma;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores = new ArrayList<>();

    public Livro() {}


    public Livro(DadosLivro d) {
        this.titulo = d.titulo();
        this.numeroDownloads = d.numeroDownloads();
        this.idioma = String.join(",", d.idioma());

        if (d.autor() != null && !d.autor().isEmpty()) {
            for (DadosAutor dadosAutor : d.autor()) {

                Autor autor = new Autor(dadosAutor);
                autor.setLivro(this);
                this.autores.add(autor);
            }
        }

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public String getIdioma() {
        return idioma;
    }


    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<Autor> getAutores() {
        return autores;
    }


    public void setAutores(List<Autor> autores) {
        this.autores = autores;
        for (Autor autor : autores) {
            autor.setLivro(this);
        }
}}



