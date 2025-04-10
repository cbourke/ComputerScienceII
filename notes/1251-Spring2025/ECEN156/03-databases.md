# Computer Science II - ECEN 156
## Spring 2025
### Databases & SQL

### Introduction/Overview

* Programs are short-lived, data is not
* We need a way to *persist* (save) data
* Up to now: flat files (CSV) or structured files (JSON, XML)

#### Flat Files

Flat data files are insufficient for storing data

* It is a terrible format to search or consolidate data
* Files can only be processed by one process (program) at a time: file locks
* Data is repeated unnecessarily
* Consistency issues: data anomalies
* Integrity issues: different ways of representing missing or non-existant data
* Organization problems: not sorted
* Formatting issues
* There no types: there are no integers, doubles, etc.
* There is no security

### Solution: use a RDBMS (Relational Database Management System)

* 1970 Edgar Codd
* Key aspects: data is stored in separate tables
* Tables have columns (pieces of data) and rows (records)
* Each column has a *type* and a *name*
* Each row in a table corresponds to a single record
* Records in *a* table are *uniquely* identified by *primary keys* (PKs)
* Records in different tables are *related* to each other through *foreign keys* (FKs)
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
    * Destroy (`delete`) - removing/deleting records in a table: THERE IS NO, **ABSOLUTELY NO** UNDO

## Getting Started

* You all have access to a database named after your canvas login (`cbourke3`) on a server named `nuros.unl.edu`
* Your password should have been sent to you via your huskers email this past weekend
* If you lose it and/or need to change it: you'll need to contact `manager@cse.unl.edu`
* DO NOT use this password for anything else and if you change your password, then make sure it is NOT something you would normally use!
* We recommend that you use MySQL Workbench to work with the SQL server: https://www.mysql.com/products/workbench/

