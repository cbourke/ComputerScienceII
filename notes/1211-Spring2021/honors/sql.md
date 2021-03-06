# Databases & SQL
## CSCE 156H - Spring 2021


### Introduction/Overview

* Video Game CSV file

* Flat data files are insufficient for storing data
  * Repetition of data
  * Formatting issues
  * Consistency issues
  * Integrity issues
  * Organization problems: not sorted, it is in one big file (no multithreaded or multiuser capability)
  * There is no security!

* Solution: use a RDBMS (Relational Database Management System)
  * Edgar Codd 1970, IBM
  * Key aspects: 
  * data is stored in *tables*
  * Tables have rows or records
  * Tables have columns (pieces of data or *fields* that belong to each record)
  * A database is typically made up of *multiple* tables
  * Each table may be *related* to each other in some way
  * Each record in a table is uniquely identified by a Primary Key or PK for short
  * Records in one table may refer to a record in another table using a foreign key or FK for short

## CRUD Demonstration

* You can interact with a RDBMS through CRUD:
    * Create (`insert`)
    * Retrieve (`select`)
    * Update (`update`)
    * Destroy (`delete`)

### C = Creating Data

* Creating data involves inserting new record(s) into a table
* You use the keywords `insert into`

```sql

-- insert Mario 3D World into the game table:
insert into game (gameId,name,publisherId) values (21,"Mario 3D World", 5);
-- insert Splatoon 3 into the game table with auto-generated gameId:
insert into game (name,publisherId) values ("Splatoon 3", 5);
-- insert Among Us:
-- 1. you need to insert the publisher first
insert into publisher (name) values ("InnerSloth");
-- inser the game record using a *nested query*:
insert into game (name,publisherId) values ("Among Us",
  (select publisherId from publisher where name = "InnerSloth")
);

-- You can also insert MULTIPLE records at the same time:
insert into game (name, publisherId) values
  ("Zelda: Breath of the Wild", 14),
  ("Splatoon 1", 14),
  ("Sonic", 4);
```

## Update

* You can alter existing records in a table
* Use the keyword `update`

```sql

-- this would update EVERY record, safe mode prevents it
update game set name = "Splatoon";

-- update using a where clause:
update game set name = "Splatoon" where gameId = 25;

```

## Destroy

```sql

-- safe mode prevents you from deleting ALL records:
-- this doesn't delete a table
delete from game;

delete from game where gameId = 28;
delete from game where gameId = 29 and gameId = 30;
delete from game where gameId in (29, 30);
```

## Retrieve

* You an use a wildcard (`*`) to get *every* column in a table 

```sql
select * from game;
select * from publisher;
```

```text









```

  
  
