# Computer Science II
## Assignment 2 - Project Phase II
## Class Hierarchy Design & Testing
---

# Introduction

In the previous phase you began initial work to support an invoice
management system. In this iteration, you will design your class hierarchy
and relate and extend your existing classes to support the application.
You will add functionality to be able to compute the necessary values to
support the core functionality of the system.  You will implement formal
JUnit testing suites to ensure your design is sound and implementations are
correct.

## Design & Functionality

As a first step you'll need to update your previous classes and/or create
new classes to represent the entities to support the project (including
invoices).  You'll need to implement appropriate inheritance.  

  - Common functionality should be placed in superclasses
  - Specialized data and functionality should be overridden in the subclasses
  - Use `interface`s and `abstract` classes appropriately
  - Add method(s) to support the core functionality of the application
  - Override standard methods (such as `toString()`) in anticipation of
    generating your reports (next phase)

You have flexibility in how you design and implement this phase of
the project. However, you must use good OOP practices. You should
non-trivially demonstrate the proper use of the four major principles:
inheritance, abstraction, encapsulation, and polymorphism. When thinking
of your design, keep the following in mind.

-   What should the public and private interface of each of the classes
    be? Don't make them simple data containers--what methods (behavior
    or services) should each class provide?

-   Think about the state and methods that are common and/or dissimilar
    in each of your objects. What would be an appropriate use of
    inheritance and which methods/state are specific to subclasses?
    What, if anything should the subclasses define or override?

-   What classes should own (via composition or some other method)
    instances of other classes? How are complex relationships made
    between objects?

-   Think about how to utilize polymorphic behavior to simplify your
    code. You should not have to handle similar objects in a dissimilar
    manner if you have properly defined a common public interface.

Object-oriented design is fundamentally different from a top-down
procedural style approach that you may be used to. Rather than breaking
a problem down into sub-parts, we instead do a bottom-up design. We
*first* identify the entities and design classes that can be used as the
building blocks to implement a larger application. You are highly
encouraged to completely understand the problem statement and have a
good prototype design on paper before you write a single line of code.

## Testing Suites

To encourage good design and to verify that your implementations are correct
you will implement several JUnit testing suites.

  - Your test suites will have at least one method per type of item
  - We recommend that you implement the test cases described below, but the
    choice is yours
  - Starter code is provided in the `EntityTests` file

Another test suite will include at least 2 invoices
  - One invoice must consist of **at least 2** items
  - The other must consist of **at least 3** items
  - All 5 types of items must be represented
  - You may *reuse* the values/implementations in the entity test suite
  - Starter code is provided in the `InvoiceTests` file

## Full Examples

### Equipment

Consider an item represented by the following line:

`81f658ef-45b9-4e63-a588-036e9ed236af,E,Backhoe 3000,BH30X2,95125`

If purchased, the customer would be invoiced $95,125.00; with a
rate of 5.25% taxes would be 95125 * .0525 = 4994.0625 = $4,994.06
when rounded to the nearest cent.  The total cost would thus be
95125 + 4994.06 = $100,119.06

#### Lease

Suppose we leased the same equipment from 2024-01-01 to 2026-06-01.
This would be 883 days (inclusive on both dates) total or 883/365 =
2.41917808219 years or 2.41917808219 / 5 = 0.483835616438 when
amortized over 5 years.  With a 50% markup the total cost would be
0.483835616438 * 95125 * 1.5 = 69037.2945205 or when rounded to the
nearest cent would be $69,037.29.  Since this more than $12,500, a
flat tax of $1,500 would also be charged making the total $70,537.29

#### Rental

Suppose we rented the same equipment for 25 hours.  The per-hour
charge would be .1% or 95125 * .001 = 95.125 per hour; for 25 hours
the total would be 95.125 * 25 = 2378.125; when rounded to the nearest
cent it would be $2,378.13.  Applying a tax rate of 4.38% gives a
tax total of 104.161875 = $104.16 when rounded for a total of $2,482.29

