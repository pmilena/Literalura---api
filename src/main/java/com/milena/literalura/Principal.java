package com.milena.literalura;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.milena.literalura.model.DadosLivro;
import com.milena.literalura.model.Livro;
import com.milena.literalura.model.Results;
import com.milena.literalura.service.ConsumoAPI;
import com.milena.literalura.service.ConverteDados;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private String menu= """
            Escolha o número de sua opção:
            
            1- buscar livro pelo título
            2- listar livros registrados
            3- listar autores registrados
            4- listar autores vivos
            5- listar livros em um determinado idioma
            
            0- sair
            """;

    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private ConverteDados conversor = new ConverteDados();
    private ConsumoAPI consumo = new ConsumoAPI();
    private Scanner leitor = new Scanner(System.in);
    private DadosLivro dadosLivro;


    public void exibeMenu() throws JsonProcessingException {

        var opcao = -1;

        while (opcao!=0) {
            System.out.println(menu);
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    buscarPorTitulo();
                    break;

                case 0:
                    System.out.println("Aplicação finalizada...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente Novamente.");
            }

        }

    }

    public void buscarPorTitulo() throws JsonProcessingException {
        System.out.println("Digite o título do livro que deseja buscar:");
        var trechoTitulo = leitor.nextLine();

        var json = consumo.obterDados(ENDERECO + trechoTitulo.replace(" ", "%20").toLowerCase().trim());

        var dadosLivro = conversor.obterDados(json, Results.class);

        var livroEncontrado = dadosLivro.results().get(0);


        System.out.println("\n------------ LIVRO ------------" +
                        "\n Título: " + livroEncontrado.titulo() +
                        "\n Autor: " + livroEncontrado.autor().stream().map(a->a.nome()).collect(Collectors.joining(",")) +
                        "\n Idioma: " + livroEncontrado.idioma().stream().collect(Collectors.joining(","))  +
                        "\n Número de Downloads: " + livroEncontrado.numeroDownloads() +
                        "\n ---------------------------------" +
                        "\n");
    }

}


