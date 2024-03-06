# Databases & SQL
## CSCE 156H - Spring 2024

### Introduction/Overview

* Flat data files are insufficient for storing data
  * Bad Formatting
  * Inconsistency
  * NO types: int, double, strings, there are ONLY strings
  * Redundancy of data leading to potential failure points and data anomalies
  * Any processing requires processing the ENTIRE file: extremely inefficient: not sorted, not organized, etc. it is one on giant "BLOB" = "Binary Large OBject"
  * There are file lock issues: no concurrency
  * There is no security: one file is an all-or-nothing thing
  * No integrity of data: you can do anything to any record to any column (no protection of data)

### Solution: use a RDBMS (Relational Database Management System)

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

# CRUD Demonstration

* CRUD = Create Retrieve Update Destroy
* Four basic operations to any data collection
* You can interact with a RDBMS through CRUD:
    * Create (`insert`) - inserting new records into a table
    * Retrieve (`select`) - pull out records or data from a table
    * Update (`update`) - modifying currently existing records
    * Destroy (`delete`) - removing/deleting records in a table: THERE IS NO, ABSOLUTELY NO UNDO

## C = Create

* Use the `insert` keyword
* Specify the `table`, `(column,names)` and `values`
* You can insert one record at a time...
* You can insert multiple records in one query
* You can hardcode primary key values for *test* data (so you know the values when used as foreign keys)

```sql

-- hard code PK/FK values for test data ONLY!
insert into publisher(name,publisherId) values ("Mojang", 500);
insert into game (name,publisherId) values ("Minecraft", 500);

-- alternatively, but much more work, a nested query:
insert into availability
  (gameId,platformId,publishYear)
  values
  ( (select gameId from game where name = "Minecraft"),
  7, 2009);
```

* Child records cannot exist without a parent record (in a well designed database that is)
* These are foreign key *constraints*: a child cannot exist without a parent
  * A parent record must be created BEFORE the child record
  * Deletion must follow the reverse order

## U = Update

* You use the `update` keyword

```sql

insert into publisher (name) values ("Geerbox");

-- fix the spelling...
-- as it is, this would change EVERY publisher to "Gearbox"
update publisher set name = "Gearbox";
-- unsafe operations like this are prevented in "safe mode"
update publisher set name = "Gearbox" where publisherId = 501;


```

## D = Destroy

* You use the keyword `delete`

```sql

-- deletes EVERY record in the game table
-- DOES NOT delete the table itself
-- not allowed in safe mode :)
delete from game;

delete from availability where gameId = 24;
delete from game where gameId = 24;
delete from publisher where publisherId = 500;
```

## R = Retrieve

* You can pull data out of a database using the `select` statement
* To pull every single record and every single column value you can use a wildcard:

```sql
use cbourke3;

select * from platform;
select * from game;
select * from publisher;
select * from availability;

-- select all game records and all columns of the game table:
-- THe * is a wildcard: it matches EVERYTHING and ANYTHING
select * from game;

-- you can select a subset of columns:
select name from game;
select name,publisherId from game;
-- order does not matter:
select publisherId,name from game;
-- you can also rename (alias) columns temporarily
select publisherId,
       name as title
       from game;

-- aggregate functions can be used:
-- how many game records do we have?
select count(*) from game;
select count(*) as numberOfGames from game;
select count(*) as `number Of Games` from game;

-- others: min, max, sum, avg
select min(publishYear) from availability;
select max(publishYear) from availability;
select avg(publishYear) from availability;
-- glorified calculator:
select now() - (min(publishYear) -1970)*60*60*24*365.25 as age from availability;
select 40 * 4 / 3;

-- where clauses can limit your results
select * from game where gameId = 5;
select * from game where gameId != 5;
select * from game where gameId > 5;
select * from game where gameId >= 5;

-- you can also use strings:
select * from game where name = "Legend of Zelda";
select * from game where name <= "Legend of Zelda";

-- you can use partial string matching using more
-- wildcards
-- % will match any number of characters

-- select games that start with a G
select * from game where name like "G%";

-- select games that end with a s
select * from game where name like "%s";

-- combination of wildcards:
select * from game where name like "%The%";
select * from game where name like "S%a%";

-- another wildcard: _ matches ANY SINGLE character
select * from game where name like "_____";

select * from game where name like "S____%";

-- ordering

-- order doesn't matter (records or columns)
-- asc = ascending order (default)
-- desc = descending order
select * from game order by name;
select * from game order by name asc;
select * from game order by name desc;

-- order by a combination of fields
select * from game order by publisherId, name desc;

-- distinct keyword provides only unique records

select distinct publisherId from game;

-- in clause

select * from game where gameId in (5, 7, 10, 3);

-- or and and keywords:
select * from game where gameId = 5 or gameId = 7 or gameId = 10 or gameId = 3;
select * from game where gameId = 5 or (gameId > 10 and name like "S%");


-- you can order by multiple columns...
select * from game order by publisherId asc, name desc;

select * from publisher;


```

