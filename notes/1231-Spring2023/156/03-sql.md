# Databases & SQL
## CSCE 156 - Spring 2023

### Introduction/Overview

* Flat data files are insufficient for storing data
  * Repetition of data
  * Formatting issues
  * Consistency issues: data anomalies
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
* Records within a table are uniquely identified by *primary keys*
* Foreign keys define relations between tables:
  * One-to-many relation: one records in table $A$ may be related to many records in table $B$
  * Many-to-one: same, but from hte perspective of $B$
  * Many-to-many relation: one record in table $A$ can be related to many records in table $B$ and vice versa

## CRUD Demonstration

* You can interact with a RDBMS through CRUD:
    * Create (`insert`)
    * Retrieve (`select`)
    * Update (`update`)
    * Destroy (`delete`)

```sql

select * from platform;
select * from game;
select * from publisher;

-- insert a new record for the publisher "Naughty Dog"

insert into publisher (name) values ("Naughty Dog");
-- you can, but should not specify primary keys:
insert into publisher (publisherId,name) values (20, "Naughty Dog");

-- remove the duplicate entry for Naughty Dog

-- bad, but safe mode prevents you from deleting everything in the table
delete from publisher;


-- delete an entire table:
-- drop table publisher;

-- safely delete ONE record using a WHERE clause:

delete from publisher where publisherId = 20;

-- hardcode a FK (foreign key):
insert into game (name, publisherId) values ("The Last of Us", 14);


-- OR you can use a "nested" loop:
insert into game (name, publisherId) values ("The Last of Us",
  (select publisherId from publisher where name = "Naughty Dog") );

insert into game (gameId, name, publisherId) values (100, "Uncharted", 14);

insert into game (name, publisherId) values
  ("Uncharted 2", 14),
  ("The Last of Us 2", 14);

insert into game (name, publisherId) values ("Crash Bandikoot", 14);

-- update my misspelling:

update game set name = "Crash Bandicoot" where gameId = 103;

```

### Observations

* Comments are usually denoted with a `--` (two hyphens)
* Be consistent on table name and column name conventions; suggestion:
  * `TableName`s are upper camel cased (NOT plural)
  * `columnName`s are lower camel cased
* In old-school SQL, keywords are `ALLCAPS`, you can use this convention, but why?
  * SQL keywords are case *insensitive*
  * The old-school way assumed a monochrome monitor
* Whitespace generally does not matter, you can go to the next line and break up long lines to make it more readable
* If you do, align things using proper indentation
* You can use either double quotes or single quotes to denote strings, but be consistent
* Every query ends with a semicolon `;`

## Retrieving Data

* You can pull data out of a database using the `select` statement
* To pull every single record and every single column value you can use a wildcard:

```sql

select * from game;

-- you can be more specific on which columns by specifying them:
select name,publisherId from game;
select name from game;

-- you can use aliasing of column names
select name as gameTitle from game;
```

## More Fancy Select statements

* You can use *aggregate* functions to compute results on the SQL server itself

```sql

-- how many game titles do we have in our database?
select count(*) as numberOfTitles from game;

-- you can specify more fine-grained data/queries using a where clause:
select * from game where name = "GTA 2";
select count(*) from game where name = "GTA 2";
select * from game where publisherId = 1;
select * from game where publisherId = 1 or name = "GTA 2";
select * from game where publisherId = 1 and name = "GTA 2";
select * from game where (publisherId > 3 and publisherId < 7)
  or name = "GTA 2";

-- other aggregate functions:
-- what is the oldest game?
select max(publishYear) from availability;
select min(publishYear) from availability;
select avg(publishYear) from availability;
select 10 * min(publishYear) + 3 as someNumber from availability;


-- you can do partial string matching:
select * from game where name like "G%";
select * from game where name like "G%";
select * from game where name like "%G%";
select * from game where name like "g%";
select * from game where name like "%t%";

-- You can match any single character using the underscore:
select * from game where name like "_a%";
select * from game where name like "___a%";

```

## `order by` clause

* In general the results you get back from a database are unordered
* you can sort the results by any column using `order by`
* Default: ascending order

```sql
select * from availability order by publishYear;
select * from availability order by publishYear asc;
select * from availability order by publishYear desc;
-- string order:
select * from game order by name;
-- order first by publisher then by name:
select * from game order by publisherId, name;
select * from game order by publisherId desc, name asc;
```

## Distinct

* You can use `distinct` to only get unique values: `select distinct publisherId from game;`

## `in` clause

* Sometimes you can treat a result as a *set*

