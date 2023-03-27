# Databases & SQL
## CSCE 156H - Spring 2023

### Introduction/Overview

* Flat data files are insufficient for storing data
  * Repetition of data
  * Formatting issues
  * Consistency issues: data anomalies
  * Integrity issues
  * Organization problems: not sorted, it is in one big file (no multithreaded or multiuser capability)
  * There is no security!

### Solution: use a RDBMS (Relational Database Management System)

* 1970 Edgar Codd (IBM)
* Key aspects: data is stored tables
* Tables have columns (pieces of data) and rows (records)
* Columns have a *type* and a *name* aka fields
* Each row in a table corresponds to a single record
* Records in a table are *uniquely* identified with a *primary key* (PK for short)
* Records in different tables are *related* to each other through *foreign keys* (FK)
* Foreign keys define relations between tables:
  * One-to-many relation: one record in table $A$ may be related to many records in table $B$
  * Many-to-one: same, but from the perspective of $B$
  * Many-to-many relation: one record in table $A$ can be related to many records in table $B$ and vice versa
  * One-to-one relation: be very suspicious of this

## CRUD Demonstration

* You can interact with a RDBMS through CRUD:
    * Create (`insert`)
    * Retrieve (`select`)
    * Update (`update`)
    * Destroy (`delete`)

### C = Creating Data

* Creating data involves inserting new records into an existing table
* You use the `insert` keyword

```sql
-- insert a publisher record using the hard-coded primary
-- key value 14:
insert into publisher (publisherId, name)
  values (14, "EA Sports");

-- Let the database determine the next available key value:
insert into publisher (name)
  values ("EA Sports");


-- insert a game record, refering the inserted EA Sports record
insert into game (name, publisherId) values ("Maddon 21", 14);

-- insert a game record and refer to EA Sports using a *nested query*:
insert into game (name, publisherId) values ("Maddon 22",
  (select publisherId from publisher where name = "EA Sports"));


  -- insert multiple records with one query:
  insert into game (name,publisherId) values
    ("Madden 21", 14),
    ("Madden 22", 14);

```

## Update Data

```sql
-- this update would change EVERY game record
-- not possible in "safe mode"
update game set name = "Madden 22";

-- update the particular game with the given gameId
update game set name = "Madden 22" where gameId = 22;

```

## Destroying Data

```sql

-- this would delete every single game record
-- it would NOT delete the table itself
-- not possible in safe mode!  Thankfully!
delete from game;

-- delete a single game by providing a gameId:
delete from game where gameId = 22;
delete from game where gameId = 21;
```

### Observations

* Comments are usually denoted with a `--` (two hyphens)
* Be consistent on table name and column name conventions; suggestion:
  * `TableName`s are upper camel cased (NOT plural)
  * `columnName`s are lower camel cased
* In old-school SQL, keywords are `ALLCAPS`, you can use this convention, but why?
  * SQL keywords are case *insensitive*
  * THe old-school way assumed a monochrome monitor
* Whitespace generally does not matter, you can go to the next line and break up long lines to make it more readable
* If you do, align things using proper indentation
* You can use either double quotes or single quotes to denote strings, but be consistent
* Every query ends with a semicolon `;`

## Retrieving Data

* You can pull data out of a database using the `select` statement
* To pull every single record and every single column value you can use a wildcard:

```sql

select * from game;
select name from game;
select gameId,name from game;
-- you can alias columns:
select gameId, name as gameTitle from game;

-- you can us a where clause to specify only certain records
select * from publisher;
select * from game where publisherId = 1;

select * from game where publisherId = 1 or publisherId = 5;
select * from game where publisherId > 1 and publisherId <= 10;

-- strings work too:
select * from game where name = "GTA";
select * from game where name != "GTA";

-- you can use a string wildcard: %
-- % matches any number of characters including zero
select * from game where name like "G%";
select * from game where name not like "% %";

-- the underscore is a *single character* wildcard: _
select * from game where name like "_a%";
select * from game where name like "%_a%";

```

## Aggregate Functions

* You can produce more data using aggregate functions: `min, max, avg, count, sum`
* You can also do basic math: `+, -, *, /`

```sql

select * from availability;

-- find the year in which the oldest game was published...
select min(publishYear) from availability;
-- newest:
select max(publishYear) from availability;
-- mix and match math:
select min(publishYear) * 10 + max(publishYear) from availability;
-- average age of any game in my database:
select count(*) as numberOfTitles from game;
select sum(publishYear) from availability;
select 52000/22;
-- the following will work:
select avg(publishYear) from availability;
```

## ORDER BY clause

* In general both columns and rows in a database are not ordered
* When you select data out, it may come in any order that the database feels is most efficient
* You can impose an ordering on the resulting data (result sets) using the `order by` clause

```sql

select * from game;
-- ascending is the default
select * from game order by name;
select * from game order by name asc;
-- descending:
select * from game order by name desc;

-- you can do a combination of columns:
select * from game order by publisherId desc, name asc;
```

# Misc

* The `distinct` keyword allows you to eliminate duplicates and results in unqiue values: `select distinct publisherId from game;`
* The `in` clause allows you to treat values or *another* query as a set

