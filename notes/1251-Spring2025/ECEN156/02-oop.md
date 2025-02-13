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

## Inheritance

* Inheritance allows you to reuse a class by `extends`ing it and creating a *subclass*
  * A superclass/subclass relationship is an **is a** relationship: the subclass *is an* instance of the superclass
  * Alternative: parent/child relation
* Pattern:
  * Common functionality is shared in the super class while
  * Specific functionality is *overridden* in the subclass
  * Ex: Animal, Dog, Cat
* The *is-a* relationship **must be invariant**
  * A hierarchy of inheritance *must be well designed* up front, otherwise it collapses when you have an example that doesn't fit the hierarchy
  * Alternative: "prefer composition over inheritance"

### Tools:
  * UML Sketches: https://app.diagrams.net/
  * UML Generation: plantUML: https://github.com/cbourke/ComputerScienceII/blob/master/resources/uml.md

### Observations:

* If a class `A` `extends` a class `B`:
  * `A` is the subclass/child class/derived
  * `B` is the superclass/parent class/
* Inheritance defines an "is-a" relationship
  * A `Dog` *is-a* `Animal`
  * An `Author` *is-a* `Person`
* If the relation is not invariant or not well-defined, avoid inheritance
* Three types of relations in inheritance:
  * Covariance: you can always treat a subclass as a super class (always safe)
  * Constravariance: sometimes you can treat a superclass as a subclass (but often times not): not always safe
  * Invariance: you can never treat "sibling" classes as another sibling class
*  You can **sometimes** use the `instanceof` keyword to determine the type or subtype of a variable
  * However, you should almost *NEVER* use this in a program
  * It is completely **wrong** to use `instanceof` to determine the control flow of your program.
  * The only time you should use this is when creating copies or instances of an object: there is no such thing as a "polymorphic constructor"
* Motivations for inheritance:
  * It provides a way for *code reuse*
  * It provides a way to *organize* code
  * Reduces redundancy
  * If well-designed, the introduction of new classes or new behavior or functionality **should not break any other piece of code**

### More Inheritance - Pitfalls

An inheritance hierarchy needs to be **very well defined**.
  * The *is-a* relationship *must* be invariant
  * Once a hierarchy has been established and code is now *dependent* on it, it *cannot be changed* without substantially breaking other code

Pitfalls
* Rectangle Problem
  * Your object relations in an inheritance hierarchy ALWAYS need to follow the is-a relationship
  * Another Example: Author, Director, Person hierarchy no longer makes sense if you have a person who is BOTH an author and director
  * This is a violation of the Liskov Substitution Principle (SOLID)
 * Antipattern (a bad behavior or bad but common mistake in code): yo-yo antipattern
  * You don't want your inheritance hierarchies to be too deep
  * YOU should prefer shallow hierarchies
  * Or: "prefer composition over inheritance"
  * YAGNI = You Ain't Gonna Need It
* Diamond Problem Antipattern
  * Animal/Dog/Cat
  * Some languages allow you to inherit from multiple classes!
  * It is ambiguous as to which behavior you are inheriting!
  * For languages (C++) that allow this, you have to write code to explicitly state what you are inheriting: defeats the purpose of code reuse anyway
  * Java (and most sane) programming languages do not allow this

### More Inheritance Tricks

* Java allows you to create `abstract` classes: classes that you are not allowed to instantiate instances of but that you can define state and behavior in
* In Java, you can create an `abstract` classes:
  * You can still inherit from an `abstract` class
  * However, you cannot create an instance of an `abstract` class
  * `abstract` classes can define state (variables) or methods that are inherited by any sublcass
  * You can have `abstract` subclasses but at some point it needs to be an actual implementation
  * You provide the behavior in NON-abstract *subclasses*
* An `interface` is a *pure* abstract class: it only has method signatures, no state, no implementations
  * This has the advantage that you can `implements` multiple interfaces!

# Polymorphism

* Polymorph = multiple form(s)


```text





```
