# Computer Science II - CSCE 156/156H
## Spring 2020
### Introduction to OOP (in Java)

# Objects/Classes

* Java is an *object-oriented* programming language (OOP)
* Encapsulation:
  1. Grouping of data
  2. Protection of data
  3. Grouping of functionality (methods) that act on that data

* Demonstration: let's design and implement an `Airport` class

## Observations

* Java is a *class-based* object oriented programming language
* Everything in Java:
  * is a class (which you can create or *instantiate* instances of)
  * or belongs to a class (and thus, need to use the dot operator to access)
* In Java, instances of classes (objects) can be created by using a constructor
  * By default, a no-argument "default" constructor is provided by the language
  * If you define your own constructor, the default constructor goes away
  * You can define any number of constructors
  * Use your IDE to its fullest: Eclipse use `Source` -> `Generate Constructor Using Fields`
* In Java, Encapsulation is achieved with the *visibility* keywords:
  * `private` - only the class and instances of the class can "see" the variable (and thus change it)
  * `protected` - only the class and its subclasses can "see" the variable
  * `public` - *any* piece of code can "see" the variable
* Generally, you should make all variables `private` unless there is a Very Good Reason (VGR) to do otherwise
* Use *composition* to its fullest: classes can have ("own") *instances* of other classes as *member variables* 
* Composition defines a *has-a* relationship: an `Airport` has a(n) `Address`
* YAGNI: take care not to *overengineer* your objects (example: a name object to hold half dozen strings, last, first, middle, other middle, title, suffix, etc.)  
* You Ain't Gonna Need It

* Up to now: our objects (classes) are mere *data containers* or data "holders"
* Third part of encapsulation: make your objects functional
* IN general, any method that acts on an object's data belongs *in the class* 

# Overview

* History of OOP
* Many OOP languages: Smalltalk, C++, Objective-C; Java, C#
* Many other non-OOP languages have adopted OOP aspects: Python, PHP, JavaScript
* Facilitates real-world entities and modeling
* Clear demarcation of responsibility and functionality
* Modularity, decomposition

## Objects

* Formally, an object is a general entity characterized by:
  * Identify - an aspect that allows one instance of an object to be distinguishable from another instance of an object
  * State - a collection of properties (elements, attributes, data, fields, *member variables*) that define the object
  * Behavior - describes what an object does or how you can interact with it (methods, functions, etc)

* Communication between objects is achieved through *message sending*: you can invoke a member method in another object without having to worry about the "dispatch details"
* The public methods of an object is its "interface"
* Objects are *constructed* instead of just assigned
* Java uses the `new` keyword to invoke a constructor (method)
* A general approach to designing objects: "semantics dictate design"
  * Don't overengineer (YAGNI)
  * Break down an object into parts until: it is so simple that you can directly implement (`Address`) it or a class already exists (`LocalDate`)
  * Generally rely on *composition* to relate objects to each other:
  an `Airport` owns an `Address`
  * Avoid the god-antipattern: your class design becomes all-knowing, all-seeing all-doing

## Four Pillars

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Breaking Encapsulation

* Don't create mutable `public` variables unless there is a Very Good Reason to do so   
* Don't locate code that involves a class's object outside the class
* Use classes
  
## Abstraction

* Procedural Abstraction: how does `Math.sqrt()` work?
  * Babylonian interpolation method, the EDSAC method, etc.
  * Who cares?
  * A function's details are hidden from us
* Abstraction in OOP: how does `LocalDate` represent a date?
  * 3 integers
  * ISO8601 strings: "2020-02-06T20:33:50+00:00"
  * A single number (unix time, epoch)
  * Who cares? 
* Abstraction in OOP means you do not have to worry about implementation OR representation details
* Another example: how does Java represent a `String`

### Encapsulation

* Encapsulation:
  1. Grouping of data
  2. Protection of data
  3. Grouping of methods that act on that data

* Visibility keywords in Java:
  * `public`: any piece of code can "see" the member variable or method
  * `protected`: any subclass of the class can "see" the member variable or method
  * "package protected": the lack of a keyword makes the member visible to anything in the same package
  * `private`: only the class (and instances of the class) can "see" it
* Best practice: make all of your member variables `private` and control access through constructors and getters/setters
* Best practice: you should never separate the "behavior" of an object with its "state"
* Key idea: Abstraction and encapsulation often work together: abstraction allows access to an object's behavior and encapsulation hides the details

# Inheritance

