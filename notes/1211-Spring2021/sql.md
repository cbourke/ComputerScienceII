# Databases & SQL
## CSCE 156 - Spring 2021

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
  * 1970 Edgar Codd 
  * Key aspects: data is stored in *tables*
  * Tables have columns and rows
  * Columns define fields or pieces of data for each record
  * Rows correspond to a single record
  * A database typically has *multiple tables* that are *related* 
  * Each row may have a unique identifier: *Primary Key* or PK for short
  * Rows in different tables may be related to each other throuogh *foreign keys* FKs for short
    * May define a *many-to-one* or *one-to-many* relation
    * Or FKs may define a *many-to-many* relation

## CRUD Demonstration

* You can interact with a RDBMS through CRUD:
  * Create (`insert`)
  * Retrieve (`select`)
  * Update (`update`)
  * Destroy (`delete`)

### C = Creating Data

* Creating data involves inserting new records into an existing table
* You use the `insert` keyword

```sql
-- insert a publisher record using the hard-coded primary
-- key value 14:
insert into publisher (publisherId, name) 
  values (14, "EA Sports");

-- Let the database determine the next available key value:
insert into publisher (name) 
  values ("EA Sports");
  
  
-- insert a game record, refering the inserted EA Sports record
insert into game (name, publisherId) values ("Maddon 21", 14);
  
-- insert a game record and refer to EA Sports using a *nested query*:
insert into game (name, publisherId) values ("Maddon 22", 
  (select publisherId from publisher where name = "EA Sports"));

  
  -- insert multiple records with one query:
  insert into game (name,publisherId) values 
    ("Madden 21", 14),
    ("Madden 22", 14);
    
```

## Update Data

```sql
-- this update would change EVERY game record
-- not possible in "safe mode"
update game set name = "Madden 22";

-- update the particular game with the given gameId
update game set name = "Madden 22" where gameId = 22;

```

## Destroying Data

```sql

-- this would delete every single game record
-- it would NOT delete the table itself
-- not possible in safe mode!  Thankfully!
delete from game;

-- delete a single game by providing a gameId:
delete from game where gameId = 22;
delete from game where gameId = 21;
```

### Observations

* Comments are usually denoted with a `--` (two hyphens)
* Be consistent on table name and column name conventions; suggestion:
  * `TableName`s are upper camel cased (NOT plural)
  * `columnName`s are lower camel cased
* In old-school SQL, keywords are `ALLCAPS`, you can use this convention, but why?
  * SQL keywords are case *insensitive*
  * THe old-school way assumed a monochrome monitor
* Whitespace generally does not matter, you can go to the next line and break up long lines to make it more readable
* If you do, align things using proper indentation
* You can use either double quotes or single quotes to denote strings, but be consistent
* Every query ends with a semicolon `;`

## Retrieving Data

* You can pull data out of a database using the `select` statement
* To pull every single record and every single column value you can use a wildcard:

```sql
select * from publisher;
select * from game;

-- you can specify only a subset of columns to retrieve:
select name from publisher;
select name,publisherId from game;

```

* You can "join" two or more tables together using a `join` query
  
```text







```

  
  
