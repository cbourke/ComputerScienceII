# Computer Science II - CSCE 156/156H
## Spring 2020
### Introduction to OOP (in Java)

# Objects/Classes

* Java is an *object-oriented* programming language (OOP)
* Encapsulation:
  1. Grouping of data
  2. Protection of data
  3. Grouping of functionality (methods) that act on that data

* Demonstration: let's design and implement an `Airport` class

## Observations

* Java is a *class-based* object oriented programming language
* Everything in Java:
  * is a class (which you can create or *instantiate* instances of)
  * or belongs to a class (and thus, need to use the dot operator to access)
* In Java, instances of classes (objects) can be created by using a constructor
  * By default, a no-argument "default" constructor is provided by the language
  * If you define your own constructor, the default constructor goes away
  * You can define any number of constructors
  * Use your IDE to its fullest: Eclipse use `Source` -> `Generate Constructor Using Fields`
* In Java, Encapsulation is achieved with the *visibility* keywords:
  * `private` - only the class and instances of the class can "see" the variable (and thus change it)
  * `protected` - only the class and its subclasses can "see" the variable
  * `public` - *any* piece of code can "see" the variable
* Generally, you should make all variables `private` unless there is a Very Good Reason (VGR) to do otherwise
* Use *composition* to its fullest: classes can have ("own") *instances* of other classes as *member variables* 
* Composition defines a *has-a* relationship: an `Airport` has a(n) `Address`
* YAGNI: take care not to *overengineer* your objects (example: a name object to hold half dozen strings, last, first, middle, other middle, title, suffix, etc.)  
* You Ain't Gonna Need It

* Up to now: our objects (classes) are mere *data containers* or data "holders"
* Third part of encapsulation: make your objects functional
* IN general, any method that acts on an object's data belongs *in the class* 

# Overview

* History of OOP
* Many OOP languages: Smalltalk, C++, Objective-C; Java, C#
* Many other non-OOP languages have adopted OOP aspects: Python, PHP, JavaScript
* Facilitates real-world entities and modeling
* Clear demarcation of responsibility and functionality
* Modularity, decomposition

## Objects

* Formally, an object is a general entity characterized by:
  * Identify - an aspect that allows one instance of an object to be distinguishable from another instance of an object
  * State - a collection of properties (elements, attributes, data, fields, *member variables*) that define the object
  * Behavior - describes what an object does or how you can interact with it (methods, functions, etc)

* Communication between objects is achieved through *message sending*: you can invoke a member method in another object without having to worry about the "dispatch details"
* The public methods of an object is its "interface"
* Objects are *constructed* instead of just assigned
* Java uses the `new` keyword to invoke a constructor (method)
* A general approach to designing objects: "semantics dictate design"
  * Don't overengineer (YAGNI)
  * Break down an object into parts until: it is so simple that you can directly implement (`Address`) it or a class already exists (`LocalDate`)
  * Generally rely on *composition* to relate objects to each other:
  an `Airport` owns an `Address`
  * Avoid the god-antipattern: your class design becomes all-knowing, all-seeing all-doing

## Four Pillars

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Breaking Encapsulation

* Don't create mutable `public` variables unless there is a Very Good Reason to do so   
* Don't locate code that involves a class's object outside the class
* Use classes
  
## Abstraction

* Procedural Abstraction: how does `Math.sqrt()` work?
  * Babylonian interpolation method, the EDSAC method, etc.
  * Who cares?
  * A function's details are hidden from us
* Abstraction in OOP: how does `LocalDate` represent a date?
  * 3 integers
  * ISO8601 strings: "2020-02-06T20:33:50+00:00"
  * A single number (unix time, epoch)
  * Who cares? 
* Abstraction in OOP means you do not have to worry about implementation OR representation details
* Another example: how does Java represent a `String`

### Encapsulation

* Encapsulation:
  1. Grouping of data
  2. Protection of data
  3. Grouping of methods that act on that data

* Visibility keywords in Java:
  * `public`: any piece of code can "see" the member variable or method
  * `protected`: any subclass of the class can "see" the member variable or method
  * "package protected": the lack of a keyword makes the member visible to anything in the same package
  * `private`: only the class (and instances of the class) can "see" it
* Best practice: make all of your member variables `private` and control access through constructors and getters/setters
* Best practice: you should never separate the "behavior" of an object with its "state"
* Key idea: Abstraction and encapsulation often work together: abstraction allows access to an object's behavior and encapsulation hides the details

# Inheritance
