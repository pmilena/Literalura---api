# LiterAlura - Catálogo de Livros

Este projeto implementa um catálogo de livros que consome dados de uma API externa e realiza a persistência de informações em um banco de dados relacional.

A aplicação é construída utilizando **Java**, **Spring Boot** e **PostgreSQL**. Ela permite realizar operações como buscar livros pela API, listar livros registrados, filtrar por autores e idiomas, e muito mais.

### Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **PostgreSQL**
- **Spring Data JPA**

## Funcionalidades

A aplicação oferece uma interface no terminal com 5 opções principais de interação com o usuário:

1. **Buscar livro pelo título**: Realiza a consulta diretamente na API Gutendex e insere o livro no banco de dados.
   
2. **Listar livros registrados**: Exibe todos os livros que foram inseridos no banco de dados.
   
3. **Listar nossos autores**: Exibe os dados dos autores registrados, incluindo nome e livros associados.
   
4. **Listar autores em determinado ano**: Filtra autores que estavam vivos em um ano específico.
   
5. **Listar livros em determinado idioma**: Filtra livros de um idioma específico, como espanhol, inglês, francês ou português.

6. **Buscar livros por autor**: Exibe títulos por autor.

## Configurações do Projeto

Este projeto foi criado com o **Spring Initializr**, com as seguintes configurações:

- **Linguagem**: Java
- **Gerenciador de dependências**: Maven
- **Versão do Spring Boot**: A versão compatível com o projeto.
- **Dependências**:
  - Spring Data JPA
  - Driver PostgreSQL

## Banco de Dados

A aplicação utiliza o banco de dados **PostgreSQL** para persistência de dados. Abaixo está o site oficial para download do PostgreSQL:

- [PostgreSQL - Download](https://www.postgresql.org/download/)

## API Gutendex

A API **Gutendex** fornece acesso a mais de 70 mil livros. Ela pode ser consultada diretamente através do site oficial, e utilizamos ela para realizar a busca e inserção de livros no nosso banco de dados.

- [Gutendex API](https://gutendex.com/)

##
