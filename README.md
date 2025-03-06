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
*******************************************************************************************************

# LiterAlura - Book Catalog

This project implements a book catalog that consumes data from an external API and persists information in a relational database.

The application is built using **Java**, **Spring Boot**, and **PostgreSQL**. It allows performing operations such as searching for books through the API, listing registered books, filtering by authors and languages, and much more.

### Technologies Used

- **Java**
- **Spring Boot**
- **PostgreSQL**
- **Spring Data JPA**

## Features

The application offers a terminal interface with 5 main options for user interaction:

1. **Search for a book by title**: Queries the Gutendex API directly and inserts the book into the database.
   
2. **List registered books**: Displays all the books that have been inserted into the database.
   
3. **List our authors**: Displays the data of registered authors, including name and associated books.
   
4. **List authors from a specific year**: Filters authors who were alive in a specific year.
   
5. **List books in a specific language**: Filters books by a specific language, such as Spanish, English, French, or Portuguese.

6. **Search books by author**: Displays titles by a specific author.

## Project Setup

This project was created with **Spring Initializr** with the following configurations:

- **Language**: Java
- **Dependency Manager**: Maven
- **Spring Boot Version**: The version compatible with the project.
- **Dependencies**:
  - Spring Data JPA
  - PostgreSQL Driver

## Database

The application uses the **PostgreSQL** database for data persistence. Below is the official website for downloading PostgreSQL:

- [PostgreSQL - Download](https://www.postgresql.org/download/)

## Gutendex API

The **Gutendex API** provides access to over 70,000 books. It can be queried directly through the official website, and we use it to search for and insert books into our database.

- [Gutendex API](https://gutendex.com/)
