package com.javier.literalura.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    private Integer descargas;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {}
    public Libro(String title, Integer descargas, List<String> idiomas, Autor autor) {
        this.title = title;
        this.descargas = descargas;
        this.idiomas = idiomas;
        this.autor = autor;
    }

    // Getters y setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Integer getDescargas() { return descargas; }
    public List<String> getIdiomas() { return idiomas; }
    public Autor getAutor() { return autor; }

    public void setTitle(String title) { this.title = title; }
    public void setDescargas(Integer descargas) { this.descargas = descargas; }
    public void setIdiomas(List<String> idiomas) { this.idiomas = idiomas; }
    public void setAutor(Autor autor) { this.autor = autor; }
}