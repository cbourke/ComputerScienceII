# Computer Science II
## Assignment 5 - Project Phase V
## Database Connectivity -- Data Retrieval
---

# Introduction

You will modify the application you developed in the first few phases
to interact with the database you designed in Phase IV. Your application
will be modified to load data from your database rather than from flat
data files. Specifically, you will implement methods to connect to your
database using the Java Database Connectivity API (JDBC), load the data
into your Java objects and produce the same reports.

## Database Connectivity

For grading purposes, the database you designed in the previous
assignment will need to be setup on **your** MySQL account on the `nuros.unl.edu`
server. Your code should be configured to connect and interact with this
database. You should add/remove/modify data to your database as needed
for your own testing purposes, but when we grade your program, your test
cases should be loaded into the database to produce the report. In the
next phase you will add functionality to add and remove data.

# Requirements

You will modify the Java classes you developed in the prior phase to
make the following updates.

The driver class that generates the summary and detail reports will
retain its functionality (in fact, if you had good OOP design, this
and most of the code shouldn't have to be modified at all).  Instead
of reading data from flat files, you will add functionality to
connect to your database, load the appropriate
data and create the appropriate objects. It is highly recommended that
you implement (and reuse) several *factory* methods that retrieve
instances of your defined classes by loading from your database. The
grader will run your JAR file which should make the database
connection and produce the reports.

# Artifacts

You will turn in all of your code and artifacts using the usual process.
Name your JAR file `Project.jar` and hand it in using the webhandin.

# Design Write-up

Your design document draft will be for *both* this assignment and the
next (both data retrieval as well as data insertion/deletion). You will
update and modify your Design Document draft to include details on your
new database API. You must hand in an updated electronic copy 1 week
prior to the assignment due date.

In the new section, make sure to given enough details so that a
technically competent individual would be able to reasonably reproduce
an implementation of your design. Be sure to address at least the
following.

-   How are records loaded from the database and into Java objects?

-   How does your API persist data to your database?

-   What are the various side-effects of each method? That is, what
    other records are consequently removed from the database in order to
    maintain data integrity?

-   Document any additional helper methods that you designed and
    implemented

-   What, if any, data validation do you perform? Where does the
    validation occur? What expectations do you place on the user with
    respect to the API?

-   The API allows a user to submit `null` data--what
    consequences does this have on how have you handled it?

-   Detail your testing strategies and any changes that were made as a
    result

# Rubric (100 pts)

Style (5)

  *  Clean, well-organized and readable code
  *  Meaningful identifier names
  *  Proper use of whitespace
  *  Consistent style
  *  No debugging or dead code remains

Documentation (5)

  *  Class and method-level documentation for all non-trivial methods
  *  Complex blocks of code are commented
  *  No unnecessary or development-style comments remain
  *  Code is otherwise self-documenting

Design (30)

  *  Classes properly map to database tables
    *  Each class is properly loaded
    *  Proper SQL queries are made (joins when appropriate)
    *  Good SQL style is used: wildcards are avoided, aliases used, etc.
    *  Database connections and other related resources are managed appropriately

Correctness (60)

  *  Database is setup and available with the required test data
  *  Program properly executes and displays correct output
  *  Output is valid and well-formatted

Bonus/Honors Items (10 each)

  *  A connection factory used implemented and used
  *  A logging system (log4j or alternative) is used
