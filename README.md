# Spring Data JPA Mapping

A hands-on reference project demonstrating how to model and implement common
JPA/Hibernate entity relationships — **`@OneToOne`**, **`@OneToMany`**, and **`@ManyToOne`** — using **Spring Boot**,
**Spring Data JPA**, **H2**, and **Lombok**, with **MapStruct** for entity–DTO mapping.

The goal isn't to build a full application, but to give a clear, runnable
example of each mapping strategy that you can read through, run, and adapt
for your own projects.

## What's inside

- Working examples of the three core JPA entity relationships:
  - `@OneToOne` (e.g. Employee ↔ Locker)
  - `@OneToMany` / `@ManyToOne` (e.g. User → Posts)
- Spring Data JPA repositories backing each entity
- MapStruct-based mapping between entities and DTOs
- An in-memory H2 database for zero-setup local testing
- A Swagger/OpenAPI UI to explore and call the REST endpoints
- A Postman collection for exercising each endpoint

## Tech stack

| Layer                 | Technology                    |
|-----------------------|-------------------------------|
| Language              | Java                          |
| Framework             | Spring Boot / Spring Data JPA |
| ORM                   | Hibernate                     |
| Database              | H2 (in-memory)                |
| Boilerplate reduction | Lombok                        |
| DTO mapping           | MapStruct                     |
| Build tool            | Gradle                        |
| API docs              | Swagger UI                    |
| API testing           | Postman                       |

## Getting started

### Prerequisites

- JDK (version used by this project — check `build.gradle`)
- Gradle (wrapper included, no separate install needed)

### Run the application

MapStruct generates mapper implementations at build time, so a clean build
is required before running:

```bash
gradle clean build
gradle clean bootRun
```

### Explore the API

Once the app is running, open the Swagger UI to see and try all available
endpoints:

```
http://localhost:8080/swagger-ui/index.html
```

## Entity relationship examples

### `@ManyToOne` — User → Post

A `User` can have many `Post`s; each `Post` belongs to one `User`.

| ID   | NAME |
|------|------|
| 1001 | Ashu |

| ID          | MESSAGE | POST_CREATE_TIME    | POST_LAST_UPDATE_TIME | USER_ID |
|-------------|---------|---------------------|-----------------------|---------|
| 48ba9b1c... | Hi Ashu | 2022-05-08 12:59:57 | 2022-05-08 12:59:57   | 1001    |

### `@OneToOne` — Employee ↔ Locker

Each `Employee` is assigned exactly one `Locker`, and vice versa.

| ID   | NAME |
|------|------|
| 1001 | Ashu |

| LOCKER_NO | EMPLOYEE_ID |
|-----------|-------------|
| LOCK101   | 1001        |

## Testing

Import and run the included Postman collection to exercise the API and
verify each relationship works as expected:

```
spring-data-jpa-mapping.postman_collection.json
```

Check the collection's test results for any failures.

## Why this repo exists

JPA relationship mappings (`@OneToOne`, `@OneToMany`, `@ManyToOne`,
`@ManyToMany`) are easy to get subtly wrong — mismatched `mappedBy`
attributes, unintended cascades, N+1 query problems, and confusing
foreign-key ownership are common pitfalls. This repo exists as a small,
self-contained, runnable reference so these patterns can be inspected,
tested via Swagger/Postman, and reused as a starting point in real
projects, rather than re-derived from documentation each time.

## Contributing

Issues and pull requests are welcome — especially additions that cover
other mapping types (e.g. `@ManyToMany`) or edge cases worth documenting.