# Computer Science II - CSCE 156
## Spring 2026
### Databases & SQL

### Introduction/Overview

* Programs are short-lived, data is not
* We need a way to *persist* (save) data
* Up to now: flat files (CSV) or structured files (JSON, XML)

* Flat data files are insufficient for storing data
  * It is a terrible format to search or consolidate data
  * Files can only be processed by one process (program) at a time: file locks
  * Data is repeated unnecessarily
  * Consistency issues: data anomalies
  * Integrity issues: different ways of representing missing or non-existent data
  * There is no enforcement of rules: if you expected a `double` (GPA) there is nothing stopping you from entering "hello"
  * Organization problems: not sorted
  * There is no security
  * Formatting issues
  * There are no types: an integer, string, double, etc.: there are only strings

### Solution: use a RDBMS (Relational Database Management System)

* 1970 Edgar Codd
* Key aspects: data is stored in separate tables
* Tables have columns (pieces of data) and rows (records)
* Each column has a *type* and a *name*
* Each row in a table corresponds to a single record
* Records in *a* table are *uniquely* identified by *primary keys* (PKs)
* Records in different tables are *related* to each other through *foreign keys* (FKs)
* Foreign keys define relations between tables:
  * One-to-many relation: one record in table $A$ may be related to multiple records in table $B$ (or none at all)
  * Many-to-One relation: this is the same as a one-to-many but just from the reversed perspective
  * Many-to-many relationship: two one-to-many relations through a third table: a "join" table; one record in table $A$ can refer to many records in $B$ and vice versa
  * One-to-one relation: one record in $A$ is related to ONLY (at most) one record in $B$ (generally this is not as useful; instead you should prefer one table, UNLESS there is a Very Good Reason)

## CRUD Demonstration

* CRUD = Create Retrieve Update Destroy
* Four basic operations to any data collection
* You can interact with a RDBMS through CRUD:
    * Create (`insert`) - inserting new records into a table
    * Retrieve (`select`) - pull out records or data from a table
    * Update (`update`) - modifying currently existing records
    * Destroy (`delete`) - removing/deleting records in a table: THERE IS NO, **ABSOLUTELY NO** UNDO

## Getting Started

* We recommend that you use MySQL Workbench to work with the SQL server: https://www.mysql.com/products/workbench/
* You all have access to a database named after your canvas login (`cbourke3`) on a server named `nuros.unl.edu`
* You password will be sent via email
* DO NOT use this password for anything else and if you change your password, then make sure it is NOT something you would normally use!
* If you lose it and/or need to change it: you'll need to contact `manager@cse.unl.edu`

# Basic CRUD

* Inserting records into the database: **C**reate (`insert`)
* Updating records: `update`
* Deleting records

```sql
use `c-cbourke3`;

select * from publisher;
select * from game;

# publishers must be inserted before a game record
insert into publisher (name) values ('CD Projekt RED');
insert into publisher (name) values ('Roblocks');

# now you can insert a game record
insert into game (name,publisherId) values ('Cyberpunk 2077', 14);
# you can use subqueries to get values of keys:
insert into game (name,publisherId) values ('The Witcher',
  (select publisherId from publisher where name = 'CD Projekt RED'));

# fix records that already exist
update publisher set name = 'Roblox' where publisherId = 15;

# D = Destroy = delete
delete from publisher where publisherId = 15;

# neato: you can insert multiple records in one query:

insert into game (name,publisherId) values
  ('Roblox', 16),
  ('Roblox 2', 16);

```

### Observations

* If you `update` or `delete` without a `where` clause: it may affect *every* record in the table!
  * Generally SAFE MODE will save you from this
  * Later on: in Java when we connect to the database, there is NO safe mode
* If you have a parent/child table then:
  * The parent must exist before you can create any child records
  * The child(ren) records must be deleted before the parent record can be deleted
  * Parent: one, child: many in a one-to-many relationship
  * The child has the FK = Foreign Key

# Retrieve = `select`

* You use the `select` keyword but it has MANY modifiers

## Basics

```sql

# basic select: * is a wildcard that selects ALL of the columns
select * from game;

select name from game;

select gameId, name from game;

select name,gameId from game;

# you can limit the number of records:
select * from game limit 5;
```

## More basics

