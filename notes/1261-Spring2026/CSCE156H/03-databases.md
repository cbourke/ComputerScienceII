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
use `cbourke3`;

# you can use the where clause to limit records:
select * from game where gameId = 7;
select * from game where gameId > 7;
select * from game where gameId >= 7;
select * from game where gameId = 7 or (gameId < 6 and gameId > 2);
# string based:
select * from game where name = 'Portal';
# string wildcard: % (matches one or more characters)
select * from game where name like 'P%';
select * from game where name like '%a%' or name like '%A%';
# single character wildcard: _
select * from game where name like '_a%';
select * from game where name like '___a%';

-- ordering
-- default: asc
select * from game order by name;
select * from game order by name desc;
-- multiple columns:
select * from game order by publisherId asc, name desc;

-- data projections
-- for eaach publisher: how many games have they published?
select publisherId, count(*) as numberOfTitles from game group by publisherId;

-- joins
-- cross join: if you join without any "on" clause you get ALL combinations
select * from publisher
  join game;
-- an on clause will limit the records to matching records:
select * from publisher
  join game
  on publisher.publisherId = game.publisherId;
-- you an use aliasing to reduce typing:
select * from publisher p
  join game g
  on p.publisherId = g.publisherId;

-- we're not getting any records of publishers with no games...
select * from publisher;
-- a left join preserves records "from the left"
select * from publisher p
  left join game g
  on p.publisherId = g.publisherId;

-- let's redo the report on publishers and the number of games
select p.name as publisher, count(g.gameId) as numberOfTitles
  from publisher p
  left join game g on p.publisherId = g.publisherId
  group by p.publisherId;

# 01. List all video games in the database

select * from game;

# List all video games that start with 'G'

select * from game where name like 'G%';

# 02. List all publishers in the database

select * from publisher;

# 03. List all video games along with their publishers
-- above
# 04. List all video games along with their publishers, but only the relevant fields
# 05. List all publishers in the database along with all their games, even if they don't have any
# 06. List all publishers with a count of how many games they have

# 07. List all games and all systems that they are available on

select g.name as title, publishYear, p.name as platform from game g
  join availability a on g.gameId = a.gameId
  join platform p on a.platformId = p.platformId;

# 08. List all games that are not available on any system

select g.name as title, publishYear, p.name as platform from game g
  left join availability a on g.gameId = a.gameId
  left join platform p on a.platformId = p.platformId
  where p.name is null;

# 09. List the oldest game(s) and its platform(s)
select g.name as title, publishYear, p.name as platform from game g
  join availability a on g.gameId = a.gameId
  join platform p on a.platformId = p.platformId
  where a.publishYear = (select min(publishYear) from availability);


# 10. Flatten the entire data model by returning all data on all games

select * from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId
union
select * from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform plat on a.platformId = plat.platformId;

# . Insert a new game, Assassin's Creed, published by Ubisoft
-- first: insert a publisher record...
insert into publisher (name,publisherId) values ('Ubisoft', 15);
insert into game (gameId,name,publisherId) values (100, 'Assassin\'s Creed', 15);
select * from publisher;

# . Make the new game available on at least two platforms
select * from platform;
insert into availability (gameId,platformId) values
  (100, 6),
  (100, 7);
#insert into availability (gameId,platformId) values (100, 7);

# . Update the record for Megaman 4: the publisher should be Capcom, not Eidos
select * from game;
update game set
  publisherId = (select publisherId from publisher where name = 'Capcom')
  where gameId = (select gameId from game where name = 'Megaman 4');

# . Delete the publisher Eidos: how?
select * from publisher;
delete from availability where availabilityId in (15, 16);
delete from game where publisherId = 10;
delete from publisher where publisherId = 10;

# You need to first delete any game associated with Eidos, but
# any availability record that refers to all the games that Eidos
# has published.


