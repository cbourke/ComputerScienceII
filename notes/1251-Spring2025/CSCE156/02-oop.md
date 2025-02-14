# Computer Science II - CSCE 156
## Spring 2025
### Object Oriented Programming

## Creating and Designing Classes: Reflection & Demo

* Java is a "class"-based object oriented programming language
* Everything in Java is a class or belongs to a class
* A `class` in Java allows you to model a "real world" object or idea or "entity"

## Objects

Formally an object is a general entity characterized by:

1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality that acts on the object's state/data: what the object does or what services it provides: its methods

* Identity is achieved in Java by generating the `equals()` and `hashCode()` methods
* Communication between objects is done via "message sending": just a fancy way of saying an object's functions are invoked or called
* Communication is facilitated by the object's *interface*: the `public` methods that are available
  * `private` methods may be useful, but are *not* part of the interface
* Objects are *constructed* rather than just assigned
  * In Java, constructor methods are named after the class; the have no return value, and can take any number of parameters
  * If you do not define a constructor, a no-argument (no parameter) default constructor is provided by Java
  * If you do define a constructor, the default goes away; you an always explicitly define it
  * You can have as many constructors as you want
  * Useful pattern: copy constructors
* Common patterns:
  * DRY Principle = Don't Repeat Yourself!
  * YAGNI = You Ain't Gonna Need It (don't over engineer your classes)
  * KISS = Keep It Simple, (i said) SIMPLE!

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* Implementation or "information hiding"
* Consider: `Math.sqrt()`: who cares how it works, it just does
  * Procedural abstraction: the details are hidden inside the function, we don't have to worry about them
* Consider: `LocalDateTime`: how does it *represent* a date/time
  * ISO8601 formatted string?
  * several integers: one for each field: day, month, year, hour, minute, second, fractional second
  * a single number: unix epoch number
  * Who cares?  THe representation is *internal* to the class
* Abstraction allows you to *reason about an object* without knowing how it works or how it is represented

## Encapsulation

Usually is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

Generally:
* All member variables should be `private` to protect them unless you have a Very Good Reason to do otherwise
* You can make as many constructors as you want in order to make your objects flexible
* You should avoid setter functions: you should prefer immutable objects
* Better patterns: copy constructors
* Another better pattern: Builder pattern (see the advanced activities in Lab 4)

## Inheritance

* Inheritance allows you to reuse a class by `extends`ing it and creating a *subclass*
  * A superclass/subclass relationship is an **is a** relationship: the subclass *is an* instance of the superclass
  * Alternative: parent/child relation
* Overall:
  * General functionality is located in the super class and shared among all of the subclasses
  * Specific functionality is located in the subclasses
* Classic example: Animal, Cat, Dog
* Specific functionality is *overridden* in the subclass
* Demonstration:
  * Extend our `Person` class so that we have `Author` and a `Director`
* Inheritance defines an *is-a* relationship: a `Dog` is-a `Animal`
* When a subclass `extends` a superclass, it *inherits* its state and functionality

### Tools:
  * UML Sketches: https://app.diagrams.net/
  * UML Generation: plantUML: https://github.com/cbourke/ComputerScienceII/blob/master/resources/uml.md

### Observations:

* If a class `A` `extends` a class `B`:
  * `A` is the subclass/child class/derived
  * `B` is the superclass/parent class/
* Inheritance defines an "is-a" relationship
  * A `Dog` *is-a* `Animal`
  * A `Cat` *is-a* `Animal`
  * An `Animal` is not necessarily a `Cat/Dog`
  * It is *never* the case that a `Cat` is-a `Dog`
* However, you really need to think about your inheritance design
  * Ex: there are some `Persons` that have directed films *and* written books
* Generally you can "prefer composition over inheritance"
  * Perfect use case: Director/Author
* Three types of relations:
  * Covariant relationship: you can treat a subclass as a super class (Dog **is an** Animal); ALWAYS safe
  * Contravariant relationship: you can *sometimes* treat a subclass as a superclass (an Animal is sometimes a Dog); sometimes safe
  * Invariant relationship: a cat is never a dog, a dog is never a cat, NEVER safe
* Sometimes it is okay to check before you perform a contravariance...
  * You can use `instanceof` keyword to determine if it is of the appropriate type
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
  * The **is-a** relationship *must* be invariant
  * Once a hierarchy has been established and code is not *dependent* on the hierarchy, it *cannot be changed* (not without breaking all other code)

### Demo

* Create some classes to support assets: `Stock` and `Annuity`

```text















```