## Data Projections

* Set Theory: a set is an unordered collection of unique elements of *similar type*
* Math:
$$A = \{a, b, c\}$$
$$B = \{1, 2\}$$
$$x \in A$$
$$x \not\in A$$
$$ \overline{A}$$
  $$A \cup B$$
  $$A \cap B$$
$$A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$$
  $$|A| = 3, |B| = 2, |A \times B| = 6$$
$$R \subseteq A \times B$$
* $R$ is a *relation* on the cartesian product of $A \times B$
* A subset; elements are in the relation only if they match some criteria
* $R \subseteq \mathbb{R} \times \mathbb{R}$ such that $(x, y) \in R \iff x < y$
* Consider the "projection" of objects: 3D->2D


```sql

select count(*) from game;
select count(*) from publisher;
select 22 * 31;
select count(*) from game join publisher;

-- a basic "inner" join will join records from
-- table A to table B such that only combinations
-- that match the "on" predicate (clause) are true
select * from game
  inner join publisher
  on game.publisherId = publisher.publisherId;

-- syntactic sugar:
-- you can omit the 'inner'
-- you can use aliases to shorthand a lot
-- you can even omit the "as"
select * from game g
  join publisher p
  on g.publisherId = p.publisherId;

-- changing the order changes the order of the columns:
select * from publisher p
  join game g
  on g.publisherId = p.publisherId;

-- the order does not matter on the predicate
select * from publisher p
  join game g
  on p.publisherId = g.publisherId ;

-- observe: are all the publishers represented?
-- generally: an inner join does not preserve records that
-- have no matches (from A to B)
-- a left (outer) join does preserve recrods:
select * from publisher p
  left join game g
  on p.publisherId = g.publisherId ;

-- yes, there is a right join: it preserves records from B
-- to A: it is best to use left joins and reorder the
-- tables so you read left to right
select * from game g
  right join publisher p
  on p.publisherId = g.publisherId;

-- goal: determine how many games each publisher published...
select p.name as publisher,
  g.name as gameTitle
  from publisher p
  left join game g
  on p.publisherId = g.publisherId ;


-- first attempt: group by publisherId in the game table...
select * from game;
select publisherId,
  count(*) as numberOfGames
  from game group by publisherId;

select p.name as publisher,
  count(g.gameId) as numberOfGames
  from publisher p
  left join game g
  on g.publisherId = p.publisherId
  group by p.publisherId;

-- get a list of all games and their platforms...
select *
  from game g
  left join availability a on g.gameId = a.gameId
  left join platform p on a.platformId = p.platformId;

-- get a list of platforms and all of their games even those
-- that have no games...
select *
  from platform p
  left join availability a on p.platformId = a.platformId
  left join game g on g.gameId = a.gameId;


insert into platform (name) values ("Valve Index"), ("Playdate");
```

$$A \cup B$$
$$A \cap B$$

```sql


-- flatten the entire data model:
select
    p.name as publisher,
    g.name as title,
    a.publishYear as `Year Published`,
    platform.name as platform
  from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform on a.platformId = platform.platformId
union
select
    p.name as publisher,
    g.name as title,
    a.publishYear as `Year Published`,
    platform.name as platform
  from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform on a.platformId = platform.platformId;  
```

## Designing & Implementing a Database

* Demonstration: create a database to model the account (Asset, Annuity, Stock; Person (owners) and their emails)

```text









```
