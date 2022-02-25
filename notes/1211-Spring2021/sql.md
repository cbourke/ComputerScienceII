# Databases & SQL
## CSCE 156 - Spring 2021

### Introduction/Overview

* Video Game CSV file

* Flat data files are insufficient for storing data
  * Repetition of data
  * Formatting issues
  * Consistency issues
  * Integrity issues
  * Organization problems: not sorted, it is in one big file (no multithreaded or multiuser capability)
  * There is no security!
* Solution: use a RDBMS (Relational Database Management System)
  * 1970 Edgar Codd
  * Key aspects: data is stored in *tables*
  * Tables have columns and rows
  * Columns define fields or pieces of data for each record
  * Rows correspond to a single record
  * A database typically has *multiple tables* that are *related*
  * Each row may have a unique identifier: *Primary Key* or PK for short
  * Rows in different tables may be related to each other throuogh *foreign keys* FKs for short
    * May define a *many-to-one* or *one-to-many* relation
    * Or FKs may define a *many-to-many* relation

## CRUD Demonstration

* You can interact with a RDBMS through CRUD:
  * Create (`insert`)
  * Retrieve (`select`)
  * Update (`update`)
  * Destroy (`delete`)

### C = Creating Data

* Creating data involves inserting new records into an existing table
* You use the `insert` keyword

```sql
-- insert a publisher record using the hard-coded primary
-- key value 14:
insert into publisher (publisherId, name)
  values (14, "EA Sports");

-- Let the database determine the next available key value:
insert into publisher (name)
  values ("EA Sports");


-- insert a game record, refering the inserted EA Sports record
insert into game (name, publisherId) values ("Maddon 21", 14);

-- insert a game record and refer to EA Sports using a *nested query*:
insert into game (name, publisherId) values ("Maddon 22",
  (select publisherId from publisher where name = "EA Sports"));


  -- insert multiple records with one query:
  insert into game (name,publisherId) values
    ("Madden 21", 14),
    ("Madden 22", 14);

```

## Update Data

```sql
-- this update would change EVERY game record
-- not possible in "safe mode"
update game set name = "Madden 22";

-- update the particular game with the given gameId
update game set name = "Madden 22" where gameId = 22;

```

## Destroying Data

```sql

-- this would delete every single game record
-- it would NOT delete the table itself
-- not possible in safe mode!  Thankfully!
delete from game;

-- delete a single game by providing a gameId:
delete from game where gameId = 22;
delete from game where gameId = 21;
```

### Observations

* Comments are usually denoted with a `--` (two hyphens)
* Be consistent on table name and column name conventions; suggestion:
  * `TableName`s are upper camel cased (NOT plural)
  * `columnName`s are lower camel cased
* In old-school SQL, keywords are `ALLCAPS`, you can use this convention, but why?
  * SQL keywords are case *insensitive*
  * THe old-school way assumed a monochrome monitor
* Whitespace generally does not matter, you can go to the next line and break up long lines to make it more readable
* If you do, align things using proper indentation
* You can use either double quotes or single quotes to denote strings, but be consistent
* Every query ends with a semicolon `;`

## Retrieving Data

* You can pull data out of a database using the `select` statement
* To pull every single record and every single column value you can use a wildcard:

```sql
select * from publisher;
select * from game;

-- you can specify only a subset of columns to retrieve:
select name from publisher;
select name, publisherId from game;

```

## More on Select

```sql


-- you can use an aggregate count function to determine
-- the number of records in a query
select count(*) from game;

-- a where clause can be used to specify a subset of records
select * from game where name = "GTA 2";
select * from game where gameId = 5;
select * from game where publisherId = 5;
select count(*) from game where publisherId = 5;
select * from publisher;
select * from game where publisherId = 3;
-- you can alias any column or table name using the
-- as keyword
select count(*) as numberOfGames from game where publisherId = 3;

-- you can use other basic math and aggregate functions:
select max(publishYear) from availability;
select min(publishYear) from availability;
select avg(publishYear) from availability;
-- basic math is also supported:
select (max(publishYear) + 10) *2 from availability;

-- you can also query based on partial string matches
-- using a combination of wildcards, % and the like clause:
select * from game where name like "GTA%";
-- select any game title that has an 'a' in it:
select * from game where name like "%a%";
-- it is case sensitive:
select * from game where name like "%A%";
-- you can also use the single character wildcard: _
select * from game where name like "_a%";

-- you can combine queries using basic logic operators:
-- or, and, not
select * from game where name = "GTA 2" or gameId > 10;
select * from game where name like "GTA%" and gameId < 3;
-- you can select any game whose publisherId is null:
select * from game where publisherId is null;
select * from game where publisherId is not null;
-- for non-null queries you can use the !=:
select * from game where name != "GTA 2";
-- you can use !=, =, <, >, <=, >=
```