* Inheritance is a mechanism by which objets can be *extended* to:
  * Inherit state and behavior form a *superclass* and/or
  * *Override* or augment state and behavior to provide more specific functionality
* Provides a way to *reuse* code
* Provides a way to *organize* code

* Example: `SavingsAccount`
  * IN Java, you can create subclasses using the `extends` keyword
  * If class `A extends B` then `A` is a subclass (child), `B` is a superclass (parent)
  * The `protected` keyword can be used to make variables visible to subclasses (`private` means only the class can see it)
  * All methods and state are *inherited* by the subclass
  * Inheritance defines an *is-a* relationship: `SavingsAccount` is a `BankAccount` and `CheckingAccount` is a `BankAccount`
  * Question: is a `BankAccount` a `SavingsAccount`?  Not necessarily (contravariance)
  * Question: is a `SavingsAccount` a `CheckingAccount`? Absolutely not (invariance)
  * A subclass can always be treated as its superclass (covariance)
  * A superclass *may* be treated as its subclass but it is not a very good idea (contravariance) 
  * A class may never be treated as its sibling/cousin (invariance)
  * The `abstract` keyword can be used in a class to make the class abstract: meaning you cannot instantiate instances of the class (useful if you design class such that the superclass is "incomplete")
  * An abstract class may have a mixture of `abstract` methods (methods with no defined implementation/no method body) and non-abstract methods (methods with an implementation).
  * Non-abstract subclasses of an abstract class *must* define an implementation for these methods
  * A *pure abstract* class in Java is an `interface`
  * A class `implements` an interface and must provide implementations of all the methods it defines

* When do you use an interface, abstract class or (non-abstract) regular class
  * If you you don't have any state and NO implementations: use an interface
  * When you do use an interface: keep it small!
  * Interfaces are more flexible because they don't lock you into a hierarchy
  * Interfaces are also more flexible because a class can implement multiple interfaces
  * You can only `extends` from one class 
  * If you DO have (some) state and *some* functionality: maybe an `abstract class` is what you want
    * Do you want to disallow users from instantiating your class (yes: you may want an abstract class)
    * Do you have some implementations but others are too generic ("abstract") to implement: then you probably want an abstract class so you can have some `abstract` methods
  * Otherwise, if you have a fully defined object (all state all methods), then make a non-abstract class!

### Inheritance Pitfalls

* Some languages do support *multiple inheritance* (C++)
  * Animal, Dog, Cat
  * What if you inherited from both a Dog and a Cat?
  * You'd be a `CatDog`
  * Diamond Problem: which class does the subclass inherit from?
  * C++: you have to explicitly say which class each method is derived from
  * Java does not support multiple inheritance
  * Java does allow you to implement multiple interfaces
  * Java 8 did (unfortunately) introduce default methods in interfaces
* Yo-yo antipattern
  * Deep inheritance hierarchies can be very complex
  * Instead in general prefer to have *shallow* hierarchies
  * Instead you could prefer *composition* over inheritance
* Rectangle Problem
  * If you have a flawed inheritance hierarchy and/or mutator functions that can *semantically* change what an object is, then you may run into the Rectangle problem.
  * Code-wise, the object is still of the subclass, but its properties redefine what it *is* (its semantics)
  * Solution: make sure your design is well-thoughout because it is VERY difficult to change a hierarchy later

## Polymorphism

* Poly=many, morph=form; polymorphism = having many forms
* IN the context of code, polymorphism is the ability for one variable, class or method to have multiple forms at different points in a program
* Example: Sorting in C: `qsort`: you have one function that can operate on any type (provided you give the appropriate comparator)
* Java: you can write a variable or method or class to apply to more than one type
* Example: collections illustrate parameterized polymorphism (that you can create a class that can be parameterized to any type)
* The following is a "raw type" that parameterizes to `Object` (do not do this)

```java
List numbers = new ArrayList();
```

### Subtype Polymorphism

* You can refer to any subclass as its super class
* Called covariance

```java
SavingsAccount s = new SavingsAccount(...);
BankAccount b = s;
```
* It allows you to utilize a common interface and treat all subtypes the same (as its supertype), reduces code, future proofs your code (if new subtypes are introduced later on)

### Function Overloading

* In C there are several functions to compute the absolute value: `fabs(), labs(), llabs(), abs()`, etc.
* Function overloading is the ability to define multiple functions with the same name but different parameters
* Example: `Math.abs()` there are four methods with the same name, but different parameter types
* As long as a function differs in the arity (number of) or type of parameters, the compiler can deduce which one you are calling
* The compiler is able to tell which one to call using *static dispatch*

