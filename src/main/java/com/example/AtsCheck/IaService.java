package com.example.AtsCheck;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class IaService {

    private final ChatModel chatModel;

    public IaService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String analyzeCv(String cvText) {
        try {

            String systemPrompt = """
                Tu és um especialista sénior em recrutamento B2B e otimização de CVs para sistemas ATS (Applicant Tracking Systems).
                A tua missão é analisar o CV fornecido e responder EXCLUSIVAMENTE num formato JSON válido, sem texto explicativo antes ou depois.
                
                O JSON deve seguir rigorosamente a seguinte estrutura:
                {
                  "pontuacaoGeral": 75,
                  "resumoExecutivo": "Pequena síntese global da qualidade do CV em relação aos padrões ATS.",
                  "formatacaoEEstrutura": [
                    {
                      "problema": "Nome do problema encontrado",
                      "impactoAts": "Por que motivo o ATS ignora ou lê mal esta secção",
                      "solucao": "Como corrigir no documento"
                    }
                  ],
                  "palavrasChaveFaltantes": {
                    "tecnicas": ["lista", "de", "palavras"],
                    "ferramentas": ["exemplo", "CRM"],
                    "softSkills": ["exemplo", "Liderança"]
                  },
                  "sugestoesMetricas": [
                    {
                      "passagemOriginal": "Texto vago detetado no CV",
                      "exemploReescrito": "Sugestão de reescrita quantificável com números ou métricas %"
                    }
                  ],
                  "sugestaoResumo": {
                    "antes": "Resumo atual detetado",
                    "depois": "Novo resumo otimizado para impacto e palavras-chave"
                  }
                }
                """;

            String userPrompt = "Analisa o seguinte CV e gera o relatório JSON:\n\n" + cvText;


            String fullPrompt = systemPrompt + "\n\n" + userPrompt;

            var response = chatModel.call(
                    new Prompt(
                            fullPrompt,
                            OpenAiChatOptions.builder()
                                    .withModel("openai/gpt-oss-20b")
                                    .withTemperature(0.4f)
                                    .withMaxTokens(2500)
                                    .build()
                    )
            );

            return response.getResult().getOutput().getContent();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao analisar o CV com a IA", e);
        }
    }
}