# Example Native


## Descrição

Propósito desse app é utilizarmos alguns recursos e ferramentas básicas do spring boot 3.0 e o build nativo utilizando graalvm
A ideia é validarmos performance e funcionamento com um build nativo.

## Teconologia

No projeto vamos usar as seguintes tecnologias:

- [Spring Boot 3.0.1](https://spring.io/projects/spring-boot)
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Redis](https://redis.io/)
- [RABBITMQMQ](https://www.RABBITMQmq.com/)
- [Mysql 8](https://www.mysql.com/)
- [GraalVM](https://www.graalvm.org/java/)
- [SDKMan](https://sdkman.io/)

## Instalação
Para instalar o compilador de imagem nativa recomendo utilizar [sdkman](https://sdkman.io/) para Linux ou MacOS
Para windows siga [essas instruções](https://medium.com/graalvm/using-graalvm-and-native-image-on-windows-10-9954dc071311) de instalação

```sh
$ sdk install java 22.3.r17-nik
$ sdk use java 22.3.r17-nik
```

```sh
$ java -version
openjdk version "17.0.5" 2022-10-18 LTS
OpenJDK Runtime Environment GraalVM 22.3.0 (build 17.0.5+8-LTS)
OpenJDK 64-Bit Server VM GraalVM 22.3.0 (build 17.0.5+8-LTS, mixed mode)

```

## Variáveis de Ambiente
| Nome | Descrição | Valor Padrão |
|------|-----------|:------------:|
PORT|Porta do socket|8080
JDBC_URL|String connection do banco de dados|jdbc:mysql://localhost:3306/database?useSSL=false&useTimezone=true&serverTimezone=UTC
DATABASE_USER|Usuário de banco de dados|root
DATABASE_PASS|Senha de banco de dados|root
LOGGING_LEVEL|Nível de severidade logs que devem ser apresentados no console. Veja os [possíveis valores](https://logging.apache.org/log4j/2.x/manual/customloglevels.html)|info
REDIS_HOST|Host do Redis.|locahost
REDIS_PORT|Porta do Redis.|6379
RABBITMQ_HOST|Host do RABBITMQlocalhost
RABBITMQ_PORT|Porta do RABBITMQ|5672
RABBITMQ_USER|User RABBITMQ
RABBITMQ_PASSWORD|Senha RABBITMQ
RABBITMQ_VHOST|Vhost RABBITMQ| teste_performance
RABBITMQ_EXCHANGE|Exchange RABBITMQ| teste_key
RABBITMQ_QUEUE|Queue RABBITMQ| teste_performance_queue
JWT_SECRET|JWT Secret| teste


### Executar
```sh
$ mvn spring-boot:run
```

### Executar testes unitários
```sh
$ mvn test verify jacoco:report
```
### Build da aplicação
```sh
$ mvn -Pnative native:compile
```
Após o build um binário será criado na pasta **target**
### Executando arquivo compilado
```sh
$ target/example-native
```

