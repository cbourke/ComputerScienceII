# Databases & SQL
## ECEN 156 - Spring 2024

### Introduction/Overview

* Flat data files are insufficient for storing data
  * Bad Formatting
  * Inconsistency
  * No types: int, double, strings, we ONLY have strings in a file!
  * Redundancy of data leads to potential failure points and data anomalies
  * Any processing requires processing the ENTIRE file: extremely inefficient: not sorted, not organized, etc. it is one on giant "BLOB" = "Binary Large OBject"
  * There are file lock issues: no concurrency
  * There is no security: one file is an all-or-nothing thing
  * No integrity of data: you can do anything to any record to any column (no protection of data)

### Solution: use a RDBMS (Relational Database Management System)

* MySQL (MariaDB), MSSQL, Oracle, PostgreSQL, etc.
* 1970 Edgar Codd (IBM)
* Key aspects: data is stored in separate tables
* Tables have columns (pieces of data with a particular TYPE: int, double, varchar) and rows (individual records)
* Each column has a *type* and a *name*
* Each row corresponds to a single record
* Each record in a table is *uniquely* identified a *primary key* (PK) that are generally automatically created by the database
* Records in different tables are *related* to each other through *foreign keys* (FKs)
* Foreign keys define relations between tables:
  * One-to-many relationship: one record in table $A$ may be related to many records in table $B$
  * Many-to-one relationship: same thing but from the perspective of table $B$
  * Many-to-many relationship: one record in $A$ can reference many in $B$ AND vice versa
  * One-to-one relationship: one record in $A$ relates to one record in $B$ (maybe ignore this one)

## Getting Started

* We suggest using mysql workbench: https://www.mysql.com/products/workbench/
* You all have access to a database named after your canvas login (`cbourke3`) on a server named `cse-linux-01.unl.edu`
* Your password should have been sent to you via your huskers email this morning
* DO NOT use this password for anything else, do NOT change your mysql password to something you care about or use for other things
* If you lose it and/or need to change it: you'll need to contact `manager@cse.unl.edu`

## Basic CRUD

* You can interact with a RDBMS through CRUD:
    * Create (`insert`) - inserts new records into an existing table
    * Retrieve (`select`) - retrieve data from tables, producing reports
    * Update (`update`) - modify data that already exists in a database
    * Destroy (`delete`) - removing data from a table: NOT UNDO!

## Inserting data

* You use the keyword `insert` and specify the table (name), the column names and value(s) that you want to enter into that table

```sql

-- insert a new publisher into the publisher table...
insert into publisher (name) values ("Disney");

-- alternatively: you *can* specify a primary key PK value:
-- but generally you don't want to
-- EXCEPT: when you are inserting test data
insert into publisher (publisherId,name) values (600, "Disney");

-- insert a game record into the game table:

insert into game (name,publisherId)
  values ("Club Penguin", 600);

-- you can insert multiple records at once:

insert into publisher (name) values
  ("PUB001"),
  ("NBA2K");
```

## Updating Data

```sql

insert into publisher (name) values ("Ruckstar");

-- unsafe: woudl change ALL records
-- safe mode prevents this!
update publisher set name = "Rockstar";

-- safe: you are only updating a single record
update publisher set name = "Rockstar" where publisherId = 603;

update game set name = "GTA 6",
  publisherId = 603 where gameId = 3;

```

## Deleting Data

```sql

-- unsafe:
delete from game;

-- try to delete Club Penguin:
delete from game where gameId = 25;

-- if you try to delete a publisher FIRST:
-- if you really wante dto do this, you'd have
-- to delete all games of the publisher FIRST
delete from publisher where publisherId = 1;
```

## Selecting Data

```sql

-- selecting data
-- generally you use the select keyword
-- you CAN (when testing and troubleshooting) use
-- the "wildcard": * to specify ALL columns
select * from game;

-- alternatively: you can specify a subset of columns
select name from game;

-- you can also alias columns:
select name as gameTitle from game;

-- multiple columns: use a comma delimited list
select
  name as gameTitle,
  publisherId as pubId,
  gameId as `Game Identification`
  from game;
```

### Aggregate Functions

* You can use "functions" to compute or retrieve certain values
* `count(*)`

```sql

-- determine the number of game records:
select count(*) as numberOfGames from game;

-- min, max, avg, sum
select min(publishYear) as oldestGameYear from availability;
select max(publishYear) as newestGameYear from availability;
select sum(publishYear) as huh from availability;
select avg(publishYear) as huh from availability;

```

## More

