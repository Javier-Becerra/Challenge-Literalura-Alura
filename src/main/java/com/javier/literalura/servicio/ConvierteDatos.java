package com.javier.literalura.servicio;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConvierteDatos {
    private final ObjectMapper mapper;

    public ConvierteDatos() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            if (json == null || json.isBlank()) return null;
            return mapper.readValue(json, clase);
        } catch (Exception e) {
            System.out.println("Error en conversión JSON: " + e.getMessage());
            return null;
        }
    }
}