## ORDER BY clause

```sql

-- alphabetic order by name, a-z:
select * from game order by name;
-- reversed alphabetic order (asc = ascending, default)
-- desc = descending
select * from game order by name desc;
select * from game order by publisherId asc, name desc;
```

## Distinct

```sql
select count(*) as numPublishers from publisher;
select count(*) as numGames from game;
-- you can select distinct elements from a query:
select distinct publisherId from game;
select count(distinct publisherId) as numPubs from game;
```

## In clause

```sql
select * from game where gameId = 1 or gameId = 5 or gameId = 9;
-- the in clause allows you to define a set:
select * from game where gameId in (1, 5, 9);
-- you can use it in conjunction with a nested query:
select * from game where gameId in (
  select gameId from game where name like "G%"
);

```

## data projections

```sql

-- projections:
-- goal: find out how many games each publisher published
select * from game;
-- project the data down to 2 dimensions: (publisherId, number of games they published)
select publisherId, count(*) from game group by publisherId;
-- careful what you group by, it may not make sense:
select name, count(*) from game group by name;

-- after your projection you may want to limit your query further
-- using the having clause allows you to filter your records further:
select publisherId, count(*) as numGames from game
  group by publisherId
  having numGames >= 3;
-- the where clause is executed BEFORE the projection/group by clause
-- the having is executed AFTER the projection
```

## Joins

```sql

-- you can join one or more tables together using a
-- join statement

-- a cross join is a full cartesian product of records:
select * from publisher cross join game;

-- usually you want to make a join that makes sense:
-- usually you join records that are actually *related*
select * from publisher p join game g
  on p.publisherId = g.publisherId;

-- use in conjunction with column selection to limit your query results:
select
  p.name as publisherName,
  g.name gameName
from publisher p join game g
on p.publisherId = g.publisherId;

-- a regular join (above) will only match on records that exist
-- publisher to the game table will only match publishers that actually
-- have published a game
-- we want ALL publishers, even if they have not published a game
-- you will want to use a "left join"
select
  p.name as publisherName,
  g.name gameName
from publisher p left join game g
on p.publisherId = g.publisherId;

select
  p.name as publisherName,
  g.name gameName
from publisher p left join game g
on p.publisherId = g.publisherId
where g.name is null;
-- you can also use right joins that join the other way
select
  p.name as publisherName,
  g.name gameName
from game g right join publisher p
on p.publisherId = g.publisherId;

-- you can also use right joins in the same order:
select
  p.name as publisherName,
  g.name gameName
from publisher p right join game g
on p.publisherId = g.publisherId;

-- reproduce the flat file from assignment 1:
select
  p.name as publisherName,
  g.name as title,
  a.publishYear as year,
  platform.name as platform
  from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform on a.platformId = platform.platformId;


select
  p.name as publisherName,
  g.name as title,
  a.publishYear as year,
  platform.name as platform
  from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform on a.platformId = platform.platformId;  

  -- you can union two results together:
  select
  p.name as publisherName,
  g.name as title,
  a.publishYear as year,
  platform.name as platform
  from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform on a.platformId = platform.platformId
union
select
  p.name as publisherName,
  g.name as title,
  a.publishYear as year,
  platform.name as platform
  from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform on a.platformId = platform.platformId;  
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
use cbourke;

drop table if exists FilmActor;
drop table if exists Actor;
drop table if exists Film;
drop table if exists Director;

-- TODO: add proper documentation
create table if not exists Director (
  directorId int primary key not null auto_increment,
  firstName varchar(255),
  lastName varchar(255) not null
);

-- This table models a film
create table if not exists Film  (
  filmId int primary key not null auto_increment,
  title varchar(255) not null,
  releaseDate varchar(10) not null default "0000-00-00",
  imdbRating double,
  directorId int not null,
  eidr varchar(100) not null unique key,
  foreign key (directorId) references Director(directorId),
  constraint `zeroToTenConstraint` check (imdbRating >= 0 and imdbRating <= 10)
);

insert into Director (directorId, lastName, firstName) values
  (1, "Russo", "Anthony");
insert into Director (directorId, lastName, firstName) values
  (2, "Favreau", "Jon");

select * from Director;  

insert into Film (filmId, title, releaseDate, imdbRating, directorId, eidr) values
  (42, "End Game", "2019-07-01", -9.5, 1, "abc"),
  (43, "Swingers", "1992-01-01", 4.5, 2, "xyz"),
  (44, "Iron Man", "2005-01-01", 7.5, 2, "ijk");

select * from Film;

create table if not exists Actor (
  actorId int primary key not null auto_increment,
  firstName varchar(255),
  lastName varchar(255) not null
);

-- This join table models a many-to-many relation
-- between the Actor table and the Film table:
create table if not exists FilmActor (
  filmActorId int primary key not null auto_increment,
  actorId int not null,
  filmId int not null,
  foreign key (actorId) references Actor(actorId),
  foreign key (filmId) references Film(filmId),
  -- this constraint limits redundant records:
  constraint `uniqueActorFilmCombo` unique (actorId,filmId)
);

insert into Actor (actorId, firstName, lastName) values
  (101, "Robert", "Downey"),
  (102, "Paul", "Rudd"),
  (103, "Jon", "Favreau");

select * from Actor;

insert into FilmActor(actorId, filmId) values
  (101, 42),
  (102, 42),
  (103, 42),
  (103, 43),
  (101, 44);

select * from Film f
  join FilmActor fa on f.filmId = fa.filmId
  join Actor a on fa.actorId = a.actorId;
```