### Material

Consider an item represented by the following line:

`93f658ef-45b9-4e63-a588-036e9ed236af,M,Nails,Box,9.99`

This represents nails (material) sold for $9.99 per box.  If
31 boxes were purchased the total would be $309.69.  With a
rate of 7.15%, the total tax would be 309.69 * .0715 = 22.142835
or $22.14 when rounded to the nearest cent.

### Contract

Consider an item represented by the following line:

`38bebacb-a3ca-459e-a71f-8e5826a2f7fa,C,foundation pour,0673a09a-5cc1-4269-88f2-e665c2f3f33c`

This is a contract for a pouring a foundation.  This contract is
serviced by the subcontractor company represented by the UUID
`0673a09a-5cc1-4269-88f2-e665c2f3f33c` which corresponds to the
record in the `Companies.csv` (Mitchell LLC).  If the contract
amount is $10,500, that would also be the total and there would
be no tax involved.

Note that the amount is *specific to an invoice* (and will be specified
in another file in the next phase).

# Artifacts -- What you need to hand in

-   Both JUnit test suite classes must be in the
    `com.vgb` package

-   You must follow the instructions for how to build your project as a
    single JAR file in Appendix B of the Project Overview document.
    Name your JAR file `Project.jar`

-   In addition, you will be writing a design document. The first draft
    of this document is due 1 week prior to this assignment.  A *sketch*
    of your class hierarchy is **required** (this will later be
    replaced with a formal UML diagram).

# Common Errors

Some common errors that students have made on this and similar
assignments in the past that you should avoid:

-   Design should come *first*--be sure to have a well-thought out
    design of your objects and how they relate and interact with each
    other *before* coding.

    -   OOP requires more of a bottom-up design: your objects are your
        building blocks that you combine to create a program (this is in
        contrast with a procedural style which is top-down)

    -   Worry about the design of objects before you worry about how
        they are created.

    -   A good litmus test: if you were to delete your driver class, are
        your other objects still usable? Is it possible to port them
        over for some other use or another application and have them
        still work without knowledge of your driver program? If yes,
        then it is probably a good design; if no, then you may need to
        reconsider what you're doing.

-   Objects should be created via a constructor (or some other pattern);
    an object should not be responsible for parsing data files or
    connecting to a database to build itself (a Factory pattern is much
    more appropriate for this kind of functionality).

-   Classes (at least not all of them) should not be mere data
    containers: if there is some value of a class that depends on
    aggregating data based on its state, this should be done in some
    method, not done outside the class and a field set (violation of
    encapsulation--grouping of data with the methods that act on it). If
    a value is based on an object's state and that state is mutable,
    then the value should be recomputed based on the state it was in at
    the time that the value was asked for.

-   Classes should be designed as stand-alone, reusable objects. Design
    them so that they could be used if the application was changed to
    read from a database (rather than a file) or used in a larger
    Graphical User Interface program, or some other completely different
    framework.

# Rubric (100 points total)

Grading will be based on the following items

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

Design (60)

  *  Classes are implemented and utilized properly
  *  Classes are well-designed
  *  Proper use of Encapsulation
    *  Related functionality and data are properly grouped in associated classes
    *  Each member field has an appropriate type
    *  Each class properly models the entity it represents
    *  Constructors are appropriately defined and used
    *  Accessor (and maybe mutator) methods are properly implemented
  *  Proper use of Abstraction
    *  Classes have non-trivial methods and are not just data containers
    *  Use of a class does not require knowledge of its internal state
  *  Proper use of Inheritance
    *  Hierarchy of inheritance makes sense, not over-engineered
  *  Proper use of Polymorphism
    *  At least 1 instance of polymorphic behavior

Test Suite (30)

  *  All required test cases are implemented and run without errors
  *  All tests must pass
  *  Submitted JAR must contain code and artifacts and properly run on the grader
  *  All test cases properly execute and display correct output
  *  Output is valid and well-formatted
