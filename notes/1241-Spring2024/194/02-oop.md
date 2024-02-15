# Computer Science II - ECEN 156
## Spring 2024
### Object Oriented Programming

## Objects

### Creating and Designing Classes

* Java is a "class"-based object oriented programming language
* Everything in Java is a class or belongs to a class
* A `class` in Java:
  * Allows you to model a "real world" object or idea or "entity"
  * Classes allow for "String Encapsulation":
    1. The grouping of data
    2. The grouping of behavior (methods/functions/functionality) that acts on that data
    3. Protection of that data
* Generally speaking:
  * Book things belong in the `Book` class, Person things belong in the `Person` class, etc.
  * You should have one class per entity, model them correctly
  * Protect your classes: it is best to make all variables `private` unless you have a Very Good Reason (VGR) for doing otherwise
* Generally look for ways to use *composition*
  * Example: a `Book` owns an instance of a `Person` (the author)
  * Classes can be *composed* of other classes and variables

## Objects

Formally an object is a general entity characterized by:

1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality of the object or what it *does* or the services it provides

* Communication between objects is done via "message sending": you invoke a method on an object and that object executes the code inside that method
* Communication is facilitated by the object's *interface*: the `public` methods that are available
* Otherwise, all other aspects of an object are *hidden* (information hiding/abstraction)

### Composition & Design

* Object design:
  * "Semantics dictate design"
  * Don't over-design: YAGNI = You Aint Gonna Need It
  * You break real-world objects or ideas into smaller and smaller parts until...
    * It is directly representable: integer, string, etc.
    * OR: it already exists
* Generally your objects are *composed* of other objects or data
  * When an object "owns" an instance of another class, it is called *composition*
  * When an object "owns" a collection of other classes, it is called *aggregation*
* Design Principles:
  * Generally: avoid the "god-antipattern" (antipattern = bad habit in code), the "god antipattern": your class is everything, knows everything, does everything
  * Single Responsibility Principle: a class should only be responsible for one thing
* With objects, you create them using *constructors*
  * In Java, by default a no-argument constructor is provided
  * If you define your OWN constructor, it goes away
  * You can always restore it by explicitly defining it
  * You can define as many constructors as you wish to make your objects more usable
  * Nice pattern: copy constructor: takes an instance of the same class and copies over the values in part or in whole

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

## Inheritance

* Inheritance allows you to reuse a class by `extends`ing it and creating a *subclass*
  * A superclass/subclass relationship is an **is a** relationship: the subclass *is an* instance of the superclass
  * Alternative: parent/child relation
* PAttern:
  * Common functionality is shared in the super class while
  * Specific functionality is *overridden* in the subclass
  * Ex: Animal, Cat, Dog
* Demonstration:
  * Redesign our `Person` class to also have an `Author` and `Director` subclasses
  * Collect common functionality in the super class
  * Place specific functionality in the subclasses

Tools:
  * UML Sketches: https://app.diagrams.net/
  * UML Generation: plantUML: https://github.com/cbourke/ComputerScienceII/blob/master/resources/uml.md

Observations:

* If a class `A` `extends` a class `B`:
  * `A` is the subclass/child class/derived
  * `B` is the superclass/parent class/
* Inheritance defines an "is-a" relationship
  * A `Dog` *is-a* `Animal`
  * An `Author` *is-a* `Person`


```text











```
