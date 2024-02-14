# Computer Science II - CSCE 156H
## Spring 2024
### Object Oriented Programming

## Objects

### Creating and Designing Classes: Demo

* Java is a "class"-based object oriented programming language
* Everything in Java is a class or belongs to a class
* A `class` in Java:
  * allows you to model a "real world" object or idea or "entity"
  * allows you to *group* multiple pieces of data together into one logical unit
  * also allows you to *encapsulate* methods within the class that *act on* or *operate on* that class's data (ie functionality)
  * IE: it allows you to group functionality together with the data that it operates on
  * Java classes also allow you to *protect* data from outside of the class
* First demonstration: design a class that models a radioactive isotope

Formally an object is a general entity characterized by:

1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality of the object or what it *does* or the services it provides

## Object Design

* "Semantics dictate design"
* Careful: YAGNI = You Ain't Gonna Need It (don't overdesign)
* You break real-world objects or ideas into smaller and smaller parts until...
  * It is directly representable OR
  * An object already exists for it
* Generally objects are *composed* of other objects; this is known as "composition"
  * One object "owns" an instance of another object or a
  * Collection of objects (sort of like "aggregation")
* Patterns: are good practices to follow when creating objects
* Anti-patterns: bad habits to get into
* Example: "god anti-pattern": if you design a class that knows everything (has all the variables) does everything (has all the methods) and is everything (there is only one class), then it is the "god class"

## Constructors

* Objects are *constructed* not assigned
* In Java:
  * A default no-argument constructor is provided if you don't create one
  * When you define your own constructor, the default goes away
  * YOu can restore it by explicitly defining it...
* Another useful pattern: copy constructor
* Even better: the builder pattern (see the advanced activities in Lab 4)
* Bidirectional compositioin: A owns an instance of B and B owns an instance of A
  * Be careful: you need a way to solve the "chicken and egg problem"
  * You need a way to avoid infinite ping pong (infinite recursion back and forth)

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* Abstraction is implementation or "information hiding"
* Review: procedural abstraction: How does `Math.sqrt()` work?  Who cares, it just works
* The details of an implementation or representation of an object are abstracted away inside the class
  * How does Java represent a `String`?
  * How does Java represent a `LocalDate`?
    * 3 integers: year/month/day
    * ISO 8601 string?
    * A single number: number of seconds since Jan 1 1970

## Encapsulation

Strong encapsulation is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

* Don't break encapsulation by locating data or methods that act on that data outside the class
* Bank stuff belongs in the `Bank` class, not the `main` or `Demo` class, not the `Book` class
* Student "stuff" belongs in the `Student` class
* Book stuff belongs in the `Book` class, etc.
* Good abstraction and encapsulation work together to make code:
  * Isolated
  * Side effects are controlled: no globals, you can validate data, etc.
  * Reusable


## Inheritance


```text






```
