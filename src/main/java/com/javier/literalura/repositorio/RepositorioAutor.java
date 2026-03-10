package com.javier.literalura.repositorio;

import com.javier.literalura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioAutor extends JpaRepository<Autor, Long> {

    boolean existsByName(String name);

    Autor findByName(String name);

    List<Autor> findByNameContainingIgnoreCase(String nombre);

    List<Autor> findByBirthYearGreaterThan(Integer anio);
}