```sql
-- always start by using YOUR database
-- the database is named after your login
use cbourke3;

-- you can list all of the tables in your database:
show tables;

-- you can describe a single table:
describe game;

-- C = Create
-- to insert new records into an existing table
-- insert a record with a hardcoded value for the primary key (PK)
-- this is okay for mock or test data, but not generally a good idea
insert into publisher (publisherId, name) values (14, 'Microsoft');
-- inserting a record without a PK, it will be *generated for you*!
insert into publisher (name) values ('Bethesda');

-- insert a game record
insert into game (name, publisherId) values ('Minecraft', 14);
-- otherwise you can use a "nested query":
insert into game (name, publisherId) values ('Flight Simulator',
  (select publisherId from publisher where name = 'Microsoft'));
-- order matters: parent records must exist
-- before child records
-- a publisher must exist before you can insert game records
insert into game (name, publisherId) values ('Eldenring',
  (select publisherId from publisher where name = 'From Software'));

-- U = Update
insert into publisher (name) values ('Take Too Software');
-- misspelled the name, so update:
-- safe mode will prevent blind updates of all records:
update publisher set name = 'Take Two Software';
-- always use a where clause:
update publisher set name = 'Take Two Software' where publisherId = 16;

-- D = Destroy
-- deletes ALL records:
delete from publisher;
-- always use a where claus:
delete from publisher where publisherId = 16;
-- you can escape special characters:
insert into publisher (name) values ('O\'Brian Software');
-- doesn't work on non key columns (PK, FK only)
delete from publisher where name = 'O\'Brian Software';
delete from publisher where publisherId = 17;

-- to delete a parent record, all child records must be
-- deleted first
delete from game where publisherId = 14;
delete from game where publisherId = 15;
delete from publisher where publisherId = 14 or publisherId = 15;

-- R = Retrieve

-- you can use the wildcard to pull every single column out of a
-- table:
select * from game;
select * from publisher;
-- or you can select only some columns:
select publisherId, name from game;
-- you can alias the columns in your result:
select name as gameTitle from game;
--
select name as publisherName from publisher;
-- alignment: white space does not matter
select
  name as gameTitle,
  gameId as id,
  publisherId
from game;

-- Aggregate functions
-- you can do some basic computations using the SQL language
-- determine how many games there are:
select count(*) as numberOfGames from game;

-- oldest available game
select min(publishYear) from availability;
-- newest game:
select max(publishYear) from availability;
-- average:
select avg(publishYear) from availability;
-- sums: weird but okay:
select sum(publishYear) from availability;
-- get really weird: use a $1M database as a calculator:
select 8 * 5 - sum(publishYear) / 3 from availability;

-- you can use a where clause to limit your results:
select * from game where gameId = 5;
select * from game where gameId != 5;
select * from game where gameId > 5;
select * from game where gameId >= 5;

-- you can use string matching:
select * from game where name = 'Legend of Zelda';
select * from game where name < 'Legend of Zelda';

-- generally you want partial string matching...
-- wildcard with strings is %
-- you use a "like" clause instead of a where clause:
-- selects all games that begin with "G"
select * from game where name like 'G%';
-- any game that has a lowercase a in it:
select * from game where name like '%a%';
-- strings are case sensitive:
select * from game where name like '%A%';

-- compound logic:
select * from game where name like '%the%' or name like '%The%';
select * from game where name like '% the%' or name like '% The%';
-- you can use the _ for single letter matches
select * from game where name like '%_the%' or name like '%_The%';
--
select * from game where name like '___ 3';

-- generally the order of rows (results) does not matter, but
-- you can reorder them in your result using ASC (default) or DESC
select * from game order by name;
select * from game order by name desc;
-- combination:
select * from game order by publisherId asc, name desc;

-- you can use the keyword distinct to get unique results:
select distinct publisherId from game;

-- you can use the in clause on a set of values to shorten your code:
select * from game where gameId = 3 or gameId = 7 or gameId = 13;
select * from game where gameId in (3, 7, 13);
select * from game where gameId in (select gameId from game where publisherId = 5);
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
  * You can break up long lines, use a beautifier
  * Use modern `lowercase` for SQL keywords (save your pinkie)

### Math

* All of SQL is based on set theory
  * $a \in A$ where $A = \{a, b, c\}$
  * $x \not\in A$
* What about projections in math?
  * Geometrically:
  * Suppose you ahve a 3D cube and you "project" it down onto a 2D plane: you get a square
  * $(x, y, z) \rightarrow (x, y)$
* Suppose you ahve two sets: $A = \{a, b, c\}$, $B = \{1, 2\}$
  * The cartesian product (cross product):
  * $A \times B = \{(a, b) | a \in A \wedge b \in B\}$
  * $A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$
  * $|A| = 3, |B| = 2$, $|A \times B| = 6$
  * Generally: $|A| = n$, $|B| = m$, $|A\times B| = n \cdot m$
  * $(x, y) \in \mathbb{R} \times \mathbb{R}$: cartesian coordinates/plane
* A *binary relation* bewteen two sets $A, B$ is a subset of the cartesian product:
  $$R \subseteq A \times B$$
  $$(x, y) \in R \iff x \leq y$$
* Basic set operations:
  * $A \cup B$: union of two sets (no duplicates)
  * $A \cap B$: intersection: elements in BOTH sets

```sql
use cbourke3;

-- how many games are published by each publisher?
select * from game;

-- use group by to aggregate projected data:
select publisherId, count(*) as numberOfTitles from game group by publisherId;

-- you can filter results *after* a project using "having"
-- you cannot use a where clause
select publisherId,
       count(*) as numberOfTitles
from game group by publisherId having numberOfTitles > 2;

-- you can still use a combination:
select publisherId,
       count(*) as numberOfTitles
from game
where publisherId > 2
group by publisherId
having numberOfTitles > 2;

-- now we need to bring multiple tables together...
select count(*) from game; -- 20
select count(*) from publisher; -- 15

-- blind cross join:
-- it is WAY too big
-- it makes no sense: it is not following our *relations*
-- in the tables!
select count(*) from game join publisher;

-- join based on a FK Foreign Key = PK Primay Key
select * from game
  join publisher
  on game.publisherId = publisher.publisherId;

-- you do this so often that you can use shorthand:
select * from game g
  join publisher p
  on g.publisherId = p.publisherId;

