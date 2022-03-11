# Databases & SQL
## CSCE 156 - Spring 2022

### Introduction/Overview

* Flat data files are insufficient for storing data
  * Repetition of data
  * Formatting issues
  * Consistency issues
  * Integrity issues
  * Organization problems: not sorted, it is in one big file (no multithreaded or multiuser capability)
  * There is no security!

### Solution: use a RDBMS (Relational Database Management System)
  * 1970 Edgar Codd
  * Key aspects: data is stored in separate tables
  * Tables have columns (pieces of data) and rows (records)
  * Columns have a *type* and a *name* aka fields
  * Each row in a table corresponds to a single record
  * Records in different tables are *related* to each other through *foreign keys*
  * Each record in a table may/should be identified with *primary key*
  * Foreign keys define relations between tables:
    * One-to-many relation: one record in table $A$ may be related to many records in table $B$
    * Many-to-one relation: just the opposite
    * Many-to-many relations: multiple records in table $A$ are related to multiple records in table $B$
    * One-to-one: not too common

## CRUD Demonstration

* You can interact with a RDBMS through CRUD:
    * Create (`insert`)
    * Retrieve (`select`)
    * Update (`update`)
    * Destroy (`delete`)

### Demo Code

```
-- use your database, your data base is named
-- after your login
use cbourke;
-- shows you the current tables in your database
show tables;

-- Create data
-- insert a game record
-- games depend on publishers, so you need a publisher
-- record first
insert into game (gameId, name, publisherId) values (3213123, "Pong", 9);
insert into game (name, publisherId) values ("Breakout", 9);

-- to insert a game record requires a publisher record
-- first
insert into publisher (name) values ("Naughty Dog");
insert into game (name, publisherId) values ("Crash Bandicoot", 14);

-- you can use "nested queries" to avoid extra work...
insert into game (name, publisherId) values ("Frogger",
  (select publisherId from publisher where name = "Atari"));

-- insert multiple records with one query:
insert into game (name, publisherId) values
 ("Frogger 2", 9),
 ("Frogger's Revenge", 9);

-- Updating records...
-- you use the update keyword to update an existing record or records...
update game set name = "Fogger Two" where gameId = 3213127;
-- strict safe mode does not allow updates based on anything other
-- than an ID!
update game set name = "Frogger II" where name = "Frogger Two";

-- if you are not in safe mode, an update with a "where clause"
-- may end up changing *every* record in the table!

-- you can delete or remove records in a table using the delete from
-- query

-- without a where clause, you may end up deleting *every* record
-- in the table!  This would *not* delete the table, just its records
-- drop table game;
delete from game;

select * from game;
delete from game where gameId = 3213128;
delete from game where gameId = (select gameId from game where name = "Fogger Two");

-- Retrieving data from a table or tables can be done using
-- a select query
-- you can use a "wildcard" * to select *every* record and every column
select * from game;
select * from publisher;

-- you can specify a *subset* of columns to select out for each
-- query:
select name, publisherId from game;

-- you can also use "aliases"
select name as title,
       publisherId as pid from game;

-- you can use aggregate functions in your query...
-- how many games do we have?
select count(*) as numberOfGames from game;

select min(publishYear) from availability;
select max(publishYear) from availability;
select 2022 - max(publishYear) from availability;
select 2022 - min(publishYear) from availability;
select avg(publishYear) from availability;

-- fuzzy string matching: you can select values from a database
-- using a string comparison but also partial strings...
select * from game where name = "GTA";
select * from game where name like "G%";
select * from game where name like "g%";
select * from game where name like "%p%";
select * from game where name like "%uper%";
select * from game where name like "% %";
select * from game where name like "_a%";

-- You can use mathematical expressions using a where clause
select * from availability where publishYear >= 2000;
select * from availability where publishYear >= 2005 and publishYear < 2007;
select * from availability where publishYear >= 2005 or publishYear < 1990;

-- negations:
select * from game where name != "GTA";

-- Ordering results: you can reorder results using an ORDER BY claus
select * from game order by name;
select * from game order by name asc;
select * from game order by name desc;
-- combinations:
select * from game order by publisherId desc, name asc;

-- You can select distinct values using the distinct keyword
select count(*) from publisher;
select count(*) from game;
select count(distinct publisherId) from game;

select count(*) - (select count(distinct publisherId) from game)
  as numberOfNonPublishers from publisher;

-- In Clause
-- allows you to create sets of values
select * from availability where gameId in (select gameId from game where name like "G%");

-- example:
select * from game where gameId in (select gameId from availability where publishYear = 1989);

-- group by clause: you can project data down into lower dimensional data
-- and use aggregates to generate new information

-- example: how many games have each publisher published?
select * from game;

select publisherId, count(*) as numberOfGamesPublished from game group by publisherId;
select gameId, count(*) from availability group by gameId;
select platformId, count(*) from availability group by platformId;

```

## Data Projections

```sql

-- where clause filters results before a projection
-- having clause filters results after a projection
select publisherId,
       count(publisherId) as numberOfGames
from game
group by publisherId
having numberOfGames > 1;


```

## Join

$$A = \{a, b, c\}$$
$$B = \{1, 2\}$$
$$A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$$

* Goal: flatten the entire data model, make it pretty, reproduce the data from assignment 1

$$A \cup B$$

# Database Design

* Design a database to model films, actors, directors
* Semantics dictate design: you usually have one table per "entity"
* When creating tables: use consistent naming conventions!
  * Don't pluralize your tables
  * Generally: use `UpperCamelCasing` for table names
  * Use `lowerCamelCasing` for column names
  * Name primary keys after the table + `Id`
  * Name foreign keys *exactly* as the primary keys they reference!
