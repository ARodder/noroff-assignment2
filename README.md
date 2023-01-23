# Noroff assignment 2 - Data Persistence and Access

The basis for the project is to complete assignment 2 of Noroff Accelerate's Java Backend delevopment program. The assignment centers around the aspects of Data persistence and access. 

## Description
### SQL-scripts
The first part of the assignment involves the creation of several SQL scripts that executes simple queries towards a PostgreSQL Database. These scripts can be found in the "sql-scripts" folder and executing them towards a PostgreSQL Database instance called SuperheroesDb (If not renamed in script) will create a basic tables representing relationships between entities of superhero, assistant and powers and will run some basic manipulations and queries towards these. 

### Data Persistence and Access
The second part of the assignment mainly encompasses the persistence and access aspects. The project contains a Java application built with Maven that makes use of Spring, JDBC and PostgreSQL. A repository CustomerRepository is used as the main tool for gaining access to data contained in the Chinook database. This is modeled after the standard repository pattern and makes use of a generic CRUD interface. In order to persist the data after accessing it, several record classes are used to store it in logical formats after retrieval. Springboot is used to encapsulate and build the system and its components as well as manage seamless management of resources.

## Running
The project includes an ApplicationRunner which contains execution and logging of all uniqe methods of the CustomerRepository. This ApplicationRunner acts as the main form of testing of the core functionalities. Build the project with Maven and Spring and run the ApplicationRunner in order to see the output of all "tested" functionality.
