# Computer Science II - ECEN 156
## Spring 2025
### Databases & SQL

### Introduction/Overview

* Programs are short-lived, data is not
* We need a way to *persist* (save) data
* Up to now: flat files (CSV) or structured files (JSON, XML)

#### Flat Files

Flat data files are insufficient for storing data

* It is a terrible format to search or consolidate data
* Files can only be processed by one process (program) at a time: file locks
* Data is repeated unnecessarily
* Consistency issues: data anomalies
* Integrity issues: different ways of representing missing or non-existant data
* Organization problems: not sorted
* Formatting issues
* There no types: there are no integers, doubles, etc.
* There is no security

### Solution: use a RDBMS (Relational Database Management System)

* 1970 Edgar Codd
* Key aspects: data is stored in separate tables
* Tables have columns (pieces of data) and rows (records)
* Each column has a *type* and a *name*
* Each row in a table corresponds to a single record
* Records in *a* table are *uniquely* identified by *primary keys* (PKs)
* Records in different tables are *related* to each other through *foreign keys* (FKs)
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
    * Destroy (`delete`) - removing/deleting records in a table: THERE IS NO, **ABSOLUTELY NO** UNDO

## Getting Started

* You all have access to a database named after your canvas login (`cbourke3`) on a server named `nuros.unl.edu`
* Your password should have been sent to you via your huskers email this past weekend
* If you lose it and/or need to change it: you'll need to contact `manager@cse.unl.edu`
* DO NOT use this password for anything else and if you change your password, then make sure it is NOT something you would normally use!
* We recommend that you use MySQL Workbench to work with the SQL server: https://www.mysql.com/products/workbench/

```sql
-- always start by using YOUR database
-- the database is named after your login
use cbourke3;

-- you can list all of the tables in your database:
show tables;

-- you can describe a single table:
describe game;

-- C = Create
-- to insert new records into an existing table
-- insert a record with a hardcoded value for the primary key (PK)
-- this is okay for mock or test data, but not generally a good idea
insert into publisher (publisherId, name) values (14, 'Microsoft');
-- inserting a record without a PK, it will be *generated for you*!
insert into publisher (name) values ('Bethesda');

-- insert a game record
insert into game (name, publisherId) values ('Minecraft', 14);
-- otherwise you can use a "nested query":
insert into game (name, publisherId) values ('Flight Simulator',
  (select publisherId from publisher where name = 'Microsoft'));
-- order matters: parent records must exist
-- before child records
-- a publisher must exist before you can insert game records
insert into game (name, publisherId) values ('Eldenring',
  (select publisherId from publisher where name = 'From Software'));

-- U = Update
insert into publisher (name) values ('Take Too Software');
-- misspelled the name, so update:
-- safe mode will prevent blind updates of all records:
update publisher set name = 'Take Two Software';
-- always use a where clause:
update publisher set name = 'Take Two Software' where publisherId = 16;

-- D = Destroy
-- deletes ALL records:
delete from publisher;
-- always use a where claus:
delete from publisher where publisherId = 16;
-- you can escape special characters:
insert into publisher (name) values ('O\'Brian Software');
-- doesn't work on non key columns (PK, FK only)
delete from publisher where name = 'O\'Brian Software';
delete from publisher where publisherId = 17;

-- to delete a parent record, all child records must be
-- deleted first
delete from game where publisherId = 14;
delete from game where publisherId = 15;
delete from publisher where publisherId = 14 or publisherId = 15;

-- R = Retrieve

-- you can use the wildcard to pull every single column out of a
-- table:
select * from game;
select * from publisher;
-- or you can select only some columns:
select publisherId, name from game;
-- you can alias the columns in your result:
select name as gameTitle from game;
--
select name as publisherName from publisher;
-- alignment: white space does not matter
select
  name as gameTitle,
  gameId as id,
  publisherId
from game;

-- Aggregate functions
-- you can do some basic computations using the SQL language
-- determine how many games there are:
select count(*) as numberOfGames from game;

-- oldest available game
select min(publishYear) from availability;
-- newest game:
select max(publishYear) from availability;
-- average:
select avg(publishYear) from availability;
-- sums: weird but okay:
select sum(publishYear) from availability;
-- get really weird: use a $1M database as a calculator:
select 8 * 5 - sum(publishYear) / 3 from availability;

-- you can use a where clause to limit your results:
select * from game where gameId = 5;
select * from game where gameId != 5;
select * from game where gameId > 5;
select * from game where gameId >= 5;

-- you can use string matching:
select * from game where name = 'Legend of Zelda';
select * from game where name < 'Legend of Zelda';

-- generally you want partial string matching...
-- wildcard with strings is %
-- you use a "like" clause instead of a where clause:
-- selects all games that begin with "G"
select * from game where name like 'G%';
-- any game that has a lowercase a in it:
select * from game where name like '%a%';
-- strings are case sensitive:
select * from game where name like '%A%';

-- compound logic:
select * from game where name like '%the%' or name like '%The%';
select * from game where name like '% the%' or name like '% The%';
-- you can use the _ for single letter matches
select * from game where name like '%_the%' or name like '%_The%';
--
select * from game where name like '___ 3';

-- generally the order of rows (results) does not matter, but
-- you can reorder them in your result using ASC (default) or DESC
select * from game order by name;
select * from game order by name desc;
-- combination:
select * from game order by publisherId asc, name desc;

-- you can use the keyword distinct to get unique results:
select distinct publisherId from game;

-- you can use the in clause on a set of values to shorten your code:
select * from game where gameId = 3 or gameId = 7 or gameId = 13;
select * from game where gameId in (3, 7, 13);
select * from game where gameId in (select gameId from game where publisherId = 5);
```

```text













```
