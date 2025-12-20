# Computer Science II
## Assignment 4 - Project Phase IV
## Database Design
---

# Introduction

In the previous phase of this project, you built an application
framework that modeled the given problem and processed data files to
produce reports. In this phase, you will design a data model that will
support your application by designing and implementing an SQL database.

# Database Design

You will design a (MariaDB/MySQL) database to store data that models the problem
in the previous phase. You will need to design your tables to support
data related to the entities that you identified and implemented in the
previous phase as well as the relationship(s) between these entities.

The exact details (including naming conventions, column/field types, and
relational design) are design decisions whose details are your
responsibility. You must follow good database design principles and best
practices. You should make your database flexible enough that records
can be easily added/modified/removed without data integrity problems.

# Requirements

You will implement your database in an SQL file
file (a plain text file containing SQL queries) and thoroughly
test it using your MySQL account on the `nuros.unl.edu` server. Your script should
contain queries to create your tables and to insert a substantial
amount of test data.  You will also write a second SQL script file
that implements queries to pull data from your database.

While designing your database keep in mind the following:

-   What tables are necessary to support the application you designed in
    the previous phase? In each table, what columns are necessary and
    what values should be allowed and/or excluded?

-   How can data integrity be preserved? What primary, non-primary, and
    foreign keys should be defined?

-   What are the relations between tables? What are the many-to-one or
    many-to-many relations? What opportunities are there for further
    data integrity and normalization?

# Artifacts

## Database Schema File

You will hand in an SQL script (name it `database.sql`) that
contains all of the necessary SQL queries to create your database. Be
sure that you do the following:

-   Use conditional `drop table if exists` statements to clear out
    old versions of tables with the same name.

-   Do *not* include a `use database` statement in your final
    script file. We will be running your script on our own database
    instance and will not have access to *your* database.

-   After creating your tables, include all necessary insert statements
    to populate your database with some test data.  This could be the
    same data as in the test cases used in the previous phase if you wish.
    You may even use a tool to convert your CSV files to SQL insert
    statements.

## Database Entity-Relation Diagram

You are also required to produce an Entity-Relation (ER) diagram of your
tables.   You can easily generate a diagram with MySQL Workbench.  Make
sure your diagram is easily readable and export it as a PNG file.  Name
the file `database.png` and submit it with your scripts.

## Database Query File

In a separate file (name it `queries.sql`), write queries to
perform the following operations. This should be considered as a bare
minimum of the testing that should be done.

1.  A query to retrieve the main attributes of each person (their UUID,
    and last/first name)

2.  A query to retrieve the major fields for every person including
    their email address(es)

3.  A query to get the email addresses of a specific person

4.  A query to change the email address of a specific email record

5.  A query (or series of queries) to remove a specific person record

6.  A query to get all the items on a specific invoice record

7.  A query to get all the items purchased by a specific customer

8.  A query to find the total number of invoices for each customer
    *even if they do not have any**

9.  A query to find the total number of sales made by each salesperson;
    do not include anyone who has zero sales

10. A query to find the subtotal charge of all **equipment** purchased
    in each invoice (hint: you can take an aggregate of a mathematical expression).
    Do not include taxes.

11. A query to detect invalid data in a invoice as follows. When a customer
    purchases **equipment** they buy a certain number of units.  It should
    not be the case that they buy (say) 20 monitors and then another 30
    monitors in two different records.  Instead there should be one record
    of 50.

    Write a query to find and report any invoice
    that includes multiple records like this.  If your
    database design prevents such a situation, you should still write
    this query (but of course would never expect any results).

12. Write a query to detect a potential instance of fraud where an
    employee makes a sale to their own company (the same person is the sales
    person as well as the customer's primary contact).

Clearly number these queries (using comments) in your SQL script and add
any additional testing queries at the end.  A starter file has been provided.


## Design Write-up

You will update and modify your design document draft.
In the database design section, make sure to given enough details so that a
technically competent individual would be able to reasonably reproduce
an implementation of your design. Be sure to address at least the
following.

-   You are required to include *at least a sketch* (hand written is fine
    as long as it is readable) of your database design as an ER digram.
    You *may* include a fully generated ER diagram as an image if you wish.

-   What tables and fields are in your database?

-   What are its primary and non-primary keys?

-   How are your tables related? What foreign key constraints did you
    use?

-   How does your database design support the application and problem
    statement requirements?

-   You must also include an Entity-Relation diagram for your database

# Tips & Common Errors

-   Make sure that your tables are normalized to a reasonable degree.

-   External identifiers should remain external. Alphanumeric
    identifiers from sources "outside" the database (not generated or
    managed by the database) do not necessarily make good primary or
    foreign keys

-   A relational model is not the same as an object model since there a
    relational model is data only and there is no behavior nor inheritance.
    You are recommended to use a single-table inheritance strategy where a
    *discriminator column* is used to determine which subtype the record
    corresponds to.

-   Keys should be integers, not floats nor `varchar`; though
    `varchar` columns may be made unique if appropriate

-   Take care that you choose the correct data types for each of your
    columns

-   Be careful with keys and relations between tables: every table
    should have unique auto-generated primary keys; every relationship
    between tables should have a proper foreign key.

-   Be careful when defining nullable fields--what can/should be allowed
    to be null and what shouldn't?

-   Your data model should be general--it should reasonably handle and
    model situations that may not necessarily be accounted for in your
    (or our) test cases

# Rubric (125 total)

Style (5)

  *  Clean, well-organized and readable code
  *  Meaningful table and column names
  *  Consistent naming conventions
  *  Proper use of whitespace

Documentation (5)

  *  Minimum of comments provided in the DDL

Design (50)

_Database Design_
  *  Column types make sense and properly model the problem
  *  Data integrity is enforced by database definitions (nullity and key definitions)
  *  Design allows for extensibility and flexibility
  *  Database is well-designed

_Primary & Foreign Keys_
  *  Keys are well-defined
  *  Non-relational or external data are not used as internal keys
  *  Non-primary keys enforce uniqueness when appropriate
  *  Foreign Keys define proper relationships
  *  Key names follow a consistent naming convention
  * -5 to -15 for not handling "natural" keys (codes) properly

Test Case(s) (15)

  *  SQL file properly inserts non-trivial test data for all tables

Query Correctness (50)

  *  SQL file properly executes the specified queries

Bonus/Honors Items (10 each)

  *  Geographical data is normalized (at a minimum, state and zip code fields should be normalized)
