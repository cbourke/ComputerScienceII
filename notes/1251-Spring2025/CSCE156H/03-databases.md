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
* MOre: a relation between two sets, $A, B$ is a subset:
  $$R \subseteq A \times B$$
  * Ex: let $R$ be a relation on $\mathbb{Z}$ and $\mathbb{Z}$ such that $(a, b) \in R$ if and only if $a \leq b$
  * $(5, 10) \in R$, $(5, 5) \in R$, $(10, 5) \not\in R$
* Set operations
  * $A \cup B$ (or, union)
  * $A \cap B$ (and, intersection)

```sql
use cbourke3;

-- problem: who are the publishers?
select count(*) from publisher;
-- problem: what about publishers with no games?
select count(*) from game;

select publisherId, count(*) as numberOfGames from game group by publisherId;

-- "blind" cross join
select count(*) from game
  join publisher;

-- make a join based on the *relation*
-- you do this using an "on" clause:
select * from game
  join publisher
  on game.publisherId = publisher.publisherId;

-- short cuts: alias table names
select * from game g
  join publisher p
  on g.publisherId = p.publisherId;

select p.name as publisherName, count(*) as numberOfGames
  from game g
  join publisher p on g.publisherId = p.publisherId
  group by p.publisherId;

-- left joins preserve records from table A to table B:
select * from publisher p
  left join game g
  on p.publisherId = g.publisherId;

select p.name as publisherName, count(g.gameId) as numberOfGames
  from publisher p
  left join game g on p.publisherId = g.publisherId
  group by p.publisherId;

-- yes, right joins exist:
-- but, who reads left to right?
select * from game g
  right join publisher p
  on g.publisherId = p.publisherId;

-- however, sometimes it is necessary...
-- flatten the entire data model
select * from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId
union
select * from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform plat on a.platformId = plat.platformId;

select * from platform;

-- insert data with special characters:
insert into game (name, publisherId) values ('️Luigi\'s Revenge', 5);

-- create a report that details: for each platform, how many games
-- are available on that platform
-- but only if they have a substantial (more than 3) games on them...
select p.name as platform_name, count(a.gameId) as number_of_games from platform p
  left join availability a on p.platformId = a.platformId
  group by p.platformId
  having number_of_games > 3;

-- recall that you can determine the oldest game:
select min(publishYear) from availability;

select * from game g
  join availability a on g.gameId = a.gameId
  where a.publishYear = (select min(publishYear) from availability);

select * from game g
  join availability a on g.gameId = a.gameId
  where a.publishYear = (select max(publishYear) from availability);

-- hardcoding values is okay for test data, but not real queries

-- stuff that is too long: error
insert into game (name, publisherId) values ('Lorem ipsum odor amet, consectetuer adipiscing elit. Tristique dictumst nam felis consequat rhoncus aliquam rhoncus etiam. Tincidunt sapien lacinia conubia suspendisse eleifend dis, odio tempor. Quisque tempor scelerisque purus risus etiam porta. Consectetur scelerisque convallis amet a taciti rutrum. Dis hac a hac facilisis interdum nibh dignissim sit porttitor.

Semper nascetur suspendisse conubia scelerisque posuere magna mi a. Elementum tortor libero lacinia non donec odio feugiat luctus. Lobortis finibus hac praesent augue phasellus metus venenatis ante orci. Id libero dictum curae lobortis velit taciti. Neque mattis penatibus placerat quam pulvinar porttitor dictum. Praesent pellentesque sit ligula tristique finibus nibh. Vivamus duis porta; blandit porttitor suspendisse phasellus odio pellentesque netus. Sollicitudin scelerisque vulputate dis quam ante nisi finibus volutpat. Conubia curabitur potenti enim est vitae non!

Purus dolor posuere cubilia conubia efficitur dictum a magnis dui. Nam faucibus eros urna tempus sit ex ut facilisi id. Rutrum bibendum erat sapien torquent montes hendrerit maximus. Libero iaculis dui sit sem habitant varius turpis laoreet. Duis varius adipiscing maecenas diam risus morbi malesuada fames. Turpis cras in at parturient conubia donec! Fermentum odio leo sodales urna placerat varius maecenas. Luctus maximus dui condimentum quis pellentesque feugiat habitasse. Conubia facilisis vel enim luctus nisi dignissim tellus.

Donec diam augue porttitor ultricies phasellus. Dictumst sem nunc penatibus lorem blandit consectetur maximus tortor ultrices. Imperdiet consectetur duis ullamcorper ultricies purus curabitur. Curabitur pharetra fusce egestas porttitor litora curae. Id facilisis duis duis bibendum convallis dictumst. Maecenas nostra mi sociosqu luctus cubilia pretium. Sed litora purus accumsan porttitor ullamcorper lacinia. Sit tincidunt nam dictumst proin convallis aliquam. Vitae vestibulum justo ullamcorper consectetur ornare eleifend. Per posuere cubilia natoque praesent bibendum posuere luctus cubilia duis.

Ac aliquam ante quisque habitant lobortis facilisis. Convallis tellus ultrices consequat cubilia fames sit nascetur iaculis parturient. Ornare auctor senectus natoque augue; pharetra auctor. Risus senectus nec fringilla scelerisque ornare quis tincidunt sociosqu. Dignissim imperdiet ut dolor facilisis potenti ante. Curabitur neque consequat mattis euismod ultricies potenti lobortis. Donec luctus leo integer eleifend potenti turpis est consectetur finibus. Eleifend a orci parturient sodales mus accumsan ipsum pretium.', 10);

-- careful: not truncation, not an error, rounds for some reason
insert into game (name, publisherId) values ('Mario\'s Revenge', 5.2);

select * from game;

insert into game (name) values ('Mario\'s Revenge', 5, 'bar');

-- reset your password
-- set password=password('1234');
-- drop database cbourke3;
-- create database cbourke3;




use cbourke3;

-- problem: who are the publishers?
select count(*) from publisher;
-- problem: what about publishers with no games?
select count(*) from game;

select publisherId, count(*) as numberOfGames from game group by publisherId;

-- "blind" cross join
select count(*) from game
  join publisher;

-- make a join based on the *relation*
-- you do this using an "on" clause:
select * from game
  join publisher
  on game.publisherId = publisher.publisherId;

-- short cuts: alias table names
select * from game g
  join publisher p
  on g.publisherId = p.publisherId;

select p.name as publisherName, count(*) as numberOfGames
  from game g
  join publisher p on g.publisherId = p.publisherId
  group by p.publisherId;

-- left joins preserve records from table A to table B:
select * from publisher p
  left join game g
  on p.publisherId = g.publisherId;

select p.name as publisherName, count(g.gameId) as numberOfGames
  from publisher p
  left join game g on p.publisherId = g.publisherId
  group by p.publisherId;

-- yes, right joins exist:
-- but, who reads left to right?
select * from game g
  right join publisher p
  on g.publisherId = p.publisherId;

-- however, sometimes it is necessary...
-- flatten the entire data model
select * from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId
union
select * from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform plat on a.platformId = plat.platformId;

select * from platform;

-- insert data with special characters:
insert into game (name, publisherId) values ('️Luigi\'s Revenge', 5);

-- create a report that details: for each platform, how many games
-- are available on that platform
-- but only if they have a substantial (more than 3) games on them...
select p.name as platform_name, count(a.gameId) as number_of_games from platform p
  left join availability a on p.platformId = a.platformId
  group by p.platformId
  having number_of_games > 3;

-- recall that you can determine the oldest game:
select min(publishYear) from availability;

select * from game g
  join availability a on g.gameId = a.gameId
  where a.publishYear = (select min(publishYear) from availability);

select * from game g
  join availability a on g.gameId = a.gameId
  where a.publishYear = (select max(publishYear) from availability);

-- hardcoding values is okay for test data, but not real queries

-- stuff that is too long: error
insert into game (name, publisherId) values ('Lorem ipsum odor amet, consectetuer adipiscing elit. Tristique dictumst nam felis consequat rhoncus aliquam rhoncus etiam. Tincidunt sapien lacinia conubia suspendisse eleifend dis, odio tempor. Quisque tempor scelerisque purus risus etiam porta. Consectetur scelerisque convallis amet a taciti rutrum. Dis hac a hac facilisis interdum nibh dignissim sit porttitor.

Semper nascetur suspendisse conubia scelerisque posuere magna mi a. Elementum tortor libero lacinia non donec odio feugiat luctus. Lobortis finibus hac praesent augue phasellus metus venenatis ante orci. Id libero dictum curae lobortis velit taciti. Neque mattis penatibus placerat quam pulvinar porttitor dictum. Praesent pellentesque sit ligula tristique finibus nibh. Vivamus duis porta; blandit porttitor suspendisse phasellus odio pellentesque netus. Sollicitudin scelerisque vulputate dis quam ante nisi finibus volutpat. Conubia curabitur potenti enim est vitae non!

Purus dolor posuere cubilia conubia efficitur dictum a magnis dui. Nam faucibus eros urna tempus sit ex ut facilisi id. Rutrum bibendum erat sapien torquent montes hendrerit maximus. Libero iaculis dui sit sem habitant varius turpis laoreet. Duis varius adipiscing maecenas diam risus morbi malesuada fames. Turpis cras in at parturient conubia donec! Fermentum odio leo sodales urna placerat varius maecenas. Luctus maximus dui condimentum quis pellentesque feugiat habitasse. Conubia facilisis vel enim luctus nisi dignissim tellus.

Donec diam augue porttitor ultricies phasellus. Dictumst sem nunc penatibus lorem blandit consectetur maximus tortor ultrices. Imperdiet consectetur duis ullamcorper ultricies purus curabitur. Curabitur pharetra fusce egestas porttitor litora curae. Id facilisis duis duis bibendum convallis dictumst. Maecenas nostra mi sociosqu luctus cubilia pretium. Sed litora purus accumsan porttitor ullamcorper lacinia. Sit tincidunt nam dictumst proin convallis aliquam. Vitae vestibulum justo ullamcorper consectetur ornare eleifend. Per posuere cubilia natoque praesent bibendum posuere luctus cubilia duis.

Ac aliquam ante quisque habitant lobortis facilisis. Convallis tellus ultrices consequat cubilia fames sit nascetur iaculis parturient. Ornare auctor senectus natoque augue; pharetra auctor. Risus senectus nec fringilla scelerisque ornare quis tincidunt sociosqu. Dignissim imperdiet ut dolor facilisis potenti ante. Curabitur neque consequat mattis euismod ultricies potenti lobortis. Donec luctus leo integer eleifend potenti turpis est consectetur finibus. Eleifend a orci parturient sodales mus accumsan ipsum pretium.', 10);

-- careful: not truncation, not an error, rounds for some reason
insert into game (name, publisherId) values ('Mario\'s Revenge', 5.2);

select * from game;

insert into game (name) values ('Mario\'s Revenge', 5, 'bar');

-- reset your password
-- set password=password('1234');
-- drop database cbourke3;
-- create database cbourke3;




```

## Designing & Implementing a Database

* Create a database to model the asset java classes/problem (Asset, Annuity, Stock, Person (owner), Email(s) of a person, etc.)


```text










```
