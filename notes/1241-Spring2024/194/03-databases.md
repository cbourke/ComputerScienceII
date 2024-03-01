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
  (select publisherId from publisher where name = "DC Games"));alter
```



```text









```