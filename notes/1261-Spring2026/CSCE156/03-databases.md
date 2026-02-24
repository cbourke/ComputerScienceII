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

```text








```
