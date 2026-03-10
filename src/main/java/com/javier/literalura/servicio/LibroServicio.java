package com.javier.literalura.servicio;

import com.javier.literalura.dto.DatosAutor;
import com.javier.literalura.dto.DatosLibro;
import com.javier.literalura.dto.DatosRespuesta;
import com.javier.literalura.modelo.Autor;
import com.javier.literalura.modelo.Libro;
import com.javier.literalura.repositorio.RepositorioAutor;
import com.javier.literalura.repositorio.RepositorioLibro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroServicio {

    private final RepositorioLibro repoLibro;
    private final RepositorioAutor repoAutor;
    private final ConvierteDatos conversor;
    private final ApiGutendex api;

    public LibroServicio(RepositorioLibro repoLibro, RepositorioAutor repoAutor,
                         ConvierteDatos conversor, ApiGutendex api) {
        this.repoLibro = repoLibro;
        this.repoAutor = repoAutor;
        this.conversor = conversor;
        this.api = api;
    }

    public void buscarLibro(String titulo) {
        String json = api.buscarJsonPorTitulo(titulo);
        DatosRespuesta datos = conversor.obtenerDatos(json, DatosRespuesta.class);
        if (datos == null || datos.results().isEmpty()) {
            ystem.out.println("\n--------------------------------------");
            System.out.println("No se encontraron libros con ese título");
            System.out.println("--------------------------------------");
            return;
        }

        DatosLibro libroDatos = datos.results().get(0);

        for (DatosAutor autorDatos : libroDatos.authors()) {
            Autor autor = repoAutor.existsByName(autorDatos.name()) ?
                    repoAutor.findByName(autorDatos.name()) :
                    repoAutor.save(new Autor(autorDatos.name(), autorDatos.birthYear(), autorDatos.deathYear()));

            if (!repoLibro.existsByTitle(libroDatos.title())) {
                repoLibro.save(new Libro(
                        libroDatos.title(),
                        libroDatos.descargas() != null ? libroDatos.descargas() : 0,
                        libroDatos.idiomas(),
                        autor
                ));
            }
        }
        System.out.println("\n--------------------------------------");
        System.out.println("Libro registrado correctamente");
        System.out.println("--------------------------------------");
    }

     public void listarLibros() {
       List<Libro> libros = repoLibro.findAll();
       if (libros.isEmpty()) {
           System.out.println("\n--------------------------------------");
           System.out.println("No hay libros registrados");
           System.out.println("--------------------------------------");
           return;
       }
       libros.forEach(l -> {
           StringBuilder sb = new StringBuilder();

           // Formateo estilo "pretty print"
           sb.append("Nombre: ").append(l.getTitle()).append("\n");
           sb.append("Descargas: ").append(l.getDescargas()).append("\n");
           sb.append("Idiomas: ").append(l.getIdiomas()).append("\n");
           sb.append("Autor: ").append(l.getAutor().getName());

           System.out.println("\n--------------------------------------");
           System.out.println(sb.toString());
           System.out.println("--------------------------------------");
       });
   }

    public void listarAutores() {
        List<Autor> autores = repoAutor.findAll();
        if (autores.isEmpty()) { System.out.println("No hay autores registrados"); return; }
        System.out.println("\n--------------------------------------");
        autores.forEach(a -> System.out.println(
                a.getName() + " (" + a.getBirthYear() + " - " + a.getDeathYear() + ")"));
        System.out.println("--------------------------------------");
    }

    public void autoresVivos(int anio) {
        List<Autor> vivos = repoAutor.findAll().stream()
                .filter(a -> (a.getBirthYear() == null || a.getBirthYear() <= anio) &&
                        (a.getDeathYear() == null || a.getDeathYear() >= anio))
                .collect(Collectors.toList());
        if (vivos.isEmpty()) System.out.println("No hay autores vivos en el año " + anio);
        else
            System.out.println("\n--------------------------------------");
        vivos.forEach(a -> System.out.println(a.getName()));
            System.out.println("--------------------------------------");
    }

    public void estadisticasDescargas() {

        var stats = repoLibro.findAll()
                .stream()
                .filter(l -> l.getDescargas() != null)
                .mapToDouble(Libro::getDescargas)
                .summaryStatistics();
        System.out.println("\n--------------------------------------");
        System.out.println("\nEstadísticas de descargas:");

        System.out.println("Cantidad de libros: " + stats.getCount());
        System.out.println("Promedio de descargas: " + stats.getAverage());
        System.out.println("Máximo de descargas: " + stats.getMax());
        System.out.println("Mínimo de descargas: " + stats.getMin());
        System.out.println("--------------------------------------");
    }

    public void top10LibrosDescargados(){

        List<Libro> top10 = repoLibro.topLibrosDescargados()
                .stream()
                .limit(10)
                .toList();

        if(top10.isEmpty()){
            System.out.println("\n--------------------------------------");
            System.out.println("No hay libros registrados.");
            System.out.println("--------------------------------------");
            return;
        }
        System.out.println("\n--------------------------------------");
        System.out.println("\nTop 10 libros más descargados:\n");
        
        top10.forEach(l ->
                System.out.println(
                        l.getTitle() +
                                " | Autor: " + l.getAutor().getName() +
                                " | Descargas: " + l.getDescargas()
                )
       );
        System.out.println("--------------------------------------");
    }


    public void autoresNacidosDespues(int anio){

        List<Autor> autores = repoAutor.findByBirthYearGreaterThan(anio);

        if(autores.isEmpty()){
            System.out.println("\n--------------------------------------");
            System.out.println("No hay autores nacidos después de ese año.");
            System.out.println("--------------------------------------");
            return;
        }
        System.out.println("\n--------------------------------------");
        System.out.println("\nAutores nacidos después de " + anio + ":\n");

        autores.forEach(a ->
                System.out.println(
                        a.getName() +
                                " | Año nacimiento: " + a.getBirthYear()
                )
        );
        System.out.println("--------------------------------------");
    }

    public void librosPorIdioma(String idioma) {
        List<Libro> filtrados = repoLibro.findAll().stream()
                .filter(l -> l.getIdiomas() != null && l.getIdiomas().contains(idioma))
                .collect(Collectors.toList());

        if (filtrados.isEmpty()) {
            System.out.println("\n--------------------------------------");
            System.out.println("No se encontraron libros en idioma '" + idioma + "'");
            System.out.println("--------------------------------------");
        } else {
            System.out.println("\n--------------------------------------");
            filtrados.forEach(l -> {
                System.out.println("Nombre: " + l.getTitle());
                System.out.println("Autor: " + l.getAutor().getName());
                System.out.println("--------------------------------------");
            });
        }
    }

}
