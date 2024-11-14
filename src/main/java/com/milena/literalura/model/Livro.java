package com.milena.literalura.model;

import java.util.ArrayList;
import java.util.List;

public class Livro {

    //private Integer gutenbergId;
    private String titulo;
    private Integer numeroDownloads;
    private List<String> idioma;
    private List<DadosAutor> autor;

    public Livro() {}

    public Livro(DadosLivro d){
        //this.gutenbergId=d.gutenbergId();
        this.titulo=d.titulo();
        this.numeroDownloads=d.numeroDownloads();
        this.idioma=d.idioma();
        this.autor=d.autor();
    }

   /* public Integer getGutenbergId() {
        return gutenbergId;
    }

    public void setGutenbergId(Integer gutenbergId) {
        this.gutenbergId = gutenbergId;
    }*/

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public List<DadosAutor> getAutor() {
        return autor;
    }

    public void setAutor(List<DadosAutor> autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
