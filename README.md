# Hinto API

## API do sistema HINTO - uma lista de filmes e animes, com recomendações personalizadas

### Tecnologias

 - Java 11
 - Git 2.9.0
 - Maven 3.6
 - Spring boot 2.4

**Testes**
 - H2
 - JUnit
 
###### Como clonar o projeto
	git clone https://github.com/marcelosimim/hinto.api.git

###### Como rodar o projeto com Maven
	Na pasta do projeto:
	cd hinto.api
  	mvn package
	cd target
	na pasta target se encontrará o executável .jar da aplicação que pode ser rodado por terminal java -jar nomeJar.jar ou diretamente nela

###### Verbos para [Usuario] Http da API:
        Criar Usuário	          | POST   | /usuario - conteúdo do corpo da requisição: application/json
	Autenticar Usuário	  | POST   | /usuario/autenticar - conteúdo do corpo da requisição: application/json com email e senha
  	Atualizar Usuário por ID  | PUT    | /usuario/{id} - conteúdo do corpo da requisição: application/json | variável da URI: Integer id
  	Deletar Usuário por ID	  | DELETE | /usuario/{id} - variável da URI: Integer id
	Obter Produto por ID      | GET    | /usuario/{id} - variável da URI: Integer id
  	Obter todos os Usuários	  | GET    | /usuario
	
###### Verbos para [Midia] Http da API:
        Criar Mídia	          | POST   | /midia - conteúdo do corpo da requisição: application/json
  	Atualizar Mídia por ID    | PUT    | /midia/{id} - conteúdo do corpo da requisição: application/json | variável da URI: Integer id
  	Deletar Mídia por ID	  | DELETE | /midia/{id} - variável da URI: Integer id
	Obter Mídia por ID        | GET    | /midia/{id} - variável da URI: Integer id
  	Obter todos as Mídias	  | GET    | /midia

