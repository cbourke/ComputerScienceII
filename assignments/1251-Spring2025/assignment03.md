# Computer Science II
## Assignment 3 - Project Phase III
## Summary & Detail Report
---

# Introduction

In the previous phase you began initial work to support an invoice
management system. In this iteration, you will continue to design
classes to support the application as well as modify
and extend your previous design to add functionality. You will
add functionality to load data for invoices and produce several reports:

1. The first report will give a summary of *all* invoices along with
   a few totals.

2. The second report will give a similar summary but for *each* customer.

3. The final report will give details for each individual invoice.

# Data Files

The data files for persons, stores, and sale items are the same as in
Phase I. Two new CSV data files are used in this phase to model sales
and items on each sale.  As before, you may assume that all data is valid and
properly formatted and all data files are named as specified.

## Invoices Data File

Data for all sales is contained in the file `data/Invoices.csv`. The
first line contains a header. Each subsequent line contains the following
data, separated by commas.

-   Invoice UUID -- A UUID identifying the invoice

-   Customer UUID -- The UUID of the customer corresponding to the customer
    in the `Companies.csv` file

-   Salesperson UUID -- a UUID code corresponding to the person
    who made the sale

-   Invoice date - the date of the sale (in the format `YYYY-MM-DD`)

An example (also provided in the same `data` directory as the previous phase's data
files):

```text
invoiceUUID,customerUUID,salesPersonUUID,date
8a9bff38-24af-4a0c-b425-bf3a6fa64746,d7d7b071-e63f-4af5-88f3-2e47a09852fb,65678ae9-43e6-48bf-b80f-6b6ec5189c66,2025-01-13
4f6adb47-2fdd-43b5-8ad0-c10bb4116e52,e2b1228a-1066-4f79-99c6-321658ef1587,7f90331d-8d51-43d7-8fab-9d5baf6019bf,2025-02-16
c89f1367-ce43-459e-8988-7e4d8cce83ce,d7d7b071-e63f-4af5-88f3-2e47a09852fb,6056d47e-dd04-46ee-b711-ba49d9d3d38a,2025-02-12
```

## Invoice Item Data File

Data associating invoices and the items on invoices is contained in the file
`data/InvoiceItems.csv`. The first line contains a header. Each subsequent line
contains the following data, separated by commas.

-   Invoice UUID -- the UUID of the invoice the item is on

-   Item UUID -- the UUID of the item (equipment, material, contract).
    Depending on the type of item, the remaining tokens will vary.

    -   For equipment, the next token will be a single character, `P`
        for a purchase; `L` for a lease followed by the start/end dates
        in `YYYY-MM-DD` format; `R` for a rental followed by the number of hours
        it was rented for.

    -   For material, the quantity is the final token

    -   for contracts, the total amount of the contract follows.

### Example

An example follows; note that it will not necessarily be in any particular order.

```csv
invoiceUuid,itemUuid,field(s)
4f6adb47-2fdd-43b5-8ad0-c10bb4116e52,4c0c851d-0466-42c8-8631-0b414db5be98,P
8a9bff38-24af-4a0c-b425-bf3a6fa64746,ac47e0f6-5d5b-49fe-af54-a6625a6374a8,100
8a9bff38-24af-4a0c-b425-bf3a6fa64746,38bebacb-a3ca-459e-a71f-8e5826a2f7fa,10500
8a9bff38-24af-4a0c-b425-bf3a6fa64746,e9afb98d-ac99-4974-a274-2dca5c1238f5,R,20
c89f1367-ce43-459e-8988-7e4d8cce83ce,50e6f93f-3792-459f-b1c6-b512b994ff2b,L,2025-02-01,2027-08-31
c89f1367-ce43-459e-8988-7e4d8cce83ce,72e157ac-5b46-4b3b-b86b-599a89be7289,20
```

In the provided example there are 3 unique invoices:

- `4f6adb47-2fdd-43b5-8ad0-c10bb4116e52` contains a single equipment purchase (bull dozer)
   with a grand total of $132,088.75

- `8a9bff38-24af-4a0c-b425-bf3a6fa64746` contains 3 items: 100 concrete bags,
  a skid steer rental for 20 hours, and a foundation pour contract for $10,500.
  There is a grand total of $14,963.45

- `c89f1367-ce43-459e-8988-7e4d8cce83ce` contains 2 items: a dump truck lease
  and 20 tons of sand (material) for a grand total of $103,675.60

# Requirements

Your program will load the data from all the data files, construct (and
relate) instances of your objects, and produce the 3 reports and output
them to the standard output.

Formatting details are left up to you but your reports should be
readable and communicate enough information to verify that your code is
correct and that you've followed all requirements and specifications.
Your report ***needs to output at least as much information*** as the
grader's expected output. An example has been provided but you are free
to make it prettier, use alternate formatting, and are encouraged to
communicate even more details if you wish.

## Testing

You are expected to thoroughly test and debug your implementation using
your own test case(s). To ensure that you do, you are required to design
and turn in at least one non-trivial test case (CSV files, not formatl
JUnit tests) to demonstrate your program was tested locally. Ultimately your application
will be tested on multiple test cases through the grader. However,
the grader is not a sufficient testing tool for you to use; you don't
have access to the test cases, nor can you debug with respect to it.

# Artifacts -- What you need to hand in

-   Your program *must* be runnable from a class named
    `InvoiceReport.java` which *must* be in the package
    `com.vgb`

-   Your input/output test case files *must* be in a `data`
    directory in your project when you hand it in.

-   You must follow the instructions for how to build your project as a
    single JAR file in Appendix B of the Project Overview document.
    Name your JAR file `Project.jar`

-   In addition, you will be writing a design document. The first draft
    of this document is due 1 week prior to this assignment.

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

Design (25)

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

Correctness (50)

  *  Submitted JAR must contain code and artifacts and properly run on the grader
  *  All test cases properly execute and display correct output
  *  Output is valid and well-formatted

Test Case(s) (15)

  *  A valid, non-trivial test case has been submitted with the correct file names, is well-formatted and correctly executed

Bonus/Honors Items (10 each)

  *  Summary report prints in order of sales total (highest to lowest) *and*
     store summary report prints in order of manager's last name/first name, *then*
     by sales total (highest to lowest)
  *  Test Case submitted 1 week prior to due date