-- a "join" is an "outer" join: it will join only on the
-- values that are matched
-- game -> publisher: the values are guaranteed to exist  
-- order matters:
-- a normal "join" does not preserve records in table A
-- to table B
select * from publisher;
select * from publisher p
  join game g
  on p.publisherId = g.publisherId;

-- a "left join" preserves records in table A joined to table B
-- that do not have any matching records in table B
select * from publisher p -- table A
  left join game g -- table B
  on p.publisherId = g.publisherId;

-- is there a right join?  Yes:
-- generally prefer left joins
-- English read left to right
select * from game g
  right join publisher p
  on g.publisherId = p.publisherId;

-- solve the original problem: how many games has
-- each publisher published: want to know the publisher AND
-- include all publishers with zero games
select p.name, count(g.gameId) as numberOfTitles from publisher p
  left join game g
  on p.publisherId = g.publisherId
  group by p.publisherId;

-- go back to the beginging: flatten the entire data model
-- so you can dump it to a CSV
select * from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId
union
select * from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform plat on a.platformId = plat.platformId;

-- exercises...

# 01. List all video games in the database
select * from game ;
# List all video games that start with 'G'
select * from game where name like 'G%';
# 02. List all publishers in the database
select * from publisher;
# 03. List all video games along with their publishers
# 04. List all video games along with their publishers, but only the relevant fields
select p.name as publisher, g.name as title from publisher p
  join game g
  on p.publisherId = g.publisherId;
# 05. List all publishers in the database along with all their games, even if they don't have any
select p.name as publisher, g.name as title from publisher p
  left join game g
  on p.publisherId = g.publisherId;
# 06. List all publishers with a count of how many games
-- they have
# 07. List all games and all systems that they are available on
# 08. List all games that are not available on any system
# 09. List the oldest game(s) and its platform(s)
# 10. Flatten the entire data model by returning all data on all games

# . Insert a new game, Assassin's Creed, published by Ubisoft
-- insert multiple records at once:
insert into publisher (name) values
  ('Ubisoft'),
  ('Microsoft'),
  ('Macrosoft');
insert into game (name, publisherId) values ('Assassin\'s Creed',
  (select publisherId from publisher where name = 'Ubisoft'));
# . Make the new game available on at least two platforms
insert into availability (gameId, platformId, publishYear) values (
  (select gameId from game where name = 'Assassin\'s Creed'),
  (select platformId from platform where name = 'Playdate'),
  2025);
insert into availability (gameId, platformId, publishYear) values (
  (select gameId from game where name = 'Assassin\'s Creed'),
  (select platformId from platform where name = 'Index'),
  2025);

# . Update the record for Megaman 4: the publisher should be Capcom, not Eidos

# . Delete the publisher Eidos: how?
# You need to first delete any game associated with Eidos, but
# any availability record that refers to all the games that Eidos
# has published.

# . Write a query to return all games along with the number of platforms they
#   are available on

```

## Designing & Implementing a Database

* Demonstration: create a database to model the asset classes (`Annuity`, `Stock`, `Person` and their emails)

```sql
use cbourke3;

drop table if exists Ownership;
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
  -- personId int not null,
  accountType varchar(1) not null,
  -- TODO: consider a constraint to force it to be 'S' = Stock, 'A' = Annuity

  -- Annuity
  monthlyPayment double,
  termYear int,

  -- Stock
  name varchar(255),
  sharePrice double,
  constraint `onlyAorS` check (accountType = 'S' or accountType = 'A'),
  constraint `validData` check (
    (monthlyPayment is not null and termYear is not null) or
    (name is not null and sharePrice is not null and numShares is not null and numShares >= 0) )
  -- foreign key (personId) references Person(personId)
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

-- represents an ownership: many-to-many relation between
-- person table and the account table
create table if not exists Ownership (
  ownershipId  int not null primary key auto_increment,
  personId int not null,
  accountId int not null,
  numShares double, -- stock types only
  foreign key (personId) references Person(personId),
  foreign key (accountId) references Account(accountId),
  unique key (personId,accountId)
);

