# CIS 530 Assignment 4.2: Student Management API

Spring Boot, Spring Data JPA, MySQL. Three layers: Controller, Service, Repository.

## Setup
1. Run db/setup.sql in MySQL Workbench (change the password first).
2. Put the same username and password in src/main/resources/application.properties.
3. Start the app with `mvn spring-boot:run` (Java 17 or newer).
4. On first start the student table is created and 12 sample rows are inserted.
5. Import postman/CIS530_Week4.postman_collection.json into Postman.

## Endpoints
POST /api/students, GET /api/students, GET/PUT/DELETE /api/students/{id},
GET /api/students/major/{major}, /gpa/{gpa}, /year/{year}, /top3,
/sort?direction=asc|desc, /page?page=0&size=5, /major-jpql/{major},
DELETE /api/students/year/{year}/jpql
