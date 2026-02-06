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

Observations:
* In Java, identity is determined by implementing the `equals()` and `hashCode()` methods: for now, just auto-generate them
* Communication between objects is done via "message sending": just a fancy way of saying an object's functions are invoked or called
* calling a function is telling an object what to do, not *how* to do it (that's the object's responsibility)
* Communication is facilitated by the object's *interface*: the `public` methods that are available
  * You can still have `private` methods, but they are not part of the interface
  * `private` methods may still be useful!
* Objects are *constructed* rather than just assigned
  * In Java, constructor methods are named after the class; the have no return value, and can take any number of parameters
  * If you do not define a constructor, a no-argument (no parameter) default constructor is provided by Java
  * If you do define a constructor, the default goes away; you an always explicitly define it
* Common patterns:
  * Make it so that specific constructors call more general constructors: DRY = Don't Repeat Yourself
  * YAGNI = You Ain't Gonna Need It: don't over-engineer your objects (Ex: `Person` class is good, )
  * You break real-world objects or ideas into smaller and smaller parts until...
    1. it is representable by a built-in type: `String, int, double` or
    2. An object already exists that does that
* Generally your objects are *composed* of other objects or data
  * Ex: `Book` class is composed of `String`s, `int`s, `double`s and `Person` (it "owns" an instance of the `Person` class)
  * This is known as *composition*
* Avoid the "god anti-pattern": a class should have a *SINGLE* purpose or SINGLE representation: Single Responsibility Principle

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* Implementation or "information hiding"
* Consider: `Math.sqrt()`: who cares how it works, it just does
  * Procedural abstraction: the details are hidden inside the function, we don't have to worry about them
  * OOP: the *representation* is abstracted
* Consider: `LocalDate`: how does it work? How does it represent a date
  * ISO 8601: string `2026-02-04`
  * 3 intgers: day, month, year
  * Unix Epoch: a single integer (number of seconds since Jan 1 1970)
  * Who cares!
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

### Demo

* Consider a radioactive isotope: design a class to support this
* Good abstraction and good encapsulation


## Inheritance

* Inheritance allows you to reuse a class by `extends`ing it and creating a *subclass*
  * A superclass/subclass relationship is an **is a** relationship: the subclass *is an* instance of the superclass
  * Alternative: parent/child relation: children inherit state and behavior from their parent class
* Overall:
  * General functionality is located in the super class and shared among all of the subclasses
  * Specific functionality is located in the subclasses
* Classic example: Animal, Cat, Dog
* Inheritance defines an *is-a* relationship: a `Dog` is-a `Animal`
* When a subclass `extends` a superclass, it *inherits* its state and functionality
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
```java

		Animal a = new Animal("Turducken", 100);
		Dog d = new Dog("Fido", 9);
		Cat c = new Cat("Fluffly", 3);

		System.out.println(a);
		System.out.println(d);
		System.out.println(c);

		a.speak();
		d.speak();
		c.speak();

		//Demonstration:
		//Covariance is always okay:
		//promoting a subclass to its superclass
		//ALWAYS safe
		Animal a2 = d;
		a2.speak();

		//Contravariance is SOMETIMES safe:
		Dog d2 = (Dog) a2;
		d2.speak();

		//Contravariance is sometimes NOT safe
		//this is an INVARIANCE
		Cat c2 = (Cat) a2;
		c2.speak();
```

### Demonstration:

* Write class(es) to support an asset management system
* Stocks: share price, number of shares
* Saving account: balance, interest rate
* ??
* Design sanity check:
  * If your system is well-designed, then the addition of a new type should *Only* result in one class
  * If your system is poorly designed: the addition of a new type would lead to MANY code changes

```text




```
