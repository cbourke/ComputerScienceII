# Databases & SQL
## CSCE 156H - Spring 2024

### Introduction/Overview

* Flat data files are insufficient for storing data
  * Bad Formatting
  * Inconsistency
  * NO types: int, double, strings, there are ONLY strings
  * Redundancy of data leading to potential failure points and data anomalies
  * Any processing requires processing the ENTIRE file: extremely inefficient: not sorted, not organized, etc. it is one on giant "BLOB" = "Binary Large OBject"
  * There are file lock issues: no concurrency
  * There is no security: one file is an all-or-nothing thing
  * No integrity of data: you can do anything to any record to any column (no protection of data)

### Solution: use a RDBMS (Relational Database Management System)

* 1970 Edgar Codd (IBM)
* Key aspects: data is stored in separate tables
* Tables have columns (pieces of data with a particular TYPE: int, double, varchar) and rows (individual records)
* Each column has a *type* and a *name*
* Each row corresponds to a single record
* Each record in a table is *uniquely* identified a *primary key* (PK) that are generally automatically created by the database
* Records in different tables are *related* to each other through *foreign keys* (FKs)
* Foreign keys define relations between tables:
  * One-to-many relationship: one record in table $A$ may be related to many records in table $B$
  * Many-to-one relationship: same thing but from the perspective of table $B$
  * Many-to-many relationship: one record in $A$ can reference many in $B$ AND vice versa
  * One-to-one relationship: one record in $A$ relates to one record in $B$ (maybe ignore this one)

## Getting Started

* We suggest using mysql workbench: https://www.mysql.com/products/workbench/
* You all have access to a database named after your canvas login (`cbourke3`) on a server named `cse-linux-01.unl.edu`
* Your password should have been sent to you via your huskers email this morning
* DO NOT use this password for anything else, do NOT change your mysql password to something you care about or use for other things
* If you lose it and/or need to change it: you'll need to contact `manager@cse.unl.edu`

# CRUD Demonstration

* CRUD = Create Retrieve Update Destroy
* Four basic operations to any data collection
* You can interact with a RDBMS through CRUD:
    * Create (`insert`) - inserting new records into a table
    * Retrieve (`select`) - pull out records or data from a table
    * Update (`update`) - modifying currently existing records
    * Destroy (`delete`) - removing/deleting records in a table: THERE IS NO, ABSOLUTELY NO UNDO

## C = Create

* Use the `insert` keyword
* Specify the `table`, `(column,names)` and `values`
* You can insert one record at a time...
* You can insert multiple records in one query
* You can hardcode primary key values for *test* data (so you know the values when used as foreign keys)

```sql

-- hard code PK/FK values for test data ONLY!
insert into publisher(name,publisherId) values ("Mojang", 500);
insert into game (name,publisherId) values ("Minecraft", 500);

-- alternatively, but much more work, a nested query:
insert into availability
  (gameId,platformId,publishYear)
  values
  ( (select gameId from game where name = "Minecraft"),
  7, 2009);
```

* Child records cannot exist without a parent record (in a well designed database that is)
* These are foreign key *constraints*: a child cannot exist without a parent
  * A parent record must be created BEFORE the child record
  * Deletion must follow the reverse order

## U = Update

* You use the `update` keyword

```sql

insert into publisher (name) values ("Geerbox");

-- fix the spelling...
-- as it is, this would change EVERY publisher to "Gearbox"
update publisher set name = "Gearbox";
-- unsafe operations like this are prevented in "safe mode"
update publisher set name = "Gearbox" where publisherId = 501;


```

## D = Destroy

* You use the keyword `delete`

```sql

-- deletes EVERY record in the game table
-- DOES NOT delete the table itself
-- not allowed in safe mode :)
delete from game;

delete from availability where gameId = 24;
delete from game where gameId = 24;
delete from publisher where publisherId = 500;
```

## R = Retrieve

* You can pull data out of a database using the `select` statement
* To pull every single record and every single column value you can use a wildcard:

