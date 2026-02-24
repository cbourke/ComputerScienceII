# Computer Science II - CSCE 156H
## Spring 2026
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
* There is no enforcment of rules: if you expected a `double` (GPA) there is nothing stopping you from entering "hello"
* Organization problems: not sorted
* There is no security
* Formatting issues
* There are no types: an integer, string, double, etc.: there are only strings

### Solution: use a RDBMS (Relational Database Management System)

* $A = \{a, b, c\}, B = \{1, 2\}$
* $A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$ (cross product)
* $R \subseteq A \times B$ (relation)

* 1970 Edgar Codd (IBM)
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
* Your password should be sent this week
* DO NOT use this password for anything else and if you change your password, then make sure it is NOT something you would normally use!
* If you lose it and/or need to change it: you'll need to contact `manager@cse.unl.edu`

## Basic CRUD

### Getting Started

## Basic CUD

```sql
use `c-cbourke3`;

# show the tables that exist:
show tables;

select * from game;
select * from publisher;

# creating records
# publisher record must be inserted first
insert into publisher (name) values ('epik');
insert into game (gameId,name,publisherId) values (101,'Fortnite',17);
# you can use subqueries to avoid hard-coded values:
insert into game (name,publisherId) values ('Fortnite 2',
  (select publisherId from publisher where name = 'epik'));
# you can insert multiple records:
insert into game (name,publisherId) values
  ('Rocket League', 17),
  ('Rocket League 2', 17);

# update records
update publisher set name = 'Epic' where publisherId = 17;

# destroy records
# order matters: child records must be deleted before parent records
delete from publisher where publisherId = 17;
delete from game where gameId = 104;

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

```sql


# Basic retrieval
# the * is a wildcard: it matches ALL of the columns
select * from game;
# you can select a subset of columns by naming them:
select name from game;
select name,gameId from game;
# you can also "alias" any column:
select
  name as title,
  gameId
from
  game;

# stay modern: use all lower case
# old school:
SELECT * FROM game;

# You can use aggregates:
select count(*) as numberOfTitles from game;
select min(publishYear) from availability;
select max(publishYear) from availability;
select avg(publishYear) from availability;
# you can do math!
select 8 * 5 - 4 + 3;
# range
select max(publishYear) - min(publishYear) from availability;
# forgot the shift key:
select 8 from publisher;

```

## More Retrieve

```sql

```





```text













```
