# Computer Science II - CSCE 156H
## Spring 2025
### Databases & SQL

### Introduction/Overview

* Programs are short-lived, data is not
* We need a way to *persist* (save) data
* Up to now: flat files (CSV) or structured files (JSON, XML)

#### Flat Files

* It is a terrible format to search or consolidate data
* Files can only be processed by one process (program) at a time: file locks
* Data is repeated unnecessarily
* Consistency issues: data anomalies
* Integrity issues: different ways of representing missing or non-existant data
* There is no enforcmeent of rules: if you expected a `double` (GPA) there is nothing stopping you from entering "hello"
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


```sql
-- get started by using your database
use cbourke3;

-- you can show tables:
show tables;

-- describe individual tables
describe game;

-- if you *really* want to you can change it:
-- set password=password('new password');

-- inserting data
-- ... with manual key management (hardcoding the key value)
--     this is OKAY (preferable) with mock/test data
insert into publisher (publisherId, name) values (100, 'From Software');
-- without a publisher id, the auto_increment takes care of it for you:
insert into publisher (name) values ('To Software');

-- insert a game
insert into game (name, publisherId) values ('Elden Ring', 100);
-- alternative:
insert into game (name, publisherId) values ('Elden Ring 2',
  (select publisherId from publisher where name = 'From Software') );

-- fails:
insert into game (name, publisherId) values ('Elden Ring 3', 1234);

-- updating records
insert into publisher (name) values ('EA Spirits');

-- update that one record...
-- not allowed in safe mode, a WHERE clause is required...
update publisher set name = 'EA Sports';
-- with a where clause:
update publisher set name = 'EA Sports' where publisherId = 102;
-- alternative: only primary or foreign keys are allowed in safe mode
update publisher set name = 'EA Sports' where name = 'EA Spirits';

-- delete
-- delete ALL records from the publisher table, does NOT delete the table itself
delete from publisher;
--
delete from publisher where publisherId = 102;
delete from publisher where publisherId = 101;

-- you must delete the publisher's games before the publisher:
delete from game where publisherId = 100;
delete from publisher where publisherId = 100;

-- get crazy:
drop database cbourke3;
create database cbourke3;
use cbourke3;
show tables;
create database awesomedb;


-- insert multiple values at once
insert into publisher (name) values
  ('ABC'),
  ('DEF');

-- you can use a more vertical style:  
select gameId, publisherId, name as title from game;
-- vertical beautified:
select
  gameId,
  publisherId,
  name as title
from
  game;
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
* Style:
  * You can break up long lines
  * Use modern `lowercase` for SQL keywords (save your pinkie)

## Basic R = Retrieve (`select`)

```sql
use cbourke3;

-- blind, select all records and all columns:
select * from game;

-- select only a subset of columns:
select gameId, name from game;

-- order of columns does not matter:
select name, gameId from game;

-- you can use an alias to rename the column *in the result*:
select name as gameTitle, gameId from game;

-- aggregate functions
select count(*) from game;
select count(*) as numberOfGames from game;
-- more: min, max, avg
-- find the oldest game:
select min(publishYear) from availability;
-- find the newest game:
select max(publishYear) from availability;
select avg(publishYear) from availability;

-- lexicographically it works:
select max(name) from game;
select min(name) from game;

-- you can turn your $10M database into a nice calculator:
select 8 * 4 - 1 + 5 / 9;
-- you can use it in combination with queries:
select avg(publishYear) * 3.5 from availability;
select avg(publishYear) * max(publishYear) from availability;
-- careful:
select 8 from game;

-- you can limit results using where clauses:
select * from game where gameId = 7;
select * from game where gameId != 7;
select * from game where gameId >= 7;
select * from game where gameId > 7;

-- you can use compound logic: or and (higher precedence)
-- use parentheses as necessary
select * from game where (gameId = 7 or gameId < 3) and gameId != 2;

-- string matching:
select * from game where name = 'Portal 2';
select * from game where name = 'portal 2';
-- everything is lexicographic AND case sensitive
select * from game where name >= 'Portal 2';
select * from game where name <= 'A'; -- case insensive?

-- check the collation for <, <=, >, >=
select * from game where name <= 'm'; -- case insensive?

-- partial string matching using the string wildcard: %
-- you have to use like with wildcards:
select * from game where name like 'G%';
select * from game where name like '%e%';
-- it IS case sensitive
select * from game where name like '%E%';
-- % matches 0 or more of ANY characters
select * from game where name like '%C%';

-- single character wildcard: _
select * from game where name like '_o%';
select * from game where name like '___o%';

-- Ordering
-- generally the order of columns AND rows does not matter
-- ascending is the default (lexicographic or otherwise)
select * from game order by publisherId;
select * from game order by name;
-- you can reverse it into descending using desc
select * from game order by name desc;
-- you can do a combination of columns:
select * from game order by publisherId asc, name desc;

-- you can use the distinct key word to get unique results:
select distinct publisherId from game;

-- you can use the "in" clause to avoid complex disjunctions (ORs)
select * from game where gameId = 3 or gameId = 5 or gameId = 17;
select * from game where gameId in (3, 5, 17);
select * from game where gameId in (select gameId from game where publisherId > 7);


-- data projection:
select * from game;
select publisherId, count(*) as numberOfTitles from game group by publisherId;

select * from publisher;

select * from game join game g2;
```

### Math!

* Basic set theory
* Set inclusion:
  * $a \in A$ ("the element $a$ is in the set $A$")
  * $a \not\in A$
  * in SQL: you use the `in` keyword instead
  * in SQL: you use parentheses instead of square brackets
  * $A = \{a, b, c\}$
  * $(a, b, c)$
* Suppose you ahve two sets: $A = \{a, b, c\}$, $B = \{1, 2\}$
  * THe cartesian product (cross product):
  * $A \times B = \{(a, b) | a \in A \wedge b \in B\}$
  * $A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$
  * $|A| = 3, |B| = 2$
  * In general, if $|A| = n, |B| = m$, you have $n\cdot m$ elements in $A \times B$
  * In SQL these are called "joins"
* What about projections in math?
  * Suppose you ahve a 3D cube and you "project" it down onto a 2D plane
  * You have a square
  * $(x, y, z) \rightarrow (x, y)$



```text










```
