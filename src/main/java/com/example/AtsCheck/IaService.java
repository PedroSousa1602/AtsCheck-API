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

    public String analyzeCvOp(String cvText, String opText) {

        try {

            String systemPrompt = """
                    Tu és um especialista sénior em recrutamento e otimização de CVs para sistemas ATS (Applicant Tracking Systems).
                    A tua missão é analisar o CV fornecido em comparação com a descrição da vaga de emprego e responder EXCLUSIVAMENTE num objeto JSON válido. 
                    Não incluas nenhum texto, introdução ou explicação fora do objeto JSON.
                    
                    O JSON deve seguir rigorosamente a seguinte estrutura:
                    {
                        "pontuacaoGeral": 78,
                        "compatibilidadeVaga": 65,
                        "resumoExecutivo": "Resumo da adequação do candidato a esta vaga específica...",
                        "formatacaoEEstrutura": [
                        {
                        "problema": "Nome do problema de formatação ou leitura",
                        "impactoAts": "Como o ATS processa este erro",
                        "solucao": "Como corrigir no documento"
                        }
                        ],
                        "palavrasChaveFaltantes": {
                        "tecnicas": ["requisitos da vaga ausentes no CV"],
                        "ferramentas": ["softwares/ferramentas exigidos na vaga que faltam"],
                        "softSkills": ["competências comportamentais da vaga que faltam"]
                        },
                        "requisitosAtendidos": [
                        "Requisitos da vaga que o candidato já cumpre no CV"
                        ],
                        "sugestoesMetricas": [
                        {
                        "passagemOriginal": "Texto vago no CV",
                        "exemploReescrito": "Sugestão reescrita a incorporar requisitos e palavras-chave da vaga"
                        }
                        ],
                        "sugestaoResumo": {
                        "antes": "Resumo atual (se existir)",
                        "depois": "Novo resumo totalmente alinhado com o título e os requisitos da vaga"
                        }
                    }
                    
                    Garante que o JSON está perfeitamente formatado e fecha todas as aspas, chavetas e colchetes ao terminar.
                    """;

            String userPrompt = String.format("""
                    --- DESCRIÇÃO DA VAGA ---
                    %s
                    
                    --- CV DO CANDIDATO ---
                    %s
                    """, opText, cvText);

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
            throw new RuntimeException("Error analyze CV and Opportunity", e);
        }
    }
}