# 📄 ATS CV Analyzer API

![Java 21](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Spring Boot 3](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=springboot)
![Spring AI](https://img.shields.io/badge/Spring%20AI-Framework-blue?style=flat-square)
![Groq](https://img.shields.io/badge/Groq-API-red?style=flat-square)
![Swagger](https://img.shields.io/badge/Swagger-UI-85EA2D?style=flat-square&logo=swagger&logoColor=black)

API REST desenvolvida em **Java 21** e **Spring Boot 3** que utiliza **Inteligência Artificial (Spring AI + Groq)** para analisar currículos em formato PDF e compará-los com descrições de vagas de emprego.

A aplicação identifica falhas de formatação, calcula a taxa de compatibilidade (*Match Rate*), sugere palavras-chave ausentes e devolve um relatório detalhado em **JSON estruturado**, pronto a ser consumido por qualquer aplicação Frontend.

---

## 🚀 Tecnologias Utilizadas

* **Java 21** — Linguagem de programação principal.
* **Spring Boot 3** — Framework para criação da API REST.
* **Spring AI** — Integração nativa e simplificada com LLMs.
* **Groq API** — Inferência de IA de alta performance e baixa latência.
* **Apache PDFBox** — Extração e leitura de texto de documentos PDF.
* **Springdoc OpenAPI (Swagger)** — Documentação interativa da API.

---

## 🛠️ Funcionalidades

* 📑 **Extração de Texto de PDF:** Leitura automática de ficheiros de CV enviados pelo utilizador.
* 🔒 **Verificação de Encriptação:** Deteção e tratamento de ficheiros PDF protegidos por palavra-passe.
* 🤖 **Análise Inteligente por IA:** Comparação em tempo real do perfil do candidato com os requisitos da vaga.
* 📊 **Pontuação de Match:** Cálculo percentual da taxa de adequação ao cargo.
* 🔑 **Mapeamento de Gaps:** Identificação de competências técnicas (*hard skills*) e *soft skills* ausentes no CV.
* 💡 **Sugestões de Reescrita:** Recomendações práticas e acionáveis para otimizar o currículo para sistemas ATS.

---

## ⚙️ Como Executar o Projeto Localmente

### Pré-requisitos
* **Java 21** instalado.
* **Maven** instalado (ou utilizar o Maven Wrapper `./mvnw`).
* Uma chave de API da **Groq** ([Obter chave gratuita no Groq Console](https://console.groq.com)).

### 1. Clonar o Repositório
```bash
git clone [https://github.com/PedroSousa1602/AtsCheck-API.git](https://github.com/PedroSousa1602/AtsCheck-API.git)
cd AtsCheck-API