```sql

-- more on the where clause..

select * from game where gameId = 5;

-- you can use the "and" and "or" keywords to
-- create compound logic:
select * from game where gameId = 5 or gameId = 6 and gameId > 20;

-- do basic string matching:
select * from game where name = "Legend of Zelda";

-- you can do partial string matching:
-- select any game that begins with a G
-- % is a string wildcard: it matche ANY character
-- zero or more
select * from game where name like "G%";

-- anything that ends with an s:
select * from game where name like "%s";

-- you can use as many wildcards as you want:
select * from game where name like "%Z%";

-- you can combine functions, where clauses, etc.
select count(*) from game where name like "%q%";

-- you can use the *single* character wildcard: _
select * from game where name like "_____";


-- you can use the distinct clause to limit results
-- to *unique* values
select distinct publisherId from game;

-- order by allows you to order the results
-- in general, the order of rows is UNDEFINED
select * from game order by name asc;
-- asc = ascending is the default
select * from game order by name;

-- desc = descending
select * from game order by name desc;

-- you can order by multiple columns
select * from game order by publisherId, name desc;

-- you can use the in clause to define a set
select * from game where gameId = 3 or gameId = 5 or gameId = 11;

select * from game where gameId in (3, 5, 11);
```

```
-- you can have queries within queries

select gameId from game where name like "G%";
select * from game where gameId
  in (select gameId from game where name like "G%");

-- alternative insert statements
insert into publisher (name) values ("DC Games");
insert into game (name, publisherId) values
  ("Suicide Squad",
  (select publisherId from publisher where name = "DC Games"));
```

## Data Projections

* You can "project" data with multiple "dimensions" (columns) down to lower dimensions (fewer columns) and *aggregate* the data to produce more information
* Basic Set Theory
* A set is an unordered collection of unique similar objects

$$A = \{a, b, c\}$$
$$B = \{1, 2\}$$
* The cardinality of a set is its size, $|A| = 3, |B| = 2$
* THe cartesian product or "cross join" is a set of pairs such that $a \in A$ (the element $a$ is in the set $A$), $b \in B$

$$A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$$
$$\mathbb{R} \times \mathbb{R}$$
$$(x, y)$$
$$|A \times B| = |A| \cdot |B|$$

$$A \cap B$$
$$A \cup B$$

```sql
use cbourke3;

select publisherId, count(*) as numberOfTitles from game group by publisherId;

select * from publisher;

select * from publisher join game;

select count(*) from publisher; -- 13
select count(*) from game; -- 20

select count(*) from publisher join game;

select p.name as publisher, g.name as title
  from publisher p
  left join game g
  on p.publisherId = g.publisherId;

-- yes there are right joins:
-- just swap your tables and do left joins
-- unless you HAVE to use a right join
select p.name as publisher, g.name as title
  from game g
  right join publisher p
  on p.publisherId = g.publisherId;

-- publisher name + number of games they've published
select p.name as publisher, count(g.gameId) as numberOfTitles
  from publisher p
  left join game g
  on p.publisherId = g.publisherId
  group by p.publisherId;

-- filter the results after the join/projection(group by)
select p.name as publisher, count(g.gameId) as numberOfTitles
  from publisher p
  left join game g
  on p.publisherId = g.publisherId
  group by p.publisherId
  having numberOfTitles > 3;

-- come full circle: flatten the entire data model
select
    p.name as publisher,
    g.name as title,
    a.publishYear,
    plat.name as platform
  from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId
union  
select
    p.name as publisher,
    g.name as title,
    a.publishYear,
    plat.name as platform
  from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform plat on a.platformId = plat.platformId;

```

## Designing & Implementing a Database

* Demonstration: create a database to model the asset classes (`Annuity`, `Stock`, `Person` and their emails)

```sql
use cbourke3;

-- nuke everything:
-- drop database cbourke3;
-- recreate YOUR database: but with no tables
-- create database cbourke3;

drop table if exists Ownership;
drop table if exists Asset;
drop table if exists Email;
drop table if exists Person;

create table if not exists Person (
  personId int not null primary key auto_increment,
  firstName varchar(255), -- allowed to be nullable
  lastName varchar(255) not null,
  dateOfBirth varchar(10) default "0000-00-00"
);

create table if not exists Email (
  emailId int not null primary key auto_increment,
  address varchar(255) not null,
  personId int not null, -- TODO: make this into a foreign key somehow
  foreign key (personId) references Person(personId)
);

-- insert some test data...

insert into Person (personId,firstName,lastName) values
  (10, "Chris", "Bourke"),
  (20, "LeBron", "James"),
  (30, "Larry", "Bird");

insert into Email (address,personId) values
  ("chris.bourke@unl.edu", 10),
  ("ljames@lakers.com", 20),
  ("lebron@cavs.com", 20);

select * from Person p
  left join Email e
  on p.personId = e.personId;

-- TODO: move account number and possibly
-- terms/monthlyPayment to the join table
create table if not exists Asset (
  assetId int not null primary key auto_increment,
  accountNumber varchar(255) not null unique key, -- ensures that it is unique and that it is an *index*: searches are efficient
  -- A = Annuity, S = Stock
  type varchar(1) not null,
  -- Annuity columns:
  terms int,
  monthlyPayment double,
  -- Stock columns:
  symbol varchar(255),
  sharePrice double
);  

-- annuities

insert into Asset (assetId,accountNumber,type,terms,monthlyPayment) values
  (101,"ABC123","A",5,500),
  (102,"XYZ456","A",10,150.01);

insert into Asset (assetId,accountNumber,type,symbol,sharePrice) values
  (201,"AAA","S","GOOG",135.28),
  (202,"BBB","S","APPH",0.067);


create table if not exists Ownership (
  ownershipId int not null primary key auto_increment,
  personId int not null,
  assetId int not null,
  numShares double, -- only for stock records
  foreign key (personId) references Person(personId),
  foreign key (assetId) references Asset(assetId)
);

-- annuities
insert into Ownership (personId,assetId) values
  (10,101),
  (10,102);
insert into Ownership (personId,assetId,numShares) values
  (10,201, 10.5),
  (10,202, 20),
  (20,201, 1000);

```

