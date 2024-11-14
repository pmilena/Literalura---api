package com.milena.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


public class Autor {

    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    public Autor() {}

    public Autor(DadosAutor d){
        this.nome=d.nome();
        this.anoNascimento=d.anoNascimento();
        this.anoFalecimento=d.anoFalecimento();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }
}


