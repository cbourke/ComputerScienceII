# Computer Science II - CSCE 156H
## Spring 2026
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
  * `private` methods can be useful but are NOT part of the interface
* Objects are *constructed* rather than just assigned
  * Usually "built-in" types (`int`) are assigned
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
* Consider: `Math.sqrt()`: Who cares how it works, it just does; ie we're only interested in using it
  * Procedural abstraction: the details are hidden inside the function, we don't have to worry about them
* Consider: `LocalDateTime`: how does it represent dates?
  * 3 integers
  * ISO 8601 standard: `2026-02-04T10:30:49.000CST`
  * Unix Epoch: the number of seconds since Jan 1 1970 ( a single integer)
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

## Demonstration

## Inheritance

* Inheritance allows you to reuse a class by `extends`ing it and creating a *subclass*
  * A superclass/subclass relationship is an **is a** relationship: the subclass *is an* instance of the superclass
  * Alternative: parent/child relation
* Overall:
  * General functionality is located in the super class and shared among all of the subclasses
  * Specific functionality is located in the subclasses
* Classic example: Animal, Cat, Dog
* Inheritance defines an *is-a* relationship: a `Dog` is-a `Animal`
* UML Generation: plantUML: https://github.com/cbourke/ComputerScienceII/blob/master/resources/uml.md

### Observations:

* If a class `A` `extends` a class `B`:
  * `A` is the subclass/child class/derived
  * `B` is the superclass/parent class/
* Inheritance defines an "is-a" relationship
  * A `Dog` *is-a* `Animal`
  * A `Cat` *is-a* `Animal`
  * Is an `Animal` a `Cat`? (not necessarily, sometimes)
  * Is a `Dog` a `Cat`? Definitely not
* Covariant, Contravariant, Invariant comparisons

```java

		Animal a = new Animal("John", 35);
		Dog d = new Dog("Rover", 6);
		Cat c = new Cat("Mittens", 3);

		System.out.println(a);
		System.out.println(d);
		System.out.println(c);

		a.speak();
		d.speak();
		c.speak();

		//You can do what is called "subtype polymorphism":
		//You can always treat a subtype as a supertype: covariance
		Animal a2 = d;
		//You can *sometimes* treat a Animal like a Dog:
		// This is a contravariance and requires an *explicit type cast*

		Dog d2 = (Dog) a2;
		d2.speak();

		//invariants are NEVER safe:
		Cat c2 = (Cat) a2;
```

## Demonstration:

* Write code to support a variety of assets:
  * Stock account (stock price and a number of shares)
  * Savings Account (balance, interest rate)
  * ??

```text





```
