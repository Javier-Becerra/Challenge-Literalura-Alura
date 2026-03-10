package com.javier.literalura.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros;

    public Autor() {}
    public Autor(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    // Getters y setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getBirthYear() { return birthYear; }
    public Integer getDeathYear() { return deathYear; }
    public List<Libro> getLibros() { return libros; }

    public void setName(String name) { this.name = name; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }
    public void setDeathYear(Integer deathYear) { this.deathYear = deathYear; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }
}