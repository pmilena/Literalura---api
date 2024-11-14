package com.milena.literalura;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.milena.literalura.model.*;
import com.milena.literalura.repository.LivroRepository;
import com.milena.literalura.service.ConsumoAPI;
import com.milena.literalura.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private String menu = """
            ------------------------------
            Escolha o número de sua opção:
            1- buscar livro pelo título
            2- listar livros registrados
            3- listar autores registrados
            4- listar autores vivos em determinado ano
            5- listar livros em um determinado idioma
            6- buscar livro por autor
            
            0- sair
            """;

    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private ConverteDados conversor = new ConverteDados();
    private ConsumoAPI consumo = new ConsumoAPI();
    private Scanner leitor = new Scanner(System.in);
    private DadosLivro dadosLivro;
    private LivroRepository repository;
    private List<Livro> livros = new ArrayList<>();

    public Principal(LivroRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() throws JsonProcessingException {

        var opcao = -1;

        while (opcao != 0) {
            System.out.println(menu);
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    buscarPorTitulo();
                    break;

                case 2:
                    listaLivros();
                    break;

                case 3:
                    listaAutores();
                    break;

                case 4:
                    listaAutorPorAno();
                    break;

                case 5:
                    listaLivroPorIdioma();
                    break;

                case 6:
                    buscaPorAutor();
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
        String titulo = obterTitulo();
        String json = obterDadosJson(titulo);
        Livro livro = processarDadosLivro(json);

        exibirInformacoesLivro(livro);

        if (!verificarLivroExistente(livro)) {
            salvarLivro(livro);
        }


    }

    private String obterTitulo() {
        System.out.println("Digite o título do livro que deseja buscar:");
        return leitor.nextLine().replace(" ", "%20").toLowerCase().trim();
    }

    private String obterDadosJson(String titulo) throws JsonProcessingException {
        return consumo.obterDados(ENDERECO + titulo);
    }

    private Livro processarDadosLivro(String json) throws JsonProcessingException {
        var dadosLivro = conversor.obterDados(json, Results.class);
        var livroEncontrado = dadosLivro.results().get(0);

        Livro livro = new Livro();
        livro.setTitulo(livroEncontrado.titulo());
        livro.setNumeroDownloads(livroEncontrado.numeroDownloads());
        livro.setIdioma(String.join(",", livroEncontrado.idioma()));
        livro.setAutores(obterAutores(livroEncontrado.autor(), livro));

        return livro;
    }

    private List<Autor> obterAutores(List<DadosAutor> dadosAutores, Livro livro) {
        return dadosAutores.stream()
                .map(dadosAutor -> {
                    Autor autor = new Autor(dadosAutor);
                    autor.setLivro(livro);
                    return autor;
                })
                .collect(Collectors.toList());
    }

    private boolean verificarLivroExistente(Livro livro) {
        Optional<Livro> livroExistente = repository.findByTitulo(livro.getTitulo());
        return livroExistente.isPresent();
    }

    private void salvarLivro(Livro livro) {
        repository.save(livro);
    }

    private void exibirInformacoesLivro(Livro livro) {
        System.out.println("\n------------ LIVRO ------------" +
                "\n Título: " + livro.getTitulo() +
                "\n Autor: " + livro.getAutores().stream().map(Autor::getNome).collect(Collectors.joining(",")) +
                "\n Idioma: " + livro.getIdioma() +
                "\n Número de Downloads: " + livro.getNumeroDownloads() +
                "\n ---------------------------------" +
                "\n");
    }

    private void listaLivros() {
        List<Livro> livros = repository.findAll();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado no banco de dados.");
            return;
        }

        livros.sort(Comparator.comparing(Livro::getTitulo));

        livros.forEach(this::exibirInformacoesLivro);
    }

    private void listaAutores() {
        List<Autor> autores = repository.findAllAutores();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado no banco de dados.");
            return;
        }

        Map<String, List<String>> autoresMap = new HashMap<>();

        for (Autor autor : autores) {
            String anoFalecimento = autor.getAnoFalecimento() != null ? String.valueOf(autor.getAnoFalecimento()) : "Ainda Vivo";
            String chaveAutor = autor.getNome() + "-" + autor.getAnoNascimento() + "-" + anoFalecimento;

            autoresMap
                    .computeIfAbsent(chaveAutor, k -> new ArrayList<>())
                    .add(autor.getLivro().getTitulo());
        }

        autoresMap.forEach((chave, livros) -> {
            String[] dadosAutor = chave.split("-");
            String nome = dadosAutor[0].trim();
            Integer anoNascimento = Integer.valueOf(dadosAutor[1]);
            String anoFalecimento = dadosAutor[2];

            System.out.println("Nome: " + nome);
            System.out.println("Ano de Nascimento: " + anoNascimento);
            System.out.println("Ano de Falecimento: " + anoFalecimento);
            System.out.println("Livros: " + String.join(", ", livros));
            System.out.println();
        });
    }

    public void listaAutorPorAno() {
        System.out.println("Insira o ano que deseja pesquisar:");
        int ano = leitor.nextInt();
        leitor.nextLine();

        List<Autor> autores = repository.findAllAutores();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado no banco de dados.");
            return;
        }

        Map<String, List<String>> autoresMap = new HashMap<>();

        for (Autor autor : autores) {
            Integer anoFalecimento = autor.getAnoFalecimento();
            Integer anoNascimento = autor.getAnoNascimento();

            if ((anoFalecimento == null || anoFalecimento >= ano) && anoNascimento <= ano) {
                String anoFalecimentoStr = (anoFalecimento != null) ? String.valueOf(anoFalecimento) : "Autor vivo";
                String chaveAutor = autor.getNome() + "-" + anoNascimento + "-" + anoFalecimentoStr;

                autoresMap
                        .computeIfAbsent(chaveAutor, k -> new ArrayList<>())
                        .add(autor.getLivro().getTitulo());
            }
        }

        if (autoresMap.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano " + ano);
        } else {
            autoresMap.forEach((chave, livros) -> {
                String[] dadosAutor = chave.split("-");
                String nome = dadosAutor[0].trim();
                Integer anoNascimento = Integer.valueOf(dadosAutor[1]);
                String anoFalecimento = dadosAutor[2];

                System.out.println("Nome: " + nome);
                System.out.println("Ano de Nascimento: " + anoNascimento);
                System.out.println("Ano de Falecimento: " + anoFalecimento);
                System.out.println("Livros: " + String.join(", ", livros));
                System.out.println();
            });
        }
    }

    public void listaLivroPorIdioma() {
        System.out.println("\nInsira o idioma para realizar a busca:" +
                "\n es - espanhol " +
                "\n en - inglês " +
                "\n fr - francês" +
                "\n pt - português" +
                "\n");
        String idiomaSolicitado = leitor.nextLine().trim().toLowerCase();

        if (!List.of("es", "en", "fr", "pt").contains(idiomaSolicitado)) {
            System.out.println("Idioma inválido. Tente novamente com um dos idiomas válidos (es, en, fr, pt).");
            return;
        }

        List<Livro> livrosPorIdioma = repository.findByIdioma(idiomaSolicitado);

        if (livrosPorIdioma.isEmpty()) {
            System.out.println("Não há livros cadastrados no idioma solicitado.");
        } else {
            livrosPorIdioma.sort(Comparator.comparing(Livro::getTitulo));

            livrosPorIdioma.forEach(this::exibirInformacoesLivro);
        }
    }

    public List<Livro> findByIdioma(String idioma) {
        return repository.findAll().stream()
                .filter(livro -> livro.getIdioma().contains(idioma))
                .collect(Collectors.toList());
    }

    public void buscaPorAutor() {
        System.out.println("Digite o nome do autor que deseja buscar:");
        var nomeAutor = leitor.nextLine().trim().toLowerCase();

        List<Livro> livrosPorAutor = repository.findByAutor(nomeAutor);

        if (livrosPorAutor.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o autor: " + nomeAutor);
        } else {
            livrosPorAutor.sort(Comparator.comparing(Livro::getTitulo));

            livrosPorAutor.forEach(this::exibirInformacoesLivro);
        }
    }



}





