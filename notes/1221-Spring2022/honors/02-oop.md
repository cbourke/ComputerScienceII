# Computer Science II - CSCE 156/156H
## Spring 2022
### Object Oriented Programming

## Intro: Creating and Designing Classes Demo

* See `Isotope.java`

## Objects

* Formally, an object is a general entity characterized by:
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

* Information or implementation hiding
* YOu don't have to worry about how an object is represented in order to use it!  
* Ex: `LocalDate`: how does it represent a date?
  * 3 integers
  * String: ISO 8601 `2022-02-08T14:17:55`
  * A single number (unix epoch/unix time)
* How is a `String` represented in Java?  How does an `ArrayList` store things? How does it grow/shrink
* Abstraction means not having to think about these "low level" details

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

  Scenario: a retail store sells products and subscription
  services.  Products are items that have a price and a
  customer may purchase any number of them (ex: USB memory stick).
  Subscriptions have an annual rate and are purchased based
  on the start date and end date.

* KISS = Keep It Simple Stupid
* YAGNI = You Aint Gonna Need It
* DRY = Don't Repeat Yourself

* Inheritance allows you to create "super classes" with common, general behavior and/or state
* Then you can *derive* or `extends` to "subclasses" with more specific behavior or specialized behavior

```text








```
