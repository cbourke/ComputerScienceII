# Databases & SQL
## ECEN 194 - Spring 2023

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
* Each row corresponds to a single record in a single table
* Records in a table are *uniquely identified* by a *primary key* (PK)
* Records between tables are *related* through *foreign keys* (FKs)
* FKs define one of several types of relationships:
  *  One-to-many relation: one record in table $A$ may be related to many records in table $B$
  * Many-to-one: same but from the perspective of $B$
  * Many-to-many relations: multiple records in table $A$ are related to multiple records in table $B$
  * One-to-one: not too common; rethink what you're doing

## CRUD Demonstration

* You can interact with a RDBMS through CRUD:
    * Create (`insert`)
    * Retrieve (`select`)
    * Update (`update`)
    * Destroy (`delete`)

## Preview: Retrieval

* Basic `select` statements:

```sql

-- select all games from the game table:
-- the "star" is a wildcard: select ALL columns from that table
select * from game;
-- if you only want certain columns, you specify:
select name from game;
-- if you want multiple columns:
select name, publisherId from game;

-- select everything from the publisher table:
select * from publisher;
select * from platform;
```

### C = Creating Data

* You can insert *new* data into an *existing* table using the `insert` keyword
* Basic syntax: `insert into TABLE_NAME (Columns,clomuns) values (values here);`

```sql

select * from game;
select * from publisher;

-- insert a new game record:
-- FIRST: you need a publisher (WB Games)
insert into publisher (name) values ("WB Games");
insert into game (name,publisherId) values ("Hogwarts Legacy", 14);

insert into publisher (publisherId,name) values
  (100, "Ubisoft");
insert into game (gameId, name,publisherId) values
  (201, "Assissin's Creed", 100);

-- fix it by removing/deleting the extra record...
-- safe mode prevents this:
delete from game;
delete from game where gameId = 201;

-- updating data
update game set name = "Assassin's Creed" where gameId = 200;

-- misc
-- you can insert multiple records at once:
insert into publisher (name) values
  ("EAGames"),
  ("Westwood Studios"),
  ("Kingsile");

```

## Retrieving Data

* You can pull data out of a database using the `select` statement
* To pull every single record and every single column value you can use a wildcard: `*`

```sql

-- retrieves every game record
-- * is the wild card and selects every column in the *result set*
select * from game;
select * from publisher;

-- you can be more selective with your columns
select gameId, name from game;

-- you can use an "alias" to rename a column:
select name as gameTitle from game;
```

### Using Aggregates to count or sum data

```sql


select count(*) as numberOfTitles from game;
select count(*) from publisher;

-- you can be even more selective on your actual results
-- using a where clause
select * from game where name = "GTA";

-- select all of the GTA games using a *partial string* match:
-- The string wildcard % matches any number of any character
select * from game where name like "G%";

-- you can use any number of wildcards
select * from game where name like "% %";

-- you can match single characters using the underscore: _
select * from game where name like "_a%";

-- more aggregates: min, max, avg, sum
select min(publishYear) from availability;
select max(publishYear) from availability;
select avg(publishYear) from availability;
select sum(publishYear) from availability;

```

## Other Clauses

```sql

-- in general, results from a database are unordered
-- you can impose an ordering using the ORDER BY clause
select * from game order by name;

-- by default it is ascending
select * from game order by name asc;

-- descending
select * from game order by name desc;

-- you can do a combination of two or more columns:
select * from game order by publisherId, name desc;

-- the distinct clause allows you to select unique items:
select distinct publisherId from game;

-- you can use the in clause like set notation...
--
select * from game where publisherId in (1, 3, 5);

-- or using basic logic:
select * from game where publisherId = 1 and (publisherId = 3 or publisherId = 5);
select 234 * 4 - 3;

-- subqueries:
select * from game where publisherId
  in (select publisherId from publisher where name = "LucasArts");

```

## Data Projections

```sql

-- how many games has each publisher published in our database?
select publisherId, count(*) as numberOfGamesPublished from game group by publisherId;

select * from publisher;

-- joining two tables together...
-- naively using a cross join:
select count(*) from game;
select count(*) from publisher;
select 22 * 17;

-- properly using an on clause for the foreign keys:
select * from game
  join publisher
  on game.publisherId = publisher.publisherId;

-- table aliasing, omitting the "as" keyword
select * from game g
  join publisher p
  on g.publisherId = p.publisherId;

-- limit the columns:
select p.name as publisherName, g.name as gameTitle from game g
  join publisher p
  on g.publisherId = p.publisherId;

--  
select p.name as publisherName, count(*) as numberOfGamesPublished from game g
  join publisher p
  on g.publisherId = p.publisherId
  group by p.publisherId;


select p.name as publisherName, count(*) as numberOfGamesPublished from publisher p
  join game g
  on p.publisherId = g.publisherId
  group by p.publisherId;

-- you need a left join to preserve records:
select * from publisher p
  left join game g
  on p.publisherId = g.publisherId
  group by p.publisherId;

select p.name as publisherName, count(g.gameId) as numberOfGamesPublished from publisher p
  left join game g
  on p.publisherId = g.publisherId
  group by p.publisherId;

-- where clauses are executed before projection
-- having clauses are exectued after hte projection
select p.name as publisherName, count(g.gameId) as numberOfGamesPublished from publisher p
  left join game g
  on p.publisherId = g.publisherId
  -- where p.name = 'LucasArts'
  group by p.publisherId
  having numberOfGamesPublished < 3;



```

