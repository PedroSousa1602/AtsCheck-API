package com.example.AtsCheck;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GroqConfig {

    @Bean
    @Primary
    public OpenAiChatModel chatModel() {
        // Tenta carregar do ficheiro .env se existir
        String apiKey = null;
        try {
            Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
            apiKey = dotenv.get("GROQ_API_KEY");
        } catch (Exception e) {
            // Ignora se falhar o ficheiro .env
        }

        // Fallback para variáveis de ambiente do sistema
        if (apiKey == null || apiKey.isBlank()) {
            apiKey = System.getenv("GROQ_API_KEY");
        }

        // Validação explícita
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("ERRO: GROQ_API_KEY não foi encontrada no ficheiro .env nem nas variáveis de ambiente!");
        }


        OpenAiApi openAiApi = new OpenAiApi("https://api.groq.com/openai", apiKey);
        return new OpenAiChatModel(openAiApi);
    }
}