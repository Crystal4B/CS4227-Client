# CS4227-Software-Design-And-Architecture

[![Gradle Build & Test](https://github.com/Crystal4B/CS4227-Client/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/Crystal4B/CS4227-Client/actions/workflows/gradle.yml) 
[![codecov](https://codecov.io/gh/Crystal4B/CS4227-Client/branch/main/graph/badge.svg?token=HZGEIPDLJV)](https://codecov.io/gh/Crystal4B/CS4227-Client)

## About
Hotel reservation system is a Java project designed to show effective and efficient use of design patterns and architectural patterns. The applications allows users to book and reserve different rooms. The user specifies a check-in and check-out date and the system then checks if a room is available. A room can accommodate multiple guests specified by the user. The system can be used by multiple types of users including a customer and staff members.

## Project Execution Guide
The execution process for the project is simple. To start make sure the correct server-ip is located within the config.properties file inside app > src > main > resources folder, if you intend to run and test the project locally make sure to follow the [Server Deployment Guide](https://github.com/Crystal4B/CS4227-Server#deploying-application) and put the server-ip as "http://localhost:8080/graphql". Run the following command in the terminal `gradlew shadowJar` following that run `java -jar /app/build/libs/app-all.jar`.

## Structure and Design
The hotel reservation system is designed using multiple technolgies/tools. It operates on a client-server design. The following are the technologies/tools used:

| Technology/Tool | Usage                                               | Implemented By                        |
|-----------------|-----------------------------------------------------|---------------------------------------|
| Java            | Programming language of choice.                     | -                                     |
| Gradle          | Used to build and test Java packages.               | Marcin Sęk                            |
| GitHub          | Used as version control for project.                | Marcin Sęk                            |
| GitHub Actions  | Used to create CI/CD pipeline.                      | Jordan Marshall & Aleksandr Jakusevs  |
| GraphQL         | Data query and manipulation language for APIs.      | Marcin Sęk                            |
| SQLite          | Used as a datebase to store data.                   | Marcin Sęk                            |
| Jacoco          | Used to generate code coverage reports.             | Jordan Marshall                       |
| CodeCov         | Analyse code coverage reports                       | Jordan Marshall                       |
| SpotBugs        | Static byte code analysis to look for bugs          | Jakub Pažej                           |

We used multiple design and architecture patterns as part of the project. The following are the patterns we used, aswell as who implemented them:

| Pattern             | Implemented By                    |
|---------------------|-----------------------------------|
| Client - Server     | Marcin Sek                        |
| Command             | Marcin Sęk                        |
| Interceptor         | Jakub Pažej                       |
| Abstract Factory    | Jakub Pažej                       |
| Template            | Aleksandr Jakusevs                |
| Visitor             | Aleksandr Jakusevs                |
| Builder             | Jordan Marshall                   |
| State (Not Counted) | Jordan Marshall                   |
| Adapter             | Eoin McDonough                    |


## Contibutors
- Marcin Sęk -            18254187         
- Jordan Marshall -       18256716    
- Aleksandr Jakusevs -    18257038  
- Jakub Pažej -           18260179         
- Eoin McDonough -        18241646      

## Links
- Overall Repository: https://github.com/Crystal4B/CS4227-Software-Design-And-Architecture
- Client Repository:  https://github.com/Crystal4B/CS4227-Client/
- Server Repository:  https://github.com/Crystal4B/CS4227-Server/
