# Computer Science II - CSCE 156
## Spring 2024
### Object Oriented Programming

## Creating and Designing Classes: Reflection & Demo

* Java is a "class"-based object oriented programming language
* Everything in Java is a class or belongs to a class
* A `class` in Java allows you to model a "real world" object or idea or "entity"

## Objects

Formally an object is a general entity characterized by:

1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality that acts on the object's state/data: what the object does or what services it provides

Observations:

* In Java, identity is determined by implementing the `equals()` and `hashCode()` methods
* Communication between objects is done via "message sending": just a fancy way of saying an object's functions are invoked or called
* Communication is facilitated by the object's *interface*: the `public` methods that are available
* Objects are *constructed* rather than just assigned
  * In Java, constructor methods are named after the class; the have no return value, and can take any number of parameters
  * If you do not define a constructor, a no-argument (no parameter) default constructor is provided by Java
  * If you do define a constructor, the default goes away; you an always explicitly define it
* Common patterns:
  * Make it so that specific constructors call more general constructors (DRY = DOn't Repeat Yourself)
  * Useful pattern: copy constructors
* Object design:
  * "Semantics dictate design": it is most often "obvious"
  * Don't over-design: YAGNI = You Aint Gonna Need It
  * You break real-world objects or ideas into smaller and smaller parts until...
    * It is directly representable: `String`, or an `int` or `double`
    * OR: an object already exists that you can use...
* Generally your objects are *composed* of other objects or data
  * Ex: a `Book` is composed of strings, integer, etc. AND another object: `Person` (author)
  * This is known as *composition*
* Avoid the "god anti-pattern": a class should have a *SINGLE* purpose or SINGLE representation: Single Responsibility Principle

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* Implementation or "information hiding"
* Consider the `Math.sqrt()`: how does it work?  A: who cares?
* Consider the `LocalDate` object: how does it represent a date?  An ISO8601 string? 3 integers?, 1 single number (number of milliseconds since Jan 1 1970); A: who cares
* How does Java represent a `String`?
* Abstraction allows you to *reason about an object* without knowing how it works or how it is represented

## Encapsulation

Usually is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

Generally:
  * All member variables should be `private` to protect them
  * You can make as many constructors as you want in order to make your objects flexible
  * You should also avoid setter functions: you should prefer immutable objects
  * You can make your classes more flexible with various other patterns: copy constructor
  * See also: the builder pattern in Lab 4 advanced activities

Other observations
* Don't break encapsulation by locating data or methods that act on that data outside the class
  * Book stuff belongs in the book class
  * Person stuff belongs in the person class
  * Methods/behavior that acts on a class's data **belongs in the class**
* Composition: when a class is *composed* of other classes
* Another relation (sort of a *aggregation* ): when an object owns a collection of other objects
  * It makes it flexible to have `add` methods that add to that collection
  * It makes it more flexible because you can add additional data later on, HOWEVER....
  * It makes your objects somewhat mutable
  * Risk: infinite ping pong: if you have *bidirectional* composition (class A owns class B and vice versa), then you risk infinite recursion back and forth
* Good abstraction and encapsulation work together to make code:
  * Isolated
  * More flexible
  * More easily testable
  * You limit "side effects" of other code
  * Makes code reusable

## Inheritance

* Inheritance allows you to reuse a class by `extends`ing it and creating a *subclass*
* In Java we use the `extends` keyword to make one class a *subclass* of another
  * Superclass/subclass
  * Parent class/child class
* Generally:
  * You place common or *general* behavior (methods) and state (variables) in the superclass
  * Variables/methods in the superclass are *inherited* by the subclass
  * YOu can also add *more specific* behavior/state in the subclass

Tools:
  * UML Sketches: https://app.diagrams.net/
  * UML Generation: plantUML: https://github.com/cbourke/ComputerScienceII/blob/master/resources/uml.md

Observations:
* If a class $A$ `extends` a class $B$:
  * $A$ is the subclass, $B$ is the superclass
  * $A$ is the child class, $B$ is the parent class
* Inheritance forms an *is-a* relationship
  * Animal, Dog, Cat: a Cat is a Animal, Animal a dog (not necessarily); A dog is an animal, but not necessarily vice versa
  * Is a dog a cat?  Is a cat a dog?
* Three types of relations:
  * Covariant relationship: you can treat a subclass as a super class (Dog **is an** Animal); ALWAYS safe
  * Contravariant relationship: you can *sometimes* treat a subclass as a superclass (an Animal is sometimes a Dog); sometimes safe
  * Invariant relationship: a cat is never a dog, a dog is never a cat, NEVER safe
* Sometimes it is okay to check before you perform a contravariance...
  * You use the `instanceof` keyword to do this (inside of an `if` statement.
  * HOWEVER: you generally don't want to do this unless you *have* to
  * If you find yourself doing this *often* then it is likely a *bad design*
  * Generally the only time you *have* to use `instanceof` and do a contravariance is when  you are making copies (calling a copy constructor)
* Motivations for inheritance:
  * It provides a way to utilize *code resuse*
  * It provides a way to reduce redundancy
  * It provides a way to reason about and organize your classes into a *hierarchy*
  * It is well-designed if the introduction of a new class does not break any of the other code

### More Inheritance - Pitfalls

An inheritance hierarchy needs to be **very well defined**.
  * The *is-a* relationship *must* be invariant
  * Once a hierarchy has been established and code is now *dependent* on it, it *cannot be changed* without substantially breaking other code

Pitfalls

* Rectangle Problem: Shape, Rectangle, Square
  * Your object relations in an inheritance hierarchy ALWAYS need to follow the is-a relationship
  * Another Example: Author, Director, Person hierarchy no longer makes sense if you have a person who is BOTH an author and director
  * This is a violation of the Liskov Substitution Principle (SOLID)
* Antipattern (a bad behavior or bad but common mistake in code): yo-yo antipattern
  * Suppose you have a *deep* inheritance hierarchy: 10 or 20 classes deep
  * It is best to keep a hierarchy *shallow*: limit the number of classes/subclasses
  * Or: "prefer composition over inheritance"
* Diamond Problem Antipattern
  * Animal, Dog, Cat
  * Suppose you could inherit from *multiple classes*
  * For this reason, MOST OOP languages do not allow multiple inheritance
  * C++ is the only one (that I know of) that does allow this
  * Java do not allow multiple inheritance
  * HOwever: most languages allow you to create more "general" ideas of inheritance: `abstract` classes and `interface`s (pure abstract classes)
  * Java is a *single inheritance hierarch*: all things `extends` from `Object`

### More Inheritance Tricks

* What happens if you have well-defined subclasses, but not-so-well-defined superclasses
* In Java, you can create an `abstract` class:
  * It is a class that you can inherit from
  * YOu can define methods and state in your class, but also
  * You can define `abstract` methods: methods that have no "default behavior"
  * You provide the behavior in NON-abstract subclasses
  * You cannot instantiate (create) an abstract class even if it has a constructor!
* An `interface` takes this to another level
  * An interface is a *pure* abstract class: it has no state and no non-abstract methods (generally)
  * It means that you can define a collection of abstract methods and then a class can `implements` that interface

```text


















```