```sql
use cbourke3;

select * from platform;
select * from game;
select * from publisher;
select * from availability;

-- select all game records and all columns of the game table:
-- THe * is a wildcard: it matches EVERYTHING and ANYTHING
select * from game;

-- you can select a subset of columns:
select name from game;
select name,publisherId from game;
-- order does not matter:
select publisherId,name from game;
-- you can also rename (alias) columns temporarily
select publisherId,
       name as title
       from game;

-- aggregate functions can be used:
-- how many game records do we have?
select count(*) from game;
select count(*) as numberOfGames from game;
select count(*) as `number Of Games` from game;

-- others: min, max, sum, avg
select min(publishYear) from availability;
select max(publishYear) from availability;
select avg(publishYear) from availability;
-- glorified calculator:
select now() - (min(publishYear) -1970)*60*60*24*365.25 as age from availability;
select 40 * 4 / 3;

-- where clauses can limit your results
select * from game where gameId = 5;
select * from game where gameId != 5;
select * from game where gameId > 5;
select * from game where gameId >= 5;

-- you can also use strings:
select * from game where name = "Legend of Zelda";
select * from game where name <= "Legend of Zelda";

-- you can use partial string matching using more
-- wildcards
-- % will match any number of characters

-- select games that start with a G
select * from game where name like "G%";

-- select games that end with a s
select * from game where name like "%s";

-- combination of wildcards:
select * from game where name like "%The%";
select * from game where name like "S%a%";

-- another wildcard: _ matches ANY SINGLE character
select * from game where name like "_____";

select * from game where name like "S____%";

-- ordering

-- order doesn't matter (records or columns)
-- asc = ascending order (default)
-- desc = descending order
select * from game order by name;
select * from game order by name asc;
select * from game order by name desc;

-- order by a combination of fields
select * from game order by publisherId, name desc;

-- distinct keyword provides only unique records

select distinct publisherId from game;

-- in clause

select * from game where gameId in (5, 7, 10, 3);

-- or and and keywords:
select * from game where gameId = 5 or gameId = 7 or gameId = 10 or gameId = 3;
select * from game where gameId = 5 or (gameId > 10 and name like "S%");


-- you can order by multiple columns...
select * from game order by publisherId asc, name desc;

select * from publisher;


```

## Data Projections

* Set Theory: a set is an unordered collection of unique elements of *similar type*
* Math:
$$A = \{a, b, c\}$$
$$B = \{1, 2\}$$
$$x \in A$$
$$x \not\in A$$
$$ \overline{A}$$
  $$A \cup B$$
  $$A \cap B$$
$$A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$$
  $$|A| = 3, |B| = 2, |A \times B| = 6$$
$$R \subseteq A \times B$$
* $R$ is a *relation* on the cartesian product of $A \times B$
* A subset; elements are in the relation only if they match some criteria
* $R \subseteq \mathbb{R} \times \mathbb{R}$ such that $(x, y) \in R \iff x < y$
* Consider the "projection" of objects: 3D->2D


```sql

select count(*) from game;
select count(*) from publisher;
select 22 * 31;
select count(*) from game join publisher;

-- a basic "inner" join will join records from
-- table A to table B such that only combinations
-- that match the "on" predicate (clause) are true
select * from game
  inner join publisher
  on game.publisherId = publisher.publisherId;

-- syntactic sugar:
-- you can omit the 'inner'
-- you can use aliases to shorthand a lot
-- you can even omit the "as"
select * from game g
  join publisher p
  on g.publisherId = p.publisherId;

-- changing the order changes the order of the columns:
select * from publisher p
  join game g
  on g.publisherId = p.publisherId;

-- the order does not matter on the predicate
select * from publisher p
  join game g
  on p.publisherId = g.publisherId ;

-- observe: are all the publishers represented?
-- generally: an inner join does not preserve records that
-- have no matches (from A to B)
-- a left (outer) join does preserve recrods:
select * from publisher p
  left join game g
  on p.publisherId = g.publisherId ;

-- yes, there is a right join: it preserves records from B
-- to A: it is best to use left joins and reorder the
-- tables so you read left to right
select * from game g
  right join publisher p
  on p.publisherId = g.publisherId;

-- goal: determine how many games each publisher published...
select p.name as publisher,
  g.name as gameTitle
  from publisher p
  left join game g
  on p.publisherId = g.publisherId ;


-- first attempt: group by publisherId in the game table...
select * from game;
select publisherId,
  count(*) as numberOfGames
  from game group by publisherId;

select p.name as publisher,
  count(g.gameId) as numberOfGames
  from publisher p
  left join game g
  on g.publisherId = p.publisherId
  group by p.publisherId;

-- get a list of all games and their platforms...
select *
  from game g
  left join availability a on g.gameId = a.gameId
  left join platform p on a.platformId = p.platformId;

-- get a list of platforms and all of their games even those
-- that have no games...
select *
  from platform p
  left join availability a on p.platformId = a.platformId
  left join game g on g.gameId = a.gameId;


insert into platform (name) values ("Valve Index"), ("Playdate");
```

$$A \cup B$$
$$A \cap B$$

