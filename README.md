# Backend Anime

This backend, developed in Java with Spring Boot, consumes the Jikan API and exposes its own API to facilitate access to various services.

## Tech Stack

**java:** 17

**springboot:** 3.3.2

**maven**

## Installation

to install the dependencies

```bash
  ./mvnw clean install
```

to run on localhost

```bash
  ./mvnw spring-boot:run
```

to run the tests

```bash
  ./mvnw test
```

## API Reference
BASE_URL = https://api.jikan.moe/v4 

JIKAN: [doc](https://docs.api.jikan.moe/)
#### Get animes

```http
  GET /search
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `query` | `string` | **Not Required** |
| `page` | `number` | **Not Required** |