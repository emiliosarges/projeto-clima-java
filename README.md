# Sistema de Informações Climáticas em Tempo Real (Java)
be informações meteorológicas atuais de uma cidade informada pelo usuário. Este projeto demonstra integração com APIs REST, tratamento de JSON e uso do `HttpClient` do
Aplicação de linha de comando em **Java** que consulta a **WeatherAPI** e exi Java.

## ✅ Funcionalidades
- Solicita ao usuário o **nome da cidade** via terminal.  
- Consulta a WeatherAPI usando uma chave de API armazenada em `api-key.txt` (O arquivo foi incluído no .gitignore, para testar será necessário criar uma chave API no site WeatherAPI).  
- Exibe dados como:
  - Cidade e país  
  - Data e hora da última atualização  
  - Temperatura atual  
  - Sensação térmica  
  - Condição do tempo  
  - Umidade  
  - Velocidade do vento  
  - Pressão atmosférica  
- Detecta quando a cidade não é encontrada (código `1006`).

## 🧰 Tecnologias e Dependências
- **Java 11+** (uso de `java.net.http.HttpClient`)  
- Biblioteca **org.json**  
- Arquivo externo `api-key.txt` contendo sua chave da WeatherAPI

## 🗂️ Estrutura do Projeto
    /projeto
    │
    ├─ ProjetoSistemaDeInformacoesClimaticasEmTempoReal.java
    ├─ api-key.txt
    └─ libs/
       └─ org.json.jar

> **Importante:** Foi adicionado o arquivo `api-key.txt` ao `.gitignore` para não vazar a chave.

## 🔒 .gitignore
    api-key.txt

## 💻 Como Compilar e Executar

### Compilar

Linux/macOS:
    javac -cp ".:libs/org.json.jar" ProjetoSistemaDeInformacoesClimaticasEmTempoReal.java

Windows:
    javac -cp ".;libs/org.json.jar" ProjetoSistemaDeInformacoesClimaticasEmTempoReal.java

### Executar

Linux/macOS:
    java -cp ".:libs/org.json.jar" ProjetoSistemaDeInformacoesClimaticasEmTempoReal

Windows:
    java -cp ".;libs/org.json.jar" ProjetoSistemaDeInformacoesClimaticasEmTempoReal

Quando executado, digite o nome da cidade (ex.: Sao Paulo) e pressione Enter.

## 🔍 Tratamento de Erros
- Cidade não encontrada → mensagem amigável: `Localização não encontrada. Por favor, tente novamente`.  

## 👨‍💻 Autor
Desenvolvido por **Emilio Silva** — projeto para portfólio e demonstração de integração com APIs REST em Java.
