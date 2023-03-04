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

```text






```
