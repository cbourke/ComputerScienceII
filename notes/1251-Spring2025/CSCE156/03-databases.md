# Computer Science II - CSCE 156
## Spring 2025
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
* Your password should have been sent to you via your huskers email this morning or Friday, look at it, use it, save it.
* DO NOT use this password for anything else and if you change your password, then make sure it is NOT something you would normally use!
* If you lose it and/or need to change it: you'll need to contact `manager@cse.unl.edu`

## Basic CUD

```sql

-- always begin by using your database:
use cbourke3;

-- show the tables in the database:
show tables;

-- describe a table
describe game;

-- CREATING Records
-- general form:
-- insert into TABLE_NAME (colume names) values (VALUES, BY, COMMA);
insert into publisher (publisherId, name) values (14, 'Foo');
-- better to let the database generate your IDs for you:
insert into publisher (name) values ('Bandai');
insert into publisher (name) values ('303 Games');

-- insert a game record:
-- a publisher record must exist first
-- there is a parent-child relationship
-- option 1: hardcord the value (you have to look it up first!)
insert into game (name, publisherId) values ('Terreria', 16);
-- option 2: use a nested subquery
insert into game (name, publisherId) values ('Terreria 2',
  (select publisherId from publisher where name = '303 Games'));

-- insert a bad record that needs to be fixed
-- playstuck -> playstack
insert into publisher (name) values ('playstuck');
--
update publisher set name = 'playstack' where publisherId = 17;

-- destroy records
-- delete ALL records in the table publisher:
-- it does NOT delete the table itself
delete from publisher;
-- order matters: child records (and grandchild) records must be
-- deleted before the parent record can be deleted
delete from publisher where publisherId = 17;

-- delete 303's games first:
delete from game where publisherId = 16;
delete from publisher where publisherId = 16;

-- select all records from each table:
select * from game;
select * from publisher;


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

## Basic R = Retrieve (`select`)

### MATH!

* everything in a *relational* database words on set theory
* $A = \{a, b, c\}$
* $a \in A$ "the element $a$ is in the set $A$"
* Brackets are used in set theory (math) and parentheses are used in SQL
* $A = \{a, b, c\}, B = \{1, 2\}$
* What is $A \times B = \{(a, b) | a \in A \wedge b \in B\}$
* $A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$
* Cartesian product or "cross product" or in SQL: "(cross) join"
* $(x, y) \in \mathbb{R} \times \mathbb{R}$: caresian plane

### Demo

```sql
use cbourke3;

describe game;

-- blind, select all records and all columns:
select * from game;

-- you can limit the columns using named columns:
select gameId, name from game;

-- you can also alias columns:
select gameId, name as gameTitle from game;

-- note on style:
-- old style: SELECT gameId FROM game;
-- white space doesn't matter:
-- beautifier:
select
  gameId,
  name as gameTitle
from
  game;

-- Aggregate Functions
-- count(), max, min, avg
select count(*) as numberOfGames from game;
select min(publishYear) from availability;
select max(publishYear) from availability;
select avg(publishYear) from availability;
-- any calculation can be performed
select 8 * 4 - 3 / 8 + 3.5;
select avg(publishYear) * 10 from availability;
-- careful: hold down the shift key:
select 8 from game;

-- you can also limit the results use where clauses and logic operators
select * from game where gameId = 5;
select * from game where gameId != 5;
select * from game where gameId > 5;
select * from game where gameId >= 5;

-- you can use compound operators:
select * from game where gameId = 5 or (gameId >= 17 and gameId != 18);

-- string matching:
select * from game where name = 'Portal';
-- generally prefer single quotes though
select * from game where name = "Portal";
select * from game where name >= "Portal";

-- partial string matching using the string wildcard: %
-- you have to use like with wildcards:
-- all games that begin with a 'G':
select * from game where name like 'G%';
-- all games that have a lower case a in them:
select * from game where name like '%a%';
-- all queries are case sensitive (* IF the database is setup sensibly)
select * from game where name like '%A%';
-- get any game with a or A in it:
select * from game where name like '%A%' or name like '%a%';

-- you can also match single characters using the underscore: _
select * from game where name like '%__e%';
select * from game where name like '___';

-- Ordering
-- generally the order of columns AND rows does not matter
-- you can rearrange columns using named columns:
select name, gameId from game;
-- you can order rows: ascending is the default
select * from game order by name;
-- use DESC for descending:
select * from game order by name desc;
-- you an use a combination of columns as well:
select * from game order by publisherId asc, name desc;

-- you can use the distinct key word to get unique results:
select distinct publisherId from game;

-- you can use the "in" clause to avoid complex disjunctions (ORs)
select * from game where gameId = 3 or gameId = 5 or gameId = 7;
select * from game where gameId in (3, 5, 7);
select * from game where publisherId in (select publisherId from game where name like 'G%');

-- data projections:
-- 3D cube and project it down onto the plane: 2D square
-- projecting 3 columns down to 2

-- you can project data by using the group by clause...
-- grouping by all the publishers
select * from game group by publisherId;
-- you need to use an aggregate function to make sense of it:
select publisherId, count(*) as numberOfGames from game group by publisherId;

