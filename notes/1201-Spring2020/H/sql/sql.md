# Databases & SQL
## CSCE 156H - Spring 2020

### Introduction/Overview

![Enrollment Data](../../sql/flatData.png "Flat DB")

* Flat data files are insufficient for storing data
  * Repetition of data
  * Formatting issues
  * Consistency issues
  * Integrity issues
  * Organization problems: not sorted, it is in one big file (no multithreaded or multiuser capability)
  * There is no security!

  
* Solution: use a RDBMS (Relational Database Management System)
  * 1970 Edgar Codd (1970, IBM)
  * Key aspects: data is stored in *tables*
  * Data is stored in columns (fields)
  * Records are stored as rows
  * Each row may have a unique identifier (Primary Key, PK)
  * Rows (records) in different tables may be related through Foreign Keys (FK)
  
* You can interact with a RDBMS through CRUD:
  * Create (`insert`)
  * Retrieve (`select`)
  * Update (`update`)
  * Destroy (`delete`)
  
## Demonstration

![Vidya Games](../../sql/gamedb.png "Game DB")


```sql


-- Create some records using an INSERT query
INSERT INTO game VALUES ("Super Smash Brothers");
-- In general, use lower casing for sql (modern convention)

select * from game;
select * from publisher;
insert into game (gameId,name,publisherId) 
  values (100,"Super Smash Brothers", 5);
insert into game (name,publisherId) values ("Super Smash Brothers", 5);

insert into game (name,publisherId) values ("Super Smash Brothers", 
  (select publisherId from publisher where name = "Nintendo"));

-- D = Destroy
-- You can destroy/remove records from a table using the DELETE query
delete from game where gameId = 100;
delete from game where gameId = 101;

-- U = Update
-- You can change column values of existing records using the UPDATE query
update game set name = "Super Smash Bros" 
  where gameId = 22; 
  
-- ?? (select gameId from game where name = "Super Smash Brothers");

-- R = Retrieve
-- You can retrieve elements from the database using the SELECT query
-- You can select every column using the wildcard: *
select * from game;

-- You can specify particular columns to retrieve instead:
select name from game;
select publisherId, name from game;

-- You can change the name of your column using an alias
select name as gameTitle,
   publisherId as publisherId
   from game;

-- You can filter your results using a WHERE clause:
select * from game where gameId > 5 and gameId <= 10 or gameId = 1;


-- You can also do string comparisons:
select * from game where name = "Katamari Damacy";

-- You can also do partial string comparisons using the string wildcard: %
select * from game where name like "G%";

-- You can use multiple wildcards:
select * from game where name like "%r%r%";

-- Goal: create a report with all games and their publishers...
-- to combine one or more tables, you need to JOIN them together
-- by some criteria
-- select count(*) from game;
select count(*) from publisher;

-- this is a full join, but doesn't make sense
select * from game 
  join publisher 
  on game.publisherId = publisher.publisherId;

-- you can futher shorten the query using *table aliases*
select * from game g
  join publisher p
  on g.publisherId = p.publisherId;

-- you *can* (but likely don't want to) join on anything you want:
select * from game g
  join publisher p
  on g.name = p.name;
  
-- you can join in any order you want:
select * from publisher p
  join game g
  on g.publisherId = p.publisherId;

-- to preserve records that have no matches you can use a LEFT JOIN:
select * from publisher p
  left join game g
  on g.publisherId = p.publisherId;

-- you can join as many tables as you want
select 
  p.name as publisherName,
  g.name as publisherTitle,
  a.publishYear as releaseYear,
  pl.name as platform
 from publisher p
  left join game g on g.publisherId = p.publisherId
  left join availability a on a.gameId = g.gameId
  left join platform pl on a.platformId = pl.platformId;


```

```sql
use cbourke;

-- from last time: inserting multiple records
select * from platform;

insert into platform (name) values
  ("Vive"),
  ("Index"),  
  ("Oculus Quest");

select * from publisher p 
  join game g on p.publisherId = g.publisherId;

select * from publisher p 
  left join game g on p.publisherId = g.publisherId;

select * from publisher p 
  right join game g on p.publisherId = g.publisherId;

select * from game g
  right join publisher p on p.publisherId = g.publisherId;

-- flatten the game/platform data model: report all games
-- and all platforms that they are available on

select * from game g 
  left join availability a on g.gameId = a.gameId
  left join platform p on a.platformId = p.platformId;

select * from game g 
  right join availability a on g.gameId = a.gameId
  right join platform p on a.platformId = p.platformId;

select * from game g 
  left join availability a on g.gameId = a.gameId
  left join platform p on a.platformId = p.platformId
union 
select * from game g 
  right join availability a on g.gameId = a.gameId
  right join platform p on a.platformId = p.platformId;

-- Modifiers

-- to select only unique values in a database use the DISTINCT keyword:
select publishYear from availability;
select distinct publishYear from availability;
  
-- You can also use an IN clause to define sets:
select * from availability where publishYear = 1988 or publishYear = 2005 or publishYear = 1992;
select * from availability where publishYear in (1988, 2005, 1992);  
select * from game g
  left join availability a on g.gameId = a.gameId
  where g.gameId in (select gameId from game where name like 'G%');
  
-- In general, results are unordered (they are a set)
-- To impose an ordering (sort them) you can use the ORDER BY clause
select g.name as title,
  a.publishYear,
  p.name as platformName
  from game g
  left join availability a on g.gameId = a.gameId
  left join platform p on a.platformId = p.platformId
  order by platformName asc, title desc;

-- Aggregates
-- the count() function allows you to count hte number of records:
select count(*) as numberOfGames from game;
select min(publishYear) from availability;
select max(publishYear) from availability;
select avg(publishYear) from availability;
select round(min(publishYear) / max(publishYear)) from availability;

-- for each publisher: how many games did they publish?

select * from publisher p
  left join game g on p.publisherId = g.publisherId;

select p.name as publisherName, count(g.gameId) as numGames from publisher p
  left join game g on p.publisherId = g.publisherId
  where p.name > 'M'
  group by p.publisherId
  having numGames <= 5 and numGames > 2
  order by publisherName;


```