## Summary

### Database Design Observations

* Semantics dictate design: usually you have one table per "entity"
* Style tips:
  * Be consistent with your naming conventions
  * Tables should be `UpperCamelCase`
  * Columns should be `lowerCamelCase`
  * Avoid pluralizations, abbreviations, etc.
* Make sure that every table has a primary key PK using:
  * an integer
  * a name that is the `tableName` + `Id`
* Foreign keys should have the *same name* as the primary keys that they reference
  * Strings (`varchar`s) should not be used for PK or FK (casing issues, encoding issues, efficiency)
  * BUT: you *can* (should in some cases) make a *secondary key* out of strings
  * Example: ISBN, SSN, NUID: you can place a uniqueness constraint and/or `key` or `index` on columns
* Join tables should be used to model a many-to-many relationship
* Be sure to put plenty of test data in your database
* Check and uniqueness constraints can be used to enforce *data integrity*

## Normalization

* 1-NF, 2-NF, 3-NF
* They build on each other: you cannot have a higher normal form without having ALL lower normal forms
* First Normal Form: "each attribute (column) in a table only has atomic values"
  * Each column in a table represents ONE piece of data or ONE value
  * Violation: store a series of emails as one CSV column: `email1@foo.com,email1@bar.com,email3@baz.com`
  * Violation: store multiple columns instead of a proper one-to-many relation: `primaryEmail`, `secondaryEmail`, `tertiaryEmail`
  * Best practice: separate out into another table and define a one-to-many relationship
  * FKs go in the "child" table
* Second Normal Form: it has to be 1-NF: no non-prime attribute (column) is dependent on a proper subset of prime attributes
  * Having a PK auto-incremented gives you 2NF automatically
  * Violation: a purchase record that contains `customerId, storeId, storeAddress`
  * Suppose the PK is `customerId/storeId` (ie a combination of the two)
  * IF you defined a PK as a combination of `customerId/storeId` then you violate 2NF: `storeAddress` only depends on the second half of the key
  * It is often useful or necessary to have "compound keys", but they should always be *secondary*
  * You split everything out into its own table
* Third Normal Form: has to be 2-NF (and transitively 1-NF)
  * No non-prime column is transitively dependent on the key
  * Violation: store the `termYears, monthlyPayment` AND `totalValue` (`= termYears * monthlyPayment * 12`)
  * Violation: that the `totalValue` is stored when it should be *recomputed* based on the other values
  * Gives rise to possible data anomalies: if you change one value, it means the transitively dependent value is now *wrong*
* Every non-key attribute must provide a fact about the key (1NF), the whole key (2NF) and nothing but the key (3NF) so help you Codd

## Misc

* There is a LOT more
  * Triggers: event-based actions in a database
  * Views: read-only "tables" in a database
  * Temp tables: temporary tables that can be created within a transaction to make data processing easier
  * Stored Procedures: functions you can define with reusable SQL code that you can treat like a function
  * Loops, variables (cursors), etc.
  * Transactions: an all-or-nothing atomic "action" that can include more than 1 query, provides the ACID principles
    * Atomicity
    * Consistency
    * Isolation
    * Durability
  * Soft vs Hard deletes: a hard delete is a result of a `delete` statement.  A soft delete involves defining a boolean column `isActive` that is true if the record is active, false if it is "deleted"
  * Generally you want to use a *single table inheritance* strategy with databases vs OOP models

## Programmatically Connecting to a Database & Processing Data

* In Java we'll use JDBC = Java Database Connectivity API (Application Programmer Interface)
* Getting Started:
  * Download and "install" the Connector/J jar file for MySQL
    <https://dev.mysql.com/downloads/connector/j/>

#### Process:

1. Create a connection to your database: need user name, password, URL
2. Create/prepare your query
  - prepare the query
  - execute the query
3. Process your results
4. Clean up your resources

## Misc

* There are other, "better" solutions: ORMs = Object-Relational Mappings (Java: JPA = Java Persistence API) 

```text














```