```sql


select * from game where publisherId = 1 or publisherId = 5 or publisherId = 8;
select * from game where publisherId in (1, 5, 8);
select * from game where publisherId in
  (select publisherId from publisher where name = "LucasArts" or name = "Valve");
```

## Data Projections

```sql

-- projections:
-- goal: find out how many games each publisher published
select count(*) from game;
select publisherId, count(gameId) as numberOfGames from game group by publisherId;
```

$$A = \{a, b, c\}, B = \{1, 2\}$$

$$A \times B = \{(a, 1), (a, 2), (b, 1), (b, 2), (c, 1), (c, 2)\}$$

$$|A \times B| = |A| \cdot |B|$$

## Joins

* You can join two tables together using a `join` statement
* Order matters: you can join table $A$ to $B$ or $B$ to $A$

```sql

-- pure cross join: joins every record in A to every record in B
select * from game join publisher;

-- join only on hte publisher ID's matching:
select * from game join publisher
  on game.publisherId = publisher.publisherId;

-- you can also use *table aliases*
select * from game g join publisher p
  on g.publisherId = p.publisherId;

-- combine a join and a group by to get the original report:
-- a list of publisher along with the number of titles they've published
select p.name, count(*) as numberOfTitles
  from game g
  join publisher p
  on g.publisherId = p.publisherId
  group by g.publisherId;

-- incorrect order: you go from game to publisher
-- but it is not possiblef or a game to exist without a publisher
select p.name, count(*) as numberOfTitles
  from game g
  left join publisher p
  on g.publisherId = p.publisherId
  group by g.publisherId;

-- reverse the order: publisher to the game:
select *
  from publisher p
  left join game g
  on g.publisherId = p.publisherId;

-- perform the same projection and aggregation:
select p.name, count(g.gameId) as numberOfTitles
  from publisher p
  left join game g
  on g.publisherId = p.publisherId
  group by p.publisherId;
```

$$A = \{a, b, c\}, B = \{c, d, e\}$$
$$A \cup B = \{a, b, c, d, e\}$$

```sql

-- final exercise: flatten the entire data model into a "CSV-like" query

-- join all four tables together:

select * from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId;

-- reversed query: preserve records *from the right*
select * from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform plat on a.platformId = plat.platformId;

-- want to combine two result SETS
-- preserve records in both directions and
-- union them together
select * from publisher p
  left join game g on p.publisherId = g.publisherId
  left join availability a on g.gameId = a.gameId
  left join platform plat on a.platformId = plat.platformId
union
select * from publisher p
  right join game g on p.publisherId = g.publisherId
  right join availability a on g.gameId = a.gameId
  right join platform plat on a.platformId = plat.platformId;



```

# Creating/Designing Databases

* Design a database to support our little financial asset application: support owner (person), their emails (any number), accounts: annuity, savings
* Variation: we now want to support *joint* accounts: accounts with more than one owner
* How should we handle inheritance?  
   * **Should we have 1 table for all type of accounts?**
   * Should we have 2 tables for each of the non-abstract account types?  
   * Should we have 3 tables one for each class?



```sql

drop table if exists Ownership;
drop table if exists Email;
drop table if exists Person;
drop table if exists Account;

-- by default, all values are "nullable"
-- "not null" specifies that they cannot be nullable
create table if not exists Person (
  personId int not null primary key auto_increment,
  -- allowed to be null (TODO: reconsider this)
  firstName varchar(255),
  lastName varchar(255) not null,
  birthDate varchar(10) not null default "0000-00-00"
);

insert into Person (personId,firstName,lastName) values
  (10, "Chris", "Bourke"),
  (20, "Anita", "Borg"),
  (30, "Magaret", "Hamilton"),
  (40, "Alan", "Turing");

create table if not exists Email (
  emailId int not null primary key auto_increment,
  address varchar(255) not null,
  personId int not null,
  foreign key (personId) references Person(personId)
);

create table if not exists Account (
  accountId int not null primary key auto_increment,
  accountNumber varchar(255) not null unique key,
  type varchar(1) not null,
  -- Savings account data:
  balance double,
  apr double,
  -- Annuity data:
  monthlyPayment double,
  termYears int,
  constraint `validApr` check (apr >= 0 and apr <= 1)
);

create table if not exists Ownership (
  ownershipId int not null primary key auto_increment,
  personId int not null,
  accountId int not null,
  foreign key (personId) references Person(personId),
  foreign key (accountId) references Account(accountId),
  constraint `uniqueOwnershipPair` unique (personId,accountId)
);


```

# Observations

* Naming convention: be consistent
* Suggestion: `UpperCamelCasing` (singular form) for table names, `lowerCamelCasing` for columns
* Make sure every table as a PK `int not null auto_increment` and name it after the table: `tableName + Id`
* Make sure that foreign keys match the name of the table/column they refer to
* avoid using strings for PK and FK: you have too many casing and encoding issues to deal with
* Join tables can be defined with multiple foreign keys to model a many-to-many relation
* Be sure to insert plenty of test data into each and every table!
* Check and uniqueness constraints can be used to enforce *data integrity*
* Have good normalization

