package org.example.exameninterfaces.services;

import org.example.exameninterfaces.repositories.LLMRepository;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;

@Service
/**
 * Clase servicio para la IA con Llama3
 */
public class LLMService implements LLMRepository {
    private final OllamaChatModel chatModel;

    public LLMService(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * La IA genera una respuesta respecto al mensaje del usuario
     * @param seed
     * @return Respuesta de la IA
     */
    @Override
    public String generarRespuesta(String seed) {
        String instrucciones = "Creame una recomendacion al usuario para mejorar la gestion de la tienda: " + seed;

        OllamaChatOptions options = OllamaChatOptions.builder()
                .model("llama3")
                .build();

        var response = chatModel.call(new Prompt(new UserMessage(instrucciones), options));
        return response.getResult().getOutput().getText();
    }
}