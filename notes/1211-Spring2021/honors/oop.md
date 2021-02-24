# Object Oriented Programming

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

* implementation hiding
* you need not worry about the details of how an object or class is implemented or how it works (behavior)
* You simply use it using its publicly available interface
* How is time represented?  How is a duration represented?  How is a `String` represented

## Encapsulation

Usually is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

## Inheritance

* Inheritance is a mechanism by which objects can be *extended* to:
  * Inherit state and behavior from its *superclass* and/or
  * *Override* or augment state and behavior to provide more *specific* functionality
* Provides a way to *reuse* code
* Provides a way to *organize* code

In Java:
* We use the `extends` keyword to subclass classes
* If `A extends B`, then `A` is the subclass (child class) of `B` which is the superclass (parent class)
* Subclassing defines an `is-a` relationship


```text









```