# Computer Science II
## Assignment 1 - Project Phase I
## Data Representation & EDI
---

# Introduction

Objects represent and model real-world entities. The *state* of an
object consists of the pieces of data that conceptually/semantically
define what that object is. It is often necessary to be able to transmit
objects between different systems which may use completely different
technology stacks. This is usually referred to as Electronic Data
Interchange (EDI) and is achieved by translating objects into a
platform-independent data format such as XML (Extensible Markup
Language) or JSON (JavaScript Object Notation) representations. Once
transferred, the data on the other end can then be translated into an
object in the second system. Different languages and frameworks have
their own terminology for this process (marshaling/unmarshaling,
serialization/deserialization, etc.).

The first phase of the project will focus on the design and
implementation of several Java classes to model the various entities in
the system. For the first phase, your classes may be simple data
containers. Full behavior, methods, and inheritance will be required in
Phase II and you are encouraged to plan ahead for any modifications that
you may need. Your classes will define, conceptually, what each of the
entities are (their data and types and accessor/mutator methods) as well
as provide means for creating and building instances of those entities.

You will also write a parser to process a collection of "flat" data
files containing data on entities in the old system and build instances
of each object. These "flat" files are all in a comma separated value
(CSV) format.  Finally, you will also implement functionality to serialize
your objects into either XML and/or JSON formats (so that data can be
transmitted to the subsystems other teams are building).  

# Data Files

Full examples of each of the following files have been
provided in a separate directory in this repository. However, you will
also be required to develop your own non-trivial test case. In general,
you may assume that all data is valid and properly formatted and all
data files are named as specified. You should assume that all CSV data
files are located in a directory called `data` and output files should be
saved to the same directory.

## Persons Data File

Data pertaining to individual people on the system is stored in a CSV
file in `data/Persons.csv`. The format is as follows: the first
line will contain a header indicating the name of each field.  
Each subsequent line contains comma delimited data fields:

