# Computer Science II - ECEN 194
## Spring 2023
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
* Demonstration: design a class that models a radioactive isotope

## Objects

* Formally an object is a general entity characterized by:
1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality of the object or what it *does* or the services it provides

* Communication between objects is done via "message sending": you invoke a method on an object and that object executes the code inside that method
* Communication is facilitated by the object's *interface*: the `public` methods that are available
* Objects are *constructed* rather than just assigned
  * In Java, by default a no-argument constructor is provided
  * If you define your OWN constructor, it goes away
  * You can always restore it by explicitly defining it
  * You can define as many constructors as you wish to make your objects more usable
* Common patterns:
  * Copy constructors that take another instance of the object and make a deep copy
  * Partial copy constructors: copy constructors that take an object and other values to make a (partial) copy of the object
* Object design:
  * "Semantics dictate design"
  * Don't over-design: YAGNI = You Aint Gonna Need It
  * You break real-world objects or ideas into smaller and smaller parts until...
    * It is directly representable OR
    * An object already exists for it
* Generally your objects are *composed* of other objects or data
  * When an object "owns" an instance of another object it is called "composition"
* Avoid the "god-antipattern": a class should only know about its own stuff, it should not *know everything, do everything, be everything*
* Follow the **Single Responsibility Principle**

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* Implementation or "information hiding"
* You shouldn't have to worry about how an object is represented, just how to use it (its interface: its publicly facing methods)
* Ex: how does `LocalDate` represent an actual date?
  * 3 integers: year/month/day
  * String: ISO 8601 standard
  * A single number: number of milliseconds since an epoch (unix epoch: Jan 1, 1970)
  * Answer: who cares?
* How does Java represent a `String`?

## Encapsulation

Usually is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

* Don't break encapsulation by locating data or methods that act on that data outside the class
* Bank stuff belongs in the `Bank` class, not the `main` or `Demo` class
* Student "stuff" belongs in the `Student` class
* Good abstraction and encapsulation work together to make code:
  * Isolated
  * More easily testable
  * Control/limit "side effects"
  * Reusable

## Inheritance

* Inheritance allows you to reuse a class by `extends`ing it and creating a *subclass*
  * Common functionality and state/behavior is located in the `super` class
  * Specific behavior/specialization is provided or *overridden* in the subclass
  * Example: Animal, Dog, Cat

Demonstration: draft a collection of financial asset classes
  * Savings Account: an account that pays *interest* on a *balance* monthly
  * Annuity: a product that pays a fixed monthly amount over a certain number of years

Tools:
  * UML Sketches: https://app.diagrams.net/
  * UML Generation: plantUML: https://github.com/cbourke/ComputerScienceII/blob/master/resources/uml.md

Observations:

* If a class `A` `extends` a class `B`:
  * `B` is the subclass/child class/derived
  * `A` is the superclass/parent class/
* Inheritance defines an "is-a" relationship
  * A `Dog` *is-a* `Animal`
  * An `Annuity` *is-a* `Account`
* Three types of relationships in inheritance:
  * Covariant - always safe (treat a subclass as a superclass)
  * Contravariant - sometimes safe but not likely (treating a superclass as a subclass)
  * Invariant - Never safe (treat a sibling class as a different class)
* You can, but should not use the `instanceof` keyword to check type safety

```text












```
