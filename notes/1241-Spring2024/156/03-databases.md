# Databases & SQL
## CSCE 156 - Spring 2024

### Introduction/Overview

* Flat data files are insufficient for storing data
  * It is a terrible format to search or consolidate data
  * Files can only be processed by one process (program) at a time: file locks
  * Data is repeated unnecessarily
  * Consistency issues: data anomalies
  * Integrity issues: different ways of representing missing or non-existant data
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
* Records in different tables are *related* to each other through *foreign keys* (FKs)
* Records in *a* table are *uniquely* identified by *primary keys* (PKs)
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
    * Destroy (`delete`) - removing/deleting records in a table: THERE IS NO, ABSOLUTELY NO UNDO

## Getting Started

* You all have access to a database named after your canvas login (`cbourke3`) on a server named `cse-linux-01.unl.edu`
* Your password should have been sent to you via your huskers email this morning
* DO NOT use this password for anything else and if you change your password, then make sure it is NOT something you would normally use!
* If you lose it and/or need to change it: you'll need to contact `manager@cse.unl.edu`
* We recommend that you use MySQL Workbench to work with the SQL server: https://www.mysql.com/products/workbench/

## Demonstration

```sq
#-- always use your database
#-- named after your canvas login
use cbourke3;

-- you can show your tables
-- initially, you won't have any
show tables;

-- C = Create some publisher records...
insert into publisher (name) values ("Ubisoft");
insert into publisher (name) values ("Bandai-Namco");

-- you *can* specify a primary key (PK)
insert into publisher (publisherId,name) values (400, "Riot");
-- you generally only want to specify a PK if you are
-- inserting test data and need to have a fixed value as a
-- Foreign key
insert into game (publisherId,name) values (400, "Valorant");
insert into game (publisherId,name) values (400, "Leegue of Legands");

update game set name = "League of Legends" where gameId = 23;

-- this would delete EVERY record in the table
-- this does not delete the TABLE itself, only records
-- this is not possible in safe mode (thankfully)
delete from game;

-- want to delete the publisher "Riot" but we cannot becuase
-- it has referencing records (children)
delete from publisher where publisherId = 400;

-- you can delete the games FIRST...
delete from game where gameId = 23;
delete from game where gameId = 22;
-- now you can delete Riot:
delete from publisher where publisherId = 400;

-- R = Retrieve records

-- you use the keyword "select" and you *can* use the
-- "wildcard": *
select * from publisher;
select * from game;

-- you can be more specific and only query certain columns:
select name from game;
-- order of columns does not matter and can be changed:
select name,gameId from game;
-- you can also "rename" or "alias" columns for the query result
select name as title,
       gameId as ID
       from game;

-- Aggregate functions
-- you can do basic computations using SQL statements
select count(*) as numberOfGames from game;

-- you also have min, sum, max, avg, etc.
-- oldest game:
select min(publishYear) from availability;
-- newest game:
select max(publishYear) from availability;
-- average (not ave)
select avg(publishYear) from availability;
-- weird, but okay:
select sum(publishYear) from availability;
-- really really sophisticated calculator:
select 8 * 5 + 3 - (5 / 4);

-- you can limit results using a where clause
select * from game where gameId = 5;
select * from game where gameId > 5;
select * from game where gameId >= 5;
select * from game where gameId != 5;

-- string based queries
select * from game where name = "Legend of Zelda";
select * from game where name < "Legend of Zelda";
-- generally you want partial string matching...
-- wildcard(s): %
-- with the "like" clause
-- this will select any game that begins with "G"
select * from game where name like "G%";
-- this will select any game that ENDS with s
select * from game where name like "%s";
-- this will selct any game that has a q in its title:
select * from game where name like "%h%";

-- compound logic: "and" and "or" clauses
select * from game where name like "%the%" or name like "%The%";
--
select * from game where name like "G%" and gameId <= 2;

select * from game where (name like "G%" and gameId <= 2) or gameId = 7;

-- you can use the _ for single letter matches
select * from game where name like "%_he%";

select * from game where name like "%______%";

-- generally the order of rows (results) does not matter, but
-- you can order them using ASC (default) or DESC
select * from game order by name;
select * from game order by name desc;

-- you can order by multiple columns...
select * from game order by publisherId asc, name desc;

select * from publisher;
```

## Misc

* You can use `distinct` to only get unique values: `select distinct publisherId from game;`
* You can use the `in` clause to define a set

```sql
select * from game where gameId = 3 or gameId = 5 or gameId = 7;
select * from game where gameId in (3, 5, 7);
```

* Set theory: an unordered set is a collection of unique elements of a similar type
* Math:
  $$A = \{a, b, c\}$$
  $$B = \{1, 2\}$$
  $$x \in A$$
  $$x \not\in A$$
  $$ \overline{A}$$
  $$A \cup B$$
  $$A \cap B$$
  $$A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$$
  $$R \subseteq A \times B$$
