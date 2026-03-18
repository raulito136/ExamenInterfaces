package org.example.exameninterfaces.repositories;

import org.springframework.stereotype.Repository;

/**
 * Repositorio para la generacion de respuestas mediante IA
 */
@Repository
public interface LLMRepository {
    public String generarRespuesta(String seed);
}
