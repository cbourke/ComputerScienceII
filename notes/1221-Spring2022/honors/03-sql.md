# Databases & SQL
## CSCE 156H - Spring 2022

### Introduction/Overview

* Flat data files are insufficient for storing data
  * Repetition of data
  * Formatting issues
  * Consistency issues
  * Integrity issues
  * Organization problems: not sorted, it is in one big file (no multithreaded or multiuser capability)
  * There is no security!

### Solution: use a RDBMS (Relational Database Management System)
  * 1970 Edgar Codd
  * Key aspects: data is stored in *tables*
  * Data is stored in columns (fields)
  * Records are stored as rows
  * Each row may have a unique identifier: *primary key*, (PK)
  * Rows (records) in different tables may be related through *foreign Keys* (FK)
  * Foreign keys define relations between tables:
    * One-to-many relation: one record in table $A$ may be related to many records in table $B$
    * Many-to-one relation: just the opposite
    * Many-to-many relations: multiple records in table $A$ are related to multiple records in table $B$
    * One-to-one: not too common

## CRUD Demonstration

* You can interact with a RDBMS through CRUD:
  * Create (`insert`)
  * Retrieve (`select`)
  * Update (`update`)
  * Destroy (`delete`)

### C = Creating Data

* You use the `insert` keyword on an existing table to insert new data

```sql
insert into publisher (publisherId, name) values (101, "Bethesda");
insert into publisher (name) values ("Foo Games");

-- insert multiple records with one query:
insert into platform (name) values
  ("Vive"),
  ("Index"),
  ("Oculus Rift");
select * from platform;
```

* YOu can, but should generally *not* hardcode primary key values
* Key management is difficult, let the database do its job
* You *can* and *should* hardcode key values for initialization/test data
* You can also use nested queries:

```sql
insert into publisher (name) values ("Mojang");
insert into game (name, publisherId) values ("Minecraft",
  (select publisherId from publisher where name = "Mojang"));
```

### U = Update

* You can alter an *existing* record in a table using the `update` keyword
* Safe mode will prevent *most* queries that end up changing multiple records, gneerally you use a `where` clause to limit the scope of your changes

```sql

update game set name = "Pong II" where gameId = 3213123;
```

### D = Destroy Existing records

* to remove (no undo generally) records, use the `delete` query
* You should use a `where` clause

```sql

update game set publisherId = 8 where gameId = 9;

delete from availability where gameId = 12;
delete from game where gameId = 12;
delete from publisher where publisherId = 10;
```

### R = Retrieve Records

* To retrieve records, you `select from` a table
* Retrieve every column of every game record:
`select * from game;`
* The start operator `*` is a *wildcard*
* You can select a subset of columns by specifying them
`select publisherId, name as title from game;`

* You can use basic logic operators using a `where` clause:
  * Numerical comparisons, `<, >, <=, >=, =, !=`
  * Strict string comparisons
```sql
select * from game where name = "GTA";
-- fuzzy string comparisons using the like clause:
-- anything that begins with a G:
select * from game where name like "G%";
select * from game where name like "%s";
select * from game where name like "%s%";
select * from game where name like "% %";
select * from game where name like "___a%";
```
* The `%` matches any characters (zero or more)
* THe `_` matches any single character (any)
* REgular expressions: may not be interoperable

### Aggregate Functions

```sql
select count(*) as numberOfGames from game;

select count(*) as numPublishers from publisher;
select count(distinct publisherId) as numPublishers from game;

select 16 - count(distinct publisherId) as numPublishers from game;

select min(publishYear) from availability;
select max(publishYear) from availability;
select avg(publishYear) from availability;
```

$$A \times B$$

```text












```