## Normalization
* 1-NF, 2-NF, 3-NF
* First Normal Form: "each attribute in a table has only atomic values"
  * Each column in a table only holds ONE piece of data, ONE value, NOT multiple
  * Violation: storing multiple emails for a person in an "email" column and separting them using commas (CSV data in a RDBMS)
  * Violation: storing emails in enumeratd columns: `email1, email2, email3`
  * Simply define another table to model any one-to-many relation
* Second Normal Form: it has to be 1-NF and: "no non-prime attribute is dependent on a proper subset of prime attributes"
  * Using a PK auto-generated, non-null key gets you 2-NF for free
  * Violation: suppose we stored a `customerId, storeId, storeAddress` all in one table and defined a PK as `customerId/storeId`
  * `storeAddress` only depends on *part* of the PK: violation of 2nd normal form
  * You split everything out into its own table
  * Often you *do* want to define a compound key, but it should almost NEVER be a PK
* Third Normal form: 2-NF (by transitivity, 1-NF): no non-prime column is transitively dependent on the key
* Suppose you stored `monthlyPayment` AND `termYears` AND `totalValue` into the `Account` table
* Store only *DATA* NOT behavior (derived data)
* It would be a violation of 3-NF to store `totalValue`
* Every non-key attribute must provide a fact about the key (1NF), the whole key (2NF) and nothing but the key (3NF) so help you Codd

## Misc

* There is a lot more to learn about
* There are other nice practical things:
  * Triggers
  * Views: read-only access to a table, series of tables or part of a table
  * Stored Procedures
  * Loops, variables (cursors) etc.
  * Temp tables (temporary tables)
* Security Issues:
  * Don't store sensitive data in a database (unencrypted or unhashed)
  * In general: don't roll your own!
* Soft vs Hard deletes: a hard delete is when you use the `delete` query to remove data (no undo, no recycle bin, etc.); soft delete: add an `isActive` column to a table that you set to true/false
* OOP vs Relational model
  * OOP model allows *inheritance*
  * RDBMs do not have inheritance
  * You need a way to resolve the difference in these two models
  * You can create a table per class/subclass
  * You can create a table per stub class: only one table per subclass that has no subclasses
  * You can use a "single table inheritance strategy"
  * You can create one table for all subtypes and use a *discriminator column*
  * you use a string to indicate the subtype of the class.  
  * Some columns may be relevant to some subtypes, others may irrelevant; simply allow them to be null to model this

## JDBC: Programmatic Database Access

* We want to be able to connect to a database and process data programmatically (using Java)
* You typically use a database connectivity API (Appliation Programmer Interface)
* The API is generally *generic* and does not connect to a particular database (MySQL vs MariaDB vs MSSQL vs Informix, PostgreSQL)
* Dependency Inversion in practice!
* Vendors (Oracle, IBM, etc.) provide a *driver* library that conforms to the API
* JDBC provides:
  * `Connection`
  * `PreparedStatement`
  * `ResultSet`
* ORMs (Object-Relational Mappings) systems also exist (JPA, jOOQ)

### Process

1. Create a connection to your database: need user name, password, URL
2. Create/prepare your query
  - prepare the query
  - execute the query
3. Process the results
4. Close your resources

## Best Practices

### Avoid the star operator

* Using the SQL `*` operator selects ALL column values and sends them over the wire, taking network bandwidth!
* Don't waste bandwidth!
* If you are not going to use or need the data, don't query it
* Only query that which you need
* Cut down on redundancies: joins mean that redundant data is transmitted
* not using the `*` operator protects you from weird database changes

### Security Issues

* For this course (only) are we storing the password in a Java source file
* This is actually (unfortunately) common
* For this course: use a password you do not care about!
* In practice: you don't store the password you either:
  * Define a "data source" or
  * You set it up to enter it ONCE without storing it

### Close Your Resources!

* Failure to close your resources wastes them
* You may *runout* of connections!
* make sure to close them in the proper order

### Always Use `PreparedStatements`

* In general, strings can contain anything including SQL code!
* Without sanitizing your code, you are susceptible to an *SQL Injection* attack: a user *may* be able to execute arbitrary SQL code on your database!
* `PreparedStatement`s in Java *sanitize* the inputs for you, ensuring that no SQL injection is possible
* Never use anything else!
* Its just simpler to use on method to connect to a database

### Other items

* Proper Logging Systems
* Better connection management: a connection pool!
* JPA (later)

# Concurrency

* Asynchronicity: a task does not *block* the remainder of the program
```
task1;
task2;
async task3;
task4;
```

* Multithreading: multiple threads running independent (may be on one core,
  allowing for scheduling, waiting processes; waiting on I/O)
* Parallel Computing: multiple processes or threads running on different cores
    (generally same machine) at the same time
* Benefits:
  * Efficiency: Time $\rightarrow$ Time / n
* Java:
  * `Thread` (full thread implementation)
  * `Callable` (returns a result, blocks until done)
  * `Runnable` (does not return a result)
  * `Future` (provides a "future" value, runs at its convenience, blocks when
  asked for a result that is not complete)


```text











```
