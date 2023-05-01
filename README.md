# credit-card-spring

Trabalho para a disciplina de Webservices and Restful do MBA Full-Stack da FIAP.

Alunos:

- Felipe Antunes
- Raquel Ferreira
- Wellington Pimentel

## Tecnologias utilizadas

Para o banco de dados foi utilizado o H2. Com o intuito de facilitar o desenvolvimento e a configuração do ambiente.

Para o processamento de arquivos foi utilizado o Spring Batch.

Para a geração e o download do arquivo de exportação de transações foi utilizado a dependência [commons-csv](https://commons.apache.org/proper/commons-csv/dependency-info.html).

Para a documentação foi utilizado SpringFox. 
## Como subir o projeto

O sistema é constituído por apenas um projeto principal. Para subi-lo basta executar o projeto no intellij.

O arquivo `src/main/resources/alunos.txt` é processado e inserido no banco automaticamente ao rodar o projeto.

Após a inserção dos alunos na base, uma massa de transações é gerada automaticamente para o primeiro aluno, ID 1.

Os endpoints ficarão disponíveis na porta 8080.

Para o CRUD de alunos: `/students`.

Para a Criação, Listagem e Remoção de transações: `/transactions`.

Para o download do extrato de transações: GET `/students/1/transactions/export-to-csv`

## Como acessar o Swagger e documentação

Para acesso ao swagger necessário acessar o endereço /swagger.html (no nosso exemplo: http://localhost:8080/swagger.html )

Para acesso à documentação das apis, acessar o endereço /v2/api-docs (no nosso exemplo: http://localhost:8080/v2/api-docs )
