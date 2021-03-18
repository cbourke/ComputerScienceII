# Database Connectivity
## CSCE 156 - Spring 2021

### Overview

* Goal: programmatically connect to a database and process or persist (save) data
* Most languages have some support for *database connectivity*
* Java: JDBC = Java DataBase Connectivity API
* API = APplication Programmer Interface
* Perfect illustration of *Dependency Inversion* 
* Don't program toward a specific database, but a generic interface
* Vendors (Oracle, IBM, etc.) provide a *driver* library that conforms to the API
* JDBC provides:
  * `Connection`
  * `PreparedStatement`
  * `ResultSet`
* ORMs (Object-Relational Mappings) systems also exist (JPA, jOOQ)

### Demonstration

* Setup: you download the driver (Connectror/J) from Oracle
* Connect to the films database and:
  * Process Film/Director data
  * Persist Film/Director data

### Process:

0. Load the JDBC Driver (archaic)
1. Make a connection to your database: url, user name, password
2. Create/prepare your query
  a. Prepare the query
  b. Execute your query
  c. Process your results
3. Clean up: close your resources!

## Best Practices

### Dealing with `SQLExceptions`

* Unfortunately most operations throw a checked `SQLException` that *has* to be caught and dealt with
* Just catch and release: rethrow the exception as a `RuntimeExcpeion`
* You *may* even want to log the error *then* rethrow it
* If you do do logging, then use a proper library; `System.out` or `System.err` are not proper logging libraries!

### Always Use `PreparedStatements`

* In general, strings can contain anything including SQL code!
* Without sanitizing your code, you are susceptible to an *SQL Injection* attack: a user *may* be able to execute arbitrary SQL code on your database!
* `PreparedStatement`s in Java *sanitize* the inputs for you, ensuring that no SQL injection is possible
* Never use anything else!
* Its just simpler to use on method to connect to a database 


### Avoid using the star operator

* Using the SQL star operator brings over all data even if you don't use it
* You end up wasting bandwidth and making the connection way slower!
* It also protects you against changes in the database

```text






```