### Operator Overloading

* An operator may have more than one meaning depending on the context
* In Java: 
  * `String + String` means: concatenation
  * `int + int` means: addition
  * `String + int` means: concatenation
* In PHP:
  * `String + String` means: juggle both and add
  * `String . String` means concatenation
* C++: MOST operators can be overloaded
  * `Set + Set`: union
  * `List + List`: Appending or union or maybe something else
  * `Time + Time`: way too many interpretations
* In general you want to avoid operator overloading
  * Far too ambiguous
  * At the end of the day, you have to write code to implement the operator overloading any way
  * If you have to write code, put it into a function, 
  * If you have a function, give a good name
  * If you have a good name, you have a well defined explanation

```java
String b = " World";
String s = "Hello" + b;

String b = " World";
String s = new StringBuilder().append("Hello").append(b).toString();
```  

* Python:
  * `String + String`: concatenation
  * `number + number`: addition
  * `String + number`: error: you'd have to wrap the number in `str()`
  
  
  ## Parameterized Polymorphism

  * Java allows you to *parameterize* a class, method or variable
  * Effectively makes the variable's *type* also variable
  * You essentially want to use parameterization to make as generic of code as possible so that you can apply it to as many different TYPES as you can
  * Saves you from having many multiple implementations for different types
  * You can also place *bounds* on your parameters to give you a *minimal interface*  using the `<T extends Foo>` syntax: then you are guaranteed that `T` is of type `Foo` (at least)

  # SOLID Principles

  * collection of design principles for OOP created by Robert Martin, Michael Feathers early 2000s

  ## S = Single Responsibility Principle

  * Encapsulation: a class should only have one responsibility
  * Classes should not be "god" classes
  * A `Person` class should be responsible for `Person` things, an `Address`
  class should be responsible for `Address` things
  * Load data from CSV files, then serialize it to XML or JSON files
  * Classes should generally be small
  * Large classes = more responsibility = less reusability

  ## O = Open/Closed Principle

  * Every unit (module or class) should be *open for extension* and *closed for modification*
  * Classical inheritance: extend the behavior in subclasses, but DO NOT modify the behavior in the super class
  * You can add fields or methods to subclasses to augment its state or behavior
  * Superclasses provide more general behavior that should NOT be modifiable: it should work *generally* and *generically* for all current types and any future types
  * Have a good, well-thought out inheritance because it is extremely difficult if not impossible to change later on

  ## Liskov Substitution Principle

  * If S is a subtype of T then objects of type T may be replaced with objects of type S without altering any of the desired properties of T.
  * Recall: the Shape-Rectangle-Square problem: having mutable methods that can semantically change an object's type violate the Liskov Substitution Principle

  ## Interface Segregation Principle

  * No client code should be forced to depend on methods it does not use
  * Example: `ClickEventHandler` interface that specified *both* `onClick()` and `onDoubleClick()` methods
  * When designing an interface: keep them as small as possible, maybe 1 or 2 methods at most (or even zero methods!)

  ## Dependency Inversion Principle

  * High-level modules (classes) should not depend on low-level modules
  * Both Should depend on abstractions
  * Example: say you have 3 libraries on a phone app that compute location
  * Library A: `double GPSLocator.getLatitude(), double GPSLocator.getLongitude()`
  * Library B: `AndroidLocation AndroidNative.getLocation()` (`AndroidLocation.getLatitude(), AndroidLocation.getLongitude()`
  * Library C: `CellTowerGPS.getLatitudeRad(), CellTowerGPS.getLongitudeRad()`

  ```java
  if(Library A) {
  return new Location(GPSLocator.getLatitude(), GPSLocator.getLongitude());
  } else if(Library B) {
  AndroidLocation l =  AndroidNative.getLocation();
  return new Location(l.getLatitude(), l.getLongitude());  
  } else if(Library C) {
  return new Location(Utils.radiansToDegrees(CellTowerGPS.getLatitudeRad()), Utils.radiansToDegrees(CellTowerGPS.getLongitudeRad()));
  }
  ```

  * The code above is the "client" code: it depends on the low-level classes `GPSLocator, AndroidNative, CellTowerGPS`
  * You can invert the dependency: define an interface `Locator` that specifies one method: `Location getLocation()`
  * You can implement 3 wrapper classes: `GPSLocator`, `AndroidLocator`, `CellLocator`, each one owns an instance (via composition) of a GPS locator library
  * Each wrapper class `implements` the interface

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  






















