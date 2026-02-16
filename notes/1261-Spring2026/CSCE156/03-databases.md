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

```text








```