## Normalization
* 1-NF, 2-NF, 3-NF
* First Normal Form: "each attribute in a table has only atomic values"
  * Every column in a table holds only one value
  * Violation of 1-NF: storing comma-delimited values in a single column
  * Violation: store a fixed number of values (Email1, Email2, Email3)
  * Simply define another table to model any one-to-many relation
* Second normal form: it has to be 1-NF: no non-prime attribute is dependent on a proper subset of prime attributes
  * Using a PK auto-generated, non-null key gets you 2-NF for free
  * Violation: a purchase record may contain a `customerId`, `storeId`, `storeLocation`
  * If the key is the *combination* of `customerId/storeId` then it violates 2nd form: `storeLocation` is only dependent on one of those things
  * It is often useful and necessary to define combination keys, but *keep them secondary*!
  * You split everything out into its own table
* Third Normal form: 2-NF (by transitivity, 1-NF): no non-prime column is transitively dependent on the key
  * Suppose you had a `pricePerUnit`, `numberOfUnits`; `totalCost = pricePerUnit * numberOfUnits`
  * If you *stored* `totalCost` as a column, it is a violation of 3-NF
  * `totalCost` is *transitively* dependent on 2 other column values, so we *don't store it*, we recompute it as needed
* Every non-key attribute must provide a fact about the key (1NF), the whole key (2NF) and nothing but the key (3NF) so help you Codd

## Misc

* There is *alot* more to learn about databases
  * Triggers, Views, Stored Procedures, variables
  * Transactions: an all or nothing series of queries
  * Security issues: do not store sensitive info in a database unhashed/unencrypted
  * Soft vs Hard deletes: a hard delete is a result of a `delete` statement.  A soft delete involves defining a boolean column `isActive` that is true if the record is active, false if it is "deleted"
* OOP Model vs Relational Model
  * OOP model allows *inheritance*
  * RDBMs do not have inheritance
  * You need a way to resolve the difference in these two models
  * You can create a table per class/subclass
  * You can create a table per stub class: only one table per subclass that has no subclasses
  * You can use a "single table inheritance strategy"
  * You can create one table for all subtypes and use a *discriminator column*
  * you use a string to indicate the subtype of the class.  
  * Some columns may be relevant to some subtypes, others may irrelevant; simply allow them to be null to model this

```text









```
