# Computer Science II - ECEN 156
## Spring 2025
### Object Oriented Programming

# Motivating Introduction

* Isotope Class Demo

### Creating and Designing Classes

* Java is a "class"-based object oriented programming language
* Everything in Java is a class or belongs to a class
* A `class` in Java:
  * Allows you to model a "real world" object or idea or "entity"
* Classes allow for "Strong Encapsulation":
    1. The grouping of data
    2. The grouping of behavior (methods/functions/functionality) that acts on that data
    3. Protection of that data
* Generally speaking:
  * Book things belong in the `Book` class, Person things belong in the `Person` class, etc.
  * You should have one class per entity
  * You should protect your variables by making them `private` unless there is a Very Good Reason not to
* Generally look for ways to use *composition*
  * Composition: a class is *composed* of other classes
  * Example: a `Book` class owns an instance of a `Person` class (author)

## Objects

Formally an object is a general entity characterized by:
1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality of the object or what it *does* or the services it provides (public methods in the object)

* Communication between objects is done via "message sending": you invoke a method on an object and that object executes the code inside that method (you call or invoke a method of the class)
* Communication is facilitated by the object's *interface*: the `public` methods that are available
* Otherwise, all other aspects of an object are *hidden* (information hiding/abstraction)
  * We don't want to have to worry about the representation NOR the implementation of an object, we just want to use it

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* Implementation or "information hiding"
* Procedural abstraction: how does the `sqrt()` work?  Who cares?
* How does `LocalDate` represent a date?
  * 3 integers: year/month/day
  * A single String ISO8601
  * A single integer (unix epoch time)
  * Who cares?  We just want to use it.
* Relieves you of the need to know about any details of an object

## Encapsulation

Usually is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

* Don't break encapsulation by locating data or methods that act on that data outside the class
* bank "stuff" belongs in the `Bank` class, person "stuff" belongs in the `Person` class, etc.
* Good abstraction and encapsulation work together to make code:
  * More isolated
  * You can more easily reuse code
  * MOre easily testable because *side effects* are isolated
* Make sure your modeling is correct:
  * Is a person a string?
  * Is an address a string?

```text





```