* You can use `unique key` to create a unique constrain on a column or columns
* `foreign key FOO references TABLE(BAR)` can be used to define foreign keys


## Normalization

* 1-NF, 2-NF, 3-NF
* First Normal Form: "each attribute (column) in a table only has atomic values"
  * Every column in a table holds only one value
  * Violation: if a column has (say) multiple values using some delimiter `email1,email2,email3`
  * Violation: simply creating multiple columns for multiple fields (columns: `Email1` and `Email2` and `Email3`)
* Second normal form: it is both 1NF and no non-prime attribute is dependent on a proper subset of prime attributes
  * If you have a PK auto-generated, you get this for free!
  * Violation: a purchase record may contain a `customerId`, `storeId`, `storeLocation`
* Third Normal Form: it must be 2NF and "no non-prme column is transitively dependent on the key"
  * $a \rightarrow b \textrm{ and } b \rightarrow c \textrm{ then } a \rightarrow c$
  * Suppose you had a `pricePerUnit`, `numberOfUnits`;
  the `totalCost = pricePerUnit * numberOfUnits`
  * DO NOT *store* the `totalCost`
* Every non-key attribute must provide a fact about the key (1NF), the whole key (2NF) and nothing but the key (3NF) so help you Codd


```sql

use cbourke;

-- describe Director;

-- A database to model films, actors, and directors

drop table if exists FilmActor;
drop table if exists Film;
drop table if exists Director;
drop table if exists Actor;

-- one director can direct multiple films
-- a film only has one director
create table Director (
  directorId int primary key not null auto_increment,
  firstName varchar(255),
  lastName varchar(255)
);

create table Film (
  filmId int primary key not null auto_increment,
  title varchar(255) not null,
  releaseDate varchar(50),
  imdbRating double default 0.0,
  eidr varchar(255) not null unique key,
  directorId int not null,
  foreign key (directorId) references Director(directorId)
);

create table Actor (
  actorId int primary key not null auto_increment,
  firstName varchar(255),
  lastName varchar(255)
);

-- create a join table to model a many-to-many relation between
-- actor and film
create table FilmActor (
  filmActorId int primary key not null auto_increment,
  filmId int not null,
  actorId int not null,
  role varchar(255),
  foreign key (filmId) references Film(filmId),
  foreign key (actorId) references Actor(actorId)
);

insert into Director (directorId, firstName, lastName) values
  (1, "Denis", "Villeneuve"),
  (2, "Quentin", "Tarantino"),
  (3, "Tim", "Burton"),
  (4, "Michael", "Bay"),
  (5, "Matt", "Reeves");

insert into Film (filmId, title, eidr, directorId) values
  (10, "The Batman", "1234foo", 5),
  (11, "Batman", "1989abc", 3),
  (12, "Dune", "2021bar", 1),
  (13, "Transformers", "123999", 4),
  (14, "Bad Boys", "3333", 4);

insert into Actor (actorId,lastName,firstName) values
  (100, "Pattinson", "Robert"),
  (101, "Smith", "Will"),
  (102, "Smith", "Willow"),
  (103, "Nicholson", "Jack"),
  (104, "Keaton", "Michael");

insert into FilmActor (actorId,filmId,role) values
  (100, 10, "Batman"),
  (101, 14, "Some Cop"),
  (103, 11, "Jack Napier"),
  (104, 11, "Batman");


select * from Director d
  left join Film f on d.directorId = f.directorId
  left join FilmActor fa on fa.filmId = f.filmId
  left join Actor a on a.actorId = fa.actorId
union
select * from Director d
  right join Film f on d.directorId = f.directorId
  right join FilmActor fa on fa.filmId = f.filmId
  right join Actor a on a.actorId = fa.actorId;



```

## Misc

* There is a *lot* more about databases
  * Triggers, Views, Stored Procedures, variables, loops
  * Transactions: are how you interact with a database, they are an all-or-nothing thing: success = commit, failure = rollback
  * Transactions guarantee ACID principles
    * Atomicity
    * Consistency
    * Isolation
    * Durability
  * Security Issues: where to store passwords, how to grant access or restrict it, etc.
  * Soft vs hard deletes: a hard delete would remove a record using the `delete` query; a soft delete would make a record "inactive" using (say) an `isActive` boolean column

### OOP Model vs Relational Model

* Relational model only has data
* Object model has behavior and inheritance
* There are several inheritance strategies:
  * Table per class strategy: each class (intermediate, subclasses, and superclasses) all have one table each
  * Table per stub class: one table for each "terminal" class that you make (subclasses)
  * Single table inheritance strategy
    * one table for all sub types in a hierarchy
    * You may have some columns that are used in some records and ignored in others
    * Example: all assets have a code: so all asset records would specify this
    * Example: only stocks/options have a share price: so for stock instances it is present, for properties, it would be `null`
    * to distinguish types you use a "discriminator" column: a single string or character to denote the type

# JDBC - Java Database Connectivity API

* Goal: programmatically connect to and interact with our database
* Most languages have some support for *database connectivity*
* API = Application Programmer Interface
* Perfect illustration of *Dependency Inversion*
* Don't program toward a specific database, but a generic interface
* Vendors (Oracle, IBM, etc.) provide a *driver* library that conforms to the API
* JDBC provides:
  * `Connection`
  * `PreparedStatement`
  * `ResultSet`
* ORMs (Object-Relational Mappings) systems also exist (JPA, jOOQ)

### Demonstration

* Setup: download and include a Connector/J *driver* from oracle

#### Process:

1. Create a connection to your database: need user name, password, URL
2. Create/prepare your query
  - prepare the query
  - execute the query
3. Process your results
4. You clean up after yourself: close your resources

```text










```