```sql

use cbourke;

drop table if exists ActorFilm;
drop table if exists Film;
drop table if exists Director;
drop table if exists Actor;

create table if not exists Director (
  directorId int primary key not null auto_increment,
  firstName varchar(100),
  lastName varchar(100)  
);

create table if not exists Film (
  filmId int primary key not null auto_increment,
  title varchar(255) not null,
  releaseDate varchar(50) not null default '0000-00-00',
  imdbRating double,
  grossEarnings double,
  eidr varchar(100) unique key,
  directorId int not null,
  check (imdbRating >= 0 and imdbRating <= 10),
  foreign key (directorId) references Director(directorId)
);

insert into Director (firstName, lastName) values 
  ("George", "Lucas"),
  ("Martin", "Scorcese"),
  ("Steven", "Speilberg"),
  ("Guermo", "Del Toro");

insert into Film (title, directorId) values
  ("Star Wars", (select directorId from Director where lastName = "Lucas")),
  ("American Graffiti", (select directorId from Director where lastName = "Lucas")),
  ("THX 1138", (select directorId from Director where lastName = "Lucas")),
  ("Pan's Labyrinth", (select directorId from Director where lastName = "Del Toro")),
  ("Hellboy", (select directorId from Director where lastName = "Del Toro"));

create table if not exists Actor (
  actorId int primary key not null auto_increment,
  firstName varchar(100),
  lastName varchar(100)  
);

create table if not exists ActorFilm (
  actorFilmId int primary key not null auto_increment,
  actorId int not null,
  filmId int not null,
  -- you can put other data here too: wonAward boolean,
  foreign key (actorId) references Actor(actorId),
  foreign key (filmId) references Film(filmId),
  constraint `uniqueActorFilmCombo` unique (actorId,filmId)
);

insert into Actor (firstName, lastName) values
  ("Mark", "Hamil"),
  ("Carrie", "Fisher"),
  ("Harrison", "Ford");

insert into ActorFilm (actorId, filmId) values
  ((select actorId from Actor where lastName = "Hamil"), (select filmId from Film where title = "Star Wars")),
  ((select actorId from Actor where lastName = "Fisher"), (select filmId from Film where title = "Star Wars")),
 -- ((select actorId from Actor where lastName = "Fisher"), (select filmId from Film where title = "Star Wars")),
  ((select actorId from Actor where lastName = "Ford"), (select filmId from Film where title = "Star Wars"));

select * from Film f
  join ActorFilm af on f.filmId = af.filmId
  join Actor a on a.actorId = af.actorId;
  
```

## Normalization

* Three basic normal forms: 1-NF, 2-NF, 3-NF
* First Normal Form: each attribute in a table only has atomic values
  * Every column in a table holds only one value
  * Violation: not having a first name/last name in seprate columns
  * Violation: a CSV string of multiple emails
  * Solution: split things out into their own columns or another table, defining a one to many relation
  * Violation: if you hardcode "enough" columns
* Second Normal Form: it has to be 1-NF and no non-prime attribute is dependent on a proper subset of prime attributes
  * If you always have an auto-incremented PK you get 2-NF for free!
  * Violation: a purchase record may contain a customerId, storeId, storeLocation
  * Solution: split things out into their own tables
* Third normal form: 2-NF AND no non-prime column is transitively dependent on the key
  * Suppose you had a `pricePerUnit` and a `numberOfUnit` and you *stored* a `totalCost` = pricePerUnit * numberOfUnit in a column
* Bottom line: Normalization is common sense or good design/intuition 
* Every non-key attribute must provide a fact about the key (1NF), the whole key (2NF) and nothing but the key (3NF) so help me Codd

* There is a lot more to learn about DBs!
  * Triggers, Views, stored procedures, etc.
  * Security: don't store passwords!!!
  * at least unhashed
* Hard vs soft deletes: a hard delete means you actually `delete` a record from the database; soft delete: every record would have an `isActive` column (`boolean`)

* OOP Model vs Relational Model
  * OOP has inheritance and behavior
  * Relational model: does not 
  * How to resolve this disconnect?
  * How do you "model" inheritance in a RDBMS
  * You can use a single table and use a *discriminator column/value* 
  * KISS Principle = Keep It Simple Stupid!
  * Alternatives: table per class or a table per subclass (stub) strategies 

