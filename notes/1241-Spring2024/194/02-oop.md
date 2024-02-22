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
* Three types of relations in inheritance:
  * Covariance: you can always treat a subclass as a super class (always safe)
  * Contravariance: sometimes you can treat a superclass as a sublcass (sometimes safe, often not)
  * Invariance: you can never treat a "sibling" class as another sibling class
* You can **sometimes** use the `instanceof` keyword to determine the type or subtype of a variable
  * However, you should almost *NEVER* use this in a program
  * It is completely WRONG to use this to determine the control flow of your program: you would end breaking encapsulation
  * The only time you should use this is when creating copies or instances of an object: there is no such thing as a "polymorphic constructor"
* Motivations for inheritance:
  * It provides a way for *code reuse*
  * It provides a way to *organize* code
  * Reduces redundancy
  * If well-designed, the introduction of a new class or behavior does NOT break any code and does not require any changes

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
* Diamond Problem Antipattern
  * Animal, Dog, Cat
  * Some languages would allow you to inherit from multiple classes
  * It is ambiguous as to which behavior you are inheriting!
  * For languages (C++) that allow this, you have to write code to explicitly state what you are inheriting: defeats the purpose of code reuse anyway
  * Java (and most sane) programming languages do not allow this

### More Inheritance Tricks

* What happens if you have well-defined subclasses but not-so-well-defined superclasses
* In Java, you can create an `abstract` class:
  * You can still inherit from an `abstract` class
  * You can define methods and state, but also...
  * You can deinfe `abstract` methods: methods that do not have any body and do not specify how they work
  * You provide the behavior in NON-abstract *subclasses*
  * An `abstract` class has constructors, but other code is NOT able to call them!
* An `interface` takes this to another level
  * An `interface` is a *pure* abstract class: there is no state, ONLY abstract methods
  * This has the advantage that you can `implements` multiple interfaces!

# Polymorphism

* Polymorph = multiple form(s)
* In code: a variable, method, or class can be generically written so that it can apply to more than one type
  * Example: `qsort()` (C language)
  * Java: `Collections.sort()`
* This allows code reuse at an even higher level!

## Subtype Polymorphism

* Is the "classic" polymorphism: you can treat a subtype as a supertype
* You can treat a `Robin` as a `Bird`
* Covariance: it always safe (as long as it is designed correctly)!
* If you don't need the specialized behavior of a subclass, you can write more generic code to apply to ALL subclasses

### Method Overloading

* Example: in C how many absolute value functions did you have? `abs(), fabs(), labs(), llabs()`
* In a language without method overloading, once you create a function with a given name, `foo`, that is the ONLY function that can have that name
* You end up "polluting the namespace": libraries will typically prepend ALL functions with some acronym: `gmp_function_name`
* In an OOP language: you have method overloading: you can have more than one method with the same name but with different parameter types!
* The compiler is able to determine which method you intended to call based on the input(s) you give it: this is known as "static dispatch"

### Operator Overloading

* Consider in Java:
  * `String + String`: here `+` means "concatenation"
  * `int + int`: here `+` means addition
  * `String + int`: here `+` means: convert the `int` to a string and then concatenate
* Consider in C:
  * `char * + char *`: here `+` means add the memory addresses
  * Plus always means addition, just not a very useful addition in many cases
  * No operator overloading in C
* In java: only this particular instance is an operator overloaded
* C++: you can customize and change ANY operator to ANY operation
* Ex: You could change `+` to mean subtraction
* Be careful: this is not very useful in general
* `List + List`: what does this mean?
  * Append one list to the end of the other
  * Vector addition (math)
  * Union
  * Confusion!
* `Time + Time`: !?!?!
* Most languages do not allow customization of operator overloading and even if they did: you'd have to write a function

### Parameterized Polymorphism

* In Java you can create a parameterized class, variable, or method
* PECS: Producer Extends, Consumer Super
  * A collection (list, set) is a *producer* of elements; if you wish to pull them out and do something with them, then you need a "named" parameter: `<T extends Item>` (`T` is the name
  of type type being used)
  * A collection is a *consumer* of elements if you wish to put stuff in: `<? super Item>`: if you don't care about what is in the collection already; you just want to put stuff in

## SOLID Principles

## STUPID Principles

```text











```