select count(*) from game join game g2;
```

* More math
  * A binary relation between two sets $A, B$ is a subset:
    $$R \subseteq A \times B$$
  * For example: Let $R$ be a relation on the sets $\mathbb{Z}$, $\mathbb{Z}$ defined by $a \leq b$ for $(a, b) \in R$
  * ex: $(5, 10) \in R$?
  * ex: $(5, 5) \in R$?
  * ex: $(10, 5) \not\in R$?
* Consider two sets: $A, B$
  * $A \cup B$ (union)
  * $A \cap B$ (intersection)

```sql
use cbourke3;

-- problem: who are the publishers?
select count(*) from publisher;
-- problem: what about publishers with no games?
select count(*) from game;

select publisherId, count(*) as numberOfGames from game group by publisherId;

-- blind cross join: A x B, not what you want
select * from game
  join publisher;

-- you only want *related* records
-- add an "on" clause (inner join)
select * from game
  join publisher
  on game.publisherId = publisher.publisherId;

-- you can short hand joins using aliases for tables:
select * from game g
  join publisher p
  on g.publisherId = p.publisherId;

select p.name as publisherName, count(*) as numberOfGames
  from game g
  join publisher p on g.publisherId = p.publisherId
  group by p.publisherId;

-- outer (left) join: you use this when you want to
-- preserve records from the *left*
select * from publisher p
  left join game g
  on g.publisherId = p.publisherId;

select p.name as publisherName, count(g.gameId) as numberOfGames
  from publisher p
  left join game g
  on g.publisherId = p.publisherId
  group by p.publisherId;

-- is there a right join?
-- Yes! But it forces you to read in reverse
-- sometimes you need though...
select p.name as publisherName, count(g.gameId) as numberOfGames
  from game g
  right join publisher p on g.publisherId = p.publisherId
  group by p.publisherId;

-- flatten the entire data model...
select * from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId
union
select * from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform plat on a.platformId = plat.platformId;


-- write a query to determine how many games were published on each
-- platform
select p.name as platformName, count(a.gameId) as numberOfGames from platform p
  left join availability a on p.platformId = a.platformId
  group by p.platformId;

-- find more info about the oldest game...
select min(publishYear) from availability;

select * from game g
  join availability a on g.gameId = a.gameId
  where a.publishYear = (select min(publishYear) from availability);

-- hardcoding values is OKAY for test data or mock data, but not
-- for actual queries

select * from game g
  join availability a on g.gameId = a.gameId
  where a.publishYear = (select max(publishYear) from availability);

insert into game (name, publisherId) values ('Assassin\'s Creed II', 10);
insert into game (name, publisherId) values ("Assassin's Creed II", 10);
```

## Designing & Implementing a Database

* Create a database to model the asset java classes/problem (Asset, Annuity, Stock, Person (owner), Email(s) of a person, etc.)


```sql
use cbourke3;

drop table if exists Account;
drop table if exists Email;
drop table if exists Person;

-- order matters!

-- Represents a person
create table if not exists Person (
  personId int not null primary key auto_increment,
  uuid varchar(36) not null unique key,
  firstName varchar(255) not null,
  middleName varchar(255),
  lastName varchar(255) not null
);

-- Represents a person's email(s)
-- There is a one to many relation from person to email
create table if not exists Email (
  emailId int not null primary key auto_increment,
  address varchar(255) not null,
  personId int not null,
  foreign key (personId) references Person(personId)
);

-- TODO: consider supporting a SSN (YAGNI?)
-- TODO: consider an address (what relation to person?)

create table if not exists Account (
  accountId int not null primary key auto_increment,
  accountNumber varchar(36) unique key,
  personId int not null,
  accountType varchar(1) not null,
  -- TODO: consider a constraint to force it to be 'S' = Stock, 'A' = Annuity

  -- Annuity
  monthlyPayment double,
  termYear int,

  -- Stock
  name varchar(255),
  sharePrice double,
  numShares double,
  constraint `onlyAorS` check (accountType = 'S' or accountType = 'A'),
  constraint `validData` check (
    (monthlyPayment is not null and termYear is not null) or
    (name is not null and sharePrice is not null and numShares is not null and numShares >= 0) ),
  foreign key (personId) references Person(personId)
);

-- insert some test data
-- insert 3 people with 0, 1, 2 emails respectively
insert into Person (personId,uuid,firstName,lastName) values
 (10,'38a53d55-72ba-4adf-bc34-d079684a2637','John','Smith'),
 (20,'7be1a7f1-38ba-4b9c-b54e-fbd18cdc1d5b','Jane','Smith'),
 (30,'9849a291-d1e0-4cbe-b11c-0d31ad94e147','Craig','Counsell');

insert into Email (address,personId) values
  ('jsmith@gmail.com', 20),
  ('ccounsell@cubs.com', 30),
  ('oldemail@brewers.com', 30);

select * from Person p
  left join Email e on p.personId = e.personId;

-- insert some account test data:
-- at least 2 of each type, such that person 3 owns 2, person 2 owns 1 and person 1 owns zero
-- TODO: use a tool!
```

```text










```