```

## Designing & Implementing a Database

* Create a database to model the asset java classes/problem (Asset, Savings, Stock, Person (owner), Email(s) of a person, etc.)

### Observations

* Semantics Dictate Design: usually you have one table per "entity"
* Style tips:
  * Be consistent with your naming convention
  * Avoid verbs, use nouns
  * Avoid abbreviations, do not pluralize table names
  * Modern convention: `UpperCamelCasing` for tables
  * Modern convention: `lowerCamelCasing` for columns
* Make sure that **every table** has a PK that:
  * that is an integer (don't use `double` or `varchar`)
  * Use `tableName` + `Id` for the PK column name
  * always use `int not null primary key auto_increment`
* Foreign keys should:
  * Use the same name as the PK they reference
  * `foreign key (columnName) references OtherTable(columnName)`
  * Always should be an `int` and never `null` (nullable FKs lead to orphaned records)
* YOu can use `unique key` to ensure:
  * Uniqueness of record values and
  * Fast search/retrieval
  * PK = Surrogate keys (auto generated and managed by the DB)
  * Other keys: "Natural" keys (NUID, SSN, UUID): these should be keys and made `unique key` but NOT primary keys
  * FKs always go in the "child" table
  * FKs to in the "many" table of a 1-to-many relation
* Be sure that there is plenty of test/mock data in your database
  * There are plenty of tools to do this: mockaroo
  * Or: you can use CSV to XML/JSON/SQL converters
* POtential data issue:
  * Can Person A own TWO instances of `MSFT` stock?  1 record with 50 shares and one record with 100 shares? YES
  * Should they be able to? If not, how can we prevent this?
  * Should stock share prices be negative?
  * Should number of shares be negative?
  * Should you ever have a stock with the `balance` defined?
  * Solution: you can define check constraints to enforce data integrity
    * They are allowed on MySQL/MariaDB
    * But they are not actually enforced

```sql
use `cbourke3`;

drop table if exists Ownership;
drop table if exists Asset;
drop table if exists Email;
drop table if exists Person;

create table if not exists Person (
  personId int not null primary key auto_increment,
  uuid varchar(36) not null unique key,
  lastName varchar(255) not null,
  firstName varchar(255)
);  

create table if not exists Email (
  emailId int not null primary key auto_increment,
  address varchar(255) not null,
  personId int not null,
  foreign key (personId) references Person(personId)
);

insert into Person (personId,uuid,lastName,firstName) values
(1, 'Felicia', 'Greest', '7ffa34f4-2887-41b5-ba3a-8cc66cd64c5a'),
(2, 'Alissa', 'Witch', '581cc382-bf40-4e81-8b76-9c6c9ab53c3c'),
(3, 'Xaviera', 'Bruhnsen', 'ca155fec-5e6b-4341-92f5-ae8fd15dc5f6'),
(4, 'Lynsey', 'Costello', '2dcf4a5b-932f-4061-afba-761846cc186a'),
(5, 'Dulce', 'Treen', 'ff6e529b-cf96-4e7f-8b14-b33dfc040f02');

insert into Email (emailId, personId, address) values (1, 1, 'tpourvoieur0@aboutads.info');
insert into Email (emailId, personId, address) values (2, 1, 'nbartoshevich1@aboutads.info');
insert into Email (emailId, personId, address) values (3, 1, 'tfery2@sitemeter.com');
insert into Email (emailId, personId, address) values (4, 2, 'kcicco3@parallels.com');
insert into Email (emailId, personId, address) values (5, 2, 'aead4@linkedin.com');

select * from Person;
select * from Email;
select * from Person p
  left join Email e
  on p.personId = e.personId;

select p.personId,count(e.emailId) from Person p
  left join Email e
  on p.personId = e.personId
  group by p.personId;

create table if not exists Asset (
  assetId int not null primary key auto_increment,
  -- S = Stock
  -- A = Savings Account
  type varchar(1) not null,
  -- Savings Account data:
  interestRate double,  
  -- Stock data:
  sharePrice double
);

create table if not exists Ownership (
  ownershipId int not null primary key auto_increment,

  personId int not null,
  assetId int not null,

  accountNumber varchar(255) not null unique key,
  -- Savings Account data
  balance double,

  -- Stock data
  numberOfShares double,

  foreign key (personId) references Person(personId),
  foreign key (assetId) references Asset(assetId),
  unique key (personId,assetId),
  constraint `positiveNumberOfShares` check (numberOfShares > 0),
  constraint `validData` check (
    (balance is not null and numberOfShares is null) or
    (balance is null and numberOfShares is not null)
  )

);


```


```text













```