```sql
use `cbourke3`;


select * from game where name >= 'Portal';
-- the percent sign is a wildcard that matches one or more characters
select * from game where name like 'G%';
select * from game where name like '%a%';
-- you can also match single cahracters using _
select * from game where name like '_o%';

-- ordering
-- asc is the default
select * from game order by name desc;
select * from game order by publisherId asc, name desc;

-- distinct will only give you unique elements:
select distinct publisherId from game;


use `cbourke3`;

select * from publisher
  join game on publisher.publisherId = game.publisherId;

select *
  from publisher p
  join game g
  on p.publisherId = g.publisherId;

select *
  from publisher p
  left join game g
  on p.publisherId = g.publisherId;  

-- how many games has each publisher published?
select p.name as publisherName, count(g.gameId) as numberOfTitles from publisher p
  left join game g on p.publisherId = g.publisherId
  group by p.publisherId;


# 07. List all games and all systems that they are available on

select * from publisher p
  join game g on p.publisherId = g.publisherId
  join availability a on g.gameId = a.gameId
  join platform plat on a.platformId = plat.platformId;

select * from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId;

# 08. List all games that are not available on any system

select * from game g
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId
  where plat.platformId is null;

# 09. List the oldest game(s) and its platform(s)

select g.name, a.publishYear from game g
  join availability a on g.gameId = a.gameId
  where a.publishYear = (select min(publishYear) from availability);

# 10. Flatten the entire data model by returning all data on all games

select * from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId
union
select * from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform plat on a.platformId = plat.platformId;

```

## Designing & Implementing a Database

* Create a database to model the asset java classes/problem (Asset, Savings, Stock, Person (owner), Email(s) of a person, etc.)

### Observations

* Sematics dictate design: for every "real world" entity, you are likely to have a table
* Style observations:
  * Be consistent in your naming conventions
  * Avoid verbs, use nouns
  * Avoid abbreviations, do not pluralize table names
  * Modern convention: `UpperCamelCasing` for tables
  * Modern convention: `lowerCamelCasing` for columns
* All tables should have a PK = Primary Key
  * it should be an integer (NOT a `double` or `varchar`)
  * Use naming: `tableName` + `Id`
  * All of them should be: `int not null primary key auto_increment`
* Foreign Keys (FKs) should have the same name as the PK they reference
  * Sometimes you have to violate this
* Define `unique key`s to ensure uniqueness on other columns
  * `unique` ensures that column values are unique
  * `key` makes sure they are indexed: they can be searched very efficiently
  * PKs = surrogate keys that are generated and managed by the database
  * Other keys may be "natural" keys: SSN, NNUID, UUIDs, etc: they are NOT managed by the database we still want them
* Compound Keys
  * You can make a key that involves two or more columns
  * Ex: want the *combination* of `assetId,personId` to be unique
* Check Constraints
  * Allows you to place limits on the data that is in the database
  * Ex: you can make it so that a column is not allowed to be negative

### Final Database Design

```sql

```

## Normalization

* 1-NF, 2-NF, 3-NF
* They build on each other: you cannot have a higher normal form without having ALL lower normal forms
* First Normal Form: "each attribute in a table only has atomic values"
  * Each column only represents ONE piece of data, ONE value
  * Violation: storing multiple pieces of data as a single CSV string: `email1,email2,email3`
  * Violation: multiple columns to support multiple values: 3 columns for 3 emails: `primaryEmail`, `secondaryEmail`, `tertiaryEmail`
  * Be sure to separate data out into separate tables as necessary
* Second normal form: it has to be 1-NF: no non-prime attribute is dependent on a proper subset of prime attributes
  * Using a PK auto-generated, not null gets you 2-NF automatically!
  * Violation: a purchase record that contains `customerId, storeId, storeAddress`
  * If the PK is a combination of `customerId/storeId` the `storeAddress` is only dependent on
  * It is often useful and necessary to define combination keys, but *keep them secondary*!
  * You split everything out into its own table
* Third Normal form: has to be 2-NF (and transitively 1-NF)
  * No non-prime column is transitively dependent on the key
  * Example: store `termsYears, monthlyPayment` for an annuity, but *also* I store the `value = termsYears * monthlyPayment * 12`
  * THe `total` is transitively dependent on the other two values so **we do not store it**
  * Storing data that is dependent on other data is *wrong*: because it quickly may become out of sync with the other data
  * Instead: transitively dependent data should be *recomputed* (in a query or in a program)
* Every non-key attribute must provide a fact about the key (1NF), the whole key (2NF) and nothing but the key (3NF) so help you Codd

