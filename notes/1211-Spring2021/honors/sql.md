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

### Aggregate Functions

```sql


-- you can use aggregate function such as count() to process some of
-- your queries
select count(*) as numberOfGames from game;
select count(*) as numPublishers from publisher;
-- you can use the distinct keyword to only select unique values:
select distinct name from game;
-- combo:
select count(distinct name) from game;
select count(distinct publisherId) from game;

-- you can use other aggregate functions and math!
select min(publishYear) from availability;
select max(publishYear) from availability;
select avg(publishYear) from availability;
select 2021 - avg(publishYear) from availability;
```

```sql


-- you can use the where clause to *filter* results
-- you can use basic numerical and string operators:
-- >, <, <=, >= !=, = 
select * from game where gameId >= 10;
select * from game where gameId is null;
select * from game where gameId is not null;

-- you can also use string comparisons:
select * from game where name = "GTa 3";
-- you can also search for partial strings using the
-- wildcard % and the like clause
select * from game where name like "G%";
select * from game where name like "%a%";
select * from game where name like "%a";
-- you can use the underscore to match ONE single character
select * from game where name like "_a%";

-- you can combine conditions using
-- or, and, not
select * from game where gameId > 10 or name like "G%";

select * from game where gameId = 1 or gameId = 5 or gameId = 27;
select * from game where gameId in (1, 5, 27);
select * from game where gameId in (
  select gameId from availability 
);
```

## ORder by

```sql

-- order of columns and rows can be manipulated
select publisherId, name as title from game order by name desc;
-- order by publisherIds then by name, descending
select publisherId, name as title from game 
  order by publisherId asc, name desc;
```

## Data Projections

```sql

-- goal: I want to know how many games each publisher has published
select publisherId, count(*) from game group by publisherId;
-- remember we have some duplicates: Splatoon 3, Among use
select publisherId, count(distinct name) from game group by publisherId;
-- you can also use the having clause to further filter your results
select publisherId, count(distinct name) as numGames
  from game 
  where name > "L"
  group by publisherId
  having numGames >= 3;
-- where filters are applied BEFORE the projection
-- having filters are applied AFTER the projection
```

$$A \times B$$
$$\{a, b, c\} \times \{1, 2\} = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$$
$$\mathbb{R} \times \mathbb{R}$$

$$A \times B$$

$$R \subseteq A \times B = \{(a, b) | a < b\}$$

```text









```

  
  
