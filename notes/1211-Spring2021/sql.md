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
select name, publisherId from game;

```

## More on Select

```sql


-- you can use an aggregate count function to determine
-- the number of records in a query
select count(*) from game;

-- a where clause can be used to specify a subset of records
select * from game where name = "GTA 2";
select * from game where gameId = 5;
select * from game where publisherId = 5;
select count(*) from game where publisherId = 5;
select * from publisher;
select * from game where publisherId = 3;
-- you can alias any column or table name using the
-- as keyword
select count(*) as numberOfGames from game where publisherId = 3;

-- you can use other basic math and aggregate functions:
select max(publishYear) from availability;
select min(publishYear) from availability;
select avg(publishYear) from availability;
-- basic math is also supported:
select (max(publishYear) + 10) *2 from availability;

-- you can also query based on partial string matches 
-- using a combination of wildcards, % and the like clause:
select * from game where name like "GTA%";
-- select any game title that has an 'a' in it:
select * from game where name like "%a%";
-- it is case sensitive:
select * from game where name like "%A%";
-- you can also use the single character wildcard: _
select * from game where name like "_a%";

-- you can combine queries using basic logic operators:
-- or, and, not
select * from game where name = "GTA 2" or gameId > 10;
select * from game where name like "GTA%" and gameId < 3;
-- you can select any game whose publisherId is null:
select * from game where publisherId is null;
select * from game where publisherId is not null;
-- for non-null queries you can use the !=:
select * from game where name != "GTA 2";
-- you can use !=, =, <, >, <=, >=
```

## ORDER BY clause

```sql

-- alphabetic order by name, a-z:
select * from game order by name;
-- reversed alphabetic order (asc = ascending, default)
-- desc = descending
select * from game order by name desc;
select * from game order by publisherId asc, name desc;
```

## Distinct

```sql
select count(*) as numPublishers from publisher;
select count(*) as numGames from game;
-- you can select distinct elements from a query:
select distinct publisherId from game;
select count(distinct publisherId) as numPubs from game;
```

## In clause

```sql
select * from game where gameId = 1 or gameId = 5 or gameId = 9;
-- the in clause allows you to define a set:
select * from game where gameId in (1, 5, 9);
-- you can use it in conjunction with a nested query:
select * from game where gameId in (
  select gameId from game where name like "G%"
);

```

## data projections

```sql

-- projections: 
-- goal: find out how many games each publisher published
select * from game;
-- project the data down to 2 dimensions: (publisherId, number of games they published)
select publisherId, count(*) from game group by publisherId;
-- careful what you group by, it may not make sense:
select name, count(*) from game group by name;

-- after your projection you may want to limit your query further
-- using the having clause allows you to filter your records further:
select publisherId, count(*) as numGames from game 
  group by publisherId
  having numGames >= 3;
-- the where clause is executed BEFORE the projection/group by clause
-- the having is executed AFTER the projection
```

## Joins

```sql

-- you can join one or more tables together using a 
-- join statement

-- a cross join is a full cartesian product of records:
select * from publisher cross join game;

-- usually you want to make a join that makes sense:
-- usually you join records that are actually *related*
select * from publisher p join game g 
  on p.publisherId = g.publisherId;

-- use in conjunction with column selection to limit your query results:
select 
  p.name as publisherName, 
  g.name gameName 
from publisher p join game g 
on p.publisherId = g.publisherId;

-- a regular join (above) will only match on records that exist
-- publisher to the game table will only match publishers that actually
-- have published a game
-- we want ALL publishers, even if they have not published a game
-- you will want to use a "left join"
select 
  p.name as publisherName, 
  g.name gameName 
from publisher p left join game g 
on p.publisherId = g.publisherId;

select 
  p.name as publisherName, 
  g.name gameName 
from publisher p left join game g 
on p.publisherId = g.publisherId
where g.name is null;
-- you can also use right joins that join the other way
select 
  p.name as publisherName, 
  g.name gameName 
from game g right join publisher p
on p.publisherId = g.publisherId;

-- you can also use right joins in the same order:
select 
  p.name as publisherName, 
  g.name gameName 
from publisher p right join game g
on p.publisherId = g.publisherId;

-- reproduce the flat file from assignment 1:
select 
  p.name as publisherName, 
  g.name as title,
  a.publishYear as year,
  platform.name as platform
  from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform on a.platformId = platform.platformId;
  

select 
  p.name as publisherName, 
  g.name as title,
  a.publishYear as year,
  platform.name as platform
  from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform on a.platformId = platform.platformId;  
  
  -- you can union two results together:
  select 
  p.name as publisherName, 
  g.name as title,
  a.publishYear as year,
  platform.name as platform
  from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform on a.platformId = platform.platformId
union
select 
  p.name as publisherName, 
  g.name as title,
  a.publishYear as year,
  platform.name as platform
  from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform on a.platformId = platform.platformId;  
```

  
  
  
  
  
  
  
  
  
```text









```

  
  
