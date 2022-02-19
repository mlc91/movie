# **Movie**
![imageTMDB](https://www.themoviedb.org/assets/2/v4/logos/v2/blue_short-8e7b30f73a4020692ccca9c88bafe5dcb6f8a62a4c6bc55cd9ba82bb2cd95f6c.svg)

## :movie_camera: **INTRODUCTION**
For this project I created a back-end server to manage the users with different roles (admin and 'common' user) to store, list and delete favorite persons or movies and to consume data from the TMDB API (https://www.themoviedb.org/). 

I developed a web application using Java RESTful web service to handle multi-user access and communication, store persistent data and implement complex business logic. More specifically, I: 

 - Used Java and the Spring Boot suite of tools to:
   - implement a RESTful API to both the database and application services.
   - apply modern-day Java programming concepts, such as lambdas, streams, and dependency injection.
 - Use JavaScript Object Notation (JSON) data structures to:
   - communicate between TMDB API and Java back-end.
   - implement the "model" in a model-view-controller.

## :movie_camera: **DESCRIPTION**
In this application a user without any registration can:
(All of these actions were written to consume from the API)
- Filter movies by:
  - Query.
  - Language.
  - Adult's movies.
  - Region.
  - Year.
  - Release date.
- Filter persons by:
  - Query.
  - Language.
  - Adult's movies.
  - Region.

A user registrated can:
- Save in the database a favorite movie or person. 
- List all the favorite movies or persons from the database.
- Delete a favorite person or movie from the database.
- Find a movie by name, release year or director from the database.

An admin can:
- List all movies and persons selected by a user as a favorite.

The ERD to illustrate the relationships between entities: 

![imageDER](https://github.com/mlc91/movie/blob/main/DER.png?raw=true)

## :wrench:**RESOURCES USED**
- JDK 8
- Spring Boot 2.6.3
- Maven
- IDE: IntelliJ IDEA 2021.3.1
- H2 Database
- Postman
- GitHub Desktop

:octocat:**Proyect developed by María Lucía Cuenca** https://github.com/mlc91
