package com.javier.literalura;

import com.javier.literalura.principal.Menu;
import com.javier.literalura.servicio.LibroServicio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private final LibroServicio servicio;

	public LiteraluraApplication(LibroServicio servicio) {
		this.servicio = servicio;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		new Menu(servicio).mostrarMenu();
	}
}