-- TODO: insert some test/mock data
-- TODO: write some basic queries to give your database design a workout
```

## Summary

### Database Design Observations

* Semantics dictate design: usually you have one table per "entity"
* Style tips:
  * Be consistent in your naming conventions
  * Do not pluralize table names
  * Modern convention: tables should be `UpperCamelCased`
  * Modern convention: columns should be `lowerCamelCased`
  * Old school: `UPPER_UNDERSCORE` for tables `lower_underscore_casing` for columns
  * Avoid abbreviations; unless it is well-understood (`uuid`, `ssn`, `nuid`)
* Make sure that **every table** has a PK that:
  * Is an integer (don't use `double` or `varchar`s)
  * Use a name that is the `tableName` + `Id`
* Foreign keys should have the same name as the PK they reference
  * Sometimes you may *have* to violate this guideline if necessary
* Define `unique key`s to ensure uniqueness on other columns
  * PKs = surrogate keys that are generated and managed by the database
  * Keys = natural keys (SSN, NUID, UUID, etc.) that are not managed by our database (if we don't control them, they should not control us)
* Join tables should be used to model a many-to-many relationship
* Be sure that there is plenty of test/mock data in your database
  * There are plenty of tools!  Mockaroo (it can also generate SQL `insert` statements)
  *  You can use any CSV to SQL/JSON/XML
* Check an uniqueness constraints can be defined to ensure data integrity
  * MySQL: does not necessarily enforce check constraints

## Normalization

* 1-NF, 2-NF, 3-NF
* They build on each other: you cannot have a higher normal form without having ALL lower normal forms
* First Normal Form: "each attribute (column) in a table only has atomic values"
  * Each column in a table represents *one* piece of data
  * Violation: storing a series of emails in one column as a CSV data: `email1@foo.com,email2@bar.com,foo@email.com`
  * Violation: store multiple columns instead of a proper one-to-many relation: `primaryEmail`, `secondaryEmail`, `tertiaryEmail`
  * You should always create a *true* one-to-many relationship
  * Best practice: separate out into another table and define a one-to-many relationship
  * FKs go in the "child" table
* Second Normal Form: it has to be 1-NF: no non-prime attribute (column) is dependent on a proper subset of prime attributes
  * Having a PK auto-incremented gives you 2NF automatically
  * Violation: a purchase record that contains `customerId, storeId, storeAddress`
  * Suppose the PK is `customerId/storeId` (ie a combination of the two)
  * IF you defined a PK as a combination of `customerId/storeId` then you violate 2NF: `storeAddress` only depends on the second half of the key
  * It is often useful or necessary to have "compound keys", but they should always be *secondary* keys
  * You split everything out into its own table
* Third Normal Form: has to be 2-NF (and transitively 1-NF)
  * No non-prime column is transitively dependent on the key
* Violation: store the `termYears, monthlyPayment` AND `totalValue` (`= termYears * monthlyPayment * 12`)
  * Violation: that the `totalValue` is stored when it should be *recomputed* based on the other values
  * The other two columns give enough data to determine the third (the third is transitively dependent on the first two)
  * Gives rise to possible data anomalies: if you change one value, it means the transitively dependent value is now *wrong*
* Every non-key attribute must provide a fact about the key (1NF), the whole key (2NF) and nothing but the key (3NF) so help you Codd

## Misc

* There is a LOT more
  * DBA = DataBase Administrator
  * Triggers: event-based actions in a database
  * Views: read-only "tables" in a database
  * Transaction: an atomic operation on a database that supports the ACID principles
    * Atomicity
    * Consistency
    * Isolation
    * Durability

```sql
use cbourke3;

start transaction;
delete from Email where emailId > 0;
commit;
-- or rollback;

