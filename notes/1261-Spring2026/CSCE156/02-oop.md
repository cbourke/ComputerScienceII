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
  * Covariant relationship: you can treat a subclass as a super class (Dog **is an** Animal); ALWAYS safe
  * Contravariant relationship: you can *sometimes* treat a subclass as a superclass (an Animal is sometimes a Dog); sometimes safe
  * Invariant relationship: a cat is never a dog, a dog is never a cat, NEVER safe

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
  * Add a new Asset class for Crypto (BTC)

## Observations

* Motivations for inheritance:
  * It provides a way to utilize *code resuse*
  * It provides a way to reduce redundancy
  * It provides a way to reason about and organize your classes into a *hierarchy*
  * It is well-designed if the introduction of a new class does not break any of the other code
* What happens if you have well-defined subclasses, but not-so-well-defined superclasses
  * Example: `Person`, `Director`, `Actor`
* Rectangle Problem: Shape, Rectangle, Square
  * Your object relations in an inheritance hierarchy ALWAYS need to follow the is-a relationship
  * Another example: Author, Director, Person hierarchy: it no longer made sense when we had a person that was *both*
  * If you have mutable methods (setters) that can change what an object "is" that violate the hierarchy, it is probably not a well-designed hierarchy
  * This is a violation of the Liskov Substitution Principle (SOLID)
* Your hierarchy has to be **very well defined**
  * If not: you end up fighting
  * or you need to redesign it!
* Others:
  * In Java, you can create an `abstract` class:
    * It is a class that you can inherit from
    * YOu can define methods and state in your class, but also
    * You can define `abstract` methods: methods that have no "default behavior"
    * You provide the behavior in NON-abstract subclasses
    * You cannot instantiate (create) an instance of an abstract class, even if it has a constructor
  * An `interface` takes this to another level
    * An interface is a pure abstraction: it only defines abstract methods, no state, no behavior
  * You can `implements` multiple interfaces: it provides more flexibility
  * It means that you can define a collection of abstract methods and then a class can `implements` that interface

# Polymorphism

* Polymorph = multiple form(s)
* Code: one piece of code (variable, method, class) can be written *generically* so that it can be applied to many types
* Other languages:
  * Python: `list.sort()`
  * C: `qsort()`
  * Java: `Collections.sort()`
* It allows you to write generic code, one function/method that can be applied to *any* type

### Subtype Polymorphism

* This is the "classic" or "default" type of polymorphism
* You can treat any subtype as a supertype so that you can apply generic code to it
* Ex: `Robin` can be treated as a `Bird`
* Ex: `ArrayList` can be treated as a `List`

### Method Overloading

* Ex: from C, how many "absolute value" functions are there?
  * `abs()` (integers), `fabs()` (floating point numbers), `labs()`, `llabs()`
  * C does not have function overloading: if you write a function ONLY that function can have that name
  * Python doesn't have it either
  * Java does: you can write multiple `abs()` methods that can be applied to multiple types
