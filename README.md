# Spring Data JPA Mapping

## Start Application

* To `mapstruct` to work properly, use following commands to run the application.

```shell

mvn clean package -DskipTests
mvn spring-boot:run

```

## ManyToOne

* User Table

| ID   | NAME |
|------|------|
| 1001 | Ashu |

* Post Table

| ID                               | MESSAGE | POST_CREATE_TIME           | POST_LAST_UPDATE_TIME      | USER_ID |
|----------------------------------|---------|----------------------------|----------------------------|---------|
| 48ba9b1c22ec4030b064f8d226cfdac1 | Hi Ashu | 2022-05-08 12:59:57.844103 | 2022-05-08 12:59:57.844103 | 1001    |

## OneToOne

* Employee Table

| ID   | NAME |
|------|------|
| 1001 | Ashu |

* Locker Table

| LOCKER_NO | EMPLOYEE_ID |
|-----------|-------------|
| LOCK101   | 1001        |

