package com.milena.literalura.repository;

import com.milena.literalura.model.Autor;
import com.milena.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    Optional<Livro> findByTitulo(String titulo);

    @Query("SELECT a FROM Autor a JOIN FETCH a.livro")
    List<Autor> findAllAutores();

    List<Livro> findByIdioma(String idiomaSolicitado);

    @Query("SELECT l FROM Livro l JOIN l.autores a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))")
    List<Livro> findByAutor(@Param("nomeAutor") String nomeAutor);
}
