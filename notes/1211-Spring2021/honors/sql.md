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

# Database Design

* Design a database to model data on films, actors, directors

```sql
use cbourke;

drop table if exists FilmActor;
drop table if exists Actor;
drop table if exists Film;
drop table if exists Director;

create table if not exists Director (
  directorId int not null primary key auto_increment,
  lastName varchar(255),
  firstName varchar(255)
);

-- this table models films
create table if not exists  Film (
  filmId int not null primary key auto_increment,
  title varchar(255) not null,
  eidr varchar(255) not null unique key,
  runTime int,
  releaseDate varchar(10) default "0000-00-00",
  directorId int not null,
  foreign key (directorId) references Director(directorId)
);

insert into Director (directorId, firstName, lastName) values
  (1, "Quentin", "Tarantino"),
  (2, "J.J.", "Abrams"),
  (3, "David", "Lynch");
  
insert into Film (filmId,title,eidr,directorId) values 
  (1, "Eraserhead", "xyz", 3),
  (2, "Hateful Eight", "abc", 1),
  (3, "Star Trek 90210", "abd", 2);

select * from Film f 
  join Director d on f.directorId = d.directorId;

create table if not exists Actor (
  actorId int not null primary key auto_increment,
  lastName varchar(255),
  firstName varchar(255)
);

insert into Actor (actorId, firstName, lastName) values
  (1, "Samuel", "Jackson"),
  (2, "Chris", "Pine"),
  (3, "Kurt", "Russell");

create table if not exists FilmActor (
  filmActorId int not null primary key auto_increment,
  actorId int not null,
  filmId int not null,
  salary double,
  foreign key (actorId) references Actor(actorId),
  foreign key (filmId) references Film(filmId)
);

-- TODO: insert records into the join table
```

```text









```

  
  