```sql


-- flatten the entire data model:
select
    p.name as publisher,
    g.name as title,
    a.publishYear as `Year Published`,
    platform.name as platform
  from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform on a.platformId = platform.platformId
union
select
    p.name as publisher,
    g.name as title,
    a.publishYear as `Year Published`,
    platform.name as platform
  from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform on a.platformId = platform.platformId;  
```

## Designing & Implementing a Database

* Demonstration: create a database to model the account (Asset, Annuity, Stock; Person (owners) and their emails)

```sql


use cbourke3;

-- nuke everything:
-- drop database cbourke3;
-- recreate YOUR database: but with no tables
-- create database cbourke3;

drop table if exists Ownership;
drop table if exists Asset;
drop table if exists Email;
drop table if exists Person;

-- models a person
create table if not exists Person (
  personId int primary key not null auto_increment,
  firstName varchar(255),
  lastName varchar(255) not null,
  dateOfBirth varchar(10) default "0000-00-00"
);

create table if not exists Email (
  emailId int primary key not null auto_increment,
  address varchar(255) not null,
  personId int not null,
  foreign key (personId) references Person(personId)
);

insert into Person (personId, firstName, lastName) values
  (1, "Chris", "Bourke"),
  (2, "Babe", "Ruth"),
  (3, "LeBron", "James");

insert into Email (address,personId) values
  ("chris.bourke@unl.edu", 1),
  ("ljames@lakers.com", 3),
  ("LeBron@cavs.com", 3);

create table if not exists Asset (
  assetId int primary key not null auto_increment,
  -- unique ensures that the column value is unique
  -- key (index) ensures that the database maintains a (separate) ordering of
  -- the column values (sorted)
  accountNumber varchar(255) not null unique key,
  -- A = Annuity, S = Stock
  type varchar(1) not null, -- TODO: you could still have "Z" assets
  constraint `supportedAssetTypes` check (type = "A" or type = "S"),
  -- Annuity columns:
  terms int,
  monthlyPayment double,
  -- Stock columns:
  symbol varchar(10) unique,
  sharePrice double,
  constraint `validData` check (
    (type = "A" and terms is not null and monthlyPayment is not null and monthlyPayment > 0) or
    (type = "S" and symbol is not null and sharePrice is not null and sharePrice >= 0)
    )
);

create table if not exists Ownership (
  ownershipId int primary key not null auto_increment,
  personId int not null,
  assetId int not null,
  numShares double,
  constraint `uniquePair` unique (personId,assetId),
  constraint `positiveShares` check (numShares > 0),
  foreign key (personId) references Person(personId),
  foreign key (assetId) references Asset(assetId)  
);

-- annuity test data
insert into Asset (assetId,accountNumber,type,terms,monthlyPayment) values
  (1,"ABC1","A",5,500.00),
  (2,"ABC2","A",10,125.25);

-- stock test data
insert into Asset (assetId,accountNumber,type,symbol,sharePrice) values
  (3,"ABC3","S","GOOG",132.615),
  (4,"ABC4","S","APPH",0.067);
-- error now:  (5,"ABCZZ","Z","APPH",0.067);

insert into Ownership (personId,assetId,numShares) values
  (1,1,null),
  (1,2,null),
  (1,3,31.0),
  (1,4,20.0);

insert into Ownership (personId,assetId,numShares) values
  (3,3,10000);


-- test queries here

select * from Person p
  left join Email e
  on p.personId = e.personId;

select * from Person p
  left join Ownership o on p.personid = o.personId
  left join Asset a on o.assetId = a.assetId;
```

### Observations

* Semantics dictate design: usually you have one table per "entity"
* Style tips:
  * Be consistent in your naming conventions
  * Suggestion: be modern
    * Tables have `UpperCamelCasing`
    * Columns have `lowerCamelCasing`
    * Avoid pluralizations, abbreviations
* Make sure every table has a primary key
  * Name it after the table + `Id`
  * Make it an `int` and never `varchar` (casing issues) nor `double` (precision issues)
* Foreign keys should have the same name as the primary keys they reference
* Join tables should be used to model many-to-many relations
* Insert plenty of test data to ensure your design is correct
* YOu can add uniqueness constraints and check constraints to enforce good data
* You can add `key` to make `index`es to columns to direct the database to maintain an ordering on that column for efficient search of records
* When you need to model inheritance: we recommend using a *single table* inheritance strategy

## Normalization

* 1-NF, 2-NF, 3-NF
* All columns in a table should depend on the key (1NF), the whole key (2NF) and nothing but the key (3NF) so help me Codd
* First Normal Form: "each attribute (column) in a table only has atomic values"
  * Each column represents only ONE piece of data, ONE value
  * Violation: storing multiple emails as a CSV string: `email@a.com,email@b.com,email3@c.com`
  * Violation: storing multiple emails in multiple columns: `emailA, emailB, emailC`, etc.
  * Be sure to separate data out into separate tables as necessary
  * FK goes in the child table: the table that has "many" records