* R forms a *relation*
* $(x,y) \in \mathbb{R} \times \mathbb{R}$

## Data Projections

* YOu can "project" data down from higher dimensions to lower dimenstions
* $(x, y, z) \rightarrow (x, y)$

```sql

-- get a total number of games published by each publisher...
select publisherId,
	   count(gameId) as numberOfTitles
       from game group by publisherId;

-- does that include publishers with no games?
-- no, nor does it even have their names!

use cbourke3;

select * from game;
select * from publisher;

select count(*) from game; -- 22 games
select count(*) from publisher; -- 31 publishers

-- blind/naive cross join: join every record in table
-- A to every record in table B!
select count(*) from game join publisher; -- 682 records

-- 10M game records, 10M publisher records
-- leads to 100 Trillion

-- an "inner" join from the game table to the publisher table
-- ON the publisherId: only records where the publisherId matches
-- will be in the result
select * from game
  inner join publisher
  on game.publisherId = publisher.publisherId;

-- clean up:
-- "inner" is not necessary
-- use aliases to not have to type the table name again...
-- aliases are so common, you can omit the "as" keyword
select * from game g
  join publisher p
  on g.publisherId = p.publisherId;

-- you can limit the columns you get back:
select g.name as gameTitle,
  p.name as publisher
  from game g
  join publisher p
  on g.publisherId = p.publisherId;

-- inner joins will OMIT any record from the first table
-- that does not match any record in the second table
-- alternative: you can use an OUTER LEFT JOIN to preserve
-- records from table A to table B

-- joining FROM the game table TO the publisher table
-- reuslts in the same result: you do not have orphan child records
select * from game
  left join publisher
  on game.publisherId = publisher.publisherId;

-- do a left join starting from the publisher table TO the game table
select * from publisher
  left join game
  on game.publisherId = publisher.publisherId;

-- yes you can do a right join to preserve records from B
-- to A: just use left joins and keep it simple, no need
-- to read right-to-left
select * from game
  right join publisher
  on game.publisherId = publisher.publisherId;

-- use everything we've covered to get a proper report...
-- how many games has each publisher published?
select p.name as publisherName,
  count(g.gameId) as numberOfGames
  from publisher p
  left join game g
  on p.publisherId = g.publisherId
  group by p.publisherId;
```

$$A \cap B$$
$$A \cup B$$

```sql

select * from publisher;
select * from game;

select publisherId, count(*) from
  game group by publisherId;

select p.name as publisherName,
  count(g.gameId) as numberOfTitles
  from publisher p
  left join game g
  on p.publisherId = g.publisherId
  group by p.publisherId;

-- flatten the entire data model...
select p.name as publisher, g.name as title, a.publishYear, plat.name as platform
  from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId
union
select p.name as publisher, g.name as title, a.publishYear, plat.name as platform
  from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform plat on a.platformId = plat.platformId;

```

## Designing & Implementing a Database

* Create a database to model the asset java classes/problem (Asset, Annuity, Stock, Person (owner), Email(s) of a person, etc.)


```sql
use cbourke3;

drop table if exists Ownership;
drop table if exists Email;
drop table if exists Person;
drop table if exists Asset;

create table if not exists Person (
  personId int primary key not null auto_increment,
  firstName varchar(255), -- allow this to be null
  lastName varchar(255) not null,
  dateOfBirth varchar(10) not null default "0000-00-00"
);  

create table if not exists Email (
  emailId int primary key not null auto_increment,
  address varchar(255) not null,
  personId int not null,
  foreign key (personId) references Person(personId)
);



create table if not exists Asset (
  assetId int primary key not null auto_increment,
  type varchar(1) not null, -- A = Annuity, S = Stock; TODO: prevent bad data by only allowing A and S...
  -- Annuity columns:
  terms int,
  monthlyPayment double,
  -- Stock columns:
  numberOfShares double,
  sharePrice double
);

create table if not exists Ownership (
  ownershipId int primary key not null auto_increment,
  personId int not null,
  assetId int not null,
  foreign key (personId) references Person(personId),
  foreign key (assetId) references Asset(assetId)
);

insert into Person (personId, firstName,lastName) values
  (123, "Chris", "Bourke"),
  (456, "Cory", "Bellinger"),
  (789, "Craig", "Counsel");


insert into Email (address,personId) values
  ("chris.bourke@unl.edu", 123),
  -- ("cbellinger@cubs.com", 456),
  ("craig@brewers.com", 789),
  ("ccounsel@cubs.com", 789);

insert into Asset (assetId,type,terms,monthlyPayment) values
  (1, "A", 5, 500),
  (2, "A", 10, 150.25);

insert into Asset (assetId,type,numberOfShares,sharePrice) values
  (3, "S", 10, 450.00),
  (4, "S", 20.5, 0.01);

insert into Ownership (personId,assetId) values
  (123, 1),
  (123, 2),
  (123, 3),
  (123, 4);
-- select * from Person p join Email e on e.personId = p.personId;



```

```text









```
