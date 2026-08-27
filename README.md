# 📄 ATS CV Analyzer API

API REST desenvolvida em **Java 21** e **Spring Boot 3** que utiliza **Inteligência Artificial (Spring AI + Groq)** para analisar currículos em formato PDF e compará-los com descrições de vagas de emprego. 

A aplicação identifica falhas de formatação, calcula a taxa de compatibilidade (*Match Rate*), sugere palavras-chave ausentes e devolve um relatório detalhado em **JSON estruturado**, pronto a ser consumido por uma aplicação Frontend.

---

## 🚀 Tecnologias Utilizadas

* **Java 21** — Linguagem de programação principal (LTS).
* **Spring Boot 3** — Framework para criação de microsserviços e APIs REST.
* **Spring AI** — Integração simplificada com modelos de Linguagem Avançados (LLM).
* **Groq API** — Processamento de IA de alta performance.
* **Apache PDFBox** — Extração e processamento de texto a partir de documentos PDF.
* **Dotenv / Spring Environment** — Gestão segura de variáveis de ambiente.

---

## 🛠️ Funcionalidades

* 📑 **Extração de Texto de PDF:** Leitura automática de ficheiros de CV enviados pelo utilizador.
* 🔒 **Verificação de Encriptação:** Deteção e tratamento de ficheiros PDF protegidos por palavra-passe.
* 🤖 **Análise por IA:** Comparação entre o perfil do candidato e a descrição da vaga enviada.
* 📊 **Pontuação e Compatibilidade:** Cálculo automático da nota geral e da taxa de adequação ao cargo.
* 🔑 **Mapeamento de Palavras-Chave:** Identificação de competências técnicas, ferramentas e soft skills em falta.
* 💡 **Sugestões de Reescrita:** Exemplos práticos para melhorar métricas e resumos profissionais.

---

## ⚙️ Como Executar o Projeto Localmente

### Pré-requisitos
* **Java 21** instalado.
* **Maven** instalado (ou utilizar o wrapper `./mvnw`).
* Uma chave de API da **Groq** ([Obter chave aqui](https://console.groq.com)).

### 1. Clonar o Repositório
```bash
git clone [https://github.com/teu-utilizador/teu-repositorio.git](https://github.com/teu-utilizador/teu-repositorio.git)
cd teu-repositorio