* You can have more than one function with the same name but with different types of arguments (inputs/parameters)
* The compiler is "smart enough" to figure out which one you want to call based on the value you give it: `int` it calls the version that takes an integer, `double`: it calls the version that takes a `double`
* This mechanism is known as "static dispatch" (the compiler *dispatches* the function call at *compile time* that's what static means)

### Operator Overloading

* Consider in Java:
  * `String + String`: string concatenation
  * `int + int`: addition
  * `String + int`: convert (automatically) the `int` to a `String` and do concatenation!
* C:
  * `int + int`: addition
  * `char * + char *`: nonsense: it adds to memory locations together!
* Python:
  * `string + string`: concatenation
  * `int + int`: addition
  * `int + string`: not allowed, it is a runtime error
  * To get it to work: `str(int) + string`
* C and python do not support *operator overloading*
* Opeator overloading: means that an operator (`+`) can have more than one meaning in different contexts
* Java: does support *limited* operator overloading
* C++ allows ANY operator overloading!
* Generally a bad idea:
  * `Time + Time`
  * `List + List`: list concatenation/appending
  * Alternative: $A \cup B$
  * Math: coordinate-wise addition (vector addition)
  * Ideally: it should be completely understood, unambiguous
* Operator overloading is a thing, but **should be extremely limited**

### Parameterized Polymorphism

* In Java you can create a parameterized variable, class or method
* That one variable can be any type, method can act on any type, class can be associated with any type!
* Allows you to write one piece of code that can apply to many types
  * `Collections.sort()`
* the type of a variable can be *variable* itself!
* YOu can parameterize using `<T>`, then `T` is replaced with an actual type for each method call
* You can provide *bounds* by using the `extends` keyword
  * Without a bound, the type `T` is only ever an `Object` which is limited to `toString()`, etc.
  * *With* a bound: you have a minimum of information
* PECS: Producer Extends, Consumer Super
  * A collection (list, set) is a *producer* of elements; if you wish to pull them out and do something with them, then you need a "named" parameter: `<T extends Item>` (`T` is the name
  of type type being used)
  * A collection is a *consumer* of elements if you wish to put stuff in: `<? super Item>`: if you don't care about what is in the collection already; you just want to put stuff in

## SOLID Principles

## S = Single Responsibility Principle

* Good encapsulation: a class should represent one thing and represent it well
* Ex: `Book` is responsible for book things, `Person` is responsible for person things
* Violation: God-Class: the class is responsible for everything, knows everything, does everything
  * Are persons and companies the same thing?
  * Be careful: you still need to have good modeling and composition: how do you represent a person in a `Company` class? String?  `UUID` WRONG. It should be a `Person`
  * How are emails represented? A single string?  Wrong
  * Loading Data, Converting Data, Saving Data
* YAGNI Principle: You Aint Gonna Need It: don't over engineering things
  * Bourke said that any two pieces of data needs a class: `lastName` and `firstName` are two pieces of data, thus I should have a `Name`
* Avoid "leaky abstractions":
  * Code should not require users to use it in a *specific* way
  * If you were forced to call `initialize_math()` before you called any `Math` function
  * Person/email demo

## O = Open/Closed Principle

* Every unit (module, class, method) should be *open for extension* and *closed for modification*
* Classical inheritance:
  * Superclasses provide a *general* behavior that should *NOT* be changed otherwise:
    * It must have been a bad design to begin with OR
    * changing it breaks all other code
* Violation: `instanceof` to determine business logic (the value of an account, the cost of items)

```java
if(object instanceof Stock) {
  //do stock thiings here
  //ex: determine its value
} else if(object instanceof Crypto) {
  //do annuity things here
}

//also wrong:
if(object.getType().equals("Stock")) {
  //do stock thiings here
} else if(object.getType().equals("Crypto")) {
  //do annuity things here
}
```

* The only time you *should* (in fact have to) use the `instanceof` keyword or type checks: when you call a constructor
  * Ex: you may need to make a copy of `Service` without knowing what the object type is (call a copy constructor)

* In Java: you can enforce this principle using the `final` keyword:
  * `final` variables mean you are making them constant
  * A `final` class cannot be extended: ex: `Integer`
  * A method can be made `final` and subclasses cannot override it!

## L = Liskov Substitution Principle

* If S is a subtype of T then objects of type T may be replaced with objects of type S without altering any of the desired properties of T.
  * Violation: Rectangle/Square example
  * `Person`: `Author` vs `Director`: invariance prevents a proper modeling of (say) STephen King who is both an author and a director
  * Prefer composition over inheritance

## I = Interface Segregation Principle

* No "client" code (code that uses other code) should depend on methods it does not care about
* Example `ClickEventHandler` is an interface that defines two methods:
  * `onClick()`
  * `onDoubleClick()`: you end up making this method useless: it may throw an exception, it may simply call `onClick()`, or it may be a NOOP = No Operation
* A bad design would *force* you to implement both of these things even if you didn't want to...
* INstead: all interfaces should be as small as possible (most are 1 or 2 or 3 at most methods)

## D = Dependency Inversion Principle

* High-level modules (classes) should not depend on low-level modules
* Ex:
  * You write code that connects to a (Free) MySQL database with very-specific MySQL methods, etc.
  * Now you've grown as a company and need to migrate to MSSQL
  * Throw away all DB code and connect to the MSSQL server: rewrite EVERYTHING
  * Vendor lock: the cost of switching is greater than the cost of staying with the current vendor
  * Instead: you should have written generic code for an *interface* that each one of the databases *implements*

* Without Inversion:

```java
if(Library A) {
  return new Location(GPSLocator.getLatitude(), GPSLocator.getLongitude());
} else if(Library B) {
  AndroidLocation l =  AndroidNative.getLocation();
  return new Location(l.getLatitude(), l.getLongitude());  
} else if(Library C) {
   return new Location(Utils.radiansToDegrees(CellTowerGPS.getLatitudeRad()),
    Utils.radiansToDegrees(CellTowerGPS.getLongitudeRad()));
}
```

* "Inverted"

```java

public interface Locator {

  public Location getLocation();

}


public AndroidLocator implements Locator {


  public Location getLocation() {
    AndroidLocation l =  AndroidNative.getLocation();
    return new Location(l.getLatitude(), l.getLongitude());  

  }

}


//client code: doesn't care about the type of Locator
Locator l = new AndroidLocator();
Location = l.getLocation();

```

* "Inverting" a dependency means creating an interface between the "highlevel" object/class/thing and the low-level library that `implements` it
* In general, you should prefer *loose coupling* so that software components can easily be interchanged

* https://williamdurand.fr/2013/07/30/from-stupid-to-solid-code/#premature-optimization
```text












```
