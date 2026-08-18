# Processa Dados

Aplicação desenvolvida em **Java** com o objetivo de demonstrar conhecimentos fundamentais da linguagem e conceitos importantes para o desenvolvimento de aplicações que trabalham com grandes volumes de dados.

O projeto aborda desde a **modelagem e leitura de dados** até a integração com banco de dados e a utilização de **paralelismo** para processamento.

## 🎯 Objetivo

O **Processa Dados** foi criado como um projeto prático para demonstrar conhecimentos em:

* Modelagem de dados utilizando Java;
* Leitura e processamento de arquivos;
* Tratamento de erros e exceções;
* Manipulação de grandes volumes de dados;
* Integração com banco de dados utilizando **JDBC (Java Database Connectivity)**;
* Execução de banco de dados em **container**;
* Processamento paralelo e concorrente;
* Organização e estruturação de uma aplicação Java.

## 📊 Dados utilizados

Os dados utilizados no projeto foram obtidos a partir do dataset **PaySim**, disponível no Kaggle:

https://www.kaggle.com/datasets/ealaxi/paysim1

O dataset contém **mais de 6 milhões de registros** que simulam transações financeiras, permitindo trabalhar com um volume significativo de informações e explorar diferentes estratégias de processamento.

Os dados representam operações financeiras simuladas, possibilitando análises e processamentos semelhantes aos encontrados em sistemas financeiros.

## 🛠️ Tecnologias e conceitos

O projeto utiliza principalmente:

* **Java**
* **JDBC**
* **SQL**
* **Banco de dados em container**
* **Docker**
* **Processamento paralelo**
* **Tratamento de exceções**
* **Manipulação de arquivos**
* **Modelagem de dados**

## 🏗️ Principais etapas da aplicação

De forma geral, a aplicação trabalha com um fluxo semelhante a:

```text
Arquivo de dados
      │
      ▼
Leitura dos registros
      │
      ▼
Modelagem dos dados
      │
      ▼
Processamento
      │
      ├──────────────► Processamento paralelo
      │
      ▼
Persistência
      │
      ▼
Banco de dados
      │
      ▼
Consultas / análise dos dados
```

A aplicação foi pensada para permitir a exploração dos desafios envolvidos no processamento de um arquivo com milhões de registros, principalmente em relação ao consumo de memória, tempo de processamento, persistência e paralelismo.

## 🗄️ Banco de dados

A comunicação com o banco de dados é realizada utilizando **JDBC**, permitindo demonstrar o acesso ao banco diretamente por meio das APIs disponibilizadas pelo Java.

O banco é executado em um **container**, facilitando a configuração do ambiente de desenvolvimento e evitando a necessidade de instalar e configurar o banco diretamente na máquina.

## ⚡ Paralelismo

Um dos objetivos do projeto é demonstrar a utilização de **paralelismo no processamento dos dados**.

Como o dataset possui mais de 6 milhões de registros, o projeto permite explorar como a divisão do trabalho entre diferentes threads pode ser utilizada para melhorar o processamento de grandes volumes de informações.

Além de demonstrar a implementação técnica, o projeto busca evidenciar alguns dos desafios relacionados à programação concorrente, como divisão de tarefas, sincronização e acesso compartilhado a recursos.

## 🚨 Tratamento de erros

O projeto também demonstra práticas de **tratamento de erros e exceções**, principalmente em operações que podem apresentar falhas, como:

* Leitura do arquivo;
* Conversão e validação dos dados;
* Comunicação com o banco de dados;
* Execução de comandos SQL;
* Processamento paralelo.

A ideia é evitar que uma falha inesperada interrompa todo o processamento sem o devido tratamento.

## 📁 Dataset

Para executar o projeto, é necessário obter o dataset utilizado na aplicação através do Kaggle:

https://www.kaggle.com/datasets/ealaxi/paysim1

Após o download, o arquivo deve ser disponibilizado no local esperado pela aplicação.

> **Observação:** o dataset possui milhões de registros e pode ocupar uma quantidade significativa de espaço em disco. O processamento também pode exigir recursos consideráveis da máquina.

## 📚 Sobre o projeto

Mais do que uma aplicação voltada para uma regra de negócio específica, o **Processa Dados** funciona como um projeto de estudo e demonstração de conceitos fundamentais do Java aplicados a um cenário de processamento de grandes volumes de dados.

O projeto reúne conceitos como **orientação a objetos, modelagem, I/O, tratamento de exceções, JDBC, persistência, containers e paralelismo**, permitindo observar como esses recursos podem ser combinados em uma aplicação prática.
