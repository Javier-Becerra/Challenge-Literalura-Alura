package com.javier.literalura.principal;

import com.javier.literalura.servicio.LibroServicio;
import java.util.Scanner;

public class Menu {

    private final Scanner teclado = new Scanner(System.in);
    private final LibroServicio servicio;

    public Menu(LibroServicio servicio){
        this.servicio = servicio;
    }

    public void mostrarMenu(){
        int opcion = -1;

        while(opcion != 0){
            System.out.println("""
                \n
                ============ LITERALURA ============
                
                1 - Buscar libro por título
                2 - Listar libros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos en un año
                5 - Listar libros por idioma
                
                6 - Top 10 libros más descargados 
                7 - Estadísticas de descargas 
                8 - Autores nacidos después de un año
                
                0 - Salir
                
                Seleccione una opción:""");

            if(teclado.hasNextInt()){
                opcion = teclado.nextInt();
                teclado.nextLine();
            } else {
                System.out.println("Opción inválida. Intente nuevamente.");
                teclado.nextLine();
                continue;
            }

            switch (opcion){
                case 1 -> {
                    System.out.println("Ingrese el título del libro:");
                    String titulo = teclado.nextLine();
                    servicio.buscarLibro(titulo);
                }
                case 2 -> servicio.listarLibros();
                case 3 -> servicio.listarAutores();
                case 4 -> {
                    System.out.println("Ingrese el año:");
                    Integer anio = teclado.nextInt();
                    teclado.nextLine();
                    servicio.autoresVivos(anio);
                }
                case 5 -> {
                    System.out.println("""
                        \n
                        == Idiomas disponibles ==
                        
                        es - Español.
                        en - Inglés.
                        fr - Francés.
                        pt - Portugués.
                        
                        Ingrese el idioma a consultar:""");
                    String idioma = teclado.nextLine();
                    servicio.librosPorIdioma(idioma);
                }

                case 6 -> servicio.top10LibrosDescargados();

                case 7 -> servicio.estadisticasDescargas();

                case 8 -> {
                    System.out.println("Ingrese el año:");
                    int anio = teclado.nextInt();
                    teclado.nextLine();
                    servicio.autoresNacidosDespues(anio);
                }

                case 0 -> System.out.println("Cerrando aplicación... Gracias por usar el programa!.");
                default -> System.out.println("Opción no inválida. Intente nuevamente.");
            }
        }
    }
}