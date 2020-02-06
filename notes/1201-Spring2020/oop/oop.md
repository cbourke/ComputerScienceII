# Computer Science II - CSCE 156/156H
## Spring 2020
### Introduction to OOP (in Java)

# Motivating Tutorial

## Objects/Classes

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
* Use *composition* to its fullest: classes can have *instances* of other classes as *member variables* 
* Composition defines a *has-a* relationship: an `Airport` has a(n) `Address`
* YAGNI: take care not to *overengineer* your objects (example: a name object to hold half dozen strings, last, first, middle, other middle, title, suffix, etc.)  
* You Ain't Gonna Need It

* Up to now: our objects (classes) are mere *data containers* or data "holders"

## Overview

* History of OOP
* Facilitates the modeling of real-world entities
* Lends itself to modularity and decomposition
* Clear demarcation of responsibilities 
* ADT = Abstract Data Types: objects are a particular realization of ADTs
* Many OOP languages and many languages adopt OOP aspects: C++, Objective-C, Java and C#), PHP JavaScript, Python have adopted more OOP-style (among other styles)

## Objects

* Formally an object is a general entity characterized by:
  1. Identity: an aspect of the object that distinguishes it from all other instances
  2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
  3. Behavior: the functionality of the object or what it *does* or the services it provides
  
* Communication between objects is done via *message sending*: you simply invoke a method on an object and the object determines which code to run
* Communication is facilitated using the object's *interface*: a Java class's "interface" is its publicly available methods
* Objects are *constructed* instead of assigned
  * In Java, by default a no-arg constructor is provided (it assigns default `null` or zero values to an object's state)
  * In Java if you define a constructor (you can define as many as you want), the default goes away
  * Copy constructors, partial constructors, etc.
* Objects are designed: 
  * A general approach is that "semantics dictate design"
  * You break a real-world entity into smaller parts until...
    * It is simple enough to just represent it directly (String, number, etc.) or:
    * An object already exists for it: just (re)use it
* Generally prefer *composition* when relating objects to each other (alternatively: inheritance, more on that later)
* Avoid the god-antipattern: a class should only know about its own stuff, it should not *know everything, do everything, be everything* 

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* How does `Math.sqrt()` work?
* Who cares?  Procedural abstraction: complex computation is encapsulated inside a function
* You *use* the function, you don't need to worry about its details
* How does `LocalDate` represent a date?
  * 3 integers (year, day, month)
  * ISO 8601 formatted string "2020-02-06T12:04:15:0.000CDT"
  * a single number: unix epoch
  * Who cares?
* Abstraction: you don't need to worry about how an object is *represented* internally,
only how to use it
* Example: how does Java's `String` class work?