# Creating/Designing Databases

* Design a database to support our little financial asset application: support owner (person), their emails (any number), accounts: annuity, savings
* Variation: we now want to support *joint* accounts: accounts with more than one owner
* How should we handle inheritance?  

```sql

-- by default all column values are "nullable": they can
-- have a null value (missing value)

drop table if exists Email;
drop table if exists Ownership;
drop table if exists Person;
drop table if exists Account;

create table if not exists Person (
  personId int not null primary key auto_increment,
  firstName varchar(255),
  lastName varchar(255) not null,
  dateOfBirth varchar(10) not null default "0000-00-00"
);

insert into Person (personId,firstName,lastName,dateOfBirth)
  values
  (10, "Chris", "Bourke", "2020-01-01"),
  (20, "Joe", "Schmoe", "1970-01-03"),
  (30, "Jane", "Doe", "0000-00-00");

create table if not exists Email (
  emailId int not null primary key auto_increment,
  address varchar(255) not null,
  personId int not null,
  foreign key (personId) references Person(personId)
);

insert into Email (address,personId) values
  ("chris.bourke@unl.edu", 10),
  ("cbourke@cse.unl.edu", 10),
  ("jdoe@gmail.com", 30);

select * from Person;
select * from Person p
  left join Email e on p.personId = e.personId;


create table if not exists Account (
  accountId int primary key not null auto_increment,
  accountNumber varchar(255) not null unique key,
  -- "Savings" or "Annuity"
  type varchar(255) not null,
  termYears int,
  monthlyPayment double,
  apr double,
  balance double,
  constraint `validApr` check (apr <= 1 and apr >=0)
);



create table if not exists Ownership (
  ownershipId int primary key not null auto_increment,
  personId int not null,
  accountId int not null,
  foreign key (personId) references Person(personId),
  foreign key (accountId) references Account(accountId),
  constraint `uniquePair` unique (personId,accountId)
);


```

## Summary

### Database Design Observations

* Semantics dictate design: usually you have one table per "entity"
* Style tips:
  * Be consistent with your naming conventions
  * Suggestion:
    * Tables should be `UpperCamelCase`
    * Columns should be `lowerCamelCase`
    * Avoid pluralizations, avoid abbreviations
  * Make sure every table has a:
    * Primary key: `primary key not null auto_increment`
    * Name it: `tableName` + `Id`: `personId`, `emailId`
  * Foreign keys should have the *same name* as the primary keys that they reference
  * Strings (`varchar`s) should not be used for PK or FK (casing issues, encoding issues, efficiency)
  * Join tables should be used to model a many-to-many relationship
  * Be sure to insert plenty of test data into your database
  * Check and uniqueness constraints can be used to enforce *data integrity*

## Normalization

* 1-NF, 2-NF, 3-NF
* They build on each other: you cannot have a higher normal form without having ALL lower normal forms
* First Normal Form: "each attribute in a table only has atomic values"
  * Each column in a table represents ONE piece of data or ONE value
  * Violation: if you stored multiple emails in one column delimited by a comma
  * Violation: if you stored a fixed number of values: `email1, email2, email3`
  * Best practice: separate out into another table and define a one-to-many relationship
* Second Normal Form: it has to be 1-NF: no non-prime attribute is dependent on a proper subset of prime attributes
  * Having a PK auto-incremented gives you 2NF automatically
  * Violation: a purchase record that contains `customerId, storeId, storeAddress`
  * IF you defined a PK as a combination of `customerId/storeId` then you violate 2NF: `storeAddress` only depends on the second half of the key
  * It is often useful and necessary to define combination keys, but *keep them secondary*!
  * You split everything out into its own table
* Third Normal Form: has to be 2-NF (and transitively 1-NF)
  * No non-prime column is transitively dependent on the key
  * Violation: store the `termYears, monthlyPayment` AND `totalValue` (`= termYears * monthlyPayment * 12`)
  * Gives rise to possible data anomalies: if you change one value, it means the transitively dependent value is now *wrong*
  * Storing data that is dependent on other data is *wrong*
* Every non-key attribute must provide a fact about the key (1NF), the whole key (2NF) and nothing but the key (3NF) so help you Codd

## Misc

* There is a LOT more
  * Triggers: event-based actions in a database
  * Views: read-only "tables" in a database
  * Temp tables: temporary tables that can be created within a transaction to make data processing easier
  * Stored Procedures: functions you can define with reusable SQL code that you can treat like a function
  * Loops, variables (cursors), etc.
  * Transactions: an all-or-nothing atomic "action" that can include more than 1 query
  * Soft vs Hard deletes: a hard delete is a result of a `delete` statement.  A soft delete involves defining a boolean column `isActive` that is true if the record is active, false if it is "deleted"
* OOP Model vs Relational Model
    * OOP model allows *inheritance* and behavior (methods)
    * RDBMs do not have inheritance nor behavior
    * You need a way to resolve the difference in these two models
    * You can create a table per class/subclass
    * Single table inheritance strategy: one table represents all subtypes and you use a *discriminator* column to tell difference
    * you use a string to indicate the subtype of the class.  
    * Some columns may be relevant to some subtypes, others may irrelevant; simply allow them to be null to model this

## Programmatically Connecting to a Database & Processing Data

* In Java we'll use JDBC = Java Database Connectivity API (Application Programmer Interface)
* Getting Started:
  * Download and "install" the Connector/J jar file for MySQL

```text










```
