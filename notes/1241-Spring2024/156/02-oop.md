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

* Is a person an author? Not always
* Is an author a person? Yes
* Is a director an author? Sometimes
* Is an author a director? Sometimes
* Is a director a person? Yes

```text








```