select * from Email;
```
  * Temp tables: temporary tables that can be created within a transaction to make data processing easier
  * Stored Procedures: functions you can define with reusable SQL code that you can treat like a function
  * Loops, variables (cursors), etc.
  * Soft vs Hard deletes: a hard delete is a result of a `delete` statement (no undo, no recycle bin).
  * Soft delete: an `isActive` boolean column in a table
    * Delete: `update RECORD set isActive = false where BLAH;`
    * When you select, you only pull *active* records
  * Generally you want to use a *single table inheritance* strategy with databases vs OOP models
    * You *could* do a table-per-class or table-per-subclass strategy (more complicated: YAGNI)

## Programmatically Connecting to a Database & Processing Data

* In Java we'll use JDBC = Java Database Connectivity API (Application Programmer Interface)
* Getting Started:
  * Download the Connector/J jar file and include it in your project: <https://dev.mysql.com/downloads/connector/j/>
  * Add it to your project as usual
  * Start writing your JDBC code!

## Overview

* Goal: programmatically connect to a database and process or persist (save) data
* Most languages have some support for *database connectivity*
* Java: JDBC = Java DataBase Connectivity API
* API = Application Programmer Interface
* Perfect illustration of *Dependency Inversion*
* Don't program toward a specific database, but a generic interface
* Vendors (Oracle, IBM, etc.) provide a *driver* library that conforms to the API
* JDBC provides:
  * `Connection`
  * `PreparedStatement`
  * `ResultSet`
* ORMs (Object-Relational Mappings) systems also exist (JPA, jOOQ)

## Process

1. Create a connection to your database: need user name, password, URL
2. Create/prepare your query
  - prepare the query
  - execute the query
3. Process your results
4. Clean up your resources

## Observations

* JDBC is a LOT of boilerplate CRUD: at least 4 operations per table to support ALL operations of a database
* There are other, "better" solutions: ORMs = Object-Relational Mappings (Java: JPA = Java Persistence API)
* For select queries use `executeQuery()` which returns a `ResultSet rs` that you iterate over
  * When iterating: use a `while` loop with `rs.next()` (if expecting multiple records)
  * If only expecting one, you can use an `if-else` statement
* For update/delete/insert statements use `executeUpdate()` (there are no separate `exeucteInsert()` methods!)
* If you need the resulting generated keys use:
  * `Statement.RETURN_GENERATED_KEYS` when you prepare the statement and
  * `ps.getGeneratedKeys()` to retrieve them so you can use them as FKs to insert more related records

## Best Practices

### Avoid the star operator

* Example: don't do `select * from Person` in JDBC
* This sends ALL data over the wire (network) whether or not you're going to use it!
* Be more intentional: generally `select` statements should enumerate only the columns you care about
* You risk sending redundant or irrelevant data over the network
* Future proofs you: if someone comes in and adds a new column with a lot of data `blob`s = binary large objects

### Security Issues

* Generally you do NOT want to put your database passwords/credentials in plaintext in your code
* BUT: for this class you will
* Unfortunately this is still very common: go github.com: scan all public repos for the keyword `password`
* In practice:
  * option: define "data source": no password at all, just approve certain apps from certain servers, etc.
  * option: an admin enters the password once at startup
* Never store passwords in a database (for users), at least never in plaintext (hashed MAY be okay)

### Close Your Resources!

* Failure to close resources: `ResultSet`s, `PreparedStatement`s and `Connection`s: you will eventually run out of connections
* Make sure you do it in the proper order: generally in reverse order than what you opened them in
* Do not close them until you are done using them
* Close them immediately after you are done with them
* It can be very difficult to track down which piece of code is not closing a resource!

### Dealing with `SQLExceptions`

* Unfortunately: JDBC is quite "mature" and so it uses old-school style *checked exceptions*: ones that you are forced to surround with a `try-catch` and deal with
* Best practice: catch and release: `catch` the exception and rethrow it as a `RuntimeException` (catch and release)

### Always Use `PreparedStatements`

* In general, strings can contain anything including unsanitized SQL code
* If you use `PreparedStatement`s then you are generally safe from that!
* `PreparedStatement`s in Java *sanitize* the inputs for you, ensuring that no SQL injection is possible

### Proper Logging System

* No one is sitting at your terminal watching for standard output error messages, ready to jump into action
* NEVER use the standard output for log messages: it is brittle, it is lost
* Instead: use a proper logging system
* Features:
  * Supports multiple levels of logging: `DEBUG, INFO, WARN, ERROR`
  * You can configure it to printout certain levels or above/below a certain level
  * Supports file-based, email-based, database-based logging: it doesn't necessarily go to the standard output, but can be redirected to (say) log files
  * Handles/maintains the files: you can place "daily rolling limits" or file size-based limits etc.


```text













```
