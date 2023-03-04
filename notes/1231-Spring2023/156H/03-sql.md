# Databases & SQL
## CSCE 156H - Spring 2023

### Introduction/Overview

* Flat data files are insufficient for storing data
  * Repetition of data
  * Formatting issues
  * Consistency issues: data anomalies
  * Integrity issues
  * Organization problems: not sorted, it is in one big file (no multithreaded or multiuser capability)
  * There is no security!

### Solution: use a RDBMS (Relational Database Management System)

* 1970 Edgar Codd (IBM)
* Key aspects: data is stored tables
* Tables have columns (pieces of data) and rows (records)
* Columns have a *type* and a *name* aka fields
* Each row in a table corresponds to a single record
* Records in a table are *uniquely* identified with a *primary key* (PK for short)
* Records in different tables are *related* to each other through *foreign keys* (FK)
* Foreign keys define relations between tables:
  * One-to-many relation: one record in table $A$ may be related to many records in table $B$
  * Many-to-one: same, but from the perspective of $B$
  * Many-to-many relation: one record in table $A$ can be related to many records in table $B$ and vice versa
  * One-to-one relation: be very suspicious of this

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

select * from game;
select name from game;
select gameId,name from game;
-- you can alias columns:
select gameId, name as gameTitle from game;

-- you can us a where clause to specify only certain records
select * from publisher;
select * from game where publisherId = 1;

select * from game where publisherId = 1 or publisherId = 5;
select * from game where publisherId > 1 and publisherId <= 10;

-- strings work too:
select * from game where name = "GTA";
select * from game where name != "GTA";

-- you can use a string wildcard: %
-- % matches any number of characters including zero
select * from game where name like "G%";
select * from game where name not like "% %";

-- the underscore is a *single character* wildcard: _
select * from game where name like "_a%";
select * from game where name like "%_a%";

```

## Aggregate Functions

* You can produce more data using aggregate functions: `min, max, avg, count, sum`
* You can also do basic math: `+, -, *, /`

```sql

select * from availability;

-- find the year in which the oldest game was published...
select min(publishYear) from availability;
-- newest:
select max(publishYear) from availability;
-- mix and match math:
select min(publishYear) * 10 + max(publishYear) from availability;
-- average age of any game in my database:
select count(*) as numberOfTitles from game;
select sum(publishYear) from availability;
select 52000/22;
-- the following will work:
select avg(publishYear) from availability;
```

## ORDER BY clause

* In general both columns and rows in a database are not ordered
* When you select data out, it may come in any order that the database feels is most efficient
* You can impose an ordering on the resulting data (result sets) using the `order by` clause

```sql

select * from game;
-- ascending is the default
select * from game order by name;
select * from game order by name asc;
-- descending:
select * from game order by name desc;

-- you can do a combination of columns:
select * from game order by publisherId desc, name asc;
```

# Misc

* The `distinct` keyword allows you to eliminate duplicates and results in unqiue values: `select distinct publisherId from game;`
* The `in` clause allows you to treat values or *another* query as a set

```sql


select * from game where publisherId = 1 or publisherId = 5 or publisherId = 8;
select * from game where publisherId in (1, 5, 8);
select * from game where publisherId in
  (select publisherId from publisher where name = "LucasArts" or name = "Valve");
```

## Data Projections

```sql

-- projections:
-- goal: find out how many games each publisher published
select count(*) from game;
select publisherId, count(gameId) as numberOfGames from game group by publisherId;
```

$$A = \{a, b, c\}, B = \{1, 2\}$$

$$A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$$

$$|A \times B| = |A| \cdot |B|$$

## Joins

* You can join two tables together using a `join` statement

$$R = \{(a, b) | a \text{ divides } b \}$$

$$(2, 4) \in R$$

$$(2, 5) \not\in R$$

```text











```
