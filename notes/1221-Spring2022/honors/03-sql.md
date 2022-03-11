# Databases & SQL
## CSCE 156H - Spring 2022

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
  * Key aspects: data is stored in *tables*
  * Data is stored in columns (fields)
  * Records are stored as rows
  * Each row may have a unique identifier: *primary key*, (PK)
  * Rows (records) in different tables may be related through *foreign Keys* (FK)
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

### C = Creating Data

* You use the `insert` keyword on an existing table to insert new data

```sql
insert into publisher (publisherId, name) values (101, "Bethesda");
insert into publisher (name) values ("Foo Games");

-- insert multiple records with one query:
insert into platform (name) values
  ("Vive"),
  ("Index"),
  ("Oculus Rift");
select * from platform;
```

* YOu can, but should generally *not* hardcode primary key values
* Key management is difficult, let the database do its job
* You *can* and *should* hardcode key values for initialization/test data
* You can also use nested queries:

```sql
insert into publisher (name) values ("Mojang");
insert into game (name, publisherId) values ("Minecraft",
  (select publisherId from publisher where name = "Mojang"));
```

### U = Update

* You can alter an *existing* record in a table using the `update` keyword
* Safe mode will prevent *most* queries that end up changing multiple records, gneerally you use a `where` clause to limit the scope of your changes

```sql

update game set name = "Pong II" where gameId = 3213123;
```

### D = Destroy Existing records

* to remove (no undo generally) records, use the `delete` query
* You should use a `where` clause

```sql

update game set publisherId = 8 where gameId = 9;

delete from availability where gameId = 12;
delete from game where gameId = 12;
delete from publisher where publisherId = 10;
```

### R = Retrieve Records

* To retrieve records, you `select from` a table
* Retrieve every column of every game record:
`select * from game;`
* The start operator `*` is a *wildcard*
* You can select a subset of columns by specifying them
`select publisherId, name as title from game;`

* You can use basic logic operators using a `where` clause:
  * Numerical comparisons, `<, >, <=, >=, =, !=`
  * Strict string comparisons
```sql
select * from game where name = "GTA";
-- fuzzy string comparisons using the like clause:
-- anything that begins with a G:
select * from game where name like "G%";
select * from game where name like "%s";
select * from game where name like "%s%";
select * from game where name like "% %";
select * from game where name like "___a%";
```
* The `%` matches any characters (zero or more)
* THe `_` matches any single character (any)
* REgular expressions: may not be interoperable

### Aggregate Functions

```sql
select count(*) as numberOfGames from game;

select count(*) as numPublishers from publisher;
select count(distinct publisherId) as numPublishers from game;

select 16 - count(distinct publisherId) as numPublishers from game;

select min(publishYear) from availability;
select max(publishYear) from availability;
select avg(publishYear) from availability;
```

## Data Projections

* Goal: how many games did each publisher publish (assignment 1)
  * Limit to those with more than 1 game
  * Names?

## Joins

$$A = \{1, 2, 3\}$$
$$B = \{a, b\}$$
$$A \times B = \{(1, a), (1, b), (2, a), (2, b), (3, a), (3, b)\}$$

$$A \cup B$$

```sql
use cbourke;
-- 16 publishers, 24 games 384 = 16 * 24
select * from publisher;
select * from game;

select * from game g
  left join availability a
  on g.gameId = a.gameId
  left join platform p
  on p.platformId = a.platformId;

select publisherId, count(gameId) as numberOfGames from game group by publisherId;

-- join:
select *
  from publisher p
  join game g
  on p.publisherId = g.publisherId;

select *
  from publisher p
  left join game g
  on p.publisherId = g.publisherId;

select *
  from publisher p
  right join game g
  on p.publisherId = g.publisherId;

select *
  from game g
  right join publisher p
  on p.publisherId = g.publisherId;

-- join + data projection
-- where clauses filter results before a projection
-- having clauses filter after
select p.name as company,
       count(gameId) as numberOfGames
  from publisher p
  left join game g
  on p.publisherId = g.publisherId
  group by p.publisherId
  having numberOfGames < 3;

-- report: games and the number of
--  platforms they are available on
select g.name as title,
  count(a.platformId) as numberOfPlatforms
  from game g
  left join availability a
  on g.gameId = a.gameId
  group by g.gameId;

-- flatten the entire data model and
-- reproduce a CSV-like result (assignment 1)

select * from publisher join platform;

select p.name as company,
       g.name as title,
       a.publishYear as publishYear,
       pl.name as platform
  from publisher p
  left join game g
    on g.publisherId = p.publisherId
  left join availability a
    on g.gameId = a.gameId
  left join platform pl
    on a.platformId = pl.platformId
union
select p.name as company,
       g.name as title,
       a.publishYear as publishYear,
       pl.name as platform
  from publisher p
  right join game g
    on g.publisherId = p.publisherId
  right join availability a
    on g.gameId = a.gameId
  right join platform pl
    on a.platformId = pl.platformId;


```

# Database Design

* Design a database to model data on films, actors, and directors
* Semantics dictate design: usually you have one table per "entity"
* Style tips:
  * Don't pluralize table names
  * Be consistent on your naming conventions
  * Suggestion table names are `UpperCamelCase`, column names `lowerCamelCase`