-   UUID - A [Universally Unique Identifier](https://en.wikipedia.org/wiki/Universally_unique_identifier)

-   Name -- the person's name in a `firstName,lastName` format

-   Email Address(es) -- an (optional) list of email addresses. If there
    are multiple email addresses, they will be delimited by a comma

An example:

```text
uuid,firstName,lastName,phone,email(s)
a74cc7d9-7ab5-47b8-84cd-9a9205f7ffe7,Bety,Grof,bgrof@gmail.com,bgrof2@unl.edu
7b70a695-33c1-4a1a-9c95-51054f4017f6,Finn,Mertens,fth@gmail.com
840397ce-4ea9-401d-9afd-bd8c0b68f4fd,Simon,Petrikov,drpet@unl.edu,iking@ooo.com,spetrikov@unl.edu
d6388b8b-b36b-4ca0-9f3d-48e86e78f79f,Marceline,Abadeer
```

## Companies Data File

Data pertaining to each company on the system is stored in a CSV file in
`data/Companies.csv`. The format is as follows: the first line will
contain a header indicating the fields.  Each
subsequent line contains a comma delimited data fields:

-   UUID

-   Contact UUID -- the UUID of the person who is the primary contact for
    the company (as specified in the Persons data file)

-   Name - the name of the company

-   Address -- the physical address of the store in the same format:
    `street,city,state,zip`

An example:

```text
companyUuid,contactUuid,name,street,city,state,zip
d7d7b071-e63f-4af5-88f3-2e47a09852fb,30f42bcf-2556-491e-8c0c-4e52d7dcfd90,Weissnat and Okuneva,531 Bunting Way,Pasadena,CA,91131
e2b1228a-1066-4f79-99c6-321658ef1587,96aa54a4-f452-405c-82b2-12f262095f75,Abernathy-Schamberger,243 Oriole Avenue,Seattle,WA,98148
d631d183-5276-49cd-8136-ba130df24826,cc88a3dc-64d6-4070-b9b0-b2ae9ace0061,Jacobi and White,46 Ruskin Lane,Amarillo,TX,79176
a8bae950-96ac-43a9-9a29-729db552e981,ddd24cee-31fa-48ac-b311-04fc329bb03b,Klocko Inc,0 Ridge Oak Trail,Washington,DC,20566
0673a09a-5cc1-4269-88f2-e665c2f3f33c,bc0d9b2c-39ad-4c01-a835-02cfe79bf43e,Mitchell LLC,5887 Bobwhite Trail,Philadelphia,PA,19136
```

## Items Data File

Data pertaining to each available item (equipment, material, contract)
is stored in a CSV file in `data/Items.csv`. The format is as follows:
the first line will contain a header.  Each subsequent line contains
comma delimited data fields that include:

-   UUID - unique to the item

-   Type - A single character indicating the type: `E` for equipment,
    `M` for material, `C` for contract

-   Name - a name/description of the item

Then, depending on the type of item there will be several other fields
each separated by a comma:

-   For equipment: a model number and retail price

-   For material: the unit and the cost per unit

-   For contracts: a UUID corresponding to the company that VGB subcontracts
    with (as specified in the Companies data file)

An example:

```text
uuid,type,name,field(s)
2dd5220e-6779-4fe7-81d2-b79dcbff3415,E,Dragline,DX200b,347125
4c0c851d-0466-42c8-8631-0b414db5be98,E,Bulldozer,XPXY4,125500
984bac8d-96f7-4d6b-9948-1cabae15ba51,E,Compactor,FLT100,4500
50e6f93f-3792-459f-b1c6-b512b994ff2b,E,Dump Truck,DUMPRX5000,90450
e9afb98d-ac99-4974-a274-2dca5c1238f5,E,Skid-Steer,S4325,8500
ac47e0f6-5d5b-49fe-af54-a6625a6374a8,M,concrete,bag,40
72e157ac-5b46-4b3b-b86b-599a89be7289,M,sand,ton,1500
97dc9f91-c009-4e8d-b08c-3d4d0ded0bad,M,nails,box,15
30bebf58-ba44-4bef-885f-8fccb7db9544,M,lumber,lft,8
8c8bbffa-a939-44f4-98b7-66837e7d16e9,M,drywall,sheet,35
38bebacb-a3ca-459e-a71f-8e5826a2f7fa,C,foundation pour,0673a09a-5cc1-4269-88f2-e665c2f3f33c
699607c3-d9db-4aef-a8ab-b3a6cab21e12,C,footing pour,0673a09a-5cc1-4269-88f2-e665c2f3f33c
ce9fc7f6-8be8-48cb-9236-7873dbf9b2b7,C,concrete pad pour,0673a09a-5cc1-4269-88f2-e665c2f3f33c
97ee84b8-dc93-4db2-84bd-48e72a7f3131,C,electric install,a8bae950-96ac-43a9-9a29-729db552e981
b118fa82-14cf-46f4-8574-41e583badc27,C,generator install,a8bae950-96ac-43a9-9a29-729db552e981
```

# Requirements

You are required to design Java classes to model the problem above and
hold the appropriate data. The classes you design and implement, their
names, and how they relate to each other are design decisions that you
must make. However, you should follow good object oriented programming
principles. In particular, you should make sure that your classes are
designed following best practices.

In addition, your program will load data from the CSV files, construct
(and relate) instances of your objects, and serialize the data into
output files. It is your choice as to which format you produce, XML or
JSON (or both for extra credit).

For XML, your output file names should be `Persons.xml`,
`Companies.xml` and `Items.xml`; for JSON, your output files
should be `Persons.json`, `Companies.json` and
`Items.json` respectively. Your output files should be placed in
the same `data` directory as the input files.

There is no need to define a rigorous schema in either case. However,
your XML/JSON should be well-formatted and valid. You should follow the general
structures in the examples provided, though some flexibility is allowed
for tag and element names. However, the output should *conceptually*
match the expected output. It must also pass any and all validation
tests (using the validators listed below). In addition, you may (in fact
are *encouraged* to) use a library to serialize your Java classes to XML
or JSON. Some common libraries and more information on each
of the formats can be found with the following resources:

-   XStream, a light-weight XML binding framework (recommended for XML):
    <http://x-stream.github.io/>  

    - Download: <http://x-stream.github.io/download.html> (you'll likely
      want the "XStream Core only" JAR and the "XmlPull" JAR files)
    - Tutorial: <http://x-stream.github.io/tutorial.html>

-   Google-gson (recommended for JSON) library to convert between Java
    objects and JSON: <http://code.google.com/p/google-gson/>  

    - Download: <https://mvnrepository.com/artifact/com.google.code.gson/gson>
    (click the latest version, then in "files", download the JAR file)
    - Tutorial: <https://howtodoinjava.com/gson/pretty-print-json-output/>

-   An XML Validator: <http://www.w3schools.com/xml/xml_validator.asp>

-   A JSON Validator: <http://jsonformatter.curiousconcept.com/>

