[![vfmneto](https://circleci.com/gh/vfmneto/file-batch-processor.svg?style=shield)](https://github.com/vfmneto/file-batch-processor)
# File Batch Processor

Aplicação desenvolvida para executar processamento em lote de arquivos, para isto o sistema é programado para verificar se chegou novos arquivos, ler, processar e gerar um arquivo com os dados das vendas consolidados.

#### O sistema suporta os tipos de linhas abaixos

##### Dados do vendedor começa com 001
```
001çCPFçNameçSalary
```
##### Dados do cliente começa com 002
```
002çCNPJçNameçBusiness Area
```
##### Dados de vendas começa com 003
```
003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
```
##### Exemplo de arquivo
```
001ç1234567891234çPedroç50000
001ç3245678865434çPauloç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
```
##### Exemplo de Resultado
```
Quantidade de clientes no arquivo de entrada: 2
Quantidade de vendedor no arquivo de entrada: 2
ID da venda mais cara: 10
O pior vendedor: Paulo
```
## Como usar
##### Pré-requisitos:
* Java 11
##### Executando a aplicação

##### Maven
```
./mvnw spring-boot:run
```
##### Docker
```
./mvnw spring-boot:build-image
docker run -d --name processor file-batch-processor:0.0.1-SNAPSHOT
```
##### Parâmetros da aplicação
```
file-batch-processor.directory.processed=${user.home}/file-batch-processor/processed
file-batch-processor.directory.in=file:${user.home}/file-batch-processor/in
file-batch-processor.directory.out=${user.home}/file-batch-processor/out
file-batch-processor.directory.error=${user.home}/file-batch-processor/error
file-batch-processor.directory.invalid=${user.home}/file-batch-processor/invalid
file-batch-processor.interval-in-seconds=*/5 * * * * *
```

### Github
https://github.com/vfmneto/file-batch-processor

### Melhorias na versão 0.0.2-SNAPSHOT

 - Extraído classes para terem responsabilidade única seguindo princípio SOLID;
 - Removido if's desnecessários usando funções lambda para melhorar a legibilidade e manutenção do código;
 - Criado classe de domínio responsável por consolidar os dados de entrada;
 - Refatoramento nos testes de unidades;
 - Configurado aplicação para "Chunk-oriented Processing" quebrando em pedaços a leitura/processamento dos arquivos onde a cada 5 arquivos lidos será feita escrita em lotes de 5 arquivos lidos e processados;
 - Melhoria na organização de pacotes;
 - Extraído classe pai com comportamento comum as classes filhas;

### Tecnologias
* [Spring Boot - [2.4.0]](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#howto-batch-applications)
* [Spring Quartz Scheduler](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-quartz)
* [Java 11](https://docs.oracle.com/en/java/javase/11/)
* [Maven](https://maven.apache.org/)

