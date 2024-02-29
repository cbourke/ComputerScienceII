# Databases & SQL
## CSCE 156 - Spring 2024

### Introduction/Overview

* Flat data files are insufficient for storing data
  * It is a terrible format to search or consolidate data
  * Files can only be processed by one process (program) at a time: file locks
  * Data is repeated unnecessarily
  * Consistency issues: data anomalies
  * Integrity issues: different ways of representing missing or non-existant data
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
* Records in different tables are *related* to each other through *foreign keys* (FKs)
* Records in *a* table are *uniquely* identified by *primary keys* (PKs)
* Foreign keys define relations between tables:
  * One-to-many relation: one record in table $A$ may be related to many records in table $B$ (or none at all)
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
    * Destroy (`delete`) - removing/deleting records in a table: THERE IS NO, ABSOLUTELY NO UNDO

## Getting Started

* You all have access to a database named after your canvas login (`cbourke3`) on a server named `cse-linux-01.unl.edu`
* Your password should have been sent to you via your huskers email this morning
* DO NOT use this password for anything else and if you change your password, then make sure it is NOT something you would normally use!
* If you lose it and/or need to change it: you'll need to contact `manager@cse.unl.edu`
* We recommend that you use MySQL Workbench to work with the SQL server: https://www.mysql.com/products/workbench/

## Demonstration

```sq
#-- always use your database
#-- named after your canvas login
use cbourke3;

-- you can show your tables
-- initially, you won't have any
show tables;

-- C = Create some publisher records...
insert into publisher (name) values ("Ubisoft");
insert into publisher (name) values ("Bandai-Namco");

-- you *can* specify a primary key (PK)
insert into publisher (publisherId,name) values (400, "Riot");
-- you generally only want to specify a PK if you are
-- inserting test data and need to have a fixed value as a
-- Foreign key
insert into game (publisherId,name) values (400, "Valorant");
insert into game (publisherId,name) values (400, "Leegue of Legands");

update game set name = "League of Legends" where gameId = 23;

-- this would delete EVERY record in the table
-- this does not delete the TABLE itself, only records
-- this is not possible in safe mode (thankfully)
delete from game;

-- want to delete the publisher "Riot" but we cannot becuase
-- it has referencing records (children)
delete from publisher where publisherId = 400;

-- you can delete the games FIRST...
delete from game where gameId = 23;
delete from game where gameId = 22;
-- now you can delete Riot:
delete from publisher where publisherId = 400;

-- R = Retrieve records

-- you use the keyword "select" and you *can* use the
-- "wildcard": *
select * from publisher;
select * from game;

-- you can be more specific and only query certain columns:
select name from game;
-- order of columns does not matter and can be changed:
select name,gameId from game;
-- you can also "rename" or "alias" columns for the query result
select name as title,
       gameId as ID
       from game;

-- Aggregate functions
-- you can do basic computations using SQL statements
select count(*) as numberOfGames from game;

-- you also have min, sum, max, avg, etc.
-- oldest game:
select min(publishYear) from availability;
-- newest game:
select max(publishYear) from availability;
-- average (not ave)
select avg(publishYear) from availability;
-- weird, but okay:
select sum(publishYear) from availability;
-- really really sophisticated calculator:
select 8 * 5 + 3 - (5 / 4);

-- you can limit results using a where clause
select * from game where gameId = 5;
select * from game where gameId > 5;
select * from game where gameId >= 5;
select * from game where gameId != 5;

-- string based queries
select * from game where name = "Legend of Zelda";
select * from game where name < "Legend of Zelda";
-- generally you want partial string matching...
-- wildcard(s): %
-- with the "like" clause
-- this will select any game that begins with "G"
select * from game where name like "G%";
-- this will select any game that ENDS with s
select * from game where name like "%s";
-- this will selct any game that has a q in its title:
select * from game where name like "%h%";

-- compound logic: "and" and "or" clauses
select * from game where name like "%the%" or name like "%The%";
--
select * from game where name like "G%" and gameId <= 2;

select * from game where (name like "G%" and gameId <= 2) or gameId = 7;

-- you can use the _ for single letter matches
select * from game where name like "%_he%";

select * from game where name like "%______%";

-- generally the order of rows (results) does not matter, but
-- you can order them using ASC (default) or DESC
select * from game order by name;
select * from game order by name desc;

-- you can order by multiple columns...
select * from game order by publisherId asc, name desc;

select * from publisher;
```



```text









```