# Process

For this initial phase, your objects may be simple data containers since
the only thing you are doing with them is parsing data files, creating
object instances, and exporting them in a different format. However,
much of the code you write in this phase will be useful in subsequent
phases, so ensure that you have well-designed, robust, bug-free and
reusable code.

## Testing

You are expected to thoroughly test and debug your implementation using
your own test case(s). To ensure that you do, you are required to design
and include at least one non-trivial test case to demonstrate your
program was tested locally to some degree. Ultimately your application
will be tested on multiple test cases through the grader including those
handed in by your peers.

Understand that the grader is *not* a sufficient testing tool.
grader is a *black-box* tester: you don't have access to its
internals, nor to the test cases, nor can you debug with respect to it.

Also understand what a test case is: it is an input-output that is known
to be good using a method independent of your program (calculated or
verified by hand). We will use your test case when grading other
assignments, so you are encouraged to be adversarial: design test cases
to probe and break "bad" code, but stay within the requirements
specified above.

There are many tools available that will help you generate test data.
Some examples:

-   Mockaroo: <http://www.mockaroo.com>

-   Generate Data: <http://www.generatedata.com>

-   Online Converter: <https://www.convertcsv.com>

# Artifacts -- What you need to hand in

-   Your program *must* be runnable from a class named
    `DataConverter.java` which *must* be in the package
    `com.vgb`

-   Your input/output test case files *must* be in a `data`
    directory in your project when you hand it in.

-   You must follow the instructions for how to build your project as a
    single JAR file in Appendix B of the Project Description document.
    Name your JAR file `Project.jar`

-   In addition, you will be writing a design document. The first draft
    of this document is due 1 week prior to this assignment.

## Common Errors

Some common errors that students have made on this and similar
assignments in the past that you should avoid:

-   Design should come first--be sure to have thought out a design for
    your objects and how they relate and interact with each other before
    coding.

-   OOP requires more of a bottom-up approach: your objects are your
    building blocks that you combine to create a program. This is in
    contrast with a procedural style which is top-down.

-   Worry about the design of objects before you worry about how they
    are created.

-   A good litmus test: if you delete your driver class, are your other
    objects still usable? Is it possible to port them over to some other
    uses or another application and have them still work without
    knowledge of your driver program? If yes, then it is probably a good
    design; if no, then you may need to reconsider what you're doing.

-   Objects should be created via a constructor (or some other pattern);
    an object should not be responsible for parsing data files or
    connecting to a database to build itself (a Factory pattern is much
    more appropriate for this kind of functionality).

-   Encapsulation should be respected. Appropriate data fields and
    appropriate types should be defined for each class. Visibility
    should be restricted with access done through accessor/mutator
    methods. Any methods or functionality that acts on a class's data
    should be encapsulated in the class (unless usage of an external
    library makes it inappropriate). If a value is based on an object's
    state and that state is mutable, then the value should be recomputed
    based on the state it was in at the time that the value was asked
    for.

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

Design (25)

 *  Classes are implemented and utilized properly
 *  Classes are well-designed
    *  State and interface make sense
    *  Encapsulation is respected
    *  Each member field has an appropriate type
    *  Each class properly models the entity it represents
    *  Constructors are appropriately defined and used
    *  Accessor (and maybe mutator) methods are properly implemented
 * Good SOLID principles
    * -5 to -10 if your `DataConverter` class violates the single-responsibility principle and is a "god class"
    * -5 for bad modeling (not defining a class when appropriate)
    * -5 for bad modeling (not using a class when appropriate)
    * -5 to -15 for breaking encapsulation depending on the severity
 * Points will not be deducted for the following, but:
    * Inheritance is not necessary for this phase, but it will be for the next; don't design yourself into a corner
    * You should prefer dynamic data structures, `List`s or `Map`s instead of arrays
    * You should prefer immutable objects *in general*; avoid setters

Correctness (50)

 *  Submitted JAR must contain code and artifacts and properly run on the grader
 *  All test cases properly execute and display correct output
 *  Output is valid and well-formatted

Test Case(s) (15)

 *  A valid, non-trivial test case has been submitted with the correct file names, is well-formatted, and correctly executes

Bonus/Honors Items (10 each)

 *  Both XML and JSON formats are used
 *  Test Case submitted 1 week prior to due date
   * All test case files (csv and xml or json) must be handed in through the webhandin
     inside your JAR file as specified.  They must all be well-formatted and **may not
     be copies of ours**
