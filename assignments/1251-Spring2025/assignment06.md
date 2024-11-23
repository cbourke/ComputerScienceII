# Computer Science II
## Assignment 6 - Project Phase VI
## Database Connectivity -- Data Manipulation
---

# Introduction

You will modify the application you developed in Phases I & II to
interact with the database you designed in Phase III. In this phase you
will modify your application to persist data to the database by
implementing an Application Programming Interface (API) to interact with
your database using the Java Database Connectivity API (JDBC).

## Database Connectivity

For grading purposes, the database you designed in the previous
assignment will need to be setup on your MySQL account on the `nuros.unl.edu`
server. Your code should be configured to connect and interact with this
database. You should add/remove/modify data to your database as needed
for your own testing purposes, but when we grade your program, we will
clear out all the data using the API described below (that you must
implement) and add/test using our own data.

# Requirements

You will modify the Java classes you developed in the prior phase to
make the following updates.

The driver class that generates the summary and detail reports will
retain its functionality. In addition, you have been provided with a new
class, `InvoiceData.java` in which we have defined a collection of
methods to interact with your database to persist and delete records.
You will fully implement each one of these methods. To better conform to
encapsulation, you *may* choose to implement helper methods that utilize
your Java classes and then call them from the methods in the
`InvoiceData` class. You may add any additional methods that you
feel will simplify your task, but you may not modify or remove any of
the methods already defined.

The methods you implement will be called by our grading program to clear
out the data and then load our own test case data into your database.
Then your JAR file is then run to produce the reports and verify the
correct output. Thus your JAR file acts as both a library *and* a an
executable program. In this context, our grading program can be viewed
as the "client" program that interacts with your API. That is, your API
is a generalized service that could also be used in a webapp, a GUI
application, a mobile app, etc.

# Artifacts

You will turn in all of your code and artifacts using the usual process.
Name your JAR file `Project.jar` and hand it in using the webhandin.

# Rubric (100 pts)

Style (5)

  *  Clean, well-organized and readable code
  *  Meaningful identifier names
  *  Proper use of whitespace
  *  Consistent style
  *  No debugging or dead code remains

Documentation (0 - this is done for you)

  *  Class and method-level documentation for all non-trivial methods
  *  Complex blocks of code are commented
  *  No unnecessary or development-style comments remain
  *  Code is otherwise self-documenting

Design (20)

  *  Classes properly map to the database
  *  Proper checks are made to ensure data integrity and prevent duplication
  *  All methods properly handle data integrity during deletes
  *  Data is validated in some manner
  *  Database connections are properly managed
  *  Integrity and exceptions are handled properly

Correctness (75)

  *  Submitted JAR must contain code and artifacts and properly run on the grader
  *  All test cases properly execute and display correct output
  *  Output is valid and well-formatted