* Second normal form: it has to be 1-NF: no non-prime attribute is dependent on a proper subset of prime attributes
  * Using a PK auto-generated, not null gets you 2-NF automatically!
  * Violation: a purchase record that contains `customerId, storeId, storeAddress`
  * If the PK is a combination of `customerId/storeId` the `storeAddress` is only dependent on the second part
  * It is often useful and necessary to define "combination keys" (keys defined by more than one column), but the should be *SECONDARY* keys, not primary keys
  * You split everything out into its own table
* Third Normal Form: has to be 2-NF (and transitively 1-NF)
  * No non-prime column is transitively dependent on the key
  * Example: storing `termYears, monthlyPayment` for an annuity account but ALSO storing the `value` (`termYears * monthlyPayment * 12` ): storing `value` violates 3NF
  * Because the `value` can be *derived* from the other two
  * It introduces a failure point: it introduces a possible data anomaly (if one value changes then the other value(s) may need to do so as well)

## Misc

* There is a LOT more
  * Triggers
  * Views: read only access to a table(s) and/or columns
  * Stored Procedures, loops, variables, etc
  * Security issues: do not store sensitive info in a database unhashed/unencrypted: offload this to a third party: Google, GitHub, etc.
* Soft vs Hard deletes: a hard delete is a result of a `delete` statement.  A soft delete involves defining a boolean column `isActive` that is true if the record is active, false if it is "deleted
* DBA = DataBase Admin
* Data Science


## Programmatically Connecting to a Database & Processing Data

* In Java we'll use JDBC = Java Database Connectivity API (Application Programmer Interface)
* Getting Started:
  * Download and "install" the Connector/J jar file for MySQL
  <https://dev.mysql.com/downloads/connector/j/>


### Overview

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

#### Process:

1. Create a connection to your database: need user name, password, URL
2. Create/prepare your query
  - prepare the query
  - execute the query
3. Process your results
4. Clean up your resources

## Observations

* JDBC is a LOT of boilerplate CRUD: at least 4 operations per table to support ALL operations of a database
* Solution (in real life): ORM (JPA)
* For select queries use `executeQuery()` which returns a `ResultSet rs` that you iterate over
  * When iterating: use a `while` loop with `rs.next()`
  * If expecting only 1 record or not expecting 0 records, write defensive logic   
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
* unfortunately this is still very common
* crawl all public repos on github.com for variable names like "password"
* In practice:
  * option: define "data source": no password at all, just approve certain apps from certain servers, etc.
  * You can have a password, but it is manually entered
  * You can also firewall out all other servers

### Other Security Issues

* Do not store sensitive info (passwords) in your database itself!
* Especially plaintext
* Do not store database passwords within code (except for this course's project)
* Number one github.com attack: look at all public repos for variables named "password"
* For this course: it is okay
* In real life: setup a "data source"
* Generally trust a server based on IP address and limit access through network solution (firewalling everyone else)

### Close Your Resources!

* Failure to close resources: `ResultSet`s, `PreparedStatement`s and `Connection`s: you will eventually run out of connections
* Make sure you do it in the proper order: generally in reverse order than what you opened them in
* Do not close them until you are done using them
* Close them immediately after you are done with them
* It can be very difficult to track down which piece of code is not closing a resource!

### Dealing with `SQLExceptions`

* Unfortunately: JDBC is quite "mature" and so it uses old-school style *checked exceptions*: ones that you are forced to surround with a `try-catch` and deal with
* Best practice: catch and release: `catch` the exception and rethrow it as a `RuntimeException`

### Always Use `PreparedStatements`

* In general, strings can contain anything including unsanitized SQL code
* If you use `PreparedStatement`s then you are generally safe from that!
* `PreparedStatement`s in Java *sanitize* the inputs for you, ensuring that no SQL injection is possible
* `PreparedStatement`s in Java *sanitize* the inputs for you, ensuring that no SQL injection is possible

### Proper Logging

* No one is sitting at your terminal watching for standard output error messages, ready to jump into action
* NEVER ever use the standard output to log error messages, information, debug statements, etc. in a real system
* Instead: use a proper logging system
* Features:
  * Supports multiple levels of logging: `DEBUG, INFO, WARN, ERROR`
  * You can configure it to printout certain levels or above/below a certain level
  * Supports file-based, email-based, database-based logging: it doesn't necessarily go to the standard output, but can be redirected to (say) log files


```text









```