```sql

select * from game where gameId = 1 or gameId = 5 or gameId = 9;
-- using the in clause:
select * from game where gameId in (1, 5, 9);
-- allows you to use a *subquery* to pull more data
select publisherId from publisher where name = "LucasArts";
select * from game where publisherId = 1;
select * from game where publisherId in
  (select publisherId from publisher where name ="LucasArts" or name = "Nintendo");

select * from game where publisherId in
  (select publisherId from publisher where
   name in ("LucasArts", "Nintendo"));
```

## Data Projections

* You can "project" data with multiple "dimensions" (columns) down to lower dimensions (fewer columns) and *aggregate* the data to produce more information
* Example: how many games has each publisher published?

```sql

select * from game;

-- restrict to only games that begin with a "G":
select * from game where name like "G%";

select publisherId, count(*) as numberOfGames
  from game
  where name like "G%"
  group by publisherId;


select publisherId, count(*) as numberOfGames
  from game
  group by publisherId
  having numberOfGames >= 2;

select publisherId, count(*) as numberOfGames
  from game
  where name like "Z%"
  group by publisherId
  having numberOfGames >= 2;

```

Let $A, B$ be sets:
  $$A = \{a, b, c\}, B = \{1, 2\}$$

$$A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$$

$$\mathbb{R} \times \mathbb{R}$$

* What is the cardinality (size) of $A$?  $|A| = 3$
* What is the cardinality (size) of $B$?  $|B| = 2$
* What is the cardinality (size) of $A\times B$?  $|A\times B| = 6$
* In general, if you have $|A| = n$, $|B| = m$, what is the cardinality of $|A \times B| = n \cdot m$

```sql

select count(*) from game;
select count(*) from publisher;
select * from publisher;

select 22 * 17;

-- naive cross join/cartesian product:
select * from game join publisher;

-- you only join ON the publisherId:
select * from game g join publisher p
  on g.publisherId = p.publisherId;

-- only select the columns you are interested in:  
select g.name as gameTitle, p.name as publisherName
  from game g join publisher p
  on g.publisherId = p.publisherId;

  -- gives a good report but does not include any
  -- publishers with zero games...
select p.name as publisherName, count(g.gameId)
  from game g join publisher p
  on g.publisherId = p.publisherId
  group by g.publisherId;

select p.name as publisherName, count(g.gameId)
  from publisher p join game g
  on g.publisherId = p.publisherId
  group by g.publisherId;

select p.name as publisherName, count(g.gameId)
  from publisher p join game g
  on g.publisherId = p.publisherId
  group by g.publisherId;

-- preserve records from the left table
select *
  from publisher p left join game g
  on g.publisherId = p.publisherId;

-- preserves records from the left table and
-- treats NULL values as zero
-- this one is wrong because you don't want to count the row, but the gameId:
select p.name as publisherName, count(*)
  from publisher p left join game g
  on g.publisherId = p.publisherId
  group by p.publisherId;

select p.name as publisherName, count(g.gameId)
  from publisher p left join game g
  on g.publisherId = p.publisherId
  group by p.publisherId;

select p.name as publisherName, count(g.gameId)
  from game g right join publisher p
  on g.publisherId = p.publisherId
  group by p.publisherId;

-- create a query to "flatten" the entire database:
-- ie produce a CSV-like file

use cbourke;

-- a single query to dump all of the data to one "flat" representation
select *
  from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on a.gameId = g.gameId
  left join platform plat on plat.platformId = a.platformId
union
select *
  from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on a.gameId = g.gameId
  right join platform plat on plat.platformId = a.platformId;

```

## Designing & Implementing a Database

* Demonstration: create a database to model the account (annuity, savings account)
  application.  Allow accounts to be owned by multiple people

```sql

drop table if exists Email;
drop table if exists Ownership;
drop table if exists Person;
drop table if exists Account;

create table if not exists Person (
  personId int primary key not null auto_increment,
  -- TODO: allowed to be null?
  firstName varchar(255),
  lastName varchar(255) not null,
  dateOfBirth varchar(10) not null default "0000-00-00"
);

create table if not exists Email (
  emailId int primary key not null auto_increment,
  address varchar(255) not null,
  personId int not null,
  foreign key (personId) references Person(personId)
);

create table if not exists Account (
  accountId int primary key not null auto_increment,
  accountNumber varchar(255) not null unique key,
  -- S = Savings, A = Annuity
  type varchar(1) not null,
  monthlyPayment double,
  termYears int,
  balance double,
  apr double, -- [0, 1]
  constraint `aprIsValid` check (apr >= 0 and apr <= 1)
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

### Observations

*


```text













```