## Misc

* There is a LOT more
  * DBA = DataBase Administrator
  * Triggers: event-based actions in a database
  * Views: read-only "tables" in a database
  * Transaction: an atomic operation on a database that supports the ACID principles
    * Atomicity
    * Consistency
    * Isolation
    * Durability

```sql

use `cbourke3`;


-- My Assets database
-- Chris Bourke
-- 2026-02-27

drop table if exists Ownership;
drop table if exists Asset;
drop table if exists Email;
drop table if exists Person;

-- represents a person
create table if not exists Person (
  personId int not null primary key auto_increment,
  uuid varchar(36) not null unique key,
  lastName varchar(255) not null,
  firstName varchar(255)
);

-- represnt an email
create table if not exists Email (
  emailId int not null primary key auto_increment,
  address varchar(255) not null,
  personId int not null,
  foreign key (personId) references Person(personId)
);

insert into Person (personId,uuid,lastName,firstName) values
  (1, 'b5abdbbb-cb12-4b77-871e-ea018da6dc9b', 'Bourke', 'Chris'),
  (2, '8b1726a1-e672-4496-8e70-647ab1f0ee17', 'Bourke', 'Jeff'),
  (3, '62466e21-39d7-4268-afe4-b724bc17a251', 'Smith', 'John');

insert into Email (emailId, address, personId) values (1, 'rallington0@cbsnews.com', 1);
insert into Email (emailId, address, personId) values (2, 'mroddan1@delicious.com', 1);
insert into Email (emailId, address, personId) values (3, 'ccubbin2@printfriendly.com', 2);

-- select the data
select * from Person p
  left join Email e on p.personId = e.personId;
-- report how many emails for each person
select p.personId, count(e.emailId) from Person p
  left join Email e on p.personId = e.personId
  group by p.personId;

create table if not exists Asset (
  assetId int not null primary key auto_increment,
  type varchar(1) not null, -- 'S' = Stock, 'A' = Savings Account
  name varchar(255) not null,

  -- stock assets:
  stockSymbol varchar(255),
  sharePrice double,

  -- savings accounts:
  interestRate double
);  

insert into Asset (assetId,type,name,stockSymbol,sharePrice) values
  (1,'S', 'Microsoft Stock', 'MSFT',200),
  (2,'S', 'Google', 'GOOG',250);

insert into Asset (assetId,type,name,interestRate) values
  (3,'A','Mega Savings', .035),
  (4,'A','CD', .0275);

create table if not exists Ownership(
  ownershipId int not null primary key auto_increment,
  accountNumber varchar(255) not null unique key,
  assetId int not null,
  personId int not null,
  -- stocks:
  numberOfShares double,
  -- savings accounts:
  balance double,
  foreign key (assetId) references Asset(assetId),
  foreign key (personId) references Person(personId),
  unique key (assetId,personId),
  constraint `nonNegativeShares` check (numberOfShares >= 0)
);

-- 100 shares of Microsoft stock
insert into Ownership (accountNumber,assetId,personId,numberOfShares) values ('ABCD',1,1,200);  
insert into Ownership (accountNumber,assetId,personId,numberOfShares) values ('ABCE',2,1,100);  

select *, sharePrice * numberOfShares as value from Asset a
  join Ownership o on a.assetId = o.assetId
  join Person p on o.personId = p.personId;
```

## Programmatically Connecting to a Database & Processing Data

* Every language has some sort of framework to connect to a database and process (insert/delete/update/select) data
* In Java we'll use JDBC = Java Database Connectivity API (Application Programmer Interface)
* Getting Started:
  * Download the Connector/J jar file and include it in your project: <https://dev.mysql.com/downloads/connector/j/>

## Overview

* Goal: programmatically connect to MySQL/MariaDB/Postgres database and process (persist/load) data
* Perfect illustration of *Dependency Inversion*
  * You don't program toward a particular database
  * You program toward an *interface* (JDBC)
  * Then each database vendor (Oracle, Postgres, IBM, etc.) each publish their own "driver" (JAR file) that *implements* that interface
* JDBC provides:
  * `Connection`
  * `PreparedStatement`
  * `ResultSet`

## Process

1. Create a connection to your database: need user name, password, URL
2. Create/prepare your query
  - prepare the query
  - execute the query
3. Process your results
4. Clean up your resources

## Observations


```text








```