* Design tips:
  * Always have a primary key field (suggestion: make it an integer, not null, `auto_increment`) and name it after the `tableName + Id`
  * Foreign keys should have the same name and type of the key they reference (usually)
  * Join tables can be defined with multiple foreign keys to model a many-to-many relation
  * Be sure to insert plenty of test data to test your database!  You can auto-generate mock data and you can easily convert existing (CSV) data to SQL insert statements using a tool!
  * Check and uniqueness constraints can be used to enforce *data integrity*

```sql


-- Database to model films, actors, and directors
--   An actor can appear in multiple films
--   A film may have multiple actors
--   A director can direct multiple films, but
--     a film has one director

drop table if exists FilmActor;
drop table if exists Film;
drop table if exists Director;
drop table if exists Actor;

create table if not exists Director (
  directorId int not null primary key auto_increment,
  firstName varchar(255),  
  lastName varchar(255) not null
);

create table if not exists Film (
  -- surrogate key
  filmId int not null primary key auto_increment,
  title varchar(255) not null,
  releaseDate varchar(100), -- 2022-03-01
  imdbRating double default 0.0,
  -- "natural" key: external unique ID for each record
  eidr varchar(100) not null unique key,
  directorId int not null,
  foreign key (directorId) references Director(directorId)
  -- constraint `foo` check (imdbRating >= 0.0 and imdbRating <= 10)
);

create table if not exists Actor (
  actorId int not null primary key auto_increment,
  firstName varchar(255),  
  lastName varchar(255) not null
);

create table if not exists FilmActor (
  filmActorId int not null primary key auto_increment,
  actorId int not null,
  filmId int not null,
  role varchar(100),
  foreign key (actorId) references Actor(actorId),
  foreign key (filmId) references Film(filmId),
  -- constraint will ensure that the triple combination
  --  of (actorId,filmId,role) are unique
  constraint `uniqueRoles` unique (actorId,filmId,role)
);

insert into Director (directorId,lastName,firstName) values
  (1, "Reeves", "Matt"),
  (2, "Nolan", "Christopher"),
  (3, "Lucas", "George"),
  (4, "Spielberg", "Steven"),
  (5, "Lynch", "David");

insert into Film (filmId,title,eidr,directorId) values
  (10, "The Batman", "batman2022", 1),
  (11, "Cloverfield", "cl2010", 1),
  (12, "Inception", "tenetFan2000", 2),
  (13, "Star Wars", "foostarWars", 3),
  (14, "Eraserhead", "weird01", 5);

insert into Actor (actorId,lastName,firstName) values
  (100, "Pattinson", "Robert"),
  (101, "Dano", "Paul"),
  (102, "Fischer", "Carrie"),
  (103, "DiCaprio", "Leonardo"),
  (104, "Ford", "Harrison");

insert into FilmActor (actorId,filmId,role) values
 (100, 10, "Batman"),
 (100, 10, "Bruce Wayne"),
 (101, 10, "Riddler"),
 (102, 13, "Leia Organa"),
 (103, 12, "Inception Man");

select * from Director d
  left join Film f on f.directorId = d.directorId
  left join FilmActor fa on fa.filmId = f.filmId
  left join Actor a on a.actorId = fa.actorId
union
select * from Director d
  right join Film f on f.directorId = d.directorId
  right join FilmActor fa on fa.filmId = f.filmId
  right join Actor a on a.actorId = fa.actorId;

```

## Normalization

* 1-NF, 2-NF, 3-NF
* First Normal Form: "each attribute (column) in a table has only atomic values"
  * Every column must hold ONE value
  * Violation: a comma delimited list of emails: `email1,email2,email3`
  * Another violation: if you simply add another column, `Email1` and `Email2`, and `Email3`  
* Second normal form: it has to be 1-NF: no non-prime attribute is dependent on a proper subset of prime attributes
  * Auto generated primary keys give you this for free
  * Violation: a purchase record may contain a `customerId`, `storeId`, `storeLocation`
* Third Normal form: 2-NF (by transitivity, 1-NF): no non-prime column is transitively dependent on the key
  * Suppose you had a `pricePerUnit`, `numberOfUnits`; `totalCost = pricePerUnit * numberOfUnits`
  * Storing `totalCost` is a violation of 3-NF
* Every non-key attribute must provide a fact about the key (1NF), the whole key (2NF) and nothing but the key (3NF) so help you Codd

## Misc

* There is *alot* more to learn about databases
  * Triggers, Views, Stored Procedures, variables, loop, etc.
  * Transactions: these are an all-or-nothing interaction with the database
  * ACID
    * Atomicity
    * Consistency
    * Isolation
    * Durability
  * Security issues: do not store sensitive info in a database unhashed/unencrypted
  * Soft vs Hard deletes: a hard delete is a result of a `delete` statement.  A soft delete involves defining a boolean column `isActive` that is true if the record is active, false if it is "deleted"

* OOP Model vs Relational Model
  * classes are roughly equal to tables
  * OOP has behavior (methods), relational model does not
  * OOP has inheritance and polymorphism, relational model does not
  * How can we "resolve" the differences in these two models?
* Several strategies:
  * Table per class/subclass strategy: each class has its own table
  * Table per stub class: every terminal class gets its own table (with potentially repeated columns)
  * Single table inheritance strategy: one table to represent all subtypes
    * Some columns will be relevant to some records, others will not be (`null`)
    * To tell the difference between the types, you use a "discriminator column"

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
