API do Kartódromo

Este é o back-end de um sistema para gerenciamento de pilotos e campeonatos de kart. A API foi construída utilizando Java, Spring Boot e banco de dados PostgreSQL.

Recursos implementados
Cadastro, listagem, atualização e exclusão de pilotos.

Cadastro, listagem, atualização e exclusão de campeonatos.

Relatório de vitórias e participações por piloto.

Relacionamento entre campeonatos e pilotos (1º, 2º e 3º colocados).

Validação de dados obrigatórios com mensagens específicas.

Tratamento de erros como:

Dados inválidos (HTTP 422),

Conflito de entidades já existentes (HTTP 409),

Entidades não encontradas (HTTP 404).

Tecnologias utilizadas
Java 17+

Spring Boot

Spring Web

Spring Data JPA

Spring Validation (Hibernate Validator)

PostgreSQL

Maven

Endpoints da API
Pilotos
GET /pilotos
Lista todos os pilotos cadastrados.

POST /pilotos


{
 
  "nome": "Ayrton Senna",
  
  "equipe": "MCLAREM"

}

PUT /pilotos/{id}
Atualiza nome e equipe de um piloto.

DELETE /pilotos/{id}
Remove um piloto pelo ID.

GET /pilotos/vitorias
Retorna todos os pilotos com suas vitórias e participações em campeonatos.

Campeonatos
GET /camp
Lista todos os campeonatos cadastrados.

POST /camp

json
Copiar
Editar

{

  "nome": "Grande Prêmio Brasil",
  
  
  "NumeroDeVoltas": 50,
  
  
  "pista": "OUTDOOR",
  
  
  "first": 1,
  
  
  "second": 2,
  
  
  "third": 3
  

}

PUT /camp/{id}
Atualiza os dados de um campeonato existente.

DELETE /camp/{id}
Remove um campeonato pelo ID.

Nos campos first, second e third do campeonato, devem ser informados os IDs de pilotos válidos já cadastrados no sistema.
