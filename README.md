# Aplicativo de Controle de Movimentações para uma Loja de Celulares  
Projeto desenvolvido de forma livre para apefeiçoamento das habilidades de programação adquiridas. A aplicação busca simular as movimentações de entrada e saída de material
realizadas por uma loja de celular qualquer, manter o histórico dessas movimentações e gerenciar os fornecedores, depósitos e produtos relacionados a essa loja.    
  
  
## Tecnologias Utilizas  
- NetBeans: Ambiente de desenvolvimento escolhido.  
- Java: linguagem de programação base para execução da maior parte das funcionalidades do sistema e conexão com o banco de dados de preferência.  
- MySQL: Sistema Gerenciador de Banco de Dados escolhido.  
- SQL: Para criação das tabelas e seus relacionamentos, inserção de dados preliminares, criação de funcões, views e triggers.  
  
  
## Instruções para executar a aplicação  
A aplicação pode ser executada através do arquivo "Controle Loja de Celular.jar" no seguinte caminho deste projeto: "Controle-Loja-de-Celular
/target/".  
  
  
## Instruções para compilação do código  
Fazer o clone do repositório na sua máquina local.  
1. Executar os scripts de sql localizados no caminho "Controle-Loja-de-Celular
/banco de dados/" na seguinte ordem: "Script_Criação.sql", "Script_Povoamento.sql" e "Script_Manipulação_Segurança.sql".  
2. Inserir no arquivo "persistence.xml" localizado no caminho "Controle-Loja-de-Celular/src/main/resources/META-INF
/persistence.xml" os dados para conexão com o banco de dados, "user" e "password" nas linhas 17 e 18 respectivamente.  
3. Fazer o Build do Projeto.  
