package com.javier.literalura.repositorio;

import com.javier.literalura.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioLibro extends JpaRepository<Libro, Long> {

    boolean existsByTitle(String title);

    @Query("SELECT l FROM Libro l ORDER BY l.descargas DESC")
    List<Libro> topLibrosDescargados();
}