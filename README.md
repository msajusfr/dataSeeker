# dataSeeker

dataSeeker is a Spring Boot sample application that demonstrates how to retrieve and query documents on a chosen topic using LangChain4j and OpenAI models. The structure is inspired by the Cat Application and relies on Spring Boot and Thymeleaf. Document data is stored in memory, so no database is required.

## 1. Maven project setup

Create a standard Spring Boot project with a `pom.xml` that includes dependencies for:

- `spring-boot-starter-web` – REST endpoints
- `spring-boot-starter-thymeleaf` – HTML views
- (no database dependency required)
- `io.github.cdimascio:dotenv-java` – loads variables from a `.env` file
- `dev.langchain4j:langchain4j` and `langchain4j-open-ai` – access OpenAI models

A snippet of the dependencies section might look like:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>io.github.cdimascio</groupId>
        <artifactId>dotenv-java</artifactId>
    </dependency>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j</artifactId>
    </dependency>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-open-ai</artifactId>
    </dependency>
</dependencies>
```

Because documents are stored in memory, no JDBC driver or database configuration is needed.

Set the Java version and Spring Boot plugin in the build section as usual.

## 2. `.env` configuration

Store API secrets in a `.env` file in the project root:

```
OPENAI_API_KEY=your-openai-api-key
OPENAI_MODEL=gpt-3.5-turbo
```

The `dotenv-java` library reads these variables so they can be injected as environment properties.

## 3. Controllers and endpoints

Implement a `DocumentController` that exposes REST endpoints for querying and managing documents:

- `GET /search?query=term` – search stored documents by term. If OpenAI credentials are configured the service also returns an answer generated with LangChain4j.
- `GET /documents/{id}` – retrieve an individual document.
- `POST /documents` – add a document (for example, text or URL) to the repository and store embeddings for later queries.

These endpoints interact with a service layer that stores documents in memory and uses LangChain4j to build prompts or retrieval-augmented generation workflows.

## 4. README instructions

The README should document how to configure environment variables, build, and run the server.

Typical usage:

```bash
# copy .env.example to .env and fill in your OpenAI credentials
mvn clean package
java -jar target/dataSeeker-0.0.1-SNAPSHOT.jar
```

During development you can also run:

```bash
mvn spring-boot:run
```

This plan should provide a basis for implementing the `dataSeeker` application with Spring Boot, LangChain4j, and OpenAI.
