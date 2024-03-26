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
  -- accountNumber will be a unique secondary or "natural" key
  -- unique keyword enures that all records have unique values
  -- key makes it into an index: internally the database will maintain an ordering
  -- order that makes it efficiently searchable
  -- accountNumber varchar(255) not null unique key,
  type varchar(1) not null, -- A = Annuity, S = Stock; TODO: prevent bad data by only allowing A and S...
  -- Annuity columns:
  terms int,
  monthlyPayment double,
  -- Stock columns:
  symbol varchar(10) unique,
  sharePrice double
);

create table if not exists Ownership (
  ownershipId int primary key not null auto_increment,
  personId int not null,
  assetId int not null,
  numberOfShares double,
  constraint `uniqueAssetPair` unique (personId,assetId),
  constraint `numberOfSharesNonNegative` check (numberOfShares >= 0),
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

insert into Asset (assetId,type,symbol,sharePrice) values
  (3, "S", "GOOG", 138.50),
  (4, "S", "APPH", 0.067);

-- bourke's assets
insert into Ownership (personId,assetId,numberOfShares) values
  (123, 1, null),
  (123, 2, null),
  (123, 3, 10.5),
  (123, 4, 20.5);

-- craig's assets

insert into Ownership (personId,assetId,numberOfShares) values
  (789, 3, 1000),
  (789, 4, 20000);


select p.lastName as `Last Name` from Person p
  join Ownership o on p.personId = o.personId
  join Asset a on o.assetId = a.assetId;

```

### Observations

* Semantics dictate design: usually you have one table per "entity"
* Style tips:
  * Be consistent in your naming conventions
  * Suggestion:
    * Tables should be `UpperCamelCased`
    * Columns should be `lowerCamelCased`
    * Avoid pluralizations, abbreviations
  * Make sure every table has a primary key
    * Name it after the table + `Id`
    * Always use an `int`: varchars (casing issues)and doubles are imprecise
  * Foreign keys should have the *same name* as the primary key they reference
  * Strings (`varchar`s) should not be used as PKs or FKs
  * Join tables should be used to model a many-to-many relationship; they can have additional columns if appropriate
  * Be sure to insert plenty of test data into your database to model all different types of relations
  * YOu can add uniqueness constraints and check constraints to enforce good data
  * You can add `key` or `index`es to columns to direct the database to maintain an ordering on that column for *efficient* search of records
  * Inheritance: we *highly recommend* you use a single table inheritance strategy!

## Normalization

* 1-NF, 2-NF, 3-NF
* They build on each other: you cannot have a higher normal form without having ALL lower normal forms
* First Normal Form: "each attribute in a table only has atomic values"
  * Each column only represents ONE piece of data, ONE value
  * Violation: storing multiple pieces of data as a single CSV string: `email1,email2,email3`
  * Violation: multiple columns to support multiple values: 3 columns for 3 emails
  * Be sure to separate data out into separate tables as necessary
* Second normal form: it has to be 1-NF: no non-prime attribute is dependent on a proper subset of prime attributes
  * Using a PK auto-generated, not null gets you 2-NF automatically!
  * Violation: a purchase record that contains `customerId, storeId, storeAddress`
  * If the PK is a combination of `customerId/storeId` the `storeAddress` is only dependent on the second part
  * It is often useful and necessary to define combination keys, but *keep them secondary*!
  * You split everything out into its own table
* Third Normal form: has to be 2-NF (and transitively 1-NF)
  * No non-prime column is transitively dependent on the key
  * Example: store `termsYears, monthlyPayment` for an annuity, but *also* I store the `value = termsYears * monthlyPayment * 12`
  * Storing data that is dependent on other data is *wrong*: because it quickly may become out of sync with the other data
  * Instead: transitively dependent data should be *recomputed* (in a query or in a program)
  * It gives a failure point: it introduces a possible data anomaly (if one value changes then the other value(s) may need to do so as well)
* Every non-key attribute must provide a fact about the key (1NF), the whole key (2NF) and nothing but the key (3NF) so help you Codd

## Misc

* There is a LOT more
  * Triggers, Views, Stored Procedures, Variables, etc
  * Transactions: all or nothing series of queries
  * ACID:
    * Atomicity: the entire transaction happens or NONE of it does
    * Consistency: Before and after the transaction, all database rules will hold
    * Isolation: each transaction occurs in isolation of all others
    * Durability: after a catastrophic failure, all database rules will still apply
* Security issues: do not store sensitive info in a database unhashed/unencrypted: it is even better to offload/outsource authentication to a third party (google, github, etc.)
* Soft vs Hard deletes: a hard delete is a result of a `delete` statement.  A soft delete involves defining a boolean column `isActive` that is true if the record is active, false if it is "deleted": it can always be "restored" or undeleted by setting `isActive` to `true` again!

# Programmatically Connecting to a Database & Processing Data

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

### Observations

* ORMs = Object Relational Mappsing Systems; Java: JPA = Java Persistence API
* When executing a `PreparedStatement`:
  * Use `executeQuery()` if you expect a `ResultSet`
  * Use `executeUpdate()` if you are inserting/deleting/updating data in the database
* Do some basic data validation: both in terms of nullable columns and to ensure that records are unique *if they need to be*
* If doing multiple inserts (emails): only use ONE prepared statement (over and over), don't close/reopen unnecessarily
* If you need generated key values back from an insert statement, use `Statement.RETURN_GENERATED_KEYS` and `ps.getGeneratedKeys();` to retrieve them

## Best Practices

### Avoid the star operator

* Example: don't do `select * from Person` in JDBC
* This sends ALL data over the wire (network) whether you want it or not
* It makes it brittle to database changes: suppose someone adds a BLOB column (image for the person)
* Be more intentional: only select the columns you are actually going to use so that you are not sending redundant or useless data over the network
* Future proofs you: if someone comes in and adds a new column with a lot of data `blob`s = binary large objects

### Security Issues

* For this course (only) are we storing the password in a Java source file
* This is unfortunately common
* Number one github.com attack: look at all public repos for variables named "password"
* For this course: it is okay
* Advanced solution: setting up a "data source"
* Firewall everything BUT trusted servers (network admins)

### Close Your Resources!

* Failure to close resources: `ResultSet`s, `PreparedStatement`s and `Connection`s: you will eventually run out of connections
* Make sure you do it in the proper order: generally in reverse order than what you opened them in
* Don't close a resource until you are done using
* It can be very difficult to track down which piece of code is not closing a resource!

### Dealing with `SQLExceptions`

* Unfortunately: JDBC is quite "mature" and so it uses old-school style *checked exceptions*: ones that you are forced to surround with a `try-catch` and deal with
* Best practice: rethrow as a `RuntimeException`
* Do NOT print anything!  Instead, use a proper logging system (To come)
* CS1 topic: error handling; the two things you do NOT do:
  * quit
  * you don't print anything!

### Always Use `PreparedStatements`

* In general, strings can contain anything including SQL code!
* If you do not use `PreparedStatements` then your queries may be unsanitary
* Instead a prepared statement sanitizes statements from any *injection attack*
* `PreparedStatement`s in Java *sanitize* the inputs for you, ensuring that no SQL injection is possible
* Never use anything else!
* Its just easier and simpler to always use `PreparedStatement`s

### Proper Logging

* No one is sitting at your terminal watching for standard output error messages, ready to jump into action
* NEVER ever use the standard output to log error messages, information, debug statements, etc. in a real system
* Instead: use a proper logging system
* Features:
  * Support multiple "levels" of logging: `DEBUG, INFO, WARN, ERROR`
  * Supports event-based actions such as emailing an admin when an error occurs
  * Supports multiple outputs: file-based outputs, database-based outputs
  * Supports file management: daily rolling file, size-based rolling files, etc. 


```text









```
