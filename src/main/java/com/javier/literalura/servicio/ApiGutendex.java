package com.javier.literalura.servicio;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ApiGutendex {

    public String buscarJsonPorTitulo(String titulo) {
        try {
            String urlString = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) content.append(line);
            in.close();
            con.disconnect();

            return content.toString();
        } catch (Exception e) {
            System.out.println("Error al consultar a la API: " + e.getMessage());
            return null;
        }
    }
}